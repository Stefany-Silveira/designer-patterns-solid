package chainofresponsibility;

import chainofresponsibility.middlewares.CheckPermissionMiddleware;
import chainofresponsibility.middlewares.CheckUserMiddleware;
import chainofresponsibility.middlewares.Middleware;
import chainofresponsibility.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    public static void init() {
        server = new Server();
        server.registerUser("stefany@gmail.com.br", "8765rtyuTRE##%");
        server.registerUser("user@gmail.com.br", "123456");

        Middleware middleware = new CheckUserMiddleware(server);
        middleware.linkWith(new CheckPermissionMiddleware());

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean done;

        do {
            System.out.println("Digite o e-mail: ");
            String email = reader.readLine();
            System.out.println("Digite a senha: ");
            String password = reader.readLine();
            done = server.logIn(email, password);
        } while (!done);
    }
}
