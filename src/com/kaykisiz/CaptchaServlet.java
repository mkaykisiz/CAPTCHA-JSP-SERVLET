package com.kaykisiz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaServlet extends HttpServlet {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


    response.setContentType("image/png");
    /*
     * Alt satır için tanımlamalar, geçiş yapılıp yapılmadığı ve  kordinatın sıfırlanması
     */
    boolean level2=false;
    int level2Start =0 ;
    /*
     * karakter sayısı max=20
     */
    int iTotalChars = 20;

    /*
     * captcha resim boyutları
     */
    int iHeight = 100;
    int iWidth = 300;

    /*
     * font bilgileri
     */
    Font fntStyle1 = new Font("Arial", Font.BOLD, 13);
    Font fntStyle2 = new Font("Verdana", Font.BOLD, 13);

    /*
     * çekilecek soru için nesne tanımlamaları
     * random çekme
     * captcha sınıfından soru çekme
     */
    Random randChars = new Random();
    Captcha captcha=new Captcha();
    String sImageCode = captcha.getQuery() ;
    /*
     * eğer random karakterler çekilmek istenirse
     * (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars)
     * 
     * Kullanılacak resimin oluşturulması
     * 
     * TYPE_INT_RGB - renkler desteğinin gelirtilmesi ve resim boyutları
     */
    BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

    // Arka plana daireler çiziyor.
    int iCircle = 3;
    //g2dImage.fillRect(0, 0, iWidth, iHeight); //background beyaz
    for (int i = 0; i < iCircle; i++) {
        g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
        int iRadius = (int) (Math.random() * iHeight / 2.0);
        int iX = (int) (Math.random() * iWidth - iRadius);
        int iY = (int) (Math.random() * iHeight - iRadius);
        g2dImage.fillRoundRect(iX, iY, iRadius * 2, iRadius * 2,100,100);
    }
    
    /*
     * karakterlerin renklendirilmesi ve konumlandırılması
     */
    g2dImage.setFont(fntStyle1);
    for (int i = 0; i < iTotalChars; i++) {
        g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
       /*
        * ikinci satır gereksinimlerinin sağlandığının kontrolü
        *  * ile ayırt ediliyor
        */
        if("*".equals(sImageCode.substring(i, i + 1))){
        	level2=true;
        	level2Start=i;
        	g2dImage.setFont(fntStyle2);
        	continue;
        	
        }
        /*
         * level2 ise alt satırda konumlandırarak yazmaya başla
         * else
         * üst satırda konumlandır
         */
        if(level2){
        	if (i % 2 == 0) {
        		g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * (i-level2Start), 74);
        	} else {
        		g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * (i-level2Start), 85);
        	}
        }else{
        	if (i % 2 == 0) {
        		g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
        	} else {
        		g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
        	}
        }
    }

    /*
     * png tipinde resim oluştur
     */
    OutputStream osImage = response.getOutputStream();
    ImageIO.write(biImage, "png", osImage);
    osImage.close();

    /*
     * işlemi gömmek için disponse kullanılır
     */
    g2dImage.dispose();
    /*
     * session'a gidecek captcha bilgileri
     */
    HttpSession session = request.getSession();
    session.setAttribute("dns_security_code", captcha.getAnswer());
    //System.out.println("Captcha Page :"+session.getAttribute("dns_security_code"));

}



protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}