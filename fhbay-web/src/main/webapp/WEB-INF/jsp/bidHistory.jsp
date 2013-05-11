<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs">
	<a href="javascript:window.history.back()">
		<img class="back" src="<c:url value='/static/img/back.png' />" />
		back to ${selectedArticle.name}
	</a>
</div>
<div id="bids">
	<h1>Bids for <em>${selectedArticle.name}</em></h1> 
	<table id="bidList">
		<thead>
			<tr>
				<th>Bidder</th>
				<th>Bid Amount</th>
				<th>Bid Time</th>
				<th>Price at time of Bid</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty bids}">
					<tr>
						<td colspan="4" align="center" valign="middle">
							<div class="noBidsPlacedYet">No bids have been placed.</div>
						</td>
					</tr>
				</c:when>
				
				<c:otherwise>
					<c:forEach var="bid" items="${bids}">
						<tr<c:if test="${bid.winning}"> class="winningBid"</c:if>>
							<td>${bid.bidderName}</td>
							<td>€ ${bid.amount}</td>
							<td>${bid.bidTimeFormatted}</td>
							<td>€ ${bid.priceAtBidTime}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td class="startingPrice">Starting Price</td>
				<td>€ ${selectedArticle.initialPrice}</td>
				<td>${selectedArticle.startDateFormatted}</td>
				<td>-</td>
			</tr>
		</tbody>
	</table>
	<p class="bidExplanation">If two people bid the same amount, the first bid has priority.</p>
</div>