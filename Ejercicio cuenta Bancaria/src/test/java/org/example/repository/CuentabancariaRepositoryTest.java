package org.example.repository;

import org.example.data.CuentaBancaria;
import org.example.exepcions.CuentaNoEncontradaExcepcion;
import org.example.exepcions.SaldoNoEncontradoExcepcion;
import org.example.exepcions.TitularNoEncontradoExcepcion;
import org.example.repository.impl.CuentabancariaRepositoryImpl;

import java.util.List;

class CuentabancariaRepositoryTest {

    CuentabancariaRepository banco = new CuentabancariaRepositoryImpl();

    @org.junit.jupiter.api.Test
    void leerDatos() {
        // Leer datos de archivo
        banco.leerDatos("datos/cuentas.txt");
    }

    @org.junit.jupiter.api.Test
    void escribirDatos() {
        // Agregar nueva cuenta
        banco.agregarCuenta(new CuentaBancaria("62086", "Andres Muñoz", 3000, "Corriente"));

        // Guardar los datos en un nuevo archivo
        banco.escribirDatos("datos/nuevas_cuentas.txt");
    }

    @org.junit.jupiter.api.Test
    void agregarCuenta() {
        // Agregar nueva cuenta
        banco.agregarCuenta(new CuentaBancaria("67890", "Maria Lopez", 1500, "Ahorros"));

    }

    @org.junit.jupiter.api.Test
    void buscarPorNumeroCuenta() {
        try {
            // Métodos de búsqueda
            CuentaBancaria cuenta1 = banco.buscarPorNumeroCuenta("12345");
            if (cuenta1 == null) {
                throw new CuentaNoEncontradaExcepcion("La cuenta no se encontró");
            } else {
                System.out.println("Cuenta encontrada: " + cuenta1.getNumeroCuenta() + " - " + cuenta1.getTitular());
            }
        } catch (CuentaNoEncontradaExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void buscarPorTitular() {
        try {
            List<CuentaBancaria> cuentasPorTitular = banco.buscarPorTitular("Juan Perez");
            if (cuentasPorTitular.isEmpty()) {
                throw new TitularNoEncontradoExcepcion("No se encontraron cuentas para el titular Juan Perez");
            }
            System.out.println("Cuentas de Juan Perez:");
            for (CuentaBancaria cuenta : cuentasPorTitular) {
                System.out.println(cuenta.getNumeroCuenta() + " - " + cuenta.getSaldo());
            }
        } catch (TitularNoEncontradoExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void buscarPorSaldoMayor() {
        try {
            List<CuentaBancaria> cuentasSaldoMayor = banco.buscarPorSaldoMayor(1000);
            if (cuentasSaldoMayor.isEmpty()) {
                throw new SaldoNoEncontradoExcepcion("No se encontraron cuentas con saldo mayor a 1000");
            }
            System.out.println("Cuentas con saldo mayor a 1000:");
            for (CuentaBancaria cuenta : cuentasSaldoMayor) {
                System.out.println(cuenta.getNumeroCuenta() + " - " + cuenta.getSaldo());
            }
        } catch (SaldoNoEncontradoExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}