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
        dp = [0 for i in range(n + 1)]
        for i in range(1, n + 1):
            j, mi = 1, 0x3f3f3f3f
            while j * j <= i:
                mi = min(mi, dp[i - j * j])
                j += 1
            dp[i] = mi + 1
        return dp[n]
```

### **Java**

```java
class Solution {
    public int numSquares(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (ans.size() <= n) {
            int m = ans.size(), val = Integer.MAX_VALUE;
            for (int i = 1; i * i <= m; i++) {
                val = Math.min(val, ans.get(m - i * i) + 1);
            }
            ans.add(val);
        }
        return ans.get(n);
    }
}
```

### **Go**

```go
/*
 * @lc app=leetcode.cn id=279 lang=golang
 * 动态规划的思路，状态转移方程：dp[n] = min(dp[n-1*1]+1, dp[n-2*2]+1, ..., dp[n-k*k]+1), ( 0< k*k <=n )
 */
func numSquares(n int) int {
	if n <= 0 {
		return 0
	}
	dp := make([]int, n+1) // 多申请了一份整形，使代码更容易理解, dp[n] 就是 n 的完全平方数的求解
	for i := 1; i <= n; i++ {
		dp[i] = i // 初始值 dp[n] 的最大值的解，也是最容易求的解
		for j := 0; j*j <= i; j++ {
			dp[i] = minInt(dp[i-j*j]+1, dp[i])
		}
	}
	return dp[n]
}

func minInt(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
