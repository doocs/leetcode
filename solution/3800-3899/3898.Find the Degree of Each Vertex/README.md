---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/README.md
---

<!-- problem:start -->

# [3898. 统计每个顶点的度](https://leetcode.cn/problems/find-the-degree-of-each-vertex)

[English Version](/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n x n</code> 的二维整数数组 <code>matrix</code>，以邻接矩阵形式表示一个&nbsp;<strong>无向图</strong>。该图包含 <code>n</code> 个顶点，编号从 0 到 <code>n - 1</code>。</p>

<ul>
	<li><code>matrix[i][j] = 1</code> 表示顶点 <code>i</code> 与顶点 <code>j</code> 之间存在一条边。</li>
	<li><code>matrix[i][j] = 0</code> 表示顶点 <code>i</code> 与顶点 <code>j</code> 之间不存在边。</li>
</ul>

<p>顶点的&nbsp;<strong>度（degree）</strong>定义为与该顶点相连的边的数量。</p>

<p>请返回一个长度为 <code>n</code> 的整数数组 <code>ans</code>，其中 <code>ans[i]</code> 表示顶点 <code>i</code> 的度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/images/g41f.png" style="width: 180px; height: 142px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">matrix = [[0,1,1],[1,0,1],[1,1,0]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,2,2]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>顶点 0 与顶点 1 和 2 相连，因此其度为 2。</li>
	<li>顶点 1 与顶点 0 和 2 相连，因此其度为 2。</li>
	<li>顶点 2 与顶点 0 和 1 相连，因此其度为 2。</li>
</ul>

<p>因此，答案为 <code>[2, 2, 2]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/images/g42f.png" style="width: 180px; height: 145px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">matrix = [[0,1,0],[1,0,0],[0,0,0]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,1,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>顶点 0 与顶点 1 相连，因此其度为 1。</li>
	<li>顶点 1 与顶点 0 相连，因此其度为 1。</li>
	<li>顶点 2 没有与任何顶点相连，因此其度为 0。</li>
</ul>

<p>因此，答案为 <code>[1, 1, 0]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">matrix = [[0]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0]</span></p>

<p><strong>解释：</strong></p>

<p>图中只有一个顶点，且没有任何边与其相连，因此答案为 <code>[0]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == matrix.length == matrix[i].length &lt;= 100</code></li>
	<li><code>matrix[i][i] == 0</code></li>
	<li><code>matrix[i][j]</code> 仅为 0 或 1</li>
	<li><code>matrix[i][j] == matrix[j][i]</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟计算每个顶点的度。

对于每个顶点 $i$，我们遍历其对应的行 $\text{matrix}[i]$，统计其中值为 1 的元素的数量，即为顶点 $i$ 的度。

时间复杂度 $O(n^2)$，其中 $n$ 是图中顶点的数量。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findDegrees(self, matrix: list[list[int]]) -> list[int]:
        ans = [0] * len(matrix)
        for i, row in enumerate(matrix):
            for x in row:
                ans[i] += x
        return ans
```

#### Java

```java
class Solution {
    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int x : matrix[i]) {
                ans[i] += x;
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
    vector<int> findDegrees(vector<vector<int>>& matrix) {
        int n = matrix.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            for (int x : matrix[i]) {
                ans[i] += x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findDegrees(matrix [][]int) []int {
	ans := make([]int, len(matrix))
	for i, row := range matrix {
		for _, x := range row {
			ans[i] += x
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findDegrees(matrix: number[][]): number[] {
    const n = matrix.length;
    const ans: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (const x of matrix[i]) {
            ans[i] += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
