Ext.define('AM.view.ReRepairTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.rerepairtab',     //别名
    requires:([
        'Ext.ux.RowExpander'
    ]),
    id:'rerepair_tabpanel',
    title:'重修复Bug',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	flex:1,
    	layout:'border',
    	tbar:[{
    		text:'重修复提交',
    		id:'add_rerepairreport_btn'
    	}],
    	items:[{
    		xtype:'gridpanel',
    		id:'rerepair_grid',
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
    		store:'ReRepairedStore',	 
    		columns:[{
    			dataIndex:'bugid',hidden:true
    		},{
    		         text:'Bug编号',dataIndex:'bugId'
    		},{
    		         text:'Bug标题',dataIndex:'bugTitle'
    		},{
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
    			 text:'bug提交日期',dataIndex:'submitedDay'
    		}],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl:[
    	               '<p>-------------bug报告--------------</p>',
    	               '<p><b>测试人员:</b>{testerName}</p>',
                       '<p><b>期望效果: </b>{bugExpected}</p>',  
                       '<p><b>运行效果: </b>{bugResult}</p>',
    	               '<p><b>Bug详细描述: </b>{bugDetail}</p>',                  
    	               '<p>----------------bug修复记录-----------</p>',
    	               '<p><b>修复日期: </b>{submitedDay5}</p>', 
    	               '<p><b>修复方案: </b>{repairScheme}</p>', 
    	               '<p>----------------重修复详情-----------</p>',
    	               '<p><b>原因: </b>{repairReason}</p>', 
    	               '<p><b>测试日期: </b>{submitedDay6}</p>',
    	               '<p><b>详情: </b>{repairDetail}</p>'
    	         ]
    		}],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ReRepairedStore',
                pageSize:15,
                dock:'bottom',     //定位
                displayInfo:true
             }],
    	    selType:'rowmodel',
    	    multiSelect:false, 
    	    enableKeyNav:true
    	}]
    }]
})