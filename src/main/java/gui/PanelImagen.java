/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author dani_
 */
public class PanelImagen extends JPanel{
    private JLabel picLabel;
    
    public PanelImagen(String path){
    super.setSize(500, 500);
        
        try {
            BufferedImage imagen = ImageIO.read(new File(path));
            picLabel = new JLabel(new ImageIcon(imagen));
            add(picLabel);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo importar la imagen");
        }    
    }

    public JLabel getPicLabel() {
        return picLabel;
    }

    public void setPicLabel(JLabel picLabel) {
        this.picLabel = picLabel;
    }
    
}
