# [750. 角矩形的数量 🔒](https://leetcode.cn/problems/number-of-corner-rectangles)

[English Version](/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/README_EN.md)

<!-- tags:数组,数学,动态规划,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包含 <code>0</code> 和 <code>1</code> 的&nbsp;<code>m x n</code>&nbsp;整数矩阵&nbsp;<code>grid</code>&nbsp;，返回 <em>其中 「<strong>角矩形 」</strong>的数量</em> 。</p>

<p>一个<strong>「角矩形」</strong>是由四个不同的在矩阵上的 <code>1</code> 形成的&nbsp;<strong>轴对齐&nbsp;</strong>的矩形。注意只有角的位置才需要为 <code>1</code>。</p>

<p><strong>注意：</strong>4 个 <code>1</code>&nbsp;的位置需要是不同的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec1-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,1,0],[0,0,1,0,1],[0,0,0,1,0],[1,0,1,0,1]]
<strong>输出：</strong>1
<strong>解释：</strong>只有一个角矩形，角的位置为 grid[1][2], grid[1][4], grid[3][2], grid[3][4]。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1],[1,1,1],[1,1,1]]
<strong>输出：</strong>9
<strong>解释：</strong>这里有 4 个 2x2 的矩形，4 个 2x3 和 3x2 的矩形和 1 个 3x3&nbsp;的矩形。
</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec3-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1]]
<strong>输出：</strong>0
<strong>解释：</strong>矩形必须有 4 个不同的角。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li>
	<li>网格中&nbsp;<code>1</code>&nbsp;的个数在&nbsp;<code>[1, 6000]</code> 范围内</li>
</ul>

## 解法

### 方法一：哈希表 + 枚举

我们枚举每一行作为矩形的下边，对于当前行，如果列 $i$ 和列 $j$ 都是 $1$，那么我们用哈希表找出此前的所有行中，有多少行的 $i$ 和 $j$ 列都是 $1$，那么就有多少个以 $(i, j)$ 为右下角的矩形，我们将其数量加入答案。然后将 $(i, j)$ 加入哈希表，继续枚举下一对 $(i, j)$。

时间复杂度 $O(m \times n^2)$，空间复杂度 $O(n^2)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def countCornerRectangles(self, grid: List[List[int]]) -> int:
        ans = 0
        cnt = Counter()
        n = len(grid[0])
        for row in grid:
            for i, c1 in enumerate(row):
                if c1:
                    for j in range(i + 1, n):
                        if row[j]:
                            ans += cnt[(i, j)]
                            cnt[(i, j)] += 1
        return ans
```

```java
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int n = grid[0].length;
        int ans = 0;
        Map<List<Integer>, Integer> cnt = new HashMap<>();
        for (var row : grid) {
            for (int i = 0; i < n; ++i) {
                if (row[i] == 1) {
                    for (int j = i + 1; j < n; ++j) {
                        if (row[j] == 1) {
                            List<Integer> t = List.of(i, j);
                            ans += cnt.getOrDefault(t, 0);
                            cnt.merge(t, 1, Integer::sum);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countCornerRectangles(vector<vector<int>>& grid) {
        int n = grid[0].size();
        int ans = 0;
        map<pair<int, int>, int> cnt;
        for (auto& row : grid) {
            for (int i = 0; i < n; ++i) {
                if (row[i]) {
                    for (int j = i + 1; j < n; ++j) {
                        if (row[j]) {
                            ans += cnt[{i, j}];
                            ++cnt[{i, j}];
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

```go
func countCornerRectangles(grid [][]int) (ans int) {
	n := len(grid[0])
	type pair struct{ x, y int }
	cnt := map[pair]int{}
	for _, row := range grid {
		for i, x := range row {
			if x == 1 {
				for j := i + 1; j < n; j++ {
					if row[j] == 1 {
						t := pair{i, j}
						ans += cnt[t]
						cnt[t]++
					}
				}
			}
		}
	}
	return
}
```

```ts
function countCornerRectangles(grid: number[][]): number {
    const n = grid[0].length;
    let ans = 0;
    const cnt: Map<number, number> = new Map();
    for (const row of grid) {
        for (let i = 0; i < n; ++i) {
            if (row[i] === 1) {
                for (let j = i + 1; j < n; ++j) {
                    if (row[j] === 1) {
                        const t = i * 200 + j;
                        ans += cnt.get(t) ?? 0;
                        cnt.set(t, (cnt.get(t) ?? 0) + 1);
                    }
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
