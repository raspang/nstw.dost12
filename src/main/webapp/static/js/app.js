$(function() {

	// code for jquery dataTable
	var $table = $('#voter'); 
	// execute the below code only where we have this table
	if ($table.length) {

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/json/data/listparticipants';

		/* alert(jsonUrl); */
		$table
				.DataTable({
					responsive: true,
					lengthMenu : [ [ 10, 20 ], [ '10 Records', '20 Records' ] ],
					pageLength : 10,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
						{
							data : 'id'
						},
						{
							data : 'code',
							mRender : function(data, type, row) {
								var str = '';

								if (data != null)
									str = "<code>"+data+"</code>";
	
								return str;
							}
						},
						{
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row) {

								var str = '';
								if (userRole === 'ADMIN') {
									str += '<a href="'
											+ window.contextRoot
											+ '/mark-participant-'
											+ data
											+ '"><span style="color:green" class="glyphicon glyphicon-thumbs-up"></span></a>';
								}
								return str;
							}
						},
						{
							data : 'attendsStr'
						},
							{
								data : 'completeName'
							},
							{
								data : 'company'
					
							},
							{
								data : 'designation'
							},

							{
								data : 'business'
							},
				
							{
								data : 'vip',
								mRender : function(data, type, row) {
									var str = '_';
									if(data)
										str = '<span class="text-warning glyphicon glyphicon-king"></span>';
									return str;
								}
								
								
							},

							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									if (userRole === 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/edit-person-'
												+ data
												+ '" class="btn btn-success custom-width"  ><span style="color: #ffffff;" class="glyphicon glyphicon-pencil"></span></a>&nbsp;';
										str += '&nbsp;<a href="'
												+ window.contextRoot
												+ '/delete-person-'
												+ data
												+ '" onclick="return confirm('
												+ '\'Are you sure you want to delete this participant?\''
												+ ');" class="btn btn-danger custom-width" ><span style="color: #ffffff;" class="glyphicon glyphicon-remove"></span></a>';
									}
									return str;
								}
							} ]
				});
	}
	



})