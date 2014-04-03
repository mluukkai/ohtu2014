<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ohtu App | new user</title>
    </head>
    <body>
        <h1>Create username and give password</h1>

        <sf:form method="POST" modelAttribute="user" action="/user" >
            <fieldset>
                username: <sf:input path="username" id="username" size="15"/>
                <sf:errors path="username" cssClass="error"/>   
                <br/>
                password: <sf:password path="password" id="password" size="15"/>
                <sf:errors path="password" cssClass="error"/>   
                <br/>                
                confirm password: <sf:password path="passwordConfirmation" id="passwordConfirmation" size="15"/>
                <sf:errors path="passwordConfirmation" cssClass="error"/>   
                <br/>                
                <input name="add" type="submit" value="add" />
            </fieldset>
        </sf:form>  

        <p><a href="/">back to home</a></p>
    </body>
</html>
