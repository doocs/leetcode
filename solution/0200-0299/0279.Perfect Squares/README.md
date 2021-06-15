# [279. 完全平方数](https://leetcode-cn.com/problems/perfect-squares)

[English Version](/solution/0200-0299/0279.Perfect%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定正整数 <em>n</em>，找到若干个完全平方数（比如 <code>1, 4, 9, 16, ...</code>）使得它们的和等于<em> n</em>。你需要让组成和的完全平方数的个数最少。</p>

<p>给你一个整数 <code>n</code> ，返回和为 <code>n</code> 的完全平方数的 <strong>最少数量</strong> 。</p>

<p><strong>完全平方数</strong> 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，<code>1</code>、<code>4</code>、<code>9</code> 和 <code>16</code> 都是完全平方数，而 <code>3</code> 和 <code>11</code> 不是。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = <code>12</code>
<strong>输出：</strong>3
<strong>解释：</strong><code>12 = 4 + 4 + 4</code></pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = <code>13</code>
<strong>输出：</strong>2
<strong>解释：</strong><code>13 = 4 + 9</code></pre>
 

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 10<sup>4</sup></code></li>
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
};
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
