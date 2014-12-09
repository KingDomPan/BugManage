Ext.define('AM.store.VersionInfoStore',{
    extend:'Ext.data.Store',
    model:'AM.model.ProjectInfoModel',
    proxy:{
       type:'ajax',
       url:'version/getVersionsByProjectId.action',
       reader:{
           type:'json'
      }
   }
});