Ext.define('AM.store.ProjectInfoStore',{
    extend:'Ext.data.Store',
    model:'AM.model.ProjectInfoModel',
    pageSize:15,
    proxy:{
       type:'ajax',
       url:'project/getProjectsByPage.action',
       reader:{
           type:'json',
           root:'topics',
           totalProperty :'totalCount'
      }
   }
});