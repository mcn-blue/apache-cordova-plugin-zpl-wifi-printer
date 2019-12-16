# Zpl printer plugin for Apache Cordova

This plugin enables communication between Zebra Printers and Apache cordova Apps


# Supported Platforms 

- Android

# Installing

	https://github.com/mcn-blue/apache-cordova-plugin-zpl-wifi-printer.git

## Switch to another file

All your files and folders are presented as a tree in the file explorer. You can switch from one to another by clicking a file in the tree.

## Methods

- find
- print

## Examples
find
- ```js
	var successCallback=function(result){
		console.log(result.printers);
	}
	cordova.plugins.ZplWifiPrinter.find([], successCallback,errorCallback);
	```		
print
- ```js
	var config={"ip":ipPrinter,"zpl":zplString};
	cordova.plugins.ZplWifiPrinter.print(config,successCallback,errorCallback);
	```

## License
[Apache 2.0](https://opensource.org/licenses/Apache-2.0):

