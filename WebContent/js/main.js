$(document)
		.ready(
				function() {

					hideNewActorForm();
					hideNewMovieForm();
					$('.rightAreaMovie').hide();
					$('.rightAreaActor').hide();

					$.get("http://localhost:8080/MovieCatalogue/rest/movies",
							function(data) {
								for ( var i in data) {
									$('#movieTable').append(
											'<li><a href="#" id="' + data[i].id
													+ '">' + data[i].name
													+ '</a></li>');
								}
								;
							}, "json");

					$.get("http://localhost:8080/MovieCatalogue/rest/actors",
							function(data) {
								for ( var i in data) {
									$('#actorTable').append(
											'<li><a href="#" id="' + data[i].id
													+ '">' + data[i].name
													+ '</a></li>');
								}
								;
							}, "json");

					function returnDate(date) {
						return new Date(date.toISOString());
					}

					function appendActors(data) {
						for ( var j in data) {
							return data[j].name + " " + data[j].description;
						}
					}

					$('#btnAddActor').click(function() {
						addNewActorForm();

						hideNewMovieForm();
					});

					$('#btnAddMovie').click(function() {
						addNewMovieForm();

						hideNewActorForm();
					});

					var counter = 1;

					$('#addMovieField')
							.click(
									function() {

										$("#textBoxesMoviesNew")
												.append(
														'<label>Movie ID '
																+ counter
																+ ':'
																+ '</label>'
																+ '<input type="text" name="movieID'
																+ counter
																+ '" id="textbox'
																+ counter
																+ '" value="" >');
										counter++;
									});

					$('#addActorField')
							.click(
									function() {

										$("#textBoxesActorsNew")
												.append(
														'<label>Actor ID '
																+ counter
																+ ':'
																+ '</label>'
																+ '<input type="text" name="actorID'
																+ counter
																+ '" id="textbox'
																+ counter
																+ '" value="" >');
										counter++;
									});

					$('#btnSearch').click(function() {
						search();
					});
					

					
					$('#btnDeleteMovie').click(function(){
						$.ajax({
	                        url: "http://localhost:8080/MovieCatalogue/rest/delete/movie/" + $('#movieID').val(),
	                        type: "DELETE"
	                    });
						alert($('#movieID').val() + " is successfully deleted");
					});
					
					
					$('#btnDeleteActor').click(function(){
						$.ajax({
	                        url: "http://localhost:8080/MovieCatalogue/rest/delete/actor/" + $('#actorID').val(),
	                        type: "DELETE"
	                    });
					});
					
					$("#btnEditMovie")
					.click(
							function() {
								$('#textBoxesActors')
										.append(
												'<label>Actor name :'
														+ '</label>'
														+ '<input type="text" name="actorID'
														+ "new"
														+ '" id="textbox'
														+ "new"
														+ '" value="" />');
							});
					
					$('#btnUpdateMovie').click(
							function(){
								$('#textBoxesActors').children('input').each(function () {
				                    $.ajax({
				                        url: "http://localhost:8080/MovieCatalogue/rest/update/movie/addActor",
				                        type: "POST",
				                        data: formToJSONMovieWithActor(this.value),
				                        contentType: "application/json",
				                        cache: false,
				                        dataType: "json"
				                    });
								});
							});

					function addNewActorForm() {
						$('.addNewActor').show();
						$('#textBoxesMoviesNew').children().remove();
						counter = 1;
					}

					function addNewMovieForm() {
						$('.addNewMovie').show();
						$('#textBoxesActorsNew').children().remove();
						counter = 1;
					}

					function hideNewActorForm() {
						$(".addNewActor").hide();
					}

					function hideNewMovieForm() {
						$('.addNewMovie').hide();
					}

					$('.leftArea')
							.click(
									function(event) {
										$('.rightAreaActor').hide();
										$('.rightAreaMovie').show();
										$
												.post(
														"http://localhost:8080/MovieCatalogue/rest/movies/findID="
																.concat(event.target.id),
														function(data) {
															$("#movieID").val(
																	data.id);
															$('#movieName1')
																	.val(
																			data.name);
															$('#movieYear1')
																	.val(
																			data.year);
															var list = data.listOfActors;
															$(
																	'#textBoxesActors')
																	.children()
																	.remove();
															for ( var i in list) {
																$(
																		'#textBoxesActors')
																		.append(
																				'<label>Actor name :'
																						+ '</label>'
																						+ '<input type="text" name="actorID'
																						+ i
																						+ '" id="textbox'
																						+ i
																						+ '" value="'
																						+ list[i].name
																						+ '"disabled />');
															}
															;
														}, "json");
									});

					$('.mainArea')
					.click(
							function(event) {
								$('.rightAreaMovie').hide();
								$('.rightAreaActor').show();
								
								$
										.post(
												"http://localhost:8080/MovieCatalogue/rest/actors/find="
														.concat(event.target.id),
												function(data) {
													$("#actorID").val(
															data.id);
													$('#actorName')
															.val(
																	data.name);
													$('#actorDescription')
															.val(
																	data.description);
													$('#actorBirthdate').val(data.dateBirth);
													var list = data.listOfMovies;
													$(
															'#textBoxesMovies')
															.children()
															.remove();
													for ( var i in list) {
														$(
																'#textBoxesMovies')
																.append(
																		'<label>Movie '
																		+ i
																		+ ' :'
																		+ '</label>'
																		+ '<input type="text" name="movieID'
																		+ i
																		+ '" id="textbox'
																		+ i
																		+ '" value="'
																		+ list[i].name
																		+'" >');
													}
													;
												}, "json");
							});
					$("#btnSave").click(function(){
	                    $.ajax({
	                        url: "http://localhost:8080/MovieCatalogue/rest/insert/actor",
	                        type: "POST",
	                        data: formToJSONActor(),
	                        contentType: "application/json",
	                        cache: false,
	                        dataType: "json"
	                    });

					});
					
					function formToJSONActor() {
						return JSON.stringify({ 
							"name": $('#name').val(), 
							"description": $('#description').val(),
							"dateBirth": $('#birthdate').val()
							});
					};
					
					$("#btnSaveMovie").click(function(){
	                    $.ajax({
	                        url: "http://localhost:8080/MovieCatalogue/rest/insert/movie",
	                        type: "POST",
	                        data: formToJSONMovie(),
	                        contentType: "application/json",
	                        cache: false,
	                        dataType: "json"
	                    });

					});
					
					function formToJSONMovie() {
						return JSON.stringify({
							"name": $('#movieName').val(), 
							"year": $('#movieYear').val()
							});
					};
					
					function formToJSONMovieWithActor(value) {
						return JSON.stringify({
							"id":$('#movieID').val(),
							"name": $('#movieName1').val(), 
							"year": $('#movieYear1').val(),
							"actorName": value
							});
					};
					
					function returnAllActorsForMovie(){
						$('#textBoxesActorsNew').children('input').each(function () {
						    alert(this.value);
						});
					}

				});
