# [1977. 划分数字的方案数](https://leetcode.cn/problems/number-of-ways-to-separate-numbers)

[English Version](/solution/1900-1999/1977.Number%20of%20Ways%20to%20Separate%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你写下了若干 <strong>正整数</strong>&nbsp;，并将它们连接成了一个字符串&nbsp;<code>num</code>&nbsp;。但是你忘记给这些数字之间加逗号了。你只记得这一列数字是 <strong>非递减</strong>&nbsp;的且&nbsp;<strong>没有</strong> 任何数字有前导 0 。</p>

<p>请你返回有多少种可能的 <strong>正整数数组</strong>&nbsp;可以得到字符串&nbsp;<code>num</code>&nbsp;。由于答案可能很大，将结果对 <code>10<sup>9</sup> + 7</code>&nbsp;<b>取余</b>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>num = "327"
<b>输出：</b>2
<b>解释：</b>以下为可能的方案：
3, 27
327
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>num = "094"
<b>输出：</b>0
<b>解释：</b>不能有数字有前导 0 ，且所有数字均为正数。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>num = "0"
<b>输出：</b>0
<strong>解释：</strong>不能有数字有前导 0 ，且所有数字均为正数。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>num = "9999999999999"
<b>输出：</b>101
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 3500</code></li>
	<li><code>num</code>&nbsp;只含有数字&nbsp;<code>'0'</code> 到&nbsp;<code>'9'</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 前缀和**

定义 $dp[i][j]$ 表示字符串 `num` 的前 $i$ 个字符，且最后一个数字的长度为 $j$ 时的方案数。显然答案为 $\sum_{j=0}^{n} dp[n][j]$。初始值 $dp[0][0] = 1$。

对于 $dp[i][j]$，对应的上一个数的结尾应该是 $i-j$，我们可以枚举 $dp[i-j][k]$，其中 $k\le j$。对于 $k \lt j$ 的部分，即长度小于 $j$ 的方案数可以直接加给 $dp[i][j]$，即 $dp[i][j] = \sum_{k=0}^{j-1} dp[i-j][k]$。因为前一个数字更短，也就意味着它比当前数更小。这里可以用前缀和优化。

但是当 $k=j$ 时，我们需要判断同样长度的两个数字的大小关系。如果前一个数字比当前数字大，那么这种情况是不合法的，我们不应该将其加给 $dp[i][j]$。否则，我们可以将其加给 $dp[i][j]$。这里我们可以先用 $O(n^2)$ 的时间预处理得到“最长公共前缀”，然后用 $O(1)$ 的时间判断两个同样长度的数字的大小关系。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 `num` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfCombinations(self, num: str) -> int:
        def cmp(i, j, k):
            x = lcp[i][j]
            return x >= k or num[i + x] >= num[j + x]

        mod = 10**9 + 7
        n = len(num)
        lcp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if num[i] == num[j]:
                    lcp[i][j] = 1 + lcp[i + 1][j + 1]

        dp = [[0] * (n + 1) for _ in range(n + 1)]
        dp[0][0] = 1
        for i in range(1, n + 1):
            for j in range(1, i + 1):
                v = 0
                if num[i - j] != '0':
                    if i - j - j >= 0 and cmp(i - j, i - j - j, j):
                        v = dp[i - j][j]
                    else:
                        v = dp[i - j][min(j - 1, i - j)]
                dp[i][j] = (dp[i][j - 1] + v) % mod
        return dp[n][n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfCombinations(String num) {
        int n = num.length();
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (num.charAt(i) == num.charAt(j)) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                int v = 0;
                if (num.charAt(i - j) != '0') {
                    if (i - j - j >= 0) {
                        int x = lcp[i - j][i - j - j];
                        if (x >= j || num.charAt(i - j + x) >= num.charAt(i - j - j + x)) {
                            v = dp[i - j][j];
                        }
                    }
                    if (v == 0) {
                        v = dp[i - j][Math.min(j - 1, i - j)];
                    }
                }
                dp[i][j] = (dp[i][j - 1] + v) % MOD;
            }
        }
        return dp[n][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfCombinations(string num) {
        int n = num.size();
        vector<vector<int>> lcp(n + 1, vector<int>(n + 1));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (num[i] == num[j]) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        auto cmp = [&](int i, int j, int k) {
            int x = lcp[i][j];
            return x >= k || num[i + x] >= num[j + x];
        };
        vector<vector<int>> dp(n + 1, vector<int>(n + 1));
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                int v = 0;
                if (num[i - j] != '0') {
                    if (i - j - j >= 0 && cmp(i - j, i - j - j, j)) {
                        v = dp[i - j][j];
                    } else {
                        v = dp[i - j][min(j - 1, i - j)];
                    }
                }
                dp[i][j] = (dp[i][j - 1] + v) % mod;
            }
        }
        return dp[n][n];
    }
};
```

### **Go**

```go
func numberOfCombinations(num string) int {
	n := len(num)
	lcp := make([][]int, n+1)
	dp := make([][]int, n+1)
	for i := range lcp {
		lcp[i] = make([]int, n+1)
		dp[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if num[i] == num[j] {
				lcp[i][j] = 1 + lcp[i+1][j+1]
			}
		}
	}
	cmp := func(i, j, k int) bool {
		x := lcp[i][j]
		return x >= k || num[i+x] >= num[j+x]
	}
	dp[0][0] = 1
	var mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= i; j++ {
			v := 0
			if num[i-j] != '0' {
				if i-j-j >= 0 && cmp(i-j, i-j-j, j) {
					v = dp[i-j][j]
				} else {
					v = dp[i-j][min(j-1, i-j)]
				}
			}
			dp[i][j] = (dp[i][j-1] + v) % mod
		}
	}
	return dp[n][n]
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
