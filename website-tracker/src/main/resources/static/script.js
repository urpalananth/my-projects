(function(angular) {
  'use strict';
angular.module('webSiteTracker', ['ngRoute', 'ngResource'])

 .controller('MainController', function($scope, $route, $routeParams, $location, $http, $rootScope) {
	 $scope.error = "";
	 
	 $http({
		  method: 'GET',
		  url: '/urls/top3'
		}).then(function successCallback(response) {
			$scope.urls = response.data;
		  }, function errorCallback(response) {
		});
	  
	 
	 
	 $scope.postUrl= function(){
		  $scope.error = "";
    	 console.log($scope.url);
    	 var reqBody = {"url" : $scope.url};
    	 
  		$http({
  		  method: 'POST',
  		  url: '/urls',
  		  data: reqBody
  		}).then(function successCallback(response) {
  			$scope.message = "";
			$http({
				  method: 'GET',
				  url: '/urls/top3'
				}).then(function successCallback(response) {
					$scope.urls = response.data;
				  }, function errorCallback(response) {
				});
  		  }, function errorCallback(response) {
  			  $scope.error = response.status +" Invalid URL";
  			  console.log(response.status);
  		});
     }
	  
	 $scope.showError = function(){
		 if($scope.error == "")
			 return false;
		 else
			 return true;
	 }
 })
 
})(window.angular);