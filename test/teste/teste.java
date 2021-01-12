package teste;

import RobotEfiorezeImportation.RobotEfiorezeImportation;
import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;
import java.util.Map;
import java.util.HashMap;

public class teste {

    public static void main(String[] args) {
        test();
    }
    public static void convertPDF(){
        Document doc =  new Document("G:\\Contábil\\Clientes\\E.Fioreze\\Escrituração mensal\\2020\\Extratos\\11.2020\\banriteste.pdf");
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
        int mes = 11;
        int ano = 2020;
        String pastaEmpresa = "E.Fioreze";
        String pastaAnual = "Extratos";
        String pastaMensal = "";
        String banco = "Template Banrisul teste (13)";
        String idTemplate = "efioreze13";
        String filtroArquivo = "banrisul;conci;.pdf";
        Map<String, String> colunas = new HashMap<>();
        colunas.put("data", "B");
        //colunas.put("documento", "");
        colunas.put("pretexto", "");
        colunas.put("historico", "G");
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
