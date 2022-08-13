# [2218. 从栈中取出 K 个硬币的最大面值和](https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles)

[English Version](/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一张桌子上总共有 <code>n</code>&nbsp;个硬币 <b>栈</b>&nbsp;。每个栈有 <strong>正整数</strong>&nbsp;个带面值的硬币。</p>

<p>每一次操作中，你可以从任意一个栈的 <strong>顶部</strong>&nbsp;取出 1 个硬币，从栈中移除它，并放入你的钱包里。</p>

<p>给你一个列表&nbsp;<code>piles</code>&nbsp;，其中&nbsp;<code>piles[i]</code>&nbsp;是一个整数数组，分别表示第 <code>i</code>&nbsp;个栈里 <strong>从顶到底</strong>&nbsp;的硬币面值。同时给你一个正整数&nbsp;<code>k</code>&nbsp;，请你返回在&nbsp;<strong>恰好</strong>&nbsp;进行&nbsp;<code>k</code>&nbsp;次操作的前提下，你钱包里硬币面值之和&nbsp;<strong>最大为多少</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/images/e1.png" style="width: 600px; height: 243px;" /></p>

<pre>
<b>输入：</b>piles = [[1,100,3],[7,8,9]], k = 2
<b>输出：</b>101
<strong>解释：</strong>
上图展示了几种选择 k 个硬币的不同方法。
我们可以得到的最大面值为 101 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
<b>输出：</b>706
<strong>解释：
</strong>如果我们所有硬币都从最后一个栈中取，可以得到最大面值和。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= piles[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= sum(piles[i].length) &lt;= 2000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

对每个栈求前缀和 $s$，$s_i$ 视为一个体积为 $i$ 且价值为 $s_i$ 的物品。

问题转化为求从 $n$ 个物品组中取物品体积为 $k$，且每组最多取一个物品时的最大价值和。

定义 $dp[i][j]$ 表示从前 $i$ 个组中取体积之和为 $j$ 的物品时的最大价值和。

枚举第 $i$ 组所有物品，设当前物品体积为 $w$，价值为 $v$，则有 $f[i][j]=max(f[i][j],f[i-1][j-w]+v)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        presum = [list(accumulate(p, initial=0)) for p in piles]
        n = len(piles)
        dp = [[0] * (k + 1) for _ in range(n + 1)]
        for i, s in enumerate(presum, 1):
            for j in range(k + 1):
                for idx, v in enumerate(s):
                    if j >= idx:
                        dp[i][j] = max(dp[i][j], dp[i - 1][j - idx] + v)
        return dp[-1][-1]
```

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        presum = [list(accumulate(p, initial=0)) for p in piles]
        dp = [0] * (k + 1)
        for s in presum:
            for j in range(k, -1, -1):
                for idx, v in enumerate(s):
                    if j >= idx:
                        dp[j] = max(dp[j], dp[j - idx] + v)
        return dp[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        List<int[]> presum = new ArrayList<>();
        for (List<Integer> p : piles) {
            int m = p.size();
            int[] s = new int[m + 1];
            for (int i = 0; i < m; ++i) {
                s[i + 1] = s[i] + p.get(i);
            }
            presum.add(s);
        }
        int[] dp = new int[k + 1];
        for (int[] s : presum) {
            for (int j = k; j >= 0; --j) {
                for (int idx = 0; idx < s.length; ++idx) {
                    if (j >= idx) {
                        dp[j] = Math.max(dp[j], dp[j - idx] + s[idx]);
                    }
                }
            }
        }
        return dp[k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        vector<vector<int>> presum;
        for (auto& p : piles) {
            int m = p.size();
            vector<int> s(m + 1);
            for (int i = 0; i < m; ++i) s[i + 1] = s[i] + p[i];
            presum.push_back(s);
        }
        vector<int> dp(k + 1);
        for (auto& s : presum) {
            for (int j = k; ~j; --j) {
                for (int idx = 0; idx < s.size(); ++idx) {
                    if (j >= idx) dp[j] = max(dp[j], dp[j - idx] + s[idx]);
                }
            }
        }
        return dp[k];
    }
};
```

### **Go**

```go
func maxValueOfCoins(piles [][]int, k int) int {
	var presum [][]int
	for _, p := range piles {
		m := len(p)
		s := make([]int, m+1)
		for i, v := range p {
			s[i+1] = s[i] + v
		}
		presum = append(presum, s)
	}
	dp := make([]int, k+1)
	for _, s := range presum {
		for j := k; j >= 0; j-- {
			for idx, v := range s {
				if j >= idx {
					dp[j] = max(dp[j], dp[j-idx]+v)
				}
			}
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
