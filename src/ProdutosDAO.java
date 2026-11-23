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

    ArrayList<ProdutosDTO> produtos = new ArrayList<>();

    String sql = "SELECT id, nome, valor, status FROM produtos";

    try (Connection conn = new conectaDAO().connectDB()) {

        if (conn == null) {
            return produtos;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                produtos.add(produto);
            }
        }

    } catch (SQLException erro) {

        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Erro ao listar produtos: " + erro.getMessage()
        );
    }

    return produtos;
}
    }
