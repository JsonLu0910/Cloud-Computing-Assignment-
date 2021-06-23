/*sql 1 Functions that requires parameter*/
%sql
SELECT FilePath
FROM FUNCTION
WHERE Parameter NOT LIKE '%null%';

/*sql 2 getter and setter count*/
%sql
SELECT Count(Name)
AS Setter_Getter
FROM FUNCTION
WHERE Name LIKE '%set%'
OR Name LIKE '%get%';

/*sql 3 get Functions and their return datatype*/
%sql
SELECT Name,Return
FROM FUNCTION
WHERE Name LIKE '%get%';

/*sql 4 Functions that return Boolean datatype*/
%sql
SELECT Name
FROM FUNCTION
WHERE Return LIKE '%boolean%';

%sql
/* Query 5 shows filepath that occurs less than 3 times*/
SELECT COUNT(FilePath), FilePath 
From `FUNCTION`
Group By FilePath
HAVING COUNT(FilePath) <3;

%sql
/* Query 6 shows filepath that occurs more than 3 times*/
SELECT COUNT(FilePath), FilePath 
From `FUNCTION`
Group By FilePath
HAVING COUNT(FilePath) >3;

%sql
/* Query 7 Shows number of times a filepath occuring*/
SELECT COUNT(FilePath), FilePath 
From `FUNCTION`
Group By FilePath
HAVING COUNT(FilePath) ;
