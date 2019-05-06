function initDatatableOnList(){
	medidorTableInit=false;
	cTable=null;
	$.ajax('../ajax/getMedidorList.do',{
		success: function(data) {
			refreshMedidorTable(data)
		},
		error: function(){
			exceptionHandler
		}
	});

	//mostrarMensajes();
	var mensaje = $('#mensaje').val();
	if (mensaje != ''){
		alert(mensaje);
	}
}

function exceptionHandler(){
	refreshFamilyPageTable([]);
	alert('Error')
}

function refreshMedidorTable(data) {
	console.log("START refreshMedidorTable");
	var object = JSON.parse(data);
	if (medidorTableInit == false) {
		medidorTableInit = true;
		cTable = $('#MedidorTable').DataTable({
			data : object,
			columns : [ {
				data : 'medidorId'
			}, {
				data : 'fechaInclusionFmt'
			}, {
				data : 'fechaUltimaLecturaFmt'
			}, {
				data : 'ultimaLectura'
			}, {
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 4,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					return '<a href="' + getOrigen() +'/web/mostrarMedidor.do?medidorId='+ full.medidorId + '"><img border="0" alt="Detalle" src="../images/magnifier.png" width="20" height="20"> </a>';
				}
			}],
			language: {emptyTable: 'Medidores no encontradas'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#MedidorTable').show();
		console.log("INIT refreshMedidorTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshMedidorTable");
	}
	console.log("FINISH refreshMedidorTable");
}
