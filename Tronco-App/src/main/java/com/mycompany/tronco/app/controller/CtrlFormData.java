/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tronco.app.controller;

import com.mycompany.tronco.app.models.CnDataBase;
import com.mycompany.tronco.app.models.MdDataSet;
import com.mycompany.tronco.app.view.FormData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author isaac
 */
public class CtrlFormData implements ActionListener {

    private FormData form_data;
    MdDataSet md_data_set = new MdDataSet();

    private Connection conect = null;
    private Statement sentences = null;
    private ResultSet result = null;

    private CnDataBase cn_base_datos = new CnDataBase();

    public String query(int cod_dep, String dep, int cod_mun, String mun, String grupo_cul, String subgrupo,
            String cultivo, String sistema, int año, String periodo, int area_sem, int area_coc, int produccion, float rendimiento,
            String estado_fis, String nombre_cin, String ciclo) {
        String sql_query = "insert into books \n"
                + "(COD_DEPARTAMENTO, DEPARTAMENTO, CODIGO_MUNICIPIO, MUNICIPIO, GRUPO_DE_CULTIVO, SUBGRUPO_DE_CULTIVO, CULTIVO, DESAGREGACION_REGIONAL_Y_O_SISTEMA_PRODUCTIVO,\n"
                + "ANO, PERIODO, AREA_SEMBRADA, AREA_COSECHADA, PRODUCCION, RENDIMIENTO, ESTADO_FISICO_PRODUCCION, NOMBRE_CIENTIFICO, CICLO_DE_CULTIVO)\n"
                + "values (" + cod_dep + ", '" + dep + "', " + cod_mun + ", '" + mun + "', '" + grupo_cul + "', '" + subgrupo + "', '" + cultivo + "', '" + sistema + "', " + año + ",'" + periodo + "'," + area_sem + " , " + area_coc + ", " + produccion + ", " + rendimiento + ",'" + estado_fis + "', '" + nombre_cin + "', '" + ciclo + "')";

        return sql_query;
    }

    public CtrlFormData(FormData form_data) {
        this.form_data = form_data;

        this.form_data.btn_send.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.form_data.btn_send == e.getSource()) {
            validForm();
            if (!md_data_set.hasEmptyData()) {
                send();
                md_data_set.clearData();
            } else {
                System.out.println("vacio");
            }
        }
    }

    public void send() {
        try {
            conect = cn_base_datos.estableceConexion();

            sentences = conect.createStatement();

            sentences.execute(query(md_data_set.getCod_departamento(),
                    md_data_set.getDepartamento(), md_data_set.getCod_municipio(), 
                    md_data_set.getMunipio(), md_data_set.getGrupo_cultivo(), md_data_set.getSub_grupo_cultivo(),
                    md_data_set.getCultivo(), md_data_set.getSistema_productivo(), md_data_set.getAño(), 
                    md_data_set.getPeriodo(), md_data_set.getArea_sembrada(), md_data_set.getArea_cosechada(),
                    md_data_set.getProduccion(), md_data_set.getRendimiento(), md_data_set.getEstado_fisico(),
                    md_data_set.getNombre_cientifico(), md_data_set.getCiclo_cultivo()));

            JOptionPane.showMessageDialog(this.form_data, "successful registration", "Genial!", 2);

            cerrarConexion();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

    }

    public void cerrarConexion() {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        if (sentences != null) {
            try {
                sentences.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }

        if (conect != null) {
            try {
                conect.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    public void validForm() {
        if (this.form_data.txt_cod_dep.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter cod. Departamento", "Form", 1);

        } else if (this.form_data.cbx_dep.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select departamento", "Form", 1);

        } else if (this.form_data.txt_cod_mun.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter cod. Municipio", "Form", 1);

        } else if (this.form_data.cbx_mun.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select municipio", "Form", 1);

        } else if (this.form_data.cbx_grupo_cul.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select grupo cultivo", "Form", 1);

        } else if (this.form_data.cbx_subgrupo_cul.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select subgrupo cultivo", "Form", 1);

        } else if (this.form_data.cbx_cultivo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select cultivo", "Form", 1);

        } else if (this.form_data.cbx_sis_productivo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select sistema productivo", "Form", 1);

        } else if (this.form_data.txt_año.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter año", "Form", 1);

        } else if (this.form_data.txt_periodo.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter periodo", "Form", 1);

        } else if (this.form_data.txt_area_sem.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter area sembrada", "Form", 1);

        } else if (this.form_data.txt_area_coc.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter area cocechada", "Form", 1);

        } else if (this.form_data.txt_produccion.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter produccion", "Form", 1);

        } else if (this.form_data.txt_rendimiento.getText().equals("")) {
            JOptionPane.showMessageDialog(this.form_data, "Enter rendimiento", "Form", 1);

        } else if (this.form_data.cbx_estado_fisico.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select estado fisico", "Form", 1);

        } else if (this.form_data.cbx_cientifico.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select nombre cientifico", "Form", 1);

        } else if (this.form_data.cbx_ciclo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this.form_data, "Select ciclo cultivo", "Form", 1);

        } else {
            int cod_dep = Integer.parseInt(this.form_data.txt_cod_dep.getText());
            String dep = this.form_data.cbx_dep.getSelectedItem().toString();
            int cod_mun = Integer.parseInt(this.form_data.txt_cod_mun.getText());
            String mun = this.form_data.cbx_mun.getSelectedItem().toString();
            String grupo_cul = this.form_data.cbx_grupo_cul.getSelectedItem().toString();
            String subgrupo = this.form_data.cbx_subgrupo_cul.getSelectedItem().toString();
            String cultivo = this.form_data.cbx_cultivo.getSelectedItem().toString();
            String sistema = this.form_data.cbx_sis_productivo.getSelectedItem().toString();
            int año = Integer.parseInt(this.form_data.txt_año.getText());
            String periodo = this.form_data.txt_periodo.getText();
            int area_sem = Integer.parseInt(this.form_data.txt_area_sem.getText());
            int area_coc = Integer.parseInt(this.form_data.txt_area_coc.getText());
            int produccion = Integer.parseInt(this.form_data.txt_produccion.getText());
            float rendimiento = Float.parseFloat(this.form_data.txt_rendimiento.getText());
            String estado_fis = this.form_data.cbx_estado_fisico.getSelectedItem().toString();
            String nombre_cin = this.form_data.cbx_cientifico.getSelectedItem().toString();
            String ciclo = this.form_data.cbx_ciclo.getSelectedItem().toString();

            md_data_set.MdDataSet(cod_dep, dep, cod_mun, mun, grupo_cul, grupo_cul, cultivo, sistema, año, periodo, area_sem, area_coc, produccion, rendimiento, estado_fis, nombre_cin, cultivo);
            clearForm();
        }
    }

    public void clearForm() {
        this.form_data.txt_cod_dep.setText("");
        this.form_data.cbx_dep.setSelectedIndex(-1);
        this.form_data.txt_cod_mun.setText("");
        this.form_data.cbx_mun.setSelectedIndex(-1);
        this.form_data.cbx_grupo_cul.setSelectedIndex(-1);
        this.form_data.cbx_subgrupo_cul.setSelectedIndex(-1);
        this.form_data.cbx_cultivo.setSelectedIndex(-1);
        this.form_data.cbx_sis_productivo.setSelectedIndex(-1);
        this.form_data.txt_año.setText("");
        this.form_data.txt_periodo.setText("");
        this.form_data.txt_area_sem.setText("");
        this.form_data.txt_area_coc.setText("");
        this.form_data.txt_produccion.setText("");
        this.form_data.txt_rendimiento.setText("");
        this.form_data.cbx_estado_fisico.setSelectedIndex(-1);
        this.form_data.cbx_cientifico.setSelectedIndex(-1);
        this.form_data.cbx_ciclo.setSelectedIndex(-1);

    }
}
