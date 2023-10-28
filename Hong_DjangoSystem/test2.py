import requests

url = 'http://127.0.0.1:8000/api/auth'

# response = requests.get(url)
response = requests.post(url, data={'username': 'admin', 'password': '123123'})

print(response.text)
myToken = response.json()

header = {'Authorization': 'Token ' + myToken['token']}
response = requests.get('http://127.0.0.1:8000/api/student_list', headers=header)
print(response.text)
