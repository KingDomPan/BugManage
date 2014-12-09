Ext.define('AM.view.ProjectManagePanel',{
    extend:'Ext.panel.Panel',
    alias:'widget.projectmanagepanel',     //别名
   // id:'assigntabpanel',
    title: '项目管理',
    items:[{
    	xtype:'treepanel',
    	store:'ProjectManageStore',
    	rootVisible:false,
    //	id:'managerbugtree',
    	useArrows:true
    }]
})