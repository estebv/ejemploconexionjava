package org.example.repositorios;

import org.example.controladores.Conexion;
import org.example.modelos.AcademicoNotas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAcademico {

    // CREATE - Registrar una nueva nota
    public void academicoNotasPost(AcademicoNotas datosReporte) {
        String sql = "INSERT INTO notas (estudiante, asignatura, nota) VALUES (?, ?, ?)";
        Conexion conexion = new Conexion();

        try (Connection conectar = conexion.conectar();
             PreparedStatement consulta = conectar.prepareStatement(sql)) {

            consulta.setString(1, datosReporte.getEstudiante());
            consulta.setString(2, datosReporte.getAsignatura());
            consulta.setDouble(3, datosReporte.getNota());
            consulta.executeUpdate();

            System.out.println("✅ Nota registrada correctamente en BD");

        } catch (SQLException error) {
            System.out.println("❌ Falló el registro de la nota: " + error.getMessage());
        }
    }

    // READ - Obtener todas las notas
    public void academicoNotasGet() {
        String sql = "SELECT * FROM notas";
        Conexion conexion = new Conexion();

        try (Connection conectar = conexion.conectar();
             Statement stmt = conectar.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📋 LISTADO DE NOTAS");
            System.out.println("--------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String estudiante = rs.getString("estudiante");
                String asignatura = rs.getString("asignatura");
                double nota = rs.getDouble("nota");

                System.out.println("ID: " + id);
                System.out.println("Estudiante: " + estudiante);
                System.out.println("Asignatura: " + asignatura);
                System.out.println("Nota: " + nota);
                System.out.println("--------------------------------------");
            }

        } catch (SQLException error) {
            System.out.println("❌ Error al leer las notas: " + error.getMessage());
        }
    }

    // UPDATE - Actualizar una nota existente
    public void academicoNotasPut(AcademicoNotas notaActualizada) {
        String sql = "UPDATE notas SET estudiante = ?, asignatura = ?, nota = ? WHERE id = ?";
        Conexion conexion = new Conexion();

        try (Connection conectar = conexion.conectar();
             PreparedStatement consulta = conectar.prepareStatement(sql)) {

            consulta.setString(1, notaActualizada.getEstudiante());
            consulta.setString(2, notaActualizada.getAsignatura());
            consulta.setDouble(3, notaActualizada.getNota());
            consulta.setInt(4, notaActualizada.getId());

            int filas = consulta.executeUpdate();

            if (filas > 0) {
                System.out.println("✅ Nota actualizada correctamente");
            } else {
                System.out.println("⚠️ No se encontró una nota con ese ID");
            }

        } catch (SQLException error) {
            System.out.println("❌ Error al actualizar la nota: " + error.getMessage());
        }
    }

    // DELETE - Eliminar una nota
    public void academicoNotasDelete(int id) {
        String sql = "DELETE FROM notas WHERE id = ?";
        Conexion conexion = new Conexion();

        try (Connection conectar = conexion.conectar();
             PreparedStatement consulta = conectar.prepareStatement(sql)) {

            consulta.setInt(1, id);
            int filas = consulta.executeUpdate();

            if (filas > 0) {
                System.out.println("🗑️ Nota eliminada correctamente");
            } else {
                System.out.println("⚠️ No se encontró una nota con ese ID");
            }

        } catch (SQLException error) {
            System.out.println("❌ Falló la eliminación: " + error.getMessage());
        }
    }
}
