
import java.net.URLEncoder;
import java.util.Arrays;

import org.junit.Test;

public class URLEncoding {

	@Test
	public void Encoding() throws Exception{
		String s = "��Һ�";
		String s2 = URLEncoder.encode(s, "UTF-8");
		System.out.println(s2);
		// %E5%A4%A7 %E5%AE%B6 %E5%A5%BD  ��%�����ַ��� UTF-8 ��3���ֽڴ���һ�������ַ�	
		
		byte[] bytes = s.getBytes("UTF-8");
		System.out.println(Arrays.toString(bytes));
		// [-27, -92, -89, -27, -82, -74, -27, -91, -67]
		// Ϊʲô�Ǹ���
		/*
		byte��һ���ֽڱ���ģ���8��λ����8��0��1��
		8λ�ĵ�һ��λ�Ƿ���λ�� 
		Ҳ����˵0000 0001�����������1 
		1000 0000����ľ���-1 
		�����������λ0111 1111��Ҳ��������127 
		�������Ϊ1111 1111��Ҳ��������-128
		-27 ���תΪE5, ��ͨ����λ�� -27+256 =229  --> ʮ������Ϊ E5
		*/
		
		String s3 = URLEncoder.encode(s, "GBK");
		System.out.println(s3);
		//%B4%F3 %BC%D2 %BA%C3   GBK ��2���ֽڴ���һ�������ַ�
		
		byte[] bytes2 = s.getBytes("GBK");
		System.out.println(Arrays.toString(bytes2));
		//[-76, -13, -68, -46, -70, -61]
		
		
		
	}
	

}
