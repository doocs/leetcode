---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2732.Find%20a%20Good%20Subset%20of%20the%20Matrix/README.md
rating: 2239
source: 第 106 场双周赛 Q4
tags:
    - 位运算
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [2732. 找到矩阵中的好子集](https://leetcode.cn/problems/find-a-good-subset-of-the-matrix)

[English Version](/solution/2700-2799/2732.Find%20a%20Good%20Subset%20of%20the%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;。</p>

<p>从原矩阵中选出若干行构成一个行的 <strong>非空&nbsp;</strong>子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 <strong>好子集</strong>。</p>

<p>更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为&nbsp;<code>floor(k / 2)</code>&nbsp;。</p>

<p>请你返回一个整数数组，它包含好子集的行下标，请你将其&nbsp;<b>升序</b>&nbsp;返回。</p>

<p>如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。</p>

<p>一个矩阵 <code>grid</code>&nbsp;的行 <strong>子集</strong> ，是删除 <code>grid</code>&nbsp;中某些（也可能不删除）行后，剩余行构成的元素集合。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
<b>输出：</b>[0,1]
<b>解释：</b>我们可以选择第 0 和第 1 行构成一个好子集。
选出来的子集大小为 2 。
- 第 0&nbsp;列的和为 0 + 0 = 0 ，小于等于子集大小的一半。
- 第 1&nbsp;列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
- 第 2&nbsp;列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
- 第 3&nbsp;列的和为 0 + 1 = 1 ，小于等于子集大小的一半。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>grid = [[0]]
<b>输出：</b>[0]
<strong>解释：</strong>我们可以选择第 0 行构成一个好子集。
选出来的子集大小为 1 。
- 第 0 列的和为 0 ，小于等于子集大小的一半。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>grid = [[1,1,1],[1,1,1]]
<b>输出：</b>[]
<b>解释：</b>没有办法得到一个好子集。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= n &lt;= 5</code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分情况讨论

我们可以从小到大考虑答案选择的行数 $k$。

-   如果 $k = 1$，每一列的和最大为 $0$，那么必须满足有一行的所有元素都是 $0$，否则无法满足条件。
-   如果 $k = 2$，每一列的和最大为 $1$，那么必须存在有两行，且这两行的元素按位与之后的结果是 $0$，否则无法满足条件。
-   如果 $k = 3$，每一列的和最大也是 $1$。如果 $k = 2$ 不满足条件，那么 $k = 3$ 也一定不满足条件，所以我们不需要考虑所有 $k \gt 2$ 且 $k$ 为奇数的情况。
-   如果 $k = 4$，每一列的和最大为 $2$，此时一定是 $k = 2$ 不满足条件，也就是说，任意选取两行，都存在至少一个列的和为 $2$。我们在 $4$ 行中任意选取 $2$ 行，一共有 $C_4^2 = 6$ 种选法，那么存在至少 $6$ 个 $2$ 的列。由于列数 $n \le 5$，所以一定存在至少一列的和大于 $2$，所以 $k = 4$ 也不满足条件。
-   对于 $k \gt 4$ 且 $k$ 为偶数的情况，我们可以得出同样的结论，即 $k$ 一定不满足条件。

综上所述，我们只需要考虑 $k = 1$ 和 $k = 2$ 的情况即可。即判断是否有一行全为 $0$，或者是否存在两行按位与之后的结果为 $0$。

时间复杂度 $O(m \times n + 4^n)$，空间复杂度 $O(2^n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def goodSubsetofBinaryMatrix(self, grid: List[List[int]]) -> List[int]:
        g = {}
        for i, row in enumerate(grid):
            mask = 0
            for j, x in enumerate(row):
                mask |= x << j
            if mask == 0:
                return [i]
            g[mask] = i
        for a, i in g.items():
            for b, j in g.items():
                if (a & b) == 0:
                    return sorted([i, j])
        return []
```

#### Java

```java
class Solution {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        Map<Integer, Integer> g = new HashMap<>();
        for (int i = 0; i < grid.length; ++i) {
            int mask = 0;
            for (int j = 0; j < grid[0].length; ++j) {
                mask |= grid[i][j] << j;
            }
            if (mask == 0) {
                return List.of(i);
            }
            g.put(mask, i);
        }
        for (var e1 : g.entrySet()) {
            for (var e2 : g.entrySet()) {
                if ((e1.getKey() & e2.getKey()) == 0) {
                    int i = e1.getValue(), j = e2.getValue();
                    return List.of(Math.min(i, j), Math.max(i, j));
                }
            }
        }
        return List.of();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> goodSubsetofBinaryMatrix(vector<vector<int>>& grid) {
        unordered_map<int, int> g;
        for (int i = 0; i < grid.size(); ++i) {
            int mask = 0;
            for (int j = 0; j < grid[0].size(); ++j) {
                mask |= grid[i][j] << j;
            }
            if (mask == 0) {
                return {i};
            }
            g[mask] = i;
        }
        for (auto& [a, i] : g) {
            for (auto& [b, j] : g) {
                if ((a & b) == 0) {
                    return {min(i, j), max(i, j)};
                }
            }
        }
        return {};
    }
};
```

#### Go

```go
func goodSubsetofBinaryMatrix(grid [][]int) []int {
	g := map[int]int{}
	for i, row := range grid {
		mask := 0
		for j, x := range row {
			mask |= x << j
		}
		if mask == 0 {
			return []int{i}
		}
		g[mask] = i
	}
	for a, i := range g {
		for b, j := range g {
			if a&b == 0 {
				return []int{min(i, j), max(i, j)}
			}
		}
	}
	return []int{}
}
```

#### TypeScript

```ts
function goodSubsetofBinaryMatrix(grid: number[][]): number[] {
    const g: Map<number, number> = new Map();
    const m = grid.length;
    const n = grid[0].length;
    for (let i = 0; i < m; ++i) {
        let mask = 0;
        for (let j = 0; j < n; ++j) {
            mask |= grid[i][j] << j;
        }
        if (!mask) {
            return [i];
        }
        g.set(mask, i);
    }
    for (const [a, i] of g.entries()) {
        for (const [b, j] of g.entries()) {
            if ((a & b) === 0) {
                return [Math.min(i, j), Math.max(i, j)];
            }
        }
    }
    return [];
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn good_subsetof_binary_matrix(grid: Vec<Vec<i32>>) -> Vec<i32> {
        let mut g: HashMap<i32, i32> = HashMap::new();
        for (i, row) in grid.iter().enumerate() {
            let mut mask = 0;
            for (j, &x) in row.iter().enumerate() {
                mask |= x << j;
            }
            if mask == 0 {
                return vec![i as i32];
            }
            g.insert(mask, i as i32);
        }

        for (&a, &i) in g.iter() {
            for (&b, &j) in g.iter() {
                if (a & b) == 0 {
                    return vec![i.min(j), i.max(j)];
                }
            }
        }

        vec![]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
