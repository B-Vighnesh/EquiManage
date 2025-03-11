# 🏷 **EquiManage**

## 📌 **Project Overview**
**EquiManage** is a **dedicated console-based application** designed to help users **issue, return, and manage equipment** efficiently. The system ensures seamless tracking of issued items and maintains records of available equipment.

In scenarios like an **electronics lab**, the **instructor (seller)** issues gadgets to students and records the entry through this program. Students can then check information about **available gadgets, issued gadgets, and gadgets assigned to them**.

### 🚀 **Key Features**
- **Equipment Issuance & Return:** Users can issue and return equipment with a structured process.
- **Inventory Management:** Tracks available and in-use equipment.
- **Authentication:** Basic login mechanism for users.
- **Token System:** Uses tokens to manage equipment transactions securely.

---

## 🏷 **Project Structure**
- **`EquipmentManagement.java`** - The main class handling the overall flow of the system.
- **`User.java`** - Manages user registrations, logins, and interactions.
- **`Equipment.java`** - Maintains the list of available equipment and their details.
- **`Token.java`** - Generates unique transaction tokens for tracking.

---

## 🛠️ **Setup & Execution**
1. **Clone the repository**
   ```sh
   git clone https://github.com/B-Vighnesh/EquiManage.git
   cd EquiManage
   ```
2. **Compile and Run**
   ```sh
   javac EquipmentManagement.java
   java EquipmentManagement
   ```
3. **Follow the console prompts** to login and interact with the system.

---

## 🔧 **Use Cases**
EquiManage can be used in various fields where equipment management is required:
- **Electronics Labs:** Instructors issue lab equipment to students and track their usage.
- **Construction Sites:** Keep track of heavy machinery and tools assigned to workers.
- **University Labs:** Help students and staff manage lab equipment usage efficiently.
- **Event Management:** Maintain records of rented equipment for events and conferences.

---

## 🔒 **Security Considerations**
- This is a **basic console-based project**, so it does not use a database or advanced authentication.
- The project currently **stores credentials in plain text**, which can be a security risk.
- Consider implementing **hashed passwords** and **database storage** for enhanced security.

---

## 📩 **Contact**
For queries and collaborations, reach out at:  
📧 **vighneshsheregar2004@gmail.com**  
🔗 [LinkedIn](https://www.linkedin.com/in/b-vighnesh-kumar/)  

