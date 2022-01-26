package com.example.sqlite;

public class ClasseCliente {

    private int id;
    private String nome;
    private int idade;
    private boolean clienteAtivo;

    public ClasseCliente(int id, String nome, int idade, boolean clienteAtivo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.clienteAtivo = clienteAtivo;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isClienteAtivo() {
        return clienteAtivo;
    }

    public void setClienteAtivo(boolean clienteAtivo) {
        this.clienteAtivo = clienteAtivo;
    }
}
