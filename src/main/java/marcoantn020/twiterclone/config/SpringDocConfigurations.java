package marcoantn020.twiterclone.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Clone Mini-Tweeter")
                        .description("API Rest que simula parte da funcionalidade do tweeter onde é possivel ver o feed, publicar um tweet apagar o mesmo, o usuário não pode excluir um tweet que não seja o seu a menos que seja um usuário administrador.")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("marco@marco.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://localhost:8080/swagger-ui/index.html")));
    }


}