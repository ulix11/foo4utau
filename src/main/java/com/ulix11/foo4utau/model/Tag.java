package com.ulix11.foo4utau.model;

import java.util.Map;

public class Tag {

    private int idTag;
    private String nome;

    public Tag(){}

    public Tag(String nome){
        this.nome = nome;
    }

    public Tag(int idTag, String nome){
        this.idTag = idTag;
        this.nome = nome;
    }

    public int getIdTag() {
        return idTag;
    }

    public String getNome() {
        return nome;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static Tag converterRegistros(Map<String,Object> registros){
        int idTag = (int) registros.get("id_tag");
        String nome = (String) registros.get("nome");
        return new Tag(idTag, nome);
    }

}
