package src.httpJava;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.InputStreamReader;

/**
 * FullResponseBuilder
 */
public class FullResponseBuilder {

    String response;

    public FullResponseBuilder(HttpURLConnection con) {

    }
    
    public static String getFullResponse(HttpURLConnection con) throws IOException {
        StringBuilder fullResponseBuilder = new StringBuilder();

        // read status and message
        fullResponseBuilder.append(con.getResponseCode())
            .append(" ")
            .append(con.getResponseMessage())
            .append("\n");

        if (con.getResponseCode() == 308) {

            fullResponseBuilder.append("Redirecting to  " + con.getHeaderField("Refresh").split(";url=")[1])
                .append("\n");

            String redirection = con.getHeaderField("Refresh").split(";url=")[1];
            URL url = new URL(redirection);

            con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");

            fullResponseBuilder.append(con.getResponseCode())
                .append(" ")
                .append(con.getResponseMessage())
                .append("\n");
        }


        // read headers
        con.getHeaderFields().entrySet().stream()
            .filter(entry -> entry.getKey() != null)
            .forEach(entry -> {
                fullResponseBuilder.append(entry.getKey()).append(": ");
                List headerValues = entry.getValue();
                Iterator it = headerValues.iterator();
                if (it.hasNext()) {
                    fullResponseBuilder.append(it.next());
                    while (it.hasNext()) {
                        fullResponseBuilder.append(", ").append(it.next());
                    }
                }
                fullResponseBuilder.append("\n");
        });

        // read response content
        Reader streamReader = null;
        if (con.getResponseCode() > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);

        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        
        in.close();
        fullResponseBuilder.append("\n")
            .append(content.toString())
            .append("\n");

        return fullResponseBuilder.toString();
    }

    public static void main(String[] args) {
        
        try {

            Scanner scanner = new Scanner(System.in);
            String uri = scanner.nextLine();
            scanner.close();
            URL url = new URL(uri);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");

            String response = FullResponseBuilder.getFullResponse(con);

            System.out.println(response);
        } catch (IOException io) {

        }
        
    }
}