# [2661. 找出叠涂元素](https://leetcode.cn/problems/first-completely-painted-row-or-column)

[English Version](/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>arr</code> 和一个 <code>m x n</code> 的整数 <strong>矩阵</strong> <code>mat</code> 。<code>arr</code> 和 <code>mat</code> 都包含范围 <code>[1，m * n]</code> 内的 <strong>所有</strong> 整数。</p>

<p>从下标 <code>0</code> 开始遍历 <code>arr</code> 中的每个下标 <code>i</code> ，并将包含整数 <code>arr[i]</code> 的 <code>mat</code> 单元格涂色。</p>

<p>请你找出 <code>arr</code> 中第一个使得&nbsp;<code>mat</code> 的某一行或某一列都被涂色的元素，并返回其下标 <code>i</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="image explanation for example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/images/grid1.jpg" style="width: 321px; height: 81px;" />
<pre>
<strong>输入：</strong>arr = [1,3,4,2], mat = [[1,4],[2,3]]
<strong>输出：</strong>2
<strong>解释：</strong>遍历如上图所示，arr[2] 在矩阵中的第一行或第二列上都被涂色。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="image explanation for example 2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/images/grid2.jpg" style="width: 601px; height: 121px;" />
<pre>
<strong>输入：</strong>arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
<strong>输出：</strong>3
<strong>解释：</strong>遍历如上图所示，arr[3] 在矩阵中的第二列上都被涂色。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n = mat[i].length</code></li>
	<li><code>arr.length == m * n</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i], mat[r][c] &lt;= m * n</code></li>
	<li><code>arr</code> 中的所有整数 <strong>互不相同</strong></li>
	<li><code>mat</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 数组计数**

我们用一个哈希表 $idx$ 记录每个元素在矩阵 $mat$ 中的位置，即 $idx[mat[i][j]] = (i, j)$，定义两个数组 $row$ 和 $col$ 分别记录每行和每列已经涂色的元素个数。

遍历数组 $arr$，对于每个元素 $arr[k]$，我们找到其在矩阵 $mat$ 中的位置 $(i, j)$，然后将 $row[i]$ 和 $col[j]$ 分别加一，如果 $row[i] = n$ 或 $col[j] = m$，说明第 $i$ 行或第 $j$ 列已经被涂色，那么 $arr[k]$ 就是我们要找的元素，返回 $k$ 即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵 $mat$ 的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def firstCompleteIndex(self, arr: List[int], mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        idx = {}
        for i in range(m):
            for j in range(n):
                idx[mat[i][j]] = (i, j)
        row = [0] * m
        col = [0] * n
        for k in range(len(arr)):
            i, j = idx[arr[k]]
            row[i] += 1
            col[j] += 1
            if row[i] == n or col[j] == m:
                return k
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, int[]> idx = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                idx.put(mat[i][j], new int[] {i, j});
            }
        }
        int[] row = new int[m];
        int[] col = new int[n];
        for (int k = 0;; ++k) {
            var x = idx.get(arr[k]);
            int i = x[0], j = x[1];
            ++row[i];
            ++col[j];
            if (row[i] == n || col[j] == m) {
                return k;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int firstCompleteIndex(vector<int>& arr, vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        unordered_map<int, pair<int, int>> idx;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                idx[mat[i][j]] = {i, j};
            }
        }
        vector<int> row(m), col(n);
        for (int k = 0;; ++k) {
            auto [i, j] = idx[arr[k]];
            ++row[i];
            ++col[j];
            if (row[i] == n || col[j] == m) {
                return k;
            }
        }
    }
};
```

### **Go**

```go
func firstCompleteIndex(arr []int, mat [][]int) int {
	m, n := len(mat), len(mat[0])
	idx := map[int][2]int{}
	for i := range mat {
		for j := range mat[i] {
			idx[mat[i][j]] = [2]int{i, j}
		}
	}
	row := make([]int, m)
	col := make([]int, n)
	for k := 0; ; k++ {
		x := idx[arr[k]]
		i, j := x[0], x[1]
		row[i]++
		col[j]++
		if row[i] == n || col[j] == m {
			return k
		}
	}
}
```

### **TypeScript**

```ts
function firstCompleteIndex(arr: number[], mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const idx: Map<number, number[]> = new Map();
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            idx.set(mat[i][j], [i, j]);
        }
    }
    const row: number[] = Array(m).fill(0);
    const col: number[] = Array(n).fill(0);
    for (let k = 0; ; ++k) {
        const [i, j] = idx.get(arr[k])!;
        ++row[i];
        ++col[j];
        if (row[i] === n || col[j] === m) {
            return k;
        }
    }
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn first_complete_index(arr: Vec<i32>, mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut idx = HashMap::new();
        for i in 0..m {
            for j in 0..n {
                idx.insert(mat[i][j], [i, j]);
            }
        }

        let mut row = vec![0; m];
        let mut col = vec![0; n];
        for k in 0..arr.len() {
            let x = idx.get(&arr[k]).unwrap();
            let i = x[0];
            let j = x[1];
            row[i] += 1;
            col[j] += 1;
            if row[i] == n || col[j] == m {
                return k as i32;
            }
        }

        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
