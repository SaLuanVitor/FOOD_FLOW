package com.example.springboot.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    DataSource dataSource;
    private Connection conn;

    public JasperReport compileSubReport() throws JRException, IOException {
        ClassPathResource resource = new ClassPathResource("relatorios/mais_vendidos.jrxml");
        InputStream input = resource.getInputStream();
        return JasperCompileManager.compileReport(input);
    }

    @SuppressWarnings("deprecation")
    @GetMapping("/gerarRelatorio")
    public void gerarRelatorio(HttpServletResponse response)
            throws SQLException, IOException, JRException {
        List<JasperPrint> jasperPrints = new ArrayList<>();

        JasperReport subreport = compileSubReport();

        ClassPathResource logoResource = new ClassPathResource("static/img/logo_marrom.jpg"); // Caminho para a imagem
        InputStream img_logo = logoResource.getInputStream();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("subreport", subreport);
        parametros.put("img_logo", img_logo); // minha imagem png aqui

        conn = dataSource.getConnection();

        ClassPathResource resource = new ClassPathResource("relatorios/novo_fluxo.jrxml");
        InputStream input = resource.getInputStream();
        JasperReport compileReport = JasperCompileManager.compileReport(input);

        JasperPrint print = JasperFillManager.fillReport(compileReport, parametros, conn);
        jasperPrints.add(print);

        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        byte[] bytes = out.toByteArray();

        response.reset();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");

        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        conn.close();
    }

}
