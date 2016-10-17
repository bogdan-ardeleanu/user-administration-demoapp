app.controller('CustomerController', function ($scope, $rootScope, $resource, $http, $sce, $routeParams, CustomerService) {
    var page2 = $sce.trustAsHtml('<p><b>Pagina 2. Vizualizare conturi</b></p><p>A doua pagina contine numele si conturile clientului ce are ID-ul introdus in prima pagina. Din aceasta pagina se poate selecta un singur cont, ale carui detalii vor fi afisate userului in a treia pagina.</p>');

    $rootScope.pageDescription = page2;

    $scope.customer = null;

    loadCustomerDetails();


    // I load the remote data from the server.
    function loadCustomerDetails() {
        CustomerService.getDetails().then(function (data) {
            $scope.customer = data.customer;
        });
    }
});


app.controller('AccountController', function ($scope, $rootScope, $resource, $http, $sce, $routeParams, CustomerService) {
    var page3 = $sce.trustAsHtml('<p><b>Pagina 3. Operatiuni bancare</b></p><p>A treia pagina contine numarul si soldul contului selectat in pagina precedenta. Aici userul poate introduce o suma si poate selecta o actiune: retragere sau alimentare. Userului i se reincarca pagina (a treia) in care se poate vizualiza noul sold al contului.</p>');

    $rootScope.pageDescription = page3;

    $scope.currentAccount = null;

    loadCurrentAccount($routeParams.id);

    $scope.operationResponse = null;

    // I load the remote data from the server.
    function loadCurrentAccount(id) {
        CustomerService.getAccount(id).then(function (data) {
            $scope.currentAccount = data;
        });
    }

    $scope.withdrawal = function (id, amount) {
        CustomerService.withdrawal(id, amount).then(function (data) {
            $scope.operationResponse = data;

            if(data.success) {
                loadCurrentAccount($routeParams.id);
            }
        });
    };

    $scope.deposit = function deposit(id, amount) {
        CustomerService.deposit(id, amount).then(function (data) {
            $scope.operationResponse = data;

            if(data.success) {
                loadCurrentAccount($routeParams.id);
            }
        });
    }
});


