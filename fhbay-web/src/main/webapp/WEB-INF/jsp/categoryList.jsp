<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="categories">
	<fieldset>
		<legend>Categories</legend>
		<ul id="categoryList" class="firstLevel">
			<c:forEach var="category" items="${categories}">
			<li>
				<c:choose>
					<c:when test="${not empty category.subCategories}">
						<img class="visibilityToggle" src="<c:url value='/static/img/arrowRight.png' />" onclick="toggleCategoryVisibility(this)" />
						<a href="<c:url value='/category/${category.id}/' />${category.urlName}">${category.name}</a>
						
						<ul class="secondLevel">
							<c:forEach var="subCategory" items="${category.subCategories}">
							<li>
								<a href="<c:url value='/category/${subCategory.id}/' />${category.urlName}/${subCategory.urlName}">${subCategory.name}</a>
							</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<img class="visibilityToggle" src="<c:url value='/static/img/dash.png' />" />
						<a href="<c:url value='/category/${category.id}/' />${category.urlName}">${category.name}</a>
					</c:otherwise>
				</c:choose>
			</li>
			</c:forEach>
		</ul>
	</fieldset>
</div>