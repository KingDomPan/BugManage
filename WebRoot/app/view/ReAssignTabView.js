Ext.define('AM.view.ReAssignTabView',{
    extend:'Ext.panel.Panel',
    alias:'widget.reassigntabview',     //别名
    layout:'fit',
    id:'reassigntabpanel',
    title: '重分配Bug',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	title:'重分配Bug信息',
    	flex:1,
    	layout:'border',  	
    	tbar:[{
    		xtype:'splitbutton',
    		text:'操作类型',
    		id:'reoperate_splitbtn',
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
    		id:'reassign_grid',
    		autoScroll:true,
    		region:'center',
            stripeRows:true,
            viewConfig: {
                plugins: {
                    ddGroup:'GridExample',
                    ptype: 'gridviewdragdrop',
                    enableDrop: false
                }
            },
            enableDragDrop:true,
    		loader:{
    			loadMask:{msg:'正在加载数据，请稍候....'}
    		},
    		store:'ReAssignStore',	   
    		columns:[{
    			 text:'BugId',dataIndex:'bugId',hidden:true
    		},{
    		     text:'Bug编号',dataIndex:'bugid'
    		},{
    		     text:'Bug标题',dataIndex:'bugTitle'},
            {
    		     text:'Bug严重度',dataIndex:'bugSeverity'
    		},{
    		     text:'项目编号',dataIndex:'projectId',hidden:true
    		},{
    		     text:'所属项目',dataIndex:'projectName'
    		},{
    		     text:'模块编号',dataIndex:'modularId',hidden:true
    		},{
    		     text:'所属模块',dataIndex:'modularName'
    		},{
    		     text:'项目版本ID',dataIndex:'versionId',hidden:true
    		},{
    		     text:'项目版本',dataIndex:'versionName'
    		},{
    		     text:'测试人员编号',dataIndex:'testerId',hidden:true
    		},{
    		     text:'测试人员',dataIndex:'testerName'
    		},{
    		     text:'提交日期',dataIndex:'submitedDay'
    		}],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl : [
                       '<p>-------------bug报告--------------</p>',
    	               '<p><b>期望效果: </b>{bugExpected}</p>',
    	               '<p><b>运行效果: </b>{bugResult}</p>',
    	               '<p><b>详细描述: </b>{bugDetail}</p>',
    	               '<p>-------------分配记录--------------</p>',
    	               '<p><b>bug优先级: </b>{bugPriority}</p>',
    	               '<p><b>分配日期: </b>{submietedDay2}</p>',
    	               '<p><b>修复人员: </b>{developerName}</p>',
    	               '<p><b>备注: </b>{remark}</p>',
    	               '<p>-------------重分配详情--------------</p>',
    	               '<p><b>重分配原因: </b>{taskReasonType}</p>',
    	               '<p><b>重分配日期: </b>{submitedDay4}</p>',
    	               '<p><b>详细原因: </b>{reasonDetail}</p>'
    	         ]
    		}],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ReAssignStore',
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
    		id:'reassign_operate_panel',
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
    			title:'重分配任务',
    			id:'reassign_form',
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
    	  	    	name:'bugId',
    	  	    	hidden:true
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
    		    },{  		    	
        		    xtype:'hiddenfield',
        		    name:'modularId',
        		    hidden:true
    		    },{
    		    	fieldLabel:'所属模块',
    		    	readOnly:true,
     		   	    name:'modularName'
    		    },{
    		    	xtype:'combobox',
    		    	fieldLabel:'Bug优先级',
    		    	name:'bugPriority',
    		    	queryMode:'local',
      	            displayField:'value',
      	            valueField:'id',
      	            store:new Ext.data.Store({
      	                 fields:[
      	                     {name:'id',name:'value'}
      	                  ],
      	                  data:[
      	                       {id:1,value:'立即解决'},
      	                       {id:2,value:'正常排队'},
      	                       {id:3,value:'不紧急'}
      	                  ]
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
    		    	id:'reassign_submit'
    		    },{
    		    	text:'重置',
    		    	id:'reassign_reset'
    		    }]
    		},{
    			xtype:'form',
    			title:'其它操作',
    			id:'reassion_other_operation',
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
    	  	    	name:'bugId',
    	  	    	hidden:true
    	  	    },{
    			   fieldLabel:'BUG编号',
    			   readOnly:true,
    			   name:'bugid'
    		    },{
    		       fieldLabel:'Bug标题',
    		       readOnly:true,
    		   	   name:'bugTitle'
    		    },{
    		    	fieldLabel:'所属项目',
    		    	readOnly:true,
     		   	    name:'projectId',
     		   	    value:'aaa'
    		    },{
    		    	xtype:'combobox',
    		        fieldLabel:'选择操作类型',
    		        name:'bugReasonType',
    		        editable:false, //禁止手写及联想功能
    		        valueField:'TypeId',    //设置需要传到后台的数据项
      	            displayField:'TypeName', //前台展示的数据项
      	            queryMode:'local',
      	            store:new Ext.data.Store({
      	            	 fields:[
      	                     {name:'TypeId'},{name:'TypeName'}
      	                 ],
      	                 data:[
      	                    {TypeId:'1',TypeName:'退回'},
      	                    {TypeId:'2',TypeName:'关闭'},
      	                    {TypeId:'3',TypeName:'延期'}
      	                  ]
      	            })     
    		    },{
    		    	xtype:'textareafield',  //大文本域
    		        fieldLabel:'理由',
    		        name:'reasonDetail'
    		    }],
    		    buttons:[{
    		    	text:'提交',
    		    	id:'reassion_other_submit'
    		    },{
    		    	text:'重置',
    		    	id:'reassign_other_reset'
    		    }]
    		}]
    	}]
    }]
})