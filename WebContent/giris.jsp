<?xml version="1.0" encoding="ISO-8859-9" ?>
<%@page import="com.kaykisiz.User"%>
<%@ page contentType=”text/html;charset=ISO-8859-9″ pageEncoding=”ISO-8859-9″ %>
<%@ page import="com.kaykisiz.Captcha"%>
<%@ page import="com.kaykisiz.User"%>
<%@ page language="java"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="tr"> <!--<![endif]-->

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" />
<title>Captcha</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link rel="stylesheet" href="css/style.css"/>
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<!--  form ile alakalı bilgiiler 
mail, parola, buton,captcha alanı -->
  <form method="post" class="login">
    <p>
      <label for="login">Email :</label>
      <input type="text" name="login" id="login" value="name@example.com">
    </p>

    <p>
      <label for="password">Parola :</label>
      <input type="password" name="password" id="password" value="4815162342">	<br/>
    
    </p>

    <p class="login-submit">
      <button type="submit" class="login-button">Giris</button>
    </p>

    <p >
    <label>Soru :</label>
	<img src="images/captchaImage.png"/> 
	</p>
	
	<p>
	<label>Cevap :</label>
	<input type="text" name="captchaCode" id="CaptchaCode" />
	</p>

    <p class="forgot-password"><a href="index.html">Parolami Unuttum</a></p>
  </form>
<!-- Bilgilendirme amaçlı kutucuk -->
  <section class="about">
    <p class="about-links">
      <a href="http://www.Kaykisiz.com/" target="_blank">Mehmet KAYKISIZ</a>
      <a href="http://www.java.net/" target="_blank">Java</a>
    </p>
    <p class="about-author"/>
      &copy; 2012&ndash;2013 <a href="http://www.kaykisi.com/" target="_blank">kaykisizcom</a> -
      <a href="http://tr.wikipedia.org/wiki/GNU_Genel_Kamu_Lisans%C4%B1" target="_blank">GPL License</a><br/>
      Original PSD by <a href="http://365psd.com/day/2-234/" target="_blank">Rich McNabb</a>
  </section>
  <!-- verilerin kontrolü yapılıyor eğer doğru ise yani eşitlik sağlanıyorsa, session oluşturuluyor(User tipinde) -->
  <%
  String captcha = (String) session.getAttribute("dns_security_code");
  String code = (String) request.getParameter("captchaCode");
  if(code != null){
  code=code.toLowerCase(); 
  }
  
  if (captcha != null && code != null) {

    if (captcha.equals(code)) {
	   
	User user = new User();
	user.setMail((String) request.getParameter("login"));
	session.setAttribute("userInfo", user);
	response.sendRedirect("index.jsp");
    } else {
          out.print("<p class='alert'><h1>Tekrar deneyin!</h1></p>");
    }
  }
%>
</body>
</html>
