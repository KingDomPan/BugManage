Ext.define('AM.view.ProjectManageTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.projectmanagetab',     //别名
/*    requires:([
        'Ext.ux.RowExpander'
    ]),*/
    id:'projectmanage_tabpanel',
    title:'项目管理',
    closable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	title:'项目信息',
    	flex:1,
    	layout:'border',  	
    	tbar:[{
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
	            width:500,
	            id:'modular_gridpanel',
	            title:'模块信息',
	            region:'west',
	            split:true, 
    		    collapsible:true,
    		  //  collapsed:true,  //默认收缩
	            autoScroll:true,
	            viewConfig: {
                   plugins: {
                       ddGroup: 'GridExample',
                       ptype: 'gridviewdragdrop',
                       enableDrop: false
                   }
                },
                enableDragDrop:true,
    		    loader:{
    			    loadMask:{msg:'正在加载数据，请稍候....'}
    		    },
    		    store:'ModularInfoStore',	  
                stripeRows:true,
    	  	    columns:[
    	  	        {text:'模块Id',dataIndex:'modularid',hidden:true},
    	  	        {text:'模块编号',dataIndex:'modularId'},
    		        {text:'模块名称',dataIndex:'modularName'},  		        
    		        {text:'模块负责人Id',dataIndex:'developerid',hidden:true},
    		        {text:'模块负责人编号',dataIndex:'developerId'},
    		        {text:'模块负责人',dataIndex:'developerName'}
    		    ],
             /*   dockedItems:[{    //任意定位工具栏
                    xtype:'pagingtoolbar',     //分页
                    store:'ModularInfoStore',
                  //  pageSize:15,
                    dock:'bottom',     //定位
                    displayInfo:true
                }],*/
    	        selType:'rowmodel',
    	        multiSelect:false, 
    	        enableKeyNav:true     
	     },{
    		    xtype:'gridpanel',
    		    id:'project_gridpanel',
    		    autoScroll:true,
    	     	title:'项目信息',
    		    region:'center',
    		    viewConfig: {
                   plugins: {
                       ddGroup: 'GridExample',
                       ptype: 'gridviewdragdrop',
                       enableDrop: false
                   }
                },
                enableDragDrop:true,
    		    loader:{
    			    loadMask:{msg:'正在加载数据，请稍候....'}
    		    },
    		    store:'ProjectInfoStore',	  
                stripeRows:true,
    	  	    columns:[
    	  	       {text:'项目Id',dataIndex:'projectid',hidden:true},
    		       {text:'项目编号',dataIndex:'projectId'},
    		       {text:'项目名称',dataIndex:'projectName'},
                   {text:'项目负责人Id',dataIndex:'managerid',hidden:true},
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
    		title:'项目操作',
    		id:'project_operate_panel',
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
    			title:'添加项目',
    			id:'project_add_form',
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
    			   fieldLabel:'项目名称',
    			   name:'projectName'
    		    },{
    		       xtype:'combobox',
    		       fieldLabel:'项目负责人',
    		       editable : false,
    		   	   name:'managerId', 
    		   	   listConfig:{        //控制
    	                emptyText:'没有找到匹配的数值..',  
    	                maxHeight:200,    //超过会出现滚动条  
    	                loadingText:'正在加载...',
    	                getInnerTpl:function(){ //模版功能
    	                    return "<div>{userId}.{userName}</div>"
    	                 }
    	            },
        	    	store:'ManagerInfoStore',
        	    	valueField:'userid',
        	    	displayField:'userName'
    		    }],
    		    buttons:[{
    		    	text:'新增',
    		    	id:'project_add_submit'
    		    },{
    		    	text:'重置',
    		    	id:'project_add_reset'
    		    }]
    		},{
    			xtype:'form',
    			title:'项目修改',
    			id:'project_update_form',
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
  			       name:'projectid',
  			       hidden:true		   
    	  	   },{
    	  	   	   fieldLabel:'项目编号',
    			   name:'projectId',
    			   readOnly:true
    	  	   },{
    			   fieldLabel:'项目名称',
    			   name:'projectName'
    		    },{
    		    	 name:'managerid',
    			     hidden:true	
    		    },{
    		       xtype:'combobox',
    		       fieldLabel:'项目负责人',
    		   	   name:'managerName',
    		   	   editable:false,
    		   	   listConfig:{        //控制
    	                emptyText:'没有找到匹配的数值..',  
    	                maxHeight:200,    //超过会出现滚动条  
    	                loadingText:'正在加载...',
    	                getInnerTpl:function(){ //模版功能
    	                    return "<div>{userId}.{userName}</div>"
    	                 }
    	            },
        	    	store:'ManagerInfoStore',
        	    	valueField:'userid',
        	    	displayField:'userName'
    		    }],
    		    buttons:[{
    		    	text:'修改',
    		    	id:'project_update_submit'
    		    },{
    		    	text:'重置',
    		    	id:'project_update_reset'
    		    }]
    		},{
    			xtype:'form',
    			title:'模块添加',
    			id:'modular_add_form',
    			defaultType:'textfield',
    			defaults:{
    	   	         allowBlank : false, 
    				 msgTarget : 'side',
    				 padding:'10 10 10 10',
    				 labelSeparator:':',
    				 labelWidth:68
    	   	    },
    	   	    waitMsgTarget:true,
    	  	   items:[{
    	  		   name:'projectid',
    	  		   hidden:true
    	  	   },{
    	  		   fieldLabel:'所属项目',
    	  		   name:'projectName',
    	  		   editable : false,
    	  	   	   /*xtype:'combobox',
    	  	   	   fieldLabel:'所属项目',
    	  	   	   width:280,
    			   name:'projectid',
    			   editable : false,
    			   displayField : 'projectName',
    			   valueField : 'id',
    			    autoScroll : true,
    				store : 'ProjectStore',
    				pageSize:10,
    				listConfig : { // 控制
    					emptyText : '没有找到匹配的数值..',
    					maxHeight : 200, // 超过会出现滚动条
    					loadingText : '正在加载...',
    					getInnerTpl : function() { // 模版功能
    						return "<div>{projectId}.{projectName}</div>"
    					}
    				}*/
    	  		   
    	  	   },{
    			   fieldLabel:'模块名称',
    			   name:'modularName'
    		    },{
    		       xtype:'combobox',
    		       fieldLabel:'模块负责人',
    		   	   name:'developerId',
    		   	   editable : false,
    		   	   listConfig:{        //控制
    	                emptyText:'没有找到匹配的数值..',  
    	                maxHeight:200,    //超过会出现滚动条  
    	                loadingText:'正在加载...',
    	                getInnerTpl:function(){ //模版功能
    	                    return "<div>{userId}.{userName}</div>"
    	                 }
    	            },
        	    	store:'RelicatorInfoStore',
        	    	valueField:'userid',
        	    	displayField:'userName'
    		    }],
    		    buttons:[{
    		    	text:'添加',
    		    	id:'modular_add_submit'
    		    },{
    		    	text:'重置',
    		    	id:'modular_add_reset'
    		    }]
    		},{
    			xtype:'form',
    			title:'模块修改',
    			id:'modular_update_form',
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
    	  		  name:'modularid',
    	  		  hidden:true
    	  	   },{
    			   fieldLabel:'模块名称',
    			   name:'modularName'
    		    },{
    		    	name:'developerid',
    		    	hidden:true
    		    },{
    		       xtype:'combobox',
    		       fieldLabel:'模块负责人',
    		   	   name:'developerName',
    		   	   editable : false,
    		   	   listConfig:{        //控制
    	                emptyText:'没有找到匹配的数值..',  
    	                maxHeight:200,    //超过会出现滚动条  
    	                loadingText:'正在加载...',
    	                getInnerTpl:function(){ //模版功能
    	                    return "<div>{userId}.{userName}</div>"
    	                 }
    	            },
        	    	store:'RelicatorInfoStore',
        	    	valueField:'userid',
        	    	displayField:'userName'
    		    }],
    		    buttons:[{
    		    	text:'修改',
    		    	id:'modular_update_submit'
    		    },{
    		    	text:'重置',
    		    	id:'modular_update_reset'
    		    }]
    		}]
    	  }]
    	}]
})