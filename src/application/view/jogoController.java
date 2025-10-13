package application.view;

import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class jogoController {

	@FXML
	private Canvas canva;

	private double playerX = 200;
	private final double playerY = 500;
	private final double raio = 16;
	private final double larguraTela = 360;
	private final double alturaTela = 600;
	private int pontuacao = 0;
	private ArrayList<Obstaculo> obstaculo = new ArrayList();
	private Random random = new Random();
	private boolean esquerda, direita;

	@FXML
	public void initialize() {
		canva.setFocusTraversable(true);
		canva.requestFocus();

		GraphicsContext gc = canva.getGraphicsContext2D();

		canva.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				esquerda = true;
			}
			if (e.getCode() == KeyCode.RIGHT) {
				esquerda = true;
			}
		});

		canva.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.LEFT) {
				esquerda = false;
			}
			if (e.getCode() == KeyCode.RIGHT) {
				esquerda = false;
			}
		});
	}

	private void desenhar(GraphicsContext gc) {
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(0, 0, larguraTela, alturaTela);

		gc.setFill(Color.RED);
		gc.fillOval(playerX - raio, playerY - raio, raio * 2, raio * 2);

		gc.setFill(Color.DARKGRAY);
		for (Obstaculo obs : obstaculo) {
			gc.fillRect(obs.x, obs.y, obs.largura, obs.altura);
		}

		gc.setFill(Color.BLACK);
		gc.setFont(javafx.scene.text.Font.font(18));
		gc.fillText("Pontuação" + pontuacao, 10, 20);
	}

	class Obstaculo {
		double x, y;
		final double largura = 70;
		final double altura = 60;

		Obstaculo(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}
