package com.example.donachiquinhakitandav1;

public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private double creditoInicial;

    public Cliente(int id, String nome, String telefone, double creditoInicial) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.creditoInicial = creditoInicial;
    }

    public Cliente(String nome, String telefone, double creditoInicial) {
        this.nome = nome;
        this.telefone = telefone;
        this.creditoInicial = creditoInicial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getCreditoInicial() {
        return creditoInicial;
    }

    public void setCreditoInicial(double creditoInicial) {
        this.creditoInicial = creditoInicial;
    }
}