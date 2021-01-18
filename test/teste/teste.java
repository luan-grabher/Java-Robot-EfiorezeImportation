package teste;

import RobotEfiorezeImportation.RobotEfiorezeImportation;
import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class teste {

    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        str.add("first");
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");

        while (str.size() > 1) {
            str.remove(str.size() - 1);
        }

        System.out.println(str);
    }

    public static void regexText() {
        String str1 = "Pgto.doc : 012357/4 - Passalacqua (EST02160/4)";
        String str2 = "          -2.098,96";

        String regex = "[^,]+";

        System.out.println("Regex 1: " + str1.matches(regex));
        System.out.println("Regex 2: " + str2.matches(regex));
    }

    public static void convertPDF() {
        Document doc = new Document("G:\\Contábil\\Clientes\\E.Fioreze\\Escrituração mensal\\2020\\Extratos\\11.2020\\banriteste.pdf");
        // Set Excel options
        ExcelSaveOptions options = new ExcelSaveOptions();
        // Set output format
        options.setFormat(ExcelSaveOptions.ExcelFormat.XLSX);
        // Set minimizing option
        options.setMinimizeTheNumberOfWorksheets(true);
        // Convert PDF to XLSX
        doc.save("workbook.xlsx", options);
    }

    public static void test() {
        int mes = 12;
        int ano = 2020;
        String pastaEmpresa = "Grafica e Editora Relampago Ltda";
        String pastaAnual = "Extratos";
        String pastaMensal = "";
        String banco = "Template Teste CEF";
        String idTemplate = "grafica10";
        String filtroArquivo = "cef;conci;.pdf";
        Map<String, String> colunas = new HashMap<>();
        colunas.put("data", "B");
        //colunas.put("documento", "");
        colunas.put("pretexto", "");
        colunas.put("historico", "C##[^,]+;F##[^,]+;G");
        colunas.put("entrada", "D");
        colunas.put("saida", "-E");
        //colunas.put("valor", "");

        System.out.println(RobotEfiorezeImportation.principal(
                mes,
                ano,
                pastaEmpresa,
                pastaAnual,
                pastaMensal,
                banco,
                idTemplate,
                filtroArquivo,
                colunas
        ).replaceAll("<br>", "\n")
        );
    }
}
