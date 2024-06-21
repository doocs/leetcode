---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2732.Find%20a%20Good%20Subset%20of%20the%20Matrix/README_EN.md
rating: 2239
source: Biweekly Contest 106 Q4
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [2732. Find a Good Subset of the Matrix](https://leetcode.com/problems/find-a-good-subset-of-the-matrix)

[中文文档](/solution/2700-2799/2732.Find%20a%20Good%20Subset%20of%20the%20Matrix/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> binary matrix <code>grid</code>.</p>

<p>Let us call a <strong>non-empty</strong> subset of rows <strong>good</strong> if the sum of each column of the subset is at most half of the length of the subset.</p>

<p>More formally, if the length of the chosen subset of rows is <code>k</code>, then the sum of each column should be at most <code>floor(k / 2)</code>.</p>

<p>Return <em>an integer array that contains row indices of a good subset sorted in <strong>ascending</strong> order.</em></p>

<p>If there are multiple good subsets, you can return any of them. If there are no good subsets, return an empty array.</p>

<p>A <strong>subset</strong> of rows of the matrix <code>grid</code> is any matrix that can be obtained by deleting some (possibly none or all) rows from <code>grid</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> We can choose the 0<sup>th</sup> and 1<sup>st</sup> rows to create a good subset of rows.
The length of the chosen subset is 2.
- The sum of the 0<sup>th</sup>&nbsp;column is 0 + 0 = 0, which is at most half of the length of the subset.
- The sum of the 1<sup>st</sup>&nbsp;column is 1 + 0 = 1, which is at most half of the length of the subset.
- The sum of the 2<sup>nd</sup>&nbsp;column is 1 + 0 = 1, which is at most half of the length of the subset.
- The sum of the 3<sup>rd</sup>&nbsp;column is 0 + 1 = 1, which is at most half of the length of the subset.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0]]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> We can choose the 0<sup>th</sup> row to create a good subset of rows.
The length of the chosen subset is 1.
- The sum of the 0<sup>th</sup>&nbsp;column is 0, which is at most half of the length of the subset.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,1,1]]
<strong>Output:</strong> []
<strong>Explanation:</strong> It is impossible to choose any subset of rows to create a good subset.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= n &lt;= 5</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
