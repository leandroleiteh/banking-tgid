package com.lebanking.api.application.controller;

import com.lebanking.api.application.dtos.input.CompanyInputDTO;
import com.lebanking.api.application.dtos.output.CompanyOutputDTO;
import com.lebanking.api.domain.services.CompanyService;
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
@RequestMapping("/lebanking/companys")
@Tag(name = "Companys")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(
            summary = "Criar uma nova Empresa",
            description = "Endpoint para criação de uma empresa",
            operationId = "create",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CompanyOutputDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<CompanyOutputDTO> create(@RequestBody @Valid CompanyInputDTO companyInputDTO) {
        CompanyOutputDTO companyOutputDTO = companyService.createCompany(companyInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyOutputDTO);
    }
}
