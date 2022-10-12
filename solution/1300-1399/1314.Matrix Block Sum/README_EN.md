# [1314. Matrix Block Sum](https://leetcode.com/problems/matrix-block-sum)

[中文文档](/solution/1300-1399/1314.Matrix%20Block%20Sum/README.md)

## Description

<p>Given a <code>m x n</code> matrix <code>mat</code> and an integer <code>k</code>, return <em>a matrix</em> <code>answer</code> <em>where each</em> <code>answer[i][j]</code> <em>is the sum of all elements</em> <code>mat[r][c]</code> <em>for</em>:</p>

<ul>
	<li><code>i - k &lt;= r &lt;= i + k,</code></li>
	<li><code>j - k &lt;= c &lt;= j + k</code>, and</li>
	<li><code>(r, c)</code> is a valid position in the matrix.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
<strong>Output:</strong> [[12,21,16],[27,45,33],[24,39,28]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
<strong>Output:</strong> [[45,45,45],[45,45,45],[45,45,45]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;mat.length</code></li>
	<li><code>n ==&nbsp;mat[i].length</code></li>
	<li><code>1 &lt;= m, n, k &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

## Solutions

Dynamic programming - 2D preSum.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def matrixBlockSum(self, mat: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        pre = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                pre[i][j] = (
                    pre[i - 1][j]
                    + pre[i][j - 1]
                    - pre[i - 1][j - 1]
                    + mat[i - 1][j - 1]
                )

        def get(i, j):
            i = max(min(m, i), 0)
            j = max(min(n, j), 0)
            return pre[i][j]

        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                ans[i][j] = (
                    get(i + k + 1, j + k + 1)
                    - get(i + k + 1, j - k)
                    - get(i - k, j + k + 1)
                    + get(i - k, j - k)
                )
        return ans
```

### **Java**

```java
class Solution {
    private int[][] pre;
    private int m;
    private int n;
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + -pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        this.pre = pre;
        this.m = m;
        this.n = n;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = get(i + k + 1, j + k + 1) - get(i + k + 1, j - k)
                    - get(i - k, j + k + 1) + get(i - k, j - k);
            }
        }
        return ans;
    }

    private int get(int i, int j) {
        i = Math.max(Math.min(m, i), 0);
        j = Math.max(Math.min(n, j), 0);
        return pre[i][j];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> matrixBlockSum(vector<vector<int>>& mat, int k) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> pre(m + 1, vector<int>(n + 1));
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + -pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = get(i + k + 1, j + k + 1, m, n, pre) - get(i + k + 1, j - k, m, n, pre) - get(i - k, j + k + 1, m, n, pre) + get(i - k, j - k, m, n, pre);
            }
        }
        return ans;
    }

    int get(int i, int j, int m, int n, vector<vector<int>>& pre) {
        i = max(min(m, i), 0);
        j = max(min(n, j), 0);
        return pre[i][j];
    }
};
```

### **Go**

```go
func matrixBlockSum(mat [][]int, k int) [][]int {
	m, n := len(mat), len(mat[0])
	pre := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		pre[i] = make([]int, n+1)
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			pre[i][j] = pre[i-1][j] + pre[i][j-1] + -pre[i-1][j-1] + mat[i-1][j-1]
		}
	}

	get := func(i, j int) int {
		i = max(min(m, i), 0)
		j = max(min(n, j), 0)
		return pre[i][j]
	}

	ans := make([][]int, m)
	for i := 0; i < m; i++ {
		ans[i] = make([]int, n)
		for j := 0; j < n; j++ {
			ans[i][j] = get(i+k+1, j+k+1) - get(i+k+1, j-k) - get(i-k, j+k+1) + get(i-k, j-k)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
