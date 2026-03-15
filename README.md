# NulX Port Scanner

> A comparative network reconnaissance tool built to understand the real performance gap between sequential and concurrent TCP scanning in Java.

Built by [nulx0x00](https://github.com/nulx0x00) — B.Tech Cybersecurity, Arya College (RTU)

---

## What this is it?

NulX is a multithreaded TCP port scanner written from scratch in Java. It supports two scanning modes — sequential and concurrent — with configurable thread count, port range, and connection timeout. The project originated as a research experiment to measure and understand the performance difference between blocking I/O and parallel execution in network scanning.

This is not a tutorial clone. The architecture, benchmarking methodology, and results are original work.

---

## Features

- Sequential scan mode — single-threaded, blocking I/O baseline
- Concurrent scan mode — configurable thread pool via `ExecutorService`
- Configurable port range, timeout, and thread count at runtime
- Open port summary on both modes — total count and list of open ports
- Thread-safe result collection using `ConcurrentLinkedQueue`
- Resource-safe socket handling using try-with-resources
- Clean exception handling — no stack traces printed to user

---

## Architecture

```
src/
├── Main.java              — entry point, user input, timing
├── SequentialScanner.java — single-threaded scan with ArrayList summary
├── ConcurrentScanner.java — thread pool scan with ConcurrentLinkedQueue summary
└── ScanTask.java          — Runnable unit: one port, one TCP attempt
```

**Design decision:** `ScanTask implements Runnable` keeps the unit of work isolated. `ConcurrentLinkedQueue` handles thread-safe result collection without explicit synchronization. Timeout is configurable and enforced per-port via `InetSocketAddress.connect()`.

---

## Benchmark Results

| Scan mode | Threads | Ports scanned | Time elapsed | vs baseline |
|-----------|---------|---------------|--------------|-------------|
| Sequential | 1 | 1000 | ~36,617 ms | baseline |
| Concurrent | 50 | 1000 | ~1,100 ms | **33.3x faster** |

**Test conditions:** `scanme.nmap.org` · Pop!_OS Linux · 1000ms timeout per port · 1–1000 port range

**Why 33x and not 50x with 50 threads?** Thread creation overhead, DNS resolution on first connect, JVM warmup, and network latency floor all reduce theoretical linear scaling. 33x on a real network target is consistent with expected results.

---

## How to run

**Prerequisites:** JDK 8 or higher

**Compile:**
```bash
javac src/*.java -d bin
```

**Run:**
```bash
java -cp bin Main
```

**Example session:**
```
****** // NULX PORT SCANNER // ******
ENTER URL (e.g., scanme.nmap.org): scanme.nmap.org
Start Port: 1
End Port: 1024
Timeout (ms): 1000

Select Mode:
1. Sequential (Slow)
2. Concurrent (Fast)
Choice: 2
Threads: 50

[*] Starting Concurrent Scan (50 Threads)...
[+] Port 22 OPEN
[+] Port 80 OPEN
Total open ports: 2 {22, 80, }
--------------------------------
Total Time: 1143 ms
--------------------------------
```

---

## Legal

This tool is built for educational purposes and authorized testing only. Only scan hosts you own or have explicit written permission to test. Unauthorized port scanning may be illegal in your jurisdiction.

---

## License

MIT — see [LICENSE](LICENSE)