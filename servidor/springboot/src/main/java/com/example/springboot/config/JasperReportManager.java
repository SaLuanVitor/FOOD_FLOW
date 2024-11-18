package com.example.springboot.config;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.engine.export.*;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import java.util.HashMap;
import java.io.File;

public class JasperReportManager {

    /**
     * Função para gerar e salvar um relatório como PDF
     * 
     * @param jasperFilePath Caminho para o arquivo .jasper
     * @param outputPdfPath  Caminho onde o PDF será salvo
     * @param params         Parâmetros para preencher o relatório
     * @param dataSource     Fonte de dados para o relatório
     * @throws JRException Caso ocorra erro ao gerar o relatório
     */
    public static void generatePdf(String jasperFilePath, String outputPdfPath, HashMap<String, Object> params,
            JRDataSource dataSource) throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, params, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfPath);
        System.out.println("Relatório salvo como PDF em: " + outputPdfPath);
    }

    /**
     * Função para compilar um arquivo JRXML para .jasper
     * 
     * @param jrxmlFilePath Caminho para o arquivo .jrxml
     * @return Caminho do arquivo .jasper gerado
     * @throws JRException Caso ocorra erro na compilação
     */
    public static String compileJrxml(String jrxmlFilePath) throws JRException {
        String jasperFilePath = jrxmlFilePath.replace(".jrxml", ".jasper");
        JasperCompileManager.compileReportToFile(jrxmlFilePath, jasperFilePath);
        System.out.println("Arquivo JRXML compilado para: " + jasperFilePath);
        return jasperFilePath;
    }

    /**
     * Função para imprimir um relatório Jasper
     * 
     * @param jasperFilePath Caminho para o arquivo .jasper
     * @param params         Parâmetros para preencher o relatório
     * @param dataSource     Fonte de dados para o relatório
     * @throws JRException Caso ocorra erro ao imprimir
     */
    public static void printJasperReport(String jasperFilePath, HashMap<String, Object> params, JRDataSource dataSource)
            throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, params, dataSource);
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService == null) {
            throw new RuntimeException("Nenhuma impressora padrão encontrada!");
        }

        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        SimplePrintServiceExporterConfiguration config = new SimplePrintServiceExporterConfiguration();

        config.setPrintService(defaultPrintService);
        config.setPrintServiceAttributeSet(defaultPrintService.getAttributes());
        config.setDisplayPageDialog(false);
        config.setDisplayPrintDialog(false);

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setConfiguration(config);
        exporter.exportReport();

        System.out.println("Relatório enviado para impressão.");
    }

    public static void main(String[] args) {
        try {
            // Verifica se os argumentos foram passados corretamente
            if (args.length < 1) {
                System.out.println("Uso: java JasperReportManager <caminho_para_jrxml_ou_jasper>");
                return;
            }

            String filePath = args[0]; // Caminho do JRXML ou .jasper passado como argumento
            File jasperFile = new File(filePath.replace(".jrxml", ".jasper"));

            // Compilar JRXML se necessário
            String jasperFilePath;
            if (!jasperFile.exists()) {
                jasperFilePath = compileJrxml(filePath);
            } else {
                jasperFilePath = jasperFile.getAbsolutePath();
            }

            // Configurar parâmetros e fonte de dados
            HashMap<String, Object> params = new HashMap<>();
            params.put("PARAM_EXEMPLO", "Valor do parâmetro");
            JRDataSource dataSource = new JREmptyDataSource();

            // Caminho para salvar o PDF
            String outputPdfPath = "relatorios/relatorio_exemplo.pdf";

            // Gerar e salvar como PDF
            generatePdf(jasperFilePath, outputPdfPath, params, dataSource);

            // Imprimir o relatório
            printJasperReport(jasperFilePath, params, dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
