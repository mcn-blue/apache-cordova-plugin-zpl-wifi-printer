var exec = require('cordova/exec');

exports.find = function (arg0, success, error) {
    exec(success, error, 'ZplWifiPrinter', 'find', [arg0]);
};

exports.print = function (arg0, success, error) {
    exec(success, error, 'ZplWifiPrinter', 'print', [arg0]);
};

