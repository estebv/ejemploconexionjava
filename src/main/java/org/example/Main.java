package org.example;

import org.example.modelos.AcademicoNotas;
import org.example.repositorios.RepositorioAcademico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RepositorioAcademico repo = new RepositorioAcademico();
        int opcion;

        do {
            System.out.println("\n===============================");
            System.out.println("📘 MENÚ DE GESTIÓN DE NOTAS");
            System.out.println("===============================");
            System.out.println("1️⃣ Registrar nueva nota");
            System.out.println("2️⃣ Mostrar todas las notas");
            System.out.println("3️⃣ Actualizar una nota");
            System.out.println("4️⃣ Eliminar una nota");
            System.out.println("0️⃣ Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n📝 Registrar nueva nota");
                    AcademicoNotas nuevaNota = new AcademicoNotas();

                    System.out.print("Nombre del estudiante: ");
                    nuevaNota.setEstudiante(scanner.nextLine());

                    System.out.print("Asignatura: ");
                    nuevaNota.setAsignatura(scanner.nextLine());

                    System.out.print("Nota: ");
                    nuevaNota.setNota(scanner.nextFloat());
                    scanner.nextLine();

                    repo.academicoNotasPost(nuevaNota);
                }

                case 2 -> {
                    System.out.println("\n📋 Listado de notas registradas:");
                    repo.academicoNotasGet();
                }

                case 3 -> {
                    System.out.println("\n✏️ Actualizar una nota existente");
                    AcademicoNotas notaEditada = new AcademicoNotas();

                    System.out.print("ID de la nota a actualizar: ");
                    notaEditada.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Nuevo nombre del estudiante: ");
                    notaEditada.setEstudiante(scanner.nextLine());

                    System.out.print("Nueva asignatura: ");
                    notaEditada.setAsignatura(scanner.nextLine());

                    System.out.print("Nueva nota: ");
                    notaEditada.setNota(scanner.nextFloat());
                    scanner.nextLine();

                    repo.academicoNotasPut(notaEditada);
                }

                case 4 -> {
                    System.out.println("\n🗑️ Eliminar una nota");
                    System.out.print("ID de la nota a eliminar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    repo.academicoNotasDelete(id);
                }

                case 0 -> System.out.println("👋 Saliendo del sistema... ¡Hasta pronto!");

                default -> System.out.println("⚠️ Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
