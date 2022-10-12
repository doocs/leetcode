# [2133. Check if Every Row and Column Contains All Numbers](https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers)

[中文文档](/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkValid(self, matrix: List[List[int]]) -> bool:
        n = len(matrix)
        for i in range(n):
            seen = [False] * n
            for j in range(n):
                v = matrix[i][j] - 1
                if seen[v]:
                    return False
                seen[v] = True
        for j in range(n):
            seen = [False] * n
            for i in range(n):
                v = matrix[i][j] - 1
                if seen[v]:
                    return False
                seen[v] = True
        return True
```

### **Java**

```java
class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            boolean[] seen = new boolean[n];
            for (int j = 0; j < n; ++j) {
                int v = matrix[i][j] - 1;
                if (seen[v]) {
                    return false;
                }
                seen[v] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            boolean[] seen = new boolean[n];
            for (int i = 0; i < n; ++i) {
                int v = matrix[i][j] - 1;
                if (seen[v]) {
                    return false;
                }
                seen[v] = true;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function checkValid(matrix: number[][]): boolean {
    const n = matrix.length;
    let rows = Array.from({ length: n }, () => new Array(n).fill(false));
    let cols = Array.from({ length: n }, () => new Array(n).fill(false));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            let cur = matrix[i][j];
            if (rows[i][cur] || cols[j][cur]) return false;
            rows[i][cur] = true;
            cols[j][cur] = true;
        }
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkValid(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 0; i < n; ++i) {
            vector<bool> seen(n);
            for (int j = 0; j < n; ++j) {
                int v = matrix[i][j] - 1;
                if (seen[v]) return false;
                seen[v] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            vector<bool> seen(n);
            for (int i = 0; i < n; ++i) {
                int v = matrix[i][j] - 1;
                if (seen[v]) return false;
                seen[v] = true;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkValid(matrix [][]int) bool {
	n := len(matrix)
	for i := 0; i < n; i++ {
		seen := make([]bool, n)
		for j := 0; j < n; j++ {
			v := matrix[i][j] - 1
			if seen[v] {
				return false
			}
			seen[v] = true
		}
	}
	for j := 0; j < n; j++ {
		seen := make([]bool, n)
		for i := 0; i < n; i++ {
			v := matrix[i][j] - 1
			if seen[v] {
				return false
			}
			seen[v] = true
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
