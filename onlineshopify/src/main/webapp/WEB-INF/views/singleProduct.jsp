<div class="container">
	<!-- Breadcrumb -->
	<div class="row">
		<div class="col-xs-12">
			
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/showall">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	</div>
	
	<div class="row">
		<!-- Display Product Image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img alt="${product.name}" src="${img}/${product.code}.jpg" style="width:800px; height:300px;" class="img img-responsive"/>
			</div>
		</div>
		
		<!-- Display Product Description -->
		<div class="col-xs-12 col-sm-4">
			<h3>${product.name}</h3>
			<hr/>
			
			<h3>${product.description}</h3>
			<hr/>
			
			<h4>Price: <strong> PKR ${product.unitPrice}</strong></h4>
			<hr/>
			
			
			
			<c:choose>
				<c:when test="${product.quantity < 1}">
					<h6>Qty: Available: <span style="color:red">Out of Stock</span> </h6>
				</c:when>
				<c:otherwise>
					<h6>Qty: Available: ${product.quantity}</h6>
				</c:otherwise>
			</c:choose>
			
			<security:authorize access="hasAuthority('USER')">
			<c:choose>
				<c:when test="${product.quantity < 1}">
					<a href="javascrit:void(0)" class="btn btn-success disabled"><strike>
					<span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart</strike></a> &#160;
				</c:when>
				<c:otherwise>
					<a href="${contextRoot}/cart/add/${product.product_id}/product" class="btn btn-success">
					<span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart</a> &#160;
				</c:otherwise>
			</c:choose>
			</security:authorize>
			
			<security:authorize access="hasAuthority('ADMIN')">
				<a href="${contextRoot}/manageProductUpdate?product_id=${product.product_id}" class="btn btn-warning">
					<span class="glyphicon glyphicon-pencil"></span> EDIT</a> &#160;
			</security:authorize>
			
			
			<a href="${contextRoot}/showall" class="btn btn-primary"> Back</a> &#160;
		</div>
	</div>
</div>