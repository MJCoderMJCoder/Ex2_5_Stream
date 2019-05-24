package com.elephant.stream;

/**
 * 按行读入一个文本文件，取出介于分隔符之间的连续的字符串作为单词，将每个单词作为一行输出到一个文件中
 * 考虑文本文件的 编码，输入和输出文件名通过命令行输入
 * 将文件作为字符流输入，程序计算结果作为字符流输出到文本文件中
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TextToWord {
	
	//输入和输出文件名是由main方法的参数带入的，这样就可以在程序运行时指定输入和输出文件名
	public static void main(String[] args) {	//args=arguments自变量
		
		/*检查是否输入两个文件名：输入文件名和输出文件名*/
		if(args.length !=2) {
			System.out.println("请输入两个文件名");
		} else {
			
			/*step1:获得输入输出文件名*/
			String infile = args[0];
			String outfile = args[1];
			try {
				
				/*step2:打开输入文件*/
				/*用InputStreamReader类打开一个文件时，可以代入文件字符编码参数（GBK、UTF-8等）*/
				//InputStreamReader是字节流通向字符流的桥梁，它使用指定（或默认）的字符集读取字节并将其解码为字符
				/*FileInputStream是字节流，BufferedReader是字符流*/
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(infile), "GBK"));		//从字节文件中读取字符
				String lineBuffer;
				
				/*step3:分解单词；分解后的单词，临时存放在对象words中*/
				StringBuffer words = new StringBuffer();	//words是一个字符串的变量（收集单词）
				
				/*while语句中对文本文件逐行读入进行处理,readLine()遇换行、回车后直接跟着换行认为某行已终止*/
				while ( null != (lineBuffer = br.readLine())) {		//若逐行读入已到达流末尾，则返回null
					
					//replaceAll将每一行的所有制表符\t替换为空格符，再用split分割单词，这时将空格作为单词间的分隔符
					String[] word = lineBuffer.replaceAll("\t", " ").split(" ");
					for (int i = 0; i < word.length; i++)
						
						//通过split可以得到两个分隔符之间的字符串，包括长度为0的空串。这里用if语句判定，去除空串
						if(word[i].trim().length() > 0)		//trim去掉起始和结尾的空格
							words.append(word[i] + "\r\n");		//每个单词后加入回车符和换行符，append：附加、添加、贴上、签名
				}
				br.close();
				
				/*step4:分解结果写入输出文件*/
				/*用OutputStreamWriter类可以代入文件字符编码参数（GBK、UTF-8等）*/
				//OutputStreamWriter是字符流通向字节流的桥梁，用指定（或默认）的字符集将要写入流中的字符编码成字节
				/*FileOutputStream是字节流，BufferedWriter是字符流*/
				FileOutputStream fos = new FileOutputStream(outfile);		//打开字节文件写
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "GBK"));		//字节流转换为字符流
				bw.write(words.toString());		//将程序中的字符写入到字节文件中
				bw.close();		//最后要关闭文件，否则文件无法保存在外部存储（磁盘）中
			} catch (Exception e) {
				System.out.println("文件处理发生错误");
				System.out.println("输入文件名：");
				System.out.println("输出文件名：");
				System.out.println("错误信息：" + e.getMessage());
			}
		}

	}

}
