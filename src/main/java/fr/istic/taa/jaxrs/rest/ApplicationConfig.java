package fr.istic.taa.jaxrs.rest;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "API Vente de Tickets de Concert",
                version = "1.0.0",
                description = "Backend REST pour la gestion des concerts, tickets, commandes et utilisateurs.",
                contact = @Contact(name = "Projet SIR")
        ),
        servers = {
                @Server(url = "http://localhost:8081/api", description = "Serveur local")
        }
)
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(OpenApiResource.class);

        classes.add(EvenementResource.class);
        classes.add(TicketResource.class);
        classes.add(CommandeResource.class);
        classes.add(ClientResource.class);
        classes.add(OrganisateurResource.class);
        classes.add(LieuResource.class);
        classes.add(GenreMusicalResource.class);
        classes.add(ArtisteResource.class);
        classes.add(CategorieTicketResource.class);
        classes.add(NotificationResource.class);
        classes.add(TransfertResource.class);
        classes.add(AdministrateurResource.class);
        classes.add(SwaggerUiResource.class);
        classes.add(OpenApiResource.class);

        return classes;
    }
}

