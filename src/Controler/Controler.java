/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Exeptions.NoHaySuficienteDineroExeption;
import Exeptions.NumerosNegativosExeption;
import Exeptions.UsuarioNoExistenteExeption;
import Model.Model;
import Model.Usuario;
import View.AltaCliente;
import View.BajaCliente;
import View.Cajero;
import View.DepositarPanel;
import View.RetirarPanel;
import View.UsuarioFrame;
import View.adminFrame;
import View.adminLogIn;
import View.clienteFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;

public class Controler implements ActionListener{
    
    // Variable declaration
    public Cajero view;
    private Model model;
    public clienteFrame clienteLogIn;
    public UsuarioFrame userFrame;
    public adminLogIn adminLog;
    public adminFrame adFrame;
    public AltaCliente altaPanel;
    public BajaCliente bajaPanel;
    public RetirarPanel retirarPanel;
    public DepositarPanel depositarPanel;
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
    // Se instancian todas las ventanas y paneles
    public void initWindows(){
        clienteLogIn = new clienteFrame();
        clienteLogIn.setResizable(false);
        userFrame = new UsuarioFrame();
        userFrame.setResizable(false);
        adminLog = new adminLogIn();
        adminLog.setResizable(false);
        adFrame = new adminFrame();
        adFrame.setResizable(false);
        altaPanel = new AltaCliente();
        bajaPanel = new BajaCliente();
        retirarPanel = new RetirarPanel();
        depositarPanel = new DepositarPanel();
        
        // Argreacion de escuchas
        clienteLogIn.regresarBtn.addActionListener(this);
        clienteLogIn.logInBtn.addActionListener(this);
        userFrame.salirBtn.addActionListener(this);
        userFrame.retirarBtn.addActionListener(this);
        userFrame.depositarBtn.addActionListener(this);
        adminLog.regresarBtn.addActionListener(this);
        adminLog.logInBtn.addActionListener(this);
        adFrame.altaBtn.addActionListener(this);
        adFrame.bajaBtn.addActionListener(this);
        adFrame.exitBtn.addActionListener(this);
        altaPanel.registerBtn.addActionListener(this);
        bajaPanel.deleteBtn.addActionListener(this);
        retirarPanel.retirarBtn.addActionListener(this);
        depositarPanel.depositarBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
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
            clienteLogIn.accountTxField.setText(null);
            clienteLogIn.nipPswField.setText(null);
            clienteLogIn.dispose();   
        }
        
        if(e.getSource() == clienteLogIn.logInBtn){
            // Excepciones aquí
            boolean flag = true;
            for(Usuario u: model.usuarios){
                // Se verifica que el usuario y contrseña sean correctos
                if(u.noCuenta.equals(clienteLogIn.accountTxField.getText())
                && u.nip == Integer.valueOf(clienteLogIn.nipPswField.getText())){
                    flag = false;
                    userFrame.setLocationRelativeTo(null);
                    userFrame.setVisible(true);
                    userFrame.bienvenidoLabel.setText("Bienvenido "+u.name+" "+u.lastName);
                    model.saveUsuarioParaCuenta(u);
                    userFrame.saldoTxField.setText(Float.toString(u.saldoInicial));
                    userFrame.saldoTxField.setEditable(false);
                    clienteLogIn.dispose();
                }
            }
            if(flag)
                JOptionPane.showMessageDialog(null,"Usuario y/o contraseña incorrectos","Error",JOptionPane.ERROR_MESSAGE);
                
            
        }
        
        // Fin botones cliente Login
        
        // Botones admin Login
        if (e.getSource() == adminLog.regresarBtn){
            view.setVisible(true);
            adminLog.accountTxField.setText(null);
            adminLog.nipPswField.setText(null);
            adminLog.dispose();
        }
        // Se verifica que el usuario y contrseña sean correctos
        if (e.getSource() == adminLog.logInBtn){
            if(adminLog.accountTxField.getText().equals(model.getAdminUser())
              &&adminLog.nipPswField.getText().equals(model.getAdminPswd())){
                adFrame.setLocationRelativeTo(null);
                adFrame.setVisible(true);
                adminLog.dispose();
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Usuario y/o contraseña incorrctos","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Fin botones admin Login
        
        // Botones clientesBD
        if(e.getSource() == adFrame.altaBtn){
            altaPanel.setSize(1200,400);
            altaPanel.setLocation(0, 0);
            adFrame.operatorPanel.removeAll();
            adFrame.operatorPanel.add(altaPanel, BorderLayout.CENTER);
            adFrame.operatorPanel.revalidate();
            adFrame.repaint();
        }
        
        if(e.getSource() == adFrame.bajaBtn){
            bajaPanel.setSize(1200,400);
            altaPanel.setLocation(0, 0);
            adFrame.operatorPanel.removeAll();
            adFrame.operatorPanel.add(bajaPanel, BorderLayout.CENTER);
            adFrame.operatorPanel.revalidate();
            adFrame.repaint();
        }
        
        if(e.getSource() == adFrame.exitBtn){
            view.setVisible(true);
            // alta panel restart
            altaPanel.inputNameTxField.setText(null);
            altaPanel.inputLastNameField.setText(null);
            altaPanel.inputNoCuentaTxField.setText(null);
            altaPanel.saldoTxField.setText(null);
            altaPanel.nipTxField.setText(null);
            // baja panel restart
            bajaPanel.inputNameTxField.setText(null);
            bajaPanel.inputLastNameField.setText(null);
            bajaPanel.inputNoCuentaTxField.setText(null);
            // admin login restart
            adminLog.accountTxField.setText(null);
            adminLog.nipPswField.setText(null);
            adFrame.dispose();
        }
        
        if(e.getSource() == altaPanel.registerBtn){
            // Posibles excepciones de tipos aquí
            if (altaPanel.inputNameTxField.getText().length() == 0 || altaPanel.inputLastNameField.getText().length() == 0)
                JOptionPane.showMessageDialog(null, "Datos incompletos", "Error",JOptionPane.ERROR_MESSAGE);
            else{
                try{
                model.addUser(new Usuario(
                        altaPanel.inputNameTxField.getText(),
                        altaPanel.inputLastNameField.getText(),
                        String.valueOf(model.agregarNoCuenta()),
                        Float.parseFloat(altaPanel.saldoTxField.getText()),
                        Integer.valueOf(altaPanel.nipTxField.getText())));
                        altaPanel.inputNoCuentaTxField.setText(String.valueOf(model.agregarNoCuenta()));
                        JOptionPane.showMessageDialog(null,"Operacion realizada con éxito", "Información",JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "Verifique que los datos sean correctos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Verificacion
            model.verify();
        }
        
        if(e.getSource() == bajaPanel.deleteBtn){
            boolean flag = true;
            // Exepciones aquí
            try{
            for (Usuario u: model.usuarios){
                if (u.name.equals(bajaPanel.inputNameTxField.getText())
                && u.lastName.equals(bajaPanel.inputLastNameField.getText())
                && u.noCuenta.equals(bajaPanel.inputNoCuentaTxField.getText())){
                    model.delUser(u);
                    flag = false;
                    JOptionPane.showMessageDialog(null,"Operacion realizada con éxito", "Información",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
           
            }
            if (flag)
                throw new UsuarioNoExistenteExeption();
            }catch(UsuarioNoExistenteExeption exeption){
                JOptionPane.showMessageDialog(null, exeption.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            // Verificacion
            model.verify();
        }
        
        // Fin botones clientesBD
        
        // Botones cuenta del cliente
        
        if (e.getSource() == userFrame.salirBtn){
            view.setVisible(true);
            retirarPanel.retirarTxFIeld.setText(null);
            depositarPanel.depositarTxFIeld.setText(null);
            clienteLogIn.accountTxField.setText(null);
            clienteLogIn.nipPswField.setText(null);
            userFrame.dispose();
        }
        
        if (e.getSource() == userFrame.retirarBtn){
            retirarPanel.setSize(1200,200);
            retirarPanel.setLocation(0,0);
            userFrame.btnActionsPanel.removeAll();
            userFrame.btnActionsPanel.add(retirarPanel, BorderLayout.CENTER);
            userFrame.btnActionsPanel.revalidate();
            userFrame.btnActionsPanel.repaint();
        }
        
        if (e.getSource() == userFrame.depositarBtn){
            depositarPanel.setSize(1200,200);
            depositarPanel.setLocation(0,0);
            userFrame.btnActionsPanel.removeAll();
            userFrame.btnActionsPanel.add(depositarPanel, BorderLayout.CENTER);
            userFrame.btnActionsPanel.revalidate();
            userFrame.btnActionsPanel.repaint();
            
        }
        if (e.getSource() == depositarPanel.depositarBtn){
            try {
                model.depositar(Float.parseFloat(depositarPanel.depositarTxFIeld.getText()));
                model.depDineroCajero(Float.parseFloat(depositarPanel.depositarTxFIeld.getText()));
                userFrame.saldoTxField.setText(Float.toString(model.usuarioParaCuenta.saldoInicial));
                System.out.println("Dinero en cajero: "+model.getDineroCajero());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null,"Solo se admiten números","Error",JOptionPane.ERROR_MESSAGE);
            } catch (NumerosNegativosExeption ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if (e.getSource() == retirarPanel.retirarBtn){
            try {
                model.retirar(Float.parseFloat(retirarPanel.retirarTxFIeld.getText()));
                model.retDineroCajero(Float.parseFloat(retirarPanel.retirarTxFIeld.getText()));
                userFrame.saldoTxField.setText(Float.toString(model.usuarioParaCuenta.saldoInicial));
                System.out.println("Dinero en cajero: "+model.getDineroCajero());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null,"Solo se admiten números","Error",JOptionPane.ERROR_MESSAGE);
            } catch (NoHaySuficienteDineroExeption | NumerosNegativosExeption exe) {
                JOptionPane.showMessageDialog(null, exe.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        // Fin botones cuenta del cliente
    }
    
   

  
    
}
