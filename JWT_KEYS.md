## Commands to generate JWT Keys

### Dependences
- **OpenSSL**

### Steps

Create a directory for generated keys.
```bash
mkdir jwt
```

Generate a RSA private key with 2048 bits (256 bytes).
```bash
openssl genrsa -out jwt\rsaPrivateKey.pem 2048
```

Generate a public key from RSA private key generated at previous step.
```bash
openssl rsa -pubout -in jwt\rsaPrivateKey.pem -out jwt\publicKey.pem
```

Generate a private key from RSA private key.
```bash
openssl pkcs8 -topk8 -nocrypt -inform pem -in jwt\rsaPrivateKey.pem -outform pem -out jwt\privateKey.pem
```