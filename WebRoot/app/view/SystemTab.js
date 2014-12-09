Ext.define('AM.view.SystemTab',{
    extend:'Ext.tree.Panel',
    alias:'widget.systemtab',     //别名
    rootVisible:false,
    title:'系统设置',
    id:'system_treepanel',
	useArrows:true,
	store:new Ext.data.TreeStore({
	   root:{
	     leaf:false,
		 children:[{
			 text:'密码修改',
			 leaf:true,
			 id:'password_change_item'
		 }]
	}
	})
})