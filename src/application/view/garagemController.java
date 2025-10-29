package application.view; 

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button; 
import javafx.stage.Stage;
import javafx.event.ActionEvent; 

import java.io.IOException;
import java.util.HashMap;  
import java.util.Map;      

// Importa a classe de modelo e a classe do outro controlador
import application.view.modeloCarro; 
import application.view.detalhesController; // 2. CORREÇÃO: Importa a classe do outro Controller (com 'D' maiúsculo)

public class garagemController { // 1. CORREÇÃO: Nome da classe com PascalCase
    
    // Injeção dos 5 botões (seus fx:id no FXML)
    @FXML private Button btnOpala; 
    @FXML private Button btnR34;
    @FXML private Button btnC8;
    @FXML private Button btnJesko;
    @FXML private Button btnChevette;
   
    private Map<Button, modeloCarro> carrosMap; 

    @FXML
    public void initialize() {
        
        // Cria e inicializa os 5 objetos modeloCarro
        modeloCarro carro1 = new modeloCarro("Chevrolet", "Opala", "4.1L (6 Cilindros)", "Marrom", 1978, "Sedan/Coupe Clássico");
        modeloCarro carro2 = new modeloCarro("Nissan", "Skyline R34", "2.6L RB26DETT Biturbo", "Azul", 1999, "JDM Esportivo");
        modeloCarro carro3 = new modeloCarro("Chevrolet", "Corvette C8", "6.2L LT2 V8", "Vermelho", 2024, "Esportivo Mid-Engine");
        modeloCarro carro4 = new modeloCarro("Koenigsegg", "Jesko", "5.0L V8 Twin-Turbo", "Branco/Preto", 2023, "Hipercarro");
        modeloCarro carro5 = new modeloCarro("Chevrolet", "Chevette", "1.6L S", "Amarelo", 1985, "Popular Nacional");

        // Mapeia o BOTÃO (Button) para o objeto modeloCarro
        carrosMap = new HashMap<>();
        carrosMap.put(btnOpala, carro1);
        carrosMap.put(btnR34, carro2);
        carrosMap.put(btnC8, carro3);
        carrosMap.put(btnJesko, carro4);
        carrosMap.put(btnChevette, carro5);
    }

    /**
     * Lida com o clique de um BOTÃO (via onAction no FXML) e abre a nova janela.
     */
    @FXML
    private void handleCarroClick(ActionEvent event) {
        
        Button sourceButton = (Button) event.getSource();
        modeloCarro carroSelecionado = carrosMap.get(sourceButton);

        if (carroSelecionado != null) {
            try {
                // Carrega o FXML da tela de detalhes (MANTENHA ESTE CAMINHO CORRETO)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("opala.fxml"));
                Parent root = loader.load();
                
                // Obtém o Controller (agora com 'D' maiúsculo)
                detalhesController detalhesController = loader.getController(); // 3. CORREÇÃO: Tipo correto
                
                Stage detalhesStage = new Stage();
                detalhesStage.setTitle("Detalhes do " + carroSelecionado.getNome());
                detalhesStage.setScene(new Scene(root));
                
                // 4. CHAMADA CORRIGIDA: Passa o carro e o Stage.
                // Este método deve existir com esta assinatura no DetalhesController.
                detalhesController.setmodeloCarro(carroSelecionado, detalhesStage); 
                
                detalhesStage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao carregar a tela de detalhes: " + e.getMessage());
            }
        }
    }
}