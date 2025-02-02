---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0598.Range%20Addition%20II/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [598. Range Addition II](https://leetcode.com/problems/range-addition-ii)

[中文文档](/solution/0500-0599/0598.Range%20Addition%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>M</code> initialized with all <code>0</code>&#39;s and an array of operations <code>ops</code>, where <code>ops[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> means <code>M[x][y]</code> should be incremented by one for all <code>0 &lt;= x &lt; a<sub>i</sub></code> and <code>0 &lt;= y &lt; b<sub>i</sub></code>.</p>

<p>Count and return <em>the number of maximum integers in the matrix after performing all the operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0598.Range%20Addition%20II/images/ex1.jpg" style="width: 750px; height: 176px;" />
<pre>
<strong>Input:</strong> m = 3, n = 3, ops = [[2,2],[3,3]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The maximum integer in M is 2, and there are four of it in M. So return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 3, ops = [[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3]]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 3, ops = []
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= ops.length &lt;= 10<sup>4</sup></code></li>
	<li><code>ops[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub> &lt;= m</code></li>
	<li><code>1 &lt;= b<sub>i</sub> &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

We notice that the intersection of all operation submatrices is the submatrix where the final maximum integer is located, and each operation submatrix starts from the top-left corner $(0, 0)$. Therefore, we traverse all operation submatrices to find the minimum number of rows and columns. Finally, we return the product of these two values.

Note that if the operation array is empty, the number of maximum integers in the matrix is $m \times n$.

The time complexity is $O(k)$, where $k$ is the length of the operation array $\textit{ops}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCount(self, m: int, n: int, ops: List[List[int]]) -> int:
        for a, b in ops:
            m = min(m, a)
            n = min(n, b)
        return m * n
```

#### Java

```java
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCount(int m, int n, vector<vector<int>>& ops) {
        for (const auto& op : ops) {
            m = min(m, op[0]);
            n = min(n, op[1]);
        }
        return m * n;
    }
};
```

#### Go

```go
func maxCount(m int, n int, ops [][]int) int {
	for _, op := range ops {
		m = min(m, op[0])
		n = min(n, op[1])
	}
	return m * n
}
```

#### TypeScript

```ts
function maxCount(m: number, n: number, ops: number[][]): number {
    for (const [a, b] of ops) {
        m = Math.min(m, a);
        n = Math.min(n, b);
    }
    return m * n;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_count(mut m: i32, mut n: i32, ops: Vec<Vec<i32>>) -> i32 {
        for op in ops {
            m = m.min(op[0]);
            n = n.min(op[1]);
        }
        m * n
    }
}
```

#### JavaScript

```js
/**
 * @param {number} m
 * @param {number} n
 * @param {number[][]} ops
 * @return {number}
 */
var maxCount = function (m, n, ops) {
    for (const [a, b] of ops) {
        m = Math.min(m, a);
        n = Math.min(n, b);
    }
    return m * n;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
