%sql
/* Query 1 */
SELECT *FROM EventProcedure ;

//Do not copy this line!! (This is for Query 2)
%sql
/* Query 2 */
SELECT COUNT(*)
FROM EventProcedure;

//Do not copy this line!! (This is for Query 3)
%sql
/* Query 3 */
SELECT *
FROM EventProcedure WHERE Component ='InputHandler';

//Do not copy this line!! (This is for Query 4)
%sql
/* Query 4 */
SELECT *
FROM EventProcedure WHERE Component ='KeyManager';

//Do not copy this line!! (This is for Query 5)
%sql
/* Query 5 */
SELECT Name, Parameter,Component,
CASE WHEN Component ='KeyManager' THEN 'the component of this class is KeyManager'
WHEN Component = 'InputHandler' THEN 'the component of this class is InputHandler'
END AS ComponentDetails
FROM EventProcedure;

//Do not copy this line!! (This is for Query 6)
%sql
/* Query 6 */
SELECT * FROM EventProcedure
ORDER BY Name;

//Do not copy this line!! (This is for Query 7)
%sql
/* Query 7 */
SELECT COUNT(Component), Name
FROM EventProcedure
GROUP BY Name
HAVING COUNT(Component) > 1;

//Do not copy this line!! (This is for Query 8)
%sql
/* Query 8 */
SELECT COUNT(Component), Name
FROM EventProcedure
GROUP BY Name
HAVING COUNT(Component) <= 1;

//Do not copy this line!! (This is for Query 9)
%sql
/* Query 9 */
SELECT * FROM EventProcedure
WHERE Name LIKE 'k%';

//Do not copy this line!! (This is for Query 10)
%sql
/* Query 10 */
SELECT * FROM EventProcedure
WHERE Parameter BETWEEN 'KeyEvent ke' AND 'boolean pressed'
ORDER BY Parameter;

