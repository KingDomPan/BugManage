Ext.define('AM.model.ModularInfoModel',{
     extend:'Ext.data.Model',
     fields:[
         {name:'ModularId',type:'string'},
         {name:'ModularName',type:'string'},
         {name:'DirectorId',type:'string'}
     ],
     belongsTo:'ProjectInfoModel'
});