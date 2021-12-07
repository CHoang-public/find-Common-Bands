import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="root"  #Change to whatever the password is on the machine this is running on
)

print("Connection: " + str(mydb))

setDB = "USE fake_spotifyDB;"

userInput = input("Query for band (WARNING HYPER CASE SENSITIVE): ")

#long multi-line string for the query
#query is really long
testQuery = """SELECT m.musician_name FROM listens_to l
JOIN Musicians m ON m.musician_id = l.musician_id
WHERE l.userid IN(
  SELECT l.userid FROM listens_to l
  JOIN Musicians m ON m.musician_id = l.musician_id
  WHERE m.musician_name = %s)
GROUP BY m.musician_name
ORDER BY COUNT(*) DESC;"""

tqParam = (userInput,)


dbCursor = mydb.cursor()

dbCursor.execute(setDB) #sql command that says to use the specific database
dbCursor.execute(testQuery, tqParam)

resultSet = dbCursor.fetchall()

resultList = []

for tuple in resultSet:
  x = str(tuple)
  x = x.replace("(", "")
  x = x.replace(")", "")
  x = x.replace("'", "")
  x = x.replace(",", "")
  resultList.append(x)
  if len(resultList) == 5:
    break

print(resultList)
