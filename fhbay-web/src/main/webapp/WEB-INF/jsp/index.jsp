<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>FHBay</title>
	
	<script type="text/javascript" src="./static/js/lib/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="./static/js/lib/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="./static/js/lib/jquery-ui-timepicker-addon.js"></script>
	<link rel="stylesheet" type="text/css" href="./static/css/style.css">
	<link rel="stylesheet" type="text/css" href="./static/css/jquery-ui.css">
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#categories .secondLevel").hide();
		});
	
		function toggleCategoryVisibility(toggle) {
			var subcategories = $(toggle).siblings("ul");
			if (subcategories.is(":visible")) {
				subcategories.hide();
				toggle.src = "./static/img/arrowRight.png";
			} else {
				subcategories.show();
				toggle.src = "./static/img/arrowDown.png";
			}
		}
		
		// activate styled select form fields
		$(function() {
			var update = function() {
				$(this).parent().attr('title', $(this).val());
			};
			$(document).on('click change update', '.dselect>select', update);
			$('.dselect>select').trigger('update');
		});
	</script>
</head>

<body>

<div id="header">
	<a href="<c:url value='/' />">
		<img id="logo" src="./static/img/fhbay.png" alt="FHBay" />
	</a>
</div>

<div id="leftPane">
	<%@ include file="/WEB-INF/jsp/search.jsp" %>
	
	<sec:authorize ifNotGranted="ROLE_USER">
		<%@ include file="/WEB-INF/jsp/loginForm.jsp" %>
	</sec:authorize>
	
	<sec:authorize ifAnyGranted="ROLE_USER">
		<%@ include file="/WEB-INF/jsp/userControls.jsp" %>
	</sec:authorize>
	
	<%@ include file="/WEB-INF/jsp/categoryList.jsp" %>
</div>

<div id="content">
	<c:if test="${showArticleList}">
		<%@ include file="/WEB-INF/jsp/articleList.jsp" %>
	</c:if>

	<c:if test="${showArticleDetails}">
		<%@ include file="/WEB-INF/jsp/articleDetails.jsp" %>
	</c:if>
	
	<c:if test="${showBids}">
		<%@ include file="/WEB-INF/jsp/bidHistory.jsp" %>
	</c:if>
	
	<c:if test="${showOfferArticleForm}">
		<%@ include file="/WEB-INF/jsp/offerArticleForm.jsp" %>
	</c:if>
</div>

<div class="clear"></div>
<div id="footer">
	<span id="copyright">Copyright © 2013 FHBay. All Rights Reserved.</span>
</div>
</body>
</html>