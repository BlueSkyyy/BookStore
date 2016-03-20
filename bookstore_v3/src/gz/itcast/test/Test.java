package gz.itcast.test;

import gz.itcast.util.MD5Util;

public class Test {
	public static void main(String[] args) {
		String md5 = MD5Util.md5("admins");
		System.out.println(md5);
	}
}
