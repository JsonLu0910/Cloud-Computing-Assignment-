//read the entity file
val entities = sc.wholeTextFiles("FileStore/tables/Java/entities/")
val entitiesCount = entities.count
//read the level file
val levels = sc.wholeTextFiles("FileStore/tables/Java/levels/")
val levelsCount = levels.count
//read the main file
val main = sc.wholeTextFiles("FileStore/tables/Java/main/")
val mainCount = main.count
//read the menu file
val menu = sc.wholeTextFiles("FileStore/tables/Java/menu/")
val menuCount = menu.count

//check from the entity file if there is any class extends from Entity
val EntitiesExtend = entities.filter(entity=> entity._2.contains("private"))
val EntitiesCount = EntitiesExtend.count
val EExtend = EntitiesExtend.keys.collect

//check from the main file if there is any class extends from other class
val MainExtend = main.filter(mainextend=> mainextend._2.contains("private"))
val MECount = MainExtend.count
val MaExtend = MainExtend.keys.collect

//check from the level file if there is any class extends from other class
val LevelExtend = levels.filter(lvlextend=> lvlextend._2.contains("private"))
val LVLECount = LevelExtend.count
val LExtend = LevelExtend.keys.collect

//check from the menu file if there is any class extends from other class
val MenuExtend = menu.filter(menuextend=> menuextend._2.contains("private"))
val MCount = MenuExtend.count
val MeExtend = MenuExtend.keys.collect

//Combine all the file and find out which file consist of private
val totalPrivate = EntitiesExtend.union(MainExtend).union(LevelExtend).union(MenuExtend)
val totalCount = totalPrivate.count
totalPrivate.keys.collect.foreach(println)


//now we knew which file consist private, lets read directly to the file and find out the private
//calculate the total instance variables of whole file 

//entities
//Read the file inside the folder first
val EnVariable = sc.textFile("/FileStore/tables/Java/entities/Balloon.java")
//Filter out the Key word Private
val Enfilter = EnVariable.filter(!_.contains("//")).filter(!_.contains("static")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Balloon",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

val EnVariable1 = sc.textFile("/FileStore/tables/Java/entities/Enemy.java")
//Filter out the Key word Private
val Enfilter1 = EnVariable1.filter(!_.contains("//")).filter(!_.contains("static")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Enemy",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

val EnVariable2 = sc.textFile("/FileStore/tables/Java/entities/Player.java")
//Filter out the Key word Private
val Enfilter2 = EnVariable2.filter(!_.contains("//")).filter(!_.contains("static")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Player",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//levels
//read the file inside the levels folder first
val lvlVariable = sc.textFile("/FileStore/tables/Java/levels/Level.java")
//filter out the Key word Private
val lvlfilter = lvlVariable.filter(!_.contains("//")).filter(!_.contains("static")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Level",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//levels
//read the file inside the levels folder first
val lvlVariable1 = sc.textFile("/FileStore/tables/Java/levels/Platform.java")
//filter out the Key word Private
val lvlfilter1 = lvlVariable1.filter(!_.contains("//")).filter(!_.contains("static")).filter(!_.contains("/*")).filter(!_.contains("class")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Platform",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))


//main
//Read all the file inside the main folder first
val MainVariable = sc.textFile("/FileStore/tables/Java/main/Game.java")
val mainfilter = MainVariable.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Game", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable1 = sc.textFile("/FileStore/tables/Java/main/Animation.java")
val mainfilter1 = MainVariable1.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Animation", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable2 = sc.textFile("/FileStore/tables/Java/main/Assets.java")
val mainfilter2 = MainVariable2.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Assets", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable3 = sc.textFile("/FileStore/tables/Java/main/BalloonBattle.java")
val mainfilter3 = MainVariable3.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("BalloonBattle", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable4 = sc.textFile("/FileStore/tables/Java/main/Display.java")
val mainfilter4 = MainVariable4.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Display", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable5 = sc.textFile("/FileStore/tables/Java/main/InputHandler.java")
val mainfilter5 = MainVariable5.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("InputHandler", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable6 = sc.textFile("/FileStore/tables/Java/main/Sound.java")
val mainfilter6 = MainVariable6.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("Sound", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//main
//Read all the file inside the main folder first
val MainVariable7 = sc.textFile("/FileStore/tables/Java/main/SpriteSheet.java")
val mainfilter7 = MainVariable7.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).filter(_.endsWith(";")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map(x => ("SpriteSheet", x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))



//menu
//read all the file inside the menu folder first
val MenuVariable = sc.textFile("/FileStore/tables/Java/menu/MainMenu.java")
val menufilter = MenuVariable.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map( x => ("MainMenu",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

//menu
//read all the file inside the menu folder first
val MenuVariable1 = sc.textFile("/FileStore/tables/Java/menu/CreditsMenu.java")
val menufilter1 = MenuVariable1.filter(!_.contains("//")).filter(!_.contains("/*")).filter(!_.contains("static")).filter(!_.contains("class")).filter(!_.contains("void")).filter(_.contains("private")).map(x=>x.trim).map(x => (x.split("=")(0))).map(x => (x.split(";")(0))).map( x => ("CreditsMenu",x.split(" ")(0),x.split(" ")(1),x.split(" ")(2)))

val total = Enfilter.union(Enfilter2).union(menufilter).union(menufilter1).union(lvlfilter).union(lvlfilter1).union(mainfilter).union(mainfilter1).union(mainfilter2).union(mainfilter3).union(mainfilter4).union(mainfilter5).union(mainfilter6).union(mainfilter7)

var Data4 = sc.parallelize(Seq.empty[(String,String, String,String)])
Data4 = Data4.union(total)
val DF4 = Data4.toDF("Class" ,"Type", "DataType","Name")
DF4.show

//write data into InstanceVariables.csv
DF4.repartition(1).write.format("com.databricks.spark.csv").option("header", "true").save("FileStore/tables/InstanceVariables.csv")