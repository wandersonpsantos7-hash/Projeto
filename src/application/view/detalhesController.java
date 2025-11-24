package application.view;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class detalhesController {

    @FXML
    private Label infoCarro;

    @FXML
    private ImageView imagemCarro;

    public void receberInfo(String texto, String caminhoImagem) {
        infoCarro.setText(texto);

        // ----------------------------------------------------
        // CORREÇÃO E BOAS PRÁTICAS: Verificando o recurso antes de criar a Image
        // O erro NullPointerException significa que getClass().getResourceAsStream(caminhoImagem) retornou null.
        // O caminho deve ser absoluto (começar com /) se for a partir da raiz do classpath.
        // Por exemplo: "/application/view/imagens/Mustang.png"
        // ----------------------------------------------------
        try (InputStream imageStream = getClass().getResourceAsStream(caminhoImagem)) {
            if (imageStream != null) {
                imagemCarro.setImage(new Image(imageStream));
            } else {
                System.err.println("ERRO: Imagem não encontrada no caminho (InputStream é nulo): " + caminhoImagem);
                // Opcional: Coloque uma imagem de erro/placeholder aqui
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ... (restante do código)

    @FXML
    private void voltarTelaCarros(ActionEvent event) {
        try {
            // Ajuste o caminho do FXML para absoluto se o pacote for application.view
            // Se o controller está em 'application.view', o FXML deve estar em '/application/view/Carros.fxml'
            // Assumindo que você quer voltar para o FXML no mesmo diretório '/view/' (relativo à raiz do classpath)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/carros.fxml"));
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
            // Caminho corrigido para absoluto (se 'login.fxml' estiver em 'application.view')
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/login.fxml"));
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
    @FXML
    private Label botaoLigar;

    @FXML
    private Circle luzStatus;

    private boolean carroLigado = false;

    @FXML
    public void alternarEstadoCarro(MouseEvent event) {
        carroLigado = !carroLigado;

        if (carroLigado) {
            luzStatus.setFill(Color.LIME); // Mudei para LIME para ser um verde mais vivo
            botaoLigar.setText("Desligar");
            botaoLigar.setStyle("-fx-text-fill: red;");
        } else {
            luzStatus.setFill(Color.DARKRED);
            botaoLigar.setText("Ligar");
            botaoLigar.setStyle("-fx-text-fill: lime;"); // Mudei para LIME para ser mais coerente
        }
    }
}