---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/README.md
rating: 1405
source: 第 373 场周赛 Q1
tags:
    - 数组
    - 数学
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [2946. 循环移位后的矩阵相似检查](https://leetcode.cn/problems/matrix-similarity-after-cyclic-shifts)

[English Version](/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个<strong>下标从 0 开始</strong>且大小为 <code>m x n</code> 的整数矩阵 <code>mat</code> 和一个整数 <code>k</code> 。矩阵行的下标是从 0 开始的。</p>

<p>进行下面操作&nbsp;<code>k</code> 次：</p>

<ul>
	<li><strong>偶数行</strong>（0, 2, 4, ...）循环向左移动。</li>
</ul>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/lshift.jpg" style="width: 283px; height: 90px;" /></p>

<ul>
	<li><strong>奇数行</strong>（1, 3, 5, ...）循环向右移动。</li>
</ul>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/rshift-stlone.jpg" style="width: 283px; height: 90px;" /></p>

<p>如果经过 <code>k</code> 步后的最终修改矩阵与原始矩阵相同，则返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>mat = [[1,2,3],[4,5,6],[7,8,9]], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><strong>解释：</strong></p>

<p>在每一步中，行 0 和行 2（偶数下标）进行左移，行 1（奇数下标）进行右移。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/t1-2.jpg" style="width: 857px; height: 150px;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/t1-3.jpg" style="width: 632px; height: 150px;" /></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>mat = [[2,2],[2,2]], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p>矩阵中的所有值都相等，即使进行循环移位，矩阵也会保持不变。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= mat.length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i].length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历矩阵的每个元素，判断其在循环移位后的位置是否与原位置的元素相同。

对于奇数行，我们将元素向右移动 $k$ 个位置，因此元素 $(i, j)$ 在循环移位后的位置为 $(i, (j + k) \bmod n)$，其中 $n$ 是矩阵的列数。

对于偶数行，我们将元素向左移动 $k$ 个位置，因此元素 $(i, j)$ 在循环移位后的位置为 $(i, (j - k + n) \bmod n)$。

如果在遍历过程中发现有任何一个元素在循环移位后的位置与原位置的元素不同，则返回 $\text{false}$。如果遍历完成后所有元素都相同，则返回 $\text{true}$。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$。

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

#### Rust

```rust
impl Solution {
    pub fn are_similar(mat: Vec<Vec<i32>>, k: i32) -> bool {
        let m = mat.len();
        let n = mat[0].len();
        let k = (k as usize) % n;

        for i in 0..m {
            for j in 0..n {
                if i % 2 == 1 && mat[i][j] != mat[i][(j + k) % n] {
                    return false;
                }
                if i % 2 == 0 && mat[i][j] != mat[i][(j + n - k) % n] {
                    return false;
                }
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
