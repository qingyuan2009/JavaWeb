package servlet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoodUtil {

	private Set<String> goods = new HashSet<>();

	public GoodUtil(String goods) {

		if (goods.trim().isEmpty()) {
			return;
		}
		String[] str = goods.split(",");
		// ��arrayתΪlist
		List<String> goodlist = Arrays.asList(str);
		this.goods.addAll(goodlist);
		
		/*  �ͼ����� 
		for (int i = 0; i < str.length; i++) {
			this.goods.add(str[i]);
		}
		*/
	}

	public void addGood(String goodName) {
		/*  ����Ĵ���
		for (String good : goods) {
			if (good.equals(goodName)) {
				return;
			} else {
				goods.add(goodName);
				return;
			}
		}*/  
		goods.add(goodName);  //������Set,��Ȼ�������ظ�
	}

	public String getAllGood() {
		//ȥ��ͷβ������ [  ]
		String str = goods.toString();
		if (str.equals("")){			
			return "";
		}else {
			return str.substring(1, str.length() -1 );
		}
		/*  �ͼ�����
		String allGoods = "";
		for (String good : goods) {
			allGoods = allGoods + "," + good;
		}
		if (allGoods.equals("")) {
			return "";
		} else {
			return allGoods.substring(1);
		}
		*/
	}

}
