Ext.define('AM.controller.MainController',{
    extend:'Ext.app.Controller',
    init:function(){
        this.control({
          /* 'assigntabview':{
        	   activate:function(tab,eOpts){
        		      
        	   }
           },	*/
          'mainlayout':{
        		'afterrender':function(lv){
        			lv.down('button[id=loginout]').on('click',function(btn,e, eOpts){
        				Ext.MessageBox.confirm('Confirm', '确定注销用户?',function(choice){
        					if(choice=='yes'){
        						Ext.Ajax.request({
                					url:'loginout.action',
                		    		 method:'POST',
                					 timeout:4000,
                					 success:function(response,opts){
                						 Ext.example.msg('提示','注销成功!');	
                						 window.location='index.html';
                					 },
                					 failure:function(response,options){
                						 Ext.example.msg('提示','用户注销失败,请重试。。。');  						
                					 }		
                				});	
        					}				
        				});
        			});
        		}
        	},
        	'searchbugtab':{
        		'afterrender':function(lv){
        			 lv.down('button[id=all_search_submit]').on('click',function(btn,e,eOpts){
         				   var grid = lv.down('gridpanel[id=all_bug_grid]');
         				   var form = btn.up('form').getForm();
         				   var BugId = form.findField("bugId").getValue();  
         				   var BugTitle = form.findField("bugTitle").getValue(); 
         				   var ProjectId = form.findField("projectId").getValue(); 
         				   var ModularId = form.findField("modularId").getValue();
         				   if(ProjectId==""||ProjectId==null){
         					  ProjectId=0;
         				   }
         				   if(ModularId==""||ModularId==null){
         					  ModularId=0;
         				   }
                         grid.getStore().load({params:{
                              page:1,
                              bugId:BugId,
                              bugTitle:BugTitle,
                              projectId:ProjectId,
                              modularId:ModularId
                         },callback:function(records,operation,success){
                      	   if(!success){//没取到数据时返回的success=false
                                 grid.store.removeAll();//清除原有数据
                      	   }
                      	   Ext.example.msg('提示', '提交成功!');
                         }});
                     });
        		}
        	},
        	'assigntabview':{
        		'afterrender':function(lv){
        			lv.down('gridpanel[id=assign_grid]').on('selectionchange',function(model,records){
            			if (records[0]) {
            				//if(!this.ownerCt.down('panel').collapsed){
            					this.ownerCt.down('form[id=assign_form]').getForm().loadRecord(records[0]);  
            				    this.ownerCt.down('form[id=other_operation]').getForm().loadRecord(records[0]);  
            			//	}      			      
              		    }
        			});
 /*       			lv.down('panel[id=operate_panel]').on('beforeexpand',function(panel){
            			 Ext.create('Ext.dd.DropTarget', panel.body.dom, {
                               ddGroup: 'GridExample',
                               notifyEnter: function(ddSource, e, data) {
                                   panel.body.stopAnimation();
                                   panel.body.highlight();
                               },
                               notifyDrop  : function(ddSource, e, data){
                                   var selectedRecord = ddSource.dragData.records[0];
                                   panel.down('form').getForm().loadRecord(selectedRecord);
                                   return true;
                               }
                           });
        			});*/
        			lv.down('splitbutton[id=operate_splitbtn]').on('click',function(btn,e,eOpts){
        				if(btn.menu&&!btn.menu.isVisible()&&btn.getText()=="操作类型"){
            				btn.menu.show(btn.el,btn.menuAlign);
            			}else if(btn.getText()=="分配"){
            				//Ext.getCmp('operate_panel').expand();  
            			//	Ext.getCmp('assign_form').setActive(true); 
            			}else if(btn.getText()=="退回"){
            				Ext.getCmp('operate_panel').expand(); 
            			//	Ext.getCmp('other_operation').setActive(true);	
            			}else if(btn.getText()=="关闭"){
            				Ext.getCmp('operate_panel').expand();  
            			//	Ext.getCmp('other_operation').setActive(true);	
            			}else if(btn.getText()=="延期"){
            				Ext.getCmp('operate_panel').expand();  
            			}
        			});
        			lv.down('menucheckitem[id=assign_menuchekitem]').on('click',function(item,e,eOpts){
        			   if(item.isXType('menucheckitem')){    	
        				   item.ownerCt.ownerButton.addListener(eOpts);
        				   item.ownerCt.ownerButton.setText('分配');
 				       }
        			   Ext.getCmp('operate_panel').expand();   
        		   });
        			lv.down('menucheckitem[id=back_menucheckitem]').on('click',function(item,e,eOpts){
         			   if(item.isXType('menucheckitem')){
         				//   item.ownerCt.ownerButton.handler=item.handler;
         				   item.ownerCt.ownerButton.setText('退回');
  				       }
         		   });
        			lv.down('menucheckitem[id=close_menucheckitem]').on('click',function(item,e,eOpts){
         			   if(item.isXType('menucheckitem')){
         				   item.ownerCt.ownerButton.setText('关闭');
  				       }
         		   });
        			lv.down('menucheckitem[id=defer_menucheckitem]').on('click',function(item,e,eOpts){
         			   if(item.isXType('menucheckitem')){
         				  // item.ownerCt.ownerButton.handler=item.handler;
         				   item.ownerCt.ownerButton.setText('延期');
  				       }
         		   });
        		   lv.down('button[id=other_submit]').on('click',function(btn,e,eOpts){
        			   btn.up('form').getForm().submit({
      					  method:'POST',
      					  timeout:3,
      					  url:'bugReason/bugOperate.action',
    					  waitMsg:'正在提交...',
      					  success:function(form,action){
      						  if(action.result.success){
      							  var grid = lv.down('gridpanel[id=assign_grid]');
      							  var data=grid.getSelectionModel().getSelection();
      							  if(data.length!=0){
      								  grid.getStore().remove(data);
      							  }	
      							  btn.up('form').getForm().reset();
      							  Ext.example.msg('提示', '提交成功!');
      						  }else{
      							 Ext.example.msg('提示', '提交失败,请重试。。。');
      						  }      						  
      					  },	 
      					  failure:function(){
      						  Ext.example.msg('提示','服务器连接失败...');
      					  }
      				   })
          		   });
        		   lv.down('button[id=other_reset]').on('click',function(btn,e,eOpts){
        			   btn.up('form').getForm().reset();
        		   });
        		 /*  lv.down('button[id=assign_search_submit]').on('click',function(btn,e,eOpts){
       				   var grid = lv.down('gridpanel[id=assign_grid]');
       				   var form = btn.up('form').getForm();
       				   var BugId = form.findField("bugId").getValue();  
       				   var BugTitle = form.findField("bugTitle").getValue(); 
       				   var ProjectId = form.findField("projectId").getValue(); 
       				   var SubmitDay = form.findField("submitedDay").getValue(); 
                       grid.getStore().load({params:{
                            start:0,
                            limit:10,
                            page:1,
                            bugId:BugId,
                            bugTitle:BugTitle,
                            projectId:ProjectId,
                            submitedDay:SubmitDay
                       },callback:function(records,operation,success){
                    	   if(!success){//没取到数据时返回的success=false
                               grid.store.removeAll();//清除原有数据
                    	   }
                    	   Ext.example.msg('提示', '提交成功!');
                       }});
                   });*/
        		  /* lv.down('button[id=assign_search_reset]').on('click',function(btn,e,eOpts){
        			   btn.up('form').getForm().reset();
        		   });*/
        		 /*  lv.down('combobox[name=modularId]').on('beforequery',function(queryEvent,eOpts){
        	           queryEvent.query = this.up('form').getForm().findField('projectId').getValue();
        		   });*/
        		   lv.down('button[id=assign_submit]').on('click',function(btn,e,eOpts){
        			   var form = btn.up('form').getForm();
        			   var bugId = form.findField('bugId').getValue();
        			   var remark = form.findField('remark').getValue();
        			   var dealDeveloperId = form.findField('dealDeveloperid').get_delivervalue();
        			   form.submit({
      					  method:'POST',
      					  timeout:3,
      					  params:{
      					     dealDeveloperId:dealDeveloperId,
      					     bugId:bugId,
      					     remark:remark 
      					  },
      					  url:'task/assignBug.action',
    					  waitMsg:'正在提交...',
      					  success:function(form,action){
      						  if(action.result.success){
      							  var grid = lv.down('gridpanel[id=assign_grid]');
      							  var data=grid.getSelectionModel().getSelection();
      							  if(data.length!=0){
      								  grid.getStore().remove(data);
      							  }	
      							  btn.up('form').getForm().reset();
      							  Ext.example.msg('提示', '提交成功!');
      						  }else{
      							 Ext.example.msg('提示', '提交失败,请重试。。。');
      						  }      						  
      					  },	 
      					  failure:function(){
      						  Ext.example.msg('提示','服务器连接失败...');
      					  }
      				   })
          		   });  
        		}	
        	},      
           'managerbugtree':{
        	  'itemclick':function(view, record, item,index, e,eOpts ){
                    if(record.raw.id=='ToBeAssigned_item'){
                    	var tab = Ext.getCmp('assigntabpanel');
                    	var ParentPanel = Ext.getCmp('tabpanel');
                    	if(!tab){
                        	var newTab = new Ext.create('AM.view.AssignTabView');
                        	ParentPanel.add(newTab).doLayout();      
                        	ParentPanel.setActiveTab(newTab);
                       // 	Ext.data.StoreManager.get('BugInfoStore').load();  //加载数据
                        	/*Ext.getCmp('assigngrid').setLoading({
                                msg: 'Loading...',
                                useTargetEl: true
                            });*/
                        	Ext.data.StoreManager.get('ToBeAssignedBugStore').loadPage(1); 
                        	
                    	}else{
                    		ParentPanel.setActiveTab(tab);
                    	}
                    }else if(record.raw.id=='ReAssign_item'){
                    	var tab = Ext.getCmp('reassigntabpanel');
                    	var ParentPanel = Ext.getCmp('tabpanel');
                    	if(!tab){
                        	var newTab = new Ext.createWidget('reassigntabview');
                        	ParentPanel.add(newTab).doLayout();
                        	ParentPanel.setActiveTab(newTab);
                    	}else{
                    		ParentPanel.setActiveTab(tab);
                    	}
                    	Ext.data.StoreManager.lookup('ReAssignStore').load();
                    }else if(record.raw.id=='Assigned_item'){
                    	var tab = Ext.getCmp('mangersubmitedbug_tabpanel');
                    	var ParentPanel = Ext.getCmp('tabpanel');
                    	if(!tab){
                        	var newTab = new Ext.create('AM.view.MangerSubmitedBugTab');
                        	ParentPanel.add(newTab).doLayout();
                        	ParentPanel.setActiveTab(newTab);
                    	}else{
                    		ParentPanel.setActiveTab(tab);
                    	}
                    	Ext.data.StoreManager.lookup('ManagerSubmitedStore').load();
                    }else if(record.raw.id=='Search_item'){
                    	var tab = Ext.getCmp('searchbug_tabpanel');
                    	var ParentPanel = Ext.getCmp('tabpanel');
                    	if(!tab){
                        	var newTab = new Ext.create('AM.view.SearchBugTab');
                        	ParentPanel.add(newTab).doLayout();
                        	ParentPanel.setActiveTab(newTab);
                    	}else{
                    		ParentPanel.setActiveTab(tab);
                    	}
                    	Ext.data.StoreManager.lookup('SearchStore').load();
                    }
               }
           },
           'testerbugtree':{
        	   'itemclick':function(view, record, item,index, e,eOpts){
                   if(record.raw.id=='add_bug_item'){
                   	   var tab = Ext.getCmp('addbugtabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.TesterAddBugTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                   }else if(record.raw.id=='submited_bug'){
                       var tab = Ext.getCmp('testersubmitedbug_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                   	   if(!tab){
                          	var newTab = new Ext.create('AM.view.TesterSubmitedBugTab');
                       	    ParentPanel.add(newTab).doLayout();
                       	    ParentPanel.setActiveTab(newTab);
                        }else{
                   		    ParentPanel.setActiveTab(tab);
                        }
                   	   Ext.data.StoreManager.lookup('TesterBugStore').load();
                   }else if(record.raw.id=='complete_bug_item'){
                       var tab = Ext.getCmp('testercompletebug_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                   	   if(!tab){
                          	var newTab = new Ext.create('AM.view.TesterCompleteBugTab');
                       	    ParentPanel.add(newTab).doLayout();
                       	    ParentPanel.setActiveTab(newTab);
                        }else{
                   		    ParentPanel.setActiveTab(tab);
                        }
                   	  Ext.data.StoreManager.lookup('ToBeCompleteBugStore').load();
                   }else if(record.raw.id=='test_bug_item'){
                       var tab = Ext.getCmp('testbug_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                   	   if(!tab){
                          	var newTab = new Ext.create('AM.view.TestBugTab');
                       	    ParentPanel.add(newTab).doLayout();
                       	    ParentPanel.setActiveTab(newTab);
                        }else{
                   		    ParentPanel.setActiveTab(tab);
                        }
                      	Ext.data.StoreManager.lookup('ToBeCheckedBugStore').load();
                   }else if(record.raw.id=='search_bug_item'){
                	   var tab = Ext.getCmp('searchbug_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                   	   if(!tab){
                          	var newTab = new Ext.create('AM.view.SearchBugTab');
                       	    ParentPanel.add(newTab).doLayout();
                       	    ParentPanel.setActiveTab(newTab);
                        }else{
                   		    ParentPanel.setActiveTab(tab);
                        }
                      	Ext.data.StoreManager.lookup('SearchStore').load();
                   }     
        	   }
           },
           'testbugtab':{
           	   'afterrender':function(lv){
           	        lv.down('button[id=test_result_submit]').on('click',function(btn,e, eOpts){
           	        	btn.up('form').getForm().submit({
         					  method:'POST',
         					  timeout:3,
         					  url:'repairReason/add.action',
       					      waitMsg:'正在提交...',
         					  success:function(form,action){
         						  if(action.result.success){
         							  btn.up('form').getForm().reset();
         							  Ext.example.msg('提示', '提交成功!');
         						  }else{
         							 Ext.example.msg('提示', '提交失败,请重试。。。');
         						  }      						  
         					  },	 
         					  failure:function(){
         						  Ext.example.msg('提示','出错了...');
         					  }
         				   })
           	        });
           		    lv.down('button[id=tester_passed_btn]').on('click',function(btn,e, eOpts){
           		    	var grid = lv.down('grid[id=test_bug_grid]');
           		    	var checkedRecord = grid.getSelectionModel().getSelection();
       			        if(checkedRecord == 0){
       				       Ext.example.msg('提示', '没有选中任何记录!');
       			        }else{
       			           var bugId =checkedRecord[0].get('bugid');
           		    	   Ext.MessageBox.confirm('Confirm', '确定该Bug已修复?',function(choice){
        					 if(choice=='yes'){
        						 Ext.Ajax.request({
                					 url:'bug/closeBug.action',
                					 params:{bugid:bugId},
                		    		 method:'POST',
                					 timeout:4000,
                					 success:function(response,opts){
                						 Ext.example.msg('提示','成功!');	
                					 },
                					 failure:function(response,options){
                						 Ext.example.msg('提示','用户注销失败,请重试。。。');  						
                					 }		
                				});	
        					}				
        				 });
       			        }
           		    });
           	   	    var TestBackForm = lv.down('form[id=test_back_form]');
           	        Ext.create('Ext.dd.DropTarget', TestBackForm.body.dom, {
                        ddGroup: 'GridExample',
                        notifyEnter: function(ddSource, e, data) {
                              TestBackForm.body.stopAnimation();
                              TestBackForm.body.highlight();
                        },
                        notifyDrop  : function(ddSource, e, data){
                            var selectedRecord = ddSource.dragData.records[0];
                            TestBackForm.getForm().loadRecord(selectedRecord);
                                return true;
                        }
           	       })
           	   }
           },
           'testercompletebugtab':{
           	    'afterrender':function(lv){
           	         lv.down('button[id=tester_compelete_btn]').on('click',function(btn,e, eOpts){
           	         	 var grid = Ext.getCmp('compelete_bug_grid');
        			     var checkedRecord = grid.getSelectionModel().getSelection();
        			     if(checkedRecord == 0){
        				     Ext.example.msg('提示', '没有选中任何记录!');
        			     }else{
        				     var win = Ext.getCmp('testercomplet_window');
                             if(!win){
                                var win = new Ext.create('AM.view.TesterCompleteWindow');
                             }
                       	     win.show();
                       	     var formPanel= win.down('form');
                             formPanel.getForm().loadRecord(checkedRecord[0]); 
                             Ext.create('Ext.dd.DropTarget', formPanel.body.dom, {
                                 ddGroup: 'GridExample',
                                 notifyEnter: function(ddSource, e, data) {
                                   formPanel.body.stopAnimation();
                                   formPanel.body.highlight();
                              },
                              notifyDrop  : function(ddSource, e, data){
                                   var selectedRecord = ddSource.dragData.records[0];
                                   formPanel.getForm().loadRecord(selectedRecord);
                                   return true;
                              }
                           });
                           
        			   }
           	         });
           	    }
           },
           'testercompletewindow':{
        	   'afterrender':function(lv){
               	lv.down('combobox[name=modularName]').on('beforequery',function(queryEvent,eOpts){
               		var ProjectId =this.up('form').getForm().findField('projectid').getValue();
    				    var ProjectName = this.up('form').getForm().findField('projectName').getValue();
    				    if(typeof(ProjectName)!="string"){
   				    	  ProjectId=ProjectName;
   				    }
               		queryEvent.query = ProjectId;
        		    });
        		    lv.down('combobox[name=projectName]').on('change',function(field,newValue,oldValue,eOpts){
       	           this.up('form').getForm().findField('modularName').clearValue();
       	           this.up('form').getForm().findField('versionName').clearValue();
       		   });
               	lv.down('combobox[name=versionName]').on('beforequery',function(queryEvent,eOpts){
               		var ProjectId =this.up('form').getForm().findField('projectid').getValue();
    				    var ProjectName = this.up('form').getForm().findField('projectName').getValue();
    				    if(typeof(ProjectName)!="string"){
   				    	  ProjectId=ProjectName;
   				    }
    				    queryEvent.query = ProjectId;
        		    });
                   lv.down('button[id=compelete_submit]').on('click',function(btn,e, eOpts){
                   	      var form = btn.up('form').getForm();
                   	      var Id = form.findField("id").getValue(); 
      				      var BugTitle = form.findField("bugTitle").getValue(); 
      				      var ProjectId = form.findField("projectid").getValue(); 
      				      var ProjectName = form.findField("projectName").getValue();
      				      var ModularId = form.findField("modularid").getValue();
      				      var ModularName = form.findField("modularName").getValue();
      				      var VersionId = form.findField("versionId").getValue();
      				      var VersionName = form.findField("versionName").getValue();
      				      var BugEnvironment = form.findField("bugEnvironment").getValue();
      				      var BugExpected = form.findField("bugExpected").getValue();
      				      var BugResult = form.findField("bugResult").getValue();
      				      var BugDetail = form.findField("bugDetail").getValue();			      
      				      if(typeof(ProjectName)!="string"){
      				    	  ProjectId=ProjectName;
      				      }
      				      if(typeof(ModularName)!="string"){
      				    	  ModularId=ModularName;
   				          }
      				      if(typeof(VersionName)!="string"){
   				    	     VersionId=VersionName;
				          }
      				      form.submit({
      					       method:'POST',
      					       timeout:3,
      					       url:'bug/updateSubmited.action',
      					       params:{
      					       	  _bugId:Id,
      					       	  bugTitle:BugTitle,
      					       	  projectId:ProjectId,
      					       	  modularId:ModularId,
      					       	  versionid:VersionId,
      					       	  bugEnvironment:BugEnvironment,
      					       	  bugExpected:BugExpected,
      					       	  bugResult:BugResult,
      					       	  bugDetail:BugDetail
      					       },
    					       waitMsg:'正在提交...',
      					       success:function(form,action){
      						      if(action.result.success){
      						      	 Ext.getCmp('compelete_bug_grid').getStore().load();
      							     btn.up('form').getForm().reset();
      							     Ext.example.msg('提示', '修改成功!');
      						      }else{
      							     Ext.example.msg('提示', '提交失败,请重试。。。');
      						      }      						  
      					       },	 
      					       failure:function(){
      						        Ext.example.msg('error','出错了，请重试...');
      					       }
      				   })
                        
                   })
                }
           },
           'testerupdatewindow':{                    //编辑
                'afterrender':function(lv){
                	lv.down('combobox[name=modularName]').on('beforequery',function(queryEvent,eOpts){
                		var ProjectId =this.up('form').getForm().findField('projectid').getValue();
     				    var ProjectName = this.up('form').getForm().findField('projectName').getValue();
     				    if(typeof(ProjectName)!="string"){
    				    	  ProjectId=ProjectName;
    				    }
                		queryEvent.query = ProjectId;
         		    });
         		    lv.down('combobox[name=projectName]').on('change',function(field,newValue,oldValue,eOpts){
        	           this.up('form').getForm().findField('modularName').clearValue();
        	           this.up('form').getForm().findField('versionName').clearValue();
        		   });
                	lv.down('combobox[name=versionName]').on('beforequery',function(queryEvent,eOpts){
                		var ProjectId =this.up('form').getForm().findField('projectid').getValue();
     				    var ProjectName = this.up('form').getForm().findField('projectName').getValue();
     				    if(typeof(ProjectName)!="string"){
    				    	  ProjectId=ProjectName;
    				    }
     				    queryEvent.query = ProjectId;
         		    });
                    lv.down('button[id=bug_update_submit]').on('click',function(btn,e, eOpts){
                    	  var form = btn.up('form').getForm();
                    	  var Id = form.findField("id").getValue(); 
       				      var BugTitle = form.findField("bugTitle").getValue(); 
       				      var ProjectId = form.findField("projectid").getValue(); 
       				      var ProjectName = form.findField("projectName").getValue();
       				      var ModularId = form.findField("modularid").getValue();
       				      var ModularName = form.findField("modularName").getValue();
       				      var VersionId = form.findField("versionId").getValue();
       				      var VersionName = form.findField("versionName").getValue();
       				      var BugEnvironment = form.findField("bugEnvironment").getValue();
       				      var BugExpected = form.findField("bugExpected").getValue();
       				      var BugResult = form.findField("bugResult").getValue();
       				      var BugDetail = form.findField("bugDetail").getValue();			      
       				      if(typeof(ProjectName)!="string"){
       				    	  ProjectId=ProjectName;
       				      }
       				      if(typeof(ModularName)!="string"){
       				    	  ModularId=ModularName;
    				      }
       				      if(typeof(VersionName)!="string"){
    				    	  VersionId=VersionName;
 				          }
       				      form.submit({
       					       method:'POST',
       					       timeout:3,
       					       url:'bug/updateSubmited.action',
       					       params:{
       					       	  _bugId:Id,
       					       	  bugTitle:BugTitle,
       					       	  projectId:ProjectId,
       					       	  modularId:ModularId,
       					       	  versionid:VersionId,
       					       	  bugEnvironment:BugEnvironment,
       					       	  bugExpected:BugExpected,
       					       	  bugResult:BugResult,
       					       	  bugDetail:BugDetail
       					       },
     					       waitMsg:'正在提交...',
       					       success:function(form,action){
       						      if(action.result.success){
       						      	 Ext.getCmp('submitedbug_grid').getStore().load();
       							     btn.up('form').getForm().reset();
       							     Ext.example.msg('提示', '修改成功!');
       						      }else{
       							     Ext.example.msg('提示', '提交失败,请重试。。。');
       						      }      						  
       					       },	 
       					       failure:function(){
       						        Ext.example.msg('error','出错了，请重试...');
       					       }
       				   })
                         
                    })
                 }
           },
           'testersubmitedbugtab':{
        	   'afterrender':function(lv){
        		   lv.down('button[id=tester_add_btn]').on('click',function(btn,e, eOpts){
        			   var tab = Ext.getCmp('addbugtabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                         	var newTab = new Ext.create('AM.view.TesterAddBugTab');
                         	ParentPanel.add(newTab).doLayout();      
                       	    ParentPanel.setActiveTab(newTab); 	
                       }else{
                   		  ParentPanel.setActiveTab(tab);
                   	   }
        		   });
        		    lv.down('combobox[name=modularId]').on('beforequery',function(queryEvent,eOpts){
        	           queryEvent.query = this.up('form').getForm().findField('projectId').getValue();
        		    });
        		   lv.down('button[id=submitedbug_search_submit]').on('click',function(btn,e,eOpts){
       				   var grid = lv.down('gridpanel[id=submitedbug_grid]');
       				   var form = btn.up('form').getForm();
       				   var BugId = form.findField("bugId").getValue();  
       				   var BugTitle = form.findField("bugTitle").getValue(); 
       				   var ProjectId = form.findField("projectId").getValue(); 
       				   var ModularId = form.findField("modularId").getValue();
       				   if(ProjectId==""||ProjectId==null){
       					  ProjectId=0;
       				   }
       				   if(ModularId==""||ModularId==null){
       					  ModularId=0;
       				   }
                       grid.getStore().load({params:{
                            page:1,
                            bugId:BugId,
                            bugTitle:BugTitle,
                            projectId:ProjectId,
                            modularId:ModularId
                       },callback:function(records,operation,success){
                    	   if(!success){//没取到数据时返回的success=false
                               grid.store.removeAll();//清除原有数据
                    	   }
                    	   Ext.example.msg('提示', '提交成功!');
                       }});
                   });
        		  
        		   lv.down('button[id=tester_update_btn]').on('click',function(btn,e, eOpts){
        			   var grid = Ext.getCmp('submitedbug_grid');
        			   var checkedRecord = grid.getSelectionModel().getSelection();
        			   if(checkedRecord == 0){
        				   Ext.example.msg('提示', '没有选中任何记录!');
        			   }else if(checkedRecord[0].get('stateName')=="待完善"){
        			   	   Ext.example.msg('提示', '请选择待完善模块操作!');  
        			   }else if(checkedRecord[0].get('stateName')!="待分配"){
                           Ext.example.msg('提示', '该bug不能修改!');       			   	
        			   }else{
        				   var win = Ext.getCmp('tester_update_window');
                           if(!win){
                               var win = new Ext.create('AM.view.TesterUpdateWindow');
                           }
                       	   win.show();
                       	   var formPanel= win.down('form');
                           formPanel.getForm().loadRecord(checkedRecord[0]); 
                           Ext.create('Ext.dd.DropTarget', formPanel.body.dom, {
                               ddGroup: 'GridExample',
                               notifyEnter: function(ddSource, e, data) {
                                   formPanel.body.stopAnimation();
                                   formPanel.body.highlight();
                               },
                               notifyDrop :function(ddSource, e, data){  	
                               	   	var selectedRecord = ddSource.dragData.records[0];
                               	   	if(selectedRecord.get('stateName')!="待分配"){
                               	   	    Ext.example.msg('提示', '该bug不能修改!'); 
                               	        return true;
                               	   	}else{
                               	   	   	formPanel.getForm().loadRecord(selectedRecord);
                                        return true;
                               	   }  
                               }
                           });
                           
        			   }
        		   });
        		   
        	   }
           },
           'testeraddbugtab':{
        	   'afterrender':function(lv){
        	   	   lv.down('combobox[name=modularId]').on('beforequery',function(queryEvent,eOpts){
        	           queryEvent.query = this.up('form').getForm().findField("projectId").getValue();
        		   });
        		   lv.down('combobox[name=versionId]').on('beforequery',function(queryEvent,eOpts){
        	           queryEvent.query = this.up('form').getForm().findField('projectId').getValue();
        		   });
        		   lv.down('combobox[name=projectId]').on('change',function(field,newValue,oldValue,eOpts){
        	           this.up('form').getForm().findField('modularId').clearValue();
        	           this.up('form').getForm().findField('versionId').clearValue();
        		   });
        		   lv.down('button[id=bug_add_submit]').on('click',function(btn,e, eOpts){
        			  btn.up('form').getForm().submit({
       					  method:'POST',
       					  timeout:3,
       					  url:'bug/add.action',
     					  waitMsg:'正在提交...',
       					  success:function(form,action){
       						  if(action.result.success){
       							 /* var grid = lv.down('gridpanel[id=assigngrid]');
       							  var data=grid.getSelectionModel().getSelection();
       							  if(data.length!=0){
       								  grid.getStore().remove(data);
       							  }	*/
       							  btn.up('form').getForm().reset();
       							  Ext.example.msg('提示', '提交成功!');
       						  }else{
       							 Ext.example.msg('提示', '提交失败,请重试。。。');
       						  }      						  
       					  },	 
       					  failure:function(){
       						  Ext.example.msg('提示','Bug报告未填完善...');
       					  }
       				   })
        		   });
        	   }
          },
          'replicatorbugtree':{
              'itemclick':function(view, record, item,index, e,eOpts){
                   if(record.raw.id=='ToBeRepaire_item'){
                   	   var tab = Ext.getCmp('toberepair_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.ToBeRepairTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                       Ext.data.StoreManager.lookup('ToBeRepairedStore').load();
                   }else if(record.raw.id=='replicator_submited_item'){
                       var tab = Ext.getCmp('replicatorsubmitedbugtab');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.ReplicatorSubmitedBugTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                       Ext.data.StoreManager.lookup('ReplicatorSubmitedStore').load();
                   }else if(record.raw.id=='Rerepare_bug_item'){
                       var tab = Ext.getCmp('rerepair_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                           var newTab = new Ext.create('AM.view.ReRepairTab');
                           ParentPanel.add(newTab).doLayout();      
                           ParentPanel.setActiveTab(newTab);
                       }else{
                       ParentPanel.setActiveTab(tab);
                       }
                       Ext.data.StoreManager.lookup('ReRepairedStore').load();
                   }else if(record.raw.id=='replicator_search_item'){
                     	var tab = Ext.getCmp('searchbug_tabpanel');
                	    var ParentPanel = Ext.getCmp('tabpanel');
                	    if(!tab){
                    	    var newTab = new Ext.create('AM.view.SearchBugTab');
                    	    ParentPanel.add(newTab).doLayout();
                    	    ParentPanel.setActiveTab(newTab);
                	    }else{
                		    ParentPanel.setActiveTab(tab);
                	    }
                    	Ext.data.StoreManager.lookup('SearchStore').load();
                }
              }
          },
          'repairreportwindow':{
        	  'afterrender':function(lv){
        		  lv.down('button[id=repairscheme_submit]').on('click',function(btn,e,eOpts){
        			  btn.up('form').getForm().submit({
       					  method:'POST',
       					  timeout:3,
       					  url:'repair/add.action',
     					  waitMsg:'正在提交...',
       					  success:function(form,action){
       						  if(action.result.success){
       						      Ext.getCmp('toberepair_grid').getStore().load();
       							  btn.up('form').getForm().reset();
       							  Ext.example.msg('提示', '提交成功!');
       						  }else{
       							 Ext.example.msg('提示', '提交失败,请重试。。。');
       						  }      						  
       					  },	 
       					  failure:function(){
       						  Ext.example.msg('提示','出错了...');
       					  }
       				   })
        		  });
        	  }
          },
          
          'toberepairtab':{
               'afterrender':function(lv){
            	    var rejectForm = lv.down('form[id=reject_form]');
              	    Ext.create('Ext.dd.DropTarget', rejectForm.body.dom, {
                             ddGroup: 'GridExample',
                             notifyEnter: function(ddSource, e, data) {
                                rejectForm.body.stopAnimation();
                                rejectForm.body.highlight();
                             },
                             notifyDrop  : function(ddSource, e, data){
                                var selectedRecord = ddSource.dragData.records[0];
                                rejectForm.getForm().loadRecord(selectedRecord);
                                    return true;
                            }
                    });
              	    lv.down('button[id=reject_submit]').on('click',function(btn,e,eOpts){
        			  btn.up('form').getForm().submit({
       					  method:'POST',
       					  timeout:3,
       					  url:'taskReason/add.action',
     					  waitMsg:'正在提交...',
       					  success:function(form,action){
       						  if(action.result.success){
       							  Ext.getCmp('toberepair_grid').getStore().load();
       							  btn.up('form').getForm().reset();
       							  Ext.example.msg('提示', '提交成功!');
       						  }else{
       							 Ext.example.msg('提示', '提交失败,请重试。。。');
       						  }      						  
       					  },	 
       					  failure:function(){
       						  Ext.example.msg('提示','出错了...');
       					  }
       				   })
        		    });
                    lv.down('button[id=add_repairreport_btn]').on('click',function(btn,e,eOpts){
                       var grid = Ext.getCmp('toberepair_grid');
        			   var checkedRecord = grid.getSelectionModel().getSelection();
        			   if(checkedRecord == 0){
        				   Ext.example.msg('提示', '没有选中任何记录!');
        			   }else{
                    	  var win = Ext.getCmp('repairreport_window');
                          if(!win){
                              var win = new Ext.create('AM.view.RepairReportWindow');
                          }
                       	  win.show();
                       	  var formPanel= win.down('form');
                          formPanel.getForm().loadRecord(checkedRecord[0]); 
                          Ext.create('Ext.dd.DropTarget', formPanel.body.dom, {
                              ddGroup: 'GridExample',
                              notifyEnter: function(ddSource, e, data) {
                                 formPanel.body.stopAnimation();
                                 formPanel.body.highlight();
                              },
                              notifyDrop  : function(ddSource, e, data){
                                 var selectedRecord = ddSource.dragData.records[0];
                                 formPanel.getForm().loadRecord(selectedRecord);
                                     return true;
                             }
                         });
        			   }
                    })
               }
        	   	   
          },
          'staffmanagepanel>treepanel':{
               'itemclick':function(view, record, item,index, e,eOpts){
                   if(record.raw.id=='staffmanage_item'){
                   	   var tab = Ext.getCmp('staffmanage_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.StaffManageTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                       Ext.data.StoreManager.lookup('StaffInfoStore').load();
                   }else if(record.raw.id=='authoritysmanage_item'){
                       var tab = Ext.getCmp('authoritymanage_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.AuthorityManageTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                   }
               }
          },
          'staffmanagetab':{
              'afterrender':function(lv){
              	  var StaffStateForm = lv.down('form[id=staff_state_form]');
                  Ext.create('Ext.dd.DropTarget', StaffStateForm.body.dom, {
                     ddGroup: 'GridExample',
                     notifyEnter: function(ddSource, e, data) {
                        StaffStateForm.body.stopAnimation();
                        StaffStateForm.body.highlight();
                     },
                     notifyDrop  : function(ddSource, e, data){
                        var selectedRecord = ddSource.dragData.records[0];
                        StaffStateForm.loadRecord(selectedRecord);
                            return true;
                     }
                  });
                  lv.down('button[id=staff_add_btn]').on('click',function(btn,e, eOpts){
                 	    var win = Ext.getCmp('staffadd_window');
                        if(!win){
                            var win = new Ext.create('AM.view.StaffAddWindow');
                        }
                      	 win.show();
                  });
                  lv.down('button[id=staff_update_btn]').on('click',function(btn,e, eOpts){
                	 var grid = Ext.getCmp('staff_grid');
     			     var checkedRecord = grid.getSelectionModel().getSelection();
     			     if(checkedRecord == 0){
     				     Ext.example.msg('提示', '没有选中任何记录!');
     			     }else{
                 	     var win = Ext.getCmp('staffupdate_window');
                         if(!win){
                            var win = new Ext.create('AM.view.StaffUpdateWindow');
                         }
                      	 win.show();
                      	 var formPanel= win.down('form');
                         formPanel.getForm().loadRecord(checkedRecord[0]); 
                         Ext.create('Ext.dd.DropTarget', formPanel.body.dom, {
                             ddGroup: 'GridExample',
                             notifyEnter: function(ddSource, e, data) {
                            	 formPanel.body.stopAnimation();
                            	 formPanel.body.highlight();
                          },
                          notifyDrop  : function(ddSource, e, data){
                               var selectedRecord = ddSource.dragData.records[0];
                               formPanel.getForm().loadRecord(selectedRecord);
                               return true;
                          }
                         });
     			     }
                  });
              }
         },
         'staffupdatewindow':{
         	  'afterrender':function(lv){
         	       lv.down('button[id=staff_update_submit]').on('click',function(btn,e, eOpts){
                     btn.up('form').getForm().submit({
      					  method:'POST',
      					  timeout:3,
      					  url:'user/update.action',
    					  waitMsg:'正在提交...',
      					  success:function(form,action){
      						  if(action.result.success){
      							  Ext.getCmp('staff_grid').getStore().load();
      							  btn.up('form').getForm().reset();
      							  Ext.example.msg('提示', '提交成功!');
      						  }else{
      							 Ext.example.msg('提示', '提交失败,请重试。。。');
      						  }      						  
      					  },	 
      					  failure:function(){
      						  Ext.example.msg('提示','服务器连接失败...');
      					  }
      				   })
       		       });   
         	  }   
         },
         'staffaddwindow':{
             'afterrender':function(lv){
                   lv.down('button[id=staff_add_submit]').on('click',function(btn,e, eOpts){
                     btn.up('form').getForm().submit({
      					  method:'POST',
      					  timeout:3,
      					  url:'user/save.action',
    					  waitMsg:'正在提交...',
      					  success:function(form,action){
      						  if(action.result.success){
      							  Ext.getCmp('staff_grid').getStore().load();
      							  btn.up('form').getForm().reset();
      							  Ext.example.msg('提示', '提交成功!');
      						  }else{
      							 Ext.example.msg('提示', '提交失败,请重试。。。');
      						  }      						  
      					  },	 
      					  failure:function(){
      						  Ext.example.msg('提示','服务器连接失败...');
      					  }
      				   })
       		 });
             }
         },
          'projectmanagepanel>treepanel':{
          	  'itemclick':function(view, record, item,index, e,eOpts){
                   if(record.raw.id=='projectmanage_item'){
                   	   var tab = Ext.getCmp('projectmanage_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.ProjectManageTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                       Ext.data.StoreManager.lookup('ProjectInfoStore').load();
                   }else if(record.raw.id=='bugversion_item'){
                   	   var tab = Ext.getCmp('projectversion_tabpanel');
                       var ParentPanel = Ext.getCmp('tabpanel');
                       if(!tab){
                       	   var newTab = new Ext.create('AM.view.ProjectVersionTab');
                           ParentPanel.add(newTab).doLayout();      
                       	   ParentPanel.setActiveTab(newTab);
                   	   }else{
                   		   ParentPanel.setActiveTab(tab);
                   	   }
                       Ext.data.StoreManager.lookup('ProjectInfoStore').load();
                   }  
          	  }
          },
          'projectversiontab':{
              'afterrender':function(lv){
                   var ProjectVersionForm = lv.down('form[id=projectversion_update_form]');
          	       Ext.create('Ext.dd.DropTarget', ProjectVersionForm.body.dom, {
                      ddGroup: 'GridExample',
                      notifyEnter: function(ddSource, e, data) {
                         ProjectVersionForm.body.stopAnimation();
                         ProjectVersionForm.body.highlight();
                      },
                      notifyDrop:function(ddSource, e, data){
                        var selectedRecord = ddSource.dragData.records[0];
                        ProjectVersionForm.loadRecord(selectedRecord);
                            return true;
                      }
                  });
          	     var VersionFormUpgrade = lv.down('form[id=projectversion_upgrade_form]');
        	       Ext.create('Ext.dd.DropTarget', VersionFormUpgrade.body.dom, {
                    ddGroup: 'GridExample',
                    notifyEnter: function(ddSource, e, data) {
                    	VersionFormUpgrade.body.stopAnimation();
                    	VersionFormUpgrade.body.highlight();
                    },
                    notifyDrop  : function(ddSource, e, data){
                      var selectedRecord = ddSource.dragData.records[0];
                      VersionFormUpgrade.loadRecord(selectedRecord);
                          return true;
                    }
                });
          	     lv.down('grid[id=project_grid]').on('selectionchange',function(model,selected,eOpts ){
          	    	if(selected!=0){
        			   var versionGrid =lv.down('grid[id=version_grid]');
        			   var ProjectId = selected[0].get('projectid');
        			   versionGrid.getStore().load({params:{
                            page:1,
                            query:ProjectId
                        },callback:function(records,operation,success){
                    	   if(!success){
                               versionGrid.store.removeAll();
                    	   }
                       }});
          	    	}
        		      
        		 });
          	     lv.down('button[id= projectversion_add_submit]').on('click',function(btn,e, eOpts){ 
          	       var ProjectId = btn.up('form').getForm().findField("projectid").getValue(); 
                   btn.up('form').getForm().submit({
    					  method:'POST',
    					  timeout:3,
    					  url:'version/add.action',
  					      waitMsg:'正在提交...',
    					  success:function(form,action){
    						  if(action.result.success){
    							  Ext.getCmp('version_grid').getStore().load({params:{query:ProjectId}});
    							  btn.up('form').getForm().reset();
    							  Ext.example.msg('提示', '提交成功!');
    						  }else{
    							 Ext.example.msg('提示', '提交失败,请重试。。。');
    						  }      						  
    					  },	 
    					  failure:function(){
    						  Ext.example.msg('提示','出错了...');
    					  }
    				   })
               });        	   
              }
          },
          'reassigntabview':{
        	  'afterrender':function(lv){
        		  var ReAssginForm = lv.down('form[id=reassign_form]');
        		  Ext.create('Ext.dd.DropTarget', ReAssginForm.body.dom, {
                      ddGroup: 'GridExample',
                      notifyEnter: function(ddSource, e, data) {
                    	  ReAssginForm.body.stopAnimation();
                    	  ReAssginForm.body.highlight();
                      },
                      notifyDrop  : function(ddSource, e, data){
                         var selectedRecord = ddSource.dragData.records[0];
                         ReAssginForm.loadRecord(selectedRecord);
                             return true;
                      }
                  });
        		  lv.down('button[id=reassign_submit]').on('click',function(btn,e,eOpts){
       			   var form = btn.up('form').getForm();
       			   var bugId = form.findField('bugid').getValue();
       			   var remark = form.findField('remark').getValue();
       			   var dealDeveloperId = form.findField('dealDeveloperid').get_delivervalue();
       			   form.submit({
     					  method:'POST',
     					  timeout:3,
     					  params:{
     					     dealDeveloperId:dealDeveloperId,
     					     bugid:bugId,
     					     remark:remark 
     					  },
     					  url:'task/assignBug.action',
   					      waitMsg:'正在提交...',
     					  success:function(form,action){
     						  if(action.result.success){
     							  var grid = lv.down('gridpanel[id=reassign_grid]');
     							  var data=grid.getSelectionModel().getSelection();
     							  if(data.length!=0){
     								  grid.getStore().remove(data);
     							  }	
     							  btn.up('form').getForm().reset();
     							  Ext.example.msg('提示', '提交成功!');
     						  }else{
     							 Ext.example.msg('提示', '提交失败,请重试。。。');
     						  }      						  
     					  },	 
     					  failure:function(){
     						  Ext.example.msg('提示','服务器连接失败...');
     					  }
     				   })
         		   });  
        		  
        		  
        	  }
          },
          'projectmanagetab':{
          	  'afterrender':function(lv){
          	     var ProjectForm = lv.down('form[id=project_update_form]');
          	     var ModualrForm = lv.down('form[id=modular_update_form]');
          	     var ModualrAddForm = lv.down('form[id=modular_add_form]');
          	     Ext.create('Ext.dd.DropTarget', ProjectForm.body.dom, {
                     ddGroup: 'GridExample',
                     notifyEnter: function(ddSource, e, data) {
                         ProjectForm.body.stopAnimation();
                         ProjectForm.body.highlight();
                     },
                     notifyDrop  : function(ddSource, e, data){
                        var selectedRecord = ddSource.dragData.records[0];
                        ProjectForm.loadRecord(selectedRecord);
                            return true;
                     }
                 });
                 Ext.create('Ext.dd.DropTarget', ModualrForm.body.dom, {
                     ddGroup: 'GridExample',
                     notifyEnter: function(ddSource, e, data) {
                         ModualrForm.body.stopAnimation();
                         ModualrForm.body.highlight();
                     },
                     notifyDrop  : function(ddSource, e, data){
                        var selectedRecord = ddSource.dragData.records[0];
                        ModualrForm.loadRecord(selectedRecord);
                            return true;
                     }
                 });
                 Ext.create('Ext.dd.DropTarget', ModualrAddForm.body.dom, {
                     ddGroup: 'GridExample',
                     notifyEnter: function(ddSource, e, data) {
                    	 ModualrAddForm.body.stopAnimation();
                    	 ModualrAddForm.body.highlight();
                     },
                     notifyDrop  : function(ddSource, e, data){
                        var selectedRecord = ddSource.dragData.records[0];
                        ModualrAddForm.loadRecord(selectedRecord);
                            return true;
                     }
                 });
          	  	 lv.down('button[id=project_add_submit]').on('click',function(btn,e, eOpts){
                      btn.up('form').getForm().submit({
       					  method:'POST',
       					  timeout:3,
       					  url:'project/add.action',
     					  waitMsg:'正在提交...',
       					  success:function(form,action){
       						  if(action.result.success){
       							  Ext.getCmp('project_gridpanel').getStore().load();
       							  btn.up('form').getForm().reset();
       							  Ext.example.msg('提示', '提交成功!');
       						  }else{
       							 Ext.example.msg('提示', '提交失败,请重试。。。');
       						  }      						  
       					  },	 
       					  failure:function(){
       						  Ext.example.msg('提示','服务器连接失败...');
       					  }
       				   })
        		 });
          	   	lv.down('button[id=project_update_submit]').on('click',function(btn,e, eOpts){
          	   	    var form = btn.up('form').getForm();
          	   		var userid = form.findField("managerid").getValue();
				      var userName = form.findField("managerName").getValue();			      
				      if(typeof(userName)!="string"){
				    	  userid=userName;
				      }
				      form.submit({
     					  method:'POST',
     					  timeout:3000,
     					  url:'project/update.action',
     					  params:{managerId:userid},
   					      waitMsg:'正在提交...',
     					  success:function(form,action){
     						  if(action.result.success){
     							  Ext.getCmp('project_gridpanel').getStore().load();
     							  btn.up('form').getForm().reset();
     							  Ext.example.msg('提示', '提交成功!');
     						  }else{
     							 Ext.example.msg('提示', '提交失败,请重试。。。');
     						  }      						  
     					  },	 
     					  failure:function(){
     						  Ext.example.msg('提示','服务器连接失败...');
     					  }
     				   })
      		     });
          	     lv.down('button[id=modular_add_submit]').on('click',function(btn,e, eOpts){
          	      var projectId = btn.up('form').getForm().findField("projectid").getValue();
                  btn.up('form').getForm().submit({
   					  method:'POST',
   					  timeout:3,
   					  url:'modular/add.action',
 					  waitMsg:'正在提交...',
   					  success:function(form,action){
   						  if(action.result.success){
   							  Ext.getCmp('modular_gridpanel').getStore().load({params:{query:projectId}});
   							  btn.up('form').getForm().reset();
   							  Ext.example.msg('提示', '提交成功!');
   						  }else{
   							 Ext.example.msg('提示', '提交失败,请重试。。。');
   						  }      						  
   					  },	 
   					  failure:function(){
   						  Ext.example.msg('提示','服务器连接失败...');
   					  }
   				   })
    		     });     
          	   lv.down('button[id=modular_update_submit]').on('click',function(btn,e, eOpts){
         	   	    var form = btn.up('form').getForm();
         	   		var userid = form.findField("developerid").getValue();
				      var userName = form.findField("developerName").getValue();			      
				      if(typeof(userName)!="string"){
				    	  userid=userName;
				      }
				      form.submit({
    					  method:'POST',
    					  timeout:3000,
    					  url:'modular/update.action',
    					  params:{developerId:userid},
  					      waitMsg:'正在提交...',
    					  success:function(form,action){
    						  if(action.result.success){
    							//  Ext.getCmp('modular_gridpanel').getStore().load();
    							  btn.up('form').getForm().reset();
    							  Ext.example.msg('提示', '提交成功!');
    						  }else{
    							 Ext.example.msg('提示', '提交失败,请重试。。。');
    						  }      						  
    					  },	 
    					  failure:function(){
    						  Ext.example.msg('提示','服务器连接失败...');
    					  }
    				   })
     		     });
        		 lv.down('button[id=project_search_submit]').on('click',function(btn,e, eOpts){
                      var grid = lv.down('gridpanel[id=project_grid]');
                      var SearchValue = Ext.getCmp('project_search_value').getValue();
                      var SearchType = Ext.getCmp('project_search_type').getValue();
                       grid.getStore().load({params:{
                            page:1,
                            SearchValue:SearchValue,
                            SearchType:SearchType
                       },callback:function(records,operation,success){
                    	   if(!success){//没取到数据时返回的success=false
                               grid.store.removeAll();//清除原有数据
                    	   }
                    	   Ext.example.msg('提示', '提交成功!');
                       }});
        		 });
        		 lv.down('grid[id=project_gridpanel]').on('selectionchange',function(model,selected,eOpts ){
        			 if(selected!=0){
        				 var modularGrid =lv.down('grid[id=modular_gridpanel]'); 
            			 var ProjectId = selected[0].get('projectid');
            			 modularGrid.getStore().load({params:{
                                page:1,
                                query:ProjectId
                           },callback:function(records,operation,success){
                        	   if(!success){
                                   modularGrid.store.removeAll();
                        	   }
                           }});
        			 }
        			    
        		 });
          	  }  
          }
           
        });
    },
    views:[
         'MainLayout',
         'HomeTabView',
         'ManagerBugTree',
         'AssignTabView',
         'ReAssignTabView',
         'ReplicatorCombobox',
         'TesterBugTree',
         'ReplicatorBugTree'
    ],
    stores:[
        'BugHandleStore',
        'ProjectStore',
        'ModularStore',
        'StaffManageStore',
        'ProjectManageStore',
        'TesterBugStore',
        'ProjectInfoStore',
        'ManagerInfoStore',
        'RelicatorInfoStore',
        'StaffInfoStore',
        'ProjectVersionStore',
        'ToBeCompleteBugStore',
        'ToBeAssignedBugStore',
        'BugSeverityStore',
        'BugEnvironmentStore',
        'ManagerSubmitedStore',
        'ToBeRepairedStore',
        'ReplicatorSubmitedStore',
        'ModularInfoStore',
        'VersionInfoStore',
        'ToBeCheckedBugStore',
        'ReAssignStore',
        'ReRepairedStore',
        'SearchStore'
        
    ],
    models:[
    ]
});