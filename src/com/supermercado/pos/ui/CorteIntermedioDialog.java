package com.supermercado.pos.ui;

import javax.swing.*;
import com.supermercado.pos.servicio.*;

public class CorteIntermedioDialog extends JDialog {

    public CorteIntermedioDialog(JFrame padre) {
        super(padre, "Retiro a bóveda", true);
        UI.permiso("CORTE_INTERMEDIO");
        JPanel p = UI.panel();
        p.add(new JLabel("Efectivo esperado Q"));
        p.add(new JLabel(Operaciones.esperado().toString()));
        JTextField monto = UI.campo(p, "Monto a retirar Q");
        JButton b = new JButton("Retirar");
        p.add(b);
        b.addActionListener(e -> UI.accion(this, () -> {
            Operaciones.retiro(Operaciones.dinero(monto.getText()));
            UI.mensaje(this, "Retiro registrado");
            dispose();
        }));
        add(p);
        pack();
        setLocationRelativeTo(padre);
    }
}
