package org.example.repository;

import org.example.data.CuentaBancaria;

import java.util.List;

public interface CuentabancariaRepository {
    void leerDatos(String archivo);
    void escribirDatos(String archivo);
    void agregarCuenta(CuentaBancaria cuenta);
    CuentaBancaria buscarPorNumeroCuenta(String numeroCuenta);
    List<CuentaBancaria> buscarPorTitular(String titular);
    List<CuentaBancaria> buscarPorSaldoMayor(double saldo);
}
