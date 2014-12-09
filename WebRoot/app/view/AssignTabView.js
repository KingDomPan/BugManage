Ext.define('AM.view.AssignTabView',{
    extend:'Ext.panel.Panel',
    alias:'widget.assigntabview',     //别名
    requires:([
        'Ext.ux.RowExpander'
    ]),
    id:'assigntabpanel',
    title: '待分配Bug',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	title:'待分配Bug信息',
    	flex:1,
    	layout:'border',  	
    	tbar:[{
    		xtype:'splitbutton',
    		text:'操作类型',
    		id:'operate_splitbtn',
    		menu:{
    			items:[
    			   {xtype:'menucheckitem',text:'分配',group:'scale',id:'assign_menuchekitem'},
    			   {xtype:'menucheckitem',text:'退回',group:'scale',id:'back_menucheckitem'},
    			   {xtype:'menucheckitem',text:'关闭',group:'scale',id:'close_menucheckitem'},
    			   {xtype:'menucheckitem',text:'延期',group:'scale',id:'defer_menucheckitem'}
    			]
    		}
    	}],
    	items:[{
    		xtype:'gridpanel',
    		id:'assign_grid',
    		autoScroll:true,
    		region:'center',
            stripeRows:true,
    		loader:{
    			loadMask:{msg:'正在加载数据，请稍候....'}
    		},
    		store:'ToBeAssignedBugStore',	 
    	/*	features:[{
    			ftype:'grouping',
    			groupByText:'日期分组', 
    			groupHeaderTpl:'{SubmitDay} --共{rows.length}',
    			showGroupsText:'展示分组',    //显示在列下拉款     
    			startCollapsed:true,         //开始是否收起
                enableGroupingMenu:true		
    		}],*/
    		columns:[{
    		         text:'BugId',dataIndex:'bugId'
    		     },{
    		         text:'Bug编号',dataIndex:'bugid'
    		     },{
    		         text:'Bug标题',dataIndex:'bugTitle'
    		     },{
                     text:'Bug严重度',dataIndex:'bugSeverity'
    		     },{
    		    	 text:'项目编号',dataIndex:'projectid',hidden:true
    		     },{
    		    	 text:'项目Id',dataIndex:'projectId',hidden:true
    		     },{
    		    	 text:'所属项目',dataIndex:'projectName'
    		     },{
    		     	 text:'模块编号',dataIndex:'modularid',hidden:true
    		     },/*{
    		    	 text:'模块Id',dataIndex:'modularId',hidden:true
    		     },*/{
    		    	 text:'所属模块',dataIndex:'modularName'
    		     },{
    		    	 text:'项目版本ID',dataIndex:'versionId',hidden:true
    		     },{
    		    	 text:'项目版本',dataIndex:'versionName'
    		     },/*{
    		     	text:'测试人员Id',dataIndex:'testerId',hidden:true
    		     },*/{
    		    	 text:'测试人员编号',dataIndex:'testerid',hidden:true
    		     },{
    		     	 text:'测试人员',dataIndex:'testerName'
    		     },{
    		    	 text:'提交日期',dataIndex:'submitedDay'
    		     }
    		],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl : [
    	               '<p><b>期望效果: </b>{bugExpected}</p>',
    	               '<p><b>运行效果: </b>{bugResult}</p>',
    	               '<p><b>详细内容: </b>{bugDetail}</p>'
    	         ]
    		}],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ToBeAssignedBugStore',
                pageSize:15,
                dock:'bottom',     //定位
                displayInfo:true
             }],
    	    selType:'rowmodel',
    	    multiSelect:false, 
    	    enableKeyNav:true
    	},{
    		region:'east',
    		collapsible:true,
    		autoScroll:true,
    		title:'BUG操作',
    		id:'operate_panel',
    		split:true,
    		width:300,
    		collapsed:true,  //默认收缩
    		layout:'accordion',
    		layoutConfig: {
    			titleCollapse: false,
    			animate: true,
    			activeOnTop: true
    		},
    		items:[{
    			xtype:'form',
    			title:'分配任务',
    			id:'assign_form',
    			striperow:true,
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
    	  	        xtype:'hiddenfield',
    	  	        hidden:true,
    	  	        name:'bugId' 
    	  	    },{
    			   fieldLabel:'BUG编号',
    			   readOnly:true,
    			   name:'bugid'
    		    },{
    		       fieldLabel:'Bug标题',
    		       readOnly:true,
    		   	   name:'bugTitle'
    		    },{
    		    	xtype:'hiddenfield',
    		    	name:'projectId',
    		    	hidden:true
    		    },{
    		    	fieldLabel:'所属项目',
    		    	readOnly:true,
     		   	    name:'projectName'
    		    },/*{  		    	
        		    xtype:'hiddenfield',
        		    name:'modularId',
        		    hidden:true
    		    },*/{
    		    	fieldLabel:'所属模块',
    		    	readOnly:true,
     		   	    name:'modularName'
    		    },{
    		    	xtype:'combobox',
    		    	fieldLabel:'Bug优先级',
    		    	name:'bugPriority',
      	            displayField:'name',
      	            store:new Ext.data.Store({
      	            	fields : [ {
      						name : 'name'
      					}, // xml子节点
      					{
      						name : 'id'
      					} // xml子节点
      					],
      					proxy : {
      						type : 'ajax',
      						url : 'xmldata/BugPriority.xml',
      						reader : {
      							type : 'xml',
      							record : 'bugPriority'
      						}
      					}
      	            }) 
    		    },{
    		    	xtype:'replicatorcombobox',
    		    	name:'dealDeveloperid'
    		    },{
    		    	xtype:'textarea',
    		    	fieldLabel:'备注',
    		    	name:'remark'
    		    }],
    		    buttons:[{
    		    	text:'提交',
    		    	id:'assign_submit'
    		    },{
    		    	text:'重置',
    		    	id:'assign_reset'
    		    }]
    		},{
    			xtype:'form',
    			title:'其它操作',
    			id:'other_operation',
    			defaultType:'textfield',
    			defaults:{
    	   	         allowBlank : false, 
    				 msgTarget : 'side',
    				 padding:'10 10 10 10',
    				 labelSeparator:':',
    				 labelWidth:80
    	   	    },
    	   	    waitMsgTarget:true,
    	  	    items:[{
    	  	    	xtype:'hiddenfield',
    	  	        hidden:true,
    	  	        name:'bugId' 
    	  	    },{
    			   fieldLabel:'BUG编号',
    			  // readOnly:true,
    			   name:'bugid'
    		    },{
    		       fieldLabel:'Bug标题',
    		       readOnly:true,
    		   	   name:'bugTitle'
    		    },{
    		    	fieldLabel:'所属项目',
    		    	readOnly:true,
     		   	    name:'projectName'
    		    },{
    		    	xtype:'combobox',
    		        fieldLabel:'选择操作类型',
    		        name:'bugReasonType',
    		        editable:false,
      	            displayField:'name', 
      	            store:new Ext.data.Store({
      	            	fields : [ {
      						name : 'name'
      					}, 
      					{
      						name : 'id'
      					} 
      					],
      					proxy : {
      						type : 'ajax',
      						url : 'xmldata/BugReason.xml',
      						reader : {
      							type : 'xml',
      							record : 'bugReason'
      						}
      					}
      	            })     
    		    },{
    		    	xtype:'textareafield',  //大文本域
    		        fieldLabel:'理由',
    		        name:'reasonDetail'
    		    }],
    		    buttons:[{
    		    	text:'提交',
    		    	id:'other_submit'
    		    },{
    		    	text:'重置',
    		    	id:'other_reset'
    		    }]
    		}]
    	}]
    }]
})