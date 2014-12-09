Ext.define('AM.store.ToBeAssignedBugStore',{
    extend:'Ext.data.Store',
    model:'AM.model.BugInfoModel',
    pageSize:15, 
    proxy: {
         //异步获取数据，这里的URL可以改为任何动态页面，只要返回JSON数据即可
         type: 'ajax',
         url: 'bug/getToBeAssignBugByUserId',  
         reader: {
              type: 'json',
              root:'topics',
              totalProperty :'totalCount'
          }
      }
});