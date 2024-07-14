---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/README.md
rating: 1766
source: 第 247 场周赛 Q2
tags:
    - 数组
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [1914. 循环轮转矩阵](https://leetcode.cn/problems/cyclically-rotating-a-grid)

[English Version](/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>grid</code>​​​ ，其中 <code>m</code> 和 <code>n</code> 都是 <strong>偶数</strong> ；另给你一个整数 <code>k</code> 。</p>

<p>矩阵由若干层组成，如下图所示，每种颜色代表一层：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid.png" style="width: 231px; height: 258px;"></p>

<p>矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 <strong>逆时针 </strong>方向的相邻元素。轮转示例如下：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/explanation_grid.jpg" style="width: 500px; height: 268px;">
<p>返回执行 <code>k</code> 次循环轮转操作后的矩阵。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/rod2.png" style="width: 421px; height: 191px;">
<pre><strong>输入：</strong>grid = [[40,10],[30,20]], k = 1
<strong>输出：</strong>[[10,20],[40,30]]
<strong>解释：</strong>上图展示了矩阵在执行循环轮转操作时每一步的状态。</pre>

<p><strong>示例 2：</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid5.png" style="width: 231px; height: 262px;"></strong> <strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid6.png" style="width: 231px; height: 262px;"></strong> <strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid7.png" style="width: 231px; height: 262px;"></strong>

<pre><strong>输入：</strong>grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
<strong>输出：</strong>[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
<strong>解释：</strong>上图展示了矩阵在执行循环轮转操作时每一步的状态。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 50</code></li>
	<li><code>m</code> 和 <code>n</code> 都是 <strong>偶数</strong></li>
	<li><code>1 &lt;= grid[i][j] &lt;=<sup> </sup>5000</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逐层模拟

我们先计算得到矩阵的层数 $p$，然后从外到内逐层模拟循环轮转的过程。

对于每一层，我们按照顺时针方向，将上、右、下、左四条边的元素依次放入数组 $nums$ 中。记数组 $nums$ 的长度为 $l$。接下来，我们将 $k$ 模 $l$。然后从数组的第 $k$ 个位置开始，将数组中的元素依次放回矩阵的上、右、下、左四条边。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotateGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        def rotate(p: int, k: int):
            nums = []
            for j in range(p, n - p - 1):
                nums.append(grid[p][j])
            for i in range(p, m - p - 1):
                nums.append(grid[i][n - p - 1])
            for j in range(n - p - 1, p, -1):
                nums.append(grid[m - p - 1][j])
            for i in range(m - p - 1, p, -1):
                nums.append(grid[i][p])
            k %= len(nums)
            if k == 0:
                return
            nums = nums[k:] + nums[:k]
            k = 0
            for j in range(p, n - p - 1):
                grid[p][j] = nums[k]
                k += 1
            for i in range(p, m - p - 1):
                grid[i][n - p - 1] = nums[k]
                k += 1
            for j in range(n - p - 1, p, -1):
                grid[m - p - 1][j] = nums[k]
                k += 1
            for i in range(m - p - 1, p, -1):
                grid[i][p] = nums[k]
                k += 1

        m, n = len(grid), len(grid[0])
        for p in range(min(m, n) >> 1):
            rotate(p, k)
        return grid
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int[][] rotateGrid(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int p = 0; p < Math.min(m, n) / 2; ++p) {
            rotate(p, k);
        }
        return grid;
    }

    private void rotate(int p, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int j = p; j < n - p - 1; ++j) {
            nums.add(grid[p][j]);
        }
        for (int i = p; i < m - p - 1; ++i) {
            nums.add(grid[i][n - p - 1]);
        }
        for (int j = n - p - 1; j > p; --j) {
            nums.add(grid[m - p - 1][j]);
        }
        for (int i = m - p - 1; i > p; --i) {
            nums.add(grid[i][p]);
        }
        int l = nums.size();
        k %= l;
        if (k == 0) {
            return;
        }
        for (int j = p; j < n - p - 1; ++j) {
            grid[p][j] = nums.get(k++ % l);
        }
        for (int i = p; i < m - p - 1; ++i) {
            grid[i][n - p - 1] = nums.get(k++ % l);
        }
        for (int j = n - p - 1; j > p; --j) {
            grid[m - p - 1][j] = nums.get(k++ % l);
        }
        for (int i = m - p - 1; i > p; --i) {
            grid[i][p] = nums.get(k++ % l);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> rotateGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        auto rotate = [&](int p, int k) {
            vector<int> nums;
            for (int j = p; j < n - p - 1; ++j) {
                nums.push_back(grid[p][j]);
            }
            for (int i = p; i < m - p - 1; ++i) {
                nums.push_back(grid[i][n - p - 1]);
            }
            for (int j = n - p - 1; j > p; --j) {
                nums.push_back(grid[m - p - 1][j]);
            }
            for (int i = m - p - 1; i > p; --i) {
                nums.push_back(grid[i][p]);
            }
            int l = nums.size();
            k %= l;
            if (k == 0) {
                return;
            }
            for (int j = p; j < n - p - 1; ++j) {
                grid[p][j] = nums[k++ % l];
            }
            for (int i = p; i < m - p - 1; ++i) {
                grid[i][n - p - 1] = nums[k++ % l];
            }
            for (int j = n - p - 1; j > p; --j) {
                grid[m - p - 1][j] = nums[k++ % l];
            }
            for (int i = m - p - 1; i > p; --i) {
                grid[i][p] = nums[k++ % l];
            }
        };
        for (int p = 0; p < min(m, n) / 2; ++p) {
            rotate(p, k);
        }
        return grid;
    }
};
```

#### Go

```go
func rotateGrid(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])

	rotate := func(p, k int) {
		nums := []int{}
		for j := p; j < n-p-1; j++ {
			nums = append(nums, grid[p][j])
		}
		for i := p; i < m-p-1; i++ {
			nums = append(nums, grid[i][n-p-1])
		}
		for j := n - p - 1; j > p; j-- {
			nums = append(nums, grid[m-p-1][j])
		}
		for i := m - p - 1; i > p; i-- {
			nums = append(nums, grid[i][p])
		}
		l := len(nums)
		k %= l
		if k == 0 {
			return
		}
		for j := p; j < n-p-1; j++ {
			grid[p][j] = nums[k]
			k = (k + 1) % l
		}
		for i := p; i < m-p-1; i++ {
			grid[i][n-p-1] = nums[k]
			k = (k + 1) % l
		}
		for j := n - p - 1; j > p; j-- {
			grid[m-p-1][j] = nums[k]
			k = (k + 1) % l
		}
		for i := m - p - 1; i > p; i-- {
			grid[i][p] = nums[k]
			k = (k + 1) % l
		}
	}

	for i := 0; i < m/2 && i < n/2; i++ {
		rotate(i, k)
	}
	return grid
}
```

#### TypeScript

```ts
function rotateGrid(grid: number[][], k: number): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const rotate = (p: number, k: number) => {
        const nums: number[] = [];
        for (let j = p; j < n - p - 1; ++j) {
            nums.push(grid[p][j]);
        }
        for (let i = p; i < m - p - 1; ++i) {
            nums.push(grid[i][n - p - 1]);
        }
        for (let j = n - p - 1; j > p; --j) {
            nums.push(grid[m - p - 1][j]);
        }
        for (let i = m - p - 1; i > p; --i) {
            nums.push(grid[i][p]);
        }
        const l = nums.length;
        k %= l;
        if (k === 0) {
            return;
        }
        for (let j = p; j < n - p - 1; ++j) {
            grid[p][j] = nums[k++ % l];
        }
        for (let i = p; i < m - p - 1; ++i) {
            grid[i][n - p - 1] = nums[k++ % l];
        }
        for (let j = n - p - 1; j > p; --j) {
            grid[m - p - 1][j] = nums[k++ % l];
        }
        for (let i = m - p - 1; i > p; --i) {
            grid[i][p] = nums[k++ % l];
        }
    };
    for (let p = 0; p < Math.min(m, n) >> 1; ++p) {
        rotate(p, k);
    }
    return grid;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
