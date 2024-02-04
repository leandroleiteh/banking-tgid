package com.lebanking.api.domain.services.implementation;

import com.lebanking.api.application.dtos.input.ClientInputDTO;
import com.lebanking.api.application.dtos.output.ClientOutputDTO;
import com.lebanking.api.common.exception.ResourceNotFoundException;
import com.lebanking.api.domain.model.Client;
import com.lebanking.api.domain.services.ClientService;
import com.lebanking.api.infrastructure.repositorys.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientOutputDTO createClient(ClientInputDTO clientInputDTO) {
        Client client = new Client(
                clientInputDTO.name(),
                clientInputDTO.cpf(),
                clientInputDTO.birthDate(),
                clientInputDTO.tel(),
                clientInputDTO.mail(),
                clientInputDTO.balance());

        existingCpf(client);
        return outputClientAssembler(clientRepository.save(client));
    }

    @Override
    public ClientOutputDTO outputClientAssembler(Client client) {
        return new ClientOutputDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getBirthDate(),
                client.getTel(),
                client.getMail(),
                client.getBalance());
    }

    @Override
    public void existingCpf(Client client) {
        boolean existing = clientRepository.findByCpf(client.getCpf())
                .stream()
                .anyMatch(e -> !e.equals(client));
        if (existing) throw new ResourceNotFoundException
                ("Não foi possível o cadastro do Cliente " + client.getName().toUpperCase() + ". O mesmo já existe.");
    }

    @Override
    public Client searchForCpfClient(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(
                () -> new ResourceNotFoundException("Cliente com CPF: " + cpf + " não encontrado!")
        );
    }
}
