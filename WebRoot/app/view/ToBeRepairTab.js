Ext.define('AM.view.ToBeRepairTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.toberepairtab',     //别名
    requires:([
        'Ext.ux.RowExpander'
    ]),
    id:'toberepair_tabpanel',
    title:'待修复Bug',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	//title:'Bug',
    	flex:1,
    	layout:'border',
    	tbar:[{
    		text:'修复提交',
    		id:'add_repairreport_btn'
    	}],
    	items:[{
    		xtype:'gridpanel',
    		id:'toberepair_grid',
    		autoScroll:true,
    		region:'center',
    		viewConfig : {
				stripeRows : true,
				getRowClass:function(record, index) {
					 if (index % 2 == 0)
					   return 'x-grid3-row-alt';
					// else
					// return 'green';
					 },
				plugins : {
					ddGroup : 'GridExample',
					ptype : 'gridviewdragdrop',
					enableDrop : false
				}
			},
			enableDragDrop : true,
    		loader:{
    			loadMask:{msg:'正在加载数据，请稍候....'}
    		},
    		store:'ToBeRepairedStore',	 
    		columns:[{
    			text:'BugId',dataIndex:'bugid',hidden:true
    		},{
    		         text:'Bug编号',dataIndex:'bugId'
    		     },{
    		         text:'Bug标题',dataIndex:'bugTitle'
    		     },{
    		    	 text:'Bug严重度',dataIndex:'bugSeverity'
    		     }/*,{
    		    	 text:'项目Id',dataIndex:'projectid',hidden:true
    		     }*/,{
    		    	 text:'项目编号',dataIndex:'projectId',hidden:true
    		     },{
    		    	 text:'所属项目',dataIndex:'projectName'
    		     }/*,{
    		    	 text:'模块Id',dataIndex:'modularid',hidden:true
    		     }*/,{
    		    	 text:'模块编号',dataIndex:'modularId',hidden:true
    		     },{
    		    	 text:'所属模块',dataIndex:'modularName'
    		     },{
    		    	 text:'测试人员编号',dataIndex:'testerId',hidden:true
    		     },{
    		    	 text:'测试人员',dataIndex:'testerName'
    		     },{
    		    	 text:'任务id',dataIndex:'taskid',hidden:true
    		     },{
    		    	 text:'提交日期',dataIndex:'submitedDay'
    		     }
    		],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl : [
    	               '<p>-------------bug报告--------------</p>',
                       '<p><b>期望效果: </b>{bugExpected}</p>',
                       '<p><b>运行效果: </b>{bugResult}</p>',
    	               '<p><b>详细描述: </b>{bugDetail}</p>',
    	               '<p>-------------分配记录--------------</p>',
    	               '<p><b>bug优先级: </b>{bugPriority}</p>',
    	               '<p><b>分配日期: </b>{submitedDay2}</p>',
    	               '<p><b>项目经理编号: </b>{managerId}</p>',
    	               '<p><b>分配经理: </b>{managerName}</p>',
    	               '<p><b>修复人员编号: </b>{developerId}</p>',
    	               '<p><b>修复人员: </b>{developerName}</p>',
    	               '<p><b>备注: </b>{remark}</p>',
    	         ]
    		}],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ToBeRepairedStore',
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
    		id:'toberepair_operate_panel',
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
    			title:'Bug拒绝',
    			id:'reject_form',
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
    	  	    	name:'taskid',
    	  	    	hidden:true
    	  	    },{
    	  	    	name:'bugid',
    	  	    	hidden:true
    	  	    },{
    			   fieldLabel:'BUG编号',
    			   readOnly:true,
    			   name:'bugId'
    		    },{
    		       fieldLabel:'Bug标题',
    		       readOnly:true,
    		   	   name:'bugTitle'
    		    },{
    		    	xtype:'textarea',
    		    	fieldLabel:'拒绝理由',
    		    	name:'reasonDetail'
    		    }],
    		    buttons:[{
    		    	text:'提交',
    		    	id:'reject_submit'
    		    },{
    		    	text:'重置',
    		    	id:'reject_reset'
    		    }]
    		}]
    	}]
    }]
})