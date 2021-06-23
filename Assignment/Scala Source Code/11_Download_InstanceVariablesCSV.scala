#PART 1
# File location and type for InstanceVariables.csv
file_location = "/FileStore/tables/InstanceVariables.csv"
file_type = "csv"

# CSV options
infer_schema = "false"
first_row_is_header = "true"
delimiter = ","

# The applied options are for CSV files. For other file types, these will be ignored.
df2 = spark.read.format(file_type) \
  .option("inferSchema", infer_schema) \
  .option("header", first_row_is_header) \
  .option("sep", delimiter) \
  .load(file_location)

display(df2)
#PART 2
# Create a view or table for InstanceVariables.csv
temp_table_name = "InstanceVariables"
df2.createOrReplaceTempView(temp_table_name)

#PART 3(DO NOT COPY THIS LINE!!)
#Once the table has create now we can click on the download button on the table, and rename the csv as InstanceVariables

