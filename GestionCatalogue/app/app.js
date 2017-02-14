var app = angular.module("catalogue", []);
var url = "http://localhost:8080/LeBonSandwich/api/";

app.controller("BaseController", ['$scope', "$http", function($scope, $http) {

    var recupererIngredients = function(){
        $http.get(url+"ingredients").then(
            function(i){
                var listeIngredients = '<ul class="collection">';
                i.forEach(function(ingredient){
                    listeIngredients += '<li class="collection-item"><h5>'+ingredient.nom+'</h5>'+ingredient.categorie.nom+'</li>';
                });
                $scope.listeIngredients = listeIngredients+'</ul>';
            },
            function(e){
                console.log(e);
            }
        )
    };

    var init = function(){
        $('.collapsible').collapsible();

        recupererIngredients();
    };

    init();
}]);

