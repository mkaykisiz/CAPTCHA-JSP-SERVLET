package com.kaykisiz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class Captcha {
/*
 * soru ve cevabın oluşturulup döndürülmesini içeriyor
 */
private String query;
private String answer;
/*
 * eğer bir rsoru istenirse ana yordam gerekli metodu çağırıyor
 */
public Captcha(){
	QueryAnswer();
}

public String getQuery() {
	return query;
}
public void setQuery(String query) {
	this.query = query;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
/*
 * sorunun hazırlandığı metot
 */
public void QueryAnswer(){
	/*
	 * random bir değer belirleniyor ve bu değer ve bir sonraki değer atanıyor.
	 * ilk değer soru, ikincisi ise bir cevaptır.
	 */
	Random randomAtanacakSayi=new Random();
	int atanacakSayi=randomAtanacakSayi.nextInt(10)+1;
	
	switch (atanacakSayi) {
	case 1:query="BSGnin*açılımı?     "; answer="bilgi sistemleri ve güvenliği";break;
	case 2:query="Türkiyenin*Başkenti?"; answer="ankara";break;
	case 3:query="25+42=?             "; answer="67"; break;
	case 4:query="paris*nerede?       "; answer="fransa"; break;
	case 5:query="Turkeyhangi*ülkedir?"; answer="ankara"; break;
	case 6:query="Manchestera*atılangol"; answer="arif"; break;
	case 7:query="Büyük*Önder ...     "; answer="mustafa kemal atatürk";break;
	case 8:query="Basbaknımız?        "; answer="Recep tayyip erdoğan"; break;
	case 9:query="soncumhur*başkanı?  "; answer="abdullah gül"; break;
	case 10:query="anıtkabir*nerede?   "; answer="ankara"; break;
	default:query="birankara*polisiyesi?"; answer="behzat ç."; break;
	
	
}



	
}
}
