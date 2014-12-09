Ext.define('AM.store.ModularInfoStore', {
	extend : 'Ext.data.Store',
	model:'AM.model.ProjectInfoModel',
	proxy : {
		type : 'ajax',
		url : 'modular/getModularsById.action',
		reader : {
			type : 'json'
		}
	}
});