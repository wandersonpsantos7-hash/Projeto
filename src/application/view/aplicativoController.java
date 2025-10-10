package application.view;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class aplicativoController {

    @FXML
    private AnchorPane conteudoPane;

    private void carregarTela(String fxmlfile) {
        try {
            URL fxmlLocation = getClass().getResource(fxmlfile);
            if (fxmlLocation == null) {
                System.err.println("ERRO CRÍTICO: Não foi possível encontrar o arquivo: " + fxmlfile);
                return;
            }

            Parent fxml = FXMLLoader.load(fxmlLocation);
            conteudoPane.getChildren().clear();
            conteudoPane.getChildren().add(fxml);

            AnchorPane.setTopAnchor(fxml, 0.0);
            AnchorPane.setBottomAnchor(fxml, 0.0);
            AnchorPane.setLeftAnchor(fxml, 0.0);
            AnchorPane.setRightAnchor(fxml, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCalculadora() {
        carregarTela("calculadora.fxml");
    }

    @FXML
    private void initialize() {
        // Nada necessário aqui por enquanto
    }
}