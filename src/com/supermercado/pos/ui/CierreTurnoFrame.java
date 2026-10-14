package com.supermercado.pos.ui;

import javax.swing.*;
import com.supermercado.pos.servicio.*;

public class CierreTurnoFrame extends JFrame {

    public CierreTurnoFrame() {
        super("Cierre de turno");
        UI.permiso("TURNO_CERRAR");
        JPanel p = UI.panel();
        p.add(new JLabel("Efectivo esperado Q"));
        p.add(new JLabel(Operaciones.esperado().toString()));
        JTextField declarado = UI.campo(p, "Efectivo contado Q");
        JButton b = new JButton("Cerrar turno");
        p.add(b);
        b.addActionListener(e -> UI.accion(this, () -> {
            UI.mensaje(this, "Diferencia Q" + Operaciones.cerrar(Operaciones.dinero(declarado.getText())));
            dispose();
        }));
        add(p);
        pack();
        setLocationRelativeTo(null);
    }
}
