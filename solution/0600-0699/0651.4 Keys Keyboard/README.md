---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0651.4%20Keys%20Keyboard/README.md
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [651. 四个键的键盘 🔒](https://leetcode.cn/problems/4-keys-keyboard)

[English Version](/solution/0600-0699/0651.4%20Keys%20Keyboard/README_EN.md)

## 题目描述

<!-- description:start -->

<p>假设你有一个特殊的键盘包含下面的按键：</p>

<ul>
	<li><code>A</code>：在屏幕上打印一个 <code>'A'</code>。</li>
	<li><code>Ctrl-A</code>：选中整个屏幕。</li>
	<li><code>Ctrl-C</code>：复制选中区域到缓冲区。</li>
	<li><code>Ctrl-V</code>：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。</li>
</ul>

<p>现在，<em>你可以 <strong>最多</strong> 按键 <code>n</code>&nbsp;次（使用上述四种按键），返回屏幕上最多可以显示&nbsp;<code>'A'</code>&nbsp;的个数&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 3
<strong>输出:</strong> 3
<strong>解释:</strong> 
我们最多可以在屏幕上显示三个'A'通过如下顺序按键：
A, A, A
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 7
<strong>输出:</strong> 9
<strong>解释:</strong> 
我们最多可以在屏幕上显示九个'A'通过如下顺序按键：
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

定义 $dp[i]$ 表示前 $i$ 个按键可以显示的最大个数。

我们可以发现，要显示最多的 `A`，要么一直按 `A`，要么以 `Ctrl-V` 结束。

-   一直按 `A` 的情况，满足 $dp[i] = i$。
-   以 `Ctrl-V` 结束的情况，我们枚举对应的 `Ctrl-A` 的位置 $j$，可以得到 $dp[i]=max(dp[i], dp[j-1] \times (i - j))$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxA(self, n: int) -> int:
        dp = list(range(n + 1))
        for i in range(3, n + 1):
            for j in range(2, i - 1):
                dp[i] = max(dp[i], dp[j - 1] * (i - j))
        return dp[-1]
```

#### Java

```java
class Solution {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            dp[i] = i;
        }
        for (int i = 3; i < n + 1; ++i) {
            for (int j = 2; j < i - 1; ++j) {
                dp[i] = Math.max(dp[i], dp[j - 1] * (i - j));
            }
        }
        return dp[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxA(int n) {
        vector<int> dp(n + 1);
        iota(dp.begin(), dp.end(), 0);
        for (int i = 3; i < n + 1; ++i) {
            for (int j = 2; j < i - 1; ++j) {
                dp[i] = max(dp[i], dp[j - 1] * (i - j));
            }
        }
        return dp[n];
    }
};
```

#### Go

```go
func maxA(n int) int {
	dp := make([]int, n+1)
	for i := range dp {
		dp[i] = i
	}
	for i := 3; i < n+1; i++ {
		for j := 2; j < i-1; j++ {
			dp[i] = max(dp[i], dp[j-1]*(i-j))
		}
	}
	return dp[n]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
