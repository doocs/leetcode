---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/README.md
rating: 1309
source: 第 323 场周赛 Q1
tags:
    - 数组
    - 矩阵
    - 排序
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [2500. 删除每行中的最大值](https://leetcode.cn/problems/delete-greatest-value-in-each-row)

[English Version](/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 大小的矩阵 <code>grid</code> ，由若干正整数组成。</p>

<p>执行下述操作，直到 <code>grid</code> 变为空矩阵：</p>

<ul>
	<li>从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。</li>
	<li>将删除元素中的最大值与答案相加。</li>
</ul>

<p><strong>注意</strong> 每执行一次操作，矩阵中列的数据就会减 1 。</p>

<p>返回执行上述操作后的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/images/q1ex1.jpg" style="width: 600px; height: 135px;" /></p>

<pre>
<strong>输入：</strong>grid = [[1,2,4],[3,3,1]]
<strong>输出：</strong>8
<strong>解释：</strong>上图展示在每一步中需要移除的值。
- 在第一步操作中，从第一行删除 4 ，从第二行删除 3（注意，有两个单元格中的值为 3 ，我们可以删除任一）。在答案上加 4 。
- 在第二步操作中，从第一行删除 2 ，从第二行删除 3 。在答案上加 3 。
- 在第三步操作中，从第一行删除 1 ，从第二行删除 1 。在答案上加 1 。
最终，答案 = 4 + 3 + 1 = 8 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/images/q1ex2.jpg" style="width: 83px; height: 83px;" /></p>

<pre>
<strong>输入：</strong>grid = [[10]]
<strong>输出：</strong>10
<strong>解释：</strong>上图展示在每一步中需要移除的值。
- 在第一步操作中，从第一行删除 10 。在答案上加 10 。
最终，答案 = 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

由于每一次操作都是从每一行中删除最大值，然后取最大值加到答案中，因此我们可以先对每一行进行排序。

接下来遍历每一列，取每一列的最大值，然后将其加到答案中即可。

时间复杂度 $O(m \times n \times \log n)$，空间复杂度 $O(\log n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def deleteGreatestValue(self, grid: List[List[int]]) -> int:
        for row in grid:
            row.sort()
        return sum(max(col) for col in zip(*grid))
```

#### Java

```java
class Solution {
    public int deleteGreatestValue(int[][] grid) {
        for (var row : grid) {
            Arrays.sort(row);
        }
        int ans = 0;
        for (int j = 0; j < grid[0].length; ++j) {
            int t = 0;
            for (int i = 0; i < grid.length; ++i) {
                t = Math.max(t, grid[i][j]);
            }
            ans += t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int deleteGreatestValue(vector<vector<int>>& grid) {
        for (auto& row : grid) {
            ranges::sort(row);
        }
        int ans = 0;
        for (int j = 0; j < grid[0].size(); ++j) {
            int t = 0;
            for (int i = 0; i < grid.size(); ++i) {
                t = max(t, grid[i][j]);
            }
            ans += t;
        }
        return ans;
    }
};
```

#### Go

```go
func deleteGreatestValue(grid [][]int) (ans int) {
	for _, row := range grid {
		sort.Ints(row)
	}
	for j := range grid[0] {
		t := 0
		for i := range grid {
			if t < grid[i][j] {
				t = grid[i][j]
			}
		}
		ans += t
	}
	return
}
```

#### TypeScript

```ts
function deleteGreatestValue(grid: number[][]): number {
    for (const row of grid) {
        row.sort((a, b) => a - b);
    }

    let ans = 0;
    for (let j = 0; j < grid[0].length; ++j) {
        let t = 0;
        for (let i = 0; i < grid.length; ++i) {
            t = Math.max(t, grid[i][j]);
        }
        ans += t;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn delete_greatest_value(grid: Vec<Vec<i32>>) -> i32 {
        let mut grid = grid;
        for i in 0..grid.len() {
            grid[i].sort();
        }

        let mut ans = 0;
        for j in 0..grid[0].len() {
            let mut mx = 0;

            for i in 0..grid.len() {
                if grid[i][j] > mx {
                    mx = grid[i][j];
                }
            }

            ans += mx;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
