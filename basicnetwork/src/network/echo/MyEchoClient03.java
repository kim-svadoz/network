package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ClientInfoStatus;

//바이너리데이터를 읽고 쓰지 않는 경우 - 문자열
public class MyEchoClient03 {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		BufferedReader keyin = null; //키보드로 들어오는 KeyIn을위한 BR
		try {
			socket = new Socket("70.12.115.65", 12345);
			System.out.println("서버접속완료....."+socket);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//생성할 때  autoflush옵션을 설정
			out = new PrintWriter(socket.getOutputStream(), true);
				
			//키보드로 입력한 데이터를 읽기 위한 스트림생성
			keyin = new BufferedReader(new InputStreamReader(System.in));
			
			//*********통신시작*********
			String sendMsg = ""; // 서버로 보낼 메시지
			String resMsg = "";
			resMsg = in.readLine();
			System.out.println("접속 후 서버로부터 받은 메시지:"+resMsg);
			while((sendMsg=keyin.readLine())!=null) {
				//2. 클라이언트 -> 서버(키보드로 입력하는 데이터를 서버로 전송)
				out.println(keyin.readLine());
				//3. 클라이언트 <- 서버
				resMsg = in.readLine();
				System.out.println("서버가 보내는 메시지>>"+resMsg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
