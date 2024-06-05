package Persistencia;

import Estrutura.TabelaHash;
import Importacoes.JsonImporter;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import Timer.TempoDeExecucao;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenciadorDeDados {
    private static final String CAMINHO_DO_ARQUIVO = "cidadaos.json";
    private List<Cidadao> cidadaos;

    public GerenciadorDeDados() {
        this.cidadaos = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    

    public static void verificarExistenciaArquivo(TabelaHash tabela) {
        // Garante que a lista seja inicializada, independentemente de sua existência prévia
        
        File arquivo = new File(CAMINHO_DO_ARQUIVO);   
        
        if (arquivo.exists()) {
            JsonImporter dados = new JsonImporter();
            // Como garantimos que a lista não é nula, passamos diretamente
            TempoDeExecucao tempo = new TempoDeExecucao();
            tempo.iniciar();
            dados.importarCidadaosDeJsonRapido(CAMINHO_DO_ARQUIVO, tabela);
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            JOptionPane.showMessageDialog(null, "Tempo de execução de: " + tempoDeExecucao + " Milissegundos", "Pesistencia de Dados", JOptionPane.INFORMATION_MESSAGE);
            tabela.imprimirColisoes();         
        } else {
            JOptionPane.showMessageDialog(null, "O arquivo " + CAMINHO_DO_ARQUIVO + " não existe.", "", 1);
        }
    }
        
    public void carregarCidadaos() {
    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader(CAMINHO_DO_ARQUIVO)) {
        Object obj = parser.parse(reader);
        JSONArray listaCidadaosJson = (JSONArray) obj;
        listaCidadaosJson.forEach(item -> {
            Cidadao cidadao = parsearObjetoCidadao((JSONObject) item);
            cidadaos.add(cidadao);
        });
    } catch (IOException e) {
        // Se não existir o arquivo, inicie uma nova lista vazia.
        // Isso é esperado na primeira execução, então não é necessário imprimir o stack trace.
        cidadaos = new ArrayList<>();
    } catch (ParseException e) {
        // Em caso de erro de parse, você pode querer informar o usuário ou logar o erro.
        e.printStackTrace();
    }
}
    
    
    
    public static Cidadao parsearObjetoCidadao(JSONObject cidadaoJson) {
        String nome = (String) cidadaoJson.get("nome");
        String cpf = (String) cidadaoJson.get("cpf");
        String datanasc = (String) cidadaoJson.get("data_nasc");
    
        JSONArray rgsJson = (JSONArray) cidadaoJson.get("rgs");
        List<Rg> rgs = new ArrayList<>();
        for (Object r : rgsJson) {
            JSONObject rgJson = (JSONObject) r;
            String numero = (String) rgJson.get("numero");
            String estadoRG = (String) rgJson.get("Estado");
            rgs.add(new Rg(numero, estadoRG));
        }

        JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
        Naturalidade naturalidade = new Naturalidade(
        (String) naturalidadeJson.get("cidade"),
        (String) naturalidadeJson.get("estado"));

        return new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
}

    public void salvarCidadaos(TabelaHash tabela) {
        JSONArray listaCidadaos = new JSONArray();
        
        for (Cidadao cidadao : tabela.getCidadaos()) {
            JSONObject detalhesCidadao = new JSONObject();
            detalhesCidadao.put("nome", cidadao.getNome());
            detalhesCidadao.put("cpf", cidadao.getCpf());
            detalhesCidadao.put("data_nasc", cidadao.getDatanasc());
            JSONArray arrayRgs = new JSONArray();
            for (Rg rg : cidadao.getRgGerais()) {
                JSONObject detalhesRg = new JSONObject();
                detalhesRg.put("numero", rg.getRg());
                detalhesRg.put("Estado", rg.getEstadoRG());
                arrayRgs.add(detalhesRg);
            }
            detalhesCidadao.put("rgs", arrayRgs);
            JSONObject detalhesNaturalidade = new JSONObject();
            detalhesNaturalidade.put("cidade", cidadao.getOrigem().getCidade());
            detalhesNaturalidade.put("estado", cidadao.getOrigem().getEstado());
            detalhesCidadao.put("naturalidade", detalhesNaturalidade);
            listaCidadaos.add(detalhesCidadao);
        }

        try (FileWriter file = new FileWriter("cidadaos.json")) {
            file.write(listaCidadaos.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Cidadao> getCidadaos() {
        return cidadaos;
    }
    

}