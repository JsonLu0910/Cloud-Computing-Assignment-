#PART 1
# File location and type
file_location = "/FileStore/tables/Function.csv"
file_type = "csv"

# CSV options
infer_schema = "false"
first_row_is_header = "true"
delimiter = ","
mode ="DROPMALFORMED"
# The applied options are for CSV files. For other file types, these will be ignored.
df5 = spark.read.format(file_type) \
  .option("inferSchema", infer_schema) \
  .option("header", first_row_is_header) \
  .option("sep", delimiter) \
  .load(file_location)

display(df5)

#PART 2
# Create a view or table
temp_table_name = "Function"
df5.createOrReplaceTempView(temp_table_name)

#PART#(INSTRUCTIONS DO NOT COPY THIS LINE!!)
#Once the table is created we can click on DOWNLOAD button on the table and the csv will be download, then rename the csv to Function.csv
