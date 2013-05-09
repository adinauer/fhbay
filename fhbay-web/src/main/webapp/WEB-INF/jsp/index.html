<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>FHBay</title>
	
	<script type="text/javascript" src="./js/lib/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="./js/lib/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="./js/lib/jquery-ui-timepicker-addon.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/style.css">
	<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#categories .secondLevel").hide();
		});
	
		function toggleCategoryVisibility(toggle) {
			var subcategories = $(toggle).siblings("ul");
			if (subcategories.is(":visible")) {
				subcategories.hide();
				toggle.src = "./img/arrowRight.png";
			} else {
				subcategories.show();
				toggle.src = "./img/arrowDown.png";
			}
		}
		
		// activate styled select form fields
		$(function() {
			var update = function() {
				$(this).parent().attr('title', $(this).val());
			};
			$(document).on('click change update', '.dselect>select', update);
			$('.dselect>select').trigger('update');
		});
		
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
</head>

<body>

<div id="header">
	<a href="#"><img id="logo" src="./img/fhbay.png" alt="FHBay" /></a>
</div>

<div id="leftPane">
	<div id="search">
		<form name="searchForm" method="GET" action="#">
		<fieldset>
			<legend>Search</legend>
		
			<div>
				<input class="styledInput" type="text" placeholder="Search for an article"/>
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
	
	<div id="login">
		<form name="loginForm" method="POST" action="j_spring_security_check">
		<fieldset>
			<legend>Login</legend>
			
			<div>
				<span><label for="j_username">Username</label></span>
				<span><input id="j_username" class="styledInput" type="text" placeholder="enter your username"/></span>
			</div>
			
			<div>
				<span><label for="j_password">Password</label></span>
				<span><input id="j_password" class="styledInput" type="password" placeholder="enter your password"/></span>
			</div>
			
			<div>
				<span class="forgotPassword"><a href="#forgotPassword">forgot password?</a></span>
				<span class="signUp"><a href="#signUp">Sign up</a></span>
			</div>
			
			<div class="clear">
				<span><input id="btn_login" class="button grey" type="submit" value="login" /></span>
			</div>
		</fieldset>
		</form>
	</div>
	
	<!-- if not logged in -->
	<div id="userControls">
		<fieldset>
			<legend>Joe User</legend>
			
			<span id="logout">[ <a href="#logout">Logout</a> ]</span><br />
			
			<ul id="userLinks">
				<li>
					<span><a href="#offerArticle">Offer an article</a></span>
				</li>
				<li>
					<span><a>Articles I'm offering</a></span>
				</li>
				<li>
					<span><a>Articles I'm bidding on</a></span>
				</li>
			</ul>
		</fieldset>
	</div>
	
	<div id="categories">
		<fieldset>
			<legend>Categories</legend>
			<ul id="categoryList" class="firstLevel">
				<li>
					<img class="visibilityToggle" src="./img/arrowRight.png" onclick="toggleCategoryVisibility(this)" />
					Photography Equipment
					
					<ul class="secondLevel">
						<li>Cameras</li>
						<li>Lenses</li>
						<li>Memory Cards</li>
						<li>Batteries</li>
					</ul>
				</li>
				
				<li>
					<img class="visibilityToggle" src="./img/dash.png" />
					Software					
				</li>
				
				<li>
					<img class="visibilityToggle" src="./img/arrowRight.png" onclick="toggleCategoryVisibility(this)" />
					Audio
					
					<ul class="secondLevel">
						<li>Receiver</li>
						<li>Speakers</li>
						<li>Cables</li>
					</ul>
				</li>
				
				<li>
					<img class="visibilityToggle" src="./img/arrowRight.png" onclick="toggleCategoryVisibility(this)" />
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
</div>

<div id="content">
	<!-- article list -->
	<div class="breadcrumbs">Listing articles in category: Video > Cables</div>
	<div class="breadcrumbs">Searching for "<span class="searchString">Canon EOS 1D X</span>" in All Categories</div>
	<div class="breadcrumbs">Searching for "<span class="searchString">Canon EOS 1D X</span>" in category: Photography Equipment > Camera</div>
	<table id="articles">
		<tbody>
			<tr>
				<td colspan="3" align="center" valign="middle">
					<div class="noArticlesFound">No articles found.</div>
				</td>
			</tr>
			<tr>
				<td align="left" valign="top">
					<a class="articleName" href="">Article A</a>
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

	<!-- article details -->
	<div class="breadcrumbs">
		<a href="#back">
			<img class="back" src="./img/back.png" />
			back to article list
		</a>
		| 
		Listed in in category: Video > Cables
	</div>
	<div id="articleDetails">
		<h1>Canon EOS 1D X (SLR) Body</h1>
		<table id="articleInfos" align="center">
			<tbody>
				<tr>
					<td class="label">Time left:</td>
					<td colspan="2">
						<span class="timeLeft">1 day 14 hours</span> 
						<span class="endDate">(May 10, 2013 19:36:44 PDT)</span>
					</td>
				</tr>
				<form method="post" action="#bid">
				<tr class="biddingArea noBottomSpacing">
					<td class="label" rowspan="2">Current bid:</td>
					<td>
						<span class="price">€ 0.99</span>
					</td>
					<td>
						<span class="bids">
							[ 
								<a href="#bidHistory">5 bids</a> 
							]
						</span>
					</td>
				</tr>
				<tr class="biddingArea">
					<td>
						<input id="bidAmount" class="styledInput" type="text" placeholder="enter your bid here" /><br />
						<span class="nextBidHint">Enter € 1.04 or more</span>
					</td>
					<td>
						<input class="cupid-green-button" id="bidButton" type="submit" value="Place bid" />
					</td>
				</tr>
				</form>
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
		<p>
		Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque porta, arcu sit amet dictum fringilla, magna nunc fringilla lorem, in rutrum leo lectus id augue. Duis ac orci enim, tincidunt sagittis ante. Suspendisse a augue tellus, ac pellentesque sem. Integer pellentesque, dolor at cursus varius, diam arcu posuere risus, at mollis eros dui at ligula. Morbi rutrum velit nec turpis placerat ut lacinia neque ultrices. Maecenas hendrerit odio libero. Praesent adipiscing imperdiet justo, nec fermentum ligula elementum ac. Aenean ut est dui, eu tristique justo. Cras in lacus neque. Phasellus eget lectus eget ligula tristique adipiscing. Suspendisse et adipiscing elit. Integer ipsum purus, tristique hendrerit porttitor a, accumsan eu leo. Pellentesque pulvinar nunc ac diam vestibulum at ultricies turpis dictum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Ut in eros sit amet urna gravida aliquet. Quisque laoreet, orci sit amet tristique molestie, ligula neque commodo enim, eget commodo magna massa eu enim.
		</p>
	</div>
	
	<!-- list of bids (need to be admin) -->
	<div class="breadcrumbs">
		<a href="#back">
			<img class="back" src="./img/back.png" />
			back to Canon EOS 1D X (SLR) Body
		</a>
	</div>
	<div id="bids">
		<h1>Bids for <em>Canon EOS 1D X (SLR) Body</em></h1> 
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
				<tr>
					<td colspan="4" align="center" valign="middle">
						<div class="noBidsPlacedYet">No bids have been placed yet.</div>
					</td>
				</tr>
				<tr class="winningBid">
					<td>Joe User</td>
					<td>€ 2.99</td>
					<td>May-08-13 19:20:08 PDT</td>
					<td>€ 1.99</td>
				</tr>
				<tr>
					<td>John Doe</td>
					<td>€ 1.99</td>
					<td>May-08-13 19:20:08 PDT</td>
					<td>€ 0.99</td>
				</tr>
				<tr>
					<td class="startingPrice">Starting Price</td>
					<td>€ 0.99</td>
					<td>May-08-13 19:20:08 PDT</td>
					<td>-</td>
				</tr>
			</tbody>
		</table>
		<p class="bidExplanation">If two people bid the same amount, the first bid has priority. </p>
	</div>
	
	<!-- offer new article form -->
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
</div>

<div class="clear"></div>
<div id="footer">
	<span id="copyright">Copyright © 2013 FHBay. All Rights Reserved.</span>
</div>

</body>
</html>