Ext.define('AM.store.ProjectStore', {
	extend : 'Ext.data.Store',
	pageSize : 15,
	proxy : {
		type : 'ajax',
		url : 'project/getProjects.action',
		reader : {
			type : 'json',
			root : 'topics',
			totalProperty : 'totalCount'
		}
	},
	fields : [ {
		name:'id',
		type:'int'
	}, {
		name : 'projectId'
	}, {
		name : 'projectName'
	} ]
});