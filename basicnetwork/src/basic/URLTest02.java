package basic;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest02 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		try {
			URL url;
			url = new URL("http://cafefiles.naver.net/20120209_226/pododumok_13287752996753FNE4_jpg/%C0%E5%B5%BF%B0%C7_5_pododumok.jpg\r\n");
		 
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			fos = new FileOutputStream("src/image/jangImg.jpg");
			while(true) {
				int data_byte = bis.read();
				if(data_byte==-1) { //스트링의 끝을 만나면 while을 빠져나가기
					break;
				}
				fos.write(data_byte);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
