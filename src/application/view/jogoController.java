package application.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private Random random = new Random();
    private boolean esquerda, direita;

    private Image imagemPlayer;
    private Image imagemObstaculo;


    @FXML
    public void initialize() {
        // carrega imagens do jogador e obstáculo
        InputStream playerStream = getClass().getResourceAsStream("f1_azul.png");
        InputStream obstaculoStream = getClass().getResourceAsStream("f1_vermelho.png");
        imagemPlayer = new Image(playerStream);
        imagemObstaculo = new Image(obstaculoStream);

        // inicia com o foco no jogo
        canva.setFocusTraversable(true);
        canva.requestFocus();

        // obtém contexto gráfico
        GraphicsContext gc = canva.getGraphicsContext2D();

        // controla teclas pressionadas
        canva.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = true;
            if (e.getCode() == KeyCode.RIGHT) direita = true;
        });

        // controla teclas soltas
        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = false;
            if (e.getCode() == KeyCode.RIGHT) direita = false;
        });

        // cria o loop do jogo
        AnimationTimer timer = new AnimationTimer() {
            long ultimoSpawn = 0;
            long intervaloSpawn = 1_000_000_000; // 1 segundo

            @Override
            public void handle(long now) {
                atualizar();
                desenhar(gc);

                if (now - ultimoSpawn > intervaloSpawn) {
                    obstaculos.add(new Obstaculo(random.nextInt((int) larguraTela - 70), -60));
                    ultimoSpawn = now;
                }
            }
        };
        timer.start();
    }

    private void desenhar(GraphicsContext gc) {
    	

        // fundo
        gc.setFill(Color. GRAY);
        gc.fillRect(0, 0, larguraTela, alturaTela);

        // jogador
        gc.drawImage(imagemPlayer, playerX - raio, playerY - raio, raio * 4, raio * 4);

        // obstáculos
        for (Obstaculo obs : obstaculos) {
            gc.drawImage(imagemObstaculo, obs.x, obs.y, obs.largura, obs.altura);
        }

        // pontuação
        gc.setFill(Color.BLACK);
        gc.setFont(javafx.scene.text.Font.font(18));
        gc.fillText("Pontuação: " + pontuacao, 10, 20);
    }

    private void atualizar() {
        // movimentação do jogador
        if (esquerda && playerX - raio > 0) playerX -= 5;
        if (direita && playerX + raio < larguraTela) playerX += 5;

        double velocidade = 4;

        // atualiza obstáculos
        Iterator<Obstaculo> it = obstaculos.iterator();
        while (it.hasNext()) {
            Obstaculo obs = it.next();
            obs.y += velocidade;

            double centroPlayerX = playerX + raio;
            double centroPlayerY = playerY + raio;
            double obsTopo = obs.y;
            double obsBase = obs.y + obs.altura;
            double obsEsquerda = obs.x;
            double obsDireita = obs.x + obs.largura;

            boolean colidiu =
                    centroPlayerX >= obsEsquerda &&
                    centroPlayerX <= obsDireita &&
                    centroPlayerY >= obsTopo &&
                    centroPlayerY <= obsBase;

            if (colidiu) {
                pontuacao = 0; // resetar pontuação ao colidir
                it.remove();
            } else if (obs.y > alturaTela) {
                pontuacao++; // ganha ponto quando o obstáculo sai da tela
                it.remove();
            }
        }
    }

    // classe interna de obstáculos
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
