Ext.onReady(function(){
   Ext.QuickTips.init();
   Ext.Loader.setConfig({
       enabled:true
   });   
   Ext.Loader.setPath('Ext.ux','extjs-4.1.0/examples/ux');
   Ext.application({
      name:'AM', 
      appFolder:'app',
      launch:function(){
    	 Ext.Ajax.request({
    		 url:'islogined.action',
    		 method:'POST',
			 timeout:4000,
			 async:false,
			 success:function(response,opts){
                 Ext.get('loading').remove();
                 Ext.fly('loading-mask').fadeOut({
                     remove:true
                 });            
				 if(Ext.JSON.decode(response.responseText).success){
					 Ext.example.msg('提示','用户已登录');		
					 Ext.create('Ext.container.Viewport',{
			             layout:'fit',
			             items:{
			                 xtype:'mainlayout'     //组件别名才可用
			             }
			         });
					 var username = Ext.JSON.decode(response.responseText).username;
					 var userstatus = Ext.JSON.decode(response.responseText).userstatus;
					 Ext.getCmp('username_label').setText('<u>'+username+'</u>');
					 Ext.getCmp('userstatus_label').setText('<u>'+userstatus+'</u>');					  	
					 if(userstatus=="项目经理"){
						 Ext.getCmp('Bug_Info').add(Ext.create('AM.view.ManagerBugTree')); 
						 Ext.getCmp('navigation_panel').add(Ext.create('AM.view.StaffManagePanel'));
						 Ext.getCmp('navigation_panel').add(Ext.create('AM.view.ProjectManagePanel'));
						 Ext.getCmp('navigation_panel').add(Ext.create('AM.view.SystemTab'));
					 }else if(userstatus=="测试员"){
						 Ext.getCmp('Bug_Info').add(Ext.create('AM.view.TesterBugTree')); 		
						 Ext.getCmp('navigation_panel').add(Ext.create('AM.view.SystemTab'));
					 }else if(userstatus=="开发人员"){
						 Ext.getCmp('Bug_Info').add(Ext.create('AM.view.ReplicatorBugTree')).doLayout();
						 Ext.getCmp('navigation_panel').add(Ext.create('AM.view.SystemTab'));
					 }		 
				 }else{
					 Ext.create('AM.view.LoginView').show();
				     Ext.get('loginview').setStyle('position', 'absolute').center(Ext.getBody());
				     Ext.create('Ext.container.Viewport',{
				         layout:'fit',
				         items:{
				             xtype:'mainlayout'     //组件别名才可用
				         }
				    });	
				 }
			 },
			 failure:function(response,options){
				 Ext.example.msg('提示',response.responseText+',请重试...');	  	
			 }		 
    	 });
      },
      controllers:[
          'LoginController',
          'MainController'
      ]
   });
});