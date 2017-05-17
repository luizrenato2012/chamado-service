var myApp = angular.module('chamadoApp', []);

myApp.controller('ChamadoController',['$scope','$http',function($scope, $http){
	$scope.chamado={};
		
	$scope.inicializa = function() {
		$scope.chamado.cpf='111111-11';
		$scope.chamado.equipamento='Equipamento ';
		$scope.chamado.marca = 'Marca'
		$scope.chamado.modelo='Modelo'
		$scope.chamado.numeroSerie = '123456';
		$scope.chamado.defeito = 'Defeito';
	}
	
	$scope.inicializa();
	$scope.listaChamado=[];
	
	$scope.lista = function  () {
		console.log ('listando ...');
		$scope.chamados = [];
		console.log($scope.chamado);
		$http.get('app/chamado/todos',null).
			success(function(data, status, headers, config){
				console.log('retorno '+ data);
				$scope.chamados = data;
			}).
			error(function(data, status, headers, config){
				console.log('Erro ao listar '+ data);
			});
	}
	$scope.lista();

	$scope.grava = function () {
		var chamado = {};
		chamado.cpf = 
		$http.post('app/chamado/', $scope.chamado).
			success(function(data, status, headers, config){
				console.log('Chamado gravado com sucesso'+ data);
				$scope.pessoa={};
				$scope.inicializa();
			}).
			error(function(data, status, headers, config){
				console.log('Erro '+ data);
			});
		console.log('gravando '+ $scope.chamado);
		
	}
}]);