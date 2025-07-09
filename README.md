# To-Do App (Java, MySQL)

A simple, professional, and efficient command-line To-Do application written in Java, using MySQL for persistent storage. Organize your tasks by categories, add and remove items, and view your categorized to-do list with ease.

---

## Features

- **View Items:** Display all to-do items grouped by category.
- **Add Item:** Add a new item to an existing category.
- **Delete Item:** Remove an item by its name.
- **Persistent Storage:** All data is stored in a MySQL database.

---

## Prerequisites

- **Java 17+** (Project is set to use Java 23, but Java 17 or newer should work)
- **MySQL Server** (running locally or accessible remotely)
- **MySQL Connector/J** (already included in `lib/`)

---

## Database Setup

### Option 1: Use the Provided SQL File

1. **Import the provided `shopping.sql` file:**

   - Open your MySQL client (phpMyAdmin, MySQL Workbench, or command line).
   - Create a new database (for example, `shopping`):
     ```sql
     CREATE DATABASE shopping;
     USE shopping;
     ```
   - Import the `shopping.sql` file:
     - **phpMyAdmin:** Use the "Import" tab and select `shopping.sql`.
     - **Command line:**
       ```sh
       mysql -u root -p shopping < shopping.sql
       ```
   - The SQL file will create the required tables (`categories`, `items`) and insert sample data.

2. **Update the database name in your Java code if needed:**
   - By default, the app connects to a database named `to_do_app_java` (see `Database.java`).
   - You can either:
     - Change the database name in `Database.java` to `shopping`, **or**
     - Rename the imported database to `to_do_app_java`:
       ```sql
       RENAME DATABASE shopping TO to_do_app_java;
       ```

### Option 2: Manual Setup

If you prefer to set up the database manually, use the following SQL:

```sql
CREATE DATABASE to_do_app_java;
USE to_do_app_java;

CREATE TABLE categories (
    id_category INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE items (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_category INT NOT NULL,
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);

-- Example categories
INSERT INTO categories (name) VALUES ('Work'), ('Personal'), ('Shopping');
```

---

## How to Run

1. **Clone the repository and open in IntelliJ IDEA or your favorite IDE.**
2. **Build the project.**
3. **Ensure MySQL server is running and the database is set up as above.**
4. **Run `Main.java`.**

---

## Usage

The app runs in a loop and presents a simple menu:

```
1. show items
2. delete item
3. add item
Choose option:
```

- **Show items:** Lists all items grouped by category.
- **Delete item:** Prompts for the item name to delete.
- **Add item:** Prompts for category and item name (category must exist in the database).

---

## Project Structure

```
src/
  Main.java        # Entry point, menu logic
  Database.java    # Database connection and queries
  Utils.java       # Grouping and displaying items
  Item.java        # Data model for items
lib/
  mysql-connector-j-9.3.0.jar
shopping.sql       # Database schema and sample data
```

---

## Customization

- To change database credentials, edit the `connect()` method in `Database.java`.
- To add new categories, insert them directly into the `categories` table in MySQL.
