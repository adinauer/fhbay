<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs">
	<a href="javascript:window.history.back()">
		<img class="back" src="<c:url value='/static/img/back.png' />" />
		back to article list
	</a>
	| 
	Listed in in category: Video > Cables
</div>
<div id="articleDetails">
	<h1>${selectedArticle.getName()}</h1>
	<table id="articleInfos" align="center">
		<tbody>
			<tr>
				<td class="label">Time left:</td>
				<td colspan="2">
					<span class="timeLeft">1 day 14 hours</span> 
					<span class="endDate">(${dateAndTimeFormat.format(selectedArticle.getEndDate())})</span>
				</td>
			</tr>
			<tr class="biddingArea noBottomSpacing">
				<td class="label" rowspan="2">Current bid:</td>
				<td>
					<span class="price">€ ${selectedArticle.getInitialPrice()}</span>
				</td>
				<td>
					<span class="bids">
						[ 
							<sec:authorize ifAnyGranted="ROLE_ADMIN">
								<a href="<c:url value='/bidHistory?articleId=123' />">5 bids</a> 
							</sec:authorize>
							<sec:authorize ifNotGranted="ROLE_ADMIN">
								5 bids 
							</sec:authorize>
						]
					</span>
				</td>
			</tr>
			<c:choose>
				<c:when test="${selectedArticle.getStartDate() > currentDateAndTime}">
					<tr class="biddingArea">
						<td colspan="2">
							<em>The auction has not started yet.</em>
						</td>
					</tr>
				</c:when>
				<c:when test="${selectedArticle.getEndDate() < currentDateAndTime}">
					<tr class="biddingArea">
						<td colspan="2">
							<em>The auction has ended.</em>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<sec:authorize ifAnyGranted="ROLE_USER">
					<form method="post" action="#bid">
					<tr class="biddingArea">
						<td>
							<input id="bidAmount" class="styledInput" type="text" placeholder="enter your bid here" /><br />
							<span class="nextBidHint">Enter € ${selectedArticle.getInitialPrice() + 1.0} or more</span>
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
			<tr>
				<td class="label">Shipping:</td>
				<td colspan="2">
					<span><em>FREE</em> Economy Shipping</span><br />
					<span class="shippingInfo">Item location: <em>Austria</em></span><br />
					<span class="shippingInfo">Ships to: <em>Austria</em></span>
				</td>
			</tr>
			<tr>
				<td class="label">Delivery:</td>
				<td colspan="2">
					<span>
						Estimated between<br />
						Tue. May 14 and Tue May 28
					</span> 
				</td>
			</tr>
			<tr>
				<td class="label">Returns:</td>
				<td colspan="2">
					<span>
						14 days money back or item exchange,<br /> 
						buyer pays return shipping
					</span> 
				</td>
			</tr>
		</tbody>
	</table>
	
	<h2>Description</h2>
	<p>${selectedArticle.getDescription()}</p>
</div>