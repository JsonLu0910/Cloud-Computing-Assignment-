

%sql 
/*sql 1 sort times imports called starting from 1*/ 
SELECT * 
FROM IMPORT
ORDER BY length(NumberCalled),NumberCalled ASC;


%sql 
/*sql 2 find times java libraries is called*/
SELECT * 
FROM IMPORT
WHERE Import LIKE '%java%'
ORDER BY length(NumberCalled),NumberCalled ASC;


%sql 
/*sql 3 to find own files imported*/
SELECT * 
FROM IMPORT
WHERE Import NOT LIKE '%java%'
ORDER BY length(NumberCalled),NumberCalled ASC;


%sql 
/*sql 4 find total times java library is called from Import Table*/
SELECT SUM(NumberCalled) 
As Time_Java_Lib_called
FROM Import
Where Import LIKE '%java%';


%sql 
/*sql 5 number of java lirary usedfrom Import table*/
SELECT Count(Import) 
As No_Java_Lib_called
FROM Import
Where Import LIKE '%java%';