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
val EntitiesExtend = entities.filter(entity=> entity._2.contains("extends"))
val EntitiesCount = EntitiesExtend.count
val EExtend = EntitiesExtend.keys.collect

//check from the main file if there is any class extends from other class
val MainExtend = main.filter(mainextend=> mainextend._2.contains("extends"))
val MECount = MainExtend.count
val MaExtend = MainExtend.keys.collect

//check from the level file if there is any class extends from other class
val LevelExtend = levels.filter(lvlextend=> lvlextend._2.contains("extends"))
val LVLECount = LevelExtend.count
val LExtend = LevelExtend.keys.collect

//check from the menu file if there is any class extends from other class
val MenuExtend = menu.filter(menuextend=> menuextend._2.contains("extends"))
val MCount = MenuExtend.count
val MeExtend = MenuExtend.keys.collect

//Combine all the file and calculate it
val total = EntitiesExtend.union(MainExtend).union(LevelExtend).union(MenuExtend)
val totalCount = total.count
total.keys.collect.foreach(println)























