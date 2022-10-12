# [221. Maximal Square](https://leetcode.com/problems/maximal-square)

[中文文档](/solution/0200-0299/0221.Maximal%20Square/README.md)

## Description

<p>Given an <code>m x n</code> binary <code>matrix</code> filled with <code>0</code>&#39;s and <code>1</code>&#39;s, <em>find the largest square containing only</em> <code>1</code>&#39;s <em>and return its area</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0221.Maximal%20Square/images/max1grid.jpg" style="width: 400px; height: 319px;" />
<pre>
<strong>Input:</strong> matrix = [[&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;],[&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;]]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0221.Maximal%20Square/images/max2grid.jpg" style="width: 165px; height: 165px;" />
<pre>
<strong>Input:</strong> matrix = [[&quot;0&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;0&quot;]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[&quot;0&quot;]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>matrix[i][j]</code> is <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        m, n = len(matrix), len(matrix[0])
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        mx = 0
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == '1':
                    dp[i + 1][j + 1] = min(dp[i][j + 1], dp[i + 1][j], dp[i][j]) + 1
                    mx = max(mx, dp[i + 1][j + 1])
        return mx * mx
```

### **Java**

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int mx = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                    mx = Math.max(mx, dp[i + 1][j + 1]);
                }
            }
        }
        return mx * mx;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        int mx = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = min(min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                    mx = max(mx, dp[i + 1][j + 1]);
                }
            }
        }
        return mx * mx;
    }
};
```

### **Go**

```go
func maximalSquare(matrix [][]byte) int {
	m, n := len(matrix), len(matrix[0])
	dp := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]int, n+1)
	}
	mx := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] == '1' {
				dp[i+1][j+1] = min(min(dp[i][j+1], dp[i+1][j]), dp[i][j]) + 1
				mx = max(mx, dp[i+1][j+1])
			}
		}
	}
	return mx * mx
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

### **C#**

```cs
public class Solution {
    public int MaximalSquare(char[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        var dp = new int[m + 1, n + 1];
        int mx = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (matrix[i][j] == '1')
                {
                    dp[i + 1, j + 1] = Math.Min(Math.Min(dp[i, j + 1], dp[i + 1, j]), dp[i, j]) + 1;
                    mx = Math.Max(mx, dp[i + 1, j + 1]);
                }
            }
        }
        return mx * mx;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
