package com.lebanking.api.domain.services;

import com.lebanking.api.application.dtos.input.ClientInputDTO;
import com.lebanking.api.application.dtos.output.ClientOutputDTO;
import com.lebanking.api.domain.model.Client;
import com.lebanking.api.domain.model.Company;

import java.util.UUID;

public interface ClientService {

    ClientOutputDTO createClient(ClientInputDTO clientInputDTO);

    ClientOutputDTO outputClientAssembler(Client client);

    void existingCpf(Client client);

    Client searchForCpfClient(String cpf);


}
