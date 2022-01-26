package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button btn_update, btn_read, btn_create, btn_delete;
    TextInputEditText edt_nome, edt_idade;
    SwitchMaterial switch_ativo;

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
        switch_ativo = findViewById(R.id.switchActive);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClasseCliente classeCliente;

                try {
                    classeCliente = new ClasseCliente(1, edt_nome.getText().toString(), Integer.parseInt(edt_idade.getText().toString()),switch_ativo.isChecked());
                    boolean sucesso = dataBaseHelper.adicionarCliente(classeCliente);
                    Toast.makeText(MainActivity.this, "Cliente adicionado! " + classeCliente.toString(), Toast.LENGTH_SHORT).show();

                } catch(Exception e){
                    Toast.makeText(MainActivity.this, "Erro, cliente não adicionado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Botão ler", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Botão atualizar", Toast.LENGTH_SHORT).show();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Botão deletar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}