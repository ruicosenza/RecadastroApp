package br.com.vasco.anistia;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import br.com.vasco.anistia.models.Socio;

public class SearchResult extends AppCompatActivity {

    private ArrayList<Socio> resultadoBusca;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    finish();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RecyclerView rvResultadoBusca = findViewById(R.id.rv_resultado);
        Bundle extra = getIntent().getExtras();
        ArrayList<Socio> socios = (ArrayList<Socio>) extra.getSerializable("lista_socios");

        /*resultadoBusca = Item.createMockResult(30);*/

        resultadoBusca = socios;

        ResultAdapter adapter = new ResultAdapter(resultadoBusca);

        rvResultadoBusca.setAdapter(adapter);

        rvResultadoBusca.setLayoutManager(new LinearLayoutManager(this));

    }

}
