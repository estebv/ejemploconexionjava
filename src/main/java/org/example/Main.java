package org.example;

import org.example.modelos.Usuario;
import org.example.repositorios.RepositorioUsuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner leerTeclado = new Scanner(System.in);
        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        Usuario usuario= new Usuario();

        System.out.println("Plataforma de registro");
        System.out.println("***********************");
        System.out.println("\nBienvenido 😊");

        System.out.print("Digita tu nombre de usuario: ");
        usuario.setNombres(leerTeclado.nextLine());

        System.out.print("Digita tu contraseña: ");
        usuario.setContraseña(leerTeclado.nextLine());

        System.out.print("Que perfil tienes?: ");
        usuario.setPerfil(leerTeclado.nextLine());

        repositorioUsuario.usuarioPOST(usuario);


    }
}