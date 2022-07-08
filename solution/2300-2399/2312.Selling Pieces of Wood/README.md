# [2312. 卖木头块](https://leetcode.cn/problems/selling-pieces-of-wood)

[English Version](/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>m</code> 和&nbsp;<code>n</code>&nbsp;，分别表示一块矩形木块的高和宽。同时给你一个二维整数数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i] = [h<sub>i</sub>, w<sub>i</sub>, price<sub>i</sub>]</code>&nbsp;表示你可以以&nbsp;<code>price<sub>i</sub></code>&nbsp;元的价格卖一块高为&nbsp;<code>h<sub>i</sub></code>&nbsp;宽为&nbsp;<code>w<sub>i</sub></code>&nbsp;的矩形木块。</p>

<p>每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：</p>

<ul>
	<li>沿垂直方向按高度 <strong>完全</strong> 切割木块，或</li>
	<li>沿水平方向按宽度 <strong>完全</strong> 切割木块</li>
</ul>

<p>在将一块木块切成若干小木块后，你可以根据 <code>prices</code>&nbsp;卖木块。你可以卖多块同样尺寸的木块。你不需要将所有小木块都卖出去。你 <strong>不能</strong>&nbsp;旋转切好后木块的高和宽。</p>

<p>请你返回切割一块大小为<em>&nbsp;</em><code>m x n</code><em> </em>的木块后，能得到的&nbsp;<strong>最多</strong>&nbsp;钱数。</p>

<p>注意你可以切割木块任意次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/images/ex1.png" style="width: 239px; height: 150px;" /></p>

<pre>
<b>输入：</b>m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
<b>输出：</b>19
<b>解释：</b>上图展示了一个可行的方案。包括：
- 2 块 2 x 2 的小木块，售出 2 * 7 = 14 元。
- 1 块 2 x 1 的小木块，售出 1 * 3 = 3 元。
- 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
总共售出 14 + 3 + 2 = 19 元。
19 元是最多能得到的钱数。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/images/ex2new.png" style="width: 250px; height: 175px;" /></p>

<pre>
<b>输入：</b>m = 4, n = 6, prices = [[3,2,10],[1,4,2],[4,1,3]]
<b>输出：</b>32
<b>解释：</b>上图展示了一个可行的方案。包括：
- 3 块 3 x 2 的小木块，售出 3 * 10 = 30 元。
- 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
总共售出 30 + 2 = 32 元。
32 元是最多能得到的钱数。
注意我们不能旋转 1 x 4 的木块来得到 4 x 1 的木块。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>1 &lt;= prices.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>prices[i].length == 3</code></li>
	<li><code>1 &lt;= h<sub>i</sub> &lt;= m</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>所有&nbsp;<code>(h<sub>i</sub>, w<sub>i</sub>)</code> <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

**方法二：动态规划**

设 $dp[i][j]$ 表示对一块高为 $i$，宽为 $j$ 的木块切割后能得到的最多钱数。答案就是 $dp[m][n]$。

时间复杂度 $O(mn(m+n))$。

相似题目：[1444. 切披萨的方案数](/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sellingWood(self, m: int, n: int, prices: List[List[int]]) -> int:
        @cache
        def dfs(h, w):
            ans = d[h].get(w, 0)
            for i in range(1, h // 2 + 1):
                ans = max(ans, dfs(i, w) + dfs(h - i, w))
            for i in range(1, w // 2 + 1):
                ans = max(ans, dfs(h, i) + dfs(h, w - i))
            return ans

        d = defaultdict(dict)
        for h, w, p in prices:
            d[h][w] = p
        return dfs(m, n)
```

```python
class Solution:
    def sellingWood(self, m: int, n: int, prices: List[List[int]]) -> int:
        d = defaultdict(dict)
        for h, w, p in prices:
            d[h][w] = p
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = d[i].get(j, 0)
                for k in range(1, i):
                    dp[i][j] = max(dp[i][j], dp[k][j] + dp[i - k][j])
                for k in range(1, j):
                    dp[i][j] = max(dp[i][j], dp[i][k] + dp[i][j - k])
        return dp[-1][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private long[][] memo;
    private int[][] d;

    public long sellingWood(int m, int n, int[][] prices) {
        d = new int[m + 1][n + 1];
        memo = new long[m + 1][n + 1];
        for (long[] e : memo) {
            Arrays.fill(e, -1);
        }
        for (int[] p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        return dfs(m, n);
    }

    private long dfs(int m, int n) {
        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        long ans = d[m][n];
        for (int i = 1; i < m / 2 + 1; ++i) {
            ans = Math.max(ans, dfs(i, n) + dfs(m - i, n));
        }
        for (int i = 1; i < n / 2 + 1; ++i) {
            ans = Math.max(ans, dfs(m, i) + dfs(m, n - i));
        }
        memo[m][n] = ans;
        return ans;
    }
}
```

```java
class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        int[][] d = new int[m + 1][n + 1];
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = d[i][j];
                for (int k = 1; k < i; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k < j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long sellingWood(int m, int n, vector<vector<int>>& prices) {
        vector<vector<ll>> memo(m + 1, vector<ll>(n + 1, -1));
        vector<vector<int>> d(m + 1, vector<int>(n + 1));
        for (auto& p : prices) d[p[0]][p[1]] = p[2];
        return dfs(m, n, d, memo);
    }

    ll dfs(int m, int n, vector<vector<int>>& d, vector<vector<ll>>& memo) {
        if (memo[m][n] != -1) return memo[m][n];
        ll ans = d[m][n];
        for (int i = 1; i < m / 2 + 1; ++i) ans = max(ans, dfs(i, n, d, memo) + dfs(m - i, n, d, memo));
        for (int i = 1; i < n / 2 + 1; ++i) ans = max(ans, dfs(m, i, d, memo) + dfs(m, n - i, d, memo));
        memo[m][n] = ans;
        return ans;
    }
};
```

```cpp
class Solution {
public:
    long long sellingWood(int m, int n, vector<vector<int>>& prices) {
        vector<vector<int>> d(m + 1, vector<int>(n + 1));
        vector<vector<long long>> dp(m + 1, vector<long long>(n + 1));
        for (auto& p : prices) d[p[0]][p[1]] = p[2];
        for (int i = 1; i <= m; ++i)
        {
            for (int j = 1; j <= n; ++j)
            {
                dp[i][j] = d[i][j];
                for (int k = 1; k < i; ++k) dp[i][j] = max(dp[i][j], dp[k][j] + dp[i - k][j]);
                for (int k = 1; k < j; ++k) dp[i][j] = max(dp[i][j], dp[i][k] + dp[i][j - k]);
            }
        }
        return dp[m][n];
    }
};
```

### **Go**

```go
func sellingWood(m int, n int, prices [][]int) int64 {
	memo := make([][]int, m+1)
	d := make([][]int, m+1)
	for i := range memo {
		memo[i] = make([]int, n+1)
		d[i] = make([]int, n+1)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	var dfs func(int, int) int
	dfs = func(m, n int) int {
		if memo[m][n] != -1 {
			return memo[m][n]
		}
		ans := d[m][n]
		for i := 1; i < m/2+1; i++ {
			ans = max(ans, dfs(i, n)+dfs(m-i, n))
		}
		for i := 1; i < n/2+1; i++ {
			ans = max(ans, dfs(m, i)+dfs(m, n-i))
		}
		memo[m][n] = ans
		return ans
	}
	return int64(dfs(m, n))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func sellingWood(m int, n int, prices [][]int) int64 {
	d := make([][]int, m+1)
	dp := make([][]int, m+1)
	for i := range d {
		d[i] = make([]int, n+1)
		dp[i] = make([]int, n+1)
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			dp[i][j] = d[i][j]
			for k := 1; k < i; k++ {
				dp[i][j] = max(dp[i][j], dp[k][j]+dp[i-k][j])
			}
			for k := 1; k < j; k++ {
				dp[i][j] = max(dp[i][j], dp[i][k]+dp[i][j-k])
			}
		}
	}
	return int64(dp[m][n])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
