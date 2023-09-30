package com.raru.data;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import com.raru.utils.Utils;

public class DataBase {
    private static HttpRequest.Builder reqBuilder;
    private static HttpClient client = HttpClient.newHttpClient();

    static {
        try {
            reqBuilder = HttpRequest
                    .newBuilder()
                    .uri(new URI("http://localhost:8000/sql"))
                    .header("Accept", "application/json")
                    .header("NS", "TUCN")
                    .header("DB", "ptass3")
                    .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("root:root".getBytes()));
        } catch (Exception e) {
            Utils.catchHelper(e);
        }
    }

    private DataBase() {
    }

    /**
     * Loads the defined database schema from the specified file.
     */
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("schema.surrealql");
        String schema = String.join("\n", Files.readAllLines(path));
        System.out.println(_run(schema).toString(4));
    }

    /**
     * Executes a query by sending an HTTP request to the database server with the
     * query string.
     *
     * @param query The query string to execute.
     * @return The JSON response.
     * @throws DBException If an error occurs during the query execution or if the
     *                     response indicates an error.
     */
    private static JSONObject _run(String query) throws DBException {
        System.out.println("Running query: " + query);

        try {
            var req = reqBuilder.POST(BodyPublishers.ofString(query)).build();
            HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
            JSONObject json;

            if (res.statusCode() != 200)
                queryError(new JSONObject(res.body()));

            json = new JSONArray(res.body()).getJSONObject(0);

            if (!json.getString("status").equals("OK"))
                throw new DBException(json.getString("detail"));

            return json;
        } catch (IOException e) {
            Utils.catchHelper(e);
        } catch (InterruptedException e) {
            Utils.catchHelper(e);
        }

        return null;
    }

    public static JSONArray run(String query) throws DBException {
        return _run(query).getJSONArray("result");
    }

    private static void queryError(JSONObject json) {
        System.err.println("Error running query");
        System.err.println(json.toString(4));
        System.exit(1);
    }
}
