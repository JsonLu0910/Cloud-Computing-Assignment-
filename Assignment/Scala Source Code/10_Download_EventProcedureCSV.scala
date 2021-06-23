#PART 1
# File location and type
file_location = "/FileStore/tables/EventProcedure.csv"
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

#PART 2
# Create a view or table
temp_table_name = "EventProcedure"
df.createOrReplaceTempView(temp_table_name)

#(Instructions DO NOT COPY THIS LINE!!) 
#PART 3
#once the PART 2 is done, all we need to do just CLICK on the download CSV button on the table, after download we need to rename the export.csv to EventProcedure.csv
