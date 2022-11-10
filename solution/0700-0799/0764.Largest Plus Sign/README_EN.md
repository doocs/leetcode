# [764. Largest Plus Sign](https://leetcode.com/problems/largest-plus-sign)

[中文文档](/solution/0700-0799/0764.Largest%20Plus%20Sign/README.md)

## Description

<p>You are given an integer <code>n</code>. You have an <code>n x n</code> binary grid <code>grid</code> with all values initially <code>1</code>&#39;s except for some indices given in the array <code>mines</code>. The <code>i<sup>th</sup></code> element of the array <code>mines</code> is defined as <code>mines[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> where <code>grid[x<sub>i</sub>][y<sub>i</sub>] == 0</code>.</p>

<p>Return <em>the order of the largest <strong>axis-aligned</strong> plus sign of </em>1<em>&#39;s contained in </em><code>grid</code>. If there is none, return <code>0</code>.</p>

<p>An <strong>axis-aligned plus sign</strong> of <code>1</code>&#39;s of order <code>k</code> has some center <code>grid[r][c] == 1</code> along with four arms of length <code>k - 1</code> going up, down, left, and right, and made of <code>1</code>&#39;s. Note that there could be <code>0</code>&#39;s or <code>1</code>&#39;s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for <code>1</code>&#39;s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0764.Largest%20Plus%20Sign/images/plus1-grid.jpg" style="width: 404px; height: 405px;" />
<pre>
<strong>Input:</strong> n = 5, mines = [[4,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In the above grid, the largest plus sign can only be of order 2. One of them is shown.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0764.Largest%20Plus%20Sign/images/plus2-grid.jpg" style="width: 84px; height: 85px;" />
<pre>
<strong>Input:</strong> n = 1, mines = [[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no plus sign, so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= mines.length &lt;= 5000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt; n</code></li>
	<li>All the pairs <code>(x<sub>i</sub>, y<sub>i</sub>)</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def orderOfLargestPlusSign(self, n: int, mines: List[List[int]]) -> int:
        dp = [[n] * n for _ in range(n)]
        for x, y in mines:
            dp[x][y] = 0
        for i in range(n):
            left = right = up = down = 0
            for j, k in zip(range(n), reversed(range(n))):
                left = left + 1 if dp[i][j] else 0
                right = right + 1 if dp[i][k] else 0
                up = up + 1 if dp[j][i] else 0
                down = down + 1 if dp[k][i] else 0
                dp[i][j] = min(dp[i][j], left)
                dp[i][k] = min(dp[i][k], right)
                dp[j][i] = min(dp[j][i], up)
                dp[k][i] = min(dp[k][i], down)
        return max(max(v) for v in dp)
```

### **Java**

```java
class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (var e : dp) {
            Arrays.fill(e, n);
        }
        for (var e : mines) {
            dp[e[0]][e[1]] = 0;
        }
        for (int i = 0; i < n; ++i) {
            int left = 0, right = 0, up = 0, down = 0;
            for (int j = 0, k = n - 1; j < n; ++j, --k) {
                left = dp[i][j] > 0 ? left + 1 : 0;
                right = dp[i][k] > 0 ? right + 1 : 0;
                up = dp[j][i] > 0 ? up + 1 : 0;
                down = dp[k][i] > 0 ? down + 1 : 0;
                dp[i][j] = Math.min(dp[i][j], left);
                dp[i][k] = Math.min(dp[i][k], right);
                dp[j][i] = Math.min(dp[j][i], up);
                dp[k][i] = Math.min(dp[k][i], down);
            }
        }
        return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int orderOfLargestPlusSign(int n, vector<vector<int>>& mines) {
        vector<vector<int>> dp(n, vector<int>(n, n));
        for (auto& e : mines) dp[e[0]][e[1]] = 0;
        for (int i = 0; i < n; ++i) {
            int left = 0, right = 0, up = 0, down = 0;
            for (int j = 0, k = n - 1; j < n; ++j, --k) {
                left = dp[i][j] ? left + 1 : 0;
                right = dp[i][k] ? right + 1 : 0;
                up = dp[j][i] ? up + 1 : 0;
                down = dp[k][i] ? down + 1 : 0;
                dp[i][j] = min(dp[i][j], left);
                dp[i][k] = min(dp[i][k], right);
                dp[j][i] = min(dp[j][i], up);
                dp[k][i] = min(dp[k][i], down);
            }
        }
        int ans = 0;
        for (auto& e : dp) ans = max(ans, *max_element(e.begin(), e.end()));
        return ans;
    }
};
```

### **Go**

```go
func orderOfLargestPlusSign(n int, mines [][]int) (ans int) {
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, n)
		for j := range dp[i] {
			dp[i][j] = n
		}
	}
	for _, e := range mines {
		dp[e[0]][e[1]] = 0
	}
	for i := 0; i < n; i++ {
		var left, right, up, down int
		for j, k := 0, n-1; j < n; j, k = j+1, k-1 {
			left, right, up, down = left+1, right+1, up+1, down+1
			if dp[i][j] == 0 {
				left = 0
			}
			if dp[i][k] == 0 {
				right = 0
			}
			if dp[j][i] == 0 {
				up = 0
			}
			if dp[k][i] == 0 {
				down = 0
			}
			dp[i][j] = min(dp[i][j], left)
			dp[i][k] = min(dp[i][k], right)
			dp[j][i] = min(dp[j][i], up)
			dp[k][i] = min(dp[k][i], down)
		}
	}
	for _, e := range dp {
		for _, v := range e {
			ans = max(ans, v)
		}
	}
	return
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

### **...**

```

```

<!-- tabs:end -->
