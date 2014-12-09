Ext.define('AM.controller.LoginController',{
    extend:'Ext.app.Controller',
    init:function(){
        this.control({
        /*	'userlist button[id=add]':{
        		
        	}		*/  
        	'loginview':{
        		 afterrender:function(lv){
        			 lv.down("panel[id=code]").body.on('click',function(){
        				 this.dom.firstChild.src='getCode.action'+'?'+Math.random(3);
        			 });
        			 lv.down('button[id=login]').on('click',function(btn){
        				 Ext.getCmp('login_form').getForm().submit({
        					 method:'POST',
        					 timeout:3,
        					 success:function(form,action){
        					 	 if(action.result.success){
        					 	 	Ext.getCmp('loginview').hide();
        						    var username = action.result.username;
        						    var userstatus = action.result.userstatus;
        						    Ext.getCmp('username_label').setText('<u>'+username+'</u>');
        						    Ext.getCmp('userstatus_label').setText('<u>'+userstatus+'</u>');					  	
        						    if(userstatus=="项目经理"){
        							    Ext.getCmp('Bug_Info').add(Ext.create('AM.view.ManagerBugTree')); 
        							    Ext.getCmp('navigation_panel').add(Ext.create('AM.view.StaffManagePanel'));
        							    Ext.getCmp('navigation_panel').add(Ext.create('AM.view.ProjectManagePanel'));
        							    Ext.getCmp('navigation_panel').add(Ext.create('AM.view.SystemTab'));
        						    }else if(userstatus=="测试员"){
        							    Ext.getCmp('Bug_Info').add(Ext.create('AM.view.TesterBugTree')).doLayout();
        							    Ext.getCmp('navigation_panel').add(Ext.create('AM.view.SystemTab'));
        						    }else if(userstatus=="开发人员"){
        						    	Ext.getCmp('Bug_Info').add(Ext.create('AM.view.ReplicatorBugTree')).doLayout();
        						    	Ext.getCmp('navigation_panel').add(Ext.create('AM.view.SystemTab'));
        						    }
        						    Ext.example.msg('提示', '登录成功!');
        					 	 }else{
        					 	 	Ext.example.msg('登录失败',action.result.errorMessage);
        					 	 }
        						 
        					 },	 
        					 failure:function(form,action){
        						 Ext.example.msg('提示','登录出错,请重试...');
        					 },
        					 url:'login.action',
        					 waitMsg:'正在登录...'
        				 })
            		 }),
            		 lv.down('button[id=reset]').on('click',function(f){
            			 Ext.getCmp('login_form').getForm().reset();
            		 })
        		 },        		 
             }             
        });
    },
    views:[
        // 'MainLayout'  
        'LoginView'
        
    ],
    stores:[
        // 'UserStore'
    ],
    models:[
       //  'UserModel'
    ]
});