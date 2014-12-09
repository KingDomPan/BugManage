Ext.define('AM.store.StaffInfoStore',{
    extend:'Ext.data.Store',
    model:'AM.model.StaffInfoModel',
    pageSize:15,
    proxy:{
       type:'ajax',
       url:'user/getUserByPage.action',
       reader:{
           type:'json',
		   root : 'topics',
		   totalProperty : 'totalCount'
      }
   }
});