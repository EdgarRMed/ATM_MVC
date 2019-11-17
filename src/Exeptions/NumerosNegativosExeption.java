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
public class NumerosNegativosExeption extends Exception {

    /**
     * Creates a new instance of <code>NumerosNegativosExeption</code> without
     * detail message.
     */
    public NumerosNegativosExeption() {
        super("No se admiten números negativos");
    }

    /**
     * Constructs an instance of <code>NumerosNegativosExeption</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NumerosNegativosExeption(String msg) {
        super(msg);
    }
}
