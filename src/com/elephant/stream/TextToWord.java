package com.elephant.stream;

/**
 * ���ж���һ���ı��ļ���ȡ�����ڷָ���֮����������ַ�����Ϊ���ʣ���ÿ��������Ϊһ�������һ���ļ���
 * �����ı��ļ��� ���룬���������ļ���ͨ������������
 * ���ļ���Ϊ�ַ������룬�����������Ϊ�ַ���������ı��ļ���
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TextToWord {
	
	//���������ļ�������main�����Ĳ�������ģ������Ϳ����ڳ�������ʱָ�����������ļ���
	public static void main(String[] args) {	//args=arguments�Ա���
		
		/*����Ƿ����������ļ����������ļ���������ļ���*/
		if(args.length !=2) {
			System.out.println("�����������ļ���");
		} else {
			
			/*step1:�����������ļ���*/
			String infile = args[0];
			String outfile = args[1];
			try {
				
				/*step2:�������ļ�*/
				/*��InputStreamReader���һ���ļ�ʱ�����Դ����ļ��ַ����������GBK��UTF-8�ȣ�*/
				//InputStreamReader���ֽ���ͨ���ַ�������������ʹ��ָ������Ĭ�ϣ����ַ�����ȡ�ֽڲ��������Ϊ�ַ�
				/*FileInputStream���ֽ�����BufferedReader���ַ���*/
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(infile), "GBK"));		//���ֽ��ļ��ж�ȡ�ַ�
				String lineBuffer;
				
				/*step3:�ֽⵥ�ʣ��ֽ��ĵ��ʣ���ʱ����ڶ���words��*/
				StringBuffer words = new StringBuffer();	//words��һ���ַ����ı������ռ����ʣ�
				
				/*while����ж��ı��ļ����ж�����д���,readLine()�����С��س���ֱ�Ӹ��Ż�����Ϊĳ������ֹ*/
				while ( null != (lineBuffer = br.readLine())) {		//�����ж����ѵ�����ĩβ���򷵻�null
					
					//replaceAll��ÿһ�е������Ʊ��\t�滻Ϊ�ո��������split�ָ�ʣ���ʱ���ո���Ϊ���ʼ�ķָ���
					String[] word = lineBuffer.replaceAll("\t", " ").split(" ");
					for (int i = 0; i < word.length; i++)
						
						//ͨ��split���Եõ������ָ���֮����ַ�������������Ϊ0�Ŀմ���������if����ж���ȥ���մ�
						if(word[i].trim().length() > 0)		//trimȥ����ʼ�ͽ�β�Ŀո�
							words.append(word[i] + "\r\n");		//ÿ�����ʺ����س����ͻ��з���append�����ӡ���ӡ����ϡ�ǩ��
				}
				br.close();
				
				/*step4:�ֽ���д������ļ�*/
				/*��OutputStreamWriter����Դ����ļ��ַ����������GBK��UTF-8�ȣ�*/
				//OutputStreamWriter���ַ���ͨ���ֽ�������������ָ������Ĭ�ϣ����ַ�����Ҫд�����е��ַ�������ֽ�
				/*FileOutputStream���ֽ�����BufferedWriter���ַ���*/
				FileOutputStream fos = new FileOutputStream(outfile);		//���ֽ��ļ�д
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "GBK"));		//�ֽ���ת��Ϊ�ַ���
				bw.write(words.toString());		//�������е��ַ�д�뵽�ֽ��ļ���
				bw.close();		//���Ҫ�ر��ļ��������ļ��޷��������ⲿ�洢�����̣���
			} catch (Exception e) {
				System.out.println("�ļ�����������");
				System.out.println("�����ļ�����");
				System.out.println("����ļ�����");
				System.out.println("������Ϣ��" + e.getMessage());
			}
		}

	}

}
