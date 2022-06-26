# [2318. 不同骰子序列的数目](https://leetcode.cn/problems/number-of-distinct-roll-sequences)

[English Version](/solution/2300-2399/2318.Number%20of%20Distinct%20Roll%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;。你需要掷一个 6 面的骰子&nbsp;<code>n</code>&nbsp;次。请你在满足以下要求的前提下，求出 <strong>不同</strong>&nbsp;骰子序列的数目：</p>

<ol>
	<li>序列中任意 <strong>相邻</strong>&nbsp;数字的 <strong>最大公约数</strong>&nbsp;为 <code>1</code>&nbsp;。</li>
	<li>序列中 <strong>相等</strong>&nbsp;的值之间，至少有 <code>2</code>&nbsp;个其他值的数字。正式地，如果第&nbsp;<code>i</code>&nbsp;次掷骰子的值 <strong>等于</strong>&nbsp;第&nbsp;<code>j</code>&nbsp;次的值，那么&nbsp;<code>abs(i - j) &gt; 2</code>&nbsp;。</li>
</ol>

<p>请你返回不同序列的 <strong>总数目</strong>&nbsp;。由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>如果两个序列中至少有一个元素不同，那么它们被视为不同的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 4
<b>输出：</b>184
<b>解释：</b>一些可行的序列为 (1, 2, 3, 4) ，(6, 1, 2, 3) ，(1, 2, 3, 1) 等等。
一些不可行的序列为 (1, 2, 1, 3) ，(1, 2, 3, 6) 。
(1, 2, 1, 3) 是不可行的，因为第一个和第三个骰子值相等且 abs(1 - 3) = 2 （下标从 1 开始表示）。
(1, 2, 3, 6) i是不可行的，因为 3 和 6 的最大公约数是 3 。
总共有 184 个不同的可行序列，所以我们返回 184 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 2
<b>输出：</b>22
<b>解释：</b>一些可行的序列为 (1, 2) ，(2, 1) ，(3, 2) 。
一些不可行的序列为 (3, 6) ，(2, 4) ，因为最大公约数不为 1 。
总共有 22 个不同的可行序列，所以我们返回 22 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

三维 DP。

设 $dp[k][i][j]$ 表示序列长度为 $k$，且序列的最后两个数字分别为 $i$, $j$ 的所有满足要求的不同序列的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distinctSequences(self, n: int) -> int:
        if n == 1:
            return 6
        mod = 10**9 + 7
        dp = [[[0] * 6 for _ in range(6)] for _ in range(n + 1)]
        for i in range(6):
            for j in range(6):
                if gcd(i + 1, j + 1) == 1 and i != j:
                    dp[2][i][j] = 1
        for k in range(3, n + 1):
            for i in range(6):
                for j in range(6):
                    if gcd(i + 1, j + 1) == 1 and i != j:
                        for h in range(6):
                            if gcd(h + 1, i + 1) == 1 and h != i and h != j:
                                dp[k][i][j] += dp[k - 1][h][i]
        ans = 0
        for i in range(6):
            for j in range(6):
                ans += dp[-1][i][j]
        return ans % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int distinctSequences(int n) {
        if (n == 1) {
            return 6;
        }
        int mod = (int) 1e9 + 7;
        int[][][] dp = new int[n + 1][6][6];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (gcd(i + 1, j + 1) == 1 && i != j) {
                    dp[2][i][j] = 1;
                }
            }
        }
        for (int k = 3; k <= n; ++k) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 6; ++j) {
                    if (gcd(i + 1, j + 1) == 1 && i != j) {
                        for (int h = 0; h < 6; ++h) {
                            if (gcd(h + 1, i + 1) == 1 && h != i && h != j) {
                                dp[k][i][j] = (dp[k][i][j] + dp[k - 1][h][i]) % mod;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                ans = (ans + dp[n][i][j]) % mod;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distinctSequences(int n) {
        if (n == 1) return 6;
        int mod = 1e9 + 7;
        vector<vector<vector<int>>> dp(n + 1, vector<vector<int>>(6, vector<int>(6)));
        for (int i = 0; i < 6; ++i)
            for (int j = 0; j < 6; ++j)
                if (gcd(i + 1, j + 1) == 1 && i != j)
                    dp[2][i][j] = 1;
        for (int k = 3; k <= n; ++k)
            for (int i = 0; i < 6; ++i)
                for (int j = 0; j < 6; ++j)
                    if (gcd(i + 1, j + 1) == 1 && i != j)
                        for (int h = 0; h < 6; ++h)
                            if (gcd(h + 1, i + 1) == 1 && h != i && h != j)
                                dp[k][i][j] = (dp[k][i][j] + dp[k - 1][h][i]) % mod;
        int ans = 0;
        for (int i = 0; i < 6; ++i)
            for (int j = 0; j < 6; ++j)
                ans = (ans + dp[n][i][j]) % mod;
        return ans;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

### **Go**

```go
func distinctSequences(n int) int {
	if n == 1 {
		return 6
	}
	dp := make([][][]int, n+1)
	for k := range dp {
		dp[k] = make([][]int, 6)
		for i := range dp[k] {
			dp[k][i] = make([]int, 6)
		}
	}
	for i := 0; i < 6; i++ {
		for j := 0; j < 6; j++ {
			if gcd(i+1, j+1) == 1 && i != j {
				dp[2][i][j] = 1
			}
		}
	}
	mod := int(1e9) + 7
	for k := 3; k <= n; k++ {
		for i := 0; i < 6; i++ {
			for j := 0; j < 6; j++ {
				if gcd(i+1, j+1) == 1 && i != j {
					for h := 0; h < 6; h++ {
						if gcd(h+1, i+1) == 1 && h != i && h != j {
							dp[k][i][j] = (dp[k][i][j] + dp[k-1][h][i]) % mod
						}
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < 6; i++ {
		for j := 0; j < 6; j++ {
			ans = (ans + dp[n][i][j]) % mod
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
