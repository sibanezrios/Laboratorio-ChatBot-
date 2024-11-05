/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

/**
 *
 * @author NXQ
 */
public class Laboratorio {
    public static String Chat(String a) throws MalformedURLException, IOException {
            //mielda
        String modelName = "llama3.2:1b";
        String promptText = a;
        
        //url
        URL url = new URL("http://localhost:11434/api/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8;");
        conn.setRequestProperty("accept", "application/json");
        conn.setDoOutput(true);
        
        //Mandar vainoso
        String JsonInputString = String.format(
                "{\"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false}", modelName, promptText);
        // a a a 
        try (OutputStream os = conn.getOutputStream()) {
                byte[] input = JsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);           
            }
        // e e e 
        // Verificar si la respuesta es exitosa
            int code = conn.getResponseCode();
            System.out.println("Response code: " + code);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            
// Mostrar la respuesta en la consola
        System.out.println("Respuesta de Ollama: " + response.toString());
        
//eaeae
        JSONObject jsonresponse = new JSONObject(response.toString());
        String responseText = jsonresponse.getString("response");
        System.out.println("Respuesta: " + responseText);
            // Cerrar la conexión
            conn.disconnect();
        
            return responseText;
    }
    

    public static void main(String[] args) throws MalformedURLException, IOException {
        ChatApp frame = new ChatApp();
        frame.setVisible(true);
        frame.setSize(1000, 500);
        
    }
}