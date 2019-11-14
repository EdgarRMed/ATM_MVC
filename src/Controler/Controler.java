/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Model.Model;
import View.Cajero;
import View.UsuarioFrame;
import View.adminFrame;
import View.adminLogIn;
import View.clienteFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler implements ActionListener{
    
    // Variable declaration
    public Cajero view;
    private Model model;
    public clienteFrame clienteLogIn;
    public adminLogIn adminLog;
    // End variable declaration
    
    public Controler(Cajero view, Model model) {
        this.view = view;
        this.model = model;
        view.clientBtn.addActionListener(this);
        view.adminBtn.addActionListener(this);
       
        
        
    }
    public void start(){
        view.setTitle("Cajero Automático");
        view.setSize(600, 420);
        initWindows();
    }
    
    public void initWindows(){
        clienteLogIn = new clienteFrame();
        clienteLogIn.setResizable(false);
        clienteLogIn.regresarBtn.addActionListener(this);
        adminLog = new adminLogIn();
        adminLog.setResizable(false);
        adminLog.regresarBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Botones del frame principal
        if (e.getSource() == view.clientBtn){
            clienteLogIn.setLocationRelativeTo(null);
            clienteLogIn.setVisible(true);
            view.setVisible(false);
            
        }
        if (e.getSource() == view.adminBtn){
            adminLog.setLocationRelativeTo(null);
            adminLog.setVisible(true);
            view.setVisible(false);
            
        }
        // Fin botones principal
        
        //Botones cliente Login
        if (e.getSource() == clienteLogIn.regresarBtn){
            view.setVisible(true);
            clienteLogIn.dispose();
            
            
        }
        // Fin botones cliente Login
        
        //Botones admin Login
        if (e.getSource() == adminLog.regresarBtn){
            view.setVisible(true);
            adminLog.dispose();
        }
    }
    
   

  
    
}
