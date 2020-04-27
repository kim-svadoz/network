package single.console.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientSenderThread extends Thread{
	Socket socket;
	
	public ClientSenderThread(Socket socket) {
		super();
		this.socket = socket;
	}

	//키보드로 입력한 내용을 서버로 보내는 작업
	public void run() {
		PrintWriter out= null;
		BufferedReader keyin = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyin = new BufferedReader(new InputStreamReader(System.in));
			String sendMsg ="";//서버로 보낼 메시지
			while(true) {
				sendMsg = keyin.readLine();
				if(sendMsg ==null) break;
				out.println(sendMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//서버가 전송하는 데이터를 클라이언트에서 읽는 작업을 수행하는 쓰레드
class ClientReceiveThread extends Thread{
	Socket socket;
	
	public ClientReceiveThread(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String resMsg = "";
			while(true) {
				resMsg = in.readLine();
				if(resMsg==null) break;
				System.out.println("서버>>"+resMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//서버에서 클라이언트로 데이터를 전송하는 작업을 수행하는 쓰레드
class ServerSenderThread extends Thread{
	Socket socket;
	
	public ServerSenderThread(Socket socket) {
		super();
		this.socket = socket;
	}

	//키보드로 입력한 내용을 서버로 보내는 작업
	public void run() {
		PrintWriter out= null;
		BufferedReader keyin = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyin = new BufferedReader(new InputStreamReader(System.in));
			String sendMsg ="";//클라이언트로 보낼 메시지
			while(true) {
				sendMsg = keyin.readLine();
				if(sendMsg ==null) break;
				out.println(sendMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//서버에서 클라이언트가 전송하는 데이터를 읽는 작업을 수행하는 쓰레드
class ServerReceiveThread extends Thread{
	Socket socket;
	
	public ServerReceiveThread(Socket socket) {
		super();
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String resMsg = "";
			while(true) {
				resMsg = in.readLine();
				if(resMsg==null) break;
				System.out.println("클라이언트>>"+resMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

