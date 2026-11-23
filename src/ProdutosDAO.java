import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    public boolean cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conn = new conectaDAO().connectDB()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, produto.getNome());
                stmt.setInt(2, produto.getValor());
                stmt.setString(3, produto.getStatus());

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException erro) {
            System.err.println("Erro ao cadastrar produto: " + erro.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        return new ArrayList<>();
    }
}