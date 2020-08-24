# [362. Design Hit Counter](https://leetcode.com/problems/design-hit-counter)

[中文文档](/solution/0300-0399/0362.Design%20Hit%20Counter/README.md)

## Description
<p>Design a hit counter which counts the number of hits received in the past 5 minutes.</p>

<p>Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.</p>

<p>It is possible that several hits arrive roughly at the same time.</p>

<p><b>Example:</b></p>

<pre>
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
</pre>

<p><b>Follow up:</b><br />
What if the number of hits per second could be very large? Does your design scale?</p>


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