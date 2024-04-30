# [2923. 找到冠军 I](https://leetcode.cn/problems/find-champion-i)

[English Version](/solution/2900-2999/2923.Find%20Champion%20I/README_EN.md)

<!-- tags:数组,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>一场比赛中共有 <code>n</code> 支队伍，按从 <code>0</code> 到&nbsp; <code>n - 1</code> 编号。</p>

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>n * n</code> 的二维布尔矩阵 <code>grid</code> 。对于满足&nbsp;<code>0 &lt;= i, j &lt;= n - 1</code> 且 <code>i != j</code> 的所有 <code>i, j</code> ：如果 <code>grid[i][j] == 1</code>，那么 <code>i</code> 队比 <code>j</code> 队 <strong>强</strong> ；否则，<code>j</code> 队比 <code>i</code> 队 <strong>强</strong> 。</p>

<p>在这场比赛中，如果不存在某支强于 <code>a</code> 队的队伍，则认为 <code>a</code> 队将会是 <strong>冠军</strong> 。</p>

<p>返回这场比赛中将会成为冠军的队伍。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0,1],[0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>比赛中有两支队伍。
grid[0][1] == 1 表示 0 队比 1 队强。所以 0 队是冠军。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0,0,1],[1,0,1],[0,0,0]]
<strong>输出：</strong>1
<strong>解释：</strong>比赛中有三支队伍。
grid[1][0] == 1 表示 1 队比 0 队强。
grid[1][2] == 1 表示 1 队比 2 队强。
所以 1 队是冠军。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>0</code> 或 <code>1</code><meta charset="UTF-8" /></li>
	<li>对于所有&nbsp;<code>i</code>，<code> grid[i][i]</code>&nbsp;等于&nbsp;<code>0.</code></li>
	<li>对于满足&nbsp;<code>i != j</code> 的所有 <code>i, j</code> ，<code>grid[i][j] != grid[j][i]</code> 均成立</li>
	<li>生成的输入满足：如果 <code>a</code> 队比 <code>b</code> 队强，<code>b</code> 队比 <code>c</code> 队强，那么 <code>a</code> 队比 <code>c</code> 队强</li>
</ul>

## 解法

### 方法一：枚举

我们可以枚举每一支队伍 $i$，如果 $i$ 队的每一场比赛都赢了，那么 $i$ 队就是冠军，直接返回 $i$ 即可。

时间复杂度 $O(n^2)$，其中 $n$ 是队伍数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findChampion(self, grid: List[List[int]]) -> int:
        for i, row in enumerate(grid):
            if all(x == 1 for j, x in enumerate(row) if i != j):
                return i
```

```java
class Solution {
    public int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0;; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (i != j && grid[i][j] == 1) {
                    ++cnt;
                }
            }
            if (cnt == n - 1) {
                return i;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int findChampion(vector<vector<int>>& grid) {
        int n = grid.size();
        for (int i = 0;; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (i != j && grid[i][j] == 1) {
                    ++cnt;
                }
            }
            if (cnt == n - 1) {
                return i;
            }
        }
    }
};
```

```go
func findChampion(grid [][]int) int {
	n := len(grid)
	for i := 0; ; i++ {
		cnt := 0
		for j, x := range grid[i] {
			if i != j && x == 1 {
				cnt++
			}
		}
		if cnt == n-1 {
			return i
		}
	}
}
```

```ts
function findChampion(grid: number[][]): number {
    for (let i = 0, n = grid.length; ; ++i) {
        let cnt = 0;
        for (let j = 0; j < n; ++j) {
            if (i !== j && grid[i][j] === 1) {
                ++cnt;
            }
        }
        if (cnt === n - 1) {
            return i;
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
