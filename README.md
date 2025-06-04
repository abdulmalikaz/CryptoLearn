# CryptoLearn
Software security engineering project

# 💳 Secure Banking System – SWE 314 Project

**King Saud University**  
**College of Computer and Information Sciences**  
**Department of Software Engineering**  
**Course:** SWE 314 – Software Security Engineering  
**Semester:** Fall 2024-2025  
**Group 10**

---

## 📘 Project Summary

This project is part of the coursework for SWE 314 – Software Security Engineering. It aims to simulate a **secure online banking system** with a focus on **integrating cybersecurity principles** into its architecture and implementation.

The system supports typical banking features like account access, transactions, and profile updates — while emphasizing **security through encryption**, **input validation**, and **role-based access control (RBAC)**.

> 💡 I contributed to the overall project design and implementation, with a primary focus on developing and testing the **DES (Data Encryption Standard)** algorithm for secure data handling.

---

## 🔐 Key Features

- User login and multi-factor authentication (MFA)
- Account and transaction viewing
- Profile updates with document uploads
- Bank statement generation
- Role-based employee access control
- Secure financial transactions
- Input validation and error handling

---

## 🧠 Security Concepts Applied

| Threat | Countermeasures |
|--------|-----------------|
| SQL Injection | Input validation, parameterized queries |
| DDoS Attacks | Load balancing, firewall configuration |
| MITM Attacks | TLS encryption |
| Insider Threats | RBAC, behavioral monitoring |
| Identity Theft | MFA, encryption |
| Eavesdropping | End-to-end encryption |
| Phishing | Email filtering, MFA |

---

## 🏗 Architecture Overview

The system is based on a **3-tier architecture**:

- **Client Tier**: User interface, HTTPS communication
- **Logic Tier**: Business logic, authentication, security
- **Data Tier**: Secure data storage, RBAC enforcement

---

## 🔧 Algorithms Implemented

- ✅ **DES (Data Encryption Standard)** – fully implemented and tested by me
- 🧩 Other encryption methods (Vigenère, RBAC logic, input validation) were designed collaboratively, and I contributed to planning, reviewing, and testing

---

## 🧪 Testing Highlights

- Unit tests for DES algorithm (encryption & decryption)
- Input validation testing (e.g., SQL injection attempts)
- Authentication testing with valid and invalid OTPs
- Future scope includes penetration testing and more robust QA

---

## 👥 Group Members & Roles

| Name | Student ID 
|------|------------
| عبدالرحمن الزميع | 444101078 
| عبدالله اليوسف | 444102160 
| عبدالملك الزير | 444100765 
| عبدالرحمن اليوسف | 444101277 
| سلمان المطيري | 444102056 

---

## ⚠ Limitations

- Not all algorithms were fully implemented
- Misuse case diagram is available only in UMLet for clarity
- Some features were simulated or partially developed

---

## ✅ Conclusion

This project helped us explore the integration of **security practices** into software development. Through hands-on design and implementation, including the DES encryption algorithm, the system showcases how **security-first thinking** can enhance the reliability and safety of modern applications.

---

## 📄 License

This repository is for educational use and part of the official coursework at King Saud University.


