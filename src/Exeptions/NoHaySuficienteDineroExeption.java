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
public class NoHaySuficienteDineroExeption extends Exception {

    /**
     * Creates a new instance of <code>NoHaySuficienteDineroExeption</code>
     * without detail message.
     */
    public NoHaySuficienteDineroExeption() {
    }

    /**
     * Constructs an instance of <code>NoHaySuficienteDineroExeption</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoHaySuficienteDineroExeption(String msg) {
        super(msg);
    }
}
