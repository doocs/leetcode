# [629. K 个逆序对数组](https://leetcode.cn/problems/k-inverse-pairs-array)

[English Version](/solution/0600-0699/0629.K%20Inverse%20Pairs%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出两个整数&nbsp;<code>n</code>&nbsp;和&nbsp;<code>k</code>，找出所有包含从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;的数字，且恰好拥有&nbsp;<code>k</code>&nbsp;个逆序对的不同的数组的个数。</p>

<p>逆序对的定义如下：对于数组的第<code>i</code>个和第&nbsp;<code>j</code>个元素，如果满<code>i</code>&nbsp;&lt;&nbsp;<code>j</code>且&nbsp;<code>a[i]</code>&nbsp;&gt;&nbsp;<code>a[j]</code>，则其为一个逆序对；否则不是。</p>

<p>由于答案可能很大，只需要返回 答案 mod 10<sup>9</sup>&nbsp;+ 7 的值。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 3, k = 0
<strong>输出:</strong> 1
<strong>解释:</strong> 
只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 3, k = 1
<strong>输出:</strong> 2
<strong>解释:</strong> 
数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>&nbsp;<code>n</code>&nbsp;的范围是 [1, 1000] 并且 <code>k</code> 的范围是 [0, 1000]。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划，我们规定 `dp[i][j]` 表示 `i` 个数字恰好拥有 `j` 个逆序对的不同数组的个数，最终答案为 `dp[n][k]`

思考如何得到 `dp[i][j]`，假设 `i - 1` 个数字已经确定，现在插入 `i` 一共有 `i` 种情况：

-   放在第一个，由于 `i` 比之前的任何数都大，所以会产生 `i - 1` 个逆序对，为了凑够 `j` 个逆序对，之前确定的数需要产生 `j - (i - 1)` 个逆序对
-   放在第二个，产生 `i - 2` 个逆序对，为了凑够 `j` 个逆序对，之前确定的数需要产生 `j - (i - 2)` 个逆序对
-   放在第三个......同理
-   放在最后一个，产生 `0` 个逆序对，之前确认的数需要产生 `j` 个逆序对

可得状态转移公式：`dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]`

看到这种累加，很容易想到需要用前缀和进行优化。最终 `dp[i][]` 只依赖前缀和数组，甚至连 `dp[i - 1][]` 都不需要，所以可以进一步用一维数组优化空间

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        mod = 1000000007
        dp, pre = [0] * (k + 1), [0] * (k + 2)
        for i in range(1, n + 1):
            dp[0] = 1

            # dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for j in range(1, k + 1):
                dp[j] = (pre[j + 1] - pre[max(0, j - i + 1)] + mod) % mod

            for j in range(1, k + 2):
                pre[j] = (pre[j - 1] + dp[j - 1]) % mod

        return dp[k]
```

`dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - (i - 1)]` ①

`dp[i][j - 1] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - (i - 1)] + dp[i - 1][j - i]` ②

① - ②，得 `dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - i]`

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        N, MOD = 1010, int(1e9) + 7
        dp = [[0] * N for _ in range(N)]
        dp[1][0] = 1
        for i in range(2, n + 1):
            dp[i][0] = 1
            for j in range(1, k + 1):
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                if j >= i:
                    dp[i][j] -= dp[i - 1][j - i]
                dp[i][j] %= MOD
        return dp[n][k]
```

空间优化：

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        N, MOD = 1010, int(1e9) + 7
        dp = [0] * N
        dp[0] = 1
        for i in range(2, n + 1):
            t = dp.copy()
            for j in range(1, k + 1):
                dp[j] = t[j] + dp[j - 1]
                if j >= i:
                    dp[j] -= t[j - i]
                dp[j] %= MOD
        return dp[k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    private static final int MOD = 1000000007;

    public int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        int[] pre = new int[k + 2];
        for (int i = 1; i <= n; i++) {
            dp[0] = 1;

            // dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for (int j = 1; j <= k; j++) {
                dp[j] = (pre[j + 1] - pre[Math.max(0, j - i + 1)] + MOD) % MOD;
            }

            for (int j = 1; j <= k + 1; j++) {
                pre[j] = (pre[j - 1] + dp[j - 1]) % MOD;
            }
        }
        return dp[k];
    }
}
```

```java
class Solution {
    public int kInversePairs(int n, int k) {
        int N = 1010, MOD = (int) (1e9 + 7);
        int[][] dp = new int[N][N];
        dp[1][0] = 1;
        for (int i = 2; i < n + 1; ++i) {
            dp[i][0] = 1;
            for (int j = 1; j < k + 1; ++j) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + MOD) % MOD;
                }
            }
        }
        return dp[n][k];
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7

func kInversePairs(n int, k int) int {
	dp := make([]int, k+1)
	pre := make([]int, k+2)
	for i := 1; i <= n; i++ {
		dp[0] = 1

		// dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
		for j := 1; j <= k; j++ {
			dp[j] = (pre[j+1] - pre[max(0, j-i+1)] + mod) % mod
		}

		for j := 1; j <= k+1; j++ {
			pre[j] = (pre[j-1] + dp[j-1]) % mod
		}
	}
	return dp[k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C++**

```cpp
class Solution {
private:
    static constexpr int MOD = 1e9 + 7;

public:
    int kInversePairs(int n, int k) {
        vector<int> dp(k + 1), pre(k + 2, 0);
        for (int i = 1; i <= n; ++i) {
            dp[0] = 1;

            // dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for (int j = 1; j <= k; ++j) {
                dp[j] = (pre[j + 1] - pre[max(0, j - i + 1)] + MOD) % MOD;
            }

            for (int j = 1; j <= k + 1; ++j) {
                pre[j] = (pre[j - 1] + dp[j - 1]) % MOD;
            }
        }
        return dp[k];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
