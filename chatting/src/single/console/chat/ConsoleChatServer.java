package single.console.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConsoleChatServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(12345);
			
			while(true) { // 1:1 통신이므로 While 하나만 있어도 된다.
				Socket socket = server.accept();
				InetAddress clientInfo = socket.getInetAddress();
				System.out.println("접속한 클라이언트:"+clientInfo.getHostAddress());
				ServerSenderThread senderThread = new ServerSenderThread(socket);
				senderThread.start();
				new ServerReceiveThread(socket).start();
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
