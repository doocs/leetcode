# [1434. Number of Ways to Wear Different Hats to Each Other](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other)

[中文文档](/solution/1400-1499/1434.Number%20of%20Ways%20to%20Wear%20Different%20Hats%20to%20Each%20Other/README.md)

## Description

<p>There are <code>n</code> people and <code>40</code> types of hats labeled from <code>1</code> to <code>40</code>.</p>

<p>Given a 2D integer array <code>hats</code>, where <code>hats[i]</code> is a list of all hats preferred by the <code>i<sup>th</sup></code> person.</p>

<p>Return <em>the number of ways that the <code>n</code> people wear different hats to each other</em>.</p>

<p>Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> hats = [[3,4],[4,5],[5]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one way to choose hats given the conditions. 
First person choose hat 3, Second person choose hat 4 and last one hat 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> hats = [[3,5,1],[3,5]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 ways to choose hats:
(3,5), (5,3), (1,3) and (1,5)
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
<strong>Output:</strong> 24
<strong>Explanation:</strong> Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == hats.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= hats[i].length &lt;= 40</code></li>
	<li><code>1 &lt;= hats[i][j] &lt;= 40</code></li>
	<li><code>hats[i]</code> contains a list of <strong>unique</strong> integers.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberWays(self, hats: List[List[int]]) -> int:
        d = defaultdict(list)
        for i, h in enumerate(hats):
            for v in h:
                d[v].append(i)
        n = len(hats)
        mx = max(max(h) for h in hats)
        dp = [[0] * (1 << n) for _ in range(mx + 1)]
        dp[0][0] = 1
        mod = int(1e9) + 7
        for i in range(1, mx + 1):
            for mask in range(1 << n):
                dp[i][mask] = dp[i - 1][mask]
                for j in d[i]:
                    if (mask >> j) & 1:
                        dp[i][mask] += dp[i - 1][mask ^ (1 << j)]
                dp[i][mask] %= mod
        return dp[mx][(1 << n) - 1]
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberWays(List<List<Integer>> hats) {
        List<Integer>[] d = new List[41];
        for (int i = 0; i < d.length; ++i) {
            d[i] = new ArrayList<>();
        }
        int n = hats.size();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            for (int h : hats.get(i)) {
                d[h].add(i);
                mx = Math.max(mx, h);
            }
        }
        long[][] dp = new long[mx + 1][1 << n];
        dp[0][0] = 1;
        for (int i = 1; i < mx + 1; ++i) {
            for (int mask = 0; mask < 1 << n; ++mask) {
                dp[i][mask] = dp[i - 1][mask];
                for (int j : d[i]) {
                    if (((mask >> j) & 1) == 1) {
                        dp[i][mask] = (dp[i][mask] + dp[i - 1][mask ^ (1 << j)]) % MOD;
                    }
                }
            }
        }
        return (int) dp[mx][(1 << n) - 1];
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    int numberWays(vector<vector<int>>& hats) {
        vector<vector<int>> d(41);
        int n = hats.size();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            for (int& h : hats[i]) {
                d[h].push_back(i);
                mx = max(mx, h);
            }
        }
        vector<vector<ll>> dp(mx + 1, vector<ll>(1 << n));
        dp[0][0] = 1;
        int mod = 1e9 + 7;
        for (int i = 1; i <= mx; ++i) {
            for (int mask = 0; mask < 1 << n; ++mask) {
                dp[i][mask] = dp[i - 1][mask];
                for (int& j : d[i]) {
                    if ((mask >> j) & 1) {
                        dp[i][mask] = (dp[i][mask] + dp[i - 1][mask ^ (1 << j)]) % mod;
                    }
                }
            }
        }
        return dp[mx][(1 << n) - 1];
    }
};
```

### **Go**

```go
func numberWays(hats [][]int) int {
	d := make([][]int, 41)
	mx := 0
	for i, h := range hats {
		for _, v := range h {
			d[v] = append(d[v], i)
			mx = max(mx, v)
		}
	}
	dp := make([][]int, mx+1)
	n := len(hats)
	for i := range dp {
		dp[i] = make([]int, 1<<n)
	}
	dp[0][0] = 1
	mod := int(1e9) + 7
	for i := 1; i <= mx; i++ {
		for mask := 0; mask < 1<<n; mask++ {
			dp[i][mask] = dp[i-1][mask]
			for _, j := range d[i] {
				if ((mask >> j) & 1) == 1 {
					dp[i][mask] = (dp[i][mask] + dp[i-1][mask^(1<<j)]) % mod
				}
			}
		}
	}
	return dp[mx][(1<<n)-1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
