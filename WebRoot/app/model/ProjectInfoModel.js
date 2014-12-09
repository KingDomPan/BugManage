Ext.define('AM.model.ProjectInfoModel',{
     extend:'Ext.data.Model',
     fields:[
         {name:'projectid',type:'auto'},
         {name:'projectId',type:'string'},
         {name:'projectName',type:'string'},
         {name:'managerId',type:'string'},
         {name:'managerid',type:'auto'},
         {name:'managerName',type:'string'},
         {name:'modularid',type:'auto'},
         {name:'modularId',type:'auto'},
         {name:'modularName',type:'string'},
         {name:'developerid',type:'auto'},
         {name:'developerId',type:'string'},
         {name:'developerName',type:'string'},
         {name:'versionId',type:'auto'},
         {name:'versionName',type:'string'},
         {name:'beginTime',type:'auto'},
         {name:'endTime',type:'auto'},
         {name:'updatedContend',type:'string'}
     ]
});