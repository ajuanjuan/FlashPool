package cn.flashpool.utils;

import java.util.UUID;

/**
 * 生成随机字符串
 * @author 王鹏飞
 *
 */
public class UUIDUtils {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
