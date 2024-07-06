/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tronco.app.controller;

import com.mycompany.tronco.app.models.CnDataBase;
import com.mycompany.tronco.app.models.MdDataSet;
import com.mycompany.tronco.app.models.MdUser;
import com.mycompany.tronco.app.view.FormAbout;
import com.mycompany.tronco.app.view.FormData;
import com.mycompany.tronco.app.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isaac CLWnwQYwN4t75blW
 */
public class CtrMainView implements ActionListener, ChangeListener {

    private final MainView main_view;
    private Connection conect = null;
    private Statement sentences = null;
    private ResultSet result = null;

    FormData form_data = new FormData();
    FormAbout form_about = new FormAbout();
    CnDataBase cn_data_base = new CnDataBase();
    MdDataSet md_data;
    MdUser md_user = new MdUser();

    public CtrMainView(MainView main_view) {
        this.main_view = main_view;

        this.main_view.btn_insert.addActionListener(this);
        this.main_view.btn_view.addActionListener(this);
        this.main_view.btn_about.addActionListener(this);
        this.main_view.slider.addChangeListener(this);

        if (md_user.getName() != null) {
            this.main_view.lb_user.setText(md_user.getName());
        }
        showData("1000");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.main_view.btn_insert == e.getSource()) {
            if (this.main_view.id_windows == 0 || this.main_view.id_windows == 1) {
                this.main_view.scroll_panel.setViewportView(form_data);
                this.main_view.repaint();
                this.main_view.revalidate();

                this.main_view.id_windows = 2;
            }
        }
        if (this.main_view.btn_view == e.getSource()) {
            if (this.main_view.id_windows == 2 || this.main_view.id_windows == 1) {
                this.main_view.tb_data.clearSelection();
                this.main_view.scroll_panel.setViewportView(this.main_view.panel_home);
                this.main_view.repaint();
                this.main_view.revalidate();
                this.main_view.slider.setValue(1000);
                this.main_view.id_windows = 0;
                showData("1000");
            }
        }
        if (this.main_view.btn_about == e.getSource()) {
            if (this.main_view.id_windows == 0 || this.main_view.id_windows == 2) {
                this.main_view.scroll_panel.setViewportView(form_about);
                this.main_view.repaint();
                this.main_view.revalidate();
                this.main_view.id_windows = 1;
            }
        }
    }

    public void showData(String limit) {
        ((DefaultTableModel) this.main_view.tb_data.getModel()).setRowCount(0);

        String sql_query_consult = "select * from books limit " + limit + "";

        conect = cn_data_base.estableceConexion();

        try {
            sentences = conect.createStatement();

            result = sentences.executeQuery(sql_query_consult);

            ResultSetMetaData meta_data = result.getMetaData();

            List<MdDataSet> list = new ArrayList<>();
            MdDataSet md_data;

//            System.out.println(meta_data.getColumnCount());
//
//            for (int i = 1; i <= meta_data.getColumnCount(); i++) {
//                String columnName = meta_data.getColumnName(i);
//                System.out.println(columnName);
//            }
            while (result.next()) {
                md_data = new MdDataSet();
                md_data.setCod_departamento(result.getInt("COD_DEPARTAMENTO"));
                md_data.setDepartamento(result.getString("DEPARTAMENTO"));
                md_data.setCod_municipio(result.getInt("CODIGO_MUNICIPIO"));
                md_data.setMunipio(result.getString("MUNICIPIO"));
                md_data.setGrupo_cultivo(result.getString("GRUPO_DE_CULTIVO"));
                md_data.setSub_grupo_cultivo(result.getString("SUBGRUPO_DE_CULTIVO"));
                md_data.setCultivo(result.getString("CULTIVO"));
                md_data.setSistema_productivo(result.getString("DESAGREGACION_REGIONAL_Y_O_SISTEMA_PRODUCTIVO"));
                md_data.setAño(result.getInt("ANO"));
                md_data.setPeriodo(result.getString("PERIODO"));
                md_data.setArea_sembrada(result.getInt("AREA_SEMBRADA"));
                md_data.setArea_cosechada(result.getInt("AREA_COSECHADA"));
                md_data.setProduccion(result.getInt("PRODUCCION"));
                md_data.setRendimiento(result.getInt("RENDIMIENTO"));
                md_data.setEstado_fisico(result.getString("ESTADO_FISICO_PRODUCCION"));
                md_data.setNombre_cientifico(result.getString("NOMBRE_CIENTIFICO"));
                md_data.setCiclo_cultivo(result.getString("CICLO_DE_CULTIVO"));

                list.add(md_data);
            }

            String[] data = new String[17];

            for (MdDataSet elem : list) {
                data[0] = Integer.toString(elem.getCod_departamento());
                data[1] = elem.getDepartamento();
                data[2] = Integer.toString(elem.getCod_municipio());
                data[3] = elem.getMunipio();
                data[4] = elem.getGrupo_cultivo();
                data[5] = elem.getSub_grupo_cultivo();
                data[6] = elem.getCultivo();
                data[7] = elem.getSistema_productivo();
                data[8] = Integer.toString(elem.getAño());
                data[9] = elem.getPeriodo();
                data[10] = Integer.toString(elem.getArea_sembrada());
                data[11] = Integer.toString(elem.getArea_cosechada());
                data[12] = Integer.toString(elem.getProduccion());
                data[13] = Float.toString(elem.getRendimiento());
                data[14] = elem.getEstado_fisico();
                data[15] = elem.getNombre_cientifico();
                data[16] = elem.getCiclo_cultivo();

                ((DefaultTableModel) this.main_view.tb_data.getModel()).addRow(data);
            }

            cerrarConexion();
            list.clear();

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

    @Override
    public void stateChanged(ChangeEvent e) {
        if (this.main_view.slider == e.getSource() && !this.main_view.slider.getValueIsAdjusting()) {
            int valor_slider = this.main_view.slider.getValue();
            String limit = Integer.toString(valor_slider);
            //System.out.println(valor_slider);
            showData(limit);
        }
    }
}
