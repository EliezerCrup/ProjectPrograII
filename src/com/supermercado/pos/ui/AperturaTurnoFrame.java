package com.supermercado.pos.ui;

import javax.swing.*;
import com.supermercado.pos.servicio.*;
import com.supermercado.pos.dao.*;
import com.supermercado.pos.util.*;
import java.sql.*;

public class AperturaTurnoFrame extends JFrame {

    public AperturaTurnoFrame() {
        super("Apertura de turno");
        UI.permiso("TURNO_ABRIR");
        JPanel p = UI.panel();
        JComboBox<String> cajas = new JComboBox<>();
        try (Connection c = Conexion.obtener()) {
            for (Object[] r : Sql.filas(c, "SELECT id,numero FROM caja WHERE activa=1 AND id NOT IN (SELECT caja_id FROM turno WHERE estado='ABIERTO') ORDER BY numero")) {
                cajas.addItem(r[0] + " - Caja " + r[1]);
            }
        } catch (Exception e) {
            UI.error(this, e);
        }
        p.add(new JLabel("Caja"));
        p.add(cajas);
        JTextField fondo = UI.campo(p, "Fondo inicial Q");
        JButton abrir = new JButton("Abrir");
        p.add(abrir);
        abrir.addActionListener(e -> UI.accion(this, () -> {
            if (cajas.getSelectedItem() == null) {
                throw new IllegalArgumentException("No hay cajas libres");
            }
            long id = Long.parseLong(cajas.getSelectedItem().toString().split(" ")[0]);
            UI.mensaje(this, "Turno #" + Operaciones.abrir(id, Operaciones.dinero(fondo.getText())));
            dispose();
        }));
        add(p);
        pack();
        setLocationRelativeTo(null);
    }
}
