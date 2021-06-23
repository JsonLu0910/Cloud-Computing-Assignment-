//Read the InputHandler.java
val MainFunc7 = sc.textFile("FileStore/tables/Java/main/InputHandler.java")
val MainFuncFilter7 = MainFunc7.filter(!_.contains("//")).filter(!_.contains("Key()")).filter(!_.contains(";")).filter(!_.contains("=")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("InputHandler")).filter(_.contains ("KeyEvent")).map(x => x.trim)
val MainFunc7Map =  MainFuncFilter7.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1),"InputHandler")).map(x => (x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0),x._5))

//Read the KeyManager.java
val MainFunc9 = sc.textFile("FileStore/tables/Java/main/KeyManager.java")
val MainFuncFilter9 = MainFunc9.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("KeyManager")).filter(_.contains ("KeyEvent")).map(x => x.trim)
val MainFunc9Map =  MainFuncFilter9.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1),"KeyManager")).map(x => (x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0),x._5))

//Combine both MainFunc7Map and MainFun9Map
val total = MainFunc9Map.union(MainFunc7Map)
var Data9 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data9 = Data9.union(total)


val DF9= Data9.toDF("Class" ,"Return", "Name", "Parameter","Component")
DF9.show
//Write data into EventProcedure.csv
DF9.repartition(1).write.format("com.databricks.spark.csv").option("header", "true").save("FileStore/tables/EventProcedure.csv")

