Ext.define('AM.store.TesterBugStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:20, 
    proxy: {
         type: 'ajax',
         url: 'bug/getSubmitedBug.action',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});