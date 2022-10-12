# [1380. Lucky Numbers in a Matrix](https://leetcode.com/problems/lucky-numbers-in-a-matrix)

[中文文档](/solution/1300-1399/1380.Lucky%20Numbers%20in%20a%20Matrix/README.md)

## Description

<p>Given an <code>m x n</code> matrix of <strong>distinct </strong>numbers, return <em>all <strong>lucky numbers</strong> in the matrix in <strong>any </strong>order</em>.</p>

<p>A <strong>lucky number</strong> is an element of the matrix such that it is the minimum element in its row and maximum in its column.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[3,7,8],[9,11,13],[15,16,17]]
<strong>Output:</strong> [15]
<strong>Explanation:</strong> 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
<strong>Output:</strong> [12]
<strong>Explanation:</strong> 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[7,8],[1,2]]
<strong>Output:</strong> [7]
<strong>Explanation:</strong> 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 50</code></li>
	<li><code>1 &lt;= matrix[i][j] &lt;= 10<sup>5</sup></code>.</li>
	<li>All elements in the matrix are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def luckyNumbers(self, matrix: List[List[int]]) -> List[int]:
        rows = {min(row) for row in matrix}
        cols = {max(col) for col in zip(*matrix)}
        return list(rows & cols)
```

### **Java**

```java
class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        Arrays.fill(rows, Integer.MAX_VALUE);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = Math.min(rows[i], matrix[i][j]);
                cols[j] = Math.max(cols[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] == cols[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> luckyNumbers(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<int> rows(m, INT_MAX);
        vector<int> cols(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = min(rows[i], matrix[i][j]);
                cols[j] = max(cols[j], matrix[i][j]);
            }
        }
        vector<int> ans;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (rows[i] == cols[j])
                    ans.push_back(matrix[i][j]);
        return ans;
    }
};
```

### **Go**

```go
func luckyNumbers (matrix [][]int) []int {
    m, n := len(matrix), len(matrix[0])
    rows, cols := make([]int, m), make([]int, n)
    for i := range rows {
        rows[i] = math.MaxInt32
    }
    for i, row := range matrix {
        for j, v := range row {
            rows[i] = min(rows[i], v)
            cols[j] = max(cols[j], v)
        }
    }
    var ans []int
    for i, row := range matrix {
        for j, v := range row {
            if rows[i] == cols[j] {
                ans = append(ans, v)
            }
        }
    }
    return ans
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
```

### **TypeScript**

```ts
function luckyNumbers(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const col = new Array(n).fill(0);
    const res = [];
    for (let j = 0; j < n; j++) {
        for (let i = 0; i < m; i++) {
            col[j] = Math.max(col[j], matrix[i][j]);
        }
    }
    for (let x = 0; x < m; x++) {
        let i = 0;
        for (let y = 1; y < n; y++) {
            if (matrix[x][i] > matrix[x][y]) {
                i = y;
            }
        }
        if (matrix[x][i] === col[i]) {
            res.push(col[i]);
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn lucky_numbers(matrix: Vec<Vec<i32>>) -> Vec<i32> {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut res = vec![];
        let mut col = vec![0; n];
        for j in 0..n {
            for i in 0..m {
                col[j] = col[j].max(matrix[i][j]);
            }
        }
        for x in 0..m {
            let mut i = 0;
            for y in 1..n {
                if matrix[x][y] < matrix[x][i] {
                    i = y;
                }
            }
            if matrix[x][i] == col[i] {
                res.push(col[i]);
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
