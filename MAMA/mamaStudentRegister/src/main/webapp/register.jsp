<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
 <form action="regForm" method="get">  
   Name: <input type="text" name="name1" /> <br>
   Email : <input type="text" name="email1" /> <br>
   Password: <input type="password" name="pass1" /> <br>
   Gender: <input type="radio" name="gender1" value="Male" /> Male
   <input type="radio" name="gender1" value="Female" /> Female <br>
   City: <select name="city1">
      <option>Select city</option>
      <option>Delhi</option>
      <option>Mumbai</option>
      <option>Pune</option>
      <option>Bangalore</option>
   </select> <br>
   <input type="submit" value="Register"/>
 </form>
</body>
</html>
