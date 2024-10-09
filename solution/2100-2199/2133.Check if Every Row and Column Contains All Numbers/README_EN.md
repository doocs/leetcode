---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/README_EN.md
rating: 1264
source: Weekly Contest 275 Q1
tags:
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [2133. Check if Every Row and Column Contains All Numbers](https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers)

[中文文档](/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/README.md)

## Description

<!-- description:start -->

<p>An <code>n x n</code> matrix is <strong>valid</strong> if every row and every column contains <strong>all</strong> the integers from <code>1</code> to <code>n</code> (<strong>inclusive</strong>).</p>

<p>Given an <code>n x n</code> integer matrix <code>matrix</code>, return <code>true</code> <em>if the matrix is <strong>valid</strong>.</em> Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/images/example1drawio.png" style="width: 250px; height: 251px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[3,1,2],[2,3,1]]
<strong>Output:</strong> true
<strong>Explanation:</strong> In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
Hence, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/images/example2drawio.png" style="width: 250px; height: 251px;" />
<pre>
<strong>Input:</strong> matrix = [[1,1,1],[1,2,3],[1,2,3]]
<strong>Output:</strong> false
<strong>Explanation:</strong> In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
Hence, we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= matrix[i][j] &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

Traverse each row and column of the matrix, using a hash table to record whether each number has appeared. If any number appears more than once in a row or column, return `false`; otherwise, return `true`

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the size of the matrix.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkValid(self, matrix: List[List[int]]) -> bool:
        n = len(matrix)
        return all(len(set(row)) == n for row in chain(matrix, zip(*matrix)))
```

#### Java

```java
class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        boolean[] vis = new boolean[n + 1];
        for (var row : matrix) {
            Arrays.fill(vis, false);
            for (int x : row) {
                if (vis[x]) {
                    return false;
                }
                vis[x] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            Arrays.fill(vis, false);
            for (int i = 0; i < n; ++i) {
                if (vis[matrix[i][j]]) {
                    return false;
                }
                vis[matrix[i][j]] = true;
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
    bool checkValid(vector<vector<int>>& matrix) {
        int n = matrix.size();
        bool vis[n + 1];
        for (const auto& row : matrix) {
            memset(vis, false, sizeof(vis));
            for (int x : row) {
                if (vis[x]) {
                    return false;
                }
                vis[x] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            memset(vis, false, sizeof(vis));
            for (int i = 0; i < n; ++i) {
                if (vis[matrix[i][j]]) {
                    return false;
                }
                vis[matrix[i][j]] = true;
            }
        }
        return true;
    }
};
```

#### Go

```go
func checkValid(matrix [][]int) bool {
	n := len(matrix)
	for _, row := range matrix {
		vis := make([]bool, n+1)
		for _, x := range row {
			if vis[x] {
				return false
			}
			vis[x] = true
		}
	}
	for j := 0; j < n; j++ {
		vis := make([]bool, n+1)
		for i := 0; i < n; i++ {
			if vis[matrix[i][j]] {
				return false
			}
			vis[matrix[i][j]] = true
		}
	}
	return true
}
```

#### TypeScript

```ts
function checkValid(matrix: number[][]): boolean {
    const n = matrix.length;
    const vis: boolean[] = Array(n + 1).fill(false);
    for (const row of matrix) {
        vis.fill(false);
        for (const x of row) {
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
        }
    }
    for (let j = 0; j < n; ++j) {
        vis.fill(false);
        for (let i = 0; i < n; ++i) {
            if (vis[matrix[i][j]]) {
                return false;
            }
            vis[matrix[i][j]] = true;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
