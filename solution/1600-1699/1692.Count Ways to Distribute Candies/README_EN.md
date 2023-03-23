# [1692. Count Ways to Distribute Candies](https://leetcode.com/problems/count-ways-to-distribute-candies)

[中文文档](/solution/1600-1699/1692.Count%20Ways%20to%20Distribute%20Candies/README.md)

## Description

<p>There are <code>n</code> <strong>unique</strong> candies (labeled <code>1</code> through <code>n</code>) and <code>k</code> bags. You are asked to distribute <strong>all</strong> the candies into the bags such that every bag has <strong>at least</strong> one candy.</p>

<p>There can be multiple ways to distribute the candies. Two ways are considered <strong>different</strong> if the candies in one bag in the first way are not all in the same bag in the second way. The order of the bags and the order of the candies within each bag do not matter.</p>

<p>For example, <code>(1), (2,3)</code> and <code>(2), (1,3)</code> are considered different because candies <code>2</code> and <code>3</code> in the bag <code>(2,3)</code> in the first way are not in the same bag in the second way (they are split between the bags <code>(<u>2</u>)</code> and <code>(1,<u>3</u>)</code>). However, <code>(1), (2,3)</code> and <code>(3,2), (1)</code> are considered the same because the candies in each bag are all in the same bags in both ways.</p>

<p>Given two integers, <code>n</code> and <code>k</code>, return <em>the <strong>number</strong> of different ways to distribute the candies</em>. As the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1692.Count%20Ways%20to%20Distribute%20Candies/images/candies-1.png" style="height: 248px; width: 600px;" /></p>

<pre>
<strong>Input:</strong> n = 3, k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can distribute 3 candies into 2 bags in 3 ways:
(1), (2,3)
(1,2), (3)
(1,3), (2)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, k = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> You can distribute 4 candies into 2 bags in 7 ways:
(1), (2,3,4)
(1,2), (3,4)
(1,3), (2,4)
(1,4), (2,3)
(1,2,3), (4)
(1,2,4), (3)
(1,3,4), (2)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 20, k = 5
<strong>Output:</strong> 206085257
<strong>Explanation:</strong> You can distribute 20 candies into 5 bags in 1881780996 ways. 1881780996 modulo 10<sup>9</sup> + 7 = 206085257.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def waysToDistribute(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                f[i][j] = (f[i - 1][j] * j + f[i - 1][j - 1]) % mod
        return f[n][k]
```

### **Java**

```java
class Solution {
    public int waysToDistribute(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j] = (int) ((long) f[i - 1][j] * j % mod + f[i - 1][j - 1]) % mod;
            }
        }
        return f[n][k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToDistribute(int n, int k) {
        const int mod = 1e9 + 7;
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (1LL * f[i - 1][j] * j + f[i - 1][j - 1]) % mod;
            }
        }
        return f[n][k];
    }
};
```

### **Go**

```go
func waysToDistribute(n int, k int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	f[0][0] = 1
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			f[i][j] = (f[i-1][j]*j + f[i-1][j-1]) % mod
		}
	}
	return f[n][k]
}
```

### **...**

```

```

<!-- tabs:end -->
