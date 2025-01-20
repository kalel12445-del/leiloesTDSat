
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class ProdutosDTO {
    private Integer id;
    private String nome;
    private Integer valor;
    private String status;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 
    public void venderProduto(ProdutosDTO p) {
        //...
    }
    
    public ArrayLisr<ProdutosDTO> listarProdutosVendidos() {
        ArrayLisr<ProdutosDTO> listaVendidos = null;
        return listaVendidos;
}
         public void venderProduto(int produtoId) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement prep = conn.prepareStatement(sql);
        prep.setInt(1, produtoId);
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    } finally {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
}

 public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> listagem;
    listagem.clear();
    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement prep = conn.prepareStatement(sql);
        ResultSet resultset = prep.executeQuery();
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setStatus(resultset.getString("status"));
            listagem.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    } finally {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
    return listagem;
}
