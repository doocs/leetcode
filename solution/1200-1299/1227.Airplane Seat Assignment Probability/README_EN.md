# [1227. Airplane Seat Assignment Probability](https://leetcode.com/problems/airplane-seat-assignment-probability)

[中文文档](/solution/1200-1299/1227.Airplane%20Seat%20Assignment%20Probability/README.md)

## Description

<p><code>n</code> passengers board an airplane with exactly <code>n</code> seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of the passengers will:</p>

<ul>
	<li>Take their own seat if it is still available, and</li>
	<li>Pick other seats randomly when they find their seat occupied</li>
</ul>

<p>Return <em>the probability that the </em><code>n<sup>th</sup></code><em> person gets his own seat</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1.00000
<strong>Explanation: </strong>The first person can only get the first seat.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 0.50000
<strong>Explanation: </strong>The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nthPersonGetsNthSeat(self, n: int) -> float:
        return 1 if n == 1 else 0.5
```

### **Java**

```java
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : .5;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : .5;
    }
};
```

### **Go**

```go
func nthPersonGetsNthSeat(n int) float64 {
	if n == 1 {
		return 1
	}
	return .5
}
```

### **...**

```

```

<!-- tabs:end -->
