package br.com.vasco.anistia.webservice;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.vasco.anistia.models.Socio;


public class HttpService extends AsyncTask<Socio, Void, ArrayList<Socio>>{

    public HttpService( ){
    }

    @Override
    protected void onPostExecute(ArrayList<Socio> socios) {
        super.onPostExecute(socios);
    }

    @Override
    protected ArrayList<Socio> doInBackground(Socio... socios) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Socio> entity = new HttpEntity<>(socios[0], headers);

        ResponseEntity<Socio[]> forEntity = restTemplate.postForEntity("http://10.5.114.254:8080/getSocio", entity, Socio[].class);
        socios = new Gson().fromJson(forEntity.toString(), Socio[].class);

        return new ArrayList<>(Arrays.asList(socios));
    }
}