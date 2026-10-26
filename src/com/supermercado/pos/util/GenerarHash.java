package com.supermercado.pos.util;

public class GenerarHash {

    public static void main(String[] args) {
        String clave = "Admin123!"; // Cambiar o definir la clave directamente aquí
    String hash = Seguridad.hash(clave.toCharArray());
    System.out.println("HASH GENERADO:");
    System.out.println(hash);
    }
}
