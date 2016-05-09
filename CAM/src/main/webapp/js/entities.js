"use strict";
var AssetManager = (function(){
		var $http = null;
        var assets = [];
		
        var createAssets = function($httpExt){
            $http = $httpExt;
            $http.get('http://localhost:8080/CAMService/assets')
                //TODO Address
            .success(function (data) {
                create(data);
            })
            .error(function() { 
				console.log("Error encountered :-("); 
			});
            
    	}
        
        var getAssets = function(){
            return assets;
        }
		
        var create = function(data){
            for (var i in data){
                var asset  = {
                    asset: data[i].normalizedName,
                    class: data[i].className,
                    model: data[i].individualName,
                    owner: '',
                    created: '2016-06-01',
                    isModel: true,
                    action :'x'
                }   
                assets.push(asset);
            }
            
        }
		
	    var reset = function(){
	    	$http = null;
        }
	    
		//Costructor
		var AssetManager = function() {
           	reset();
		}
		
	    AssetManager.prototype = {
	       //constructor
	    	constructor : AssetManager,
	    	reset: reset,
            createAssets: createAssets,
            getAssets: getAssets
	   	 }
	    return AssetManager;
	})();
	
	var AssetManager = new AssetManager();

