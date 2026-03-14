---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/README_EN.md
rating: 1407
source: Weekly Contest 244 Q1
tags:
    - Array
    - Matrix
---

<!-- problem:start -->

# [1886. Determine Whether Matrix Can Be Obtained By Rotation](https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation)

[中文文档](/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/README.md)

## Description

<!-- description:start -->

<p>Given two <code>n x n</code> binary matrices <code>mat</code> and <code>target</code>, return <code>true</code><em> if it is possible to make </em><code>mat</code><em> equal to </em><code>target</code><em> by <strong>rotating</strong> </em><code>mat</code><em> in <strong>90-degree increments</strong>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid3.png" style="width: 301px; height: 121px;" />
<pre>
<strong>Input:</strong> mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
<strong>Output:</strong> true
<strong>Explanation: </strong>We can rotate mat 90 degrees clockwise to make mat equal target.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid4.png" style="width: 301px; height: 121px;" />
<pre>
<strong>Input:</strong> mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to make mat equal to target by rotating mat.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid4.png" style="width: 661px; height: 184px;" />
<pre>
<strong>Input:</strong> mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
<strong>Output:</strong> true
<strong>Explanation: </strong>We can rotate mat 90 degrees clockwise two times to make mat equal target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == mat.length == target.length</code></li>
	<li><code>n == mat[i].length == target[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>mat[i][j]</code> and <code>target[i][j]</code> are either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: In-Place Comparison

We observe the rotation pattern of the matrix and find that for an element $\text{mat}[i][j]$, after rotating 90 degrees it appears at position $\text{mat}[j][n-1-i]$, after rotating 180 degrees it appears at position $\text{mat}[n-1-i][n-1-j]$, and after rotating 270 degrees it appears at position $\text{mat}[n-1-j][i]$.

Therefore, we can use an integer $\textit{ok}$ to record the current rotation state, initialized to $0b1111$, indicating that all four rotation states are possible. For each element in the matrix, we compare whether its position under different rotation states matches the corresponding element in the target matrix. If they are not equal, we remove that rotation state from $\textit{ok}$. Finally, if $\textit{ok}$ is not zero, it means at least one rotation state can make the matrix consistent with the target matrix, and we return $\textit{true}$; otherwise, we return $\textit{false}$.

The time complexity is $O(n^2)$, where $n$ is the size of the matrix. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRotation(self, mat: List[List[int]], target: List[List[int]]) -> bool:
        n = len(mat)
        ok = 0b1111
        for i in range(n):
            for j in range(n):
                if mat[i][j] != target[i][j]:
                    ok &= ~0b0001
                if mat[j][n - 1 - i] != target[i][j]:
                    ok &= ~0b0010
                if mat[n - 1 - i][n - 1 - j] != target[i][j]:
                    ok &= ~0b0100
                if mat[n - 1 - j][i] != target[i][j]:
                    ok &= ~0b1000
                if ok == 0:
                    return False
        return ok != 0
```

#### Java

```java
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        int ok = 0b1111;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    ok &= ~0b0001;
                }
                if (mat[j][n - 1 - i] != target[i][j]) {
                    ok &= ~0b0010;
                }
                if (mat[n - 1 - i][n - 1 - j] != target[i][j]) {
                    ok &= ~0b0100;
                }
                if (mat[n - 1 - j][i] != target[i][j]) {
                    ok &= ~0b1000;
                }
                if (ok == 0) {
                    return false;
                }
            }
        }
        return ok != 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool findRotation(vector<vector<int>>& mat, vector<vector<int>>& target) {
        int n = mat.size();
        int ok = 0b1111;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] != target[i][j]) {
                    ok &= ~0b0001;
                }
                if (mat[j][n - 1 - i] != target[i][j]) {
                    ok &= ~0b0010;
                }
                if (mat[n - 1 - i][n - 1 - j] != target[i][j]) {
                    ok &= ~0b0100;
                }
                if (mat[n - 1 - j][i] != target[i][j]) {
                    ok &= ~0b1000;
                }
                if (ok == 0) {
                    return false;
                }
            }
        }
        return ok != 0;
    }
};
```

#### Go

```go
func findRotation(mat [][]int, target [][]int) bool {
    n := len(mat)
    ok := 0b1111

    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            if mat[i][j] != target[i][j] {
                ok &= ^0b0001
            }
            if mat[j][n-1-i] != target[i][j] {
                ok &= ^0b0010
            }
            if mat[n-1-i][n-1-j] != target[i][j] {
                ok &= ^0b0100
            }
            if mat[n-1-j][i] != target[i][j] {
                ok &= ^0b1000
            }
            if ok == 0 {
                return false
            }
        }
    }

    return ok != 0
}
```

#### TypeScript

```ts
function findRotation(mat: number[][], target: number[][]): boolean {
    const n = mat.length;
    let ok = 0b1111;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] !== target[i][j]) {
                ok &= ~0b0001;
            }
            if (mat[j][n - 1 - i] !== target[i][j]) {
                ok &= ~0b0010;
            }
            if (mat[n - 1 - i][n - 1 - j] !== target[i][j]) {
                ok &= ~0b0100;
            }
            if (mat[n - 1 - j][i] !== target[i][j]) {
                ok &= ~0b1000;
            }
            if (ok === 0) {
                return false;
            }
        }
    }

    return ok !== 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_rotation(mat: Vec<Vec<i32>>, target: Vec<Vec<i32>>) -> bool {
        let n = mat.len();
        let mut ok: i32 = 0b1111;

        for i in 0..n {
            for j in 0..n {
                if mat[i][j] != target[i][j] {
                    ok &= !0b0001;
                }
                if mat[j][n - 1 - i] != target[i][j] {
                    ok &= !0b0010;
                }
                if mat[n - 1 - i][n - 1 - j] != target[i][j] {
                    ok &= !0b0100;
                }
                if mat[n - 1 - j][i] != target[i][j] {
                    ok &= !0b1000;
                }
                if ok == 0 {
                    return false;
                }
            }
        }

        ok != 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
