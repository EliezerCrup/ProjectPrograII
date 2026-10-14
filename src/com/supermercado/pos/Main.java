package com.supermercado.pos;

import javax.swing.*;import com.supermercado.pos.ui.*;
public class Main {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(()->new LoginFrame().setVisible(true));
    }
}