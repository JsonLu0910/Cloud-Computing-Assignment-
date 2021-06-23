#PART 1
# File location and type for Extends.csv
file_location = "/FileStore/tables/Extends_Implement.csv"
file_type = "csv"

# CSV options
infer_schema = "false"
first_row_is_header = "true"
delimiter = ","

# The applied options are for CSV files. For other file types, these will be ignored.
df = spark.read.format(file_type) \
  .option("inferSchema", infer_schema) \
  .option("header", first_row_is_header) \
  .option("sep", delimiter) \
  .load(file_location)

display(df)
#PART 2
# Create a view or table for Extends.csv
temp_table_name = "Extends_Implement"
df.createOrReplaceTempView(temp_table_name)

#PART 3(INSTRUCTION, DO NOT COPY THIS LINE)
#Once table has succesfully created, now lets download the csv and rename it as Extends_Implement.csv