package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class calculadoraController {

    @FXML private Button btnDividir;

    @FXML private Button btnMultiplicar;

    @FXML private Button btnSoma;

    @FXML private Button btnSubtrair;

    @FXML private Label lblResultado;

    @FXML private TextField txtNumero1;

    @FXML private TextField txtNumero2;
    
    @FXML private Button btnReset;

    @FXML
    private void initialize() {
    	
    	/*
    	iniciar programa com valores zerados */
    	txtNumero1.setText("0");
		txtNumero2.setText("0");
    	
    	/* O setOnAction aciona o evento do componente
    	por exemplo: o click no botão
    	ou o enter em um text field */
    	
    	btnSubtrair.setOnAction(e->{Subtrair();});
    	btnMultiplicar.setOnAction(e->{Multiplicar();});
    	btnDividir.setOnAction(e->{Dividir();});
    	btnReset.setOnAction(e->{
    		txtNumero1.setText("0");
    		txtNumero2.setText("0");
    		lblResultado.setText("Resultado:");    		
    	});
    	

    	/*adicionar um escutador de evento no 
    	text field de numero 1
    	ao digitar dentro do text fiel ele vai trocar a letra
    	 por uma informação vazia atravez do replaceAll*/
    	txtNumero1.textProperty().addListener(
    	(observable, oldValue, newValue)->{
    	txtNumero1.setText(newValue.replaceAll("[^\\d.]",""));	
    	});
    	
    	txtNumero2.textProperty().addListener(
    	(observable, oldValue, newValue)->{
    	 txtNumero2.setText(newValue.replaceAll("[^\\d.]",""));	
    	 });
    
    	
    
    }
    
  
    public void Somar() {
    	double numero1;
    	double numero2;
    	try {
    	 numero1 = Double.valueOf(txtNumero1.getText()); // utiliza o getText para retornar a informação digitada 
    	} catch(Exception e) {
    	 numero1=0;
    	 txtNumero1.setText("0");
    	}
    	
    	try {
       	 numero2 = Double.valueOf(txtNumero2.getText()); // utiliza o getText para retornar a informação digitada 
       	} catch(Exception e) {
       	 numero2=0;
       	 txtNumero2.setText("0");
       	}
    	
    	double resultado=numero1+numero2;
    	
    	String parOuImpar;
    	
    	if(resultado % 2 == 0){
    		parOuImpar=" é Par.";
    	} else {
    		parOuImpar=" é Impar.";
    	}
    	lblResultado.setText("Resultado:"+String.valueOf(resultado)+parOuImpar);
    	
    	// retorna o valor de double para string
    	//informa o resultado na label com o setText
    
    }
    
    public void Subtrair() {
    	double numero1 = StrToDbl(txtNumero1.getText()); // utiliza o getText para retornar a informação digitada 
    	double numero2 = StrToDbl(txtNumero2.getText());//converte o tipo de texto para double
    	txtNumero1.setText(String.valueOf(numero1));
    	txtNumero2.setText(String.valueOf(numero2));
    	
    	double resultado=numero1-numero2;
    	
    	String parOuImpar;
    	if(resultado % 2 == 0){
    		parOuImpar=" é Par.";
    	}else {
    		parOuImpar=" é Impar.";
    	}
    	lblResultado.setText("Resultado:"+String.valueOf(resultado)+parOuImpar);
    	

    }
    
    public void Multiplicar() {
    	double numero1 = Double.valueOf(txtNumero1.getText()); // utiliza o getText para retornar a informação digitada 
    	double numero2 = Double.valueOf(txtNumero2.getText());//converte o tipo de texto para double
    	double resultado=numero1*numero2;
    	
    	String parOuImpar;
    	if(resultado % 2 == 0){
    		parOuImpar=" é Par.";
    	}else {
    		parOuImpar=" é Impar.";
    	}
    	lblResultado.setText("Resultado:"+String.valueOf(resultado)+parOuImpar);
    }
    
    public void Dividir() {
    	double numero1 = Double.valueOf(txtNumero1.getText()); // utiliza o getText para retornar a informação digitada 
    	double numero2 = Double.valueOf(txtNumero2.getText());//converte o tipo de texto para double
    	double resultado=numero1/numero2;
    	
    	String parOuImpar;
    	if(resultado % 2 == 0){
    		parOuImpar=" é Par.";
    	}else {
    		parOuImpar=" é Impar.";
    	}
    	lblResultado.setText("Resultado:"+String.valueOf(resultado)+parOuImpar);
    }
//metodo de converter string para double
    private static double StrToDbl(String numero) {
	try {
		return Double.valueOf(numero);
	} catch(Exception e) {
		return 0;
	}
	
}
}