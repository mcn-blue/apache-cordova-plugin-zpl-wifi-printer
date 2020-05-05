# Zpl printer plugin for Apache Cordova

This plugin enables communication between Zebra Printers and Apache cordova Apps through ip address


# Supported Platforms 

- Android

# Installing

	cordova plugin add https://github.com/mcn-blue/apache-cordova-plugin-zpl-wifi-printer.git

## Methods

- find
- print

## Examples
find
- ```js
	var successCallback=function(result){
		console.log(result.printers);
	}
	cordova.plugins.ZplWifiPrinter.find(successCallback,errorCallback);
	```		
print
- ```js
	var config={"ip":ipPrinter,"zpl":zplString};
	cordova.plugins.ZplWifiPrinter.print(config,successCallback,errorCallback);
	```

## License
[Apache 2.0](https://opensource.org/licenses/Apache-2.0):
