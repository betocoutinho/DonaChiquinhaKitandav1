package com.example.donachiquinhakitandav1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroCliente extends AppCompatActivity {

    EditText edit_nome, edit_nomeRes, edit_telefRes, edit_credito;
    Button bt_voltar, bt_adicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edit_nome = findViewById(R.id.input_nome);
        edit_nomeRes = findViewById(R.id.input_nomeres);
        edit_telefRes = findViewById(R.id.input_telefres);
        edit_credito = findViewById(R.id.input_credito);

        bt_adicionar = findViewById(R.id.bt_adicionar);
        bt_voltar = findViewById(R.id.bt_voltar);

        bt_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_nome.getText().toString();
                String nomeRes = edit_nomeRes.getText().toString();
                String telefRes = edit_telefRes.getText().toString();
                double credito = Double.parseDouble(edit_credito.getText().toString());

                if(nome.length() > 0 && nomeRes.length() > 0){
                    new ClienteDB(CadastroCliente.this).inserirCliente(new Cliente(nome, nomeRes, telefRes, credito));
                    Toast.makeText(CadastroCliente.this, "Registro Adicionado com Sucesso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CadastroCliente.this, "Por favor Completar campos obrigatórios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}