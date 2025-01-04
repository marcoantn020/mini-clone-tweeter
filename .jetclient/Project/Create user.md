```toml
name = 'Create user'
method = 'POST'
url = '{{baseUrl}}/users'
sortWeight = 2000000
id = 'a8d517ff-652b-420c-803a-ffc57b66bc3a'

[body]
type = 'JSON'
raw = '''
{
  "username": "Basic User",
  "password": "123"
}'''
```
