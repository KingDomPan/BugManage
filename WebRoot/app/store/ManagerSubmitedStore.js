Ext.define('AM.store.ManagerSubmitedStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:15, 
    proxy: {
         type: 'ajax',
         url: 'bug/getAssignedBugByPage',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});