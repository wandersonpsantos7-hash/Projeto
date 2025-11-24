package application.view;

public class modeloCarrosController {
    private String modelo, motor, cor, ano, tipo, imagem;

    public modeloCarrosController(String modelo, String motor, String cor, String ano, String tipo, String imagem) {
        this.modelo = modelo;
        this.motor = motor;
        this.cor = cor;
        this.ano = ano;
        this.tipo = tipo;
        this.imagem = imagem;
    }

    public String getInfo() {
        return String.format(
            "Modelo: %s\nMotor: %s\nCor: %s\nAno: %s\nTipo: %s",
            modelo, motor, cor, ano, tipo
        );
    }

    public String getImagem() {
        return imagem;
    }
}