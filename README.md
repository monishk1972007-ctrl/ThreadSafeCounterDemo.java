# Thread-Safe Counter

## Day 27 - Java Programming Internship

### Objective
To understand race conditions and synchronization in Java.

### Concepts Used
- Java Threads
- Race Condition
- synchronized
- AtomicInteger
- Thread-safe programming

### Description
This project demonstrates the difference between an unsafe counter
and thread-safe counters using synchronized and AtomicInteger.

### Expected Result
Five threads increment the counter 10,000 times each.

Expected count:

50000

The unsafe counter may produce an incorrect result because of race
conditions.

The synchronized and AtomicInteger counters produce the correct result.

### Technologies
- Java
- Eclipse
- GitHub
