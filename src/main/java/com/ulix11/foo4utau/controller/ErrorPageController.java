package com.ulix11.foo4utau.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.webmvc.error.ErrorAttributes;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ErrorPageController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public ErrorPageController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        ErrorAttributeOptions options = ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.STATUS,
                ErrorAttributeOptions.Include.ERROR,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.STACK_TRACE,
                ErrorAttributeOptions.Include.PATH);

        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, options);
        Throwable error = errorAttributes.getError(webRequest);

        model.addAttribute("status", attributes.getOrDefault("status", response.getStatus()));
        model.addAttribute("error", attributes.getOrDefault("error", "Erro inesperado"));
        model.addAttribute("message", attributes.getOrDefault("message", "Nenhuma mensagem disponivel."));
        model.addAttribute("exception", attributes.getOrDefault("exception", getExceptionName(error)));
        model.addAttribute("path", attributes.getOrDefault("path", request.getRequestURI()));
        model.addAttribute("trace", attributes.getOrDefault("trace", getStackTrace(error)));

        return "error";
    }

    private String getExceptionName(Throwable error) {
        if (error == null) {
            return "Nao disponivel";
        }

        return error.getClass().getName();
    }

    private String getStackTrace(Throwable error) {
        if (error == null) {
            return "Stack trace nao disponivel para esta requisicao.";
        }

        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        return stackTrace.toString();
    }

}
