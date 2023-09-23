import imaplib
import PyPDF2
import mysql.connector

# Open the PDF file in read-binary mode
with open('studentsxl.pdf', 'rb') as pdf_file:
    pdf_reader = PyPDF2.PdfReader(pdf_file)

    # Initialize an empty string to store the extracted text
    extracted_text = ''

    # Iterate through each page in the PDF
    for page_num in range(len(pdf_reader.pages)):
        page = pdf_reader.pages[page_num]
        extracted_text += page.extract_text()



enrol_nos = []
names = []
n=0

for i in extracted_text.split("\n"):
    # print(i, end = ",")
    for j in i.split("  "):
        try :
            int(j) 
            enrol_nos.append(j.strip())
        except Exception:
            names.append(j.strip())
    print()




names0 = [i for i in names if i != '']
enrol_nos.pop(0)
names0.pop(0)
enrol_nos.insert(0, 22110001)
names0.remove('JAGTAP')
names0.remove('SINGH')
names0.remove('BHARDWAJ')

status = ['N']*1367

# Connect to the MySQL server
conn = mysql.connector.connect(host="127.0.0.1", user="root", password="#Attherate123", database="messmanagementsystem")

# Create a cursor object to interact with the database
cursor = conn.cursor()

for i in range(int(len(names0))):
    # Define the SQL INSERT statement
    insert_query = "INSERT INTO stuNames (EnrollmentNo, Names, Recieved) VALUES (%s, %s, %s)"
    values_query = (enrol_nos[i], names0[i], status[i])
    cursor.execute(insert_query, values_query)

conn.commit()
cursor.close()
conn.close()




conn = mysql.connector.connect(host="127.0.0.1", user="root", password="#Attherate123", database="messmanagementsystem")
cursor = conn.cursor()

table_name = "stunames"
query = f"SELECT * FROM {table_name}"

cursor.execute(query)

# Fetch and print the retrieved data
result = cursor.fetchall()
for row in result:
    print(row)

# Close the cursor and the connection
cursor.close()
conn.close()


