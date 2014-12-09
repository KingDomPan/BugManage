Ext.define('AM.view.ReplicatorBugTree',{
	extend:'Ext.tree.Panel',
	alias:'widget.replicatorbugtree', 
	boder:0,
	rootVisible:false,
	store:new Ext.data.TreeStore({
	root:{
	     leaf:false,
		 children:[{
		 	  text:'待修复Bug',
			  id:'ToBeRepaire_item',
			  leaf:true
	     },{
			  text:'重修复Bug',
			  id:'Rerepare_bug_item',
			  leaf:true
		 },{
			  text:'已提交BUG',
			  leaf:true,
			  id:'replicator_submited_item'
		 },{
			  text:'Bug查询',
			  leaf:true,
			  id:'replicator_search_item'
		 }]
	},
	id:'replicatorbug_treepanel',
	useArrows:true
	})
});