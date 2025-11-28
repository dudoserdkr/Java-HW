package edu.hw10;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class RegexServer {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5000;
        new RegexServer().start(port);
    }

    public void start(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket client = serverSocket.accept();
                executor.submit(() -> handleClient(client));
            }
        }
    }

    private void handleClient(Socket socket) {
        try (socket;
             DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
             DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {

            String regex = RegexProtocol.readString(in);
            if (regex == null) {
                out.writeBoolean(false);
                RegexProtocol.writeString(out, "Missing regex.");
                out.flush();
                return;
            }

            Pattern pattern;
            try {
                pattern = Pattern.compile(regex);
            } catch (PatternSyntaxException e) {
                out.writeBoolean(false);
                RegexProtocol.writeString(out, "Invalid regex: " + e.getDescription());
                out.flush();
                return;
            }

            out.writeBoolean(true);
            out.flush();

            while (true) {
                String line = RegexProtocol.readString(in);
                if (line == null) {
                    break;
                }
                boolean match = pattern.matcher(line).find();
                out.writeBoolean(match);
                out.flush();
            }
        } catch (IOException ignored) {
        }
    }
}
