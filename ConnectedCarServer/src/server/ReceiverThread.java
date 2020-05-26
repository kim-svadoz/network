package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class ReceiverThread extends Thread {
	Socket client;
	BufferedReader br;					//클라이언트의 메시지를 읽는 스트림
	PrintWriter pw;						//클라이언트에게 메시지를 전달하는 스트림
	
	public ReceiverThread() {
		// TODO Auto-generated constructor stub
	}
	public ReceiverThread(Socket client) {
		this.client = client;
		try {
			br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			pw = new PrintWriter(this.client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String msg = br.readLine();
				System.out.println("클라이언트가 보낸 메시지: " + msg);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
