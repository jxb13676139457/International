package com.international.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

public class VerifyCode{
	private int width = 85;
	private int heigth = 35;
	private Random r = new Random();
	
	private String[] fontNames = {"宋体","华文楷体","黑体","微软雅黑","楷体_GB2312"};  //可选字体
	private String codes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  //可选字符
	private Color bgColor = new Color(255,255,255);    //背景颜色
	private String text;   //验证码上的文本
	
	//生成随机颜色
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red,green,blue);
	}
	
	//给图片画几条干扰线
	private void drawLine(BufferedImage image) {
		int num = 4;
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		for(int i=0;i<num;i++) {
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(heigth);
			int x2 = r.nextInt(width);
			int y2 = r.nextInt(heigth);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(Color.BLUE);
			g2.drawLine(x1,y1,x2,y2);
		}
	}
	
	//生成随机验证码
	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}
	
	//生成随机字体
	private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];  //生成的随机字体名称
		int style = r.nextInt(4);   //生成的随机样式（0无样式，1粗体，2斜体，3粗体加斜体）
		int size = r.nextInt(5)+24;   //生成的字体大小（24~28）
		return new Font(fontName,style,size);
	}
	
	//创建BufferedImage
	private BufferedImage createImg() {
		BufferedImage image = new BufferedImage(width,heigth,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		//画一个背景方框图片
		g2.setColor(this.bgColor);
		g2.fillRect(0,0,width,heigth);
		return image;
	}
	
	//获得验证码图片
	public BufferedImage getImage() {
		BufferedImage image = createImg();
		Graphics2D g2 = (Graphics2D)image.getGraphics();   //获得绘制环境
		StringBuffer sb = new StringBuffer();   //结果验证码序列
		for(int i=0;i<4;i++) {
			String s = randomChar()+"";
			sb.append(s);
			float x = i*1.0F*width/4;  //设置当前字符的x轴坐标
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s,x,heigth-5);  //画图
		}
		this.text = sb.toString();  //生成的随机字符串
		drawLine(image);   //添加干扰线
		return image;
	}
	
	//返回验证码上面的文本
	public String getContent() {
		return this.text;
	}
	
	//保存图片到指定的输出流
	public void output(FileImageOutputStream out) throws IOException{
		ImageIO.write(getImage(),"jpg",out);
	}
}
