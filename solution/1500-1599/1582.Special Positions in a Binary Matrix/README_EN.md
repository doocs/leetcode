# [1582. Special Positions in a Binary Matrix](https://leetcode.com/problems/special-positions-in-a-binary-matrix)

[中文文档](/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/README.md)

## Description

<p>Given an <code>m x n</code> binary matrix <code>mat</code>, return <em>the number of special positions in </em><code>mat</code><em>.</em></p>

<p>A position <code>(i, j)</code> is called <strong>special</strong> if <code>mat[i][j] == 1</code> and all other elements in row <code>i</code> and column <code>j</code> are <code>0</code> (rows and columns are <strong>0-indexed</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/images/special1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> mat = [[1,0,0],[0,0,1],[1,0,0]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/images/special-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> mat = [[1,0,0],[0,1,0],[0,0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> (0, 0), (1, 1) and (2, 2) are special positions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSpecial(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        r = [0] * m
        c = [0] * n
        for i, row in enumerate(mat):
            for j, v in enumerate(row):
                r[i] += v
                c[j] += v
        ans = 0
        for i in range(m):
            for j in range(n):
                if mat[i][j] == 1 and r[i] == 1 and c[j] == 1:
                    ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] r = new int[m];
        int[] c = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                r[i] += mat[i][j];
                c[j] += mat[i][j];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1 && r[i] == 1 && c[j] == 1) {
                    ++ans;
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
    int numSpecial(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> r(m), c(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                r[i] += mat[i][j];
                c[j] += mat[i][j];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1 && r[i] == 1 && c[j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numSpecial(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	r, c := make([]int, m), make([]int, n)
	for i, row := range mat {
		for j, v := range row {
			r[i] += v
			c[j] += v
		}
	}
	ans := 0
	for i, x := range r {
		for j, y := range c {
			if mat[i][j] == 1 && x == 1 && y == 1 {
				ans++
			}
		}
	}
	return ans
}
```

### **C**

```c
int numSpecial(int **mat, int matSize, int *matColSize) {
    int m = matSize;
    int n = *matColSize;
    int *rows = (int *) malloc(sizeof(int) * m);
    int *cols = (int *) malloc(sizeof(int) * n);
    memset(rows, 0, sizeof(int) * m);
    memset(cols, 0, sizeof(int) * n);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    int res = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) {
                res++;
            }
        }
    }
    free(rows);
    free(cols);
    return res;
}
```

### **TypeScript**

```ts
function numSpecial(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const rows = new Array(m).fill(0);
    const cols = new Array(n).fill(0);
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] === 1) {
                rows[i]++;
                cols[j]++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j] === 1 && rows[i] === 1 && cols[j] === 1) {
                res++;
            }
        }
    }

    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn num_special(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut rows = vec![0; m];
        let mut cols = vec![0; n];
        for i in 0..m {
            for j in 0..n {
                rows[i] += mat[i][j];
                cols[j] += mat[i][j];
            }
        }

        let mut res = 0;
        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1 {
                    res += 1;
                }
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
