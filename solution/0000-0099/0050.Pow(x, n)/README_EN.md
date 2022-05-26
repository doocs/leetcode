# [50. Pow(x, n)](https://leetcode.com/problems/powx-n)

[中文文档](/solution/0000-0099/0050.Pow%28x%2C%20n%29/README.md)

## Description

<p>Implement <a href="http://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(x, n)</a>, which calculates <code>x</code> raised to the power <code>n</code> (i.e., <code>x<sup>n</sup></code>).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 2.00000, n = 10
<strong>Output:</strong> 1024.00000
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 2.10000, n = 3
<strong>Output:</strong> 9.26100
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> x = 2.00000, n = -2
<strong>Output:</strong> 0.25000
<strong>Explanation:</strong> 2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-100.0 &lt; x &lt; 100.0</code></li>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        if n < 0:
            return 1 / self.myPow(x, -n)
        y = self.myPow(x, n >> 1)
        return y * y if (n & 1) == 0 else y * y * x
```

### **Java**

```java
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? pow(x, N) : 1.0 / pow(x, -N);
    }

    public double pow(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = pow(x, N >> 1);
        return (N & 1) == 0 ? y * y : y * y * x;
    }
}
```

### **TypeScript**

```ts
function myPow(x: number, n: number): number {
    let res = 1;
    if (n < 0) {
        n = -n;
        x = 1 / x;
    }
    for (let i = n; i != 0; i = Math.floor(i / 2)) {
        if ((i & 1) == 1) {
            res *= x;
        }
        x *= x;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
