Ext.define('AM.view.StaffManageTab', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.staffmanagetab', // 别名
	id : 'staffmanage_tabpanel',
	title : '员工管理',
	closable : true,
	layout : {
		type : 'vbox',
		align : 'stretch',
		pack : 'start'
	},
	items : [ {
		xtype : 'panel',
		title : '查询',
		// collapsed:true, //默认收缩
		height : 200,
		collapsible : true,
		items : [ {
			xtype : 'form',
			// id:'search_form',
			layout : {
				type : 'hbox',
				align : 'stretch', // vbox:[left,center,stretch,stretchmax]
				pack : 'start' // 控制子元素展示的位置,start左面，center中间，end右面
			},
			defaults : {
				msgTarget : 'side',
				padding : '10 10 10 30',
				border : 0
			},
			items : [ {
				xtype : 'panel',
				defaults : {
					labelWidth : 60,
					selectOnFocus : true,
					labelSeparator : ':'
				},
				defaultType : 'textfield',
				items : [ {
					fieldLabel : '员工编号',
					name : 'userId'
				}, {
					fieldLabel : '员工姓名',
					name : 'userName'
				}, {
					xtype : 'combobox',
					fieldLabel : '性别',
					editable : false, // 禁止手写及联想功能
					queryMode : 'local',
					displayField : 'sex',
					store : new Ext.data.Store({
						fields : [ {
							name : 'sex'
						} ],
						data : [ {
							sex : '男'
						}, {
							sex : '女'
						} ]
					})
				} ]
			}, {
				xtype : 'panel',
				padding : '10 10 10 30',
				defaults : {
					labelWidth : 60,
					selectOnFocus : true,
					labelSeparator : ':',
					padding : '10 30 10 10'
				},
				items : [ {
					xtype : 'combobox',
					fieldLabel : '职位',
					name : 'post',
					editable : false, // 禁止手写及联想功能
					queryMode : 'local',
					displayField : 'post',
					store : new Ext.data.Store({
						fields : [ {
							name : 'post'
						} ],
						data : [ {
							post : '测试员'
						}, {
							post : '项目经理'
						}, {
							post : '开发人员'
						} ]
					})
				}, {
					xtype : 'combobox',
					name : 'ModularId',
					fieldLabel : '是否在职',
					listConfig : { // 控制
						emptyText : '没有找到匹配的数值..',
						maxHeight : 200, // 超过会出现滚动条
						loadingText : '正在加载...',
						getInnerTpl : function() { // 模版功能
							return "<div>{ModularId}.{ModularName}</div>"
						}
					},
					store : 'ModularStore',
					typeAhead : true,
					valueField : 'ModulartId',
					displayField : 'ModularName'
				} ]
			} ],
			buttons : [ {
				text : '查询',
				id : 'staffsearch_submit'
			}, {
				text : '重置',
				id : 'staffsearch_reset'
			} ]
		} ]
	}, {
		xtype : 'panel',
		title : '人员信息',
		flex : 1,
		layout : 'border',
		tbar:[{
    	    text:'新增',
    	    id:'staff_add_btn',
    	    icon:'image/user_add.png'
    	},{
    		text:'修改',
    		id:'staff_update_btn',
    		icon:'image/user_edit.png'
    	}],
		items : [ {
			xtype : 'gridpanel',
			id : 'staff_grid',
			autoScroll : true,
			region : 'center',
			loader : {
				loadMask : {
					msg : '正在加载数据，请稍候....'
				}
			},
			store : 'StaffInfoStore',
			viewConfig : {
				plugins : {
					ddGroup : 'GridExample',
					ptype : 'gridviewdragdrop',
					enableDrop : false
				}
			},
			enableDragDrop : true,
			columns : [
			{
				text : '人员id',
				dataIndex : 'userid',
				hidden:true
			}, {
				text : '人员编号',
				dataIndex : 'userId'
			}, {
				text : '人员姓名',
				dataIndex : 'userName',
				field : {
					xtype : 'textfield',
					allowBlank : false
				}
			}, {
				text : '性别',
				dataIndex : 'sex',
				field : {
					xtype : 'combobox',
					editable : false, // 禁止手写及联想功能
					queryMode : 'local',
					displayField : 'sex',
					store : new Ext.data.Store({
						fields : [ {
							name : 'sex'
						} ],
						data : [ {
							sex : '男'
						}, {
							sex : '女'
						} ]
					})
				}
			}, {
				text : '联系方式',
				dataIndex : 'telephone',
				field : {
					xtype : 'textfield',
					allowBlank : false
				}
			}, {
				text : '出生日期',
				dataIndex : 'birthday',
			},{
				text : '职位',
				dataIndex : 'post',
				field : {
					xtype : 'combobox',
					editable : false, // 禁止手写及联想功能
					queryMode : 'local',
					displayField : 'post',
					store : new Ext.data.Store({
						fields : [ {
							name : 'post'
						} ],
						data : [ {
							post : '测试员'
						}, {
							post : '项目经理'
						}, {
							post : '开发人员'
						} ]
					})
				}
			}, {
				text : '状态',
				dataIndex : 'userState'
			}, {
				text : '备注',
				dataIndex : 'remark'
			} ],
			plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 2
			}) ],
			dockedItems : [ { // 任意定位工具栏
				xtype : 'pagingtoolbar', // 分页
				store : 'StaffInfoStore',
				pageSize : 15,
				dock : 'bottom', // 定位
				displayInfo : true
			} ],
			selType : 'rowmodel',
			multiSelect : false,
			enableKeyNav : true
		}, {
			region : 'east',
			collapsible : true,
			autoScroll : true,
			title : '人员操作',
			id : 'staff_operate_panel',
			split : true,
			width : 300,
			collapsed : true, // 默认收缩
			layout : 'accordion',
			layoutConfig : {
				titleCollapse : false,
				animate : true,
				activeOnTop : true
			},
			items : [ {
				xtype : 'form',
				title : '员工状态更改',
				id : 'staff_state_form',
				defaultType : 'textfield',
				defaults : {
					allowBlank : false,
					msgTarget : 'side',
					padding : '10 10 10 30',
					labelSeparator : ':',
					labelWidth : 60,
					selectOnFocus : true
				},
				waitMsgTarget : true,
				items : [ {
					fieldLabel : '员工Id',
					name : 'userid',
					hidden:true
				}, {
					fieldLabel : '员工编号',
					name : 'userId',
					readOnly : true
				},{
					fieldLabel : '员工姓名',
					readOnly : true,
					name : 'userName'
				}, {
					xtype : 'combobox',
					fieldLabel : '用户状态',
					name : 'userState',
					editable : false, // 禁止手写及联想功能
					displayField : 'name', // 前台展示的数据项
					store : new Ext.data.Store({
						 fields : [ {
								name : 'name'
							}, // xml子节点
							{
								name : 'id'
							} ],
							proxy : {
								type : 'ajax',
								url : 'xmldata/userState.xml',
								reader : {
									type : 'xml',
									record : 'userState'
								}
							}
					})
				}, {
					xtype : 'textarea',
					fieldLabel : '备注',
					name : 'remark'
				} ],
				buttons : [ {
					text : '提交',
					id : 'staff_state_submit'
				}, {
					text : '重置',
					id : 'staff_state_reset'
				} ]
			} ]
		} ]
	} ]
})