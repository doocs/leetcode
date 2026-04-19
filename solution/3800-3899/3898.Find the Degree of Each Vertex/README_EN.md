---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/README_EN.md
rating: 1202
source: Weekly Contest 497 Q1
---

<!-- problem:start -->

# [3898. Find the Degree of Each Vertex](https://leetcode.com/problems/find-the-degree-of-each-vertex)

[中文文档](/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>matrix</code> of size <code>n x n</code> representing the adjacency matrix of an undirected graph with <code>n</code> vertices labeled from 0 to <code>n - 1</code>.</p>

<ul>
	<li><code>matrix[i][j] = 1</code> indicates that there is an edge between vertices <code>i</code> and <code>j</code>.</li>
	<li><code>matrix[i][j] = 0</code> indicates that there is no edge between vertices <code>i</code> and <code>j</code>.</li>
</ul>

<p>The <strong>degree</strong> of a vertex is the number of edges connected to it.</p>

<p>Return an integer array <code>ans</code> of size <code>n</code> where <code>ans[i]</code> represents the degree of vertex <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/images/g41f.png" style="width: 180px; height: 142px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[0,1,1],[1,0,1],[1,1,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Vertex 0 is connected to vertices 1 and 2, so its degree is 2.</li>
	<li>Vertex 1 is connected to vertices 0 and 2, so its degree is 2.</li>
	<li>Vertex 2 is connected to vertices 0 and 1, so its degree is 2.</li>
</ul>

<p>Thus, the answer is <code>[2, 2, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3800-3899/3898.Find%20the%20Degree%20of%20Each%20Vertex/images/g42f.png" style="width: 180px; height: 145px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[0,1,0],[1,0,0],[0,0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Vertex 0 is connected to vertex 1, so its degree is 1.</li>
	<li>Vertex 1 is connected to vertex 0, so its degree is 1.</li>
	<li>Vertex 2 is not connected to any vertex, so its degree is 0.</li>
</ul>

<p>Thus, the answer is <code>[1, 1, 0]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="1129" data-start="1068">There is only one vertex and it has no edges connected to it. Thus, the answer is <code data-end="1156" data-start="1151">[0]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == matrix.length == matrix[i].length &lt;= 100</code>​​​​​​​</li>
	<li><code>​​​​​​​matrix[i][i] == 0</code></li>
	<li><code>matrix[i][j]</code> is either 0 or 1</li>
	<li><code>matrix[i][j] == matrix[j][i]</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the process of computing the degree of each vertex.

For each vertex $i$, we traverse its corresponding row $\text{matrix}[i]$ and count the number of elements equal to 1, which is exactly the degree of vertex $i$.

The time complexity is $O(n^2)$, where $n$ is the number of vertices in the graph. Ignoring the space consumed by the answer array, the space complexity is $O(1)$.

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
