# [2428. Maximum Sum of an Hourglass](https://leetcode.com/problems/maximum-sum-of-an-hourglass)

[中文文档](/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>.</p>

<p>We define an <strong>hourglass</strong> as a part of the matrix with the following form:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/images/img.jpg" style="width: 243px; height: 243px;" />
<p>Return <em>the <strong>maximum</strong> sum of the elements of an hourglass</em>.</p>

<p><strong>Note</strong> that an hourglass cannot be rotated and must be entirely contained within the matrix.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/images/1.jpg" style="width: 323px; height: 323px;" />
<pre>
<strong>Input:</strong> grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
<strong>Output:</strong> 30
<strong>Explanation:</strong> The cells shown above represent the hourglass with the maximum sum: 6 + 2 + 1 + 2 + 9 + 2 + 8 = 30.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/images/2.jpg" style="width: 243px; height: 243px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> 35
<strong>Explanation:</strong> There is only one hourglass in the matrix, with the sum: 1 + 2 + 3 + 5 + 7 + 8 + 9 = 35.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>3 &lt;= m, n &lt;= 150</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(1, m - 1):
            for j in range(1, n - 1):
                t = 0
                for x in [i - 1, i, i + 1]:
                    for y in [j - 1, j, j + 1]:
                        t += grid[x][y]

                t -= grid[i][j - 1]
                t -= grid[i][j + 1]
                ans = max(ans, t)
        return ans
```

### **Java**

```java
class Solution {
    public int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                int t = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        t += grid[x][y];
                    }
                }
                t -= grid[i][j - 1];
                t -= grid[i][j + 1];
                ans = Math.max(ans, t);
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
    int maxSum(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                int t = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        t += grid[x][y];
                    }
                }
                t -= grid[i][j - 1];
                t -= grid[i][j + 1];
                ans = max(ans, t);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSum(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0
	for i := 1; i < m-1; i++ {
		for j := 1; j < n-1; j++ {
			t := 0
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					t += grid[x][y]
				}
			}
			t -= grid[i][j-1]
			t -= grid[i][j+1]
			ans = max(ans, t)
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
```

### **TypeScript**

```ts
function maxSum(grid: number[][]): number {
    const m = grid.length, n = grid[0].length;
    let threeSum = Array.from({ length: m }, () => new Array(n - 2).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 1; j < n - 1; j++) {
            threeSum[i][j - 1] = grid[i][j - 1] + grid[i][j] + grid[i][j + 1];
        }
    }
    let ans = 0;
    for (let i = 1; i < m - 1; i++) {
        for (let j = 1; j < n - 1; j++) {
            ans = Math.max(ans, threeSum[i - 1][j - 1] + grid[i][j] + threeSum[i + 1][j - 1]);
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
