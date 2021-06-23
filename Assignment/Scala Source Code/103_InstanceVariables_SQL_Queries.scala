%sql

/* Query 1 */

select * from `InstanceVariables`

//Do not copy this line!! (This is for Query 2)
%sql

/* Query 2 */

SELECT COUNT(*)
FROM InstanceVariables;

//Do not copy this line!! (This is for Query 3)
%sql

/* Query 3 */

SELECT * From InstanceVariables WHERE DataType ='int';

//Do not copy this line!! (This is for Query 4)
%sql

/* Query 4 */

SELECT * From InstanceVariables WHERE DataType ='boolean';

//Do not copy this line!! (This is for Query 5)
%sql

/* Query 5 */

SELECT * From InstanceVariables WHERE DataType !='byte' AND DataType !='boolean' AND DataType !='int';

//Do not copy this line!! (This is for Query 6)
%sql

/* Query 6 */

SELECT * From InstanceVariables WHERE DataType ='byte';

//Do not copy this line!! (This is for Query 7)
%sql

/* Query 7 */

SELECT COUNT(*)

FROM InstanceVariables WHERE Class ='Game';

//Do not copy this line!! (This is for Query 8)
%sql

/* Query 8 */
SELECT COUNT(*)

FROM InstanceVariables WHERE Class ='Balloon';

//Do not copy this line!! (This is for Query 9)
%sql

/* Query 9 */
SELECT COUNT(*)

FROM InstanceVariables WHERE Class ='Player';

//Do not copy this line!! (This is for Query 10)
%sql

/* Query 10 */
SELECT COUNT(*)

FROM InstanceVariables WHERE Class ='Platform';

//Do not copy this line!! (This is for Query 11)
%sql

/* Query 11 */
SELECT COUNT(*) From InstanceVariables WHERE Class !='Platform' AND Class !='Player' AND Class !='Balloon' AND Class !='Game';

//Do not copy this line!! (This is for Query 12)
%sql
/* Query 12 */

SELECT COUNT(Class), Class 
From InstanceVariables
Group By Class
HAVING COUNT(Class) >3;
