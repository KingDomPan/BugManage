Ext.define('AM.view.TesterCompleteWindow',{
    extend:'Ext.window.Window',
    alias:'widget.testercompletewindow',     //别名
    id:'testercomplet_window',
    title:'完善Bug',
    width:500,
    height:500,
    closable: true,
    closeAction: 'hide',
    animateTarget:'tester_compelete_btn',
    items:[{
    	xtype:'form',
    	defaultType:'textfield',
    	waitMsgTarget:true,
    	defaults:{
    	    allowBlank : false, 
    	    msgTarget : 'side',
    	    padding:'10 10 10 10',
    	    labelSeparator:':',
    	    anchor:'80%',
    	    labelWidth:68
    	},
    	items:[{
    		xtype:'hiddenfield',
    		name:'id',
    		hidden:true
    	},{
    		fieldLabel:'BUG编号',
    		readOnly:true,
    		name:'bugId'
    	},{
    		fieldLabel:'BUG标题',
    		name:'bugTitle'
    	},{
    		xtype:'combobox',
    	    fieldLabel:'Bug严重度',
    	   	name:'bugSeverity',
    	   	editable:false, //禁止手写及联想功能
      	    displayField:'name',
      	    store:'BugSeverityStore'
    	},{
    		xtype:'hiddenfield',
    		name:'projectid',
    		hidden:true
    	},{
    		xtype:'combobox',
    	    fieldLabel:'所属项目',
    	   	name:'projectName',
    	   	displayField:'projectName',
    	   	valueField:'id',
    	   	autoScroll:true,
        	store:'ProjectStore',
        	pageSize:10,
        	listConfig:{        //控制
                emptyText:'没有找到匹配的数值..',  
                maxHeight:200,    //超过会出现滚动条  
                loadingText:'正在加载...',
                getInnerTpl:function(){ //模版功能
                    return "<div>{projectId}.{projectName}</div>"
                 }
            }	
    	},{
			xtype:'hiddenfield',
    		name:'modularid',
    		hidden:true
		},{
    		xtype:'combobox',
    	    fieldLabel:'所属模块',
    	   	name:'modularName',
    	   	listConfig:{        //控制
                emptyText:'没有找到匹配的数值..',  
                maxHeight:200,    //超过会出现滚动条  
                loadingText:'正在加载...',
                getInnerTpl:function(){ //模版功能
                    return "<div>{modularId}.{modularName}</div>"
                 }
            },
        	store:'ModularStore',
        	typeAhead:true, 
        	valueField:'id',
        	displayField:'modularName'
    	},{
    		xtype:'hiddenfield',
    		name:'versionId',
    		hidden:true
    	},{
    		xtype:'combobox',
			fieldLabel:'项目版本',
			editable:false,
			name:'versionName',
			listConfig:{        //控制
                emptyText:'没有找到匹配的数值..',  
                maxHeight:200,    //超过会出现滚动条  
                loadingText:'正在加载...',
                getInnerTpl:function(){ //模版功能
                    return "<div>{versionId}.{versionName}</div>"
                 }
            },
	    	store:'ProjectVersionStore',
	    	valueField:'versionId',
	    	displayField:'versionName'
    	},{
    		xtype:'combobox',
    		fieldLabel:'运行环境',
    	   	name:'bugEnvironment',
    	   	editable:false, //禁止手写及联想功能
      	    displayField:'name',
      	    store:'BugEnvironmentStore'
    	},{
    		fieldLabel:'期望效果',
			name:'bugExpected'
    	},{
    	    fieldLabel:'运行效果',
			name:'bugResult'
    	},{
    		xtype:'textareafield',
    		fieldLabel:'详细描述',
    	   	name:'bugDetail'
    	}],
    	buttons:[{
    	    text:'提交',
    	    id:'compelete_submit'
    	},{
    	    text:'重置',
    	    id:'complete_reset'
    	}]
    }]  
})