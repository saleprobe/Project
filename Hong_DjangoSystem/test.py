import requests

url = 'http://127.0.0.1:8000/api/student_list'

response = requests.get(url)

print(response.text)
