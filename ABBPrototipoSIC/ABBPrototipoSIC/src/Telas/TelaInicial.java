package Telas;

import Estrutura.*;
import Importacoes.JsonImporter;
import Persistencia.GerenciadorDeDados;
import Timer.TempoDeExecucao;
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
    
    private static ABB abb;
    
    public TelaInicial(ABB abb) {
        initComponents();        
        setLocationRelativeTo(null);
        setResizable(false);
        this.abb = abb;
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
        btnOpcoes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnImportarJson.setText("Importar  ERGUF");
        btnImportarJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarJsonActionPerformed(evt);
            }
        });

        btnOpcoes.setText("Outras Opções");
        btnOpcoes.setToolTipText("");
        btnOpcoes.setActionCommand("btnOpcoes");
        btnOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnImportarJson, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(btnImportarJson, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Inicialmente, a lista é nula
    private void btnImportarJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarJsonActionPerformed
        GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados();  
        
        if (abb == null) {
            abb = new ABB();  // Cria uma nova lista se não existir
        }

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos ERGUF", "ERGUF");
        fileChooser.setFileFilter(filter);
    
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String caminhoDoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
            TempoDeExecucao tempo = new TempoDeExecucao();
        
            // Começa a calcular o tempo
            tempo.iniciar();
        
            // Chamada da função para importar cidadãos do arquivo JSON
            JsonImporter jsonImporter = new JsonImporter(); // Criando uma instância de JsonImporter
            abb = jsonImporter.importarCidadaosDeJson(caminhoDoArquivo, abb);

            // Atualiza a interface de usuário se necessário e informa quantos cidadãos foram importados
            // System.out.println("Número de cidadãos na lista após a importação: " + lista.getQuantidadeCidadao());
           
            // Termina de calcular o tempo
            tempo.finalizar();
            long tempoDeExecucao = tempo.obterTempoEmMilissegundos();
            // System.out.println("Tempo de execução: " + tempoDeExecucao + " Milissegundos");
        
            // Salva os dados após a importação
            gerenciadorDeDados.salvarCidadaos(abb);
            JOptionPane.showMessageDialog(null, "Tempo de execução: " + tempoDeExecucao + " Milissegundos", "Importar", JOptionPane.INFORMATION_MESSAGE);
            abb.imprimir();
            
        }
    }//GEN-LAST:event_btnImportarJsonActionPerformed

    private void btnOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcoesActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new TelaEscolhas(abb).setVisible(true);
        
    }//GEN-LAST:event_btnOpcoesActionPerformed

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
                new TelaInicial(abb).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportarJson;
    private javax.swing.JButton btnOpcoes;
    // End of variables declaration//GEN-END:variables
}
