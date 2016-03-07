package ola_socket.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		int porta = 12345;
		System.out.println("Tentar Iniciar Servidor na Porta "+porta);
		try {
			ServerSocket servidor = new ServerSocket(porta, 100);
			do{
				final Socket conexao = servidor.accept();	
				System.out.println("Nova Conexao!");
				//Para que possamos ter várias conexões ao mesmo tempo 
				//Independentemente da Thread o laco continua rodando. 
				processandoConexao(conexao);
			}while(true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public static void processandoConexao(final Socket conexao){
		Thread processando = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//Aqui eu pego a conexao e faco alguma troca interessante. 
				//Esperamos que o cliente envie uma string. Vamos devolver outra e fechar a conexao. 
				try {
					PrintStream saida = new PrintStream(conexao.getOutputStream());
					BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
					String mensagemDoCliente = entrada.readLine();
					System.out.println("Primeira Mensagem do Cliente: "+mensagemDoCliente);
					saida.println("Eu sou o servidor e recebi sua mensagem, agora vou fechar a conexao. Adeus!");
					conexao.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
                

			}
		});
		processando.start();
		
	}
}
