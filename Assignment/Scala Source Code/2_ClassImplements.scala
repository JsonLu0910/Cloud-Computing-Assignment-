//check from the main file if there is any class implement from other class
val MainImplement = main.filter(mainImpl=> mainImpl._2.contains("implement"))
val MIPCount = MainImplement.count
val Mimplement = MainImplement.keys.collect


//check from the entities file if there is any class implement from other class
val ExImplement = entities.filter(exImpl=> exImpl._2.contains("implement"))
val ExIPCount = ExImplement.count
val Enimplement = ExImplement.keys.collect

//check from the levels file if there is any class implement from other class
val LvlImplement = levels.filter(lvlImpl=> lvlImpl._2.contains("implement"))
val LvlIPCount = LvlImplement.count
val Lvimplement = LvlImplement.keys.collect

//check from the menu file if there is any class implement from other class
val MenuImplement = menu.filter(MenuImpl=> MenuImpl._2.contains("implement"))
val MenuIPCount = MenuImplement.count
val Menuimplement = MenuImplement.keys.collect

//Combine all the file and calculate it
val total = MainImplement.union(ExImplement).union(LvlImplement).union(MenuImplement)
val totalCount = total.count
total.keys.collect.foreach(println)

