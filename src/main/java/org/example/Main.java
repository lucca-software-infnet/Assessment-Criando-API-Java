package org.example;

import org.example.Controllers.UsuarioController;


import static spark.Spark.get;
import static spark.Spark.port;


public class Main {
    public static void main(String[] args) throws InterruptedException {
             UsuarioController usuarioController = new UsuarioController();

        port(4567);





    }
}