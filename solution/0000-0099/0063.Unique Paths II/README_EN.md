# [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii)

[中文文档](/solution/0000-0099/0063.Unique%20Paths%20II/README.md)

## Description

<p>A robot is located at the top-left corner of a <code>m x n</code> grid (marked &#39;Start&#39; in the diagram below).</p>

<p>The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked &#39;Finish&#39; in the diagram below).</p>

<p>Now consider if some obstacles are added to the grids. How many unique paths would there be?</p>

<p>An obstacle and space is marked as <code>1</code> and <code>0</code> respectively in the grid.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="/solution/0000-0099/0063.Unique Paths II/images/robot1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -&gt; Right -&gt; Down -&gt; Down
2. Down -&gt; Down -&gt; Right -&gt; Right
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="/solution/0000-0099/0063.Unique Paths II/images/robot2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Input:</strong> obstacleGrid = [[0,1],[0,0]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;obstacleGrid.length</code></li>
	<li><code>n ==&nbsp;obstacleGrid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>obstacleGrid[i][j]</code> is <code>0</code> or <code>1</code>.</li>
</ul>


## Solutions

### **Go**

```go
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
    m,n := len(obstacleGrid),len(obstacleGrid[0])
    dp := make([][]int,m)
    for i:=0; i < m;i++ {
        dp[i] = make([]int,n)
    }
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if obstacleGrid[i][j] == 0 {
                if i == 0 && j == 0 {
                    dp[i][j] = 1
                } else if i > 0 && j >0 {
                    dp[i][j] = dp[i][j-1]+dp[i-1][j]
                } else if i > 0 {
                    dp[i][j] = dp[i-1][j]
                } else {
                    dp[i][j] = dp[i][j-1]
                }
            }
        }
    }
    return dp[m-1][n-1]
}
```

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
