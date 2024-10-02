package org.example.data;

public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private String tipo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo, String tipo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}

