
import java.net.URLEncoder;
import java.util.Arrays;

import org.junit.Test;

public class URLEncoding {

	@Test
	public void Encoding() throws Exception{
		String s = "大家好";
		String s2 = URLEncoder.encode(s, "UTF-8");
		System.out.println(s2);
		// %E5%A4%A7 %E5%AE%B6 %E5%A5%BD  用%隔开字符， UTF-8 用3个字节代表一个汉字字符	
		
		byte[] bytes = s.getBytes("UTF-8");
		System.out.println(Arrays.toString(bytes));
		// [-27, -92, -89, -27, -82, -74, -27, -91, -67]
		// 为什么是负数
		/*
		byte是一个字节保存的，有8个位，即8个0、1。
		8位的第一个位是符号位， 
		也就是说0000 0001代表的是数字1 
		1000 0000代表的就是-1 
		所以正数最大位0111 1111，也就是数字127 
		负数最大为1111 1111，也就是数字-128
		-27 如何转为E5, 是通过补位： -27+256 =229  --> 十六进制为 E5
		*/
		
		String s3 = URLEncoder.encode(s, "GBK");
		System.out.println(s3);
		//%B4%F3 %BC%D2 %BA%C3   GBK 用2个字节代表一个汉字字符
		
		byte[] bytes2 = s.getBytes("GBK");
		System.out.println(Arrays.toString(bytes2));
		//[-76, -13, -68, -46, -70, -61]
		
		
		
	}
	

}
