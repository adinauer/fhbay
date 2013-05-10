<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${not empty searchString}"> 
	<div class="breadcrumbs">Searching for "<span class="searchString">Canon EOS 1D X</span>" in All Categories</div>
	<div class="breadcrumbs">
		Searching for "
			<span class="searchString">${searchString}</span>
		" in category: Photography Equipment > Camera
	</div>
	</c:when>
	<c:otherwise>
		<div class="breadcrumbs">Listing articles in category: Video > Cables</div>
	</c:otherwise>
</c:choose>

<table id="articles">
	<tbody>
		<c:choose>
			<c:when test="${empty articles}">
				<tr>
					<td colspan="3" align="center" valign="middle">
						<div class="noArticlesFound">No articles found.</div>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="article" items="${articles}">
					<tr>
						<td align="left" valign="top">
							<a class="articleName" href="<c:url value='/article/${article.getId()}/' />${article.getName()}">${article.getName()}</a>
						</td>
						<td align="right" valign="top">
							<span class="remainingTime">
								<c:choose>
									<c:when test="${article.getEndDate() < currentDateAndTime}">
										<span class="ended">ended</span>
									</c:when>
									<c:otherwise>
										ends ${dateAndTimeFormat.format(article.getEndDate())}
									</c:otherwise>
								</c:choose>
							</span>
						</td>
						<td align="right" valign="top">
							<span class="price">€ ${article.getInitialPrice()}</span><br />
							<span class="bids">10 bids</span>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>