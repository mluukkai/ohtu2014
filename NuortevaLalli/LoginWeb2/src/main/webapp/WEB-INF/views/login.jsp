<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ohtu App | login</title>
    </head>
    <body>
        <h1>Give your credentials to login</h1>

        <div id="error">
        <p><em>${message}</em></p>
        </div>
        
        <sf:form method="POST" modelAttribute="user" action="/login" >
            <fieldset>
                username: <sf:input path="username" id="username" size="15"/>
                <sf:errors path="username" />   
                <br/>
                password: <sf:password path="password" id="password" size="15"/>
                <sf:errors path="password" />    
                <br/>                
                <input name="login" type="submit" value="login" />
            </fieldset>
        </sf:form>  
        <p><a href="/">back to home</a></p>
    </body>
</html>
