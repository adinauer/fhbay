<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="userControls">
	<fieldset>
		<sec:authentication property="principal" var="user"/>
		<legend>${user}</legend>
		
		<span id="logout">[ <a href="<c:url value="j_spring_security_logout" />" >Logout</a> ]</span><br />
		
		<ul id="userLinks">
			<li>
				<span><a href="<c:url value='offerArticle' />">Offer an article</a></span>
			</li>
			<li>
				<span><a>Articles I'm offering</a></span>
			</li>
			<li>
				<span><a>Articles I'm bidding on</a></span>
			</li>
		</ul>
	</fieldset>
</div>