<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="login">
	<form name="loginForm" method="POST" action="<c:url value='j_spring_security_check' />">
	<fieldset>
		<legend>Login</legend>
		
		<div>
			<span><label for="j_username">Username</label></span>
			<span><input name="j_username" class="styledInput" type="text" placeholder="enter your username"/></span>
		</div>
		
		<div>
			<span><label for="j_password">Password</label></span>
			<span><input name="j_password" class="styledInput" type="password" placeholder="enter your password"/></span>
		</div>
		
		<div>
			<span class="forgotPassword"><a href="#forgotPassword">forgot password?</a></span>
			<span class="signUp"><a href="#signUp">Sign up</a></span>
		</div>
		
		<div class="clear">
			<span><input id="btn_login" class="button grey" type="submit" value="login" /></span>
		</div>
	</fieldset>
	</form>
</div>