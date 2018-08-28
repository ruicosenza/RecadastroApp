package br.com.vasco.anistia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vasco.anistia.models.Socio;
import br.com.vasco.anistia.webservice.HttpService;

public class SearchPartner extends AppCompatActivity {

    private TextView tnome;
    private TextView tcpf;
    private TextView tmatricula;
    private TextView retornoVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_partner);

        final Button searchButton = findViewById(R.id.buscar_socio);
        tnome = findViewById(R.id.input_nome);
        tcpf = findViewById(R.id.input_cpf);
        tmatricula = findViewById(R.id.input_matricula);
        retornoVazio = findViewById(R.id.retorno_vazio);

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                HttpService service = new HttpService();
                String nome = "";
                String cpf = "";
                String matricula = "";

                if( tnome != null && tnome.getText() != null){
                    nome = tnome.getText().toString();   
                }
                
                if( tcpf != null && tcpf.getText() != null ){
                    cpf = tcpf.getText().toString();
                }

                if( tmatricula != null && tmatricula.getText() != null ){
                    matricula = tmatricula.getText().toString();
                }

                try {
                    ArrayList<Socio> socios = service.execute(nome, cpf, matricula).get();

                    if( socios.isEmpty() || socios.size() == 0){
                        retornoVazio.setText("A busca não retornou resultado");
                        /*startActivityForResult(SearchPartner.this, 300);*/
                    } else {
                        retornoVazio.setText("");
                        for (Socio s : socios) {
                            System.out.println(s.getNome());
                        }

                        Intent searchResultIndent = new Intent(getBaseContext(), SearchResult.class);
                        searchResultIndent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        searchResultIndent.putExtra("lista_socios", socios);
                        startActivityForResult(searchResultIndent,2);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}