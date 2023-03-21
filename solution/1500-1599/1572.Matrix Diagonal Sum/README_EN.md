# [1572. Matrix Diagonal Sum](https://leetcode.com/problems/matrix-diagonal-sum)

[中文文档](/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/README.md)

## Description

<p>Given a&nbsp;square&nbsp;matrix&nbsp;<code>mat</code>, return the sum of the matrix diagonals.</p>

<p>Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/images/sample_1911.png" style="width: 336px; height: 174px;" />
<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;             [4,<strong>5</strong>,6],
&nbsp;             [<strong>7</strong>,8,<strong>9</strong>]]
<strong>Output:</strong> 25
<strong>Explanation: </strong>Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>Output:</strong> 8
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>5</strong>]]
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == mat.length == mat[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def diagonalSum(self, mat: List[List[int]]) -> int:
        ans = 0
        n = len(mat)
        for i, row in enumerate(mat):
            j = n - i - 1
            ans += row[i] + (0 if j == i else row[j])
        return ans
```

### **Java**

```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int ans = 0;
        int n = mat.length;
        for (int i = 0; i < n; ++i) {
            int j = n - i - 1;
            ans += mat[i][i] + (i == j ? 0 : mat[i][j]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int diagonalSum(vector<vector<int>>& mat) {
        int ans = 0;
        int n = mat.size();
        for (int i = 0; i < n; ++i) {
            int j = n - i - 1;
            ans += mat[i][i] + (i == j ? 0 : mat[i][j]);
        }
        return ans;
    }
};
```

### **Go**

```go
func diagonalSum(mat [][]int) (ans int) {
	n := len(mat)
	for i, row := range mat {
		ans += row[i]
		if j := n - i - 1; j != i {
			ans += row[j]
		}
	}
	return
}
```

### **TypeScript**

```ts
function diagonalSum(mat: number[][]): number {
    const n = mat.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += mat[i][i] + mat[i][n - 1 - i];
    }
    if (n & 1) {
        ans -= mat[n >> 1][n >> 1];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut ans = 0;
        for i in 0..n {
            ans += mat[i][i] + mat[n - 1 - i][i];
        }
        if n & 1 == 1 {
            ans -= mat[n >> 1][n >> 1];
        }
        ans
    }
}
```

### **C**

```c
int diagonalSum(int **mat, int matSize, int *matColSize) {
    int ans = 0;
    for (int i = 0; i < matSize; i++) {
        ans += mat[i][i] + mat[i][matSize - 1 - i];
    }
    if (matSize & 1) {
        ans -= mat[matSize >> 1][matSize >> 1];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
