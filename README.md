# Cybersecurity Incident Tracker

## Cyenadi Greene, Sterling Vieau, Adrian Salcedo

Sterling Vieau – UI Designer
Cyenadi Greene - security notification
Adrian Salcedo - backend/database development


---

## Project Description

**CyberSentinel** is a lightweight, desktop-based Cybersecurity Incident Tracker built using Java Swing. It allows organizations to log, monitor, and analyze security incidents in a structured and user-friendly manner. This tool is designed for internal cybersecurity teams to ensure incidents are properly recorded and escalated based on severity.

Key features include:
- User authentication system (Login/Sign-Up)
- Real-time incident logging and monitoring
- File security scanning
- Role-based access control foundation
- Extensible reporting architecture

---

## How to Run

### Requirements

- **JDK**: Java 17 or newer
- **IDE**: IntelliJ IDEA (Recommended)
- **Build Tool**: Maven (Bundled in IntelliJ)
- **Libraries**: None (uses only built-in Java Swing and standard Java libraries)

### Setup Steps

1. **Download or Clone the Repository**
   ```bash
   git clone https://github.com/your-username/CyberSentinel.git
   ```

2. **Open in IntelliJ**
   - Go to `File → Open` and select the project folder.
   - Let IntelliJ import the Maven project.

3. **Mark Resources Folder**
   - Right-click `src/main/resources` → "Mark Directory as" → "Resources Root"

4. **Run the App**
   - Right-click `Main.java` → Run `Main`

---

## Features Implemented

- [x] **Login & Sign-Up** as separate windows
- [x] **Incident Logging**: Log data breaches, phishing attempts
- [x] **Real-Time Monitoring**: View incidents and their severity
- [x] **File Scanner**: Scan any file for suspicious content (e.g., `eval(`, `password`, etc.)
- [x] **OOP Principles Applied**:
  - Encapsulation for user and incident data
  - Inheritance for specific incident types
  - Polymorphism for custom incident notifications
  - Abstraction via the `IncidentManager` interface
- [x] **Swing GUI**: Responsive and minimal interface
- [x] **Background Image Support** for security dashboard

---

## Future Work

- [ ] Add user roles (admin vs analyst) with access control
- [ ] Export incident reports to PDF or CSV
- [ ] Save incidents to a persistent database (PostgreSQL)
- [ ] Real-time incident alerts via system tray or email
- [ ] Improve file scanner with machine learning-based heuristics

---

## Known Issues

- File scanner works best with text-based content; binary file analysis is limited.
- No encryption or hashing is currently applied to stored passwords.
- All data is held in-memory; closing the application erases all incidents/users.
- 🖼If the background image doesn't display, check that `background.png` is placed in `src/main/resources` and marked as a resource folder.
