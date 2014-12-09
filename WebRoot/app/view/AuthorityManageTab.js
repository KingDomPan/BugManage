Ext.define('AM.view.AuthorityManageTab',{
    extend:'Ext.panel.Panel',
    alias:'widget.authoritymanagetab',     //别名
    id:'authoritymanage_tabpanel',
    title: '权限管理',
    closeable:true,
    layout: {
		type: 'vbox', 
        align : 'stretch', //vbox:[left,center,stretch,stretchmax]
        pack : 'start'   
	},
    items:[{
    	xtype:'panel',
    	title:'用户权限',
    	height:250,
    	collapsible:true,
    	items:[{
    		xtype:'form',
    		//id:'search_form',
    		layout:{
       		    type:'hbox', 
                align:'stretch', //vbox:[left,center,stretch,stretchmax]
                pack:'start'   //控制子元素展示的位置,start左面，center中间，end右面
       	    },    
       	    padding:'10 10 10 30',
       	    border:0,
    	    items:[{
    	    		xtype:'fieldset',
    	    		title:'测试员默认权限',
    	    		defaultType: 'checkbox',
    	    		defaults:{
    				    padding:'10 10 10 10',
    				    labelSeparator:':',
    				    labelWidth:60
    	   	        },
    	    		items:[{
    	    			fieldLabel: '新增Bug'
    	    		},{
    	    			fieldLabel: '完善Bug'
    	    		},{
    	    			fieldLabel: '验证Bug'
    	    		}]
    	     },{
    	    		xtype:'fieldset',
    	    		title:'项目经理默认权限',
    	    		defaultType: 'checkbox',
    	    		defaults:{
    				    padding:'10 10 10 10',
    				    labelSeparator:':',
    				    labelWidth:60
    	   	        },
    	    		items:[{
    	    			fieldLabel: '分配Bug'
    	    		},{
    	    			fieldLabel: '查询Bug'
    	    		},{
    	    			fieldLabel: 'Bug统计'
    	    		}]    	
    	    },{
    	    	    xtype:'fieldset',
    	    		title:'开发人员默认权限',
    	    		defaultType: 'checkbox',
    	    		defaults:{
    				    padding:'10 10 10 10',
    				    labelSeparator:':',
    				    labelWidth:60
    	   	        },
    	    		items:[{
    	    			fieldLabel: '修复Bug'
    	    		},{
    	    			fieldLabel: '重修复Bug'
    	    		}]     
    	    },{
    	    	    xtype:'fieldset',
    	    		title:'其它权限',
    	    		defaultType: 'checkbox',
    	    		defaults:{
    				    padding:'10 10 10 10',
    				    labelSeparator:':',
    				    labelWidth:60
    	   	        },
    	    		items:[{
    	    			fieldLabel: '员工管理'
    	    		},{
    	    			fieldLabel: '项目管理'
    	    		},{
    	    			fieldLabel: '权限分配'
    	    		}]    
    	    }],
    	    buttons:[{
    	    	text:'修改',
    	    	id:'authority_search_submit'
    	    },{
    	    	text:'重置',
    	    	id:'authority_search_reset'
    	    }]
    	}]
    },{
    	xtype:'panel',
    	title:'Bug',
    	flex:1,	
    	layout:'border',
    	items:[{
    		xtype:'gridpanel',
    		id:'authority_grid',
    		autoScroll:true,
    		region:'center',
            stripeRows:true,
    		loader:{
    			loadMask:{msg:'正在加载数据，请稍候....'}
    		},
    		store:'BugInfoStore',	 
    	/*	features:[{
    			ftype:'grouping',
    			groupByText:'日期分组', 
    			groupHeaderTpl:'{SubmitDay} --共{rows.length}',
    			showGroupsText:'展示分组',    //显示在列下拉款     
    			startCollapsed:true,         //开始是否收起
                enableGroupingMenu:true		
    		}],*/
    		columns:[
    		     {text:'员工编号',dataIndex:'userId'},
    		     {text:'员工姓名',dataIndex:'userName'},
    		     {text:'职位',dataIndex:'post'},
    		     {text:'新增Bug',dataIndex:'isSubmit'},
    		     {text:'完善Bug',dataIndex:'ProjectId',hidden:true},
    		     {text:'验证Bug',dataIndex:'ProjectName'},
    		     {text:'分配Bug',dataIndex:'ModularId',hidden:true},
    		     {text:'查询Bug',dataIndex:'ModularName'},
    		     {text:'Bug统计',dataIndex:'UserName'},
    		     {text:'员工管理',dataIndex:'SubmitDay'},
    		     {text:'项目管理',dataIndex:'SubmitDay'},
    		     {text:'权限分配',dataIndex:'SubmitDay'}
    		],
            dockedItems:[{    //任意定位工具栏
                xtype:'pagingtoolbar',     //分页
                store:'BugInfoStore',
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