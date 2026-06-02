package com.ulix11.foo4utau.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagDAO tdao;

    public void inserirTag(Tag tag){
        tdao.inserirTag(tag);
    }

    public Tag obterTag(int id){
        return tdao.obterTag(id);
    }

    public List<Tag> obterTodasTags(){
        return tdao.obterTodasTags();
    }

    public void atualizarTag(int id, Tag nova){
        tdao.atualizarTag(id, nova);
    }

    public void excluirTag(int id){
        tdao.excluirTag(id);
    }

}
