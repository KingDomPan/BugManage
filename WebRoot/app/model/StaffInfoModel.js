Ext.define('AM.model.StaffInfoModel',{
     extend:'Ext.data.Model',
     fields:[
         {name:'userId',type:'auto'},
         {name:'userid',type:'auto'},
         {name:'userName',type:'string'},
         {name:'pwd',type:'string'},
         {name:'post',type:'string'},
         {name:'sex',type:'string'},
         {name:'telephone',type:'string'},
         {name:'birthday',type:'auto',format:'Y-m-d'},
         {name:'userState',type:'string'},
         {name:'remark',type:'string'}
     ]
});