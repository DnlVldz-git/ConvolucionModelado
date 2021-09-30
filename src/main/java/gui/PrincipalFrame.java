/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class PrincipalFrame extends JFrame {

    private PanelImagen pnlImg1;
    private PanelImagen pnlImg2;
    private PanelBotonesMain pnlBtns;
    private int[][] media;
    private int[][] mediaDiagonal;
    private int[][] gauss = {{1, 2, 4, 6, 8, 10, 8, 6, 4, 2, 1},   
                            {2, 4, 6, 8, 10, 12, 10, 8, 6, 4, 2},
                            {4, 6, 8, 10, 12, 14, 12, 10, 8, 6, 4},
                            {6, 8, 10, 12, 14, 16, 14, 12, 10, 8, 6},
                            {8, 10, 12, 14, 16, 18, 16, 14, 12, 10, 8},
                            {10, 12, 14, 16, 18, 20, 18, 16, 14, 12, 10},
                            {8, 10, 12, 14, 16, 18, 16, 14, 12, 10, 8},
                            {6, 8, 10, 12, 14, 16, 14, 12, 10, 8, 6},
                            {4, 6, 8, 10, 12, 14, 12, 10, 8, 6, 4},
                            {2, 4, 6, 8, 10, 12, 10, 8, 6, 4, 2},
                            {1, 2, 4, 6, 8, 10, 8, 6, 4, 2, 1}};
    int[][] vecindadR;
    int[][] vecindadG;
    int[][] vecindadB;
    int[] vecindadRH;
    int[] vecindadGH;
    int[] vecindadBH;

    public PrincipalFrame() {
        super.setBackground(Color.WHITE);
        super.setLocationRelativeTo(null);
        super.setSize(270, 200);
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        fillMedia();
        fillMediaDiag();
        pnlBtns = new PanelBotonesMain();

        pnlBtns.getBtnMedia().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setUp(0).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            BufferedImage img = ImageIO.read(new File("img.png"));

                            for (int x = 0; x < img.getWidth(); x++) {
                                for (int y = 0; y < img.getHeight(); y++) {
                                    obtenerVecindad11x11(x, y, img);
                                    Color colorNuevo = getMedia();
                                    img.setRGB(x, y, colorNuevo.getRGB());
                                }
                            }
                            try {
                                ImageIO.write(img, "png", new File("img2.png"));
                            } catch (IOException ex) {
                                Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            pnlImg2 = new PanelImagen("img2.png");
                            add(pnlImg2, BorderLayout.EAST);
                            setSize(1000, 601);
                            setSize(1000, 600);
                        } catch (IOException ex) {
                            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );
            }

        });

        pnlBtns.getBtnGaussiana().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setUp(1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            BufferedImage img = ImageIO.read(new File("img.png"));

                            for (int x = 0; x < img.getWidth(); x++) {
                                for (int y = 0; y < img.getHeight(); y++) {
                                    obtenerVecindad11x11(x, y, img);
                                    Color colorNuevo = getGauss();
                                    img.setRGB(x, y, colorNuevo.getRGB());
                                }
                            }
                            try {
                                ImageIO.write(img, "png", new File("img2.png"));
                            } catch (IOException ex) {
                                Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            pnlImg2 = new PanelImagen("img2.png");
                            add(pnlImg2, BorderLayout.EAST);
                            setSize(1000, 601);
                            setSize(1000, 600);
                        } catch (IOException ex) {
                            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );
            }

        });

        pnlBtns.getBtnMediaVertical().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setUp(2).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            BufferedImage img = ImageIO.read(new File("img3.png"));

                            for (int x = 0; x < img.getWidth(); x++) {
                                for (int y = 0; y < img.getHeight(); y++) {
                                    obtenerVecindad21x21(x, y, img);
                                    Color colorNuevo = getMediaDiagonal();
                                    
                                    
                                    img.setRGB(x, y, colorNuevo.getRGB());
                                }
                            }

                            try {
                                ImageIO.write(img, "png", new File("img2.png"));
                            } catch (IOException ex) {
                                Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            pnlImg2 = new PanelImagen("img2.png");
                            add(pnlImg2, BorderLayout.EAST);
                            setSize(750, 601);
                            setSize(750, 600);
                        } catch (IOException ex) {
                            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );
            }

        });
        add(pnlBtns);

        setVisible(true);
    }

    public void fillMedia() {
        media = new int[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                media[i][j] = 1;
            }
        }
    }

    public void fillMediaDiag() {
        mediaDiagonal = new int[21][21];
        int num = 10;
        int numAnt = num;
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                if (i == j) {
                    num--;
                    mediaDiagonal[i][j] = 1;
                } else {
                    mediaDiagonal[i][j] = 0;
                }

            }
        }
    }

    public Color getGauss() {
        int sumaR = 0;
        int sumaG = 0;
        int sumaB = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                sumaR += gauss[i][j] * vecindadR[i][j];
                sumaG += gauss[i][j] * vecindadG[i][j];
                sumaB += gauss[i][j] * vecindadB[i][j];
            }
        }
        Color colorNuevo = new Color(Math.abs(sumaR / 1104), Math.abs(sumaG / 1104), Math.abs(sumaB / 1104));
        return colorNuevo;
    }

    public Color getMediaDiagonal() {
        int sumaR = 0;
        int sumaG = 0;
        int sumaB = 0;

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                sumaR += mediaDiagonal[i][j] * vecindadR[i][j];
                sumaG += mediaDiagonal[i][j] * vecindadG[i][j];
                sumaB += mediaDiagonal[i][j] * vecindadB[i][j];
            }
        }
        
        int num = Math.abs(sumaR/50)+50;
        
        if (num >= 256) {
            num = 255;
        }else if (num <= -1) {
            num = 0;
        }
        
        Color colorNuevo = new Color(num, num, num);

        return colorNuevo;
    }

    public Color getMedia() {
        int sumaR = 0;
        int sumaG = 0;
        int sumaB = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                sumaR += media[i][j] * vecindadR[i][j];
                sumaG += media[i][j] * vecindadG[i][j];
                sumaB += media[i][j] * vecindadB[i][j];
            }
        }
        Color colorNuevo = new Color(Math.abs(sumaR / 121), Math.abs(sumaG / 121), Math.abs(sumaB / 121));

        return colorNuevo;
    }

    public void obtenerVecindad11x11(int x, int y, BufferedImage img) {
        vecindadR = new int[11][11];
        vecindadG = new int[11][11];
        vecindadB = new int[11][11];
        for (int i = x - 5; i < x + 6; i++) {
            for (int j = y - 5; j < y + 6; j++) {
                if (i < 0 || j < 0 || i >= img.getWidth() || j >= img.getHeight()) {
                    vecindadR[i - (x - 5)][j - (y - 5)] = 0;
                    vecindadG[i - (x - 5)][j - (y - 5)] = 0;
                    vecindadB[i - (x - 5)][j - (y - 5)] = 0;
                } else {
                    int rgb = img.getRGB(i, j);
                    Color color = new Color(rgb);
                    vecindadR[i - (x - 5)][j - (y - 5)] = color.getRed();
                    vecindadG[i - (x - 5)][j - (y - 5)] = color.getGreen();
                    vecindadB[i - (x - 5)][j - (y - 5)] = color.getBlue();
                }
            }
        }
    }
    
    public void obtenerVecindad21x21(int x, int y, BufferedImage img) {
        vecindadR = new int[21][21];
        vecindadG = new int[21][21];
        vecindadB = new int[21][21];
        for (int i = x - 10; i < x + 11; i++) {
            for (int j = y - 10; j < y + 11; j++) {
                if (i < 0 || j < 0 || i >= img.getWidth() || j >= img.getHeight()) {
                    vecindadR[i - (x - 10)][j - (y - 10)] = 0;
                    vecindadG[i - (x - 10)][j - (y - 10)] = 0;
                    vecindadB[i - (x - 10)][j - (y - 10)] = 0;
                } else {
                    int rgb = img.getRGB(i, j);
                    Color color = new Color(rgb);
                    vecindadR[i - (x - 10)][j - (y - 10)] = color.getRed();
                    vecindadG[i - (x - 10)][j - (y - 10)] = color.getGreen();
                    vecindadB[i - (x - 10)][j - (y - 10)] = color.getBlue();
                }
            }
        }
    }

    public void obtenerVecindad3x3(int x, int y, BufferedImage img) {
        vecindadR = new int[3][3];
        vecindadG = new int[3][3];
        vecindadB = new int[3][3];
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i < 0 || j < 0 || i >= img.getWidth() || j >= img.getHeight()) {
                    vecindadR[i - (x - 1)][j - (y - 1)] = 0;
                    vecindadG[i - (x - 1)][j - (y - 1)] = 0;
                    vecindadB[i - (x - 1)][j - (y - 1)] = 0;
                } else {
                    int rgb = img.getRGB(i, j);
                    Color color = new Color(rgb);
                    vecindadR[i - (x - 1)][j - (y - 1)] = color.getRed();
                    vecindadG[i - (x - 1)][j - (y - 1)] = color.getGreen();
                    vecindadB[i - (x - 1)][j - (y - 1)] = color.getBlue();
                }
            }
        }
    }

    public JButton setUp(int flag) {
        remove(pnlBtns);
        JPanel pnlS = new JPanel();
        //pnlS.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton btnRegresar = new JButton("Regresar");
        JButton btnRealizar = new JButton("Realizar");

        JPanel pnlC = new JPanel();
        if (flag == 0) {
            super.setTitle("Media 11x11");
            pnlImg1 = new PanelImagen("img.png");            
        }else if(flag == 1){
            super.setTitle("Media Gaussiana 11x11");
            pnlImg1 = new PanelImagen("img.png");
        }else{
            super.setTitle("Media diagonal 21x21");
            pnlImg1 = new PanelImagen("img3.png");
        }
        
        pnlC.add(pnlImg1);

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                remove(pnlC);

                if (pnlImg2 != null) {
                    remove(pnlImg2);
                }
                remove(pnlS);
                add(pnlBtns);
                setSize(270, 200);
            }
        });

        pnlS.add(Box.createRigidArea(new Dimension(20, 0)));
        pnlS.add(btnRegresar);
        pnlS.add(Box.createRigidArea(new Dimension(20, 0)));
        pnlS.add(btnRealizar);

        add(pnlC, BorderLayout.WEST);
        add(pnlS, BorderLayout.SOUTH);
        setSize(1000, 600);
        return btnRealizar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new PrincipalFrame();

            }
        });
    }

}
