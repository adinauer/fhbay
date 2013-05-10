<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="search">
	<form name="searchForm" method="GET" action="search">
	<fieldset>
		<legend>Search</legend>
	
		<div>
			<input name="q" class="styledInput" type="text" placeholder="Search for an article"/>
		</div>
		
		<div class="dselect">
			<select>
				<option>All categories</option>
				<option>Photography Equipment</option>
					<option class="sub">Receiver</option>
				<option>Audio</option>
					<option class="sub">Receiver</option>
					<option class="sub">Speakers</option>
					<option class="sub">Cables</option>
				<option>Video</option>
			</select>
		</div>
		
		<div>
			<span><input id="btn_search" class="button grey" type="submit" value="search" /></span>
		</div>
	</fieldset>
	</form>
</div>