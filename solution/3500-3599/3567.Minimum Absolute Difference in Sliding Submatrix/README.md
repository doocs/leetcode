---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3567.Minimum%20Absolute%20Difference%20in%20Sliding%20Submatrix/README.md
rating: 1568
source: 第 452 场周赛 Q2
tags:
    - 数组
    - 矩阵
    - 排序
---

<!-- problem:start -->

# [3567. 子矩阵的最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference-in-sliding-submatrix)

[English Version](/solution/3500-3599/3567.Minimum%20Absolute%20Difference%20in%20Sliding%20Submatrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的整数矩阵 <code>grid</code> 和一个整数 <code>k</code>。</p>

<p>对于矩阵 <code>grid</code> 中的每个连续的 <code>k x k</code> <strong>子矩阵</strong>，计算其中任意两个&nbsp;<strong>不同</strong>值 之间的&nbsp;<strong>最小绝对差&nbsp;</strong>。</p>

<p>返回一个大小为 <code>(m - k + 1) x (n - k + 1)</code> 的二维数组 <code>ans</code>，其中 <code>ans[i][j]</code> 表示以 <code>grid</code> 中坐标 <code>(i, j)</code> 为左上角的子矩阵的最小绝对差。</p>

<p><strong>注意</strong>：如果子矩阵中的所有元素都相同，则答案为 0。</p>

<p>子矩阵 <code>(x1, y1, x2, y2)</code> 是一个由选择矩阵中所有满足 <code>x1 &lt;= x &lt;= x2</code> 且 <code>y1 &lt;= y &lt;= y2</code> 的单元格 <code>matrix[x][y]</code> 组成的矩阵。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,8],[3,-2]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[[2]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>只有一个可能的 <code>k x k</code> 子矩阵：<code><span class="example-io">[[1, 8], [3, -2]]</span></code>。</li>
	<li>子矩阵中的不同值为 <code>[1, 8, 3, -2]</code>。</li>
	<li>子矩阵中的最小绝对差为 <code>|1 - 3| = 2</code>。因此，答案为 <code>[[2]]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[3,-1]], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[[0,0]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>每个 <code>k x k</code> 子矩阵中只有一个不同的元素。</li>
	<li>因此，答案为 <code>[[0, 0]]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,-2,3],[2,3,5]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[[1,2]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>有两个可能的 <code>k × k</code> 子矩阵：

    <ul>
    	<li>以 <code>(0, 0)</code> 为起点的子矩阵：<code>[[1, -2], [2, 3]]</code>。

    	<ul>
    		<li>子矩阵中的不同值为 <code>[1, -2, 2, 3]</code>。</li>
    		<li>子矩阵中的最小绝对差为 <code>|1 - 2| = 1</code>。</li>
    	</ul>
    	</li>
    	<li>以 <code>(0, 1)</code> 为起点的子矩阵：<code>[[-2, 3], [3, 5]]</code>。
    	<ul>
    		<li>子矩阵中的不同值为 <code>[-2, 3, 5]</code>。</li>
    		<li>子矩阵中的最小绝对差为 <code>|3 - 5| = 2</code>。</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li>因此，答案为 <code>[[1, 2]]</code>。</li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 30</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 30</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(m, n)</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举所有可能的 $k \times k$ 子矩阵的左上角坐标 $(i, j)$，对于每个子矩阵，我们可以提取出其中的所有元素，放入一个列表 $\textit{nums}$ 中。然后对 $\textit{nums}$ 进行排序，接着计算相邻的不同元素之间的绝对差，找到最小的绝对差值。最后将结果存储在一个二维数组中。

时间复杂度 $O((m - k + 1) \times (n - k + 1) \times k^2 \log(k))$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数，而 $k$ 是子矩阵的大小。空间复杂度 $O(k^2)$，用于存储每个子矩阵的元素。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAbsDiff(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        ans = [[0] * (n - k + 1) for _ in range(m - k + 1)]
        for i in range(m - k + 1):
            for j in range(n - k + 1):
                nums = []
                for x in range(i, i + k):
                    for y in range(j, j + k):
                        nums.append(grid[x][y])
                nums.sort()
                d = min((abs(a - b) for a, b in pairwise(nums) if a != b), default=0)
                ans[i][j] = d
        return ans
```

#### Java

```java
class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                List<Integer> nums = new ArrayList<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        nums.add(grid[x][y]);
                    }
                }
                Collections.sort(nums);
                int d = Integer.MAX_VALUE;
                for (int t = 1; t < nums.size(); t++) {
                    int a = nums.get(t - 1);
                    int b = nums.get(t);
                    if (a != b) {
                        d = Math.min(d, Math.abs(a - b));
                    }
                }
                ans[i][j] = (d == Integer.MAX_VALUE) ? 0 : d;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m - k + 1, vector<int>(n - k + 1, 0));
        for (int i = 0; i <= m - k; ++i) {
            for (int j = 0; j <= n - k; ++j) {
                vector<int> nums;
                for (int x = i; x < i + k; ++x) {
                    for (int y = j; y < j + k; ++y) {
                        nums.push_back(grid[x][y]);
                    }
                }
                sort(nums.begin(), nums.end());
                int d = INT_MAX;
                for (int t = 1; t < nums.size(); ++t) {
                    if (nums[t] != nums[t - 1]) {
                        d = min(d, abs(nums[t] - nums[t - 1]));
                    }
                }
                ans[i][j] = (d == INT_MAX) ? 0 : d;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minAbsDiff(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m-k+1)
	for i := range ans {
		ans[i] = make([]int, n-k+1)
	}
	for i := 0; i <= m-k; i++ {
		for j := 0; j <= n-k; j++ {
			var nums []int
			for x := i; x < i+k; x++ {
				for y := j; y < j+k; y++ {
					nums = append(nums, grid[x][y])
				}
			}
			sort.Ints(nums)
			d := math.MaxInt
			for t := 1; t < len(nums); t++ {
				if nums[t] != nums[t-1] {
					diff := abs(nums[t] - nums[t-1])
					if diff < d {
						d = diff
					}
				}
			}
			if d != math.MaxInt {
				ans[i][j] = d
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minAbsDiff(grid: number[][], k: number): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const ans: number[][] = Array.from({ length: m - k + 1 }, () => Array(n - k + 1).fill(0));
    for (let i = 0; i <= m - k; i++) {
        for (let j = 0; j <= n - k; j++) {
            const nums: number[] = [];
            for (let x = i; x < i + k; x++) {
                for (let y = j; y < j + k; y++) {
                    nums.push(grid[x][y]);
                }
            }
            nums.sort((a, b) => a - b);
            let d = Number.MAX_SAFE_INTEGER;
            for (let t = 1; t < nums.length; t++) {
                if (nums[t] !== nums[t - 1]) {
                    d = Math.min(d, Math.abs(nums[t] - nums[t - 1]));
                }
            }
            ans[i][j] = d === Number.MAX_SAFE_INTEGER ? 0 : d;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
