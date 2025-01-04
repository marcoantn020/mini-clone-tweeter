```toml
name = 'Create new tweet'
method = 'POST'
url = '{{baseUrl}}/tweets'
sortWeight = 4000000
id = 'eeb8eac2-1df2-4286-8232-2f5968cb0444'

[auth.bearer]
token = 'eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJDbG9uZVR3ZWV0Iiwic3ViIjoiOGFmMmEyNGItOWVhOS00NmJiLWE0NjktOWUwNTFiMmE5OWM5IiwiZXhwIjoxNzM1NzI5MDE5LCJpYXQiOjE3MzU2OTkwMTksInNjb3BlIjoiYWRtaW4ifQ.aBW5U8OgbpAvdQTDVbrc5t3D1ltmTQLNlDy9gSvaWM8w1W6If72JMnrXJKiK0BZoDyfacaAWL2HvzaJyBPKLRznHV3u38EVApZwSjSvlwO8sGGmW8G6o-aEBz3DN96Y7pk9fzAsy2HWKqSxxMr_nZeODXYejhMGlLOdN3aqrsX7LRvX8Yf8N6YCrLwgVIjRNlphlYYzyd-uaI52yxVxMid18cPRhRHsZ6N2s9burnhe3b08zfhrXpR2ffWhCPNTRprhiqgRFGlCMJwCbSsWbdH6u3lllS5jukWaeEkEM0pa5d1_cBwwNOPeOCVdk4j2U7TXGQu8qvHHWI9OJTkg2AA'

[body]
type = 'JSON'
raw = '''
{
  "content": "tweet admin"
}'''
```
