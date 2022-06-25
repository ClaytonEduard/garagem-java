package com.br.garagem.cars;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_movimentacao")
public class Car {
    // criacao do construtor interno
    public Car() {
        this.tempo = (long) 60;
        this.data_entrada = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String placa;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private Date data_entrada;
    @Column()
    private Date data_saida;
    @Column(nullable = false)
    private Long tempo;
    @Column()
    private Double valor_pago;

    public Double calculateTotalPay(Double valorHora) {
    
        Date entrada = this.data_entrada;
        if (entrada == null) {
            return 0.0;
        }
   
        Date saida = this.data_saida;
        if (saida == null) {
            return valorHora;
        }
      
        long diff = saida.getTime() - entrada.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

      
        this.setTempo(minutes);
        Long tempoHoras = minutes / 60;
       
        Double totalToPay = valorHora * tempoHoras;

        
        setValor_pago(totalToPay);
    
        return totalToPay;

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getData_entrada() {
        return this.data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_saida() {
        return this.data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public Long getTempo() {
        return this.tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }

    public Double getValor_pago() {
        return this.valor_pago;
    }

    public void setValor_pago(Double valor_pago) {
        this.valor_pago = valor_pago;
    }

}
