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
		// 把array转为list
		List<String> goodlist = Arrays.asList(str);
		this.goods.addAll(goodlist);
		
		/*  低级代码 
		for (int i = 0; i < str.length; i++) {
			this.goods.add(str[i]);
		}
		*/
	}

	public void addGood(String goodName) {
		/*  多余的代码
		for (String good : goods) {
			if (good.equals(goodName)) {
				return;
			} else {
				goods.add(goodName);
				return;
			}
		}*/  
		goods.add(goodName);  //由于是Set,天然不允许重复
	}

	public String getAllGood() {
		//去掉头尾的括号 [  ]
		String str = goods.toString();
		if (str.equals("")){			
			return "";
		}else {
			return str.substring(1, str.length() -1 );
		}
		/*  低级代码
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
