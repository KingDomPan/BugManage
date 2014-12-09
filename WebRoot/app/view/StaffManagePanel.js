Ext.define('AM.view.StaffManagePanel',{
    extend:'Ext.panel.Panel',
/*    requires:([
        'AM.store.StaffManageStore'
    ]),*/
    alias:'widget.staffmanagepanel',     //别名
   // id:'assigntabpanel',
    title: '员工管理',
    
    items:[{
    	xtype:'treepanel',
    	rootVisible:false,
    	store:'StaffManageStore',
    //	id:'managerbugtree',
    	useArrows:true
    }]
})