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
	
	// indent sub-options in select form elements
	$(function() {
		$("select .sub").prepend("&nbsp;&nbsp;&nbsp;&nbsp;");
	});
</script>

<div id="offerArticle">
	<h1>Offer an article</h1>
	<form>
	<table id="addArticle" align="center">
		<tbody>
			<tr>
				<td class="label">Name:</td>
				<td>
					<input id="articleName" class="styledInput" type="text" placeholder="name of the article" required />
				</td>
			</tr>
			<tr>
				<td class="label">Initial Price:</td>
				<td>
					<input id="initialPrice" class="styledInput" type="number" placeholder="initial price of the article" min="0" required />
				</td>
			</tr>
			<tr>
				<td class="label">Start Date:</td>
				<td>
					<input id="startDate" class="styledInput" type="text" placeholder="when to start the auction (click for datepicker)" required />
				</td>
			</tr>
			<tr>
				<td class="label">End Date:</td>
				<td>
					<input id="endDate" class="styledInput" type="text" placeholder="when to end the auction (click for datepicker)" required />
				</td>
			</tr>
			<tr>
				<td class="label">Category:</td>
				<td>
					<div class="dselect">
						<select id="category">
							<option>--- Please select a category ---</option>
							<option>Photography Equipment</option>
								<option class="sub">Receiver</option>
							<option>Audio</option>
								<option class="sub">Receiver</option>
								<option class="sub">Speakers</option>
								<option class="sub">Cables</option>
							<option>Video</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label">Description:</td>
				<td>
					<textarea id="description" placeholder="description of the article" rows="10" cols="50"></textarea>
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