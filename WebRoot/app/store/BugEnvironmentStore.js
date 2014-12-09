Ext.define('AM.store.BugEnvironmentStore',{
    extend:'Ext.data.Store',
    fields : [ {
		name : 'name'
	}, // xml子节点
	{
		name : 'id'
	} ],
	proxy : {
		type : 'ajax',
		url : 'xmldata/BugEnvironment.xml',
		reader : {
			type : 'xml',
			record : 'bugEnvironment'
		}
	}
});