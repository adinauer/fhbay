<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	// activate datetimepickers
	$(function() {
		$("#startDate").datetimepicker({
			dateFormat: 'dd.mm.yy',
			timeFormat: 'HH:mm:ss',
			minDateTime: 0
		});
		
		$("#endDate").datetimepicker({
			dateFormat: 'dd.mm.yy',
			timeFormat: 'HH:mm:ss',
			minDateTime: 0
		});
	});
</script>

<div id="offerArticle">
	<h1>Offer an article</h1>
	<form method="post" action="<c:url value='/offerArticle' />">
	<table id="addArticle" align="center">
		<tbody>
			<tr>
				<td class="label">Name:</td>
				<td>
					<input name="name" id="articleName" class="styledInput" type="text" placeholder="name of the article" required />
				</td>
			</tr>
			<tr>
				<td class="label">Initial Price:</td>
				<td>
					<input name="initialPrice" id="initialPrice" class="styledInput" type="number" placeholder="initial price of the article" min="0" required />
				</td>
			</tr>
			<tr>
				<td class="label">Start Date:</td>
				<td>
					<input name="startDate" id="startDate" class="styledInput" type="text" placeholder="when to start the auction (click for datepicker)" required />
				</td>
			</tr>
			<tr>
				<td class="label">End Date:</td>
				<td>
					<input name="endDate" id="endDate" class="styledInput" type="text" placeholder="when to end the auction (click for datepicker)" required />
				</td>
			</tr>
			<tr>
				<td class="label">Category:</td>
				<td>
					<div class="dselect">
						<select name="category" id="category">
							<option value="-1">--- Please select a category ---</option>
							
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}">${category.name}</option>
								<c:if test="${not empty category.subCategories}">
									<c:forEach var="subCategory" items="${category.subCategories}">
										<option value="${subCategory.id}" class="sub">${subCategory.name}</option>
									</c:forEach>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label">Description:</td>
				<td>
					<textarea name="description" id="description" placeholder="description of the article" rows="10" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="cupid-green-button" type="submit" value="Offer article" />
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</div>