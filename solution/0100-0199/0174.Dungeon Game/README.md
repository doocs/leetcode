# [174. 地下城游戏](https://leetcode.cn/problems/dungeon-game)

[English Version](/solution/0100-0199/0174.Dungeon%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<style type="text/css">table.dungeon, .dungeon th, .dungeon td {
  border:3px solid black;
}

 .dungeon th, .dungeon td {
    text-align: center;
    height: 70px;
    width: 70px;
}
</style>
<p>恶魔们抓住了公主并将她关在了地下城&nbsp;<code>dungeon</code> 的 <strong>右下角</strong> 。地下城是由 <code>m x n</code> 个房间组成的二维网格。我们英勇的骑士最初被安置在 <strong>左上角</strong> 的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。</p>

<p>骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。</p>

<p>有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为<em>负整数</em>，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 <em>0</em>），要么包含增加骑士健康点数的魔法球（若房间里的值为<em>正整数</em>，则表示骑士将增加健康点数）。</p>

<p>为了尽快解救公主，骑士决定每次只 <strong>向右</strong> 或 <strong>向下</strong> 移动一步。</p>

<p>返回确保骑士能够拯救到公主所需的最低初始健康点数。</p>

<p><strong>注意：</strong>任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0174.Dungeon%20Game/images/dungeon-grid-1.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
<strong>输出：</strong>7
<strong>解释：</strong>如果骑士遵循最佳路径：右 -&gt; 右 -&gt; 下 -&gt; 下 ，则骑士的初始健康点数至少为 7 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>dungeon = [[0]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == dungeon.length</code></li>
	<li><code>n == dungeon[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>-1000 &lt;= dungeon[i][j] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $dp[i][j]$ 表示从 $(i, j)$ 到终点所需的最小初始值，那么 $dp[i][j]$ 的值可以由 $dp[i+1][j]$ 和 $dp[i][j+1]$ 得到，即：

$$
dp[i][j] = \max(\min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j], 1)
$$

初始时 $dp[m][n-1]$ 和 $dp[m-1][n]$ 都为 $1$，其他位置的值为最大值。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为地牢的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def calculateMinimumHP(self, dungeon: List[List[int]]) -> int:
        m, n = len(dungeon), len(dungeon[0])
        dp = [[inf] * (n + 1) for _ in range(m + 1)]
        dp[m][n - 1] = dp[m - 1][n] = 1
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                dp[i][j] = max(
                    1, min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j])
        return dp[0][0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (var e : dp) {
            Arrays.fill(e, 1 << 30);
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        int m = dungeon.size(), n = dungeon[0].size();
        int dp[m + 1][n + 1];
        memset(dp, 0x3f, sizeof dp);
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; ~i; --i) {
            for (int j = n - 1; ~j; --j) {
                dp[i][j] = max(1, min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
};
```

### **Go**

```go
func calculateMinimumHP(dungeon [][]int) int {
	m, n := len(dungeon), len(dungeon[0])
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
		for j := range dp[i] {
			dp[i][j] = 1 << 30
		}
	}
	dp[m][n-1], dp[m-1][n] = 1, 1
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1])-dungeon[i][j])
		}
	}
	return dp[0][0]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int CalculateMinimumHP(int[][] dungeon) {
        int m = dungeon.Length, n = dungeon[0].Length;
        int[][] dp = new int[m + 1][];
        for (int i = 0; i < m + 1; ++i) {
            dp[i] = new int[n + 1];
            Array.Fill(dp[i], 1 << 30);
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.Max(1, Math.Min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
