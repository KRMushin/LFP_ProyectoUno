/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectounolfp.frontend.Vistas;

import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCodigoFuente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;

/**
 *
 * @author kevin-mushin
 */
public class VistaPrincipal extends javax.swing.JFrame {
     
    public VistaPrincipal() {
        initComponents();
        configuracionFrame();
        detectorDeCursor();
    }
    
   private void configuracionFrame(){
        
           this.setExtendedState(JFrame.MAXIMIZED_BOTH);
           this.panelContenedor.setLayout(new BorderLayout());
           jScrollPane1.setPreferredSize(new Dimension(100, 0));
           numerosFila.setLayout(new BoxLayout(numerosFila, BoxLayout.Y_AXIS));
           numerosFila.setAlignmentY(Component.TOP_ALIGNMENT);
           numerosFila.setBackground(Color.LIGHT_GRAY); 
           this.listaNumeros.setPreferredSize(new Dimension(40, 0));
           this.panelContenedor.add(barraBotones, BorderLayout.NORTH);
           this.panelContenedor.add(jScrollPane1, BorderLayout.CENTER);
           this.panelContenedor.add(listaNumeros,BorderLayout.WEST);
    }
    private void detectorDeCursor(){
        this.areaTexto.addCaretListener(this::actualizarPosicionCursor);
        
    }
    private void actualizarPosicionCursor(CaretEvent e){
        int posicion = e.getDot();
        try {
            int linea = areaTexto.getLineOfOffset(posicion) + 1;
            int columna = posicion  - areaTexto.getLineStartOffset(linea - 1) +1;
            cursorPos.setText(" Linea: " + linea + " Columna " + columna);
            actualizarNumerosDeLinea();
        } catch (BadLocationException ex) {
            System.out.println(" ERROR AL ACTUALIZAR POSICION" + ex.getMessage());
        }
    }    
    private void actualizarNumerosDeLinea() {
            numerosFila.removeAll(); // liimpiar el panel antes de actualizar
            int totalLineas = areaTexto.getLineCount();
            // crear una etiqueta para cada número de línea
            for (int i = 1; i <= totalLineas; i++) {
                JLabel labelNumero = new JLabel(String.valueOf(i));
                numerosFila.add(labelNumero);
            }

            // redibujar el panel de números de línea
            numerosFila.revalidate();
            numerosFila.repaint();
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        barraBotones = new javax.swing.JPanel();
        analizar = new javax.swing.JButton();
        cursorPos = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        listaNumeros = new javax.swing.JScrollPane();
        numerosFila = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        barraBotones.setBackground(new java.awt.Color(255, 204, 0));

        analizar.setText("ANALIZAR");
        analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarActionPerformed(evt);
            }
        });

        cursorPos.setForeground(new java.awt.Color(0, 0, 0));
        cursorPos.setText("Vacio");

        jButton5.setText("Guardar Lienzo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout barraBotonesLayout = new javax.swing.GroupLayout(barraBotones);
        barraBotones.setLayout(barraBotonesLayout);
        barraBotonesLayout.setHorizontalGroup(
            barraBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBotonesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cursorPos)
                .addGap(18, 18, 18)
                .addComponent(analizar)
                .addGap(816, 816, 816)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        barraBotonesLayout.setVerticalGroup(
            barraBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(barraBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(cursorPos)
                    .addComponent(analizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        areaTexto.setColumns(20);
        areaTexto.setRows(5);
        jScrollPane1.setViewportView(areaTexto);

        listaNumeros.setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout numerosFilaLayout = new javax.swing.GroupLayout(numerosFila);
        numerosFila.setLayout(numerosFilaLayout);
        numerosFilaLayout.setHorizontalGroup(
            numerosFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        numerosFilaLayout.setVerticalGroup(
            numerosFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        listaNumeros.setViewportView(numerosFila);

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addComponent(barraBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listaNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 126, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarActionPerformed
        try {
               AnalizadorCodigoFuente analizador = new AnalizadorCodigoFuente();      
               analizador.analizarCodigoFuente(areaTexto.getText());
        } catch (Exception e) {
                
        }
    }//GEN-LAST:event_analizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analizar;
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JPanel barraBotones;
    private javax.swing.JLabel cursorPos;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane listaNumeros;
    private javax.swing.JPanel numerosFila;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}



















