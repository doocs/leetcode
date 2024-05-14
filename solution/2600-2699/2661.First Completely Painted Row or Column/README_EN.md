# [2661. First Completely Painted Row or Column](https://leetcode.com/problems/first-completely-painted-row-or-column)

[中文文档](/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/README.md)

<!-- tags:Array,Hash Table,Matrix -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>arr</code>, and an <code>m x n</code> integer <strong>matrix</strong> <code>mat</code>. <code>arr</code> and <code>mat</code> both contain <strong>all</strong> the integers in the range <code>[1, m * n]</code>.</p>

<p>Go through each index <code>i</code> in <code>arr</code> starting from index <code>0</code> and paint the cell in <code>mat</code> containing the integer <code>arr[i]</code>.</p>

<p>Return <em>the smallest index</em> <code>i</code> <em>at which either a row or a column will be completely painted in</em> <code>mat</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/images/image explanation for example 1" /><img alt="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/images/image explanation for example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/images/grid1.jpg" style="width: 321px; height: 81px;" />
<pre>
<strong>Input:</strong> arr = [1,3,4,2], mat = [[1,4],[2,3]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="image explanation for example 2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2661.First%20Completely%20Painted%20Row%20or%20Column/images/grid2.jpg" style="width: 601px; height: 121px;" />
<pre>
<strong>Input:</strong> arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The second column becomes fully painted at arr[3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n = mat[i].length</code></li>
	<li><code>arr.length == m * n</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i], mat[r][c] &lt;= m * n</code></li>
	<li>All the integers of <code>arr</code> are <strong>unique</strong>.</li>
	<li>All the integers of <code>mat</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

### Solution 1: Hash Table + Array Counting

We use a hash table $idx$ to record the position of each element in the matrix $mat$, that is $idx[mat[i][j]] = (i, j)$, and define two arrays $row$ and $col$ to record the number of colored elements in each row and each column respectively.

Traverse the array $arr$. For each element $arr[k]$, we find its position $(i, j)$ in the matrix $mat$, and then add $row[i]$ and $col[j]$ by one. If $row[i] = n$ or $col[j] = m$, it means that the $i$-th row or the $j$-th column has been colored, so $arr[k]$ is the element we are looking for, and we return $k$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here $m$ and $n$ are the number of rows and columns of the matrix $mat$ respectively.

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
