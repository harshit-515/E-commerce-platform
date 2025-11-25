📦 E-Commerce Platform
Java | JSP | Servlets | JDBC | MySQL
This is a fully functional multi-role e-commerce web application implemented using JSP/Servlets (MVC), JDBC & MySQL, designed as part of the Review-1 submission for the course project.
The system supports Buyers, Sellers, and Admin, each with their own dashboards and dedicated functionalities.
=========================================================================================================================================================================================
🚀 Features Implemented 
👤 Authentication

✔ Buyer Login
✔ Seller Login
✔ Admin Login
✔ Session Management
=========================================================================================================================================================================================
🛒 Buyer Module

Browse all products
Add products to cart
Update or remove items from cart
Checkout & place orders (with full transaction handling)
View past orders
Order success page
Clean, modern buyer dashboard
=========================================================================================================================================================================================
🏪 Seller Module

Seller Dashboard (white theme)
Add New Product
View & Edit Existing Products
Delete Products
View Orders Received
=========================================================================================================================================================================================
🛠️ Admin Module

Admin Dashboard (simple white theme)
Manage Users (Coming in Review-2)
Product Catalogue
Review All Orders
Reports & Stats (Placeholder – “Coming Soon”)
=========================================================================================================================================================================================
🗄️ Database Design

Tables included:
users
products
cart
orders
order_items
wishlist
browsing_history
Important Concepts

✔ Foreign key constraints
✔ Indexed tables
✔ Automatic timestamps
✔ Transaction-safe order creation
✔ Robust DAO architecture
=========================================================================================================================================================================================
🧩 Tech Stack

Component	      Technology
Frontend	      JSP, HTML5, CSS3, Bootstrap
Backend	        Java Servlets (MVC), JDBC
Database	      MySQL
Server	        Apache Tomcat 9
Tools	          Eclipse

=========================================================================================================================================================================================
🔐 Core Implementation Concepts

MVC Architecture Pattern
DAO Layer for Database Operations
PreparedStatement (SQL Injection Safe)
Session Tracking
Servlet-based Routing
MySQL Transaction Control
=========================================================================================================================================================================================
🧪 Fully Implemented for Review-1

✔ User Authentication
✔ Buyer Dashboard
✔ Seller Dashboard
✔ Admin Dashboard
✔ Add / Update / Delete Products
✔ Add to Cart & Checkout
✔ Order Placement + Order Items
✔ Order History (Buyer)
✔ Orders Received (Seller)
✔ Clean UI across all pages
=========================================================================================================================================================================================
📌 Screenshots
Login <img width="943" height="818" alt="Screenshot 2025-11-25 121752" src="https://github.com/user-attachments/assets/202aa978-20bb-450d-b53d-77333feca47a" />
admin panel <img width="1370" height="839" alt="Screenshot 2025-11-25 121803" src="https://github.com/user-attachments/assets/3e1af47b-783e-41f6-a2f4-4d000d45d2e1" />
buyer dashboard <img width="1268" height="932" alt="Screenshot 2025-11-25 124114" src="https://github.com/user-attachments/assets/ce324ee7-fa70-4244-825b-470d3eee982b" />
products <img width="1222" height="953" alt="Screenshot 2025-11-25 124127" src="https://github.com/user-attachments/assets/a018c6a2-e324-4c37-8169-0f7c27f96603" />
cart <img width="920" height="504" alt="Screenshot 2025-11-25 124143" src="https://github.com/user-attachments/assets/7b945e71-bdb2-4b1f-8349-556f855f242f" />
checkout <img width="1089" height="625" alt="Screenshot 2025-11-25 124209" src="https://github.com/user-attachments/assets/2976cb99-0c9c-4b98-901a-760da98bfd7d" />
order success <img width="881" height="578" alt="Screenshot 2025-11-25 124216" src="https://github.com/user-attachments/assets/dc2ea8a6-f67d-4989-8007-5b356ed2cb6c" />
seller dashboard <img width="1245" height="922" alt="Screenshot 2025-11-25 124302" src="https://github.com/user-attachments/assets/8ee34f22-8366-4a38-81d9-ca0c0d867aee" />
my Products <img width="1268" height="881" alt="Screenshot 2025-11-25 124308" src="https://github.com/user-attachments/assets/aa356746-410f-4d0f-a140-74e8a0010db8" />
add product <img width="921" height="826" alt="Screenshot 2025-11-25 124317" src="https://github.com/user-attachments/assets/037d406e-8c5f-4c5c-b267-b31d8214a85c" />
user database <img width="1912" height="372" alt="Screenshot 2025-11-25 140048" src="https://github.com/user-attachments/assets/42cd2cb1-708a-436e-b1e0-cc5032513de9" />
product table <img width="1675" height="187" alt="Screenshot 2025-11-25 140112" src="https://github.com/user-attachments/assets/cbd4e4be-3f74-4bd6-96f1-10c26fc92058" />
orders table <img width="1150" height="278" alt="Screenshot 2025-11-25 140158" src="https://github.com/user-attachments/assets/0d8c2e47-d635-4dee-9ee6-dc6b026db109" />

===========================================================================================================================================================================================
📦 How to Run the Project

Install MySQL, Tomcat 9, and JDK 17 or 21.
Import the project into Eclipse .
Create the database using the SQL file:
ecommerce_db.sql
DBConnection.java
Deploy the project to Tomcat 9.
Visit:
http://localhost:8080/ecommerce-platform




