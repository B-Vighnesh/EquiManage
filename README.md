# 🏷 **EquiManage**

## 📌 **Project Overview**
**EquiManage** is a **JDBC-based Java application** that provides a structured system for **issuing, returning, and managing equipment** in environments such as university labs, electronics labs, and research centers. The system facilitates **student-lab attendant interactions** by maintaining detailed records of available and rented equipment.

### 🚀 **Key Features**
- **Equipment Issuance & Return:** Tracks issued equipment with timestamps.
- **Inventory Management:** Ensures accurate records of available and rented equipment.
- **User Authentication:** Supports login for buyers (students) and sellers (lab attendants).
- **Token System:** Generates transaction tokens for each issued equipment.
- **Database Integration:** Uses **MySQL** for storing users, equipment, and transaction logs.
- **Automated Availability Updates:** Uses a database trigger to update equipment status upon issuance.

---

## 📚 **Project Structure**
- **`Main.java`** - Entry point of the application.
- **`DatabaseConnector.java`** - Establishes and manages database connections.
- **`User.java`** - Handles buyer registration and login.
- **`Seller.java`** - Manages lab attendants (sellers) and equipment records.
- **`Equipment.java`** - Represents the equipment inventory.
- **`IssueList.java`** - Tracks issued equipment and return status.

---

## 🛠 **Setup & Execution**

### **1. Database Setup**
Ensure you have **MySQL** installed and set up your database with the following commands:
```sql
CREATE DATABASE equimanage;
USE equimanage;

CREATE TABLE Buyers (
    buyer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
) AUTO_INCREMENT = 1001;

CREATE TABLE Equipments (
    equipment_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    isAvailable BOOLEAN DEFAULT TRUE NOT NULL
) AUTO_INCREMENT = 1001;

CREATE TABLE Issuelist (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    buyer_id INT NOT NULL,
    equipment_id INT NOT NULL,
    token VARCHAR(100) NOT NULL,
    issue_date DATETIME DEFAULT NOW(),
    return_date DATETIME,
    FOREIGN KEY (buyer_id) REFERENCES buyers(buyer_id) ON DELETE CASCADE,
    FOREIGN KEY (equipment_id) REFERENCES equipments(equipment_id) ON DELETE CASCADE
) AUTO_INCREMENT = 1001;

CREATE TRIGGER set_false
AFTER INSERT ON Issuelist
FOR EACH ROW
UPDATE Equipments
SET isAvailable = FALSE
WHERE equipment_id = NEW.equipment_id;

CREATE TABLE Sellers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    role VARCHAR(100)
) AUTO_INCREMENT = 1000;
```

### **2. Running the Application**
1. **Clone the Repository**
   ```sh
   git clone https://github.com/B-Vighnesh/EquiManage.git
   cd EquiManage
   ```
2. **Add MySQL JDBC Driver**
   - If using IntelliJ IDEA, go to `Project Structure -> Libraries` and add the **MySQL Connector/J**.
3. **Compile & Run**
   ```sh
   javac Main.java
   java Main
   ```

---

## 🎨 **User Roles & Functionalities**

### **🎓 Buyer (Student) Features**
- **Register & Login**
- **View Available Equipment**
- **Request Equipment** (Issue & Return)
- **View Rental History**

### **👨‍💻 Seller (Lab Attendant) Features**
- **Approve Equipment Issuance & Returns**
- **Monitor Student Transactions**
- **Add, Update, and Remove Equipment**
- **Track Inventory in Real Time**

---

## 🔒 **Security Considerations**
- **Passwords are currently stored in plain text** (consider using hashing for security).
- **Ensure MySQL permissions are set appropriately** to prevent unauthorized access.
- **Avoid running SQL queries directly from user inputs** to prevent SQL injection.

---

## 📩 **Contact**
For queries or collaboration, reach out at:  
📧 **vighneshsheregar2004@gmail.com**  
👉 [LinkedIn](https://www.linkedin.com/in/b-vighnesh-kumar/)

