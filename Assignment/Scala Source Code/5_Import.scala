//main
//read all the file and find the total import statment 
val Anmain = sc.wholeTextFiles("FileStore/tables/Java/main/*")
val Anmainfilter = Anmain.filter(imp=>imp._2.contains("import"))
val Anmcount = Anmainfilter.count
val AnmainKey = Anmainfilter.keys.collect

//entities
//read all the file and find the total import statment 
val Enmain = sc.wholeTextFiles("FileStore/tables/Java/entities/*")
val Enmainfilter = Enmain.filter(imp=>imp._2.contains("import"))
val Enmcount = Enmainfilter.count
val EnmCountKey = Enmainfilter.keys.collect

//levels
//read all the file and find the total import statment 
val lvl = sc.wholeTextFiles("FileStore/tables/Java/levels/*")
val lvlfilter = lvl.filter(imp=>imp._2.contains("import"))
val lvlcount = lvlfilter.count
val lvlCountKey =lvlfilter.keys.collect

//menu
//read all the file and find the total import statment 
val menu = sc.wholeTextFiles("FileStore/tables/Java/menu/*")
val menufilter = menu.filter(imp=>imp._2.contains("import"))
val menucount = menufilter.count
val menuKey = menufilter.keys.collect


//Calculate and show all the file which has import statments (duplicate import statement exist)
val totalImportFile = Anmainfilter .union(Enmainfilter).union(lvlfilter).union(menufilter)
val totalImportFileCount = totalImportFile.count
val totalImportFileName = totalImportFile.keys.collect

//now lets find out what the class import 
//Animation.java imports
val AnimationImp = sc.textFile("/FileStore/tables/Java/main/Animation.java")
val AnimationImpFilz = AnimationImp.filter(_.contains("import"))
val AnimationImpFilzz = AnimationImpFilz.map(_.split(";")(0))
val AnimationImpFil = AnimationImpFilzz.map(_.split(" ")(1))
val AnimationImpcount = AnimationImpFil.count


//Assets.java imports
val AssetsImp = sc.textFile("/FileStore/tables/Java/main/Assets.java")
val AssetsImpFilz = AssetsImp.filter(_.contains("import"))
val AssetsImpFilzz = AssetsImpFilz.map(_.split(";")(0))
val AssetsImpFil = AssetsImpFilzz.map(_.split(" ")(1))
val AssetsImpcount = AssetsImpFil.count


//Display.java imports
val DisplayImp = sc.textFile("/FileStore/tables/Java/main/Display.java")
val DisplayImpFilz = DisplayImp.filter(_.contains("import"))
val DisplayImpFilzz = DisplayImpFilz.map(_.split(";")(0))
val DisplayImpFil = DisplayImpFilzz.map(_.split(" ")(1))
val DisplayImpcount = DisplayImpFil.count


//Game.java imports
val GameImp = sc.textFile("/FileStore/tables/Java/main/Game.java")
val GameImpFilz = GameImp.filter(_.contains("import"))
val GameImpFilzz = GameImpFilz.map(_.split(";")(0))
val GameImpFil = GameImpFilzz.map(_.split(" ")(1))
val GameImpcount = GameImpFil.count


//ImageLoader.java imports
val ImageLoaderImp = sc.textFile("/FileStore/tables/Java/main/ImageLoader.java")
val ImageLoaderImpFilz = ImageLoaderImp.filter(_.contains("import"))
val ImageLoaderImpFilzz = ImageLoaderImpFilz.map(_.split(";")(0))
val ImageLoaderImpFil = ImageLoaderImpFilzz.map(_.split(" ")(1))
val ImageLoaderImpcount = ImageLoaderImpFil.count


//InputHandler.java imports
val InputHandlerImp = sc.textFile("/FileStore/tables/Java/main/InputHandler.java")
val InputHandlerImpFilz = InputHandlerImp.filter(_.contains("import"))
val InputHandlerImpFilzz = InputHandlerImpFilz.map(_.split(";")(0))
val InputHandlerImpFil = InputHandlerImpFilzz.map(_.split(" ")(1))
val InputHandlerImpcount = InputHandlerImpFil.count


//Item.java imports
val ItemImp = sc.textFile("/FileStore/tables/Java/main/Item.java")
val ItemImpFilz = ItemImp.filter(_.contains("import"))
val ItemImpFilzz = ItemImpFilz.map(_.split(";")(0))
val ItemImpFil = ItemImpFilzz.map(_.split(" ")(1))
val ItemImpcount = ItemImpFil.count


//KeyManager.java imports
val KeyManagerImp = sc.textFile("/FileStore/tables/Java/main/KeyManager.java")
val KeyManagerImpFilz = KeyManagerImp.filter(_.contains("import"))
val KeyManagerImpFilzz = KeyManagerImpFilz.map(_.split(";")(0))
val KeyManagerImpFil = KeyManagerImpFilzz.map(_.split(" ")(1))
val KeyManagerImpcount = KeyManagerImpFil.count


//Sound.java imports
val SoundImp = sc.textFile("/FileStore/tables/Java/main/Sound.java")
val SoundImpFilz = SoundImp.filter(_.contains("import"))
val SoundImpFilzz = SoundImpFilz.map(_.split(";")(0))
val SoundImpFil = SoundImpFilzz.map(_.split(" ")(1))
val SoundImpcount = SoundImpFil.count


//SpriteSheet.java imports
val SpriteSheetImp = sc.textFile("/FileStore/tables/Java/main/SpriteSheet.java")
val SpriteSheetImpFilz = SpriteSheetImp.filter(_.contains("import"))
val SpriteSheetImpFilzz = SpriteSheetImpFilz.map(_.split(";")(0))
val SpriteSheetImpFil = SpriteSheetImpFilzz.map(_.split(" ")(1))
val SpriteSheetImpcount = SpriteSheetImpFil.count

//Balloon.java imports
val BalloonImp = sc.textFile("/FileStore/tables/Java/entities/Balloon.java")
val BalloonImpFilz = BalloonImp.filter(_.contains("import"))
val BalloonImpFilzz = BalloonImpFilz.map(_.split(";")(0))
val BalloonImpFil = BalloonImpFilzz.map(_.split(" ")(1))
val BalloonImpcount = BalloonImpFil.count


//Enemy.java imports
val EnemyImp = sc.textFile("/FileStore/tables/Java/entities/Enemy.java")
val EnemyImpFilz = EnemyImp.filter(_.contains("import"))
val EnemyImpFilzz = EnemyImpFilz.map(_.split(";")(0))
val EnemyImpFil = EnemyImpFilzz.map(_.split(" ")(1))
val EnemyImpcount = EnemyImpFil.count


//Entity.java imports
val EntityImp = sc.textFile("/FileStore/tables/Java/entities/Entity.java")
val EntityImpFilz = EntityImp.filter(_.contains("import"))
val EntityImpFilzz = EntityImpFilz.map(_.split(";")(0))
val EntityImpFil = EntityImpFilzz.map(_.split(" ")(1))
val EntityImpcount = EntityImpFil.count


//Player.java imports
val PlayerImp = sc.textFile("/FileStore/tables/Java/entities/Player.java")
val PlayerImpFilz = PlayerImp.filter(_.contains("import"))
val PlayerImpFilzz = PlayerImpFilz.map(_.split(";")(0))
val PlayerImpFil = PlayerImpFilzz.map(_.split(" ")(1))
val PlayerImpcount = PlayerImpFil.count


//Level.java imports
val LevelImp = sc.textFile("/FileStore/tables/Java/levels/Level.java")
val LevelImpFilz = LevelImp.filter(_.contains("import"))
val LevelImpFilzz = LevelImpFilz.map(_.split(";")(0))
val LevelImpFil = LevelImpFilzz.map(_.split(" ")(1))
val LevelImpcount = LevelImpFil.count


//Platform.java imports
val PlatformImp = sc.textFile("/FileStore/tables/Java/levels/Platform.java")
val PlatformImpFilz = PlatformImp.filter(_.contains("import"))
val PlatformImpFilzz = PlatformImpFilz.map(_.split(";")(0))
val PlatformImpFil = PlatformImpFilzz.map(_.split(" ")(1))
val PlatformImpcount = PlatformImpFil.count


//CreditsMenu.java imports
val CreditsMenuImp = sc.textFile("/FileStore/tables/Java/menu/CreditsMenu.java")
val CreditsMenuImpFilz = CreditsMenuImp.filter(_.contains("import"))
val CreditsMenuImpFilzz = CreditsMenuImpFilz.map(_.split(";")(0))
val CreditsMenuImpFil = CreditsMenuImpFilzz.map(_.split(" ")(1))
val CreditsMenuImpcount = CreditsMenuImpFil.count


//MainMenu.java imports
val MainMenuImp = sc.textFile("/FileStore/tables/Java/menu/MainMenu.java")
val MainMenuImpFilz = MainMenuImp.filter(_.contains("import"))
val MainMenuImpFilzz = MainMenuImpFilz.map(_.split(";")(0))
val MainMenuImpFil = MainMenuImpFilzz.map(_.split(" ")(1))
val MainMenuImpcount = MainMenuImpFil.count

//Menu.java imports
val MenuImp = sc.textFile("/FileStore/tables/Java/menu/Menu.java")
val MenuImpFilz = MenuImp.filter(_.contains("import"))
val MenuImpFilzz = MenuImpFilz.map(_.split(";")(0))
val MenuImpFil = MenuImpFilzz.map(_.split(" ")(1))
val MenuImpcount = MenuImpFil.count

val finalresult = MenuImpFil.union(MainMenuImpFil).union(CreditsMenuImpFil).union(PlatformImpFil).union(LevelImpFil).union(PlayerImpFil).union(EntityImpFil).union(EnemyImpFil).union(BalloonImpFil).union(SpriteSheetImpFil).union(SoundImpFil).union(KeyManagerImpFil).union(ItemImpFil).union(InputHandlerImpFil).union(ImageLoaderImpFil).union(GameImpFil).union(DisplayImpFil).union(AssetsImpFil).union(AnimationImpFil)

val resultmap = finalresult.flatMap(line => line.split(" ")).map(word => (word,1))
val finalcount = resultmap.reduceByKey((x,y) => x + y)

finalcount.collect.foreach(println)

var finalData = sc.parallelize(Seq.empty[(String, Int)])
finalData = finalData.union(finalcount)
val dfData = finalData.toDF("Import", "NumberCalled")
//Write the data into the Import.csv 
dfData.repartition(1).write.format("com.databricks.spark.csv").option("header", "true").save("FileStore/tables/Import.csv")


//Download the csv file
# File location and type
file_location = "/FileStore/tables/ImportLibraries.csv"
file_type = "csv"

# CSV options
infer_schema = "false"
first_row_is_header = "true"
delimiter = ","
mode ="DROPMALFORMED"
# The applied options are for CSV files. For other file types, these will be ignored.
df = spark.read.format(file_type) \
  .option("inferSchema", infer_schema) \
  .option("header", first_row_is_header) \
  .option("sep", delimiter) \
  .load(file_location)

display(df)




