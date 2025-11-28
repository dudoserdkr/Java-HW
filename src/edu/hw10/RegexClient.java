package edu.hw10;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class  RegexClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            System.out.println("Usage: java edu.hw10.RegexClient <host> <port> <regex> <filePath>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String regex = args[2];
        Path filePath = Path.of(args[3]);

        try (Socket socket = new Socket(host, port);
             DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
             DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
             BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {

            RegexProtocol.writeString(out, regex);
            out.flush();

            boolean ok = in.readBoolean();
            if (!ok) {
                String error = RegexProtocol.readString(in);
                System.out.println("Server error: " + (error == null ? "Unknown error." : error));
                return;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                RegexProtocol.writeString(out, line);
                out.flush();

                boolean match = in.readBoolean();
                String verdict = match ? "MATCH" : "NO_MATCH";
                System.out.println(verdict + ": " + line);
            }

            RegexProtocol.writeEnd(out);
            out.flush();
        }
    }
}
