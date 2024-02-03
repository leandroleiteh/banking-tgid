package com.lebanking.api.domain.services;

import com.lebanking.api.application.dtos.ClientInputDTO;
import com.lebanking.api.application.dtos.ClientOutputDTO;
import com.lebanking.api.domain.model.Client;
import com.lebanking.api.application.exception.ResourceNotFoundException;
import com.lebanking.api.infrastructure.repositorys.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public ClientOutputDTO creatClient(ClientInputDTO clientInputDTO) {

        Client client = new Client(
                clientInputDTO.name(),
                clientInputDTO.cpf(),
                clientInputDTO.birthDate(),
                clientInputDTO.tel(),
                clientInputDTO.mail(),
                clientInputDTO.balance());

        existingCpf(client);

        return outputBuildingAssembler(buildingRepository.save(buildingEntity));
    }

    private void existingCpf(Client client) {
        boolean existing = clientRepository.findByCpf(client.setCpf())
                .stream()
                .anyMatch(e -> !e.equals(client));
        if (existing) throw new ResourceNotFoundException
                ("Não foi possível o cadastro do Cliente " + client.getName().toUpperCase() + ". O mesmo já existe.");
    }
}
