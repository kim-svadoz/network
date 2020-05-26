package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class ReceiverThread extends Thread {
	Socket client;
	BufferedReader br;					//Ŭ���̾�Ʈ�� �޽����� �д� ��Ʈ��
	PrintWriter pw;						//Ŭ���̾�Ʈ���� �޽����� �����ϴ� ��Ʈ��
	
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
				System.out.println("Ŭ���̾�Ʈ�� ���� �޽���: " + msg);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
