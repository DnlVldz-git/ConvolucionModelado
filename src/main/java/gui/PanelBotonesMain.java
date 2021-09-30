/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author dani_
 */
public class PanelBotonesMain extends JPanel{
    private JButton btnMedia;
    private JButton btnGaussiana;
    private JButton btnMediaVertical;    
    
    public PanelBotonesMain(){
        super.setBackground(Color.WHITE);
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        
        this.btnMedia = new JButton("Media 11x11");
        this.btnGaussiana = new JButton("Gaussiana 11x11");
        this.btnMediaVertical =  new JButton("Media Vertical 21x21");
        
        this.btnMedia.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.btnGaussiana.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.btnMediaVertical.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        
        add(btnMedia);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(btnGaussiana);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(btnMediaVertical);
    }

    public JButton getBtnMedia() {
        return btnMedia;
    }

    public JButton getBtnGaussiana() {
        return btnGaussiana;
    }

    public JButton getBtnMediaVertical() {
        return btnMediaVertical;
    }
    
}
