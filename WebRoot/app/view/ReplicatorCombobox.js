Ext.define('AM.view.ReplicatorCombobox',{
	extend:'Ext.form.field.ComboBox',
	alias:'widget.replicatorcombobox', 
	fieldLabel:'选择修复人员',
	editable : false,
	displayField:'userid',
    store:new Ext.data.ArrayStore({fields:[],data:[[]]}),
    listConfig:{maxWidth:300,maxHeight:300},
    config:{
        _delivervalue:null
    },
    initComponent:function(){
    	this.treeRenderId = Ext.id();
		this.tpl = "<tpl><div id='"+this.treeRenderId+"'></div></tpl>";		
		this.treeObj = new Ext.tree.Panel({
            border : false,
            height:150,
            width: '100%',
            autoScroll : true,
            rootVisible:false,
            store:new Ext.data.TreeStore({
                loadMask:true,
                proxy:{
                   type:'ajax',
                   url:'project/getProjectAndModularAndDeveloper.action',
                   reader:{
                       type:'json'
                   }
               }
           })
        }),
		this.callParent(arguments);
		this.on({
			'expand':function(){
			    if(!this.treeObj.rendered&&this.treeObj&&!this.readOnly){
			        Ext.defer(function(){
		        		this.treeObj.render(this.treeRenderId);
		        	},200,this);
			    }
			    this.treeObj.getStore().load({params:{projectid:this.up('form').getForm().findField('projectId').getValue()}});
			}
		});
		this.treeObj.on('itemclick',function(view,record){
			if(record.isLeaf()){
				this.setValue(record.get('text'));
				this._delivervalue=record.raw.userid;
				this.collapse();
			}
		},this);
    }
});