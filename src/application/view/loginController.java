package application.view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
	
	public void sair() {
		System.exit(0);
	}
	
	public void entrar() {
		try {
		String usuario=txtUsuario.getText();
		String senha=txtSenha.getText();
		
		if (usuario.equals("wp")  && senha.equals("admin")) {
			Alert aviso;
			aviso=new Alert(Alert.AlertType.CONFIRMATION);
			aviso.setTitle("Confirmação");
			aviso.setHeaderText(null);
			aviso.setContentText("Bem Vindo ao Sistema "+usuario);
			aviso.showAndWait();
			
			//FECHAR TELA DE LOGIN
			txtUsuario.getScene().getWindow().hide();
			
			//ABRE A TELA PRINCIPAL
			Parent root = FXMLLoader.load(getClass().getResource("aplicativo.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			
		} else {
			Alert aviso;
			aviso=new Alert(Alert.AlertType.ERROR);
			aviso.setTitle("Erro");
			aviso.setHeaderText(null);
			aviso.setContentText("Usuário ou senha incorretos");
			aviso.showAndWait();
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML  
	/* @FXML OU @override -> indica que o codigo  
	será executado assim que carregar a página*/
	private void initialize() {
	/* QUANDO PRESSIONAR ENTER NO CAMPO USUARIO 
		FOCA A EDIÇÃO NO CAMPO DE SENHA
	*/
		txtUsuario.setOnAction(e->{txtSenha.requestFocus();});
	/* QUANDO PRESSIONAR ENTER NO CAMPO SENHA 
		ACIONA O METODO DE ENTRAR
	*/
		txtSenha.setOnAction(e->{entrar();});
	}
}