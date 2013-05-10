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
		<tr>
			<td colspan="3" align="center" valign="middle">
				<div class="noArticlesFound">No articles found.</div>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="<c:url value='/article/123/' />Canon-EOS-1D-X-(SLR)-Body">Canon EOS 1D X (SLR) Body</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime"><span class="ended">ended</span></span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
		
		<tr>
			<td align="left" valign="top">
				<a class="articleName" href="">Article A</a>
			</td>
			<td align="right" valign="top">
				<span class="remainingTime">ends 2013/05/16 15:41</span>
			</td>
			<td align="right" valign="top">
				<span class="price">€ 0.98</span><br />
				<span class="bids">10 bids</span>
			</td>
		</tr>
	</tbody>
</table>