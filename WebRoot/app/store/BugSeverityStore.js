Ext.define('AM.store.BugSeverityStore',{
    extend:'Ext.data.Store',
    fields : [ {
		name : 'name'
	}, // xml子节点
	{
		name : 'id'
	} ],
	proxy : {
		type : 'ajax',
		url : 'xmldata/BugSeverity.xml',
		reader : {
			type : 'xml',
			record : 'bugSevertity'
		}
	}
});