package application.view;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class idadeController {

    @FXML
    private Button btnCalcular;

    @FXML
    private DatePicker dtIdade;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblResultado;

    @FXML
    private void initialize() {
        lblResultado.setVisible(false);
    }

    @FXML
    private void calcularIdade(ActionEvent event) {
        String nome = txtNome.getText();
        LocalDate dataNascimento = dtIdade.getValue();

        if (dataNascimento != null && nome != null && !nome.isEmpty()) {
            LocalDate hoje = LocalDate.now();
            Period idade = Period.between(dataNascimento, hoje);
            long diasVividos = ChronoUnit.DAYS.between(dataNascimento, hoje);
            String diaSemanaNascimento = dataNascimento.format(
                DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            );

            lblResultado.setText(
                nome + ", sua idade é: " + idade.getYears() + " anos.\n" +
                "Você já viveu " + diasVividos + " dias.\n" +
                "Você nasceu em uma " + diaSemanaNascimento + "."
            );
        } else {
            lblResultado.setText("Por favor, preencha todos os campos.");
        }

        lblResultado.setVisible(true);
    }
}