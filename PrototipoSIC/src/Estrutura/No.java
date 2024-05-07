/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estrutura;
import Individuo.Cidadao;

/**
 *
 * @author ra
 */
public class No { /*Essa classe eh a chave da estrutura*/
    private Cidadao chave;
    public No prox;

    public No(Cidadao chave) {
        this.chave = chave;
        this.prox = null;
    }

    public void setChave(Cidadao chave) {
        this.chave = chave;
    }
    
    public Cidadao getCidadao() {
        return chave;
    }
}
