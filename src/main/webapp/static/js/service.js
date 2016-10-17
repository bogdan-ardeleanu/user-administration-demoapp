app.factory("CustomerService", function ($http, $q) {
    // Return public API.
    return ({
        getDetails: getDetails,
        getAccount: getAccount,
        withdrawal: withdrawal,
        deposit: deposit
    });
    // ---
    // PUBLIC METHODS.
    // ---
    // I add a friend with the given name to the remote collection.
    function getDetails() {
        var request = $http({method: 'get', url: 'customer/details'});
        return ( request.then(handleSuccess, handleError) );
    }

    function getAccount(id) {
        var request = $http({method: 'get', url: 'customer/account/' + id});
        return ( request.then(handleSuccess, handleError) );
    }

    function withdrawal(id, ammount) {
        var request = $http({
            method: 'post',
            url: 'customer/account/' + id + '/withdrawal',
            params: {amount: ammount}
        });
        return ( request.then(handleSuccess, handleError) );
    }

    function deposit(id, ammount) {
        var request = $http({
            method: 'post',
            url: 'customer/account/' + id + '/deposit',
            params: {amount: ammount}
        });
        return ( request.then(handleSuccess, handleError) );
    }

    // I transform the error response, unwrapping the application dta from
    // the API response payload.
    function handleError(response) {
        // The API response from the server should be returned in a
        // nomralized format. However, if the request was not handled by the
        // server (or what not handles properly - ex. server error), then we
        // may have to normalize it on our end, as best we can.
        if (
            !angular.isObject(response.data) || !response.data.message
        ) {
            return ( $q.reject("An unknown error occurred.") );
        }
        // Otherwise, use expected error message.
        return ( $q.reject(response.data.message) );
    }

    // I transform the successful response, unwrapping the application data
    // from the API response payload.
    function handleSuccess(response) {
        return ( response.data );
    }
});