package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class calculadoraController {

    @FXML
    private Button btnSoma;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;
    
    // Este método é chamado automaticamente quando o FXML é carregado
    @FXML
    private void initialize() {
    }

    // Corrigido: Adicionado @FXML e nome do método em minúsculo
    @FXML
    private void somar() {
        try {
            double numero1 = Double.parseDouble(txtNumero1.getText());
            double numero2 = Double.parseDouble(txtNumero2.getText());
            double resultado = numero1 + numero2;
            
            lblResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException e) {
            lblResultado.setText("Erro: Entrada inválida");
        }
    }
}