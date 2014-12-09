Ext.define('AM.view.TestBugTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.testbugtab',     //别名
    requires:([
        'Ext.ux.RowExpander'
    ]),
    id:'testbug_tabpanel',
    title:'待测试Bug',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	title:'待测试Bug信息',
    	flex:1,
    	layout:'border',  	
    	tbar:[{
    		text:'通过',
    		id:'tester_passed_btn'	
        },{
        	text:'退回',
        	id:'tester_back_btn'
        },'-',{
    		xtype:'label',
    		text:'项目查询:'     
    	},'-',{
    	    xtype:'combobox',   
    	    width:100,
    	    emptyText:'选择查询字段'	,
    	    editable : false,
    	    id:'project_search_type',  
        	store:new Ext.data.Store({
        		fields:['value','text'],
        		data:[
        			{value:'ProjectId',text:'项目编号'},
        			{value:'ProjectName',text:'项目名称'},
        			{value:'ModularId',text:'模块编号'},
        			{value:'ModularName',text:'模块名称'}
        		]
        	}),
        	valueField:'value',
        	displayField:'text'
    	},'-',{
    	    xtype:'textfield',
    	    id:'project_search_value',
    	    emptyText:'请输入查询内容',
    	    width:200
    	},'-',{
    		text:'查询',
    		id:'project_search_submit'
    	}],
    	items:[{
    		xtype:'gridpanel',
    		id:'test_bug_grid',
    		autoScroll:true,
    		region:'center',
    		viewConfig: {
                plugins: {
                    ddGroup: 'GridExample',
                    ptype: 'gridviewdragdrop',
                    enableDrop: false
                }
            },
    		loader:{
    			loadMask:{msg:'正在加载数据，请稍候....'}
    		},
    		store:'ToBeCheckedBugStore',	  
    		enableDragDrop:true,
            stripeRows:true,
    		columns:[
    		     {text:'BugId',dataIndex:'bugid',hidden:true},
    		     {text:'Bug编号',dataIndex:'bugId'},
    		     {text:'Bug标题',dataIndex:'bugTitle'},
    		     {text:'项目编号',dataIndex:'projectId'},
    		     {text:'所属项目',dataIndex:'projectName'},
    		     {text:'模块编号',dataIndex:'modularId'},
    		     {text:'所属模块',dataIndex:'modularName'},
    		     {name:'修复Id',dataIndex:'rid',hidden:true},
                 {text:'修复人员id',dataIndex:'developerid',hidden:true},
                 {text:'修复人员编号',dataIndex:'developerId',hidden:true},
                 {text:'修复人员',dataIndex:'developerName'},
    		     {text:'提交日期',dataIndex:'submitedDay4'}
    		],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl : [
    	               '<p><b>修复报告:</b>{repairScheme}</p>'
    	         ]
    		}],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ToBeCheckedBugStore',
                pageSize:15,
                dock:'bottom',     //定位
                displayInfo:true
             }],
    	    selType:'rowmodel',
    	    multiSelect:false, 
    	    enableKeyNav:true
    	},{
    		region:'east',
    		xtype:'form',
    		collapsible:true,
    		autoScroll:true,
    		title:'BUG退回',
    		id:'test_back_form',
    		split:true,
    		width:300,
    		collapsed:true,  //默认收缩
    		defaultType:'textfield',
    		defaults:{
    	   	    allowBlank : false, 
    		    msgTarget : 'side',
    		    padding:'10 10 10 10',
    		    labelSeparator:':',
    		    labelWidth:80,
    		    selectOnFocus:true
    	   	},
    	   	waitMsgTarget:true,
    	  	items:[{
    	  		name:'bugid',
    	  		hidden:true
    	  	},{
    			fieldLabel:'BUG编号',
    			readOnly:true,
    		    name:'bugId'
    		},{
    			fieldLabel:'BUG标题',
    			readOnly:true,
    		    name:'bugTitle'
    		},{
    			name:'rid',
    			hidden:true
    		},{
    			fieldLabel:'修复人员',
    			readOnly:true,
    		    name:'developerName'
    		},{
    		    xtype:'textarea',
    		    fieldLabel:'退回原因',
    		    name:'reasonDetail'
    		}],
    		buttons:[{
    		    text:'提交',
    		    id:'test_result_submit'
    		},{
    		    text:'重置',
    		    id:'test_result_reset'
    		}]
    	}]
    }]
})