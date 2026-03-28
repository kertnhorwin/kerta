# 🎓 Scholarship Management System

<div align="center">

[![Laravel](https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white)](https://laravel.com)
[![Vue.js](https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D)](https://vuejs.org)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://mysql.com)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)](https://postman.com)

**A comprehensive web application for managing scholarship programs, students, and user accounts**

</div>

---

## 👨‍💻 Developer

<table>
  <tr>
    <td align="center">
      <img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" width="100" height="100" style="border-radius: 50%;"/>
      <br />
      <strong>King Jon C. Baga</strong>
      <br />
      <em>BSIT - 2D</em>
    </td>
  </tr>
</table>

---

## 📋 Table of Contents

- [Prerequisites](#-prerequisites)
- [Project Features](#-project-features)
- [API Endpoints](#-api-endpoints)
- [Installation Guide](#-installation-guide)
- [Tech Stack](#-tech-stack)
- [License](#-license)

---

## 🛠 Prerequisites

Ensure you have the following tools installed before setting up the project:

| Tool | Purpose | Status |
| :--- | :--- | :---: |
| **XAMPP** | Local Server & MySQL Database | ✅ Required |
| **VS Code** | Primary Code Editor | ✅ Recommended |
| **Composer** | PHP Dependency Manager | ✅ Required |
| **Node.js** | Frontend Asset Management | ✅ Required |
| **Postman** | API Testing & Documentation | ✅ Optional |

> **📌 Note:** The Postman workspace is available for API testing. [Access the workspace here](https://www.postman.com/shusaaitano-6045566/team-workspace/folder/53028294-43f8e12d-c258-4bd6-a216-5c09fa320175?sideView=agentMode)

---

## ✨ Project Features

### 🔐 Authentication Module
| Method | Endpoint | Description |
| :----- | :------- | :---------- |
| `POST` | `/api/register` | Create a new user account |
| `POST` | `/api/login` | Authenticate user & get token |
| `POST` | `/api/logout` | Invalidate user session |

---

### 👥 User Management
Manage system users with full CRUD operations:

| Action | Icon | Description |
| :----- | :--- | :---------- |
| **Create** | ➕ | Add new users to the system |
| **Read** | 👁️ | View all user records |
| **Update** | ✏️ | Edit existing user information |
| **Delete** | 🗑️ | Remove users from the system |

---

### 🎓 Student Management
Comprehensive student record management:

| Action | Icon | Description |
| :----- | :--- | :---------- |
| **Create** | ➕ | Enroll new students |
| **Read** | 📋 | Display complete student list |
| **Update** | ✏️ | Modify student details |
| **Delete** | 🗑️ | Remove student records |

---

### 💰 Scholarship Management
Manage scholarship programs efficiently:

| Action | Icon | Description |
| :----- | :--- | :---------- |
| **Create** | 🏆 | Add new scholarship programs |
| **Read** | 📊 | View all available scholarships |
| **Update** | ⚙️ | Update program details & criteria |
| **Delete** | 🗑️ | Remove discontinued programs |
