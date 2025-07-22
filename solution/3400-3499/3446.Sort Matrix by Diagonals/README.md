---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/README.md
rating: 1372
source: 第 436 场周赛 Q1
tags:
    - 数组
    - 矩阵
    - 排序
---

<!-- problem:start -->

# [3446. 按对角线进行矩阵排序](https://leetcode.cn/problems/sort-matrix-by-diagonals)

[English Version](/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为&nbsp;<code>n x n</code> 的整数方阵 <code>grid</code>。返回一个经过如下调整的矩阵：</p>

<ul>
	<li><strong>左下角三角形</strong>（包括中间对角线）的对角线按&nbsp;<strong>非递增顺序&nbsp;</strong>排序。</li>
	<li><strong>右上角三角形&nbsp;</strong>的对角线按&nbsp;<strong>非递减顺序&nbsp;</strong>排序。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,7,3],[9,8,2],[4,5,6]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[8,2,3],[9,6,7],[4,5,1]]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/images/4052example1drawio.png" style="width: 461px; height: 181px;" /></p>

<p>标有黑色箭头的对角线（左下角三角形）应按非递增顺序排序：</p>

<ul>
	<li><code>[1, 8, 6]</code> 变为 <code>[8, 6, 1]</code>。</li>
	<li><code>[9, 5]</code> 和 <code>[4]</code> 保持不变。</li>
</ul>

<p>标有蓝色箭头的对角线（右上角三角形）应按非递减顺序排序：</p>

<ul>
	<li><code>[7, 2]</code> 变为 <code>[2, 7]</code>。</li>
	<li><code>[3]</code> 保持不变。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[0,1],[1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[2,1],[1,0]]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/images/4052example2adrawio.png" style="width: 383px; height: 141px;" /></p>

<p>标有黑色箭头的对角线必须按非递增顺序排序，因此 <code>[0, 2]</code> 变为 <code>[2, 0]</code>。其他对角线已经符合要求。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[1]]</span></p>

<p><strong>解释：</strong></p>

<p>只有一个元素的对角线已经符合要求，因此无需修改。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>grid.length == grid[i].length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟 + 排序

我们可以按照题目描述的要求，模拟对角线的排序过程。

我们首先对左下角三角形的对角线进行排序，然后对右上角三角形的对角线进行排序。最后返回排序后的矩阵即可。

时间复杂度 $O(n^2 \log n)$，空间复杂度 $O(n)$。其中 $n$ 是矩阵的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortMatrix(self, grid: List[List[int]]) -> List[List[int]]:
        n = len(grid)
        for k in range(n - 2, -1, -1):
            i, j = k, 0
            t = []
            while i < n and j < n:
                t.append(grid[i][j])
                i += 1
                j += 1
            t.sort()
            i, j = k, 0
            while i < n and j < n:
                grid[i][j] = t.pop()
                i += 1
                j += 1
        for k in range(n - 2, 0, -1):
            i, j = k, n - 1
            t = []
            while i >= 0 and j >= 0:
                t.append(grid[i][j])
                i -= 1
                j -= 1
            t.sort()
            i, j = k, n - 1
            while i >= 0 and j >= 0:
                grid[i][j] = t.pop()
                i -= 1
                j -= 1
        return grid
```

#### Java

```java
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            List<Integer> t = new ArrayList<>();
            while (i < n && j < n) {
                t.add(grid[i++][j++]);
            }
            Collections.sort(t);
            for (int x : t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            List<Integer> t = new ArrayList<>();
            while (i >= 0 && j >= 0) {
                t.add(grid[i--][j--]);
            }
            Collections.sort(t);
            for (int x : t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> sortMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            vector<int> t;
            while (i < n && j < n) {
                t.push_back(grid[i++][j++]);
            }
            ranges::sort(t);
            for (int x : t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            vector<int> t;
            while (i >= 0 && j >= 0) {
                t.push_back(grid[i--][j--]);
            }
            ranges::sort(t);
            for (int x : t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
};
```

#### Go

```go
func sortMatrix(grid [][]int) [][]int {
	n := len(grid)
	for k := n - 2; k >= 0; k-- {
		i, j := k, 0
		t := []int{}
		for ; i < n && j < n; i, j = i+1, j+1 {
			t = append(t, grid[i][j])
		}
		sort.Ints(t)
		for _, x := range t {
			i, j = i-1, j-1
			grid[i][j] = x
		}
	}
	for k := n - 2; k > 0; k-- {
		i, j := k, n-1
		t := []int{}
		for ; i >= 0 && j >= 0; i, j = i-1, j-1 {
			t = append(t, grid[i][j])
		}
		sort.Ints(t)
		for _, x := range t {
			i, j = i+1, j+1
			grid[i][j] = x
		}
	}
	return grid
}
```

#### TypeScript

```ts
function sortMatrix(grid: number[][]): number[][] {
    const n = grid.length;
    for (let k = n - 2; k >= 0; --k) {
        let [i, j] = [k, 0];
        const t: number[] = [];
        while (i < n && j < n) {
            t.push(grid[i++][j++]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[--i][--j] = x;
        }
    }
    for (let k = n - 2; k > 0; --k) {
        let [i, j] = [k, n - 1];
        const t: number[] = [];
        while (i >= 0 && j >= 0) {
            t.push(grid[i--][j--]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[++i][++j] = x;
        }
    }
    return grid;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
