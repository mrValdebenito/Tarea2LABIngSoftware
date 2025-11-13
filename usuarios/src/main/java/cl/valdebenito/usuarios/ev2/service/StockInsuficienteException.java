package cl.valdebenito.usuarios.ev2.service;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}