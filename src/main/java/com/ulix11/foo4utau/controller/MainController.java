package com.ulix11.foo4utau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ulix11.foo4utau.model.Tag;
import com.ulix11.foo4utau.model.TagService;

@Controller
public class MainController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/tag")
    public String formTag(Model model){
        model.addAttribute("tag", new Tag());
        return "formtag";
    }

    @PostMapping("/tag")
    public String formTag(@ModelAttribute Tag tag, Model model){
        TagService ts = context.getBean(TagService.class);
        ts.inserirTag(tag);
        return "sucesso";
    }

    @GetMapping("/tags")
    public String listarTags(Model model){
        TagService ts = context.getBean(TagService.class);
        List<Tag> lista = ts.obterTodasTags();
        model.addAttribute("tags", lista);
        return "listartags";
    }

    @GetMapping("/tag/{id}/atualizar")
    public String formAtualizarTag(Model model, @PathVariable int id){
        model.addAttribute("id", id);
        TagService ts = context.getBean(TagService.class);
        Tag tagAntiga = ts.obterTag(id);
        model.addAttribute("tag", tagAntiga);
        return "formupdatetag";
    }

    @PostMapping("/tag/{id}/atualizar")
    public String atualizarTag(Model model,
                               @PathVariable int id,
                               @ModelAttribute Tag tag){
        TagService ts = context.getBean(TagService.class);
        ts.atualizarTag(id, tag);
        return "redirect:/tags";
    }

    @PostMapping("/tag/{id}/excluir")
    public String excluirTag(@PathVariable int id){
        TagService ts = context.getBean(TagService.class);
        ts.excluirTag(id);
        return "redirect:/tags";
    }

}
