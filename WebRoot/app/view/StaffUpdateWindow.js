Ext.define('AM.view.StaffUpdateWindow',{
    extend:'Ext.window.Window',
    alias:'widget.staffupdatewindow',     //别名
    id:'staffupdate_window',
    title:'修改员工信息',
    width:300,
    height:400,
    closable: true,
    closeAction: 'hide',
    animateTarget:'staff_add_btn',
    items:[{
    	xtype:'form',
    	defaultType:'textfield',
    	waitMsgTarget:true,
    	defaults:{
    	    allowBlank : false, 
    	    msgTarget : 'side',
    	    padding:'10 10 10 10',
    	    labelSeparator:':',
    	    anchor:'80%',
    	    labelWidth:68
    	},
    	items:[{
    		fieldLabel:'员工Id',
    		name:'userid'
    	},{
    		fieldLabel:'员工编号',
    		name:'userId'
    	},{
    		fieldLabel:'员工姓名',
    		name:'userName'
    	},{
    		xtype:'combobox',
    	    fieldLabel:'性别',
    	   	name:'sex',
    	   	editable:false, 
    	   	store:new Ext.data.Store({
    	   		fields:[{
    	   			name:'sex'
    	   		}],
    	   		data:[{sex:'男'},{sex:'女'}]
    	   	}),
        	displayField:'sex'
    	},{
    		xtype:'datefield',
    		fieldLabel:'出生日期',
    		format:'Y-m-d',
    		name:'birthday'
    	},{
    		fieldLabel:'联系方式',
    		name:'telephone'
    	},{
    		xtype:'combobox',
    	    fieldLabel:'职位',
    	   	name:'post',
    	   	displayField:'post',
        	store:new Ext.data.Store({
    	   		fields:[{
    	   			name:'post'
    	   		}],
    	   		data:[{post:'测试人员'},{post:'项目经理'},{post:'开发人员'}]
    	   	})
  
    	}],
    	buttons:[{
    	    text:'修改',
    	    id:'staff_update_submit'
    	},{
    	    text:'重置',
    	    id:'staff_update_reset'
    	}]
    }]  
})