# [2312. Selling Pieces of Wood](https://leetcode.com/problems/selling-pieces-of-wood)

[中文文档](/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/README.md)

## Description

<p>You are given two integers <code>m</code> and <code>n</code> that represent the height and width of a rectangular piece of wood. You are also given a 2D integer array <code>prices</code>, where <code>prices[i] = [h<sub>i</sub>, w<sub>i</sub>, price<sub>i</sub>]</code> indicates you can sell a rectangular piece of wood of height <code>h<sub>i</sub></code> and width <code>w<sub>i</sub></code> for <code>price<sub>i</sub></code> dollars.</p>

<p>To cut a piece of wood, you must make a vertical or horizontal cut across the <strong>entire</strong> height or width of the piece to split it into two smaller pieces. After cutting a piece of wood into some number of smaller pieces, you can sell pieces according to <code>prices</code>. You may sell multiple pieces of the same shape, and you do not have to sell all the shapes. The grain of the wood makes a difference, so you <strong>cannot</strong> rotate a piece to swap its height and width.</p>

<p>Return <em>the <strong>maximum</strong> money you can earn after cutting an </em><code>m x n</code><em> piece of wood</em>.</p>

<p>Note that you can cut the piece of wood as many times as you want.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/images/ex1.png" style="width: 239px; height: 150px;" />
<pre>
<strong>Input:</strong> m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
<strong>Output:</strong> 19
<strong>Explanation:</strong> The diagram above shows a possible scenario. It consists of:
- 2 pieces of wood shaped 2 x 2, selling for a price of 2 * 7 = 14.
- 1 piece of wood shaped 2 x 1, selling for a price of 1 * 3 = 3.
- 1 piece of wood shaped 1 x 4, selling for a price of 1 * 2 = 2.
This obtains a total of 14 + 3 + 2 = 19 money earned.
It can be shown that 19 is the maximum amount of money that can be earned.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/images/ex2new.png" style="width: 250px; height: 175px;" />
<pre>
<strong>Input:</strong> m = 4, n = 6, prices = [[3,2,10],[1,4,2],[4,1,3]]
<strong>Output:</strong> 32
<strong>Explanation:</strong> The diagram above shows a possible scenario. It consists of:
- 3 pieces of wood shaped 3 x 2, selling for a price of 3 * 10 = 30.
- 1 piece of wood shaped 1 x 4, selling for a price of 1 * 2 = 2.
This obtains a total of 30 + 2 = 32 money earned.
It can be shown that 32 is the maximum amount of money that can be earned.
Notice that we cannot rotate the 1 x 4 piece of wood to obtain a 4 x 1 piece of wood.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>1 &lt;= prices.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>prices[i].length == 3</code></li>
	<li><code>1 &lt;= h<sub>i</sub> &lt;= m</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>All the shapes of wood <code>(h<sub>i</sub>, w<sub>i</sub>)</code> are pairwise <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
