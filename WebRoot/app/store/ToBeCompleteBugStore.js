Ext.define('AM.store.ToBeCompleteBugStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:20, 
    proxy: {
         type: 'ajax',
         url: 'bug/getToBeCompletedBugByPage',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});