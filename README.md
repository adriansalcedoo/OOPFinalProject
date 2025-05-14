# Cybersecurity Incident Tracker

## Cyenadi Greene, Sterling Vieau, Adrian Salcedo

Sterling Vieau – UI Designer
Cyenadi Greene - security notification
Adrian Salcedo - backend/database development


---

A Java Swing-based application for logging, detecting, and monitoring cybersecurity incidents. Designed with secure login, file scanning, and visual reporting to help simulate real-world incident response practices.

---

## 📋 Features

- 🔐 **Secure Authentication**
  - User accounts with SHA-256 password hashing
  - Users only see their own data (per-user isolation)

- 📝 **Incident Logging**
  - Manual logging of incidents: Data Breach, Phishing, Malware, etc.
  - Severity levels: Low, Medium, High

- 🧠 **Smart File Scanner**
  - Scans uploaded files for suspicious patterns
  - Auto-detects incident type (phishing, malware, DDoS, etc.)
  - Notifies user and logs the threat

- 📊 **Dashboard + Visual Reports**
  - Filter incidents by type/severity
  - View summaries in a dynamic chart (separate tab)
  - Export reports to `.txt`

- 🌄 **Background Image**
  - Custom background image for enhanced UI

---

## 🧠 OOP Principles Used

| Principle      | Implementation Example |
|----------------|------------------------|
| **Inheritance**     | `PhishingAttempt` extends abstract `Incident` |
| **Polymorphism**    | `incident.notifyResponseTeam()` dynamically resolved |
| **Abstraction**     | `Incident` class defines abstract methods |
| **Encapsulation**   | User data and passwords are private and accessed via methods |




Run in IntelliJ or your IDE
Open the project

Make sure sqlite-jdbc and jfreechart are in your pom.xml dependencies

Run Main.java

▶How to Run This Project
Requirements
Java JDK 17 or later

Maven (or use IDE’s built-in Maven support)

IntelliJ IDEA or any Java IDE

SQLite JDBC driver (already included in pom.xml)

Steps to Run
Option A: Using IntelliJ IDEA (Recommended)
Clone or download the project to your machine

Open IntelliJ IDEA

Select File > Open > select the project folder

IntelliJ will detect it's a Maven project and resolve dependencies automatically

Ensure the image VIRUS DETECTOR.png is in:

Run Main.java 
