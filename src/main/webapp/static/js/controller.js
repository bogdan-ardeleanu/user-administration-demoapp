app.controller('CustomerController', function ($scope, $resource, $http, CustomerService) {
    $scope.headingTitle = "Customer Details";

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
});
