function conftabelasimples(tabela) {
	$(document).ready(function() {
		$(tabela).DataTable({
			"language" : {
				"lengthMenu" : "exibir _MENU_ linhas por página",
				"zeroRecords" : "sem resultados",
				"info" : "exibindo página(s) _PAGE_ de _PAGES_",
				"infoEmpty" : "sem resultados",
				"infoFiltered" : "(filtranto _MAX_ de records)",
				"paginate" : {
					"previous" : "anterior",
					"next" : "próxima"
				},
				search : "_INPUT_",
				searchPlaceholder : "buscar..."
			}
		});
	});
};