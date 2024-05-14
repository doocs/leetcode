# [2946. 循环移位后的矩阵相似检查](https://leetcode.cn/problems/matrix-similarity-after-cyclic-shifts)

[English Version](/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/README_EN.md)

<!-- tags:数组,数学,矩阵,模拟 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个<strong>下标从 0 开始</strong>且大小为 <code>m x n</code> 的整数矩阵 <code>mat</code> 和一个整数 <code>k</code> 。请你将矩阵中的<strong> 奇数</strong> 行循环 <strong>右</strong> 移 <code>k</code> 次，<strong>偶数</strong> 行循环 <strong>左</strong> 移 <code>k</code> 次。</p>

<p>如果初始矩阵和最终矩阵完全相同，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2
<strong>输出：</strong>true
<strong>解释：</strong>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2946.Matrix%20Similarity%20After%20Cyclic%20Shifts/images/similarmatrix.png" style="width: 500px; height: 117px;" />

初始矩阵如图一所示。
图二表示对奇数行右移一次且对偶数行左移一次后的矩阵状态。
图三是经过两次循环移位后的最终矩阵状态，与初始矩阵相同。
因此，返回 true 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[2,2],[2,2]], k = 3
<strong>输出：</strong>true
<strong>解释：</strong>由于矩阵中的所有值都相等，即使进行循环移位，矩阵仍然保持不变。因此，返回 true 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2]], k = 1
<strong>输出：</strong>false
<strong>解释：</strong>循环移位一次后，mat = [[2,1]]，与初始矩阵不相等。因此，返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= mat.length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i].length &lt;= 25</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 25</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- end -->
