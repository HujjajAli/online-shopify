<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%> 
<div class="container">
	<div class="row">
	
	<c:if test="${not empty msg}">
		<div class="col-xs-12">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				
				${msg}
			</div>
		</div>
	</c:if>
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				
				<div class="panel-body">
				
					<!-- FORM ELEMENT -->
					<sf:form  class="form-horizontal" modelAttribute="product" action="${contextRoot}/add_product" enctype="multipart/form-data" method="POST">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Product Name: </label>
							
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block"  element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter Brand Name: </label>
							
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block"  element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="category">Select Category: </label>
							
							<div class="col-md-8">
								<sf:select path="category" id="category" class="form-control" items="${categories}" itemLabel="name" itemValue="id"/>
								<c:if test="${product.product_id == 0}">
									<br/>
									<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-sm">Add Category</button>
								</c:if>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Enter Description: </label>
							
							<div class="col-md-8">
								<sf:textarea rows="2" cols="8" path="description" id="description" placeholder="Wirte Your Description Here" class="form-control"></sf:textarea>
								<sf:errors path="description" cssClass="help-block"  element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter Unit Price: </label>
							
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="0.0" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block"  element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Enter Quantity: </label>
							
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="0" class="form-control" />
							</div>
						</div>
						
						<!-- File Element for Image Upload -->
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select an Image: </label>
							
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file"  class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" path="submit" id="submit" value="Submit" class="btn btn-primary" />
								
								<sf:hidden path="product_id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="user_details"/>
								<sf:hidden path="active"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
							</div>
							
						</div>
						
					</sf:form>
				</div>
			</div>
		</div>
		
	</div>


	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3>
			<hr>
		</div>
		
		<div class="col-xs-12">
			<div class="container-fluid">
				<div class="table-responsive">

				<!-- Products table for Admin -->
				<table id="adminProductsTable"
					class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</thead>
					
					
					
					<tfoot>
						<tr>
							<th>ID</ht>
							<th>&#160;</ht>
							<th>Name</ht>
							<th>Brand</th>
							<th>Quantity</ht>
							<th>Unit Price</ht>
							<th>Active</ht>
							<th>Edit</ht>
						</tr>
					</tfoot>
				</table>
			 </div>
			</div>
		
		</div>
	</div>


<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">Add New Category</h4>
			</div>
			
			<div class="modal-body">
				<!-- Category Form -->
				<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot}/add_category" method="POST" class="form-horizontal">
					
					<div class="form-group">
						<label for="cat_name" class="control-label col-md-4">Category Name</label>
						<div class="col-md-8">
							<sf:input type="text" path="name" id="cat_name" class="form-control"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="cat_description" class="control-label col-md-4">Category Description</label>
						<div class="col-md-8">
							<sf:textarea cols="" rows="5" path="description" id="cat_description" class="form-control"/>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-offset-4 col-md-8">
							<input type="submit" value="Add Category" class="btn btn-primary"/>
						</div>
					</div>
					
					
				</sf:form>
			</div>
		</div>
	</div>
</div>


</div>