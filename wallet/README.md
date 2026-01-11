# Oracle Wallet Setup

This directory should contain the extracted contents of your Oracle Cloud wallet.

## Setup Steps

1. Download `Wallet_DBCPOC.zip` from Oracle Cloud Console
2. Extract the contents to this directory:
   ```bash
   unzip Wallet_DBCPOC.zip -d ./wallet/
   ```

3. After extraction, you should have these files:
   - `cwallet.sso` - Auto-login wallet
   - `ewallet.p12` - PKCS#12 wallet
   - `tnsnames.ora` - TNS configuration
   - `sqlnet.ora` - SQL*Net configuration
   - `ojdbc.properties` - JDBC properties
   - `keystore.jks` - Java keystore
   - `truststore.jks` - Java truststore

## Running with Oracle Profile

```bash
mvn spring-boot:run -Dspring.profiles.active=oracle
```

## Security Note

Wallet files contain sensitive credentials and are excluded from git via `.gitignore`.
Never commit wallet files to version control.
