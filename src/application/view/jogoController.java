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
    private Image imagemFundo;

    // --- TURBO ---
    private boolean turboAtivo = false;                 // indica se o turbo está ligado
    private long tempoTurboAtivado = 0;                 // guarda o momento em que ativou
    private final long duracaoTurbo = 2_000_000_000L;   // 2 segundos (em nanos)

    @FXML
    public void initialize() {
        // carrega imagens do jogador e obstáculo
    	InputStream fundoStream = getClass().getResourceAsStream("estrada.jpg");
    	imagemFundo = new Image(fundoStream);

        InputStream playerStream = getClass().getResourceAsStream("f1_azul.png");
        InputStream obstaculoStream = getClass().getResourceAsStream("f1_vermelho.png");
        imagemPlayer = new Image(playerStream);
        imagemObstaculo = new Image(obstaculoStream);

        // inicia com o foco no jogo
        canva.setFocusTraversable(true);
        canva.requestFocus();

        GraphicsContext gc = canva.getGraphicsContext2D();

        // controla teclas pressionadas
        canva.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = true;
            if (e.getCode() == KeyCode.RIGHT) direita = true;
            if (e.getCode() == KeyCode.SHIFT && !turboAtivo) { // ativa turbo
                turboAtivo = true;
                tempoTurboAtivado = System.nanoTime();
            }
        });

        // controla teclas soltas
        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = false;
            if (e.getCode() == KeyCode.RIGHT) direita = false;
        });

        // cria o loop do jogo
        AnimationTimer timer = new AnimationTimer() {
            long ultimoSpawn = 0;
            long intervaloSpawn = 600_000_000; // 1 segundo

            @Override
            public void handle(long now) {
                atualizar(now);
                desenhar(gc);

                if (now - ultimoSpawn > intervaloSpawn) {
                    obstaculos.add(new Obstaculo(random.nextInt((int) larguraTela - 70), -60));
                    ultimoSpawn = now;
                }
            }
        };
        timer.start();
    }

    private void atualizar(long now) {
        // tempo de turbo
        if (turboAtivo && (now - tempoTurboAtivado > duracaoTurbo)) {
            turboAtivo = false;
        }

        double velocidadeJogador = turboAtivo ? 15 : 8; // dobra velocidade no turbo

        // movimentação do jogador
        if (esquerda && playerX - raio > 0) playerX -= velocidadeJogador;
        if (direita && playerX + raio < larguraTela) playerX += velocidadeJogador;

        double velocidadeObstaculo = turboAtivo ? 12 : 8; // obstáculos ficam um pouco mais rápidos

        // atualiza obstáculos
        Iterator<Obstaculo> it = obstaculos.iterator();
        while (it.hasNext()) {
            Obstaculo obs = it.next();
            obs.y += velocidadeObstaculo;

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

    private void desenhar(GraphicsContext gc) {
        // fundo
    	// fundo com imagem
    	gc.drawImage(imagemFundo, 0, 0, larguraTela, alturaTela);


        // jogador
        gc.drawImage(imagemPlayer, playerX - raio, playerY - raio, raio * 4, raio * 4);

        // --- efeito do turbo (chamas atrás do carro) ---
        if (turboAtivo) {
            gc.setFill(Color.ORANGE);
            gc.fillOval(playerX + 10, playerY + raio * 2, 10, 10);
            gc.setFill(Color.YELLOW);
            gc.fillOval(playerX + 12, playerY + raio * 2 + 5, 6, 6);
        }

        // obstáculos
        for (Obstaculo obs : obstaculos) {
            gc.drawImage(imagemObstaculo, obs.x, obs.y, obs.largura, obs.altura);
        }

        // pontuação
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font(18));
        gc.fillText("Pontuação: " + pontuacao, 10, 20);

        // barra de turbo
        gc.setStroke(Color.BLACK);
        gc.strokeRect(10, 35, 100, 10);
        if (turboAtivo) {
            double restante = (duracaoTurbo - (System.nanoTime() - tempoTurboAtivado)) / (double) duracaoTurbo;
            double larguraBarra = Math.max(0, restante * 100);
            gc.setFill(Color.LIMEGREEN);
            gc.fillRect(10, 35, larguraBarra, 10);
        } else {
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(10, 35, 100, 10);
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
