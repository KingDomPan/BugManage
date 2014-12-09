create table t_bug (id int identity not null, bugDetail varchar(255) null, bugEnvironment varchar(255) null, bugExpected varchar(255) null, bugId varchar(255) null, bugResult varchar(255) null, bugSeverity varchar(255) null, bugTitle varchar(255) null, stateName varchar(255) null, submitedDay datetime2 null, projectId int null, modularId int null, testerId int null, versionId int null, primary key (id))
create table t_bugReason (bugDealReasonId bigint identity not null, bugReasonType varchar(255) null, reasonDetail varchar(255) null, submitedDay datetime2 null, dealledBugId int null, managerId int null, primary key (bugDealReasonId))
create table t_modular (id int identity not null, modularId varchar(255) null, modularName varchar(255) null, developerId int null, projectId int null, primary key (id))
create table t_project (id int identity not null, projectId varchar(255) null, projectName varchar(255) null, managerId int null, primary key (id))
create table t_repair (repairId int identity not null, repairScheme varchar(255) null, submitedDay datetime2 null, repairBugId int null, repairDeveloperId int null, primary key (repairId))
create table t_repairReason (rrid int identity not null, reasonDetail varchar(255) null, repairReason varchar(255) null, submitedDay datetime2 null, repairId int null, primary key (rrid))
create table t_task (taskId int identity not null, isActive bit default 1 null, bugPriority varchar(255) null, remark varchar(255) null, submitedDay datetime2 null, assignManagerId int null, bugId int null, dealDeveloperId int null, primary key (taskId))
create table t_taskReason (taskReasonId int identity not null, reasonDetail varchar(255) null, submitedDay datetime2 null, taskReasonType varchar(255) null, taskId int null, primary key (taskReasonId))
create table t_user (id int identity not null, birthday datetime2 null, post varchar(255) null, pwd varchar(255) default '888888' null, remark varchar(255) null, sex varchar(255) null, telephone varchar(255) null, userId varchar(255) null, userName varchar(255) null, userState varchar(255) null, primary key (id))
create table t_version (versionId int identity not null, beginTime datetime2 null, endTime datetime2 null, updatedContend varchar(255) null, versionName varchar(255) null, projectId int null, primary key (versionId))
alter table t_bug add constraint FK68F54296D681A68 foreign key (versionId) references t_version
alter table t_bug add constraint FK68F5429648BEAAA foreign key (projectId) references t_project
alter table t_bug add constraint FK68F542998F66A4C foreign key (modularId) references t_modular
alter table t_bug add constraint FK68F5429B3CB3D68 foreign key (testerId) references t_user
alter table t_bugReason add constraint FK354A4FCD2DA0FA61 foreign key (dealledBugId) references t_bug
alter table t_bugReason add constraint FK354A4FCDDBE1D4D6 foreign key (managerId) references t_user
alter table t_modular add constraint FK4BB1A3F648BEAAA foreign key (projectId) references t_project
alter table t_modular add constraint FK4BB1A3FEAB419B3 foreign key (developerId) references t_user
alter table t_project add constraint FKA9223E4EDBE1D4D6 foreign key (managerId) references t_user
alter table t_repair add constraint FK7BC471B852E0CDB3 foreign key (repairBugId) references t_bug
alter table t_repair add constraint FK7BC471B8FAC6B006 foreign key (repairDeveloperId) references t_user
alter table t_repairReason add constraint FKD010C61C4842CBB8 foreign key (repairId) references t_repair
alter table t_task add constraint FKCB6316708AF1F0C7 foreign key (assignManagerId) references t_user
alter table t_task add constraint FKCB631670BF8161C7 foreign key (dealDeveloperId) references t_user
alter table t_task add constraint FKCB631670F4D63D20 foreign key (bugId) references t_bug
alter table t_taskReason add constraint FKF49F00D4C39C2E68 foreign key (taskId) references t_task
alter table t_version add constraint FKD087100D648BEAAA foreign key (projectId) references t_project
