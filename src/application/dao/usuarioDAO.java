package application.dao;

import java.awt.Button;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import application.model.usuarioModel;
import application.util.conexao;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class usuarioDAO {
	@FXML
    private Button btnSalvar;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNomeCompleto;

    @FXML
    private PasswordField txtSenha;

	//INSERIOR USUARIO
	public boolean inserirUsuario(usuarioModel u) {
		try {
		Connection conn=null;
		PreparedStatement query=null;

		conn=conexao.getConnection();
		String sql="insert usuario (nomeCompleto,login,senha)" + "values (?,?,?)";

		query= conn.prepareStatement(sql);
		query.setString(1, u.getNomeCompleto());
		query.setString(2, u.getLogin());
		query.setString(3, u.getSenha());

		int insert = query.executeUpdate();

		return insert > 0;

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// AUTENTICAR USUÁRIO
	public boolean autenticar (String usuario, String senha) {
		try {
			Connection conn = conexao.getConnection();
			String sql="select *from usuario where BINARY login=? and BINARY senha=?";
			PreparedStatement query=conn.prepareStatement(sql);
			query.setString(1, usuario);
			query.setString(2, senha);
			
			ResultSet resultado=query.executeQuery();
			return resultado.next();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}