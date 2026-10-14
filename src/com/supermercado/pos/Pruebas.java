package com.supermercado.pos;

import java.math.*;
import com.supermercado.pos.servicio.*;
import com.supermercado.pos.util.*;

public class Pruebas {

    public static void main(String[] args) {
        int ok = 0;
        try {
            if (Operaciones.dinero("10.125").compareTo(new BigDecimal("10.13")) == 0) {
                ok++;
            }
            if (Seguridad.verificar("Clave123!".toCharArray(), Seguridad.hash("Clave123!".toCharArray()))) {
                ok++;
            }
            if (!Seguridad.verificar("Mala".toCharArray(), Seguridad.hash("Buena".toCharArray()))) {
                ok++;
            }
            System.out.println("Pruebas locales: " + ok + "/3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
