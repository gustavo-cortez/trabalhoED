/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import Estrutura.ListaEncadeada;
import Importacoes.JsonImporter;
import Individuo.Cidadao;
import Persistencia.GerenciadorDeDados;
import Relatorio.Relatorio;
import Timer.TempoDeExecucao;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Eduardo
 */
public class TelaInicial extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImportarJson = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnImportarJson.setText("Importar  Json");
        btnImportarJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarJsonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnImportarJson)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnImportarJson)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private ListaEncadeada lista = null;  // Inicialmente, a lista é nula
    private void btnImportarJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarJsonActionPerformed
        GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados();  

        if (lista == null) {
            lista = new ListaEncadeada();  // Cria uma nova lista se não existir
        }

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos JSON", "json");
        fileChooser.setFileFilter(filter);
    
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String caminhoDoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
            TempoDeExecucao tempo = new TempoDeExecucao();
        
            // Começa a calcular o tempo
            tempo.iniciar();
        
            // Chamada da função para importar cidadãos do arquivo JSON
            JsonImporter jsonImporter = new JsonImporter(); // Criando uma instância de JsonImporter
            lista = jsonImporter.importarCidadaosDeJson(caminhoDoArquivo, lista);

            // Atualiza a interface de usuário se necessário e informa quantos cidadãos foram importados
            // System.out.println("Número de cidadãos na lista após a importação: " + lista.getQuantidadeCidadao());
        
            Relatorio relatorio = new Relatorio(lista, 10, 30);
            relatorio.imprimirRelatorio();
        
            // Termina de calcular o tempo
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            // System.out.println("Tempo de execução: " + tempoDeExecucao + " Milissegundos");
        
            // Salva os dados após a importação
            gerenciadorDeDados.salvarCidadaos(lista);
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnImportarJsonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportarJson;
    // End of variables declaration//GEN-END:variables
}
