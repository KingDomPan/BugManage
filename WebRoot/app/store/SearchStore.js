Ext.define('AM.store.SearchStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:15, 
    proxy: {
         type: 'ajax',
         url: 'bug/getAllBugs.action',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});