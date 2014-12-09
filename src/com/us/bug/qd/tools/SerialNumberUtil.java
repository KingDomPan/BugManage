package com.us.bug.qd.tools;

import java.util.Random;

/**
 * 
 * * ���к������� * @author Johney *
 * 
 */
public class SerialNumberUtil
{
	/** �Զ������(0,1û�м���,������o,l����) */
	private static final char[] r = new char[] { 'Q', 'w', 'E', '8', 'a', 'S',
			'2', 'd', 'Z', 'x', '9', 'c', '7', 'p', 'O', '5', 'i', 'K', '3',
			'm', 'j', 'U', 'f', 'r', '4', 'V', 'y', 'L', 't', 'N', '6', 'b',
			'g', 'H' };
	/** �Զ���ȫ��(�������Զ���������ظ�) */
	private static final char[] b = new char[] { 'q', 'W', 'e', 'A', 's', 'D',
			'z', 'X', 'C', 'P', 'o', 'I', 'k', 'M', 'J', 'u', 'F', 'R', 'v',
			'Y', 'T', 'n', 'B', 'G', 'h' };
	/** ���Ƴ��� */
	private static final int l = 35;
	/** ������С���� */
	private static final int s = 6;

	/** * ����ID������λ����� * @param num ID * @return ����� */
	public static String toSerialNumber(long num)
	{
		char[] buf = new char[32];
		int charPos = 32;
		while ((num / l) > 0)
		{
			buf[--charPos] = r[(int) (num % l)];
			num /= l;
		}
		buf[--charPos] = r[(int) (num % l)];
		String str = new String(buf, charPos, (32 - charPos));
		// �������ȵ��Զ������ȫ
		if (str.length() < s)
		{
			StringBuffer sb = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < s - str.length(); i++)
			{
				sb.append(b[rnd.nextInt(24)]);
			}
			str += sb.toString();
		}
		return str;
	}
}
