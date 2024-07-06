/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tronco.app.controller;

import com.mycompany.tronco.app.models.CnDataBase;
import com.mycompany.tronco.app.models.MdUser;
import com.mycompany.tronco.app.view.FormSingUp;
import com.mycompany.tronco.app.view.LoginView;
import com.mycompany.tronco.app.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author isaac
 */
public class CtrlLoginView implements ActionListener {

    private final LoginView login_view;
    private MdUser md_user = new MdUser();
    private MainView main_view = new MainView();
    private FormSingUp form_sing_up = new FormSingUp();

    private Connection conect = null;
    private Statement sentences = null;
    private ResultSet result = null;

    private CnDataBase cn_base_datos = new CnDataBase();

    public String query(String email, String password) {
        String sql_query = "select\n"
                + "  metadata ->> 'name' as name,\n"
                + "  metadata ->> 'email' as email,\n"
                + "  metadata ->> 'password' as password\n"
                + "from\n"
                + "  users\n"
                + "where\n"
                + "  metadata ->> 'email' = '" + email + "'\n"
                + "  and metadata ->> 'password' = '" + password + "'";

        return sql_query;
    }

    public CtrlLoginView(LoginView login_view) {
        this.login_view = login_view;

        this.login_view.btn_login.addActionListener(this);
        this.login_view.btn_sing_up.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.login_view.btn_login == e.getSource()) {
            login();
        }

        if (this.login_view.btn_sing_up == e.getSource()) {
            if (this.login_view.btn_sing_up.getText() == "Sing Up") {
                this.login_view.btn_sing_up.setText("Login");
                this.login_view.lb_acount.setText("With account?");
                this.login_view.jPanel1.remove(this.login_view.jPanel6);
                this.login_view.jPanel1.add(form_sing_up, java.awt.BorderLayout.CENTER);
                this.login_view.jPanel1.revalidate();
                this.login_view.jPanel1.repaint();
            }else if (this.login_view.btn_sing_up.getText() == "Login"){
                this.login_view.btn_sing_up.setText("Sing Up");
                this.login_view.lb_acount.setText("No account?");
                this.login_view.jPanel1.removeAll();
                this.login_view.jPanel1.add(this.login_view.jPanel2, java.awt.BorderLayout.PAGE_START);
                this.login_view.jPanel1.add(this.login_view.jPanel6, java.awt.BorderLayout.CENTER);
                this.login_view.jPanel1.revalidate();
                this.login_view.jPanel1.repaint();
                
            }
        }
    }

    public void login() {
        try {

            String email = this.login_view.txt_user.getText();
            String password = new String(this.login_view.txt_password.getPassword());
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this.login_view, "Enter email", "Session", 1);

            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this.login_view, "Enter password", "Session", 1);

            } else {
                conect = cn_base_datos.estableceConexion();

                sentences = conect.createStatement();

                result = sentences.executeQuery(query(email, password));

                while (result.next()) {
                    md_user.setName(result.getString("name"));
                    md_user.setEmail(result.getString("email"));
                    md_user.setPassword(result.getString("password"));
                }

                if (md_user.getName() != null) {
                    main_view.setVisible(true);
                    this.login_view.dispose();
                } else {
                    JOptionPane.showMessageDialog(this.login_view, "User or password incorrent", "Session error", 0);
                }

            }

            System.out.println(md_user.toString());
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

}
