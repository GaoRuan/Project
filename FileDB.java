package net.iyouyu.schedule;

import java.io.File;
import java.io.IOException;

public class FileDB {
	public static File path = new File("/sdcard/youyu/data/");// ����Ŀ¼
	public static File f = new File("/sdcard/youyu/data/schedule.db");// �����ļ�
	public static boolean fileflag = false;

	public static void create() {
		//System.out.println(" File create()"+android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED));
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
	
			if (!path.exists()) { // �������ݿ�mysql���²�����
				path.mkdirs();
				System.out.println("����Ŀ¼");
			}
			if (!f.exists()) { // �ļ����ڷ���false
				try {
					f.createNewFile();// �����ļ�
					fileflag = true;
					System.out.println("�����ļ�");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("�ļ������쳣");
				}
			}
		}
	}
}
