package application.view;

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

public class jogoTiroController {

    @FXML
    private Canvas canva;

    private double playerX = 200;
    private final double playerY = 500;
    private final double raio = 16;
    private final double larguraTela = 360;
    private final double alturaTela = 600;
    private int pontuacao = 0;
    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private ArrayList<Tiro> tiros = new ArrayList<>();
    private Random random = new Random();
    private boolean esquerda, direita;

    private Image imagemPlayer;
    private Image imagemObstaculo;
    

    public void initialize() {
        imagemPlayer = new Image(getClass().getResourceAsStream("nave.png"));
        imagemObstaculo = new Image(getClass().getResourceAsStream("neon.PNG"));

        canva.setFocusTraversable(true);
        canva.requestFocus();

        GraphicsContext gc = canva.getGraphicsContext2D();

        canva.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = true;
            if (e.getCode() == KeyCode.RIGHT) direita = true;
            if (e.getCode() == KeyCode.SPACE) {
                tiros.add(new Tiro(playerX - 2.5, playerY - raio)); // ✅ corrigido
            }
        });

        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = false;
            if (e.getCode() == KeyCode.RIGHT) direita = false;
        });

        AnimationTimer timer = new AnimationTimer() {
            long lastSpawn = 0;

            @Override
            public void handle(long now) {
                atualizar();
                desenhar(gc);

                if (now - lastSpawn > 1_000_000_000) {
                    obstaculos.add(new Obstaculo(random.nextInt((int) larguraTela - 40), -40));
                    lastSpawn = now;
                }
            }
        };

        timer.start();
    }

    private void atualizar() {
        if (esquerda && playerX - raio > 0) playerX -= 5;
        if (direita && playerX + raio < larguraTela) playerX += 5;

        // Atualiza obstáculos
        Iterator<Obstaculo> itObs = obstaculos.iterator();
        while (itObs.hasNext()) {
            Obstaculo obs = itObs.next();
            obs.y += 4;

            if (obs.y > alturaTela) {
                itObs.remove();
            }
        }

        // Atualiza tiros
        Iterator<Tiro> itTiro = tiros.iterator();
        while (itTiro.hasNext()) {
            Tiro tiro = itTiro.next();
            tiro.y -= 8;

            if (tiro.y < 0) {
                itTiro.remove();
                continue;
            }

            // Verifica colisão com obstáculos
            Iterator<Obstaculo> itObsColisao = obstaculos.iterator();
            while (itObsColisao.hasNext()) {
                Obstaculo obs = itObsColisao.next();
                boolean colidiu = tiro.y <= obs.y + obs.altura &&
                                  tiro.y + tiro.altura >= obs.y &&
                                  tiro.x + tiro.largura >= obs.x &&
                                  tiro.x <= obs.x + obs.largura;

                if (colidiu) {
                    pontuacao++;
                    itObsColisao.remove();
                    itTiro.remove();
                    break;
                }
            }
        }
    }

    private void desenhar(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, larguraTela, alturaTela);

        // Estrelas
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            double estrelaX = random.nextDouble() * larguraTela;
            double estrelaY = random.nextDouble() * alturaTela;
            gc.fillOval(estrelaX, estrelaY, 2, 2);
        }

        // ✅ Corrigido: agora a nave aparece na posição certa
        gc.drawImage(imagemPlayer, playerX - raio, playerY - raio, raio * 2, raio * 2);

        for (Obstaculo obs : obstaculos) {
            gc.drawImage(imagemObstaculo, obs.x, obs.y, obs.largura, obs.altura);
        }

        gc.setFill(Color.YELLOW);
        for (Tiro tiro : tiros) {
            gc.fillRect(tiro.x, tiro.y, tiro.largura, tiro.altura);
        }

        // ✅ texto branco para aparecer
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font(18));
        gc.fillText("Pontuação: " + pontuacao, 10, 20);
    }

    // Classe de obstáculos
    class Obstaculo {
        double x, y;
        final double largura = 70;
        final double altura = 90;

        Obstaculo(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Classe de tiros
    class Tiro {
        double x, y;
        final double largura = 5;
        final double altura = 10;

        Tiro(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
