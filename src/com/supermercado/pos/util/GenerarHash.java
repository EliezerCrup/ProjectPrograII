package com.supermercado.pos.util;

public class GenerarHash {public static void main(String[] args){if(args.length!=1){System.out.println("Uso: GenerarHash clave");return;}System.out.println(Seguridad.hash(args[0].toCharArray()));}}