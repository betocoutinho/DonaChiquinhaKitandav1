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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Venda extends AppCompatActivity {

    EditText input_nomeCliente, input_produto, input_quant;
    Button bt_searchnome, bt_searchproduto, bt_meno1, bt_mais1,
            bt_add_Prod, bt_fechar_venda, bt_voltar;
    TextView credito_text, valor_text, preco_text;
    TableLayout tb_layout;

    Produto produtoSelecionado = null;
    List<Produto> listaProduto = new ArrayList<>();
    List<Cliente> listaClientes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        //Busca os dados correspondentes
        listaClientes = new ClienteDB(Venda.this).buscarClientes();
        listaProduto = new ProdutoDB(Venda.this).buscarProdutos();

        input_nomeCliente = findViewById(R.id.input_nomecliente);
        input_produto = findViewById(R.id.input_produto2);
        input_quant = findViewById(R.id.input_quant);

        credito_text = findViewById(R.id.credito_text);
        valor_text = findViewById(R.id.valor_text);
        preco_text = findViewById(R.id.preco_text);

        tb_layout = findViewById(R.id.tb_vendalayout);

        bt_searchnome = findViewById(R.id.bt_searchnome);
        bt_searchnome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listaClientes.isEmpty()){
                    String nomeCliente = input_nomeCliente.getText().toString().toUpperCase();
                    Cliente cliente = null;

                    if(!nomeCliente.isEmpty()){
                        for (Cliente cl : listaClientes) {
                            if (cl.getNome().equals(nomeCliente)){
                                cliente = cl;
                            }
                        }

                        if(cliente != null){
                            credito_text.setText(Double.toString(cliente.getCreditoInicial()));
                        }else {
                            Toast.makeText(Venda.this, "Cliente não encontrado", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(Venda.this, "Por favor informe o nome do cliente", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Venda.this, "Os cliente ainda não foram cadastrados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_searchproduto = findViewById(R.id.bt_searchproduto);
        bt_searchproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!listaProduto.isEmpty()){
                    String produto = input_produto.getText().toString().toUpperCase();

                    if(!produto.isEmpty()){
                        for (Produto pr : listaProduto) {
                            if (pr.getNomeProduto().equals(produto)){
                                produtoSelecionado = pr;
                            }
                        }

                        if(produtoSelecionado != null){
                            preco_text.setText(Double.toString(produtoSelecionado.getPreco()));
                        }else {
                            Toast.makeText(Venda.this, "Produto não encontrado", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(Venda.this, "Por favor informe o nome do Produto", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Venda.this, "Os Produtos ainda não foram cadastrados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_meno1 = findViewById(R.id.bt_menos1);
        bt_meno1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preco = preco_text.getText().toString();
                double quant = Double.parseDouble(input_quant.getText().toString());

                if(!preco.isEmpty() && quant > 1){
                    quant--;
                    double value = Double.parseDouble(preco) * quant;

                    input_quant.setText(Double.toString(quant));
                    preco_text.setText(Double.toString(value));
                }
            }
        });

        bt_mais1 = findViewById(R.id.bt_mais1);
        bt_mais1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preco = preco_text.getText().toString();
                double quant = Double.parseDouble(input_quant.getText().toString());

                if(!preco.isEmpty()){
                    quant++;
                    double value = Double.parseDouble(preco) * quant;

                    input_quant.setText(Double.toString(quant));
                    preco_text.setText(Double.toString(value));
                }
            }
        });

        bt_add_Prod = findViewById(R.id.bt_add_prod);
        bt_add_Prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quant = input_quant.getText().toString();
                String nomeProd = input_produto.getText().toString();
                String sub = preco_text.getText().toString();

                if(!sub.isEmpty()){
                    TableRow row = new TableRow(Venda.this);

                    TextView viewQuant = new TextView(Venda.this);
                    viewQuant.setText(quant);
                    viewQuant.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

                    TextView nomeProView = new TextView(Venda.this);
                    nomeProView.setText(nomeProd);
                    nomeProView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

                    TextView subView = new TextView(Venda.this);
                    subView.setText(sub);
                    subView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT
                    );

                    row.addView(viewQuant, layoutParams);
                    row.addView(nomeProView, layoutParams);
                    row.addView(subView, layoutParams);

                    tb_layout.addView(row);

                    double valor = Double.parseDouble(valor_text.getText().toString());
                    double soma = valor + Double.parseDouble(sub);
                    valor_text.setText(Double.toString(soma));
                }
            }
        });

        bt_fechar_venda = findViewById(R.id.bt_fechar_venda);
        bt_fechar_venda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valor = Double.parseDouble(valor_text.getText().toString());

                if(valor > 0){
                    Toast.makeText(Venda.this, "Venda Realizada com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(Venda.this, "A lista de produtos esta vazia", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_voltar = findViewById(R.id.bt_voltarpr2);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}