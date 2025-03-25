var exec = require('cordova/exec');

module.exports = {
    get: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'BarsMargin', 'get');
    }
};