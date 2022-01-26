package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button btn_update, btn_read, btn_create, btn_delete;
    TextInputEditText edt_nome, edt_idade, edt_id;
    SwitchMaterial switch_ativo;
    ListView lv_clientes;

    DataBaseHelper dataBaseHelper;
    ArrayAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create = findViewById(R.id.btn_create);
        btn_read = findViewById(R.id.btn_read);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        edt_nome = findViewById(R.id.edt_nome);
        edt_idade = findViewById(R.id.edt_idade);
        edt_id = findViewById(R.id.edt_id);
        switch_ativo = findViewById(R.id.switchActive);
        lv_clientes = findViewById(R.id.lv_clientes);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        popularLista(dataBaseHelper);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClasseCliente classeCliente;

                try {
                    classeCliente = new ClasseCliente(1, edt_nome.getText().toString(), Integer.parseInt(edt_idade.getText().toString()),switch_ativo.isChecked());
                    boolean sucesso = dataBaseHelper.adicionarCliente(classeCliente);
                    Toast.makeText(MainActivity.this, "Cliente adicionado!", Toast.LENGTH_SHORT).show();
                    popularLista(dataBaseHelper);

                } catch(Exception e){
                    Toast.makeText(MainActivity.this, "Erro, cliente não adicionado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popularLista(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Lista atualizada", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClasseCliente classeCliente;
                String idCliente = edt_id.getText().toString();
                try {
                    classeCliente = new ClasseCliente(1, edt_nome.getText().toString(), Integer.parseInt(edt_idade.getText().toString()), switch_ativo.isChecked());
                    boolean sucesso = dataBaseHelper.atualizarCliente(idCliente, classeCliente);
                    Toast.makeText(MainActivity.this, "Sucesso, cliente atualizado!", Toast.LENGTH_SHORT).show();
                    popularLista(dataBaseHelper);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Erro, cliente não atualizado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edt_id.getText().toString());
                dataBaseHelper.deletarCliente(id);
                Toast.makeText(MainActivity.this, "Cliente deletado do banco de dados", Toast.LENGTH_SHORT).show();
                popularLista(dataBaseHelper);
            }
        });

    }

    public void popularLista(DataBaseHelper dataBaseHelper){
        clienteAdapter = new ArrayAdapter<ClasseCliente>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.listaClientes());
        lv_clientes.setAdapter(clienteAdapter);
    }
}