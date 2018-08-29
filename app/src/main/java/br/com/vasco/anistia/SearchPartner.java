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

    private TextView tvnome;
    private TextView tvcpf;
    private TextView tvmatricula;
    private TextView tvretornoVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_partner);

        final Button searchButton = findViewById(R.id.buscar_socio);
        tvnome = findViewById(R.id.input_nome);
        tvcpf = findViewById(R.id.input_cpf);
        tvmatricula = findViewById(R.id.input_matricula);
        tvretornoVazio = findViewById(R.id.retorno_vazio);

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                HttpService service = new HttpService();
                String nome = "";
                String cpf = "";
                String matricula = "";

                if( tvnome != null && tvnome.getText() != null){
                    nome = tvnome.getText().toString();
                }
                
                if( tvcpf != null && tvcpf.getText() != null ){
                    cpf = tvcpf.getText().toString();
                }

                if( tvmatricula != null && tvmatricula.getText() != null ){
                    matricula = tvmatricula.getText().toString();
                }

                try {
                    ArrayList<Socio> socios = service.execute(new Socio(nome, cpf, matricula)).get();

                    if( socios.isEmpty() || socios.size() == 0){
                        tvretornoVazio.setText(R.string.a_busca_nao_retornou_resultado);
                        /*startActivityForResult(SearchPartner.this, 300);*/
                    } else {
                        tvretornoVazio.setText("");
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