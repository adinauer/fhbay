<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tr class="biddingArea noBottomSpacing">
	<td class="label" rowspan="2">Current bid:</td>
	<td>
		<span class="price">€ ${selectedArticle.currentPrice}</span>
	</td>
	<td>
		<span class="bids">
			[ 
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<a href="<c:url value='/bidHistory?articleId=${selectedArticle.id}' />">${selectedArticle.numberOfBids} bids</a> 
				</sec:authorize>
				<sec:authorize ifNotGranted="ROLE_ADMIN">
					${selectedArticle.numberOfBids} bids 
				</sec:authorize>
			]
		</span>
	</td>
</tr>
<c:choose>
	<c:when test="${selectedArticle.hasAuctionStarted}">
		<tr class="biddingArea">
			<td colspan="2">
				<em>The auction has not started yet.</em>
			</td>
		</tr>
	</c:when>
	<c:when test="${selectedArticle.hasAuctionEnded}">
		<tr class="biddingArea">
			<td colspan="2">
				<em>The auction has ended.</em>
			</td>
		</tr>
	</c:when>
	<c:otherwise>
		<sec:authorize ifAnyGranted="ROLE_USER">
		<form method="post" action="<c:url value='/bid' />">
			<input type="hidden" name="articleId" value="${selectedArticle.id}" />
		<tr class="biddingArea">
			<td>
				<input name="amount" id="bidAmount" class="styledInput" type="text" placeholder="enter your bid here" /><br />
				<span class="nextBidHint">Enter € ${selectedArticle.minimumBid} or more</span>
			</td>
			<td>
				<input class="cupid-green-button" id="bidButton" type="submit" value="Place bid" />
			</td>
		</tr>
		</form>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
		<tr class="biddingArea">
			<td colspan="2">
				<em>Please login to place a bid.</em>
			</td>
		</tr>
		</sec:authorize>
	</c:otherwise>
</c:choose>