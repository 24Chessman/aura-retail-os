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

| Pattern          | Where Used                        | Status         |
|------------------|-----------------------------------|----------------|
| Singleton        | CentralRegistry                   | ✅ Complete     |
| Abstract Factory | Kiosk creation (3 kiosk types)    | ✅ Complete     |
| Command          | Transaction system                | ✅ Complete     |
| Facade           | KioskInterface (external API)     | ✅ Complete     |
| Bridge           | Hardware abstraction layer        | 🔧 In Progress |
| Strategy         | Dispenser behaviour at runtime    | 🔧 In Progress |
| Decorator        | Optional hardware modules         | 🔧 In Progress |
| Proxy            | Secure inventory access           | 🔧 In Progress |
| Composite        | Nested product/bundle inventory   | 🔧 In Progress |
| Adapter          | Payment provider integration      | 🔧 In Progress |

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
    │   └── impl/      # Bridge implementations — dispenser types
    ├── strategy/      # Strategy pattern — dispensing algorithms
    ├── inventory/     # Composite + Proxy + JSON persistence
    │   └── composite/ # InventoryItem, Product, ProductBundle
    ├── payment/       # Adapter pattern — payment providers
    ├── commands/      # Command pattern — transactions
    └── monitoring/    # City monitoring system
```

---

## ▶️ How to Run

**Requirements:** Java 17+

```bash
# Clone the repo
git clone https://github.com/24Chessman/aura-retail-os.git
cd aura-retail-os

# Compile
javac -d out src/com/aura/retailos/**/*.java src/com/aura/retailos/Main.java

# Run simulation
java -cp out com.aura.retailos.Main
```

---

## 🎬 Simulation Scenarios (Subtask 2)

The current simulation demonstrates 3 fully implemented patterns across
11 scenes:

1. **FoodKiosk creation** — Abstract Factory builds kiosk with compatible components
2. **Purchase, Refund, Restock** — Command pattern with full transaction audit trail
3. **PharmacyKiosk and EmergencyKiosk** — Factory creates 3 different kiosk types
4. **Registry status** — Singleton tracks all kiosks and system mode

**Planned for Final Submission:**

1. **Adding a hardware module at runtime** — Attach RefrigerationDecorator to a FoodKiosk dynamically
2. **Integrating a new payment provider** — Plug in DigitalWalletAdapter without touching existing code
3. **Nested bundle availability** — EmergencyKit propagates unavailability when a nested item goes out of stock

---

## 📄 Documentation

- `docs/PRD.docx` — Full Product Requirements Document
- `docs/ClassDiagram.drawio` — Class diagram
- `docs/ArchitectureDiagram.drawio` — Subsystem architecture

---

## 📁 Data Persistence

Inventory, transaction history, and system config will be stored as JSON
files in the `data/` directory and loaded on startup via
`InventoryPersistence.java` (planned for final submission).

---

## 👨‍🏫 Guided by

Prof. Sourish Dasgupta & TAs | DA-IICT, Gandhinagar
