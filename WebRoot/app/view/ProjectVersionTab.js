Ext.define('AM.view.ProjectVersionTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.projectversiontab',     //别名

    id:'projectversion_tabpanel',
    title:'项目版本管理',
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
    		xtype:'label',
    		text:'项目查询'
    	},'-',{
    	    xtype:'combobox',   
    	    width:100,
    	    emptyText:'选择查询字段'	,
    	    editable : false,
    	    id:'projectversion_search_type',  
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
    	    id:'projectversion_search_value',
    	    emptyText:'请输入查询内容',
    	    width:200
    	},'-',{
    		text:'查询',
    		id:'projectversion_search_submit'
    	}],
    	items:[{
    		xtype:'gridpanel',
    		id:'version_grid',
    		title:'版本信息',
    		width:500,
    		autoScroll:true,
    		region:'west',
    		split:true, 
    		collapsible:true,
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
    		store:'VersionInfoStore',	  
    		enableDragDrop:true,
            stripeRows:true,
    		columns:[
    		     {text:'版本编号',dataIndex:'versionId'},
    		     {text:'版本名称',dataIndex:'versionName'},
    		     {text:'起始日期',dataIndex:'beginTime'},
    		     {text:'更新日期',dataIndex:'endTime'}
    		],
    		plugins:[{
    			 ptype: 'rowexpander',
    	         rowBodyTpl : [
    	              '<p><b>更新内容:  </b>{updatedContend}</p>'
    	         ]
    		}],
    	    selType:'rowmodel',
    	    multiSelect:false, 
    	    enableKeyNav:true
    	},{
    		xtype:'gridpanel',
    		id:'project_grid',
    		title:'项目信息',
    		autoScroll:true,
    		region:'center',
    		split:true, 
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
    		store:'ProjectInfoStore',	  
    		enableDragDrop:true,
            stripeRows:true,
    		columns:[
    		     {text:'项目Id',dataIndex:'projectid',hidden:true},
    		     {text:'项目编号',dataIndex:'projectId'},
    		     {text:'项目名称',dataIndex:'projectName'},
    		     {text:'项目负责人Id',dataIndex:'managerid'},
                 {text:'项目负责人编号',dataIndex:'managerId'},
                 {text:'项目负责人',dataIndex:'managerName'}
    		],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'ProjectInfoStore',
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
    		title:'版本操作',
    		id:'projectversion_operate_panel',
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
    			title:'项目版本升级',
    			id:'projectversion_upgrade_form',
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
    	  	    	name:'projectid', 
    	  	    	hidden:true
    	  	    },{
    		       fieldLabel:'所属项目',
    		       editable : false,
    		   	   name:'projectName'
    		    },{
    		    	xtype:'textareafield',  //大文本域
    		        fieldLabel:'更新内容',
    		        name:'updatedContend'
    		    }],
    		    buttons:[{
    		    	text:'升级',
    		    	id:'projectversion_add_submit'
    		    },{
    		    	text:'重置',
    		    	id:'projectversion_add_reset'
    		    }]
    		},{
    			xtype:'form',
    			title:'版本信息修改',
    			id:'projectversion_update_form',
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
    	  	   	   fieldLabel:'项目编号',
    			   name:'projectId',
    			   readOnly:true
    	  	   },{
    			   fieldLabel:'版本',
    			   readOnly:true,
    			   name:'versionName'
    		    },{
    		    	xtype:'textareafield',  //大文本域
    		        fieldLabel:'更新内容',
    		        name:'updatedContend'
    		    }],
    		    buttons:[{
    		    	text:'修改',
    		    	id:'projectversion_update_submit'
    		    },{
    		    	text:'重置',
    		    	id:'projectverison_update_reset'
    		    }]
    		}]
    	}]
    }]
})