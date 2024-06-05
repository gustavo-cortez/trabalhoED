package Relatorio;

import Estrutura.ListaEncadeada;
import Estrutura.No;
import Timer.TempoDeExecucao;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
/**
 *
 * @author nivis
 */
public class Relatorio {
    
    /*estrutura:*/
    ListaEncadeada estruraDeDados;
    /*lista dos estados*/
    ListaRelatorio[] estado;
    

    public Relatorio(ListaEncadeada estruraDeDados, ListaRelatorio[] estados, int faixaetaria1, int faixaetaria2) {
        this.estruraDeDados = estruraDeDados;
        this.estado = estados;      
    
        No i = this.estruraDeDados.getCabeca();
        while(i != null) {
            String data = i.getCidadao().getDatanasc();
            String anoTexto = data.substring(data.length()-4, data.length());
            int ano = Integer.parseInt(anoTexto);
            if((2024-ano) >= faixaetaria1 && (2024-ano) <= faixaetaria2) {           
                String naturalidadeEstado = i.getCidadao().getOrigem().getEstado();
                int j = EspalhamentoEstado.retornaIndiceEstado(naturalidadeEstado);
                estado[j].inserirNaLista(i.getCidadao());              
            }
            i=i.prox;
        }      
    }
    
    public void imprimirRelatorio(int faixaetaria1, int faixaetaria2) {
        try {
            TempoDeExecucao tempo = new TempoDeExecucao();
            // Começa a calcular o tempo
            tempo.iniciar();
            String nomeArquivo = "RelatorioPorFaixaEtaria_" + faixaetaria1 + "_a_" + faixaetaria2 +"_anos.pdf";
            PdfWriter writer = new PdfWriter(nomeArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (int i = 0; i < 27; i++) {
                if (estado[i].getCabeca() != null) {
                    EnumSiglaEstado sigla = EnumSiglaEstado.valueOf(estado[i].getCabeca().getCidadao().getOrigem().getEstado());
                    String estadoNome = sigla.getNomeEstado();
                    Color color = new DeviceRgb(0, 0, 0); // Cor preta
                    // Cria um texto em negrito
                    Text boldText = new Text(estadoNome + ": ").setBold().setFontColor(color);
                    // Adiciona o texto em negrito ao documento
                    document.add(new Paragraph(boldText));
                    estado[i].imprimirLista(document);
                }
            }

            document.close();
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso.", "Sucesso!", 1);
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Relatorio", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo PDF: " + e.getMessage());
        }
        
    }

    
    
    
}
