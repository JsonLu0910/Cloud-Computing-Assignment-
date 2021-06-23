//Since we got the result of which class contains "extends"
//read through all the file in Java to find extends Keyword
// we can check again is the result correct or not
val Extends = sc.textFile("/FileStore/tables/Java/*")
//Filter out the Key word extends
val Extendsfilter = Extends.filter(_.contains("extends")).map(x=>x.trim).map(x => (x.split("\\{")(0)))
val ExMap =  Extendsfilter.map(x => ("class",x.split(" ")(2),x.split(" ")(3),x.split(" ")(4)))

//Since we got the result of which class contains "implement"
//Read through all the file in Java to find implement keyword
//then we can check again 
//we can check again the result correct or not
val Implement = sc.textFile("/FileStore/tables/Java/*")
//Filter out the Key word implement
val ImplementFilter = Implement.filter(_.contains("implement")).map(x=>x.trim).map(x => (x.split("\\{")(0)))
val ImpMap =  ImplementFilter.map(x => ("class",x.split(" ")(2),x.split(" ")(3),x.split(" ")(4)))

//combine ExMap and ImMap
val total = ImpMap.union(ExMap)
var Data3 = sc.parallelize(Seq.empty[(String,String, String,String)])
Data3 = Data3.union(total)
val DF3 = Data3.toDF("Class" ,"Name", "Operation","From")
DF3.show
//write data into Extends_Implement.csv
DF3.repartition(1).write.format("com.databricks.spark.csv").option("header", "true").save("FileStore/tables/Extends_Implement.csv")