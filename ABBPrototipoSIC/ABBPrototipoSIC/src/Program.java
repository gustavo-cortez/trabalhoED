
import Estrutura.*;
import Persistencia.GerenciadorDeDados;
import Telas.TelaInicial;

/**
 *
 * @author Gustavo
 */
public class Program {
    
    public static void main(String[] args) {
        ABB abb = new ABB();
        
        GerenciadorDeDados.verificarExistenciaArquivo(abb);
        new TelaInicial(abb).setVisible(true);
        abb.imprimir();    
    }

        
}
