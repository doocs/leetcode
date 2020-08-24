# [359. Logger Rate Limiter](https://leetcode.com/problems/logger-rate-limiter)

[中文文档](/solution/0300-0399/0359.Logger%20Rate%20Limiter/README.md)

## Description
<p>Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is <b>not printed in the last 10 seconds</b>.</p>

<p>Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.</p>

<p>It is possible that several messages arrive roughly at the same time.</p>

<p><b>Example:</b></p>

<pre>
Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
</pre>


## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->