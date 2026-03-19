---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/README.md
rating: 1407
source: 第 244 场周赛 Q1
tags:
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [1886. 判断矩阵经轮转后是否一致](https://leetcode.cn/problems/determine-whether-matrix-can-be-obtained-by-rotation)

[English Version](/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个大小为 <code>n x n</code> 的二进制矩阵 <code>mat</code> 和 <code>target</code> 。现<strong> 以 90 度顺时针轮转 </strong>矩阵 <code>mat</code> 中的元素 <strong>若干次</strong> ，如果能够使 <code>mat</code> 与 <code>target</code> 一致，返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> 。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid3.png" style="width: 301px; height: 121px;" />
<pre>
<strong>输入：</strong>mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
<strong>输出：</strong>true
<strong>解释：</strong>顺时针轮转 90 度一次可以使 mat 和 target 一致。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid4.png" style="width: 301px; height: 121px;" />
<pre>
<strong>输入：</strong>mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
<strong>输出：</strong>false
<strong>解释：</strong>无法通过轮转矩阵中的元素使 equal 与 target 一致。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1886.Determine%20Whether%20Matrix%20Can%20Be%20Obtained%20By%20Rotation/images/grid4.png" style="width: 661px; height: 184px;" />
<pre>
<strong>输入：</strong>mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
<strong>输出：</strong>true
<strong>解释：</strong>顺时针轮转 90 度两次可以使 mat 和 target 一致。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == mat.length == target.length</code></li>
	<li><code>n == mat[i].length == target[i].length</code></li>
	<li><code>1 <= n <= 10</code></li>
	<li><code>mat[i][j]</code> 和 <code>target[i][j]</code> 不是 <code>0</code> 就是 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：原地比较

我们观察矩阵的轮转规律，发现对于矩阵中的元素 $\text{mat}[i][j]$，它在轮转 90 度后会出现在 $\text{mat}[j][n-1-i]$ 的位置，在轮转 180 度后会出现在 $\text{mat}[n-1-i][n-1-j]$ 的位置，在轮转 270 度后会出现在 $\text{mat}[n-1-j][i]$ 的位置。

因此，我们可以用一个整数 $\textit{ok}$ 来记录当前轮转的状态，初始值为 $0b1111$，表示四种轮转状态都可能。对于矩阵中的每个元素，我们比较它在不同轮转状态下的位置与目标矩阵中的对应位置的元素是否相等，如果不相等，则将对应的轮转状态从 $\textit{ok}$ 中去掉。最后，如果 $\textit{ok}$ 不为零，说明至少有一种轮转状态可以使矩阵与目标矩阵一致，返回 $\textit{true}$；否则，返回 $\textit{false}$。

时间复杂度 $O(n^2)$，其中 $n$ 是矩阵的大小。空间复杂度 $O(1)$。

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
