# [279. Perfect Squares](https://leetcode.com/problems/perfect-squares)

[中文文档](/solution/0200-0299/0279.Perfect%20Squares/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>the least number of perfect square numbers that sum to</em> <code>n</code>.</p>

<p>A <strong>perfect square</strong> is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, <code>1</code>, <code>4</code>, <code>9</code>, and <code>16</code> are perfect squares while <code>3</code> and <code>11</code> are not.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 12
<strong>Output:</strong> 3
<strong>Explanation:</strong> 12 = 4 + 4 + 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 13
<strong>Output:</strong> 2
<strong>Explanation:</strong> 13 = 4 + 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

For dynamic programming, define `dp[i]` to represent the least number of perfect square numbers that sum to `i`.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSquares(self, n: int) -> int:
        dp = [0] * (n + 1)
        for i in range(1, n + 1):
            j, mi = 1, inf
            while j * j <= i:
                mi = min(mi, dp[i - j * j])
                j += 1
            dp[i] = mi + 1
        return dp[-1]
```

### **Java**

```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int mi = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; ++j) {
                mi = Math.min(mi, dp[i - j * j]);
            }
            dp[i] = mi + 1;
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSquares(int n) {
        vector<int> dp(n + 1);
        for (int i = 1; i <= n; ++i) {
            int mi = 100000;
            for (int j = 1; j * j <= i; ++j) {
                mi = min(mi, dp[i - j * j]);
            }
            dp[i] = mi + 1;
        }
        return dp[n];
    }
};
```

### **TypeScript**

```ts
function numSquares(n: number): number {
    let dp = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        let min = Infinity;
        for (let j = 1; j * j <= i; ++j) {
            min = Math.min(min, dp[i - j * j]);
        }
        dp[i] = min + 1;
    }
    return dp.pop();
}
```

### **Go**

```go
func numSquares(n int) int {
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		mi := 100000
		for j := 1; j*j <= i; j++ {
			mi = min(mi, dp[i-j*j])
		}
		dp[i] = mi + 1
	}
	return dp[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
