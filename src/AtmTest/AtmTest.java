/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AtmTest;

import Controler.Controler;
import Model.Model;
import View.Cajero;
import javax.swing.JFrame;


public class AtmTest {
    public static void main(String[] args) {
        Cajero view = new Cajero();
        Model model = new Model();
        Controler controler = new Controler(view, model);
        controler.start();
        controler.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controler.view.setLocationRelativeTo(null);
        controler.view.setVisible(true);
    }

}
