# NulX Scanner: Comparative Network Reconnaissance Tool

### 🛡️ Research Artifact for "Comparative Analysis of Sequential vs. Concurrent Network Scanning"

**NulX Scanner** is a custom-built Java network enumeration tool designed to demonstrate the efficiency gap between blocking I/O (Sequential) and non-blocking multi-threaded architectures (Concurrent).

This repository serves as the **Proof of Concept (PoC)** for the academic research paper comparing network latency handling in Java.

---

## 📊 Experimental Results

| Scan Mode | Threads | Time (1000 Ports) | Performance |
| :--- | :--- | :--- | :--- |
| **Sequential** | 1 | ~36,617 ms | Baseline |
| **Concurrent** | 50 | ~1,100 ms | **33x Faster** |

> *Benchmarks run against `scanme.nmap.org` on Pop!_OS Linux.*

---

## 🚀 How to Run

### Prerequisites
* Java Development Kit (JDK) 8 or higher.

### Compilation
```bash
javac src/*.java -d bin
