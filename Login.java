package myproject.src.main.java.com.example;

import java.sql.*;

public class Login {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=FOOD_FLOW";
        String user = "sa";
        String password = "LuanVitor852@123";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String usuario = "sa";
            String senha = "LuanVitor852@123";

            // Consulta SQL para verificar o nome de usuário e senha
            String sql = "SELECT * FROM usuarios WHERE USUARIO_NOME=? AND USUARIO_SENHA=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setString(2, senha);
                ResultSet resultSet = statement.executeQuery();

                // Verificando se a consulta retornou algum resultado
                if (resultSet.next()) {
                    // Login bem-sucedido
                    System.out.println("Login bem-sucedido!");
                    // Adicione aqui o código para gerenciar a sessão do usuário
                } else {
                    // Login falhou
                    System.out.println("Nome de usuário ou senha incorretos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
