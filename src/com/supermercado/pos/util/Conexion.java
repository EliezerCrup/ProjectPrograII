package com.supermercado.pos.util;

import java.sql.*;import java.util.*;import java.io.*;
public final class Conexion {
 private Conexion(){}
 public static Connection obtener() throws SQLException {
  Properties p=new Properties();try(InputStream in=new FileInputStream("config.properties")){p.load(in);}catch(IOException e){throw new SQLException("No se puede leer config.properties",e);}
  return DriverManager.getConnection(p.getProperty("db.url"),p.getProperty("db.usuario"),p.getProperty("db.clave"));
 }
 public static java.math.BigDecimal minimo(){Properties p=new Properties();try(InputStream in=new FileInputStream("config.properties")){p.load(in);}catch(IOException e){throw new IllegalStateException(e);}return new java.math.BigDecimal(p.getProperty("efectivo.minimo","500.00"));}
}