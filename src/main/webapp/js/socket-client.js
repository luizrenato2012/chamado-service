var socket ;

(function() {
	console.log('Aguardando conexao...');
	escreve('Aguardando conexao...');
	
	socket = new WebSocket("ws://10.0.20.98:8080/chamado-service/notificacao");
	escreve('Conexao iniciada');
})();

function conectar() {
	console.log('Tentando conectar ...');
	//var divStatus = document.querySelector('#div_status');
	
	if (!navigator.onLine){
		console.log('Navegador offline...');
		
		//divStatus.innerHTML = 'Navegador offline...';
		escreve('Navegador offline...');
		return;
	}
}

socket.onopen = function(event){
	console.log('Iniciando conexao');
	//var divStatus = document.querySelector('#div_status');
	escreve('Iniciando conexao');
}

function envia() {
	var mensagem = document.querySelector('#mensagem').value;
	console.log('Enviando mensagem '+ mensagem);
	escreve('Enviando mensagem: '+ mensagem)
	socket.send(mensagem);
}

socket.onmessage = function (event){
	console.log('Mensagem recebida: '+ event.data);
//	escreve(event.data);
	atualiza(event.data);
}

var atualiza = function(data) {
	var table = document.querySelector('table');
	var registros = JSON.parse(data);
	var registro={};
	var linhas = '';
	
	if (registros.length > 0){
		table.innerHTML = '<tr>	<th>Num.</th> <th>Data</th> <th>Equipamento</th> <th>Marca</th> <th>Modelo</th> <th>Situação</th></tr>';
	}
	for (var i=0; i < registros.length ;i++) {
		registro = registros[i];
		console.log(registro);
		linhas+='<tr><td>'+ registro.id + '</td><td>' + registro.dataCriacao+ '</td><td>' + registro.equipamento +'</td><td>' +
				 registro.marca + '</td><td>'+ registro.modelo +'</td><td>'+ registro.situacao+'</td></tr>';
	}
	table.innerHTML+=linhas;
	
}

socket.onclose = function(event) {
	console.log('Conexao fechada');
	escreve('Conexao fechada');
}

socket.onerror = function (event){
	console.log('Erro ao conectar');
	escreve('Erro ao conectar');
}

function escreve(msg) {
	var data = new Date();
	var dataStr = '['+ data.getHours() + ':' + data.getMinutes() + ':'+ data.getSeconds() +'] ';
	var divStatus = document.querySelector('#div_status');
	divStatus.innerHTML = divStatus.innerHTML+'<br/>' + dataStr + msg;
}

