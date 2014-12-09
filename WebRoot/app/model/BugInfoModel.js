Ext.define('AM.model.BugInfoModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		type : 'auto'
	},
	{
		name : 'bugid',
		type : 'auto'
	},
	{
		name : 'bugId',
		type : 'string'
	}, {
		name : 'bugTitle',
		type : 'string'
	}, {
		name : 'bugDetail',
		type : 'string'
	}, {
		name : 'stateName',
		type : 'string'
	}, {
		name : 'projectid',
		type : 'auto'
	},{
		name : 'projectId',
		type : 'auto'
	}, {
		name : 'projectName',
		type : 'string'
	}, {
		name : 'modularid',
		type : 'auto'
	}, {
		name : 'modularId',
		type : 'auto'
	}, {
		name : 'modularName',
		type : 'string'
	}, {
		name : 'bugEnvironment',
		type : 'string'
	}, {
		name : 'bugSeverity',
		type : 'string'
	}, {
		name : 'submitedDay',  //bug提交日期
		type : 'auto'
	}, {
		name : 'submitedDay2',  //bug分配日期
		type : 'auto'
	},{
		name : 'submitedDay3', //bug退回日期
		type : 'auto'
	},{
		name : 'submitedDay4',  //拒绝日期
		type : 'auto'
	},{
		name : 'submitedDay5',   //修复日期
		type : 'auto'
	},{
		name : 'submitedDay6',   //测试不通过日期
		type : 'auto'
	},{
		name : 'bugExpected',
		type : 'string'
	}, {
		name : 'bugResult',
		type : 'string'
	}, {
		name : 'testerId',
		type : 'auto'
	},{
		name : 'testerid',
		type : 'auto'
	},{
		name : 'testerName',
		type : 'string'
	},{
		name:'taskid',
		type : 'auto'
	},{
		name : 'versionId',
		type : 'auto'
	}, {
		name : 'versionName',
		type : 'string'
	}, {
		name : 'testerName',
		type : 'string'
	}, {
		name : 'manager',
		type : 'string'
	}, {
		name : 'reasonDetail',
		type : 'string'
	},{
		name : 'bugPriority',
	    type : 'string'
	},{
		name : 'remark',
	    type : 'string'
	},{
		name :'managerId',
		type : 'string'
	},{
		name :'managerName',
		type : 'string'
	},{
		name:'developerid',
		type:'auto'
	},{
		name:'developerId',
		type:'auto'
	},{
		name:'developerName',
		type:'string'
	},{
		name:'rid',
		type:'auto'
	},{
		name:'taskReasonType',
		type:'auto'
	},{
		name:'reasonDetail',
		type:'string'
	},{
		name:'repairReason',
		type:'string'
	},{
		name:'repairScheme',
		type:'string'
	},{
		name:'repairDetail',
		type:'string'
	}]
});