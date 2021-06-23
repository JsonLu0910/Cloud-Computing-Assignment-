#PART 1
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
#create a temp table inside the default database
temp_table_name = "Import"
df.createOrReplaceTempView(temp_table_name)


#PART 2 (DO NOT COPY THIS LINE!!)
%sql

/* Query the created temp table in a SQL cell */
select * from `Import` where NumberCalled  >4

