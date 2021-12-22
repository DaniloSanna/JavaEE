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
														<input type="hidden" name="action" id="action" value="">
														
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
															
															<h6><span class="msg">${msg}</span> <span class="msg">${msg1}</span></h6>															
															
															<button class="btn btn-primary waves-effect waves-light" onclick="deleteUser()"  value="delete" type="button">Delete</button>
															<button class="btn btn-primary waves-effect waves-light" onclick="clearForm()"  value="clear" type="button">Clear</button>
															<button class="btn btn-primary waves-effect waves-light"  type="submit">Apply</button>
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


	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<script type="text/javascript">
		function clearForm() {
			document.getElementById("formUser").reset();
		}
	
		function deleteUser(){
			if(confirm("Do you really wanna delete this user?")){
			    document.getElementById("action").value = 'delete';
				document.getElementById("formUser").method = 'get';
				document.getElementById("formUser").submit();
			}
		}
	</script>
</body>

</html>
