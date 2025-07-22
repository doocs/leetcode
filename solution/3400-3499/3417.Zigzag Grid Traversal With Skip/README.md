---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/README.md
rating: 1290
source: 第 432 场周赛 Q1
tags:
    - 数组
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [3417. 跳过交替单元格的之字形遍历](https://leetcode.cn/problems/zigzag-grid-traversal-with-skip)

[English Version](/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的二维数组 <code>grid</code>，数组由&nbsp;<strong>正整数</strong> 组成。</p>

<p>你的任务是以&nbsp;<strong>之字形&nbsp;</strong>遍历 <code>grid</code>，同时跳过每个&nbsp;<strong>交替&nbsp;</strong>的单元格。</p>

<p>之字形遍历的定义如下：</p>

<ul>
	<li>从左上角的单元格 <code>(0, 0)</code> 开始。</li>
	<li>在当前行中向 <strong>右</strong> 移动，直到到达该行的末尾。</li>
	<li>下移到下一行，然后在该行中向&nbsp;<strong>左</strong><em>&nbsp;</em>移动，直到到达该行的开头。</li>
	<li>继续在行间交替向右和向左移动，直到所有行都被遍历完。</li>
</ul>

<p><strong>注意：</strong>在遍历过程中，必须跳过每个&nbsp;<strong>交替&nbsp;</strong>的单元格。</p>

<p>返回一个整数数组 <code>result</code>，其中包含按&nbsp;<strong>顺序&nbsp;</strong>记录的、且跳过交替单元格后的之字形遍历中访问到的单元格值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,2],[3,4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,4]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/images/4012_example0.png" style="width: 200px; height: 200px;" /></strong></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[2,1],[2,1],[2,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,1,2]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/images/4012_example1.png" style="width: 200px; height: 240px;" /></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,2,3],[4,5,6],[7,8,9]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,3,5,7,9]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/images/4012_example2.png" style="width: 260px; height: 250px;" /></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == grid.length &lt;= 50</code></li>
	<li><code>2 &lt;= m == grid[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 2500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历每一行，如果当前行的索引是奇数，我们就将这一行的元素逆序，然后遍历这一行的元素，按照题目要求的规则将元素加入答案数组中。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是二维数组 $\textit{grid}$ 的行数和列数。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def zigzagTraversal(self, grid: List[List[int]]) -> List[int]:
        ok = True
        ans = []
        for i, row in enumerate(grid):
            if i % 2:
                row.reverse()
            for x in row:
                if ok:
                    ans.append(x)
                ok = not ok
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> zigzagTraversal(int[][] grid) {
        boolean ok = true;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < grid.length; ++i) {
            if (i % 2 == 1) {
                reverse(grid[i]);
            }
            for (int x : grid[i]) {
                if (ok) {
                    ans.add(x);
                }
                ok = !ok;
            }
        }
        return ans;
    }

    private void reverse(int[] nums) {
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> zigzagTraversal(vector<vector<int>>& grid) {
        vector<int> ans;
        bool ok = true;
        for (int i = 0; i < grid.size(); ++i) {
            if (i % 2 != 0) {
                ranges::reverse(grid[i]);
            }
            for (int x : grid[i]) {
                if (ok) {
                    ans.push_back(x);
                }
                ok = !ok;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func zigzagTraversal(grid [][]int) (ans []int) {
	ok := true
	for i, row := range grid {
		if i%2 != 0 {
			slices.Reverse(row)
		}
		for _, x := range row {
			if ok {
				ans = append(ans, x)
			}
			ok = !ok
		}
	}
	return
}
```

#### TypeScript

```ts
function zigzagTraversal(grid: number[][]): number[] {
    const ans: number[] = [];
    let ok: boolean = true;
    for (let i = 0; i < grid.length; ++i) {
        if (i % 2) {
            grid[i].reverse();
        }
        for (const x of grid[i]) {
            if (ok) {
                ans.push(x);
            }
            ok = !ok;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
