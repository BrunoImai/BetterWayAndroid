package com.example.betterwayfinal.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterwayfinal.Activity.Adapter.RecyclerAdapter;
import com.example.betterwayfinal.Activity.Helper.DBHelper;
import com.example.betterwayfinal.Activity.Helper.RecyclerItemClickListener;
import com.example.betterwayfinal.Activity.model.CoordenadasDAO;
import com.example.betterwayfinal.Activity.model.Recycler;
import com.example.betterwayfinal.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotificacaoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<Recycler> listaDeCord = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        FloatingActionButton fabconfig = findViewById(R.id.fab_config);
        fabconfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarCoordenada.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fabUsuario = findViewById(R.id.fab_usuario);
        fabUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UsuarioActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fabMapa = findViewById(R.id.fab_mapa);
        fabMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapaActivity.class);
                startActivity(intent);
            }
        });

        //Configurando RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        DBHelper db = new DBHelper( getApplicationContext() );

        //Adicionando evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }
    @Override
    protected void onStart() {
        carregarRecyclerView();
        super.onStart();
    }

    public void carregarRecyclerView(){
        //Listar tarefas
        CoordenadasDAO cord = new CoordenadasDAO( getApplicationContext() );

        //Exibe lista de Cordenadas no Recyclerview
        listaDeCord = cord.listar();
        //Configurar um adapters
        recyclerAdapter = new RecyclerAdapter(cord.listar());
        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( recyclerAdapter );
    }
}
