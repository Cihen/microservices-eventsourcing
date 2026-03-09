package com.thucchien02.employeeservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee API specification - thucchien02",
                description = "API docs for Employee service",
                version = "1.0",
                contact = @Contact(
                        name = "Nguyen Thuc Chien",
                        email = "thucchien02@gmail.com",
                        url = "https://www.facebook.com/thucchien02/"
                ),
                license = @License(
                        name = "NTC License",
                        url = "https://thucchien02.test/licenses"
                ),
                termsOfService = "https://thucchien02.test/terms"
        ),
        servers = {
                @Server(
                        description = "Local env",
                        url = "https://localhost:9002"
                ),
                @Server(
                        description = "Dev env",
                        url = "https://employee-service.dev.test.com"
                )
        }
)
public class OpenAPIConfig {
}
