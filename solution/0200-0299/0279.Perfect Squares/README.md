# [279. 完全平方数](https://leetcode.cn/problems/perfect-squares)

[English Version](/solution/0200-0299/0279.Perfect%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，返回 <em>和为 <code>n</code> 的完全平方数的最少数量</em> 。</p>

<p><strong>完全平方数</strong> 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，<code>1</code>、<code>4</code>、<code>9</code> 和 <code>16</code> 都是完全平方数，而 <code>3</code> 和 <code>11</code> 不是。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>n = <code>12</code>
<strong>输出：</strong>3 
<strong>解释：</strong><code>12 = 4 + 4 + 4</code></pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = <code>13</code>
<strong>输出：</strong>2
<strong>解释：</strong><code>13 = 4 + 9</code></pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划，定义 `dp[i]` 表示和为 `i` 的完全平方数的最少数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
