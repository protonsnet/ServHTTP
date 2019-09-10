import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ServHttp {

	public static void main(String[] args) throws IOException {
		// Cria o servidor na porta 8080
		int porta = 8080;
		Socket socket = null;
		BufferedReader buffer = null;
		StringBuilder response = null;
		ServerSocket serversocket = null;
		DateFormat simpledataformat = null;
		OutputStream outputstream = null;
		
		try {
			serversocket = new ServerSocket(porta);
			
		} catch (IOException e) {
			System.out.println("Erro ao iniciar o servidor");
		}
		//Aguardando conexão
		while (true) {
			try {
				// Cria um socket
				socket = serversocket.accept();
				
				Messagem messagem = new Messagem();
				
				if(socket.isConnected()) {
					//Ler o buffer de entrada do cliente
					buffer = new BufferedReader(
							new InputStreamReader(socket.getInputStream())
							);
					//Exibe uma mensagem com ip do cliente conectado
					messagem.ipConected(socket.getInetAddress().toString());
					//Quebra o conteudo de buffer e coloca no array
					String[] arraybuffer = buffer.readLine().split(" ");
					//caminho do arquivo solicitado
					//String path = arraybuffer[1];
					
					//Response
					response = new StringBuilder();
					response.append(arraybuffer[2].toString()+" 200 OK \r\n");
					//formata a data
					simpledataformat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH);
					response.append("Date: "+simpledataformat.format(new Date())+"\r\n");
					
					//Informações do servidor
					response.append("Server: ServHttp\r\n");
					
					// 
					response.append("Content-Type: text/html; charset=UTF-8\r\n");
					
					//Encerra 
					response.append("Connection: Close\r\n");
					
					//Pagina de resposta
					response.append("<html>"
							+ "<head><title>Pagina do Servidor</title></head>"
							+ "<body><h1>Resposta do Servidor</h1></body>"
							+"</html>");
					//Responde requisição
					outputstream = socket.getOutputStream();
					outputstream.write(response.toString().getBytes());
					
					//encerra conexão
					socket.close();
				}
			} catch (Exception e) {
				continue;
			}

		}
		
		
	}

}
