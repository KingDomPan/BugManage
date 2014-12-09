Ext.define('AM.store.ReRepairedStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:15, 
    proxy: {
         type: 'ajax',
         url: 'repairReason/getReRepairBugByUserId.action',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});