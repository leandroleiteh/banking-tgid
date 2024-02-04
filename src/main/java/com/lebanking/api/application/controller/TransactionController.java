package com.lebanking.api.application.controller;

import com.lebanking.api.application.dtos.input.TransactionInputDTO;
import com.lebanking.api.application.dtos.output.TransactionOutputDTO;
import com.lebanking.api.domain.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lebanking/transactions")
@Tag(name = "Transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Operation(
            summary = "Criar um novo Depósito",
            description = "Endpoint para criação de um Depósito",
            operationId = "createDeposit",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionOutputDTO.class)))
            }
    )
    @PostMapping("/deposit")
    public ResponseEntity<TransactionOutputDTO> createDeposit(@RequestBody @Valid TransactionInputDTO transactionInputDTO) {
        TransactionOutputDTO transactionOutputDTO = transactionService.createDeposit(transactionInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionOutputDTO);
    }


    @Operation(
            summary = "Criar um novo Saque",
            description = "Endpoint para criação de um Saque",
            operationId = "createWithdrawal",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionOutputDTO.class)))
            }
    )
    @PostMapping("/withDrawal")
    public ResponseEntity<TransactionOutputDTO> createWithdrawal(@RequestBody @Valid TransactionInputDTO transactionInputDTO) {
        TransactionOutputDTO transactionOutputDTO = transactionService.createWithDrawal(transactionInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionOutputDTO);
    }

    @Operation(
            summary = "Listar todas Transaçœs",
            description = "Endpoint para listar todasTransaçœs",
            operationId = "listAll",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionOutputDTO.class)))
            }
    )
    @GetMapping
    public ResponseEntity<Page<TransactionOutputDTO>> listAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageRequest = PageRequest.of(page, size);

        Page<TransactionOutputDTO> transactionOutputDTOS = transactionService.findAllTransactions(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(transactionOutputDTOS);
    }
}
