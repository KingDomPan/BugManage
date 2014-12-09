Ext.define('AM.view.LoginView',{	
    extend:'Ext.window.Window',
    alias:'widget.loginview',     //别名
    id:'loginview',
    height:220,
    width:400,
    title:'用户登录',
    closable:false,
    modal:true,
    items:[{
    	xtype:'panel',
    	border:0,
    	layout:{
    		type:'hbox',
    		align:'stretch'
    	},
    	items:[{
             flex:1,
             padding:'20 10 25 20',
             html:'<img alt="logo" src="image/logo.jpg">'
    	},{
    	  xtype:'form',
    	  flex:1.7,
    	  border:0,
    	  id:'login_form',
    	  padding:'15 20 20 16',
    	  defaultType:'textfield',
    	  defaults:{
    	     allowBlank : false, 
			 msgTarget : 'side',
			 padding:'10 10 10 10',
			 labelSeparator:':',
			 labelWidth:50,
			 selectOnFocus:true
    	  },
    	  waitMsgTarget:true,
    	  items:[{
    		fieldLabel:'用户名',
    		name:'userName',
    		id:'username',
    		blankText : '用户名不允许为空!'	
    	  },{
    	    fieldLabel:'密码',
    		name:'pwd',
    		id:'password',
    		inputType:'password',
    		blankText : '密码不允许为空!'	
    	  },{
    	     xtype:'panel',
    	     layout:'column',
    	     border:0,
    	     items:[{
    	  	     xtype:'textfield',
    	  	     allowBlank : false, 
			     msgTarget : 'side',
			     selectOnFocus:true,
			     blankText : '验证码不允许为空!',	
    	      	 fieldLabel:'验证码',
    	  	     labelWidth:50,
    		     name:'checkcode',
    		     id:'checkcode',
    		     columnWidth:.63
    	      },{
    	      	xtype:'panel',
    	      	style:'cursor:pointer;',
    	      	padding:'0 0 0 8',
    	      	id:'code',
    	      	html:'<img src="getCode.action" id="imgcode">',
    	      	columnWidth:.36
    	      }]
    	  }]
    	}]
    }],
    buttons:[{
    	text:'登录',
    	id:'login'
    },{
    	text:'重置',
    	id:'reset'
    }]
});
