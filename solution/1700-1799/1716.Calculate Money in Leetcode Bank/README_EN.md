# [1716. Calculate Money in Leetcode Bank](https://leetcode.com/problems/calculate-money-in-leetcode-bank)

[中文文档](/solution/1700-1799/1716.Calculate%20Money%20in%20Leetcode%20Bank/README.md)

## Description

<p>Hercy wants to save money for his first car. He puts money in the Leetcode&nbsp;bank <strong>every day</strong>.</p>

<p>He starts by putting in <code>$1</code> on Monday, the first day. Every day from Tuesday to Sunday, he will put in <code>$1</code> more than the day before. On every subsequent Monday, he will put in <code>$1</code> more than the <strong>previous Monday</strong>.<span style="display: none;"> </span></p>

<p>Given <code>n</code>, return <em>the total amount of money he will have in the Leetcode bank at the end of the </em><code>n<sup>th</sup></code><em> day.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 10
<strong>Explanation:</strong>&nbsp;After the 4<sup>th</sup> day, the total is 1 + 2 + 3 + 4 = 10.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 37
<strong>Explanation:</strong>&nbsp;After the 10<sup>th</sup> day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2<sup>nd</sup> Monday, Hercy only puts in $2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 20
<strong>Output:</strong> 96
<strong>Explanation:</strong>&nbsp;After the 20<sup>th</sup> day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def totalMoney(self, n: int) -> int:
        a, b = divmod(n, 7)
        return (28 + 28 + 7 * (a - 1)) * a // 2 + (a * 2 + b + 1) * b // 2
```

### **Java**

```java
class Solution {
    public int totalMoney(int n) {
        int a = n / 7, b = n % 7;
        return (28 + 28 + 7 * (a - 1)) * a / 2 + (a * 2 + b + 1) * b / 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int totalMoney(int n) {
        int a = n / 7, b = n % 7;
        return (28 + 28 + 7 * (a - 1)) * a / 2 + (a * 2 + b + 1) * b / 2;
    }
};
```

### **Go**

```go
func totalMoney(n int) int {
	a, b := n/7, n%7
	return (28+28+7*(a-1))*a/2 + (a*2+b+1)*b/2
}
```

### **...**

```

```

<!-- tabs:end -->
