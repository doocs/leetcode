# [1572. Matrix Diagonal Sum](https://leetcode.com/problems/matrix-diagonal-sum)

[中文文档](/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/README.md)

## Description

<p>Given a&nbsp;square&nbsp;matrix&nbsp;<code>mat</code>, return the sum of the matrix diagonals.</p>

<p>Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/images/sample_1911.png" style="width: 336px; height: 174px;" />
<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;             [4,<strong>5</strong>,6],
&nbsp;             [<strong>7</strong>,8,<strong>9</strong>]]
<strong>Output:</strong> 25
<strong>Explanation: </strong>Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;             [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>Output:</strong> 8
</pre>

<p><strong>Example 3:</strong></p>

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
        n = len(mat)
        res = 0
        for i in range(n):
            res += mat[i][i] + (0 if n - i - 1 == i else mat[i][n - i - 1])
        return res
```

### **Java**

```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += mat[i][i] + (n - i - 1 == i ? 0 : mat[i][n - i - 1]);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function diagonalSum(mat: number[][]): number {
    let n = mat.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += mat[i][i];
        let j = n - 1 - i;
        if (i != j) {
            ans += mat[i][j];
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int diagonalSum(vector<vector<int>>& mat) {
        int n = mat.size();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += mat[i][i] + (n - i - 1 == i ? 0 : mat[i][n - i - 1]);
        }
        return res;
    }
};
```

### **Go**

```go
func diagonalSum(mat [][]int) int {
	n, res := len(mat), 0
	for i := 0; i < n; i++ {
		res += mat[i][i]
		if n-i-1 != i {
			res += mat[i][n-i-1]
		}
	}
	return res
}
```

### **Rust**

```rust
impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut res = 0;
        for i in 0..n {
            res += mat[i][i] + mat[n - i - 1][i];
        }
        if n & 1 == 1 {
            return res - mat[n / 2][n / 2];
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
