<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ServletResponse Wrapper Demo</title>
    </head>
    <body>
        <b> Please enter your name :  </b>
        <br />
        <br />
        <br />
        <form action="MyServ" method="get">
            Name : <input type="text" name="username" /> City : <input type="text" name="cityname" />
            <input type="submit" name="submit" />
        </form>
    </body>
</html>