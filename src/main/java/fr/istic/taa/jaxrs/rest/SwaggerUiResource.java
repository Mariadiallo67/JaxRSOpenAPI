package fr.istic.taa.jaxrs.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/docs")
public class SwaggerUiResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String swaggerUi() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Swagger UI</title>
                    <link rel="stylesheet" href="https://unpkg.com/swagger-ui-dist/swagger-ui.css">
                </head>
                <body>
                    <div id="swagger-ui"></div>
                    <script src="https://unpkg.com/swagger-ui-dist/swagger-ui-bundle.js"></script>
                    <script>
                        window.onload = function() {
                            SwaggerUIBundle({
                                url: '/api/openapi.json',
                                dom_id: '#swagger-ui'
                            });
                        };
                    </script>
                </body>
                </html>
                """;
    }
}