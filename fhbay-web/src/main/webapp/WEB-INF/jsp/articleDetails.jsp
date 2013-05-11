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
			
			<%@ include file="/WEB-INF/jsp/biddingArea.jsp" %>
			
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