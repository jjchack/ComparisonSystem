<%@ page language="java" import="java.util.*,cn.edu.hpu.utils.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(session.getAttribute("username") == null) {
	response.sendRedirect("index.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<title>搜索结果</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-select.css">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui1.css">
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Resale Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<!--fonts-->
<link href='http://fonts.useso.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!--//fonts-->	
<!-- js -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- js -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script>
  $(document).ready(function () {
    var mySelect = $('#first-disabled2');

    $('#special').on('click', function () {
      mySelect.find('option:selected').prop('disabled', true);
      mySelect.selectpicker('refresh');
    });

    $('#special2').on('click', function () {
      mySelect.find('option:disabled').prop('disabled', false);
      mySelect.selectpicker('refresh');
    });

    $('#basic2').selectpicker({
      liveSearch: true,
      maxOptions: 1
    });
  });
</script>
<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
<link href="css/jquery.uls.css" rel="stylesheet"/>
<link href="css/jquery.uls.grid.css" rel="stylesheet"/>
<link href="css/jquery.uls.lcd.css" rel="stylesheet"/>
<!-- Source -->
<script src="js/jquery.uls.data.js"></script>
<script src="js/jquery.uls.data.utils.js"></script>
<script src="js/jquery.uls.lcd.js"></script>
<script src="js/jquery.uls.languagefilter.js"></script>
<script src="js/jquery.uls.regionfilter.js"></script>
<script src="js/jquery.uls.core.js"></script>
<script>
			$( document ).ready( function() {
				$( '.uls-trigger' ).uls( {
					onSelect : function( language ) {
						var languageName = $.uls.data.getAutonym( language );
						$( '.uls-trigger' ).text( languageName );
					},
					quickList: ['en', 'hi', 'he', 'ml', 'ta', 'fr'] //FIXME
				} );
			} );
		</script>
    <script src="js/tabs.js"></script>
	
<script type="text/javascript">
$(document).ready(function () {    
var elem=$('#container ul');      
	$('#viewcontrols a').on('click',function(e) {
		if ($(this).hasClass('gridview')) {
			elem.fadeOut(1000, function () {
				$('#container ul').removeClass('list').addClass('grid');
				$('#viewcontrols').removeClass('view-controls-list').addClass('view-controls-grid');
				$('#viewcontrols .gridview').addClass('active');
				$('#viewcontrols .listview').removeClass('active');
				elem.fadeIn(1000);
			});						
		}
		else if($(this).hasClass('listview')) {
			elem.fadeOut(1000, function () {
				$('#container ul').removeClass('grid').addClass('list');
				$('#viewcontrols').removeClass('view-controls-grid').addClass('view-controls-list');
				$('#viewcontrols .gridview').removeClass('active');
				$('#viewcontrols .listview').addClass('active');
				elem.fadeIn(1000);
			});									
		}
	});
});
</script>
</head>
<body>
<div class="header">
		<div class="container">
			<div class="logo">
				<a href="productList.jsp"><span>比价</span>系统</a>
			</div>
			<div class="header-right">
			<a class="account" href="${pageContext.request.contextPath}/servlet/logOut">欢迎你：${sessionScope.username}</a>
			</div>
		</div>
		</div>
	</div>
	<div class="main-banner banner text-center">
	  <div class="container">    
			<h1>基于  <span class="segment-heading">    协同过滤算法 </span> 的智能比价系统模型</h1>
			<p></p>
	  </div>
	</div>
	
	<!-- Products -->
	<div class="total-ads main-grid-border">
		<div class="container">
			
			
			<ol class="breadcrumb" style="margin-bottom: 5px;">
			  <li><a href="${pageContext.request.contextPath}/productList.jsp">主页</a></li>
			  <li class="active">${sessionScope.pname}</li>
			</ol>
			<div class="ads-grid">
				<div class="side-bar col-md-3">
					<div class="search-hotel">
<!-- -----搜索框--------------- -->
					<h3 class="sear-head">搜索</h3>
					<form action="${pageContext.request.contextPath }/servlet/search" method="post">
						<input type="text" name="property" value="Product name..." onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Product name...';}" required="">
						<input type="submit" value=" ">
					</form>
				</div>
				
				<div class="range">
					<h3 class="sear-head">价格范围</h3>
							<ul class="dropdown-menu6">
								<li>
									                
									<div id="slider-range"></div>							
										<input type="text" id="amount" style="border: 0; color: #ffffff; font-weight: normal;" />
									</li>			
							</ul>
							<!---->
							<script type="text/javascript" src="js/jquery-ui.js"></script>
							<script type='text/javascript'>//<![CDATA[ 
							$(window).load(function(){
							 $( "#slider-range" ).slider({
										range: true,
										min: 0,
										max: 9000,
										values: [ 50, 6000 ],
										slide: function( event, ui ) {  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
										}
							 });
							$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );

							});//]]>  

							</script>
							
				</div>
				<div class="featured-ads">
					<h2 class="sear-head fer">特色商品</h2>
					<div class="featured-ad">
						<a href="${pageContext.request.contextPath}/dealProduct?id=350">
									<li>
									<img src="images/sz3.jpg" title="" alt="" />
									<section class="list-left">
									<h5 class="title">森马</h5>
									<span class="adprice">360</span>
									<p>时尚显瘦休闲铅笔裤ALC133JPT007</p>
									<p class="catpath">亚马逊</p>
									</section>
									<section class="list-right">
									<span class="date">2016.5.6</span>
									<span class="cityname">焦作</span>
									</section>
									<div class="clearfix"></div>
									</li> 
								</a>

					</div>
					<div class="featured-ad">
						<a href="${pageContext.request.contextPath}/dealProduct?id=388">
									<li>
									<img src="images/qc1.jpg" title="" alt="" />
									<section class="list-left">
									<h5 class="title">别克</h5>
									<span class="adprice">250000</span>
									<p>汽车贴膜 全车膜 太阳膜 车膜 汽车膜 汽车玻璃防爆膜隔热膜</p>
									<p class="catpath">天猫</p>
									</section>
									<section class="list-right">
									<span class="date">2016.5.6</span>
									<span class="cityname">焦作</span>
									</section>
									<div class="clearfix"></div>
									</li> 
								</a>

					</div>
					<div class="featured-ad">
						<a href="${pageContext.request.contextPath}/dealProduct?id=409">
									<li>
									<img src="images/bk0.jpg" title="" alt="" />
									<section class="list-left">
									<h5 class="title">嘉陵</h5>
									<span class="adprice">2100</span>
									<p>越野摩托车赛车服四季防摔服拉力服骑行服机车服套装男装备</p>
									<p class="catpath">苏宁</p>
									</section>
									<section class="list-right">
									<span class="date">2016.5.6</span>
									<span class="cityname">焦作</span>
									</section>
									<div class="clearfix"></div>
									</li> 
								</a>

					</div>
					<div class="clearfix"></div>
				</div>
				</div>
				<div class="ads-display col-md-9">
					<div class="wrapper">					
					<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
					  <ul id="myTab" class="nav nav-tabs nav-tabs-responsive" role="tablist">
						<li role="presentation" class="active">
						  <a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">
							<span class="text">${sessionScope.search}</span>
						  </a>
						</li>
						
					  </ul>
					  <div id="myTabContent" class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledby="home-tab">
						   <div>
												<div id="container">
								<div class="view-controls-list" id="viewcontrols">
									<label>view :</label>
									<a class="listview active"><i class="glyphicon glyphicon-th-list"></i></a>
								</div>
								<div class="sort">
								   
									 </div>
								<div class="clearfix"></div>
							<ul class="list">
<c:forEach items="${sessionScope.pb.pageData}" var="product" varStatus="status" >
								<a href="${pageContext.request.contextPath}/dealProduct?id=${product.id}">
									<li>
									<img src="images/${product.img}" title="" alt="" />
									<section class="list-left">
									<h5 class="title">${product.name}</h5>
									<span class="adprice">${product.price }</span>
									<p>${product.descs }</p>
									<p class="catpath"><font color="#FF0000"><b>${product.source }</b></font>
</p>
									</section>
									<section class="list-right">
									<span class="date">2016.5.6</span>
									<span class="cityname">焦作</span>
									</section>
									<div class="clearfix"></div>
									</li> 
								</a>
</c:forEach>
							</ul>
						</div>
							</div>
						</div>				
						<ul class="pagination pagination-sm">
							<li><a href="${pageContext.request.contextPath}/servlet/search?property=${sessionScope.search}&pageNumber=${sessionScope.pb.currentPage-1}&encode=1">Prev</a></li>
							<li><a href="#">第${sessionScope.pb.currentPage}页</a></li>
							<li><a href="${pageContext.request.contextPath}/servlet/search?property=${sessionScope.search}&pageNumber=${sessionScope.pb.currentPage+1}&encode=1">next</a></li>	
							<li><a href="#">共${sessionScope.pb.totalPage}页</a></li>
						</ul>
					  </div>
					</div>
				</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>	
	</div>
	<!-- // Products -->
	<!--footer section start-->		
		<footer>
			
			</div>	
			<div class="footer-bottom text-center">
			<div class="container">
				<div class="footer-logo">
					<a href="productList.jsp"><span>比价</span>系统</a>
				</div>
				<div class="footer-social-icons">
					<ul>
						<li><a class="facebook" href="#"><span>Facebook</span></a></li>
						<li><a class="twitter" href="#"><span>Twitter</span></a></li>
						<li><a class="flickr" href="#"><span>Flickr</span></a></li>
						<li><a class="googleplus" href="#"><span>Google+</span></a></li>
						<li><a class="dribbble" href="#"><span>Dribbble</span></a></li>
					</ul>
				</div>
				<div class="copyrights">
					<p>Copyright &copy; 2016.Company name All rights reserved.</a></p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		</footer>
        <!--footer section end-->
</body>
</html>