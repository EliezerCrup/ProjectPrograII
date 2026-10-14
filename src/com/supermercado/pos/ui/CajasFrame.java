package com.supermercado.pos.ui;

import javax.swing.*;
import com.supermercado.pos.dao.*;
import com.supermercado.pos.servicio.*;

public class CajasFrame extends JFrame {

    public CajasFrame() {
        super("Catálogo de cajas");
        UI.permiso("CATALOGO_ADMIN");
        JPanel p = UI.panel();
        JTextField numero = UI.campo(p, "Número de caja");
        JTextField nombre = UI.campo(p, "Nombre");
        JButton b = new JButton("Agregar caja");
        JButton ver = new JButton("Ver cajas");
        p.add(b);
        p.add(ver);
        b.addActionListener(e -> UI.accion(this, () -> {
            Operaciones.tx(c -> {
                Sql.ejecutar(c, "INSERT INTO caja(numero,nombre) VALUES(?,?)", Integer.parseInt(numero.getText()), nombre.getText());
                return null;
            });
            UI.mensaje(this, "Caja agregada");
        }));
        ver.addActionListener(e -> UI.mostrar("Cajas", "SELECT id,numero,nombre,activa FROM caja ORDER BY numero"));
        add(p);
        pack();
        setLocationRelativeTo(null);
    }
}
