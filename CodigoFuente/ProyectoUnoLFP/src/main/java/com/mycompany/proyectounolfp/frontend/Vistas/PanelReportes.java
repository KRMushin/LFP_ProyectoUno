/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectounolfp.frontend.Vistas;

import com.mycompany.proyectounolfp.backend.tokens.Token;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin-mushin
 */
public class PanelReportes extends javax.swing.JFrame {

    private JTable tablaReporte;
    private DefaultTableModel tableModel;
    private List<Token> reporteTokens;
    private List<Token> reporteErrores;
    private List<Token> reporteOptimizacion;

    /**
     * Creates new form PanelReportes
     */
    public PanelReportes() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        configuracionPanel();
    }

    public List<Token> getReporteTokens() {
        return reporteTokens;
    }

    public void setReporteTokens(List<Token> reporteTokens) {
        this.reporteTokens = reporteTokens;
    }

    public List<Token> getReporteErrores() {
        return reporteErrores;
    }

    public void setReporteErrores(List<Token> reporteErrores) {
        this.reporteErrores = reporteErrores;
    }

    public List<Token> getReporteOptimizacion() {
        return reporteOptimizacion;
    }

    public void setReporteOptimizacion(List<Token> reporteOptimizacion) {
        this.reporteOptimizacion = reporteOptimizacion;
    }

    private void configuracionPanel(){
        panelContenedor.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        JButton btnReporte1 = new JButton("Reporte Tokens");
        JButton btnReporte2 = new JButton("Reporte Optimizacion");
        JButton btnReporte3 = new JButton("Reporte Errores");

        panelBotones.add(btnReporte1);
        panelBotones.add(btnReporte2);
        panelBotones.add(btnReporte3);

        tableModel = new DefaultTableModel();
        tablaReporte = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tablaReporte);

        panelContenedor.add(panelBotones, BorderLayout.NORTH); 
        panelContenedor.add(scrollPane, BorderLayout.CENTER);   
        
        btnReporte1.addActionListener((ActionEvent e) -> {
            mostrarReporteTokens();
        });

    btnReporte2.addActionListener((ActionEvent e) -> {
        mostrarReporteOptimizacion();  // Acción para mostrar el reporte de optimización
        });

    btnReporte3.addActionListener((ActionEvent e) -> {
        mostrarReporteErrores();  
        });
}

// Método para mostrar el reporte de Tokens
private void mostrarReporteTokens() {
    String[] columnas = {"Token", "Expresion Regular", "Lenguaje", "Tipo", "Fila", "Columna"};
    if (reporteTokens != null && !reporteTokens.isEmpty()) {
        Object[][] datos = new Object[reporteTokens.size()][6];  // Creamos una matriz para almacenar los datos

        for (int i = 0; i < reporteTokens.size(); i++) {
            Token token = reporteTokens.get(i);
            datos[i][0] = token.getLexema();
            datos[i][1] = token.getExpresionRegular(); // Asumiendo que hay un método getExpresionRegular()
            datos[i][2] = token.getContexto();        // Asumiendo que hay un método getLenguaje()
            datos[i][3] = token.getTipoToken().toString();            // Asumiendo que hay un método getTipo()
            datos[i][4] = token.getFila();            // Asumiendo que hay un método getFila()
            datos[i][5] = token.getColumna();         // Asumiendo que hay un método getColumna()
        }
    actualizarTabla(columnas, datos);
}
}

private void mostrarReporteErrores() {
    String[] columnas = {"Token", "Lugar de Error", "Tipo", "Fila" , "Columna"};
    if (reporteErrores != null && !reporteErrores.isEmpty()) {
        Object[][] datos = new Object[reporteErrores.size()][6];  // Creamos una matriz para almacenar los datos

        for (int i = 0; i < reporteErrores.size(); i++) {
            Token token = reporteErrores.get(i);
            datos[i][0] = token.getLexema();
            datos[i][1] = token.getContexto();        // Asumiendo que hay un método getLenguaje()
            datos[i][2] = token.getTipoToken().toString();            // Asumiendo que hay un método getTipo()
            datos[i][3] = token.getFila();            // Asumiendo que hay un método getFila()
            datos[i][4] = token.getColumna();         // Asumiendo que hay un método getColumna()
        }
        actualizarTabla(columnas, datos);
    }
}
// Método para mostrar el reporte de Optimización
private void mostrarReporteOptimizacion() {
    String[] columnas = {"Token", "ExpresionRegular", "Lenguaje", "Tipo", "Fila", "Columna"};
    if (reporteOptimizacion != null && !reporteOptimizacion.isEmpty()) {
        Object[][] datos = new Object[reporteOptimizacion.size()][6];  // Creamos una matriz para almacenar los datos

        for (int i = 0; i < reporteOptimizacion.size(); i++) {
            Token token = reporteOptimizacion.get(i);
            datos[i][0] = token.getLexema();
            datos[i][1] = token.getExpresionRegular();        // Asumiendo que hay un método getLenguaje()
            datos[i][2] = token.getTipoToken().toString();            // Asumiendo que hay un método getTipo()
            datos[i][3] = token.getContexto();            // Asumiendo que hay un método getFila()
            datos[i][4] = token.getFila();            // Asumiendo que hay un método getFila()
            datos[i][5] = token.getColumna();         // Asumiendo que hay un método getColumna()

        }
        actualizarTabla(columnas, datos);
    }
}

// Método para mostrar el reporte de Errores
private void actualizarTabla(String[] columnas, Object[][] datos) {
    tableModel.setDataVector(datos, columnas);  // Actualiza el modelo de la tabla con las nuevas columnas y datos
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContenedor.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
