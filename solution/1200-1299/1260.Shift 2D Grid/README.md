# [1260. 二维网格迁移](https://leetcode.cn/problems/shift-2d-grid)

[English Version](/solution/1200-1299/1260.Shift%202D%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m</code> 行 <code>n</code> 列的二维网格 <code>grid</code> 和一个整数 <code>k</code>。你需要将 <code>grid</code> 迁移 <code>k</code> 次。</p>

<p>每次「迁移」操作将会引发下述活动：</p>

<ul>
	<li>位于 <code>grid[i][j]</code> 的元素将会移动到 <code>grid[i][j + 1]</code>。</li>
	<li>位于 <code>grid[i][n - 1]</code> 的元素将会移动到 <code>grid[i + 1][0]</code>。</li>
	<li>位于 <code>grid[m - 1][n - 1]</code> 的元素将会移动到 <code>grid[0][0]</code>。</li>
</ul>

<p>请你返回 <code>k</code> 次迁移操作后最终得到的 <strong>二维网格</strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1260.Shift%202D%20Grid/images/e1-1.png" style="height: 158px; width: 400px;" /></p>

<pre>
<code><strong>输入：</strong>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], k = 1
<strong>输出：</strong>[[9,1,2],[3,4,5],[6,7,8]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1260.Shift%202D%20Grid/images/e2-1.png" style="height: 166px; width: 400px;" /></p>

<pre>
<code><strong>输入：</strong>grid</code> = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
<strong>输出：</strong>[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<code><strong>输入：</strong>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], k = 9
<strong>输出：</strong>[[1,2,3],[4,5,6],[7,8,9]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m <= 50</code></li>
	<li><code>1 <= n <= 50</code></li>
	<li><code>-1000 <= grid[i][j] <= 1000</code></li>
	<li><code>0 <= k <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        k %= m * n
        t = [grid[i][j] for i in range(m) for j in range(n)]
        t = t[-k:] + t[:-k]
        for i in range(m):
            for j in range(n):
                grid[i][j] = t[i * n + j]
        return grid
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        k %= (m * n);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                t.add(0);
            }
            ans.add(t);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = (i * n + j + k) % (m * n);
                ans.get(t / n).set(t % n, grid[i][j]);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function shiftGrid(grid: number[][], k: number): number[][] {
    const m = grid.length,
        n = grid[0].length;
    const size = m * n;
    k = k % size;
    if (k == 0 || size <= 1) return grid;
    let arr = grid.flat();
    if (size <= 1) return grid;
    let ans = Array.from({ length: m }, v => new Array(n));
    for (let i = 0, j = size - k; i < size; i++) {
        ans[Math.floor(i / n)][i % n] = arr[j];
        j = j == size - 1 ? 0 : j + 1;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = (i * n + j + k) % (m * n);
                ans[t / n][t % n] = grid[i][j];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shiftGrid(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			t := (i*n + j + k) % (m * n)
			ans[t/n][t%n] = grid[i][j]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
