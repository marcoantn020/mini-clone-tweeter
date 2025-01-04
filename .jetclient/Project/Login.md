```toml
name = 'Login'
method = 'POST'
url = '{{baseUrl}}/login'
sortWeight = 1000000
id = '239bf358-a711-4826-be0f-1bce5c813ea5'

[body]
type = 'JSON'
raw = '''
{
  "username": "basic",
  "password": "123"
}'''
```
