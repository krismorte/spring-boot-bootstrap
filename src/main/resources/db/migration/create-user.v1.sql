declare @idProfile varchar(255)
declare @idRole varchar(255)
declare @idUser varchar(255)
set @idProfile =newid();
set @idRole =newid();
set @idUser =newid();

insert into profile values(@idProfile,getdate(),null,'','ADMIN');
insert into profile values(newid(),getdate(),null,'','USER');
insert into role values(@idRole,getdate(),null,'ROLE_ADMIN');
insert into profile_role values(@idProfile,@idRole);
insert into users (id,account_non_expired,account_non_locked,create_at,credentials_non_expired,enabled,login,name,password,update_at)
values(@idUser,1,1,getdate(),1,1,'admin@admin.com','ADMIN','$2a$10$bfvaotMW5QM59C/0IfU2eec/KJ131WIT5ffLeLA4gAGiwRqXP7TlK',null)
insert into user_profiles values(@idUser,@idProfile);

