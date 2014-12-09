Ext.define('AM.store.ModularStore', {
	extend : 'Ext.data.Store',
	proxy : {
		type : 'ajax',
		url : 'modular/getModulars.action',
		reader : {
			type : 'json'
		}
	},
	fields : [ {
		name:'id',
		type:'int'
	}, {
		name : 'modularId'
	}, {
		name : 'modularName'
	} ]
});