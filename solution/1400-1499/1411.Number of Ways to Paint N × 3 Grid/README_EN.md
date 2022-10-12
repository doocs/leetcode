# [1411. Number of Ways to Paint N × 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid)

[中文文档](/solution/1400-1499/1411.Number%20of%20Ways%20to%20Paint%20N%20%C3%97%203%20Grid/README.md)

## Description

<p>You have a <code>grid</code> of size <code>n x 3</code> and you want to paint each cell of the grid with exactly one of the three colors: <strong>Red</strong>, <strong>Yellow,</strong> or <strong>Green</strong> while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).</p>

<p>Given <code>n</code> the number of rows of the grid, return <em>the number of ways</em> you can paint this <code>grid</code>. As the answer may grow large, the answer <strong>must be</strong> computed modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1411.Number%20of%20Ways%20to%20Paint%20N%20%C3%97%203%20Grid/images/e1.png" style="width: 400px; height: 257px;" />
<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 12
<strong>Explanation:</strong> There are 12 possible way to paint the grid as shown.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5000
<strong>Output:</strong> 30228214
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numOfWays(self, n: int) -> int:
        mod = 10**9 + 7
        f0 = f1 = 6
        for _ in range(n - 1):
            g0 = (3 * f0 + 2 * f1) % mod
            g1 = (2 * f0 + 2 * f1) % mod
            f0, f1 = g0, g1
        return (f0 + f1) % mod
```

### **Java**

```java
class Solution {
    public int numOfWays(int n) {
        int mod = (int) 1e9 + 7;
        long f0 = 6, f1 = 6;
        for (int i = 0; i < n - 1; ++i) {
            long g0 = (3 * f0 + 2 * f1) % mod;
            long g1 = (2 * f0 + 2 * f1) % mod;
            f0 = g0;
            f1 = g1;
        }
        return (int) (f0 + f1) % mod;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    int numOfWays(int n) {
        int mod = 1e9 + 7;
        ll f0 = 6, f1 = 6;
        while (--n) {
            ll g0 = (f0 * 3 + f1 * 2) % mod;
            ll g1 = (f0 * 2 + f1 * 2) % mod;
            f0 = g0;
            f1 = g1;
        }
        return (int)(f0 + f1) % mod;
    }
};
```

### **Go**

```go
func numOfWays(n int) int {
	mod := int(1e9) + 7
	f0, f1 := 6, 6
	for n > 1 {
		n--
		g0 := (f0*3 + f1*2) % mod
		g1 := (f0*2 + f1*2) % mod
		f0, f1 = g0, g1
	}
	return (f0 + f1) % mod
}
```

### **...**

```

```

<!-- tabs:end -->
