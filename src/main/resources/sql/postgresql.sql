CREATE EXTENSION IF NOT EXISTS "uuid-ossp";--enable GUID extesion for database


DO $$
DECLARE  
   idProfile varchar := uuid_generate_v1();   
   idRole varchar := uuid_generate_v1();   
   idUser varchar := uuid_generate_v1(); 
BEGIN  
    insert into profile values(idProfile,now(),null,'','ADMIN');
	insert into profile values(uuid_generate_v1(),now(),null,'','USER');
	insert into role values(idRole,now(),null,'ROLE_ADMIN');
	insert into profile_role values(idProfile,idRole);
	insert into users (id,account_non_expired,account_non_locked,create_at,credentials_non_expired,enabled,login,name,password,update_at)
	values(idUser,1,1,getdate(),1,1,'admin@admin.com','ADMIN','$2a$10$bfvaotMW5QM59C/0IfU2eec/KJ131WIT5ffLeLA4gAGiwRqXP7TlK',null)
	insert into user_profiles values(idUser,idProfile);
END $$;
