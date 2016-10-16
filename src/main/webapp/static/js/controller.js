app.controller('CustomerController', function ($scope, $resource, $http, $sce, $routeParams, CustomerService) {
    var page2 = $sce.trustAsHtml('<p><b>Pagina 2. Vizualizare conturi</b></p><p>A doua pagina contine numele si conturile clientului ce are ID-ul introdus in prima pagina. Din aceasta pagina se poate selecta un singur cont, ale carui detalii vor fi afisate userului in a treia pagina.</p>');

    $scope.pageDescription = page2;

    $scope.customer = null;

    loadCustomerDetails();

    // I apply the remote data to the local scope.
    function applyCustomerDetails(customer) {
        $scope.customer = customer;
    }

    // I load the remote data from the server.
    function loadCustomerDetails() {
        CustomerService.getDetails().then(function (data) {
            applyCustomerDetails(data.customer);
        });
    }

    $scope.editAccount = function (id) {
        console.log('Account ID', id);
    }
});


app.controller('AccountController', function ($scope, $resource, $http, $sce, $routeParams, CustomerService) {
    var page3 = $sce.trustAsHtml('<p><b>Pagina 3. Operatiuni bancare</b></p><p>A treia pagina contine numarul si soldul contului selectat in pagina precedenta. Aici userul poate introduce o suma si poate selecta o actiune: retragere sau alimentare. Userului i se reincarca pagina (a treia) in care se poate vizualiza noul sold al contului.</p>');

    $scope.pageDescription = page3;

    $scope.currentAccount = loadCurrentAccount($routeParams.id);

    // I apply the remote data to the local scope.
    function applyCurrentAccount(account) {
        $scope.currentAccount = account;
    }

    // I load the remote data from the server.
    function loadCurrentAccount(id) {
        CustomerService.getAccount(id).then(function (data) {
            applyCurrentAccount(data);
        });
    }
});


