/* Companies. */
insert into SHOWCASE_COMPANY(idCompany, siret, name, description) values (1, '5521202222', 'GBIS', 'Investment Banking');
insert into SHOWCASE_COMPANY(idCompany, siret, name, description) values (2, '5521202223', 'BSC', 'Group entity');
insert into SHOWCASE_COMPANY(idCompany, siret, name, description) values (3, '5521202224', 'BDDF', 'Retail Banking');
insert into SHOWCASE_COMPANY(idCompany, siret, name, description) values (4, '5521202224', 'IBFS', 'International Retail Banking');
insert into SHOWCASE_COMPANY(idCompany, siret, name, description) values (5, '5521202224', 'SGSS', 'Private Banking');

/* Department. */
insert into SHOWCASE_DEPARTMENT(iddepartment, name, company) values (1, 'CTT', 1);
insert into SHOWCASE_DEPARTMENT(iddepartment, name, company) values (2, 'DPR', 1);
insert into SHOWCASE_DEPARTMENT(iddepartment, name, company) values (3, 'RRF', 1);
insert into SHOWCASE_DEPARTMENT(iddepartment, name, company) values (4, 'FCC', 1);
insert into SHOWCASE_DEPARTMENT(iddepartment, name, company) values (5, 'CTY', 1);
insert into SHOWCASE_DEPARTMENT(iddepartment, name, company) values (6, 'COO', 1);

/* Employees. */ 
insert into SHOWCASE_EMPLOYEE(idemployee, firstname, lastname, email, company, department) values (1, 'Erwan', 'Ducroquet', 'r1@socgen', 1, 2);
insert into SHOWCASE_EMPLOYEE(idemployee, firstname, lastname, email, company, department) values (2, 'Christian', 'Klat', 'cklat@socgen.com', 1, 2);
insert into SHOWCASE_EMPLOYEE(idemployee, firstname, lastname, email, company, department) values (3, 'Bertrand', 'Taboulet', 'btaboulet@socgen.com', 1, 2);
insert into SHOWCASE_EMPLOYEE(idemployee, firstname, lastname, email, company, department) values (4, 'Cedrick', 'Lunven', 'clunven@socgen.com', 1, 2);








