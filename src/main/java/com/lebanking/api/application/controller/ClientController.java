package com.lebanking.api.application.controller;

import com.lebanking.api.application.dtos.input.ClientInputDTO;
import com.lebanking.api.application.dtos.output.ClientOutputDTO;
import com.lebanking.api.domain.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lebanking/clients")
@Tag(name = "Client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @Operation(
            summary = "Criar um novo Cliente",
            description = "Endpoint para criação de um novo cleinte",
            operationId = "create",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientOutputDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<ClientOutputDTO> create(@RequestBody @Valid ClientInputDTO clientInputDTO) {
        ClientOutputDTO clientOutputDTO = clientService.createClient(clientInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientOutputDTO);
    }

}
