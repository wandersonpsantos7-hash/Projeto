package application.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class carrosController {

    @FXML
    private ImageView imgAudi, imgBMW, imgCamaro, imgMustang, imgPorsche, imgFerrari;

    private Map<ImageView, modeloCarrosController> carrosMap = new HashMap<>();

    @FXML
    public void initialize() {
        // ---------------------------------------------------------------------------------------
        // CORREÇÃO: Os caminhos de imagem devem incluir o pacote raiz 'application'.
        // Agora, o caminho é ABSOLUTO a partir da raiz do classpath: /application/view/NomeDaImagem.png
        // ---------------------------------------------------------------------------------------
        carrosMap.put(imgAudi, new modeloCarrosController("Audi R8 V10", "5.2 V10", "Cinza Daytona", "2023", "Quattro Coupé", "/application/view/Audi.png"));
        carrosMap.put(imgBMW, new modeloCarrosController("BMW M4", "3.0L Twin-Turbo", "Azul", "2023", "M4 Competition", "/application/view/BMW.png"));
        carrosMap.put(imgCamaro, new modeloCarrosController("Chevrolet Camaro SS", "6.2 V8", "Preto", "2023", "SS Coupe", "/application/view/Camaro.png"));
        carrosMap.put(imgMustang, new modeloCarrosController("Ford Mustang GT", "5.0 V8", "Vermelho", "2023", "GT Premium Fastback", "/application/view/Mustang.png"));
        carrosMap.put(imgPorsche, new modeloCarrosController("Porsche 911 Turbo", "3.8L Twin-Turbo", "Cinza", "2023", "911 Turbo S", "/application/view/Porsche.png"));
        carrosMap.put(imgFerrari, new modeloCarrosController("Ferrari F8 Tributo", "3.9 V8 Biturbo", "Vermelho Corsa", "2023", "F8 Coupé", "/application/view/Ferrari.png"));

        for (ImageView img : carrosMap.keySet()) {
            img.setCursor(Cursor.HAND);
            img.setOnMouseClicked(this::mostrarInfoCarro);
        }
    }

    private void mostrarInfoCarro(MouseEvent event) {
        ImageView clicada = (ImageView) event.getSource();
        modeloCarrosController carro = carrosMap.get(clicada);

        if (carro != null) {
            try {
                // Caminho de FXML corrigido para ser relativo ao controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detalhes.fxml")); 
                Parent root = loader.load();

                detalhesController detalhesController = loader.getController();
                detalhesController.receberInfo(carro.getInfo(), carro.getImagem());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Detalhes do Carro");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void voltarTelaInicial(ActionEvent event) {
        try {
            // Ajuste no caminho do FXML para absoluto: /application/view/aplicativo.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/aplicativo.fxml")); 
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    @FXML
    public void logout(MouseEvent event) {
        try {
            // Caminho de FXML corrigido para ser relativo ao controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml")); 
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            // Fecha a tela atual
            Stage atual = (Stage) ((Label) event.getSource()).getScene().getWindow();
            atual.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}