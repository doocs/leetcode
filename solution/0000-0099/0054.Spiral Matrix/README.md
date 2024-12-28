---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0054.Spiral%20Matrix/README.md
tags:
    - 数组
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix)

[English Version](/solution/0000-0099/0054.Spiral%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵 <code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 10</code></li>
	<li><code>-100 <= matrix[i][j] <= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟整个遍历的过程，用 $i$ 和 $j$ 分别表示当前访问到的元素的行和列，用 $k$ 表示当前的方向，用数组或哈希表 $\textit{vis}$ 记录每个元素是否被访问过。每次我们访问到一个元素后，将其标记为已访问，然后按照当前的方向前进一步，如果前进一步后发现越界或者已经访问过，则改变方向继续前进，直到遍历完整个矩阵。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        dirs = (0, 1, 0, -1, 0)
        vis = [[False] * n for _ in range(m)]
        i = j = k = 0
        ans = []
        for _ in range(m * n):
            ans.append(matrix[i][j])
            vis[i][j] = True
            x, y = i + dirs[k], j + dirs[k + 1]
            if x < 0 or x >= m or y < 0 or y >= n or vis[x][y]:
                k = (k + 1) % 4
            i += dirs[k]
            j += dirs[k + 1]
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        List<Integer> ans = new ArrayList<>();
        boolean[][] vis = new boolean[m][n];
        for (int h = m * n; h > 0; --h) {
            ans.add(matrix[i][j]);
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int dirs[5] = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        vector<int> ans;
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        for (int h = m * n; h; --h) {
            ans.push_back(matrix[i][j]);
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
};
```

#### Go

```go
func spiralOrder(matrix [][]int) (ans []int) {
	m, n := len(matrix), len(matrix[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := [5]int{0, 1, 0, -1, 0}
	i, j, k := 0, 0, 0
	for h := m * n; h > 0; h-- {
		ans = append(ans, matrix[i][j])
		vis[i][j] = true
		x, y := i+dirs[k], j+dirs[k+1]
		if x < 0 || x >= m || y < 0 || y >= n || vis[x][y] {
			k = (k + 1) % 4
		}
		i, j = i+dirs[k], j+dirs[k+1]
	}
	return
}
```

#### TypeScript

```ts
function spiralOrder(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const ans: number[] = [];
    const vis: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const dirs = [0, 1, 0, -1, 0];
    for (let h = m * n, i = 0, j = 0, k = 0; h > 0; --h) {
        ans.push(matrix[i][j]);
        vis[i][j] = true;
        const x = i + dirs[k];
        const y = j + dirs[k + 1];
        if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn spiral_order(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut dirs = vec![0, 1, 0, -1, 0];
        let mut vis = vec![vec![false; n]; m];
        let mut i = 0;
        let mut j = 0;
        let mut k = 0;
        let mut ans = Vec::new();

        for _ in 0..(m * n) {
            ans.push(matrix[i][j]);
            vis[i][j] = true;
            let x = i as i32 + dirs[k] as i32;
            let y = j as i32 + dirs[k + 1] as i32;

            if x < 0 || x >= m as i32 || y < 0 || y >= n as i32 || vis[x as usize][y as usize] {
                k = (k + 1) % 4;
            }

            i = (i as i32 + dirs[k] as i32) as usize;
            j = (j as i32 + dirs[k + 1] as i32) as usize;
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const ans = [];
    const vis = Array.from({ length: m }, () => Array(n).fill(false));
    const dirs = [0, 1, 0, -1, 0];
    for (let h = m * n, i = 0, j = 0, k = 0; h > 0; --h) {
        ans.push(matrix[i][j]);
        vis[i][j] = true;
        const x = i + dirs[k];
        const y = j + dirs[k + 1];
        if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        int[] dirs = { 0, 1, 0, -1, 0 };
        int i = 0, j = 0, k = 0;
        IList<int> ans = new List<int>();
        bool[,] vis = new bool[m, n];
        for (int h = m * n; h > 0; --h) {
            ans.Add(matrix[i][j]);
            vis[i, j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || vis[x, y]) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：模拟（空间优化）

注意到，题目中矩阵元素取值范围为 $[-100, 100]$，因此，我们可以将访问过的元素加上一个较大的值，比如 $300$，这样只需要判断访问的元素是否大于 $100$ 即可，无需额外的空间记录是否访问过。如果最终需要将访问过的元素恢复原值，可以在遍历结束后再次遍历一遍矩阵，将所有元素减去 $300$。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        dirs = (0, 1, 0, -1, 0)
        i = j = k = 0
        ans = []
        for _ in range(m * n):
            ans.append(matrix[i][j])
            matrix[i][j] += 300
            x, y = i + dirs[k], j + dirs[k + 1]
            if x < 0 or x >= m or y < 0 or y >= n or matrix[x][y] > 100:
                k = (k + 1) % 4
            i += dirs[k]
            j += dirs[k + 1]
        for i in range(m):
            for j in range(n):
                matrix[i][j] -= 300
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        List<Integer> ans = new ArrayList<>();
        for (int h = m * n; h > 0; --h) {
            ans.add(matrix[i][j]);
            matrix[i][j] += 300;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                matrix[i][j] -= 300;
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
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int dirs[5] = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        vector<int> ans;
        for (int h = m * n; h; --h) {
            ans.push_back(matrix[i][j]);
            matrix[i][j] += 300;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        for (i = 0; i < m; ++i) {
            for (j = 0; j < n; ++j) {
                matrix[i][j] -= 300;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func spiralOrder(matrix [][]int) (ans []int) {
	m, n := len(matrix), len(matrix[0])
	dirs := [5]int{0, 1, 0, -1, 0}
	i, j, k := 0, 0, 0
	for h := m * n; h > 0; h-- {
		ans = append(ans, matrix[i][j])
		matrix[i][j] += 300
		x, y := i+dirs[k], j+dirs[k+1]
		if x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100 {
			k = (k + 1) % 4
		}
		i, j = i+dirs[k], j+dirs[k+1]
	}
	for i = 0; i < m; i++ {
		for j = 0; j < n; j++ {
			matrix[i][j] -= 300
		}
	}
	return
}
```

#### TypeScript

```ts
function spiralOrder(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const ans: number[] = [];
    const dirs = [0, 1, 0, -1, 0];
    for (let h = m * n, i = 0, j = 0, k = 0; h > 0; --h) {
        ans.push(matrix[i][j]);
        matrix[i][j] += 300;
        const x = i + dirs[k];
        const y = j + dirs[k + 1];
        if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            matrix[i][j] -= 300;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn spiral_order(mut matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut dirs = vec![0, 1, 0, -1, 0];
        let mut i = 0;
        let mut j = 0;
        let mut k = 0;
        let mut ans = Vec::new();

        for _ in 0..(m * n) {
            ans.push(matrix[i][j]);
            matrix[i][j] += 300;
            let x = i as i32 + dirs[k] as i32;
            let y = j as i32 + dirs[k + 1] as i32;

            if x < 0
                || x >= m as i32
                || y < 0
                || y >= n as i32
                || matrix[x as usize][y as usize] > 100
            {
                k = (k + 1) % 4;
            }

            i = (i as i32 + dirs[k] as i32) as usize;
            j = (j as i32 + dirs[k + 1] as i32) as usize;
        }

        for i in 0..m {
            for j in 0..n {
                matrix[i][j] -= 300;
            }
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const ans = [];
    const dirs = [0, 1, 0, -1, 0];
    for (let h = m * n, i = 0, j = 0, k = 0; h > 0; --h) {
        ans.push(matrix[i][j]);
        matrix[i][j] += 300;
        const x = i + dirs[k];
        const y = j + dirs[k + 1];
        if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            matrix[i][j] -= 300;
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        int[] dirs = { 0, 1, 0, -1, 0 };
        int i = 0, j = 0, k = 0;
        IList<int> ans = new List<int>();
        for (int h = m * n; h > 0; --h) {
            ans.Add(matrix[i][j]);
            matrix[i][j] += 300;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        for (int a = 0; a < m; ++a) {
            for (int b = 0; b < n; ++b) {
                matrix[a][b] -= 300;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
