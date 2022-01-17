<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->

	<jsp:include page="theme-load.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<div class="row">
											<div class="col-sm-12">

												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">User Registration</h4>
														
														<form class="form-material" action="<%=request.getContextPath()%>/ServletUserController" method="post" id="formUser">
														<input type="hidden" name="acao" id="acao" value="">
														
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${information.id}">
																	<span class="form-bar"></span> 
																	<label class="float-label">ID*(read only)</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="name" id="name" class="form-control" required="required" autocomplete="off" value="${information.name}">
																<span class="form-bar"></span>
																<label class="float-label">Name</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${information.email}">
																<span class="form-bar"></span> 
																<label class="float-label">E-mail</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${information.login}">
																<span class="form-bar"></span> 
																<label class="float-label">Login</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="password" name="pass" id="pass" class="form-control" required="required" autocomplete="off" value="${information.pass}">
																<span class="form-bar"></span>
																<label class="float-label">Password</label>
															</div>
															
															<h6><span class="msg" id="msg">${msg}</span></h6>															
															
															<button class="btn btn-primary waves-effect waves-light"  type="submit">Apply</button>
															<button class="btn btn-primary waves-effect waves-light" onclick="clearForm()"  type="button">Clear</button>
															<button class="btn btn-primary waves-effect waves-light" onclick="deleteUserByAjax()" type="button" >Delete</button>
															
															
															<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#searchModal">Search</button>

														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- Modal -->
<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Use´s Search</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<!-- Modal Body Content-->

			<div class="input-group mb-3">
			  <input type="text" class="form-control" placeholder="name" aria-label="name" aria-describedby="basic-addon2" id="nameSearched">
			  <div class="input-group-append">
			    <button class="btn btn-outline-success" type="button" onclick="searchForUser()">Search</button>
			  </div>
			
      		<p class="msg1" id="msg1">${msg1}</p>
		    </div>
		      <table class="table" id="tableUserList">
				  <thead>
					   <tr>
						    <th scope="col">ID</th>
						    <th scope="col">Name</th>
						    <th scope="col">Check</th>
					    </tr>
				  </thead>
				  <tbody>
				  </tbody>
				</table>
      			
      
      
      
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<script type="text/javascript">
		
	function searchForUser(){
			 
			var nameSearch = document.getElementById("nameSearched").value;

			if (nameSearch != null && nameSearch != '' && nameSearch.trim()!= ''){
				
			 $.ajax({
				     
				     method: "get",
				     url : document.getElementById('formUser').action,
				     data : "nameSearched=" + document.getElementById("nameSearched").value + '&acao=searchForUser',
				     success: function (response) {
				    	 
				    	 
				    	 document.getElementById('msg').textContent = response;
				    	 
				     }
				 }).fail(function(xhr, status, errorThrown){
				    alert('Search Error by name: : ' + xhr.responseText);
				 });
			    }
		}
	
	
		function clearForm() {
			
			/*.Reset deixa de funcionar*/
			document.getElementById("formUser").reset();
			
		    var elementos = document.getElementById("formUser").elements; 
		    
		    for (p = 0; p < elementos.length; p ++){
			    elementos[p].value = '';
		    }
			
		}
	
		function deleteUser(){
			if(confirm("Do you really wanna delete this user?")){
			    document.getElementById("action").value = 'delete';
				document.getElementById("formUser").method = 'get';
				document.getElementById("formUser").submit();
			}
		}

		function deleteUserByAjax(){
			
			if (confirm("Do you really wanna delete this user?")){
				
				 $.ajax({
				     
				     method: "get",
				     url : document.getElementById('formUser').action,
				     data : "id=" + document.getElementById('id').value + '&acao=deleteajax',
				     success: function (response) {
				    	clearForm();
					  alert(response);
					  document.getElementById('msg').textContent = response;
				     }
				 }).fail(function(xhr, status, errorThrown){
				    alert('Deletion did not work as expected, check the information inserted: ' + xhr.responseText);
				 });
			    }
		}
		
			
	</script>
</body>

</html>