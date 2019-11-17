/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Exeptions.NoHaySuficienteDineroExeption;
import Exeptions.NumerosNegativosExeption;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Model {
    
    public ArrayList<Usuario> usuarios;
    private float dineroCajero;
    private final String adminUser;
    private final String adminPswd;
    private Usuario defaultUser;
    public Usuario usuarioParaCuenta;
    public static int noCuenta;

    public Model() {
        dineroCajero = 50000;
        noCuenta = 1298371234;
        usuarios = new ArrayList<>();
        this.adminUser = "root";
        this.adminPswd = "bankcode";
        setDefaultUser();
    }

    public void setDefaultUser(){
        defaultUser = new Usuario("Juan", "Montiel",String.valueOf(noCuenta), 4000,0000);
        usuarios.add(defaultUser);
    }
    
    public void addUser(Usuario u){
        // Exepcion aqui
        usuarios.add(u);
    }
    
    public void delUser(Usuario u){
        // Excepcion aqui
        usuarios.remove(u);
    }
    
    public String getAdminUser() {
        return adminUser;
    }

    public String getAdminPswd() {
        return adminPswd;
    }
    
    public void saveUsuarioParaCuenta(Usuario u){
        usuarioParaCuenta = u;
    }
    
    public void depositar(float deposito) throws NumerosNegativosExeption{
        if (deposito < 0)
            throw new NumerosNegativosExeption();
        else{
            usuarios.stream().filter((usr) -> (usr == usuarioParaCuenta)).forEachOrdered((usr) -> {
                usuarioParaCuenta.saldoInicial += deposito;
                usr.saldoInicial = usuarioParaCuenta.saldoInicial;
                JOptionPane.showMessageDialog(null,"Deposito realizado con éxito","Información",JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }
    
    public void retirar (float retiro) throws NoHaySuficienteDineroExeption, NumerosNegativosExeption{
        if(retiro < 0)
            throw new NumerosNegativosExeption();
        else{
            if (usuarioParaCuenta.saldoInicial < retiro)
                throw new NoHaySuficienteDineroExeption("No hay suficiente dinero en su cuenta");
            else
                if (dineroCajero < retiro)
                    throw new NoHaySuficienteDineroExeption("No hay suficiente dinero en el cajero");
                else{
                    usuarios.stream().filter((usr) -> (usr == usuarioParaCuenta)).forEachOrdered((usr) -> {
                        usuarioParaCuenta.saldoInicial -= retiro;
                        usr.saldoInicial = usuarioParaCuenta.saldoInicial;
                        JOptionPane.showMessageDialog(null,"Deposito realizado con éxito","Información",JOptionPane.INFORMATION_MESSAGE);

                });
                }
            }
    
    }
    
    public void retDineroCajero(float dineroCajero){
        this.dineroCajero -= dineroCajero;
    }
    
    public void depDineroCajero(float dineroCajero) {
        this.dineroCajero += dineroCajero;
    }

    public float getDineroCajero() {
        return dineroCajero;
    }
    
    public int agregarNoCuenta(){
        noCuenta +=1;
        return noCuenta;
    }
    
    // Verificacion
    public void verify(){
        System.out.println("Usuarios en la base de datos");
        usuarios.forEach((a) -> {
            System.out.println(a.name);
            System.out.println("Número de cuenta: "+a.noCuenta);
        });
        System.out.println();
        System.out.println();
    }
    
    
    
    
}
