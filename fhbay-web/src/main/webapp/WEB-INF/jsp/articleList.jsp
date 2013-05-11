<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:choose>
	<c:when test="${not empty searchString}"> 
	<div class="breadcrumbs">
		Searching for "<span class="searchString">${searchString}</span>" in 
		<c:choose>
			<c:when test="${empty selectedCategoryName}">
				all categories.
			</c:when>
			
			<c:otherwise>
				category: ${selectedCategoryName}
			</c:otherwise>
		</c:choose>
	</div>
	</c:when>
	<c:otherwise>
		<div class="breadcrumbs">
			<c:choose>
				<c:when test="${empty selectedCategoryName}">
					Listing all articles.
				</c:when>
				
				<c:otherwise>
					Listing articles in category: ${selectedCategoryName}
				</c:otherwise>
			</c:choose>
		</div>
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
							<a class="articleName" href="<c:url value='/article/${article.id}/' />${article.urlName}">${article.name}</a>
						</td>
						<td align="right" valign="top">
							<span class="remainingTime">
								<c:choose>
									<c:when test="${article.hasAuctionEnded}">
										<span class="ended">ended</span>
									</c:when>
									
									<c:otherwise>
										${article.timeRemainingFormatted} left<br />
										ends ${article.endDateFormatted}
									</c:otherwise>
								</c:choose>
							</span>
						</td>
						<td align="right" valign="top">
							<span class="price">€ ${article.currentPrice}</span><br />
							<span class="bids">${article.numberOfBids} bids</span>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>