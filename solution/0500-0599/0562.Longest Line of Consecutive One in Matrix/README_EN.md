# [562. Longest Line of Consecutive One in Matrix](https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix)

[中文文档](/solution/0500-0599/0562.Longest%20Line%20of%20Consecutive%20One%20in%20Matrix/README.md)

## Description

<p>Given an <code>m x n</code> binary matrix <code>mat</code>, return <em>the length of the longest line of consecutive one in the matrix</em>.</p>

<p>The line could be horizontal, vertical, diagonal, or anti-diagonal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0562.Longest%20Line%20of%20Consecutive%20One%20in%20Matrix/images/long1-grid.jpg" style="width: 333px; height: 253px;" />
<pre>
<strong>Input:</strong> mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0562.Longest%20Line%20of%20Consecutive%20One%20in%20Matrix/images/long2-grid.jpg" style="width: 333px; height: 253px;" />
<pre>
<strong>Input:</strong> mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestLine(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        a = [[0] * (n + 2) for _ in range(m + 2)]
        b = [[0] * (n + 2) for _ in range(m + 2)]
        c = [[0] * (n + 2) for _ in range(m + 2)]
        d = [[0] * (n + 2) for _ in range(m + 2)]
        ans = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if mat[i - 1][j - 1]:
                    a[i][j] = a[i - 1][j] + 1
                    b[i][j] = b[i][j - 1] + 1
                    c[i][j] = c[i - 1][j - 1] + 1
                    d[i][j] = d[i - 1][j + 1] + 1
                    ans = max(ans, a[i][j], b[i][j], c[i][j], d[i][j])
        return ans
```

### **Java**

```java
class Solution {
    public int longestLine(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] a = new int[m + 2][n + 2];
        int[][] b = new int[m + 2][n + 2];
        int[][] c = new int[m + 2][n + 2];
        int[][] d = new int[m + 2][n + 2];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (mat[i - 1][j - 1] == 1) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                    c[i][j] = c[i - 1][j - 1] + 1;
                    d[i][j] = d[i - 1][j + 1] + 1;
                    ans = max(ans, a[i][j], b[i][j], c[i][j], d[i][j]);
                }
            }
        }
        return ans;
    }

    private int max(int... arr) {
        int ans = 0;
        for (int v : arr) {
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestLine(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> a(m + 2, vector<int>(n + 2));
        vector<vector<int>> b(m + 2, vector<int>(n + 2));
        vector<vector<int>> c(m + 2, vector<int>(n + 2));
        vector<vector<int>> d(m + 2, vector<int>(n + 2));
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (mat[i - 1][j - 1]) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                    c[i][j] = c[i - 1][j - 1] + 1;
                    d[i][j] = d[i - 1][j + 1] + 1;
                    ans = max(ans, max(a[i][j], max(b[i][j], max(c[i][j], d[i][j]))));
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestLine(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	f := make([][][4]int, m+2)
	for i := range f {
		f[i] = make([][4]int, n+2)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if mat[i-1][j-1] == 1 {
				f[i][j][0] = f[i-1][j][0] + 1
				f[i][j][1] = f[i][j-1][1] + 1
				f[i][j][2] = f[i-1][j-1][2] + 1
				f[i][j][3] = f[i-1][j+1][3] + 1
				for _, v := range f[i][j] {
					if ans < v {
						ans = v
					}
				}
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
