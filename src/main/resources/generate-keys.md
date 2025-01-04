## Gerar chave privada

```
openssl genrsa > app.key
```

## Gerar chave publica
```
openssl rsa -in app.key -pubout -out app.pub
```