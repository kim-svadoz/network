package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.text.SimpleDateFormat;

//바이너리데이터를 읽고 쓰지 않는 경우 - 문자열
public class MyEchoServer03 {
	public static void main(String[] args) {
		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;
		Date time = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader keyin = null; //키보드로 들어오는 KeyIn을위한 BR
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버접속완료.....");
			
			while(true) {
				
				client = server.accept();
				InetAddress clientInfo = client.getInetAddress();
				System.out.println("접속한 클라이언트:"+clientInfo.getHostAddress());
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(), true);
				
				//키보드로 입력한 데이터를 읽기 위한 스트림생성
				keyin = new BufferedReader(new InputStreamReader(System.in));
				
				//1. 서버 -> 클라이언트
				out.println("안녕하세요 클라이언트님?");
				
				//**************통신시작****************
				String resMsg = "";
				String sendMsg = "";
				while(true) {
					//2. 서버 <- 클라이언트
					resMsg = in.readLine();
					System.out.println("클라이언트>>"+resMsg);
					if(resMsg==null) break;
					
					//3. 서버 -> 클라이언트 (키보드로 입력한 데이터)
					sendMsg = keyin.readLine();
					out.println(sendMsg);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
