<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="categories">
	<fieldset>
		<legend>Categories</legend>
		<ul id="categoryList" class="firstLevel">
			<li>
				<img class="visibilityToggle" src="./static/img/arrowRight.png" onclick="toggleCategoryVisibility(this)" />
				Photography Equipment
				
				<ul class="secondLevel">
					<li>Cameras</li>
					<li>Lenses</li>
					<li>Memory Cards</li>
					<li>Batteries</li>
				</ul>
			</li>
			
			<li>
				<img class="visibilityToggle" src="./static/img/dash.png" />
				Software					
			</li>
			
			<li>
				<img class="visibilityToggle" src="./static/img/arrowRight.png" onclick="toggleCategoryVisibility(this)" />
				Audio
				
				<ul class="secondLevel">
					<li>Receiver</li>
					<li>Speakers</li>
					<li>Cables</li>
				</ul>
			</li>
			
			<li>
				<img class="visibilityToggle" src="./static/img/arrowRight.png" onclick="toggleCategoryVisibility(this)" />
				Video
				
				<ul class="secondLevel">
					<li>TVs</li>
					<li>Cables</li>
					<li>Recorder</li>
				</ul>					
			</li>
		</ul>
	</fieldset>
</div>