package com.supermercado.pos.ui;

import javax.swing.*;
import java.util.*;

public class AutorizacionDialog extends JDialog {

    public String usuario;
    public char[] clave;

    public AutorizacionDialog(JFrame padre) {
        super(padre, "Autorizar con supervisor", true);
        JPanel p = UI.panel();
        JTextField u = UI.campo(p, "Supervisor");
        JPasswordField c = UI.clave(p, "Contraseña");
        JButton b = new JButton("Autorizar");
        p.add(b);
        b.addActionListener(e -> {
            usuario = u.getText().trim();
            clave = c.getPassword();
            dispose();
        });
        add(p);
        pack();
        setLocationRelativeTo(padre);
    }
}
