/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BACASABLE_UI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author antoi
 */
public class Fenetre1 extends JFrame{
    
    public static void main(String[] args) {
    new Fenetre1();
  }

  private Panneau1 pan = new Panneau1();

  public Fenetre1() {
    this.setTitle("Animation");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setContentPane(pan);
    this.setVisible(true);

    go();
  }

  private void go() {
    // Les coordonnées de départ de notre rond
    int x = pan.getPosX(), y = pan.getPosY();
    // Le booléen pour savoir si l'on recule ou non sur l'axe x
    boolean backX = false;
    // Le booléen pour savoir si l'on recule ou non sur l'axe y
    boolean backY = false;

    // Dans cet exemple, j'utilise une boucle while
    // Vous verrez qu'elle fonctionne très bien
    while (true) {
      // Si la coordonnée x est inférieure à 1, on avance
      if (x < 1)
        backX = false;
      // Si la coordonnée x est supérieure à la taille du Panneau1 moins la taille du rond, on recule
      if (x > pan.getWidth() - 50)
        backX = true;
      // Idem pour l'axe y
      if (y < 1)
        backY = false;
      if (y > pan.getHeight() - 50)
        backY = true;

      // Si on avance, on incrémente la coordonnée
      // backX est un booléen, donc !backX revient à écrire
      // if (backX == false)
      if (!backX)
        pan.setPosX(++x);
      // Sinon, on décrémente
      else
        pan.setPosX(--x);
      // Idem pour l'axe Y
      if (!backY)
        pan.setPosY(++y);
      else
        pan.setPosY(--y);

      // On redessine notre Panneau1
      pan.repaint();
      // Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
      try {
        Thread.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
    
}
