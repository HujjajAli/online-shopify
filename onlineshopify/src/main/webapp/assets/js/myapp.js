/**
 * 
if (window.jQuery) 
{       // jQuery is loaded => print the version     
	console.log('JQuery Version :'+jQuery.fn.jquery); 
}*/

$(function(){
	//console.log('JQuery Version :'+JQuery().jquery);
	//Solving the Active menu problem
	switch(menu){
		case 'About Us':
			console.log(menu);
			$('#about').addClass('active');
		    break;
		case 'Contact Us':
			$('#contact').addClass('active');
		    break;
		case 'All Products':
			$('#listProducts').addClass('active');
			break;
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;
		case 'User Cart':
			$('#userCart').addClass('active');
			break;	
		default:
			if(menu == "Home")break;
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
	
	
	//to tackle the csrf token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		//set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	
	console.log('Loading Table Code....')
	$table = $('#productListTable');
	
	//execute the below code only where we have this table
	if($table.length){
		//console.log('Inside the table');
		
		var jsonURL = '';
		if(window.categorId == ''){
			jsonURL = window.context + '/json/data/products';
		}else{
			jsonURL = window.context + '/json/data/products/category/'+window.categorId;
		}
		
		$table.DataTable({
			lengthMenu:[[3,5,8,-1],['3 Records','5 Records','8 Records','All Records']],
			pageLength:5,
			ajax:{
				url:jsonURL,
				dataSrc:''
			},
			columns:[
				{
					data:'code',
					bSortable:false,
					mRender:function(data, type, row){
						return '<img src="'+window.context+'/resources/images/'+data+'.jpg" class="dataTableImg" />';
					}
				},
				{ data:'name' },{ data:'brand' },
				{ 
					data:'unitPrice',
					mRender:function(data, type, row){
						return 'PKR ' + data
					}
				},
				{ 
					data:'quantity',mRender:function(data, type, row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					} 
				},
				{ data:'views' },
				{  
					data:'product_id',
					bSortable:false,
					mRender:function(data, type, row){
						var str = '';
						str += '<a href="'+window.context+'/show_product?product='+data+'" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						
						if(userRole == 'ADMIN'){
							str += '<a href="'+window.context+'/manageProductUpdate?product_id='+data+'" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
						}else{
						
							if(row.quantity < 1){
								str += '<a href="javascrit:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}else{
								str += '<a href="'+window.context+'/cart/add/'+ data +'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';	
							}
					    }
						
						
						return str;
					}
				}
				
			]
			
		});
	}//product table end
	
	//dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	
	//------------------
	
	
	//--------------------
	//Data Table for Admin
	//--------------------
	var debugStr = 'Nan';
	$adminProductsTable = $('#adminProductsTable');
	if($adminProductsTable.length){
		var jsonURL = window.context+'/json/data/all/products/';
		debugStr += jsonURL;
		$adminProductsTable.DataTable({
			lengthMenu:[[5,10,20,-1],['5 Records','10 Records','20 Records','All Records']],
			pageLength:10,
			ajax:{
				url:jsonURL,
				dataSrc:''
			},
			columns:[
				{data:'product_id'},
				{
					data:'code',
					bSortable:false,
					mRender:function(data, type, row){
						return '<img src="'+window.context+'/resources/images/'+data+'.jpg" class="adminDataTableImg" />';
					}
				},{ data:'name' },{ data:'brand' },
				{ 
					data:'quantity',mRender:function(data, type, row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					} 
				},
				{ 
					data:'unitPrice',
					mRender:function(data, type, row){
						return 'PKR ' + data
					}
				},
				{
					data:'active',
					bSortable:false,
					mRender: function(data,type,row){
						
						var str = '';
						// Toogle Button -->
						str = '<label class="switch">';
						if(data){
							str   += '<input type="checkbox" checked="checked" value="'+row.product_id+'"/> ';
						}else{
							str   += '<input type="checkbox" value="'+row.product_id+'"/> ';
						}
						
						str   +='<div class="slider"></div> </label>';
						return str;	
					}
				},
				{
					data:'product_id',
					bSortable:false,
					mRender: function(data,type,row){
						var str = '';
						str += '<a href="'+window.context+'/manageProductUpdate?product_id='+data+'" class="btn btn-warning">';
						str += '<span class="glyphicon glyphicon-pencil"></span></a>';
					
						return str;
					}
				}
			
			],
			initComplete:function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					var checkbox = $(this);
					var checked  = checkbox.prop('checked');
					var dMsg     = (checked)? 'You Want to Activate the Product?':'You Want to Deactivate the Product?';
					var value    = checkbox.prop('value');
					
					//alert(dMsg);
					
					//After Adding BootBox Library Remove Comments
					bootbox.confirm({
						size:'medium',
						title:'Product Activation & Deactivation',
						message:dMsg,
						callback:function(confirmed){
							if(confirmed){
								console.log(value);
								
								activationURL = window.context + '/product_activation_deactivation?prdID='+value;
								$.post(activationURL,function(data){
									bootbox.alert({
										size:'medium',
										title:'Information',
										message:data
									 });
								});
								
								
								
							}else{
								checkbox.prop('checked',!checked)
							}
						}
					});
					
					
				});
			}
			
		});
	}
	//--------------------
	
	
	//validation code for category form
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		
		$categoryForm.validate({
			rules : {
				name : {
					required :true,
					minlength:3
				},
				description :{
					required :true
				}
			},
			messages : {
				name : {
					required : 'Please add the category name',
					minlength: 'The category name should not be less then 3 characters'
				},
				description:{
					required : 'Please add the category description'
				}
			},
			errorElement:'em',
			errorPlacement:function(error,element){
				//add the class of help-block
				error.addClass('help-block');
				//add the error after the input element
				error.insertAfter(element);
			}
		});
		
	}//validate if
	
	//-------/
	//validate code for Login Form
	
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		
		$loginForm.validate({
			rules : {
				username : {
					required:true,
					email:true
				},
				
				password : {
					required:true
				}
			},
			messages : {
				username : {
					required : 'Please Enter the Username',
					email: 'Please Enter Valid Email Address'
				},
				password : {
					required : 'Please Enter the Password'
				}
			},
			errorElement:'em',
			errorPlacement:function(error,element){
				//add the class of help-block
				error.addClass('help-block');
				//add the error after the input element
				error.insertAfter(element);
			}
		});
		
	}//validate if login form
	
	//-------------
	//handling the click event of refresh cart button
	$('button[name="refreshCart"]').click(function(){
		//fetch the CartLine ID
		var cartLineId = $(this).attr('value');
		var countElmnt = $('#count_' + cartLineId);
		
		var originalCount = countElmnt.attr('value');
		var currentCount  = countElmnt.val();
		
		//work only when the count has changed
		if(currentCount != originalCount){
			console.log('Current Count '+currentCount);
			console.log('Orginal Count '+originalCount);
			
			if(currentCount < 1 || currentCount > 3){
				//reverting back the original count
				//cannot be above 3 and below 1 
				countElmnt.val(originalCount);
				bootbox.alert({
					size : 'medium',
					title: 'ERROR',
					message: 'Product Count shoud be Minimum 1 and Maximum 3!'
				});
			}else{
				updateURL = window.context +'/cart/'+ cartLineId +'/update?count='+currentCount;
				window.location.href = updateURL;
			}
		}
	});
	
	
	//-------------
	
});