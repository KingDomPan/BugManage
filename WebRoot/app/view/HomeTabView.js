Ext.define('AM.view.HomeTabView',{
    extend:'Ext.tab.Panel',
    alias:'widget.hometabview',     //别名
    requires:([
    	'AM.view.StaffManagePanel'
    ]),
    layout:'fit',
    id:'tabpanel',
    autoScroll:true,
    items: [{
    	title: '首页',
    	defaults:{
    		frame:true,
    		collapsible:true,
    		draggable:true,
    		tools:[{
    			type:'refresh',
    			qtip:'刷新',
    		    handler: function(e, target, panel, tool){
                    var portlet = panel.ownerCt;
                    portlet.setLoading('Loading...');
                    Ext.defer(function() {
                        portlet.setLoading(false);
                    }, 1500);
                }
    		}]
    	},
    	items:[{
    		xtype:'panel',
    		title:'公司',
    		width:400,
    		height:300,
    		layout:'fit',
    		autoLoad:'Company.html'
    	},{
    		xtype:'panel',
    		title:'最新通知',
    		width:400,
    		height:300,
    		layout:'fit'
    	}]
    }]
})