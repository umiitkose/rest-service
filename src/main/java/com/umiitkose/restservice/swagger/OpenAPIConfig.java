package com.umiitkose.restservice.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//https://springdoc.org/
//https://github.com/springdoc/springdoc-openapi

//http://localhost:8080/swagger-ui/index.html
@Configuration
public class OpenAPIConfig {

    private final static String devUrl="localhost:8080";
    private final static String prodUrl="canliURL:8080";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("umiitkose@gmail.com");
        contact.setName("Ümit KÖSE");
        contact.setUrl("https://www.umiitkose.com");

        License mitLicense = new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info()
                .title("Service API Documentation")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://localhost:8080/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }


}