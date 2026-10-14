package com.supermercado.pos.ui;

import javax.swing.*;import com.supermercado.pos.dao.*;import com.supermercado.pos.servicio.*;
public class RolesFrame extends JFrame 
{
    public RolesFrame()
    {
        super("Roles");
        UI.permiso("ROL_ADMIN");
        JPanel p=UI.panel();
        JTextField nombre=UI.campo(p,"Nuevo rol");
        JTextField nivel=UI.campo(p,"Nivel");
        JButton crear=new JButton("Crear rol");
        JButton ver=new JButton("Ver roles");
        p.add(crear);p.add(ver);
        crear.addActionListener(e->UI.accion(this,()->{
            Operaciones.tx(c->{
                Sql.ejecutar(c,"INSERT INTO rol(nombre,nivel) VALUES(?,?)",nombre.getText(),Integer.parseInt(nivel.getText()));
                return null;
            });
            UI.mensaje(this,"Rol creado");
        }));
        ver.addActionListener(e->UI.mostrar("Roles","SELECT id,nombre,nivel FROM rol ORDER BY nivel"));
        add(p);pack();setLocationRelativeTo(null);
    }
}