// CONTROLLERS
camApp.controller('homeController', [
		'$scope',
		'$http',
		function($scope, $http) {

			$scope.columnDefs = [ {
				"mDataProp" : "asset",
				"aTargets" : [ 0 ]
			}, {
				"mDataProp" : "class",
				"aTargets" : [ 1 ]
			}, {
				"mDataProp" : "model",
				"aTargets" : [ 2 ]
			}, {
				"mDataProp" : "owner",
				"aTargets" : [ 3 ]
			}, {
				"mDataProp" : "created",
				"aTargets" : [ 4 ]
			}, {
				"mDataProp" : "action",
				"aTargets" : [ 5 ]
			} ];

			$scope.overrideOptions = {
				"bStateSave" : true,
				"iCookieDuration" : 2419200, /* 1 month */
				"bJQueryUI" : true,
				"bPaginate" : true,
				"bLengthChange" : false,
				"bFilter" : true,
				"bInfo" : true,
				"bDestroy" : true
			};
			
			$scope.assetList = [];

//			$http.get('resources/asset.json').then(function(response) {
//				$scope.assetList = $scope.formatAssetListTable(response.data);
//			});
			
			$scope.classList1 = [ {
				"className" : "Root",
				"classId" : "root",
				"children" : [ {
					"className" : "Example Class",
					"classId" : "exclass",
					"children" : []
				} ]
			} ];

			// roleList1 to treeview
			$scope.classList = $scope.classList1;
			
			
			//funzioni di utilità
			
			$scope.loadAsset = function(){
//				alert($scope.currentNode); //per recuperare il nodo da passare in input a servizio rest
				if($scope.currentNode.classId == 'exclass'){
					AssetManager.createAssets($http);
                    $scope.assetList = AssetManager.getAssets();
                    console.log($scope.assetList);
				}else{
				    $scope.assetList=[]
				}
			}
			
			$scope.formatAssetListTable = function(data){
				if(!data)
					return [];
				for(var i = 0; i <data.length; i++){
					data[i].action = '<div><i data-toggle="tooltip" title="Delete asset model" class="fa fa-remove cam-table-button"></i><i data-toggle="tooltip" title="Open detail" class="fa fa-search cam-table-button"></i>';
					if(data[i].isModel == 'true')
						data[i].action +='<i data-toggle="tooltip" title="Create new asset from this model" class="fa fa-plus cam-table-button"></i></div>';
				}
				return data;
			};
		} ]);

