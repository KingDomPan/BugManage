Ext.define('AM.view.TesterCompleteBugTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.testercompletebugtab',     //别名
    requires:([
        'Ext.ux.RowExpander'
    ]),
    id:'testercompletebug_tabpanel',
    title:'待完善BUG',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	title:'待完善Bug信息',
    	flex:1,
    	layout:'border',  	
    	tbar:[{
    		text:'完善',
    		id:'tester_compelete_btn'
    	}],
    	items:[{
    		xtype:'gridpanel',
    		id:'compelete_bug_grid',
    		autoScroll:true,
    		region:'center',
    		viewConfig: {
                plugins: {
                    ddGroup:'GridExample',
                    ptype: 'gridviewdragdrop',
                    enableDrop: false
                }
            },
    		loader:{
    			loadMask:{msg:'正在加载数据，请稍候....'}
    		},
    		store:'ToBeCompleteBugStore',	  
    		enableDragDrop:true,
            stripeRows:true,
    		columns:[{
    			text:'BugId',dataIndex:'id',hidden:true
    		},{
    		    text:'Bug编号',dataIndex:'bugId'
    		},{
    		    text:'Bug标题',dataIndex:'bugTitle'
    		},{
    		    text:'Bug严重度',dataIndex:'bugSeverity'
    		},{
    		    text:'运行环境',dataIndex:'bugEnvironment'
    		},{
    		    text:'项目编号',dataIndex:'projectid',hidden:true
    		},{
    		    text:'项目Id',dataIndex:'projectId',hidden:true
    		},{
    		    text:'所属项目',dataIndex:'projectName'
    		},{
    		    text:'模块Id',dataIndex:'modularId',hidden:true
    		},{
    		    text:'模块编号',dataIndex:'modularid',hidden:true
    		},{
    		    text:'所属模块',dataIndex:'modularName'
    		},{
    		    text:'项目版本Id',dataIndex:'versionId',hidden:true
    		},{
    		    text:'项目版本',dataIndex:'versionName'
    		},{
    		    text:'bug提交日期',dataIndex:'submitedDay'
    		}],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl : [
                       '<p>-------------bug报告--------------</p>',
                       '<p><b>期望效果: </b>{bugExpected}</p>',  
                       '<p><b>运行效果: </b>{bugResult}</p>',
                       '<p><b>Bug详细描述: </b>{bugDetail}</p>',           
                       '<p>-------------bug退回详情--------------</p>',
                       '<p><b>退回经理: </b>{managerName}</p>',
                       '<p><b>退回日期: </b>{submitedDay3}</p>',
                       '<p><b>退回理由: </b>{reasonDetail}</p>'
    	         ]
    		}],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ToBeCompleteBugStore',
                pageSize:10,
                dock:'bottom',     //定位
                displayInfo:true
             }],
    	    selType:'rowmodel',
    	    multiSelect:false, 
    	    enableKeyNav:true
    	}]
    }]
})