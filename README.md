# Oracle HBM Spring Boot POC

A Spring Boot 2.7.x proof-of-concept application demonstrating Hibernate XML mapping files (`.hbm.xml`) with Oracle Autonomous Database connectivity.

## Features

- **Spring Boot 2.7.18** with Java 11
- **Hibernate XML Mapping** (no `@Entity` annotations) for ORM
- **Dual Database Support**: H2 (local development) and Oracle Autonomous DB (production)
- **REST APIs** for CRUD operations on dimension and fact tables
- **Oracle Wallet Support** for secure Oracle Autonomous DB connections

## Project Structure

```
oracle-hbm-springboot-poc/
├── src/main/java/com/example/oraclehbm/
│   ├── config/           # Hibernate configuration
│   ├── controller/       # REST controllers
│   ├── model/            # Domain POJOs
│   ├── repository/       # Data access layer
│   └── OracleHbmApplication.java
├── src/main/resources/
│   ├── hbm/              # Hibernate mapping files (.hbm.xml)
│   ├── db/               # SQL scripts
│   ├── application.properties
│   ├── application-h2.properties
│   └── application-oracle.properties
└── postman/              # Postman collection
```

## Database Schema

### Tables

| Table | Type | Description |
|-------|------|-------------|
| `DIM_CUSTOMER` | Dimension | Customer master data |
| `DIM_PRODUCT` | Dimension | Product catalog |
| `FACT_ORDER` | Fact | Order header information |
| `FACT_ORDER_LINE` | Fact | Order line items |

## Prerequisites

- Java 11 (JDK 11)
- Maven 3.6+
- (Optional) Oracle Autonomous Database with wallet credentials

## Running Locally with H2 Database

### 1. Build the project

```bash
mvn clean package -DskipTests
```

### 2. Run with H2 profile (default)

```bash
mvn spring-boot:run
```

Or run the JAR:

```bash
java -jar target/oracle-hbm-springboot-poc-1.0.0-SNAPSHOT.jar
```

The application will start on `http://localhost:8080` with an in-memory H2 database pre-loaded with sample data.

### 3. Access H2 Console (optional)

Navigate to `http://localhost:8080/h2-console` with:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Running with Oracle Autonomous Database

### Option 1: Wallet-Based Connection (Recommended)

1. **Download the wallet** from Oracle Cloud Console:
   - Navigate to your Autonomous Database
   - Click "Database connection"
   - Download the wallet ZIP file
   - Extract to a local directory (e.g., `/path/to/wallet`)

2. **Configure the application**:

   Edit `application-oracle.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@<tns_alias>?TNS_ADMIN=/path/to/wallet
   spring.datasource.username=ADMIN
   spring.datasource.password=your_password
   ```

   Replace `<tns_alias>` with your TNS alias (e.g., `mydb_high`, `mydb_medium`, `mydb_low`).

3. **Create the schema** in Oracle:

   Run the SQL script `src/main/resources/db/schema-oracle.sql` in SQL Developer or SQLcl.

4. **Run with Oracle profile**:

   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=oracle
   ```

   Or using environment variables:
   ```bash
   SPRING_PROFILES_ACTIVE=oracle \
   WALLET_PATH=/path/to/wallet \
   ORACLE_USER=ADMIN \
   ORACLE_PASSWORD=your_password \
   java -jar target/oracle-hbm-springboot-poc-1.0.0-SNAPSHOT.jar
   ```

### Option 2: Non-Wallet JDBC URL (mTLS Disabled)

If you've disabled mTLS on your Oracle Autonomous Database:

1. **Configure the application**:

   Edit `application-oracle.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@(description=(retry_count=3)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.us-ashburn-1.oraclecloud.com))(connect_data=(service_name=abc123_mydb_high.adb.oraclecloud.com))(security=(ssl_server_dn_match=no)))
   ```

2. **Run with Oracle profile** as shown above.

## API Endpoints

### Customers (`/api/customers`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customers` | Get top N customers (default 10) |
| GET | `/api/customers/all` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| GET | `/api/customers/active` | Get active customers |
| GET | `/api/customers/city/{city}` | Get customers by city |
| GET | `/api/customers/count` | Get customer count |

### Products (`/api/products`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get top N products (default 10) |
| GET | `/api/products/all` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/code/{productCode}` | Get product by code |
| GET | `/api/products/category/{category}` | Get products by category |
| GET | `/api/products/active` | Get active products |
| GET | `/api/products/count` | Get product count |

### Orders (`/api/orders`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/orders` | Get top N orders (default 10) |
| GET | `/api/orders/all` | Get all orders |
| GET | `/api/orders/{id}` | Get order by ID |
| GET | `/api/orders/number/{orderNumber}` | Get order by order number |
| GET | `/api/orders/customer/{customerId}` | Get orders by customer |
| GET | `/api/orders/status/{status}` | Get orders by status |
| GET | `/api/orders/date-range?startDate=&endDate=` | Get orders by date range |
| GET | `/api/orders/count` | Get order count |

### Order Lines (`/api/order-lines`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/order-lines` | Get top N order lines (default 10) |
| GET | `/api/order-lines/all` | Get all order lines |
| GET | `/api/order-lines/{id}` | Get order line by ID |
| GET | `/api/order-lines/order/{orderId}` | Get lines by order ID |
| GET | `/api/order-lines/product/{productId}` | Get lines by product ID |
| GET | `/api/order-lines/count` | Get order line count |

## Importing Postman Collection

1. Open Postman
2. Click **Import** button
3. Select the file `postman/oracle-hbm-poc.postman_collection.json`
4. The collection "Oracle HBM POC" will be imported with all API endpoints
5. Ensure the application is running on `http://localhost:8080`
6. Execute requests from the collection

## Sample API Calls

```bash
# Get top 5 customers
curl http://localhost:8080/api/customers?limit=5

# Get customer by ID
curl http://localhost:8080/api/customers/1

# Get all products in Electronics category
curl http://localhost:8080/api/products/category/Electronics

# Get order with details
curl http://localhost:8080/api/orders/1

# Get order lines for an order
curl http://localhost:8080/api/order-lines/order/1
```

## Hibernate XML Mapping

This project uses Hibernate XML mapping files (`.hbm.xml`) instead of JPA annotations. The mapping files are located in `src/main/resources/hbm/`:

- `DimCustomer.hbm.xml` - Maps `DimCustomer` POJO to `DIM_CUSTOMER` table
- `DimProduct.hbm.xml` - Maps `DimProduct` POJO to `DIM_PRODUCT` table
- `FactOrder.hbm.xml` - Maps `FactOrder` POJO to `FACT_ORDER` table with customer relationship
- `FactOrderLine.hbm.xml` - Maps `FactOrderLine` POJO to `FACT_ORDER_LINE` table with order and product relationships

## Configuration Reference

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `h2` |
| `WALLET_PATH` | Path to Oracle wallet directory | - |
| `ORACLE_USER` | Oracle database username | `ADMIN` |
| `ORACLE_PASSWORD` | Oracle database password | - |

### Application Properties

See `application-h2.properties` and `application-oracle.properties` for detailed configuration options.

## Troubleshooting

### Common Issues

1. **ORA-12505: TNS:listener does not currently know of SID**
   - Verify your TNS alias matches one in the wallet's `tnsnames.ora` file

2. **IO Error: could not resolve the connect identifier**
   - Check that `TNS_ADMIN` points to the correct wallet directory
   - Ensure the wallet contains `tnsnames.ora`, `sqlnet.ora`, and credential files

3. **Connection timeout**
   - Verify network connectivity to Oracle Cloud
   - Check if the database is running in Oracle Cloud Console

4. **H2 console not accessible**
   - Ensure you're running with the `h2` profile
   - Check that `spring.h2.console.enabled=true` is set

## License

This project is provided as-is for demonstration purposes.
