
%sql
/* Query 1 */
SELECT Name, Operation,
CASE WHEN Operation ='implements' THEN 'this class consist implements'
WHEN Operation = 'extends' THEN 'this class consist extends'
END AS ClassDetails
FROM Extends_Implement;

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 2)
%sql
/* Query 2 */
SELECT COUNT(*)
FROM Extends_Implement;

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 3)
%sql
/* Query 3 */
SELECT *FROM Extends_Implement WHERE Extends_Implement.From = 'Menu'

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 4)
%sql
/* Query 4 */
SELECT *FROM Extends_Implement WHERE Extends_Implement.From = 'Entity'

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 5)
%sql
/* Query 5 */
SELECT *FROM Extends_Implement WHERE Extends_Implement.From = 'Runnable'

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 6)
%sql
/* Query 6 */
SELECT *FROM Extends_Implement WHERE Extends_Implement.From = 'KeyListener'

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 7)
%sql
/* Query 7 */
SELECT *FROM Extends_Implement WHERE Operation = 'extends'

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 8)
%sql
/* Query 8 */
SELECT *FROM Extends_Implement WHERE Operation = 'implements';

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 9)
%sql
/* Query 9 */
SELECT *FROM Extends_Implement ;

//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 10)
%sql
/* Query 10 */
SELECT *FROM Extends_Implement WHERE Extends_Implement.From = 'Entity' AND Name ='Player';


//(DO NOT COPY THIS LINE, THIS IS FOR QUERY 11)
%sql
/* Query 11 */
SELECT *FROM Extends_Implement WHERE Extends_Implement.From = 'Menu' AND Name ='Player';
