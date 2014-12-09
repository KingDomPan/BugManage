Ext.define('AM.view.RepairReportWindow',{
    extend:'Ext.window.Window',
    alias:'widget.repairreportwindow',     //别名
    id:'repairreport_window',
    title:'提交修复方案',
    width:500,
    height:280,
    closable: true,
    closeAction: 'hide',
    animateTarget:'add_repairreport_btn',
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
    		xtype:'hiddenfield',
    		name:'bugid',
    		hidden:true
    	},{
    		fieldLabel:'BUG编号',
    		readOnly:true,
    		name:'bugId'
    	},{
    		fieldLabel:'BUG标题',
    		name:'bugTitle'
    	},/*{
    		 xtype:'fieldset',
    		 collapsible:true,
    		 collapsed : true,
    		 margin:'0 0 0 10',
    		 name:'fileupload',
    		 title:'附件上传',
    		 defaults:{
    			 padding:'10 10 10 10',
    			 labelSeparator:':',
    			 anchor:'80%',
    			 labelWidth:60
    	   	 },
    		 items:[{
    			 xtype:'filefield',
    			 fieldLabel:'文件',
    			 name:'file'
    		 },{
    			 xtype:'filefield',
    			 fieldLabel:'文件',
    			 name:'file' 
    		 },{
    			 xtype:'filefield',
    			 fieldLabel:'文件',
    			 name:'file' 
    		 }]
    	},*/{
    		xtype:'textareafield',
    		fieldLabel:'修复方案',
    	   	name:'repairScheme'
    	}],
    	buttons:[{
    	    text:'提交',
    	    id:'repairscheme_submit'
    	},{
    	    text:'重置',
    	    id:'repairschem_reset'
    	}]
    }]  
})