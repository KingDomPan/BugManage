Ext.define('AM.store.RelicatorInfoStore',{
    extend:'Ext.data.Store',
    model:'AM.model.StaffInfoModel',
    pageSize:15,
    proxy:{
       type:'ajax',
       url:'user/getDevelopersInPosition.action',
       reader:{
           type:'json'
      }
   }
});