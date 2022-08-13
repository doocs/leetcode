# [1049. 最后一块石头的重量 II](https://leetcode.cn/problems/last-stone-weight-ii)

[English Version](/solution/1000-1099/1049.Last%20Stone%20Weight%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一堆石头，用整数数组&nbsp;<code>stones</code> 表示。其中&nbsp;<code>stones[i]</code> 表示第 <code>i</code> 块石头的重量。</p>

<p>每一回合，从中选出<strong>任意两块石头</strong>，然后将它们一起粉碎。假设石头的重量分别为&nbsp;<code>x</code> 和&nbsp;<code>y</code>，且&nbsp;<code>x &lt;= y</code>。那么粉碎的可能结果如下：</p>

<ul>
	<li>如果&nbsp;<code>x == y</code>，那么两块石头都会被完全粉碎；</li>
	<li>如果&nbsp;<code>x != y</code>，那么重量为&nbsp;<code>x</code>&nbsp;的石头将会完全粉碎，而重量为&nbsp;<code>y</code>&nbsp;的石头新重量为&nbsp;<code>y-x</code>。</li>
</ul>

<p>最后，<strong>最多只会剩下一块 </strong>石头。返回此石头 <strong>最小的可能重量 </strong>。如果没有石头剩下，就返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [2,7,4,1,8,1]
<strong>输出：</strong>1
<strong>解释：</strong>
组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [31,26,33,21,40]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 30</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

**两个**石头的重量越接近，粉碎后的新重量就越小。同样的，**两堆**石头的重量越接近，它们粉碎后的新重量也越小。

所以本题可以转换为，计算容量为 `sum / 2` 的背包最多能装多少重量的石头。

定义 `dp[i][j]` 表示从前 i 个石头中选出若干个，使得所选石头重量之和为不超过 j 的最大重量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划——`0-1` 背包朴素做法：

```python
class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        s = sum(stones)
        m, n = len(stones), s >> 1
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(n + 1):
                dp[i][j] = dp[i - 1][j]
                if stones[i - 1] <= j:
                    dp[i][j] = max(
                        dp[i][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]
                    )
        return s - 2 * dp[-1][-1]
```

动态规划——`0-1` 背包空间优化：

```python
class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        s = sum(stones)
        m, n = len(stones), s >> 1
        dp = [0] * (n + 1)
        for v in stones:
            for j in range(n, v - 1, -1):
                dp[j] = max(dp[j], dp[j - v] + v)
        return s - dp[-1] * 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int s = 0;
        for (int v : stones) {
            s += v;
        }
        int m = stones.length;
        int n = s >> 1;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (stones[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        return s - dp[m][n] * 2;
    }
}
```

```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int s = 0;
        for (int v : stones) {
            s += v;
        }
        int m = stones.length;
        int n = s >> 1;
        int[] dp = new int[n + 1];
        for (int v : stones) {
            for (int j = n; j >= v; --j) {
                dp[j] = Math.max(dp[j], dp[j - v] + v);
            }
        }
        return s - dp[n] * 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lastStoneWeightII(vector<int>& stones) {
        int s = accumulate(stones.begin(), stones.end(), 0);
        int m = stones.size(), n = s >> 1;
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (stones[i - 1] <= j) dp[i][j] = max(dp[i][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
            }
        }
        return s - dp[m][n] * 2;
    }
};
```

```cpp
class Solution {
public:
    int lastStoneWeightII(vector<int>& stones) {
        int s = accumulate(stones.begin(), stones.end(), 0);
        int n = s >> 1;
        vector<int> dp(n + 1);
        for (int& v : stones)
            for (int j = n; j >= v; --j)
                dp[j] = max(dp[j], dp[j - v] + v);
        return s - dp[n] * 2;
    }
};
```

### **Go**

```go
func lastStoneWeightII(stones []int) int {
	s := 0
	for _, v := range stones {
		s += v
	}
	m, n := len(stones), s>>1
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			dp[i][j] = dp[i-1][j]
			if stones[i-1] <= j {
				dp[i][j] = max(dp[i][j], dp[i-1][j-stones[i-1]]+stones[i-1])
			}
		}
	}
	return s - dp[m][n]*2
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func lastStoneWeightII(stones []int) int {
	s := 0
	for _, v := range stones {
		s += v
	}
	n := s >> 1
	dp := make([]int, n+1)
	for _, v := range stones {
		for j := n; j >= v; j-- {
			dp[j] = max(dp[j], dp[j-v]+v)
		}
	}
	return s - dp[n]*2
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeightII = function (stones) {
    let s = 0;
    for (let v of stones) {
        s += v;
    }
    const n = s >> 1;
    let dp = new Array(n + 1).fill(0);
    for (let v of stones) {
        for (let j = n; j >= v; --j) {
            dp[j] = Math.max(dp[j], dp[j - v] + v);
        }
    }
    return s - dp[n] * 2;
};
```

### **...**

```

```

<!-- tabs:end -->
