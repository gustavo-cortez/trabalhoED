/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Importacoes;

import Estrutura.ListaEncadeada;
import Individuo.Cidadao;
import Individuo.Naturalidade;
import Individuo.Rg;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonImporter {

        public ListaEncadeada importarCidadaosDeJson(String arquivoJson, ListaEncadeada listaCidadaos) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(arquivoJson));
                JSONObject jsonObject = (JSONObject) obj;
                String estadoRG = (String) jsonObject.get("uf");
                JSONArray cidadaosJson = (JSONArray) jsonObject.get("cidadãos");

                for (Object c : cidadaosJson) {
                    JSONObject cidadaoJson = (JSONObject) c;
                    String nome = (String) cidadaoJson.get("nome");
                    String cpf = (String) cidadaoJson.get("cpf");
                    String datanasc = (String) cidadaoJson.get("data_nasc");
                    String numeroRg = (String) cidadaoJson.get("rg");

                    JSONObject naturalidadeJson = (JSONObject) cidadaoJson.get("naturalidade");
                    Naturalidade naturalidade = new Naturalidade((String) naturalidadeJson.get("cidade"), (String) naturalidadeJson.get("estado"));
                    Rg rg = new Rg(numeroRg, estadoRG);

                    List<Rg> rgs = new ArrayList<>();
                    rgs.add(rg);
                    Cidadao novoCidadao = new Cidadao(nome, datanasc, cpf, rgs, naturalidade);
                    listaCidadaos.adicionarLista(novoCidadao);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listaCidadaos;
        }
    }

