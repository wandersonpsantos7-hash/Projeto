package application.view; 

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage; 

// Importa a classe do outro Controller (garantindo que o import exista!)
// import application.view.DetalhesCarroController; 

public class garagemController { 

    // Botões injetados pelo FXML Loader
    @FXML private Button btnOpala;
    @FXML private Button btnR34;
    @FXML private Button btnC8;
    @FXML private Button btnJesko;
    @FXML private Button btnChevette;
   
    private Map<Button, modeloCarro> carrosMap; 

    @FXML
    public void initialize() {
        
        // A. Criação dos objetos modeloCarro (Pode ficar aqui)
        modeloCarro carro1 = new modeloCarro("Chevrolet", "Opala", "4.1L (6 Cilindros)", "Marrom", 1978, "Sedan/Coupe Clássico");
        modeloCarro carro2 = new modeloCarro("Nissan", "Skyline R34", "2.6L RB26DETT Biturbo", "Azul", 1999, "JDM Esportivo");
        modeloCarro carro3 = new modeloCarro("Chevrolet", "Corvette C8", "6.2L LT2 V8", "Vermelho", 2024, "Esportivo Mid-Engine");
        modeloCarro carro4 = new modeloCarro("Koenigsegg", "Jesko", "5.0L V8 Twin-Turbo", "Branco/Preto", 2023, "Hipercarro");
        modeloCarro carro5 = new modeloCarro("Chevrolet", "Chevette", "1.6L S", "Amarelo", 1985, "Popular Nacional");

        // B. Inicialização do Map (Deve ser feito ANTES de ser usado)
        carrosMap = new HashMap<>();

        // C. O mapeamento DEVE ser feito APÓS a inicialização dos componentes FXML.
        // Para simplificar e garantir, faremos a inicialização do Map e o Mapeamento aqui,
        // confiando que o FXML injetou os botões com sucesso. Se houver erro, a solução ideal
        // é usar um método @Override public void start(Stage primaryStage) na classe principal.
        
        // Mapeamento: Associe o botão (chave) ao objeto carro (valor)
        // **Este bloco é a parte crítica movida para cá**
        carrosMap.put(btnOpala, carro1);
        carrosMap.put(btnR34, carro2);
        carrosMap.put(btnC8, carro3);
        carrosMap.put(btnJesko, carro4);
        carrosMap.put(btnChevette, carro5);
    }


    @FXML
    private void handleCarroClick(ActionEvent event) {
        
        // 1. Identifica qual botão foi clicado (sourceButton)
        Button sourceButton = (Button) event.getSource();
        
        // 2. Obtém o objeto modeloCarro correspondente no Map
        modeloCarro carroSelecionado = carrosMap.get(sourceButton);

        if (carroSelecionado != null) {
            try {
                // O caminho do FXML é relativo ao Controller (package application.view)
                // Se o arquivo estiver no mesmo pacote, o caminho está correto.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detalhesCarro.fxml"));
                Parent root = loader.load();
                
                // 3. Obtém o Controller de Detalhes
                DetalhesCarroController detalhesController = loader.getController();
                
                // 4. PASSO ESSENCIAL: Passa o objeto Carro
                detalhesController.setCarro(carroSelecionado);
                
                // 5. Abre a nova janela
                Stage detalhesStage = new Stage();
                detalhesStage.setTitle("Detalhes do " + carroSelecionado.getNome());
                detalhesStage.setScene(new Scene(root));
                detalhesStage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao carregar a tela de detalhes: " + e.getMessage());
            }
        }
    }
}