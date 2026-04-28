package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.rest.ApplicationConfig;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class Main {

    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        server.start(
                Undertow.builder()
                        .addHttpListener(8081, "localhost")
        );

        server.deploy(ApplicationConfig.class, "/");

        System.out.println("API démarrée : http://localhost:8081/api");
        System.out.println("OpenAPI : http://localhost:8081/api/docs");
    }
}