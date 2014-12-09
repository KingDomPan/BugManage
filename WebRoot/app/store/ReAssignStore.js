Ext.define('AM.store.ReAssignStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:15, 
    proxy: {
         type: 'ajax',
         url: 'taskReason/getReAssignBugByUserId.action',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});