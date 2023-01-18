# [2482. 行和列中一和零的差值](https://leetcode.cn/problems/difference-between-ones-and-zeros-in-row-and-column)

[English Version](/solution/2400-2499/2482.Difference%20Between%20Ones%20and%20Zeros%20in%20Row%20and%20Column/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;二进制矩阵&nbsp;<code>grid</code>&nbsp;。</p>

<p>我们按照如下过程，定义一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;差值矩阵&nbsp;<code>diff</code>&nbsp;：</p>

<ul>
	<li>令第&nbsp;<code>i</code>&nbsp;行一的数目为&nbsp;<code>onesRow<sub>i</sub></code>&nbsp;。</li>
	<li>令第&nbsp;<code>j</code>&nbsp;列一的数目为&nbsp;<code>onesCol<sub>j</sub></code><sub>&nbsp;</sub>。</li>
	<li>令第&nbsp;<code>i</code>&nbsp;行零的数目为&nbsp;<code>zerosRow<sub>i</sub></code>&nbsp;。</li>
	<li>令第&nbsp;<code>j</code>&nbsp;列零的数目为&nbsp;<code>zerosCol<sub>j</sub></code>&nbsp;。</li>
	<li><code>diff[i][j] = onesRow<sub>i</sub> + onesCol<sub>j</sub> - zerosRow<sub>i</sub> - zerosCol<sub>j</sub></code></li>
</ul>

<p>请你返回差值矩阵<em>&nbsp;</em><code>diff</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2482.Difference%20Between%20Ones%20and%20Zeros%20in%20Row%20and%20Column/images/image-20221106171729-5.png" style="width: 400px; height: 208px;"></p>

<pre><b>输入：</b>grid = [[0,1,1],[1,0,1],[0,0,1]]
<b>输出：</b>[[0,0,4],[0,0,4],[-2,-2,2]]
<b>解释：</b>
- diff[0][0] = <code>onesRow<sub>0</sub> + onesCol<sub>0</sub> - zerosRow<sub>0</sub> - zerosCol<sub>0</sub></code> = 2 + 1 - 1 - 2 = 0 
- diff[0][1] = <code>onesRow<sub>0</sub> + onesCol<sub>1</sub> - zerosRow<sub>0</sub> - zerosCol<sub>1</sub></code> = 2 + 1 - 1 - 2 = 0 
- diff[0][2] = <code>onesRow<sub>0</sub> + onesCol<sub>2</sub> - zerosRow<sub>0</sub> - zerosCol<sub>2</sub></code> = 2 + 3 - 1 - 0 = 4 
- diff[1][0] = <code>onesRow<sub>1</sub> + onesCol<sub>0</sub> - zerosRow<sub>1</sub> - zerosCol<sub>0</sub></code> = 2 + 1 - 1 - 2 = 0 
- diff[1][1] = <code>onesRow<sub>1</sub> + onesCol<sub>1</sub> - zerosRow<sub>1</sub> - zerosCol<sub>1</sub></code> = 2 + 1 - 1 - 2 = 0 
- diff[1][2] = <code>onesRow<sub>1</sub> + onesCol<sub>2</sub> - zerosRow<sub>1</sub> - zerosCol<sub>2</sub></code> = 2 + 3 - 1 - 0 = 4 
- diff[2][0] = <code>onesRow<sub>2</sub> + onesCol<sub>0</sub> - zerosRow<sub>2</sub> - zerosCol<sub>0</sub></code> = 1 + 1 - 2 - 2 = -2
- diff[2][1] = <code>onesRow<sub>2</sub> + onesCol<sub>1</sub> - zerosRow<sub>2</sub> - zerosCol<sub>1</sub></code> = 1 + 1 - 2 - 2 = -2
- diff[2][2] = <code>onesRow<sub>2</sub> + onesCol<sub>2</sub> - zerosRow<sub>2</sub> - zerosCol<sub>2</sub></code> = 1 + 3 - 2 - 0 = 2
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2482.Difference%20Between%20Ones%20and%20Zeros%20in%20Row%20and%20Column/images/image-20221106171747-6.png" style="width: 358px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[1,1,1],[1,1,1]]
<b>输出：</b>[[5,5,5],[5,5,5]]
<strong>解释：</strong>
- diff[0][0] = onesRow<sub>0</sub> + onesCol<sub>0</sub> - zerosRow<sub>0</sub> - zerosCol<sub>0</sub> = 3 + 2 - 0 - 0 = 5
- diff[0][1] = onesRow<sub>0</sub> + onesCol<sub>1</sub> - zerosRow<sub>0</sub> - zerosCol<sub>1</sub> = 3 + 2 - 0 - 0 = 5
- diff[0][2] = onesRow<sub>0</sub> + onesCol<sub>2</sub> - zerosRow<sub>0</sub> - zerosCol<sub>2</sub> = 3 + 2 - 0 - 0 = 5
- diff[1][0] = onesRow<sub>1</sub> + onesCol<sub>0</sub> - zerosRow<sub>1</sub> - zerosCol<sub>0</sub> = 3 + 2 - 0 - 0 = 5
- diff[1][1] = onesRow<sub>1</sub> + onesCol<sub>1</sub> - zerosRow<sub>1</sub> - zerosCol<sub>1</sub> = 3 + 2 - 0 - 0 = 5
- diff[1][2] = onesRow<sub>1</sub> + onesCol<sub>2</sub> - zerosRow<sub>1</sub> - zerosCol<sub>2</sub> = 3 + 2 - 0 - 0 = 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意模拟即可。

时间复杂度 $O(m \times n)$，忽略答案的空间消耗，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def onesMinusZeros(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        rows = [0] * m
        cols = [0] * n
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                rows[i] += v
                cols[j] += v
        diff = [[0] * n for _ in range(m)]
        for i, r in enumerate(rows):
            for j, c in enumerate(cols):
                diff[i][j] = r + c - (n - r) - (m - c)
        return diff
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = grid[i][j];
                rows[i] += v;
                cols[j] += v;
            }
        }
        int[][] diff = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                diff[i][j] = rows[i] + cols[j] - (n - rows[i]) - (m - cols[j]);
            }
        }
        return diff;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> onesMinusZeros(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> rows(m);
        vector<int> cols(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = grid[i][j];
                rows[i] += v;
                cols[j] += v;
            }
        }
        vector<vector<int>> diff(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                diff[i][j] = rows[i] + cols[j] - (n - rows[i]) - (m - cols[j]);
            }
        }
        return diff;
    }
};
```

### **Go**

```go
func onesMinusZeros(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	rows := make([]int, m)
	cols := make([]int, n)
	diff := make([][]int, m)
	for i, row := range grid {
		diff[i] = make([]int, n)
		for j, v := range row {
			rows[i] += v
			cols[j] += v
		}
	}
	for i, r := range rows {
		for j, c := range cols {
			diff[i][j] = r + c - (n - r) - (m - c)
		}
	}
	return diff
}
```

### **TypeScript**

```ts
function onesMinusZeros(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const rows = new Array(m).fill(0);
    const cols = new Array(n).fill(0);
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j]) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    const ans = Array.from({ length: m }, () => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans[i][j] = rows[i] + cols[j] - (m - rows[i]) - (n - cols[j]);
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn ones_minus_zeros(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = grid.len();
        let n = grid[0].len();
        let mut rows = vec![0; m];
        let mut cols = vec![0; n];
        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == 1 {
                    rows[i] += 1;
                    cols[j] += 1;
                }
            }
        }
        let mut ans = vec![vec![0; n]; m];
        for i in 0..m {
            for j in 0..n {
                ans[i][j] = (rows[i] + cols[j] - (m - rows[i]) - (n - cols[j])) as i32;
            }
        }
        ans
    }
}
```

### **C**

```c
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int **onesMinusZeros(int **grid, int gridSize, int *gridColSize, int *returnSize, int **returnColumnSizes) {
    int *rows = malloc(sizeof(int) * gridSize);
    int *cols = malloc(sizeof(int) * gridColSize[0]);
    memset(rows, 0, sizeof(int) * gridSize);
    memset(cols, 0, sizeof(int) * gridColSize[0]);
    for (int i = 0; i < gridSize; i++) {
        for (int j = 0; j < gridColSize[0]; j++) {
            if (grid[i][j]) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    int **ans = malloc(sizeof(int *) * gridSize);
    for (int i = 0; i < gridSize; i++) {
        ans[i] = malloc(sizeof(int) * gridColSize[0]);
        for (int j = 0; j < gridColSize[0]; j++) {
            ans[i][j] = rows[i] + cols[j] - (gridSize - rows[i]) - (gridColSize[0] - cols[j]);
        }
    }
    *returnSize = gridSize;
    *returnColumnSizes = gridColSize;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
