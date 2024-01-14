create table look(
    id int primary key AUTO_INCREMENT,
    nom varchar(255) NOT NULL
);

SELECT TABLE_NAME as table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'poketra';

SELECT tablename as table_name FROM pg_catalog.pg_tables WHERE schemaname = 'public';