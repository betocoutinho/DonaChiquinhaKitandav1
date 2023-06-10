package com.example.donachiquinhakitandav1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CadastroProduto extends AppCompatActivity {

    EditText input_nomepr, input_preco;
    Button bt_menos50, bt_mais50, bt_adicionar, bt_voltar;

    List<Produto> lista;

    TableLayout tb_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        input_nomepr = findViewById(R.id.input_nomeproduto);
        input_preco = findViewById(R.id.input_preco);

        bt_menos50 = findViewById(R.id.bt_menos50);
        bt_mais50 = findViewById(R.id.bt_mais50);
        bt_adicionar = findViewById(R.id.bt_adicionar2);
        bt_voltar = findViewById(R.id.bt_voltarpr);

        tb_layout = findViewById(R.id.tbli_principal);

        lista = new ProdutoDB(CadastroProduto.this).buscarProdutos();

        if (!lista.isEmpty()){
            for (Produto item : lista) {
                TableRow row = new TableRow(this);

                TextView nomeProduto = new TextView(this);
                nomeProduto.setText(item.getNomeProduto());
                nomeProduto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                TextView preco = new TextView(this);
                preco.setText("R$" + String.valueOf(item.getPreco()));
                preco.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT
                );

                row.addView(nomeProduto, layoutParams);
                row.addView(preco, layoutParams);

                tb_layout.addView(row);
            }
        }



        bt_menos50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valorAtual = Double.parseDouble(input_preco.getText().toString());

                if(valorAtual > 0){
                    double novoValor = valorAtual - 0.50;
                    input_preco.setText(String.valueOf(novoValor));
                }
            }
        });

        bt_mais50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valorAtual = Double.parseDouble(input_preco.getText().toString());
                double novoValor = valorAtual + 0.50;
                input_preco.setText(String.valueOf(novoValor));

            }
        });

        bt_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeProduto = input_nomepr.getText().toString();
                double preco = Double.parseDouble(input_preco.getText().toString());

                if (nomeProduto.length() > 0 && preco > 0){
                    new ProdutoDB(CadastroProduto.this).inserirProduto(new Produto(nomeProduto, preco));
                    Toast.makeText(CadastroProduto.this, "Produto Cadastrado", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }else {
                    Toast.makeText(CadastroProduto.this, "Erro ao cadastrar: verifique os campos", Toast.LENGTH_SHORT).show();
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