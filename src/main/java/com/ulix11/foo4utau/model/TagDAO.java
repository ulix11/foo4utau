package com.ulix11.foo4utau.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class TagDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirTag(Tag tag){
        String sql = "INSERT INTO tb_tags(nome) VALUES(?)";
        Object[] obj = new Object[1];
        obj[0] = tag.getNome();
        jdbc.update(sql, obj);
    }

    public void atualizarTag(int id, Tag nova){
        String sql = "UPDATE tb_tags SET nome = ? WHERE id_tag = ?";
        Object[] obj = new Object[2];
        obj[0] = nova.getNome();
        obj[1] = id;
        jdbc.update(sql, obj);
    }

    public void excluirTag(int id){
        String sql = "DELETE FROM tb_tags WHERE id_tag = ?";
        jdbc.update(sql, id);
    }

    public Tag obterTag(int id){
        String sql = "SELECT * FROM tb_tags WHERE id_tag = ?";
        return Tag.converterRegistros((Map<String,Object>) jdbc.queryForMap(sql, id));
    }

    public List<Tag> obterTodasTags(){
        String sql = "SELECT * FROM tb_tags ORDER BY id_tag";
        List<Map<String,Object>> listaRegistros = jdbc.queryForList(sql);
        ArrayList<Tag> aux = new ArrayList<>();
        for(Map<String,Object> registro : listaRegistros){
            aux.add(Tag.converterRegistros(registro));
        }
        return aux;
    }

}
