---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1605.Find%20Valid%20Matrix%20Given%20Row%20and%20Column%20Sums/README_EN.md
rating: 1867
source: Biweekly Contest 36 Q3
tags:
    - Greedy
    - Array
    - Matrix
---

<!-- problem:start -->

# [1605. Find Valid Matrix Given Row and Column Sums](https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums)

[中文文档](/solution/1600-1699/1605.Find%20Valid%20Matrix%20Given%20Row%20and%20Column%20Sums/README.md)

## Description

<!-- description:start -->

<p>You are given two arrays <code>rowSum</code> and <code>colSum</code> of non-negative integers where <code>rowSum[i]</code> is the sum of the elements in the <code>i<sup>th</sup></code> row and <code>colSum[j]</code> is the sum of the elements of the <code>j<sup>th</sup></code> column of a 2D matrix. In other words, you do not know the elements of the matrix, but you do know the sums of each row and column.</p>

<p>Find any matrix of <strong>non-negative</strong> integers of size <code>rowSum.length x colSum.length</code> that satisfies the <code>rowSum</code> and <code>colSum</code> requirements.</p>

<p>Return <em>a 2D array representing <strong>any</strong> matrix that fulfills the requirements</em>. It&#39;s guaranteed that <strong>at least one </strong>matrix that fulfills the requirements exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rowSum = [3,8], colSum = [4,7]
<strong>Output:</strong> [[3,0],
         [1,7]]
<strong>Explanation:</strong> 
0<sup>th</sup> row: 3 + 0 = 3 == rowSum[0]
1<sup>st</sup> row: 1 + 7 = 8 == rowSum[1]
0<sup>th</sup> column: 3 + 1 = 4 == colSum[0]
1<sup>st</sup> column: 0 + 7 = 7 == colSum[1]
The row and column sums match, and all matrix elements are non-negative.
Another possible matrix is: [[1,2],
                             [3,5]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rowSum = [5,7,10], colSum = [8,6,8]
<strong>Output:</strong> [[0,5,0],
         [6,1,0],
         [2,0,8]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rowSum.length, colSum.length &lt;= 500</code></li>
	<li><code>0 &lt;= rowSum[i], colSum[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>sum(rowSum) == sum(colSum)</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Construction

We can first initialize an $m$ by $n$ answer matrix $ans$.

Next, we traverse each position $(i, j)$ in the matrix, set the element at this position to $x = \min(rowSum[i], colSum[j])$, and subtract $x$ from $rowSum[i]$ and $colSum[j]$ respectively. After traversing all positions, we can get a matrix $ans$ that meets the requirements of the problem.

The correctness of the above strategy is explained as follows:

According to the requirements of the problem, we know that the sum of $rowSum$ and $colSum$ is equal, so $rowSum[0]$ must be less than or equal to $\sum_{j = 0}^{n - 1} colSum[j]$. Therefore, after $n$ operations, $rowSum[0]$ can definitely be made $0$, and for any $j \in [0, n - 1]$, $colSum[j] \geq 0$ is guaranteed.

Therefore, we reduce the original problem to a subproblem with $m-1$ rows and $n$ columns, continue the above operations, until all elements in $rowSum$ and $colSum$ are $0$, we can get a matrix $ans$ that meets the requirements of the problem.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the lengths of $rowSum$ and $colSum$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def restoreMatrix(self, rowSum: List[int], colSum: List[int]) -> List[List[int]]:
        m, n = len(rowSum), len(colSum)
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                x = min(rowSum[i], colSum[j])
                ans[i][j] = x
                rowSum[i] -= x
                colSum[j] -= x
        return ans
```

```java
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = Math.min(rowSum[i], colSum[j]);
                ans[i][j] = x;
                rowSum[i] -= x;
                colSum[j] -= x;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> restoreMatrix(vector<int>& rowSum, vector<int>& colSum) {
        int m = rowSum.size(), n = colSum.size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = min(rowSum[i], colSum[j]);
                ans[i][j] = x;
                rowSum[i] -= x;
                colSum[j] -= x;
            }
        }
        return ans;
    }
};
```

```go
func restoreMatrix(rowSum []int, colSum []int) [][]int {
	m, n := len(rowSum), len(colSum)
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := range rowSum {
		for j := range colSum {
			x := min(rowSum[i], colSum[j])
			ans[i][j] = x
			rowSum[i] -= x
			colSum[j] -= x
		}
	}
	return ans
}
```

```ts
function restoreMatrix(rowSum: number[], colSum: number[]): number[][] {
    const m = rowSum.length;
    const n = colSum.length;
    const ans = Array.from(new Array(m), () => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = Math.min(rowSum[i], colSum[j]);
            ans[i][j] = x;
            rowSum[i] -= x;
            colSum[j] -= x;
        }
    }
    return ans;
}
```

```js
/**
 * @param {number[]} rowSum
 * @param {number[]} colSum
 * @return {number[][]}
 */
var restoreMatrix = function (rowSum, colSum) {
    const m = rowSum.length;
    const n = colSum.length;
    const ans = Array.from(new Array(m), () => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = Math.min(rowSum[i], colSum[j]);
            ans[i][j] = x;
            rowSum[i] -= x;
            colSum[j] -= x;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
