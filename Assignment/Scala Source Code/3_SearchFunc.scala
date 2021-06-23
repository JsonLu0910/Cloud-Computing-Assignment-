//Read the Animation.java inside the Main folder
val MainFunc1 = sc.textFile("FileStore/tables/Java/main/Animation.java")
val MainFuncFilter1 = MainFunc1.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("Animation")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc1Map =  MainFuncFilter1.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/Animation.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data1 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data1 = Data1.union(MainFunc1Map)
val DF1 = Data1.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write the data into a Animtaion.csv


//Read the Assets.java inside the Main folder
val MainFunc2 = sc.textFile("FileStore/tables/Java/main/Assets.java")
val MainFuncFilter2 = MainFunc2.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("Assets")).filter(_.contains("public ")).map(x => x.trim).filter(!_.endsWith (","))
val MainFunc2Map =  MainFuncFilter2.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/Assets.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data2 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data2 = Data2.union(MainFunc2Map)
val DF2 = Data2.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write the data into a Animtaion.csv


//Read the BalloonBattle.java inside the Main folder
val MainFunc3 = sc.textFile("FileStore/tables/Java/main/BalloonBattle.java")
val MainFuncFilter3 = MainFunc3.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc3Map =  MainFuncFilter3.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/BalloonBattle.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data3 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data3 = Data3.union(MainFunc3Map)
val DF3 = Data3.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into BalloonBattle.csv


//Read the Display.java inside the Main folder
val MainFunc4 = sc.textFile("FileStore/tables/Java/main/Display.java")
val MainFuncFilter4 = MainFunc4.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("Display")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc4Map =  MainFuncFilter4.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/Display.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data4 = sc.parallelize(Seq.empty[(String,String,String, String, String)])
Data4 = Data4.union(MainFunc4Map)
val DF4 = Data4.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Display.csv

//Read the Game.java inside the Main folder
val MainFunc5 = sc.textFile("FileStore/tables/Java/main/Game.java")
val MainFuncFilter5 = MainFunc5.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("Game")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc5Map =  MainFuncFilter5.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/Game.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data5 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data5 = Data5.union(MainFunc5Map)
val DF5 = Data5.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data ito Game.csv


//Read the ImageLoader.java inside the Main folder
val MainFunc6 = sc.textFile("FileStore/tables/Java/main/ImageLoader.java")
val MainFuncFilter6 = MainFunc6.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("ImageLoader")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc6Map =  MainFuncFilter6.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/ImageLoader.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data6 = sc.parallelize(Seq.empty[(String,String,String, String, String)])
Data6 = Data6.union(MainFunc6Map)
val DF6 = Data6.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into ImageLoader.csv

//Read the InputHandler.java inside the Main folder
val MainFunc7 = sc.textFile("FileStore/tables/Java/main/InputHandler.java")
val MainFuncFilter7 = MainFunc7.filter(!_.contains("//")).filter(!_.contains("Key()")).filter(!_.contains(";")).filter(!_.contains("=")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("InputHandler")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc7Map =  MainFuncFilter7.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/InputHandler.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data7 = sc.parallelize(Seq.empty[(String,String,String, String, String)])
Data7 = Data7.union(MainFunc7Map)
val DF7 = Data7.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into InputHandler.csv

//Read the Item.java inside the Main folder
val MainFunc8 = sc.textFile("FileStore/tables/Java/main/Item.java")
val MainFuncFilter8 = MainFunc8.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("Item")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc8Map =  MainFuncFilter8.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/Item.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data8 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data8 = Data8.union(MainFunc8Map)
val DF8 = Data8.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//wrtie data into Item.csv

//Read the KeyManager.java inside the Main folder
val MainFunc9 = sc.textFile("FileStore/tables/Java/main/KeyManager.java")
val MainFuncFilter9 = MainFunc9.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("KeyManager")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc9Map =  MainFuncFilter9.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/KeyManager.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data9 = sc.parallelize(Seq.empty[(String,String,String, String, String)])
Data9 = Data9.union(MainFunc9Map)
val DF9= Data9.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into KeyManager.csv

val MainFunc10 = sc.textFile("FileStore/tables/Java/main/Sound.java")
val MainFuncFilter10 = MainFunc10.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("Sound")).filter(_.contains("public ")).map(x => x.trim).map(_.split("\\{")(0))
val MainFunc10Map =  MainFuncFilter10.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/Sound.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data10 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data10 = Data10.union(MainFunc10Map)
val DF10 = Data10.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Sound.csv



val MainFunc11 = sc.textFile("FileStore/tables/Java/main/SpriteSheet.java")
val MainFuncFilter11 = MainFunc11.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("SpriteSheet")).filter(_.contains("public ")).map(x => x.trim).map(_.split("\\{")(0))
val MainFunc11Map =  MainFuncFilter11.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("FileStore/tables/Java/main/SpriteSheet.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data11 = sc.parallelize(Seq.empty[(String,String,String, String, String)])
Data11 = Data11.union(MainFunc11Map)
val DF11 = Data11.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into SpriteSheet.csv

val MainFunc12 = sc.textFile("/FileStore/tables/Java/entities/Balloon.java")
val MainFuncFilter12 = MainFunc12.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc12Map =  MainFuncFilter12.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/entities/Balloon.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data12 = sc.parallelize(Seq.empty[(String,String,String, String, String)])
Data12 = Data12.union(MainFunc12Map)
val DF12 = Data12.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Balloon.csv

val MainFunc13 = sc.textFile("/FileStore/tables/Java/entities/Enemy.java")
val MainFuncFilter13 = MainFunc13.filter(!_.contains("Enemy(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc13Map =  MainFuncFilter13.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/entities/Enemy.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data13 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data13 = Data13.union(MainFunc13Map)
val DF13 = Data13.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Enemy.csv

val MainFunc14 = sc.textFile("/FileStore/tables/Java/entities/Entity.java")
val MainFuncFilter14 = MainFunc14.filter(!_.contains("Entity(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc14Map =  MainFuncFilter14.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/entities/Entity.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data14 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data14 = Data14.union(MainFunc14Map)
val DF14 = Data14.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Entity.csv

val MainFunc15 = sc.textFile("/FileStore/tables/Java/entities/Player.java")
val MainFuncFilter15 = MainFunc15.filter(!_.contains("Player(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc15Map =  MainFuncFilter15.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/entities/Player.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data15 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data15 = Data15.union(MainFunc15Map)
val DF15 = Data15.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Player.csv

val MainFunc16 = sc.textFile("/FileStore/tables/Java/levels/Level.java")
val MainFuncFilter16 = MainFunc16.filter(!_.contains("Level(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc16Map =  MainFuncFilter16.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/levels/Level.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data16 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data16 = Data16.union(MainFunc16Map)
val DF16 = Data16.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Level.csv

val MainFunc17 = sc.textFile("/FileStore/tables/Java/levels/Platform.java")
val MainFuncFilter17 = MainFunc17.filter(!_.contains("Platform(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc17Map =  MainFuncFilter17.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/levels/Platform.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data17 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data17 = Data17.union(MainFunc17Map)
val DF17 = Data17.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Platform.csv

val MainFunc18 = sc.textFile("/FileStore/tables/Java/menu/CreditsMenu.java")
val MainFuncFilter18 = MainFunc18.filter(!_.contains("CreditsMenu(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc18Map =  MainFuncFilter18.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/menu/CreditsMenu.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data18 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data18 = Data18.union(MainFunc18Map)
val DF18 = Data18.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into CreditsMenu.csv

val MainFunc19 = sc.textFile("/FileStore/tables/Java/menu/MainMenu.java")
val MainFuncFilter19 = MainFunc19.filter(!_.contains("MainMenu(")).filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc19Map =  MainFuncFilter19.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/menu/MainMenu.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data19 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data19 = Data19.union(MainFunc19Map)
val DF19 = Data19.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into MainMenu.csv

val MainFunc20 = sc.textFile("/FileStore/tables/Java/menu/Menu.java")
val MainFuncFilter20 = MainFunc20.filter(!_.contains("//")).filter(!_.contains(";")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(!_.contains("BalloonBattle")).filter(_.contains("public ")).map(x => x.trim)
val MainFunc20Map =  MainFuncFilter20.map(x => (x.split(" ")(0),x.split(" ")(1),x.split(" ")(2),x.split("\\(")(1))).map(x => ("/FileStore/tables/Java/menu/Menu.java",x._1, x._2 ,x._3.split("\\(")(0) , x._4.split("\\)")(0)))
var Data20 = sc.parallelize(Seq.empty[(String,String, String, String,String)])
Data20 = Data20.union(MainFunc20Map)
val DF20 = Data20.toDF("FilePath","Class" ,"Return", "Name", "Parameter")

//write data into Menu.csv

//Union MainFunc1Map to MainFunc10Map
val total1 = MainFunc1Map.union(MainFunc2Map).union(MainFunc3Map).union(MainFunc4Map).union(MainFunc5Map).union(MainFunc6Map).union(MainFunc7Map).union(MainFunc8Map).union(MainFunc9Map).union(MainFunc10Map)
//Union MainFunc11Map to MainFunc20Map
val total2= MainFunc11Map.union(MainFunc12Map).union(MainFunc13Map).union(MainFunc14Map).union(MainFunc15Map).union(MainFunc16Map).union(MainFunc17Map).union(MainFunc18Map).union(MainFunc19Map).union(MainFunc20Map)

//Combine Both total1 and total2
val finaldata = total1.union(total2)
var  FINALDATA = sc.parallelize(Seq.empty[(String,String, String, String,String)])
FINALDATA = FINALDATA.union(finaldata)
val FT = FINALDATA.toDF("FilePath","Class" ,"Return", "Name", "Parameter")
FT .show
//Write data into Function.csv
FT.repartition(1).write.format("com.databricks.spark.csv").option("header", "true").save("FileStore/tables/Function.csv")