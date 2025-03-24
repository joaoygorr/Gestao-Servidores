package br.com.gestaoServidores.core.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão de Servidores")
                        .description("""
                            API Rest para gestão de servidores, com CRUD completo para as entidades Servidor Efetivo, Servidor Temporário, Unidade e Lotação. 
                            A API permite a inclusão e edição dos dados das tabelas relacionadas, além de disponibilizar os seguintes endpoints:
                            - Consultar servidores efetivos lotados em uma unidade específica.
                            - Consultar o endereço funcional da unidade de lotação de um servidor efetivo.
                            - Upload de fotografias para o Min.IO com links temporários para recuperação das imagens.
                            """)
                        .version("v1")
                        .termsOfService("https://github.com/joaoygorr/Gestao-Servidores")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/joaoygorr/Gestao-Servidores")));
//                .components(new Components()
//                        .addSecuritySchemes("bearer-key",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer").bearerFormat("JWT")));
    }
}
