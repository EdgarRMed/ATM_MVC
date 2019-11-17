/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exeptions;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UsuarioNoExistenteExeption extends Exception {

    /**
     * Creates a new instance of <code>UsuarioNoExistenteExeption</code> without
     * detail message.
     */
    public UsuarioNoExistenteExeption() {
        super("Usuario no existente");
    }

    /**
     * Constructs an instance of <code>UsuarioNoExistenteExeption</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioNoExistenteExeption(String msg) {
        super(msg);
    }
}
