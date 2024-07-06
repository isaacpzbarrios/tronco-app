/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tronco.app.models;

import java.lang.reflect.Field;

/**
 *
 * @author isaac
 */
public class MdDataSet {

    private int cod_departamento;
    private String departamento;
    private int cod_municipio;
    private String munipio;
    private String grupo_cultivo;
    private String sub_grupo_cultivo;
    private String cultivo;
    private String sistema_productivo;
    private int año;
    private String periodo;
    private int area_sembrada;
    private int area_cosechada;
    private int produccion;
    private float rendimiento;
    private String estado_fisico;
    private String nombre_cientifico;
    private String ciclo_cultivo;

    public MdDataSet() {
    }

    public void MdDataSet(int cod_departamento, String departamento, int cod_municipio, String munipio, String grupo_cultivo, String sub_grupo_cultivo, String cultivo, String sistema_productivo, int año, String periodo, int area_sembrada, int area_cosechada, int produccion, float rendimiento, String estado_fisico, String nombre_cientifico, String ciclo_cultivo) {
        this.cod_departamento = cod_departamento;
        this.departamento = departamento;
        this.cod_municipio = cod_municipio;
        this.munipio = munipio;
        this.grupo_cultivo = grupo_cultivo;
        this.sub_grupo_cultivo = sub_grupo_cultivo;
        this.cultivo = cultivo;
        this.sistema_productivo = sistema_productivo;
        this.año = año;
        this.periodo = periodo;
        this.area_sembrada = area_sembrada;
        this.area_cosechada = area_cosechada;
        this.produccion = produccion;
        this.rendimiento = rendimiento;
        this.estado_fisico = estado_fisico;
        this.nombre_cientifico = nombre_cientifico;
        this.ciclo_cultivo = ciclo_cultivo;
    }

    public int getCod_departamento() {
        return cod_departamento;
    }

    public void setCod_departamento(int cod_departamento) {
        this.cod_departamento = cod_departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getCod_municipio() {
        return cod_municipio;
    }

    public void setCod_municipio(int cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public String getMunipio() {
        return munipio;
    }

    public void setMunipio(String munipio) {
        this.munipio = munipio;
    }

    public String getGrupo_cultivo() {
        return grupo_cultivo;
    }

    public void setGrupo_cultivo(String grupo_cultivo) {
        this.grupo_cultivo = grupo_cultivo;
    }

    public String getSub_grupo_cultivo() {
        return sub_grupo_cultivo;
    }

    public void setSub_grupo_cultivo(String sub_grupo_cultivo) {
        this.sub_grupo_cultivo = sub_grupo_cultivo;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getSistema_productivo() {
        return sistema_productivo;
    }

    public void setSistema_productivo(String sistema_productivo) {
        this.sistema_productivo = sistema_productivo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getArea_sembrada() {
        return area_sembrada;
    }

    public void setArea_sembrada(int area_sembrada) {
        this.area_sembrada = area_sembrada;
    }

    public int getArea_cosechada() {
        return area_cosechada;
    }

    public void setArea_cosechada(int area_cosechada) {
        this.area_cosechada = area_cosechada;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }

    public float getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(float rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getEstado_fisico() {
        return estado_fisico;
    }

    public void setEstado_fisico(String estado_fisico) {
        this.estado_fisico = estado_fisico;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public String getCiclo_cultivo() {
        return ciclo_cultivo;
    }

    public void setCiclo_cultivo(String ciclo_cultivo) {
        this.ciclo_cultivo = ciclo_cultivo;
    }
    
    public boolean hasEmptyData() {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }
    
    public void clearData() {
    for (Field field : this.getClass().getDeclaredFields()) {
        try {
            field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                field.set(this, 0);
            } else if (field.getType().equals(String.class)) {
                field.set(this, "");
            } else {
                field.set(this, null);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

    
}
