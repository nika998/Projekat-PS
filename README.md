# **Project Overview**

This project is a client-server application designed to manage airline operations, including flights, passengers, and attendance records. The server handles the business logic and database interactions, while the client provides a user interface for interacting with the system.

## **Installation**
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-directory>

2. **Build the project:**
   - **Client:** Navigate to the `ND_2017_0177_Client` directory and use your preferred IDE or build tool to compile the client-side code.
   - **Server:** Navigate to the `ND_2017_0177_Server` directory and compile the server-side code.

## **Configuration**

### **Database Configuration:**
The server module requires a MySQL database. Configure the database connection details in the `Database.java` file under `rs/bg/ac/fon/ps/database`.  
You can customize the database settings (e.g., URL, username, password) according to your environment.

## **Building and Running**

### **Server:**
1. Compile the server code.
2. Run the `Main.java` file in the `rs/bg/ac/fon/ps/main` package to start the server.

### **Client:**
1. Compile the client code.
2. Run the client application by executing the main class in the `rs/bg/ac/fon/ps/client` package.

## **Usage**

### **Client:**
After starting the server, the client application can be used to interact with the system, performing operations like managing flights, passengers, and attendance records.
