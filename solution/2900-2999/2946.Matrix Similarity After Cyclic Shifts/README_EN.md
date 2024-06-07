---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/README_EN.md
rating: 1405
source: Weekly Contest 373 Q1
tags:
    - Array
    - Math
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [2946. Matrix Similarity After Cyclic Shifts](https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts)

[中文文档](/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> integer matrix <code>mat</code> and an integer <code>k</code>. The matrix rows are 0-indexed.</p>

<p>The following proccess happens <code>k</code> times:</p>

<ul>
	<li><strong>Even-indexed</strong> rows (0, 2, 4, ...) are cyclically shifted to the left.</li>
</ul>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/lshift.jpg" style="width: 283px; height: 90px;" /></p>

<ul>
	<li><strong>Odd-indexed</strong> rows (1, 3, 5, ...) are cyclically shifted to the right.</li>
</ul>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/rshift-stlone.jpg" style="width: 283px; height: 90px;" /></p>

<p>Return <code>true</code> if the final modified matrix after <code>k</code> steps is identical to the original matrix, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[1,2,3],[4,5,6],[7,8,9]], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>In each step left shift is applied to rows 0 and 2 (even indices), and right shift to row 1 (odd index).</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/t1-2.jpg" style="width: 857px; height: 150px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/t1-3.jpg" style="width: 632px; height: 150px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[2,2],[2,2]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>As all the values are equal in the matrix, even after performing cyclic shifts the matrix will remain the same.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= mat.length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i].length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areSimilar(self, mat: List[List[int]], k: int) -> bool:
        n = len(mat[0])
        for i, row in enumerate(mat):
            for j, x in enumerate(row):
                if i % 2 == 1 and x != mat[i][(j + k) % n]:
                    return False
                if i % 2 == 0 and x != mat[i][(j - k + n) % n]:
                    return False
        return True
```

#### Java

```java
class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k %= n;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i % 2 == 1 && mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
                if (i % 2 == 0 && mat[i][j] != mat[i][(j - k + n) % n]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool areSimilar(vector<vector<int>>& mat, int k) {
        int m = mat.size(), n = mat[0].size();
        k %= n;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i % 2 == 1 && mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
                if (i % 2 == 0 && mat[i][j] != mat[i][(j - k + n) % n]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func areSimilar(mat [][]int, k int) bool {
	n := len(mat[0])
	k %= n
	for i, row := range mat {
		for j, x := range row {
			if i%2 == 1 && x != mat[i][(j+k)%n] {
				return false
			}
			if i%2 == 0 && x != mat[i][(j-k+n)%n] {
				return false
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function areSimilar(mat: number[][], k: number): boolean {
    const m = mat.length;
    const n = mat[0].length;
    k %= n;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i % 2 === 1 && mat[i][j] !== mat[i][(j + k) % n]) {
                return false;
            }
            if (i % 2 === 0 && mat[i][j] !== mat[i][(j - k + n) % n]) {
                return false;
            }
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
