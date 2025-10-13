package application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class calcularIMCCotroller {

    @FXML
    private Button btnCalcularIMC;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPeso;

    @FXML
    	private void calcularIMC() {

    		String nome = txtNome.getText();
    		double peso = Double.valueOf(txtPeso.getText());
    		double altura = calculadoraController.StrToDbl(txtAltura.getText());
    		double imc=peso / (altura*altura);
    		String resultadoFormatado = String.format("%.2f", imc);
    		lblResultado.setText(nome + ", seu IMC é: " + resultadoFormatado );
    }
}