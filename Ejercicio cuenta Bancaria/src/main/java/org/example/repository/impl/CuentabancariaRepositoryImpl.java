package org.example.repository.impl;
import org.example.data.CuentaBancaria;
import org.example.repository.CuentabancariaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CuentabancariaRepositoryImpl implements CuentabancariaRepository {
    private List<CuentaBancaria> cuentas;
    private static final Logger logger = Logger.getLogger(CuentabancariaRepositoryImpl.class.getName());

    public CuentabancariaRepositoryImpl() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
        logger.info("Cuenta agregada: " + cuenta.getNumeroCuenta());
    }

    public CuentaBancaria buscarPorNumeroCuenta(String numeroCuenta)  {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }


    public List<CuentaBancaria> buscarPorTitular(String titular) {
        List<CuentaBancaria> resultados = new ArrayList<>();
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getTitular().equalsIgnoreCase(titular)) {
                resultados.add(cuenta);
            }
        }
        return resultados;
    }

    public List<CuentaBancaria> buscarPorSaldoMayor(double saldo) {
        List<CuentaBancaria> resultados = new ArrayList<>();
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getSaldo() > saldo) {
                resultados.add(cuenta);
            }
        }
        return resultados;
    }

    @Override
    public void leerDatos(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String tipo = datos[0];
                    String numero = datos[1];
                    String titular = datos[2];
                    double saldo = Double.parseDouble(datos[3]);

                    agregarCuenta(new CuentaBancaria(numero, titular, saldo, tipo));
                }
            }
            logger.info("Datos leídos desde " + archivo);
        } catch (IOException e) {
            logger.info("Error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void escribirDatos(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (CuentaBancaria cuenta : cuentas) {
                String linea = String.format("%s,%s,%s,%.2f\n",
                        cuenta.getTipo(),
                        cuenta.getNumeroCuenta(),
                        cuenta.getTitular(),
                        cuenta.getSaldo());
                bw.write(linea);
            }
            logger.info("Datos escritos en " + archivo);
        } catch (IOException e) {
            logger.severe("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

