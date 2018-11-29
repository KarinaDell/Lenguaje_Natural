/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kedr;
import javax.swing.JFileChooser;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 5e
 */
public class LeerArchivo extends javax.swing.JFrame {
    JFileChooser jfc;
    File file;
    FileReader fr;
    BufferedReader br;
    List<ContarPorArchivo> list = new ArrayList<>();
    Hashtable<String,String> repetidas=new Hashtable<String,String>();
    Hashtable<Character,Letra> letras=new Hashtable<Character,Letra>();
    
    public LeerArchivo() {
        initComponents();
       jfc = new JFileChooser();
    }
    
    private void metodoAbrir() throws IOException 
    {
        ContarPorArchivo contador;
        String linea;
        String[] texto;
        int count=0;
        int repe=0;
        int vocales=0;
        if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
        {
            file = jfc.getSelectedFile();
            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while((linea=br.readLine()) != null){
                    linea=linea.toLowerCase();
                    texto=linea.split(" ");
                    vocales+=cuentaVocales(texto);
                    letras(texto);
                    repe+=palabras(texto);
                    count += texto.length;
                }
                String url= file.toString();
                System.out.println(url);
                contador = new ContarPorArchivo(url, repe, count);
                list.add(contador);
                br.close();
            } catch(FileNotFoundException nel)
            { System.out.println("nel"); }
            
            catch(IOException nel) {
                System.out.print(nel);
            }
        }
        System.out.println( "Vocales:" + vocales);
        System.out.println( getLetras());

        repetidas.clear();
    }
    
    public int cuentaVocales(String[] texto){
        int cont=0;
        for(int i=0; i<texto.length; i++){
            cont+=contarSilabas(texto[i]);
        }
        return cont;
    }
    
    
   private String cargarM(){
        String conte="";
        for(ContarPorArchivo elemento:list){
            conte += elemento.toString()+"\n";
        }
        return conte;
    } 
   
    private int palabras(String[] texto){
        int cont=0;
        for(int i=0; i<texto.length; i++){
            if(!repetidas.containsKey(texto[i])){
                repetidas.put(texto[i], texto[i]);
                cont++;
            }
        } return cont;
    }
    
     public static int contarSilabas(String p){
        int count = 0;
        p = p.toLowerCase();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '\"' || p.charAt(i) == '\'' || p.charAt(i) == '-' || p.charAt(i) == ',' || p.charAt(i) == ')' || p.charAt(i) == '(') {
                p = p.substring(0,i)+p.substring(i+1, p.length());
            }
        }
        boolean antesVoc = false;
        for (int j = 0; j < p.length(); j++) {
            if (p.contains("a") || p.contains("e") || p.contains("i") || p.contains("o") || p.contains("u")) {
                if (esVocal(p.charAt(j)) && !((p.charAt(j) == 'e') && (j == p.length()-1))) {
                    if (antesVoc == false) {
                        count++;
                        antesVoc = true;
                    }
                } else {
                    antesVoc = false;
                }
            } else {
                count++;
                break;
            }
        } return count;
    }
    
    private void letras(String [] texto){
        for(int i=0; i<texto.length; i++){
            String letra=texto[i];
            for(int j=0; j<letra.length(); j++){
                char caracter = letra.charAt(j);
                if(letras.containsKey(caracter)){
                    Letra l=letras.get(caracter);
                            l.aumentar();
                    letras.put(caracter, l);
                }else{
                    letras.put(caracter, new Letra(caracter));
                }
            }
        }
    }
    
    public String getLetras(){
        String conte="";
        Enumeration elements=letras.elements();
        while(elements.hasMoreElements()){
            conte+=elements.nextElement().toString()+"\n";
        }
        return conte;
    }
    
   
     public static boolean esVocal(char c){
        
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        } else {
            return false;
        }
    }
    
    private void file() throws IOException{
        List<String> lineas = Arrays.asList(cargarM(), getLetras());
        Path file = Paths.get("the-file-name.txt");
        Files.write(file, lineas, Charset.forName("UTF-8"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAbrir.setText("Buscar archivo");
        btnAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAbrirMouseClicked(evt);
            }
        });

        jLabel1.setText("Selecciona un archivo de texto:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnAbrir))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseClicked
        try {
            // TODO add your handling code here:
            metodoAbrir();
        } catch (IOException nel) {
            Logger.getLogger(LeerArchivo.class.getName()).log(Level.SEVERE, null, nel);
        }
    }//GEN-LAST:event_btnAbrirMouseClicked

    /**
     * @param args the command linea arguments
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
            java.util.logging.Logger.getLogger(LeerArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeerArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeerArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeerArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeerArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
