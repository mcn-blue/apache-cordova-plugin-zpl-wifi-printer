var exec = require('cordova/exec');

exports.find = function (success, error) {
    exec(success, error, 'ZplWifiPrinter', 'find', []);
};

exports.print = function (arg0, success, error) {
    exec(success, error, 'ZplWifiPrinter', 'print', [arg0]);
};

