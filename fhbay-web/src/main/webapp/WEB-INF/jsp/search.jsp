<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="search">
	<form name="searchForm" method="GET" action="search">
	<fieldset>
		<legend>Search</legend>
	
		<div>
			<input name="q" class="styledInput" type="text" placeholder="Search for an article"/>
		</div>
		
		<div class="dselect">
			<select name="category">
				<option>All categories</option>
							
				<c:forEach var="category" items="${categories}">
					<option>${category.name}</option>
					<c:if test="${not empty category.subCategories}">
						<c:forEach var="subCategory" items="${category.subCategories}">
							<option class="sub">${subCategory.name}</option>
						</c:forEach>
					</c:if>
				</c:forEach>
			</select>
		</div>
		
		<div>
			<span><input id="btn_search" class="button grey" type="submit" value="search" /></span>
		</div>
	</fieldset>
	</form>
</div>