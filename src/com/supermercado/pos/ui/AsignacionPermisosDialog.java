package com.supermercado.pos.ui;

import javax.swing.*;
import com.supermercado.pos.dao.*;
import com.supermercado.pos.servicio.*;

public class AsignacionPermisosDialog extends JDialog {

    public AsignacionPermisosDialog(JFrame padre) {
        super(padre, "Asignar permisos", true);
        UI.permiso("ROL_ADMIN");
        JPanel p = UI.panel();
        JTextField rol = UI.campo(p, "ID rol");
        JTextField permiso = UI.campo(p, "ID permiso");
        JButton b = new JButton("Asignar permiso a rol");
        JButton ver = new JButton("Ver permisos");
        p.add(b);
        p.add(ver);
        b.addActionListener(e -> UI.accion(this, () -> {
            Operaciones.tx(c -> {
                Sql.ejecutar(c, "INSERT INTO rol_permiso(rol_id,permiso_id) VALUES(?,?)", Long.parseLong(rol.getText()), Long.parseLong(permiso.getText()));
                return null;
            });
            UI.mensaje(this, "Permiso asignado");
        }));
        ver.addActionListener(e -> UI.mostrar("Permisos", "SELECT id,codigo FROM permiso ORDER BY id"));
        add(p);
        pack();
        setLocationRelativeTo(padre);
    }
}
