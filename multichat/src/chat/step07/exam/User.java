package chat.step07.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class User extends Thread {

	// ChatServerView에서 넘겨받을 데이터
	Socket client;
	ChatServerView serverView;

	InputStream is;
	InputStreamReader ir;
	BufferedReader br;

	OutputStream os;
	PrintWriter pw;

	String nickname;

	// 7-3. User객체에 기존 사용자의 정보를 액세스할 수 있도록 변수 정의
	Vector<User> userlist;

	public User() {

	}

	// 서버가 접속한 클라이언트의 정보를 User객체로 만들 때 접속한 User의 소켓객체와 서버뷰를 전달
	// 7-4. Vector<User> userlist 추가
	public User(Socket client, ChatServerView serverView, Vector<User> userlist) {
		super();
		this.client = client;
		this.serverView = serverView;
		this.userlist = userlist;

		ioWork();
	}

	// 접속한 클라이언트와 서버가 통신할 수 있도록 스트림객체 생성
	public void ioWork() { // 처음 접속했을 떼 한번만 실행되는 메소드
		try {
			is = client.getInputStream();
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);

			os = client.getOutputStream();
			pw = new PrintWriter(os, true);

			// 클라이언트와 통신할 수 있는 스트림을 생성하고 클라이언트가 전송하는 nickname을 읽어서 서버창에 출력
			nickname = br.readLine();
			System.out.println("nickname" + nickname);
			serverView.taclientlist.append("********" + nickname + "님이 입장********");

			// 7-e-2. 기존for문을 메소드 호출문으로 변경
			broadCast("new/" + nickname);

			// 7-e-3. 새로운 접속자(this)에게 기존 클라이언트의 정보를 알려주기
			int size = userlist.size(); // 기존접속자 인원수
			for (int i = 0; i < size; i++) {
				User user = userlist.get(i);
				sendMsg("old/" + user.nickname);

				// 7-8. 접속자리스트에 현재 새로접속한 클라이언트 정보도 저장
			
			}
			userlist.add(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 7-7. 클라이언트에게 메시지를 보내는 메소드를 정의
	// 7-e-1. 기존 사용자가에게 메시지를 보내느 메소드를 정의
	private void broadCast(String msg) {
		int size = userlist.size();
		for (int i = 0; i < size; i++) {
			User user = userlist.get(i);
			user.sendMsg(msg);
		}

	}

	public void sendMsg(String message) {
		pw.println(message);
	}

	// 쓰레드로 처리할 작업 - 접속한 해당 클라이언트가 전송하는 메시지를 계속 받아서 서버창에 출력
	public void run() {
		while (true) {
			try {
				String msg = br.readLine();
				serverView.taclientlist.append(nickname + "님이 전송한 메시지 : " + msg + "\n");
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}
}
