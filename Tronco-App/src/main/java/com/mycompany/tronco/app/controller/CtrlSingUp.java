/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tronco.app.controller;

import com.mycompany.tronco.app.models.CnDataBase;
import com.mycompany.tronco.app.models.MdUser;
import com.mycompany.tronco.app.view.FormSingUp;
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
public class CtrlSingUp implements ActionListener {

    private FormSingUp form_sing_up;
    private MdUser md_user = new MdUser();

    private Connection conect = null;
    private Statement sentences = null;
    private ResultSet result = null;

    private CnDataBase cn_base_datos = new CnDataBase();

    public String query(String user_json) {
        String sql_query = "insert into users\n"
                + "  (metadata)\n"
                + "values\n"
                + "  (\n"
                + "    '" + user_json + "'\n"
                + "  )";

        return sql_query;
    }

    public CtrlSingUp(FormSingUp form_sing_up) {
        this.form_sing_up = form_sing_up;

        this.form_sing_up.btn_sing.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.form_sing_up.btn_sing == e.getSource()) {
            singUp();
            limpiarInputs();
        }
    }

    public void singUp() {
        try {
            String name = this.form_sing_up.txt_name.getText();
            String email = this.form_sing_up.txt_user.getText();
            String password = new String(this.form_sing_up.txt_password.getPassword());
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this.form_sing_up, "Enter name", "Register", 1);

            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this.form_sing_up, "Enter email", "Register", 1);

            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this.form_sing_up, "Enter password", "Register", 1);

            } else {

                md_user.setName(name);
                md_user.setEmail(email);
                md_user.setPassword(password);

                conect = cn_base_datos.estableceConexion();

                sentences = conect.createStatement();

                sentences.execute(query(md_user.toString()));

                JOptionPane.showMessageDialog(this.form_sing_up, "successful registration", "Genial!", 2);

            }
            
            cerrarConexion();
            System.out.println(md_user.toString());
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

    private void limpiarInputs() {
        this.form_sing_up.txt_name.setText("");
        this.form_sing_up.txt_user.setText("");
        this.form_sing_up.txt_password.setText("");
    }
}
