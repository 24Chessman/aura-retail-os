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

| Name    | Roll Number | Responsibility                                      |
|---------|-------------|-----------------------------------------------------|
| Naitik  | 202512006   | Core, Singleton, Facade, Command, Decorator (base)  |
| Priya   | 202512003   | Abstract Factory, Adapter (Payment)                 |
| Gaurang | 202512007   | Bridge, Strategy, Decorator (Modules)               |
| Harshal | 202512013   | Proxy, Composite, Persistence                       |

---

## 🧩 Design Patterns Implemented

| Pattern          | Where Used                        | Status      |
|------------------|-----------------------------------|-------------|
| Singleton        | CentralRegistry                   | ✅ Complete |
| Abstract Factory | Kiosk creation (3 kiosk types)    | ✅ Complete |
| Command          | Transaction system                | ✅ Complete |
| Facade           | KioskInterface (external API)     | ✅ Complete |
| Bridge           | Hardware abstraction layer        | ✅ Complete |
| Strategy         | Dispenser behaviour at runtime    | ✅ Complete |
| Decorator        | Optional hardware modules         | ✅ Complete |
| Proxy            | Secure inventory access           | ✅ Complete |
| Composite        | Nested product/bundle inventory   | ✅ Complete |
| Adapter          | Payment provider integration      | ✅ Complete |

---

## 🗂️ Project Structure

```
src/
└── com/aura/retailos/
    ├── Main.java      # Simulation entry point — all 3 Path B scenarios
    ├── core/          # Singleton, Facade, Command
    ├── factory/       # Abstract Factory — 3 kiosk types
    ├── kiosk/         # BaseKiosk + Decorator modules
    ├── hardware/
    │   ├── bridge/    # Bridge pattern — DispenserAbstraction + DispenserImplementation
    │   └── impl/      # Bridge implementations — Conveyor, Robotic, Spiral
    ├── strategy/      # Strategy pattern — dispensing algorithms
    ├── inventory/     # Proxy + Persistence
    │   └── composite/ # Composite — InventoryItem, Product, ProductBundle
    ├── payment/       # Adapter pattern — PaymentProcessor interface
    │   └── providers/ # Third-party stubs — CreditCardGateway, DigitalWallet, UPISystem
    ├── commands/      # Command pattern — Purchase, Refund, Restock
    └── monitoring/    # Observer stub — CityMonitoringSystem
```

---

## ▶️ How to Run

**Requirements:** Java 17+

```bash
# Clone the repo
git clone https://github.com/24Chessman/aura-retail-os.git
cd aura-retail-os

# Compile (all sources)
javac -d out -sourcepath src src\com\aura\retailos\Main.java

# Run final simulation
java -cp out com.aura.retailos.Main
```

---

## 🎬 Final Simulation — 3 Path B Scenarios

All 10 patterns are demonstrated across the following 3 scenarios:

### Scenario 1 — Adding a Hardware Module at Runtime (Decorator)
- A `FoodKiosk` is created via **Abstract Factory**
- A `ConveyorDispenserImpl` is wired with `ConveyorDispensingStrategy` (**Bridge + Strategy**)
- `UPIAdapter` is injected as the payment provider (**Adapter**)
- Purchasing a cold item is blocked — kiosk has no refrigeration (**Proxy stock check**)
- `RefrigerationDecorator` then `SolarPowerDecorator` are attached at runtime — **Decorator**
- `getStatus()` now shows Temp and Solar readings; `getCapabilities()` shows 2 new modules
- Both purchases succeed; full transaction log via **Command + Facade**

### Scenario 2 — Integrating a New Payment Provider (Adapter)
- A `PharmacyKiosk` is created via **Abstract Factory**
- `CreditCardAdapter` and `UPIAdapter` process standalone payments (**Adapter**)
- `DigitalWalletAdapter` is plugged in as a new provider — **zero existing code changed**
- Paracetamol is purchased via **DigitalWallet** through **Facade → Proxy → Command**

### Scenario 3 — Nested Bundle Availability (Composite + Proxy)
- An `EmergencyKiosk` is created via **Abstract Factory**
- A nested bundle tree is built: `EmergencyKit → FirstAidKit → [Bandages, Antiseptic, Gauze]` — **Composite**
- Zeroing Antiseptic stock cascades `isAvailable() = false` up to the root bundle
- **Proxy** blocks all write operations when `CentralRegistry` enters `EMERGENCY` mode — **Singleton**
- Restocking restores availability; purchase completes through **Facade → Command**
- State persisted to `data/transactions.json` via **InventoryPersistence**

---

## 📁 Data Persistence

Transaction history is written to `data/transactions.json` after Scenario 3
via `KioskInterface.saveState()` → `InventoryPersistence.saveTransactions()`.

---

## 📄 Documentation

- `docs/Aura_Retail_OS_PRD.docx` — Full Product Requirements Document
- `docs/ClassDiagram.drawio` — Class diagram
- `docs/ArchitectureDiagram.drawio` — Subsystem architecture

---

## 👨‍🏫 Guided by

Prof. Sourish Dasgupta & TAs | DA-IICT, Gandhinagar
