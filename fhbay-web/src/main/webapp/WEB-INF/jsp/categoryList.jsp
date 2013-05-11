<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="categories">
	<fieldset>
		<legend>Categories</legend>
		<ul id="categoryList" class="firstLevel">
			<c:forEach var="category" items="${categories}">
			<li>
				<c:choose>
					<c:when test="${not empty category.getSubCategories()}">
						<img class="visibilityToggle" src="<c:url value='/static/img/arrowRight.png' />" onclick="toggleCategoryVisibility(this)" />
						<a href="<c:url value='/category/${category.getId()}/' />${category.getName().replace(' ' , '-')}">${category.getName()}</a>
						
						<ul class="secondLevel">
							<c:forEach var="subCategory" items="${category.getSubCategories()}">
							<li>
								<a href="<c:url value='/category/${subCategory.getId()}/' />${category.getName().replace(' ' , '-')}/${subCategory.getName().replace(' ' , '-')}">${subCategory.getName()}</a>
							</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<img class="visibilityToggle" src="<c:url value='/static/img/dash.png' />" />
						${category.getName()}
					</c:otherwise>
				</c:choose>
			</li>
			</c:forEach>
		</ul>
	</fieldset>
</div>