# 🏙️ Aura Retail OS

**Designing an Autonomous Modular Smart-City Retail Infrastructure**

> Course: Object Oriented Programming (IT620) | DA-IICT  
> Team: Code Crafters | Path B — Modular Hardware Platform

---

## 📌 Overview

Aura Retail OS is a modular Java platform that powers autonomous retail kiosks
across the smart city of Zephyrus — deployed in hospitals, metro stations,
universities, and disaster zones. The system is designed around 10 OOP design
patterns to ensure hardware extensibility, payment flexibility, and strong
modular architecture.

---

## 👥 Team — Code Crafters

| Name    | Roll Number | Responsibility                        |
|---------|-------------|---------------------------------------|
| Naitik  | 202512006   | Core, Singleton, Facade, Command, Decorator (base) |
| Priya   | 202512003   | Abstract Factory, Adapter (Payment)   |
| Gaurang | 202512007   | Bridge, Strategy, Decorator (Modules) |
| Harshal | 202512013   | Proxy, Composite, Persistence         |

---

## 🧩 Design Patterns Implemented

| Pattern          | Where Used                        |
|------------------|-----------------------------------|
| Singleton        | CentralRegistry                   |
| Abstract Factory | Kiosk creation (3 kiosk types)    |
| Bridge           | Hardware abstraction layer        |
| Strategy         | Dispenser behaviour at runtime    |
| Decorator        | Optional hardware modules         |
| Proxy            | Secure inventory access           |
| Composite        | Nested product/bundle inventory   |
| Adapter          | Payment provider integration      |
| Command          | Transaction system                |
| Facade           | KioskInterface (external API)     |

---

## 🗂️ Project Structure
```
src/
└── com/aura/retailos/
    ├── core/          # Singleton, Facade, Command, Monitoring
    ├── factory/       # Abstract Factory — kiosk types
    ├── kiosk/         # BaseKiosk + Decorator modules
    ├── hardware/
    │   ├── bridge/    # Bridge pattern — dispenser abstraction
    │   └── strategy/  # Strategy pattern — dispensing algorithms
    ├── inventory/     # Composite + Proxy + JSON persistence
    ├── payment/       # Adapter pattern — payment providers
    └── commands/      # Command pattern — transactions
```

---

## ▶️ How to Run

**Requirements:** Java 17+, Maven or plain javac
```bash
# Clone the repo
git clone https://github.com/<your-username>/aura-retail-os.git
cd aura-retail-os

# Compile
javac -d out src/com/aura/retailos/**/*.java

# Run simulation
java -cp out com.aura.retailos.Main
```

---

## 🎬 Simulation Scenarios

1. **Adding a hardware module at runtime** — Attach RefrigerationDecorator to a FoodKiosk dynamically
2. **Integrating a new payment provider** — Plug in DigitalWalletAdapter without touching existing code
3. **Nested bundle availability** — EmergencyKit propagates unavailability when a nested item goes out of stock

---

## 📄 Documentation

- `docs/PRD.docx` — Full Product Requirements Document
- `docs/ClassDiagram.drawio` — Class diagram
- `docs/SequenceDiagram.drawio` — Sequence diagram
- `docs/ArchitectureDiagram.drawio` — Subsystem architecture

---

## 📁 Data Persistence

Inventory, transaction history, and system config are stored as JSON files in the `data/` directory and loaded on startup via `InventoryPersistence.java`.

---

## 👨‍🏫 Guided by

Prof. Sourish Dasgupta | DA-IICT, Gandhinagar

---

##  Folder Structure in Repo
```
aura-retail-os/
├── src/
│   └── com/aura/retailos/   ← all Java packages here
├── data/
│   ├── inventory.json
│   ├── transactions.json
│   └── config.json
├── docs/
│   ├── PRD.docx
│   ├── ClassDiagram.drawio
│   ├── SequenceDiagram.drawio
│   └── ArchitectureDiagram.drawio
├── .gitignore
└── README.md
```
