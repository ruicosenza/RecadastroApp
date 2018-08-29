package br.com.vasco.anistia.models;

import java.io.Serializable;

public class Socio implements Serializable{
    private Long id;
    private String nome;
    private String matricula;
    private String cpf;

    public Socio(String nome, String cpf, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}