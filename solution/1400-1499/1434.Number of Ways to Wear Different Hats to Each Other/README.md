# [1434. 每个人戴不同帽子的方案数](https://leetcode.cn/problems/number-of-ways-to-wear-different-hats-to-each-other)

[English Version](/solution/1400-1499/1434.Number%20of%20Ways%20to%20Wear%20Different%20Hats%20to%20Each%20Other/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>总共有 <code>n</code>&nbsp;个人和 <code>40</code> 种不同的帽子，帽子编号从 <code>1</code> 到 <code>40</code> 。</p>

<p>给你一个整数列表的列表&nbsp;<code>hats</code>&nbsp;，其中&nbsp;<code>hats[i]</code>&nbsp;是第 <code>i</code>&nbsp;个人所有喜欢帽子的列表。</p>

<p>请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数。</p>

<p>由于答案可能很大，请返回它对&nbsp;<code>10^9 + 7</code>&nbsp;取余后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>hats = [[3,4],[4,5],[5]]
<strong>输出：</strong>1
<strong>解释：</strong>给定条件下只有一种方法选择帽子。
第一个人选择帽子 3，第二个人选择帽子 4，最后一个人选择帽子 5。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>hats = [[3,5,1],[3,5]]
<strong>输出：</strong>4
<strong>解释：</strong>总共有 4 种安排帽子的方法：
(3,5)，(5,3)，(1,3) 和 (1,5)
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
<strong>输出：</strong>24
<strong>解释：</strong>每个人都可以从编号为 1 到 4 的帽子中选。
(1,2,3,4) 4 个帽子的排列方案数为 24 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
<strong>输出：</strong>111
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == hats.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= hats[i].length &lt;= 40</code></li>
	<li><code>1 &lt;= hats[i][j] &lt;= 40</code></li>
	<li><code>hats[i]</code>&nbsp;包含一个数字互不相同的整数列表。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 DP**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberWays(List<List<Integer>> hats) {
        List<Integer>[] d = new List[41];
        Arrays.setAll(d, k -> new ArrayList<>());
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
