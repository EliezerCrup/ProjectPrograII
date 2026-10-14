package com.supermercado.pos.ui;

import javax.swing.*;
import java.util.*;
import com.supermercado.pos.servicio.*;
import com.supermercado.pos.util.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        super("POS - Ingreso");
        JPanel p = UI.panel();
        JTextField usuario = UI.campo(p, "Usuario");
        JPasswordField clave = UI.clave(p, "Contraseña");
        JButton entrar = new JButton("Ingresar");
        p.add(entrar);
        add(p);
        entrar.addActionListener(e -> {
            char[] a = clave.getPassword();
            try {
                Sesion.setUsuario(new AuthService().autenticar(usuario.getText().trim(), a));
                dispose();
                new MenuPrincipalFrame().setVisible(true);
            } catch (Exception ex) {
                UI.error(this, ex);
            } finally {
                Arrays.fill(a, '\0');
                clave.setText("");
            }
        });
        getRootPane().setDefaultButton(entrar);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
