package com.example.donachiquinhakitandav1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_addcliente, bt_addproduto, add_vendas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_addcliente = findViewById(R.id.bt_cliente);
        bt_addproduto = findViewById(R.id.bt_produto);
        add_vendas = findViewById(R.id.bt_venda);

        bt_addcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroCliente.class);
                startActivity(intent);
            }
        });

        bt_addproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroProduto.class);
                startActivity(intent);
            }
        });

        add_vendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Venda.class);
                startActivity(intent);
            }
        });
    }
}