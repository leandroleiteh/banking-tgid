package com.lebanking.api.application.controller;

import com.lebanking.api.application.dtos.ClientInputDTO;
import com.lebanking.api.application.dtos.ClientOutputDTO;
import com.lebanking.api.domain.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lebanking/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientOutputDTO> create(@RequestBody ClientInputDTO beneficiaryInputDTO) {
        ClientOutputDTO clientOutputDTO = clientService.creatClient();
        return ResponseEntity.status(HttpStatus.CREATED).body(clientOutputDTO);
    }

}
