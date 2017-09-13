package guiqian.xingzuo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonStringHelper {

    private CommonStringHelper() {
        throw new AssertionError();
    }
    
	public static String formatNumberPlus(int num, int base) {
		if (num > base) {
			return num + "+";
		} else {
			return num + "";
		}
	}
	
	public static final String format10000plus(int num){
		
		if (num > 10000 && num < 1000000) {
			return num/10000 + "万+";
		}
		
		if(num >= 1000000){
			return num/1000000 + "百万+";
		}
				
		return num + "";
	}

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.length() == 0);
    }

	public static String getCheckedStr(String str) {
		return isNullOrEmpty(str) ? "" : str;
	}

	public static String getFormatCurrency(String currency) {
		if (currency == null || currency.length() == 0) {
			return "";
		}

		if ("RMB".equalsIgnoreCase(currency) || "CNY".equalsIgnoreCase(currency)) {
			return "￥";
		}else {
			return currency;
		}
	}
	
	public static String connectWithPre(String pre, String... param) {

		StringBuilder sbAllParam = new StringBuilder();

		for (int i = 0; i < param.length; i++) {

			if (CommonStringHelper.isNullOrEmpty(param[i])) {
				continue;
			}

			if (sbAllParam.length() == 0) {
				sbAllParam.append(param[i]);
				continue;
			}
			sbAllParam.append(pre).append(param[i]);
		}

		return sbAllParam.toString();
	}
	
	/**
	 * 将string转为int ，异常时返回-1
	 * 
	 * @param s
	 * @return
	 */
	public static int toInt(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			i = -1;
		}
		return i;
	}
	
	/**
	 * 将string转为int ，异常时返回-1
	 * 
	 * @param s
	 * @return
	 */
	public static double toDouble(String s) {
		double i = 0;
		try {
			i = Double.parseDouble(s);
		} catch (Exception e) {
			i = -1;
		}
		return i;
	}
	
	/**
	 * 判断字串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static int isNumString(String str) {
		Pattern p = Pattern.compile("[0-9]*");// 格式"a"
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return 1;
		} else {
			return 0;
		}
	}
}
