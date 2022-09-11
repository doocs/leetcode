# [877. 石子游戏](https://leetcode.cn/problems/stone-game)

[English Version](/solution/0800-0899/0877.Stone%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，<strong>排成一行</strong>；每堆都有 <strong>正</strong> 整数颗石子，数目为 <code>piles[i]</code>&nbsp;。</p>

<p>游戏以谁手中的石子最多来决出胜负。石子的 <strong>总数</strong> 是 <strong>奇数</strong> ，所以没有平局。</p>

<p>Alice 和 Bob 轮流进行，<strong>Alice 先开始</strong> 。 每回合，玩家从行的 <strong>开始</strong> 或 <strong>结束</strong> 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 <strong>石子最多</strong> 的玩家 <strong>获胜</strong> 。</p>

<p>假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回&nbsp;<code>true</code>&nbsp;，当 Bob 赢得比赛时返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [5,3,4,5]
<strong>输出：</strong>true
<strong>解释：</strong>
Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>piles = [3,7,2,3]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= piles.length &lt;= 500</code></li>
	<li><code>piles.length</code> 是 <strong>偶数</strong></li>
	<li><code>1 &lt;= piles[i] &lt;= 500</code></li>
	<li><code>sum(piles[i])</code>&nbsp;是 <strong>奇数</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

设 $dp[i][j]$ 表示在石子堆 $[i,j]$ 中，当前玩家与另一个玩家的石子数量的最大差值。

若 $dp[0][n-1] \gt 0$，说明当前玩家能赢得比赛。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是石子堆的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGame(self, piles: List[int]) -> bool:
        n = len(piles)
        dp = [[0] * n for _ in range(n)]
        for i, v in enumerate(piles):
            dp[i][i] = v
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
        return dp[0][-1] > 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[i][i] = piles[i];
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        int n = piles.size();
        vector<vector<int>> dp(n, vector<int>(n));
        for (int i = 0; i < n; ++i) dp[i][i] = piles[i];
        for (int i = n - 2; ~i; --i)
            for (int j = i + 1; j < n; ++j)
                dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
        return dp[0][n - 1] > 0;
    }
};
```

### **Go**

```go
func stoneGame(piles []int) bool {
	n := len(piles)
	dp := make([][]int, n)
	for i, v := range piles {
		dp[i] = make([]int, n)
		dp[i][i] = v
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = max(piles[i]-dp[i+1][j], piles[j]-dp[i][j-1])
		}
	}
	return dp[0][n-1] > 0
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
