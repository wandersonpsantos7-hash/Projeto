package application.view; 

public class modeloCarro { 
    
    private String marca; 
    private String nome;
    private String motor;
    private String cor; 
    private int ano; 
    private String modelo; 

    // Construtor com 6 argumentos
    public modeloCarro(String marca, String nome, String motor, String cor, int ano, String modelo) {
        this.marca = marca;
        this.nome = nome;
        this.motor = motor;
        this.cor = cor;
        this.ano = ano;
        this.modelo = modelo;
    }

    // Getters
    public String getMarca() { return marca; } 
    public String getNome() { return nome; }
    public String getMotor() { return motor; }
    public String getCor() { return cor; } 
    public int getAno() { return ano; } 
    public String getModelo() { return modelo; } 
    
    // Método ligar
    public void ligar() { 
        System.out.println("O " + nome + " está sendo ligado...");
    }
}