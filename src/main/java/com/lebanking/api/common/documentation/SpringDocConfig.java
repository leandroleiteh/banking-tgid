package com.lebanking.api.common.documentation;

import com.lebanking.api.application.exceptionhandler.Problem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
@OpenAPIDefinition
public class SpringDocConfig {

    private static final String BAD_REQUEST_RESPONSE = "BadRequestResponse";
    private static final String NOT_FOUND_RESPONSE = "NotFoundResponse";
    private static final String INTERNAL_SERVER_ERROR_RESPONSE = "InternalServerErrorResponse";

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("TGID - Teste desenvolvedor Java Jr")
                        .version("1.0.0")
                        .description("API para transações")
                        .contact(new Contact()
                                .name("Leandro Leite")
                                .email("leandroluz201616@gmail.com")
                        )
                )
                .tags(List.of(
                                new Tag().name("Transactions").description("API de Transaçœs"),
                                new Tag().name("Client").description("API de Cadastro de Clientes"),
                                new Tag().name("Companys").description("API de Cadastro de Empresas")


                        )
                )
                .components(
                        new Components()
                                .responses(gerarResponses())
                );
    }


    private Map<String, ApiResponse> gerarResponses() {

        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problem>().$ref("Problem")));

        apiResponseMap.put(BAD_REQUEST_RESPONSE, new ApiResponse()
                .description("Bad Request")
                .content(content));

        apiResponseMap.put(NOT_FOUND_RESPONSE, new ApiResponse()
                .description("Not Found")
                .content(content));


        apiResponseMap.put(INTERNAL_SERVER_ERROR_RESPONSE, new ApiResponse()
                .description("Internal Server Error Response")
                .content(content));

        return apiResponseMap;
    }
}