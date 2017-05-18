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
	escreve(event.data);
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

