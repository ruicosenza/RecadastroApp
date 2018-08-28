package br.com.vasco.anistia.webservice;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import br.com.vasco.anistia.models.Socio;


public class HttpService extends AsyncTask<String, Void, ArrayList<Socio>>{

    public HttpService( ){
    }

    /*public Socio[] getSocioPorNome(String nome, String cpf, String matricula){
        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Socio[]> forEntity = restTemplate.getForEntity("http://192.168.0.17:8091/getSocio/?name=" + nome, Socio[].class);

            Socio[] socios = forEntity.getBody();

            for (int i = 0; i < socios.length; i++) {
                System.out.println(socios[i].getNome());
            }

            return socios;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }*/

    @Override
    protected ArrayList<Socio> doInBackground(String... strings) {
        StringBuilder resposta = new StringBuilder();
        ArrayList<Socio> socios = new ArrayList<>();

        URL url;
        String params = "nome=" + strings[0] + "&cpf=" + strings[1] + "&matricula=" + strings[2];
        try {
            url = new URL("http://192.168.0.15:8091/getSocio?" + params);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream()).useDelimiter("\\p{Zp}");
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }

            Socio[] array_socios = new Gson().fromJson(resposta.toString(), Socio[].class);

            socios = new ArrayList<>(Arrays.asList(array_socios));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return socios;
    }


}