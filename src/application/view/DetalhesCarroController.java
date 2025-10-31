package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// IMPORTANTE: Este controller precisa ter o fx:controller definido no DetalhesView.fxml

public class detalhesController {
    
    // ATENÇÃO: Seus fx:ids no DetalhesView.fxml DEVEM CORRESPONDER a estes nomes!
    @FXML private Label marcaLabel;
    @FXML private Label nomeLabel;
    @FXML private Label motorLabel;
    @FXML private Label corLabel;
    @FXML private Label anoLabel;
    @FXML private Label modeloLabel;
    @FXML private Label statusLabel;
    // ... (Seus botões e outros componentes)

    private Stage stage; // Atributo para guardar a referência da janela
    
    // MÉTODO CORRIGIDO (ACEITANDO DOIS ARGUMENTOS)
    public void setmodeloCarro(modeloCarro carro, Stage stage) {
        this.stage = stage;
        
        // Preenchimento dos Labels com os dados do carro
        marcaLabel.setText("Marca: " + carro.getMarca());
        nomeLabel.setText("Nome: " + carro.getNome());
        motorLabel.setText("Motor: " + carro.getMotor());
        corLabel.setText("Cor: " + carro.getCor());
        anoLabel.setText("Ano: " + carro.getAno());
        modeloLabel.setText("Modelo: " + carro.getModelo());
        
        statusLabel.setText("Status: Desligado.");
        // ... (Outra lógica de inicialização)
    }
    
    // Exemplo de ação de voltar usando a referência do Stage
    @FXML
    private void acaoVoltar() {
        if (stage != null) {
            stage.close();
            // Aqui você pode reabrir a tela Garagem, se necessário.
        }
    }
    
    // ... (Seu método acaoLigar)
}