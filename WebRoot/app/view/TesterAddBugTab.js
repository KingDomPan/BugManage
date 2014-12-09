Ext.define('AM.view.TesterAddBugTab', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.testeraddbugtab', // 别名
	id : 'addbugtabpanel',
	title : '新增BUG',
	layout : 'border',
	closable : true,
	items : [ {
		xtype : 'form',
		region : 'center',
		defaultType : 'textfield',
		waitMsgTarget : true,
		defaults : {
			allowBlank : false,
			msgTarget : 'side',
			padding : '10 10 10 90',
			labelSeparator : ':',
			anchor : '80%',
			labelWidth : 68
		},
		items : [ {
			padding : '60 10 10 90',
			fieldLabel : '*BUG标题',
			emptyText:'-----------------------请输入bug标题---------------------',
			name : 'bugTitle'
		}, {
			xtype : 'combobox',
			fieldLabel : '*Bug严重度',
			name : 'bugSeverity',
			emptyText:'-----------------------请选择bug严重度--------------------',
			editable : false,	
			displayField : 'name',
			store : 'BugSeverityStore'
		}, {
			xtype : 'combobox',
			fieldLabel : '*所属项目',
			editable : false,
			name : 'projectId',
			displayField : 'projectName',
			emptyText:'-----------------------请选择bug所属项目-----------------------',
			valueField : 'id',
			autoScroll : true,
			store : 'ProjectStore',
			pageSize : 10,
			listConfig : { // 控制
				emptyText : '没有找到匹配的数值..',
				maxHeight : 200, // 超过会出现滚动条
				loadingText : '正在加载...',
				getInnerTpl : function() { // 模版功能
					return "<div>{projectId}.{projectName}</div>"
				}
			}
		}, {
			xtype : 'combobox',
			fieldLabel : '*所属模块',
			editable : false,
			name : 'modularId',
			emptyText:'------------------------请选择bug所属模块-----------------------',
			listConfig : { // 控制
				emptyText : '没有找到匹配的数值..',
				maxHeight : 200, // 超过会出现滚动条
				loadingText : '正在加载...',
				getInnerTpl : function() { // 模版功能
					return "<div>{modularId}.{modularName}</div>"
				}
			},
			store : 'ModularStore',
			valueField : 'id',
			displayField : 'modularName'
		}, {
			xtype : 'combobox',
			fieldLabel : '*项目版本',
			editable : false,
			name : 'versionId',
			emptyText:'-------------------------请选择bug所属版本-----------------------',
			listConfig : { // 控制
				emptyText : '没有找到匹配的数值..',
				maxHeight : 200, // 超过会出现滚动条
				loadingText : '正在加载...',
				getInnerTpl : function() { // 模版功能
					return "<div>{versionId}.{versionName}</div>"
				}
			},
			store : 'ProjectVersionStore',
			valueField : 'versionId',
			displayField : 'versionName'
		}, {
			xtype : 'combobox',
			fieldLabel : '*运行环境',
			name : 'bugEnvironment',
			emptyText:'-------------------------请选择bug运行环境------------------------',
			displayField : 'name',
			store :'BugEnvironmentStore'		
		}, {
			fieldLabel : '*期望效果',
			name : 'bugExpected',
			emptyText:'-------------------------请选择bug期望效果------------------------',
		}, {
			fieldLabel : '*运行效果',
			name : 'bugResult',
			emptyText:'--------------------------请选择bug运行效果-----------------------',
				
		}, {
			xtype : 'textareafield',
			fieldLabel : '*详细描述',
			name : 'bugDetail',
			height : 100
		} ],
		buttons : [ {
			text : '提交',
			id : 'bug_add_submit'
		}, {
			text : '重置',
			id : 'bug_add_reset'
		} ]
	}, {
		xtype : 'panel',
		region : 'east',
		id:'bug_tip_panel',
		width : 360,
		title : '提示',
		icon:'image/bug_error.png',
		autoLoad:{
			url:'BugTip.html',
			discardUrl: false,  
	        scripts: true  
	    }
	} ]
})