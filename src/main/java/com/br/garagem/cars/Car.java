package com.br.garagem.cars;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        this.tempo = 60.0;
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
    private Double tempo;
    @Column()
    private Double valor_pago;

    public String getFormatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String today = formatter.format(date.getTime());
        return today;
    }

    public Double calculateTotalPay(Double priceFirstHour, Double otherHours) {
        Double totalToPay = 0.0;
        Date startDate = this.data_entrada;
        if (startDate == null) {
            return 0.0;
        }

        Date endDate = this.data_saida;
        if (endDate == null) {
            setData_saida(new Date());
            setValor_pago(priceFirstHour);
            return priceFirstHour;
        }
        long difference = endDate.getTime() - startDate.getTime();
        Double minutes =  (double) TimeUnit.MILLISECONDS.toMinutes(difference);

        Double timeHours = (double) (minutes / 60);
        timeHours = Math.ceil(timeHours);
        if (timeHours <= 1.0) {
            setValor_pago(priceFirstHour);
            this.setTempo(minutes);
            return priceFirstHour;
        }
        totalToPay = ((timeHours - 1) * otherHours);
        totalToPay += priceFirstHour;
        setValor_pago(totalToPay);
        this.setTempo(minutes);
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

    public Double getTempo() {
        return this.tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public Double getValor_pago() {
        return this.valor_pago;
    }

    public void setValor_pago(Double valor_pago) {
        this.valor_pago = valor_pago;
    }

}
