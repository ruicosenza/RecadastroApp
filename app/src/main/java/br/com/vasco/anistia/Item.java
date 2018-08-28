package br.com.vasco.anistia;

import java.util.ArrayList;

public class Item {
    private String nome;
    private String cpf;
    private String matricula;

    public Item(String nome, String cpf, String matricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public static ArrayList<Item> createMockResult(int numResult) {
        ArrayList<Item> resultItems = new ArrayList<Item>();

       /* for (int i = 0; i <= numResult; i++){
            resultItems.add(new Item("Budah", "CPF: 000.000.000-00", "MATRICULA: 99999999"));
        }*/

        return resultItems;
    }

}
