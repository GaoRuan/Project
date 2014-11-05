package net.iyouyu.schedule;

import java.io.File;
import java.io.IOException;

public class FileDB {
	public static File path = new File("/sdcard/youyu/data/");// 创建目录
	public static File f = new File("/sdcard/youyu/data/schedule.db");// 创建文件
	public static boolean fileflag = false;

	public static void create() {
		//System.out.println(" File create()"+android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED));
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
	
			if (!path.exists()) { // 创建数据库mysql如下操作：
				path.mkdirs();
				System.out.println("创建目录");
			}
			if (!f.exists()) { // 文件存在返回false
				try {
					f.createNewFile();// 创建文件
					fileflag = true;
					System.out.println("创建文件");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("文件创建异常");
				}
			}
		}
	}
}
