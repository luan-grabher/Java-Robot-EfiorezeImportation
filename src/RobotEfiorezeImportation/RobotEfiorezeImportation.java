package RobotEfiorezeImportation;

import Entity.Executavel;
import Robo.AppRobo;
import TemplateContabil.Control.ControleTemplates;
import TemplateContabil.Model.Entity.Importation;
import fileManager.FileManager;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.ini4j.Ini;

public class RobotEfiorezeImportation {

    private static String nomeApp = "";

    public static void main(String[] args) {
        try {
            AppRobo robo = new AppRobo(nomeApp);

            robo.definirParametros();

            String pastaEmpresa = robo.getParametro("pastaEmpresa").getString();
            String pastaAnual = "Extratos"; //robo.getParametro("pastaAnual").getString();
            String pastaMensal = ""; //robo.getParametro("pastaMensal").getString();
            String config = robo.getParametro("config").getString();

            //Ini
            Ini ini = new Ini(FileManager.getFile("efioreze.ini"));

            //Colunas
            Map<String, String> colunas = new HashMap<>();
            colunas.put("data", ini.get("Colunas", "data"));
            colunas.put("documento", ini.get("Colunas", "documento"));
            colunas.put("pretexto", ini.get("Colunas", "pretexto"));
            colunas.put("historico", ini.get("Colunas", "historico"));
            colunas.put("entrada", ini.get("Colunas", "entrada"));
            colunas.put("saida", ini.get("Colunas", "saida"));
            colunas.put("valor", ini.get("Colunas", "valor"));

            int mes = robo.getParametro("mes").getMes();
            int ano = robo.getParametro("ano").getInteger();
            nomeApp = "Importação " + pastaEmpresa + " Bancos ";

            robo.setNome(nomeApp);
            robo.executar(
                    //Banrisul
                    principal(mes, ano, pastaEmpresa, pastaAnual, pastaMensal,
                            "Banrisul", config + "13", "banri;concili;.pdf", colunas)
                    + //Banco do Brasil
                    principal(mes, ano, pastaEmpresa, pastaAnual, pastaMensal,
                            "Banco do Brasil", config + "17", "bb;concili;.pdf", colunas)
                    + //CEF
                    principal(mes, ano, pastaEmpresa, pastaAnual, pastaMensal,
                            "CEF", config + "10", "cef;concili;.pdf", colunas)
                    + //Sicredi
                    principal(mes, ano, pastaEmpresa, pastaAnual, pastaMensal,
                            "Sicredi", config + "2138", "sicredi;concili;.pdf", colunas)
            );
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na aplicação: " + e);
            System.exit(0);
        }
    }

    public static String principal(int mes, int ano, String pastaEmpresa, String pastaAnual, String pastaMensal, String nomeTemplate, String idTemplate, String filtroArquivo, Map<String, String> colunas) {
        try {
            Importation importation = new Importation(Importation.TIPO_EXCEL);
            importation.setIdTemplateConfig(idTemplate);
            importation.getExcelCols().putAll(colunas);
            importation.setNome(nomeTemplate);

            ControleTemplates controle = new ControleTemplates(mes, ano);
            controle.setPastaEscMensal(pastaEmpresa);
            controle.setPasta(pastaAnual, pastaMensal);

            Map<String, Executavel> execs = new LinkedHashMap<>();
            execs.put("Procurando arquivo", controle.new defineArquivoNaImportacao(filtroArquivo, importation));
            execs.put("Criando template", controle.new converterArquivoParaTemplate(importation));

            return AppRobo.rodarExecutaveis(nomeApp, execs);
        } catch (Exception e) {
            return "Ocorreu um erro no Java: " + e;
        }
    }

}
