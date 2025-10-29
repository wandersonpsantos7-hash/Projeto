package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class notasController {

    @FXML
    private Button btnMedia;

    @FXML
    private Label lblResultadoMedia;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    private TextField txtNota4;
    
    @FXML
    public void initialize() {
        txtNota1.setOnAction(e -> txtNota2.requestFocus());
        txtNota2.setOnAction(e -> txtNota3.requestFocus());
        txtNota3.setOnAction(e -> txtNota4.requestFocus());
        txtNota4.setOnAction(e -> btnMedia.requestFocus());
        
        txtNota4.setOnAction(e -> calcularNotas());
        
    }
    
@FXML
private void calcularNotas() {
	
	
	double nota1= Double.valueOf(txtNota1.getText());
	double nota2= Double.valueOf(txtNota2.getText());
	double nota3= Double.valueOf(txtNota3.getText());
	double nota4= Double.valueOf(txtNota4.getText());
	double media= (nota1 + nota2 + nota3 + nota4) / 4;
	
	String classificacaoMedia = "";
	if (media < 5) {
		classificacaoMedia = "Reprovado!";
	} else if (media >= 5 && media <= 6.9) {
		classificacaoMedia = "recuperação!";
	} else
		classificacaoMedia = "Aprovado!";
	
	
	String resultadoFormatado = String.format("%.1f", media);
	lblResultadoMedia.setText("Média final é: " + resultadoFormatado + "\n(" + classificacaoMedia + ")");

}
}
