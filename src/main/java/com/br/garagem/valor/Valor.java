package com.br.garagem.valor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_valor")
public class Valor {

    public Valor() {
        this.data_fim = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Double primeira_hora;
    @Column(nullable = false)
    private Double demais_horas;
    @Column()
    private Date data_fim;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrimeira_hora() {
        return this.primeira_hora;
    }

    public void setPrimeira_hora(Double primeira_hora) {
        this.primeira_hora = primeira_hora;
    }

    public Double getDemais_horas() {
        return this.demais_horas;
    }

    public void setDemais_horas(Double demais_horas) {
        this.demais_horas = demais_horas;
    }

    public Date getData_fim() {
        return this.data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

}
