//Read all the file first
val readAllfile = sc.textFile("FileStore/tables/Java/*")
//Count the total line of code
val totalcount = readAllfile.count