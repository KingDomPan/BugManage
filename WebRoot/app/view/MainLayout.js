Ext.define('AM.view.MainLayout',{
    extend:'Ext.panel.Panel',
    alias:'widget.mainlayout',     //别名
    layout:'border',
    items:[{
    	html:'<font size=10>BUG管理系统</font>',
        region:'north',
        height:100,
        bbar:[{
        	xtype:'label',
        	text:'欢迎您,'
        },{id:'username_label'},'-',{xtype:'label',text:'用户身份:'},{id:'userstatus_label',icon:'image/vcard.png'},'-','->',{
        	 text:'菜单',
        	 menu:{
        	   items:[{
        	     text:'密码修改'
        	   }]
        	 }
        },{
             text:'注销',
             id:'loginout'
        }]
    },{  
    	title:'导航菜单',
        region:'west',  
        split:true,
        id:'navigation_panel',
        collapsible:true,
        tools:[{
           type:'refresh',     //刷新按钮
           qtip:'刷新'  
        }],
        width:300,
        layout: {
             type: 'accordion',
           //  titleCollapse: false,
             animate: true,
             activeOnTop: true
        },
        items:[{	
            title:'<b>Bug管理</b>',
            id:'Bug_Info',
            icon:'image/bug_go.png'
        }]
    },{
    	region:'center',
    	layout:'fit',
    	items:[{
    	    xtype:'hometabview'
    	}]
    	
    },{
    	region:'south',
    	height:20
    }]
})