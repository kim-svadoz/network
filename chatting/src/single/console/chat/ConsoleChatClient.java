package single.console.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ClientInfoStatus;

//바이너리데이터를 읽고 쓰지 않는 경우 - 문자열
public class ConsoleChatClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("70.12.115.51", 12345);
			new ClientReceiveThread(socket).start();
			new ClientSenderThread(socket).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
