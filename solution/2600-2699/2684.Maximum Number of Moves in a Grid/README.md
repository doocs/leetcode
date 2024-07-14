---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/README.md
rating: 1625
source: 第 345 场周赛 Q3
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [2684. 矩阵中移动的最大次数](https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid)

[English Version](/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的矩阵 <code>grid</code> ，矩阵由若干 <strong>正</strong> 整数组成。</p>

<p>你可以从矩阵第一列中的 <strong>任一</strong> 单元格出发，按以下方式遍历&nbsp;<code>grid</code> ：</p>

<ul>
	<li>从单元格 <code>(row, col)</code> 可以移动到&nbsp;<code>(row - 1, col + 1)</code>、<code>(row, col + 1)</code> 和 <code>(row + 1, col + 1)</code> 三个单元格中任一满足值 <strong>严格</strong> 大于当前单元格的单元格。</li>
</ul>

<p>返回你在矩阵中能够 <strong>移动</strong> 的 <strong>最大</strong> 次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgriddrawio-10.png" style="width: 201px; height: 201px;">
<pre><strong>输入：</strong>grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
<strong>输出：</strong>3
<strong>解释：</strong>可以从单元格 (0, 0) 开始并且按下面的路径移动：
- (0, 0) -&gt; (0, 1).
- (0, 1) -&gt; (1, 2).
- (1, 2) -&gt; (2, 3).
可以证明这是能够移动的最大次数。</pre>

<p><strong>示例 2：</strong></p>

<pre><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgrid4drawio.png">
<strong>输入：</strong>grid = [[3,2,4],[2,1,9],[1,1,7]]
<strong>输出：</strong>0
<strong>解释：</strong>从第一列的任一单元格开始都无法移动。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们定义一个队列 $q$，初始时将第一列的所有行坐标加入队列中。

接下来，我们从第一列开始，逐列进行遍历。对于每一列，我们将队列中的所有行坐标依次取出，然后对于每一个行坐标 $i$，我们得到其下一列的所有可能行坐标 $k$，并且满足 $grid[i][j] < grid[k][j + 1]$，将这些行坐标加入到一个新的集合 $t$ 中。如果 $t$ 为空，说明我们无法继续移动，返回当前列数。否则，我们将 $t$ 赋值给 $q$，继续下一列的遍历。

最后，如果我们遍历完了所有列，说明我们可以移动到最后一列，返回 $n - 1$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = set(range(m))
        for j in range(n - 1):
            t = set()
            for i in q:
                for k in range(i - 1, i + 2):
                    if 0 <= k < m and grid[i][j] < grid[k][j + 1]:
                        t.add(k)
            if not t:
                return j
            q = t
        return n - 1
```

#### Java

```java
class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<Integer> q = IntStream.range(0, m).boxed().collect(Collectors.toSet());
        for (int j = 0; j < n - 1; ++j) {
            Set<Integer> t = new HashSet<>();
            for (int i : q) {
                for (int k = i - 1; k <= i + 1; ++k) {
                    if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                        t.add(k);
                    }
                }
            }
            if (t.isEmpty()) {
                return j;
            }
            q = t;
        }
        return n - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxMoves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        unordered_set<int> q, t;
        for (int i = 0; i < m; ++i) {
            q.insert(i);
        }
        for (int j = 0; j < n - 1; ++j) {
            t.clear();
            for (int i : q) {
                for (int k = i - 1; k <= i + 1; ++k) {
                    if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                        t.insert(k);
                    }
                }
            }
            if (t.empty()) {
                return j;
            }
            q.swap(t);
        }
        return n - 1;
    }
};
```

#### Go

```go
func maxMoves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	q := map[int]bool{}
	for i := range grid {
		q[i] = true
	}
	for j := 0; j < n-1; j++ {
		t := map[int]bool{}
		for i := range q {
			for k := i - 1; k <= i+1; k++ {
				if k >= 0 && k < m && grid[i][j] < grid[k][j+1] {
					t[k] = true
				}
			}
		}
		if len(t) == 0 {
			return j
		}
		q = t
	}
	return n - 1
}
```

#### TypeScript

```ts
function maxMoves(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let q = new Set<number>(Array.from({ length: m }, (_, i) => i));
    for (let j = 0; j < n - 1; ++j) {
        const t = new Set<number>();
        for (const i of q) {
            for (let k = i - 1; k <= i + 1; ++k) {
                if (k >= 0 && k < m && grid[i][j] < grid[k][j + 1]) {
                    t.add(k);
                }
            }
        }
        if (t.size === 0) {
            return j;
        }
        q = t;
    }
    return n - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
