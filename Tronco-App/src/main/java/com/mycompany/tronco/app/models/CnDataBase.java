/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tronco.app.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author isaac
 */
public class CnDataBase {
    private Connection conect = null;
    private Statement sentences = null;
    private ResultSet result = null;

    String bd = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.zlplegjxuwawntrbcuyy&password=CLWnwQYwN4t75blW";
    
    public Connection estableceConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conect = DriverManager.getConnection(bd);
            
        } catch (Exception e) {
            System.out.println("Error en la conexion");
        }
        
        return conect;
    }
}
