Ext.define(
				'AM.view.TesterSubmitedBugTab',
				{
					extend : 'Ext.panel.Panel',
					alias : 'widget.testersubmitedbugtab', // 别名
					requires : ([ 'Ext.ux.RowExpander' ]),
					id : 'testersubmitedbug_tabpanel',
					title : '已提交BUG',
					closable : true,
					layout : {
						type : 'vbox',
						align : 'stretch', // vbox:[left,center,stretch,stretchmax]
						pack : 'start'
					},
					items : [
							{
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
									items : [
											{
												xtype : 'panel',
												defaults : {
													labelWidth : 60,
													selectOnFocus : true,
													padding : '10 30 10 10',
													width : 300,
													labelSeparator : ':'
												},
												defaultType : 'textfield',
												items : [ {
													fieldLabel : 'Bug编号',
													name : 'bugId'
												}, {
													fieldLabel : 'Bug标题',
													name : 'bugTitle'
												} ]
											},
											{
												xtype : 'panel',
												padding : '10 10 10 30',
												defaults : {
													labelWidth : 60,
													selectOnFocus : true,
													labelSeparator : ':',
													width : 300,
													padding : '10 30 10 10'
												},
												items : [
														{
															xtype : 'combobox',
															name : 'projectId',
															fieldLabel : '所属项目',
															listConfig : { // 控制
																emptyText : '没有找到匹配的数值..',
																maxHeight : 200, // 超过会出现滚动条
																loadingText : '正在加载...',
																getInnerTpl : function() { // 模版功能
																	return "<div>{projectId}.{projectName}</div>"
																}
															},
															autoScroll : true,
															store : 'ProjectStore',
															pageSize : 10,
															typeAhead : true,
															valueField : 'id',
															displayField : 'projectName'
														},
														{
															xtype : 'combobox',
															name : 'modularId',
															fieldLabel : '所属模块',
															listConfig : { // 控制
																emptyText : '没有找到匹配的数值..',
																maxHeight : 200, // 超过会出现滚动条
																loadingText : '正在加载...',
																getInnerTpl : function() { // 模版功能
																	return "<div>{modularId}.{modularName}</div>"
																}
															},
															store : 'ModularStore',
															typeAhead : true,
															valueField : 'id',
															displayField : 'modularName'
														} ]
											} ],
									buttons : [ {
										text : '查询',
										id : 'submitedbug_search_submit'
									}, {
										text : '重置',
										id : 'submitedbug_search_reset'
									} ]
								} ]
							},
							{
								xtype : 'panel',
								title : '已提交Bug信息',
								flex : 1,
								layout : 'border',
								tbar : [ {
									text : '新增',
									id : 'tester_add_btn',
									icon:'image/bug_add.png'
								}, {
									text : '修改',
									id : 'tester_update_btn',
									icon:'image/bug_edit.png'
								} ],
								items : [ {
									xtype : 'gridpanel',
									id : 'submitedbug_grid',
									autoScroll : true,
									region : 'center',
//									view : new Ext.grid.View({
									// getRowClass:function(record, index) {
									// if (index % 2 == 0)
									// console.log(index);
									// return 'x-grid3-row-alt';
									// else
									// return 'green';
									// }
//									}),
									viewConfig : {
										stripeRows : true,
										getRowClass:function(record, index) {
											 if (index % 2 == 0)
										        return 'x-grid3-row-alt';
											// else
											// return 'green';
											 },
										plugins : {
											ddGroup : 'GridExample',
											ptype : 'gridviewdragdrop',
											enableDrop : false
										}
									},
									loader : {
										loadMask : {
											msg : '正在加载数据，请稍候....'
										}
									},
									store : 'TesterBugStore',
									enableDragDrop : true,
									columns : [ {
										text : 'BugId',
										dataIndex : 'id',
										hidden : true
									}, {
										text : 'Bug编号',
										dataIndex : 'bugId',
										sortable:true
									}, {
										text : 'Bug标题',
										dataIndex : 'bugTitle'
									}, {
										text : 'Bug严重度',
										dataIndex : 'bugSeverity'
									}, {
										text : '运行环境',
										dataIndex : 'bugEnvironment'
									}, {
										text : '项目Id',
										dataIndex : 'projectid',
										hidden : true
									},{
										text : '项目编号',
										dataIndex : 'projectId',
										hidden : true
									},{
										text : '所属项目',
										dataIndex : 'projectName'
									},{
										text : '模块Id',
										dataIndex : 'modularid',
										hidden : true
									}, {
										text : '模块编号',
										dataIndex : 'modularId',
										hidden : true
									}, {
										text : '所属模块',
										dataIndex : 'modularName'
									}, {
										text : '项目版本Id',
										dataIndex : 'versionId',
										hidden : true
									}, {
										text : '项目版本',
										dataIndex : 'versionName'
									}, {
										text : '提交日期',
										dataIndex : 'submitedDay'

									}, {
										text : 'Bug状态',
										dataIndex : 'stateName'
									} ],
									plugins : [ {
										ptype : 'rowexpander',
										rowBodyTpl : [
												'<p><b>期望效果: </b>{bugExpected}</p>',
												'<p><b>运行效果: </b>{bugResult}</p>',
												'<p><b>Bug详细内容: </b>{bugDetail}</p>' 
										]
									} ],
									dockedItems : [ { // 任意定位工具栏
										xtype : 'pagingtoolbar', // 分页
										store : 'TesterBugStore',
										pageSize : 10,
										dock : 'bottom', // 定位
										displayInfo : true
									} ],
									selType : 'rowmodel',
									multiSelect : false,
									enableKeyNav : true
								} ]
							} ]
				})