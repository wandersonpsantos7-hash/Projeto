package application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

	@FXML
	private TextField txtSenha;

	@FXML
	private TextField txtUsuario;

	public void sair() {
		System.exit(0);
	}

	public void entrar() {
		try {
			String usuario = txtUsuario.getText();
			String senha = txtSenha.getText();

			if (usuario.equals("wanderson") && senha.equals("09062007")) {
				Alert aviso;
				aviso = new Alert(Alert.AlertType.CONFIRMATION);
				aviso.setTitle("Confirmação");
				aviso.setHeaderText(null);
				aviso.setContentText("Bem Vindo Ao Sistema " + usuario);
				aviso.showAndWait();

				txtUsuario.getScene().getWindow().hide();

				Parent root = FXMLLoader.load(getClass().getResource("aplicativo.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			} else {
				Alert aviso;
				aviso = new Alert(Alert.AlertType.ERROR);
				aviso.setTitle("Erro");
				aviso.setHeaderText(null);
				aviso.setContentText("Usuário ou Senha Incorretos");
				aviso.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML

	private void initialize() {

		txtUsuario.setOnAction(e -> {
			txtSenha.requestFocus();
		});
		
		txtSenha.setOnAction(e->{entrar();});
	}
}
