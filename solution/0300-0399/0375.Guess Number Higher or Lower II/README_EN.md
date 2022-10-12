# [375. Guess Number Higher or Lower II](https://leetcode.com/problems/guess-number-higher-or-lower-ii)

[中文文档](/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README.md)

## Description

<p>We are playing the Guessing Game. The game will work as follows:</p>

<ol>
	<li>I pick a number between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>n</code>.</li>
	<li>You guess a number.</li>
	<li>If you guess the right number, <strong>you win the game</strong>.</li>
	<li>If you guess the wrong number, then I will tell you whether the number I picked is <strong>higher or lower</strong>, and you will continue guessing.</li>
	<li>Every time you guess a wrong number&nbsp;<code>x</code>, you will pay&nbsp;<code>x</code>&nbsp;dollars. If you run out of money, <strong>you lose the game</strong>.</li>
</ol>

<p>Given a particular&nbsp;<code>n</code>, return&nbsp;<em>the minimum amount of money you need to&nbsp;<strong>guarantee a win regardless of what number I pick</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/images/graph.png" style="width: 505px; height: 388px;" />
<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 16
<strong>Explanation:</strong> The winning strategy is as follows:
- The range is [1,10]. Guess 7.
&nbsp;   - If this is my number, your total is $0. Otherwise, you pay $7.
&nbsp;   - If my number is higher, the range is [8,10]. Guess 9.
&nbsp;       - If this is my number, your total is $7. Otherwise, you pay $9.
&nbsp;       - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
&nbsp;       - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
&nbsp;   - If my number is lower, the range is [1,6]. Guess 3.
&nbsp;       - If this is my number, your total is $7. Otherwise, you pay $3.
&nbsp;       - If my number is higher, the range is [4,6]. Guess 5.
&nbsp;           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
&nbsp;           - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
&nbsp;           - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
&nbsp;       - If my number is lower, the range is [1,2]. Guess 1.
&nbsp;           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
&nbsp;           - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;There is only one possible number, so you can guess 1 and not have to pay anything.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;There are two possible numbers, 1 and 2.
- Guess 1.
&nbsp;   - If this is my number, your total is $0. Otherwise, you pay $1.
&nbsp;   - If my number is higher, it must be 2. Guess 2. Your total is $1.
The worst case is that you pay $1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMoneyAmount(self, n: int) -> int:
        dp = [[0] * (n + 10) for _ in range(n + 10)]
        for l in range(2, n + 1):
            for i in range(1, n - l + 2):
                j = i + l - 1
                dp[i][j] = inf
                for k in range(i, j + 1):
                    t = max(dp[i][k - 1], dp[k + 1][j]) + k
                    dp[i][j] = min(dp[i][j], t)
        return dp[1][n]
```

### **Java**

```java
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 10][n + 10];
        for (int l = 2; l <= n; ++l) {
            for (int i = 1; i + l - 1 <= n; ++i) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; ++k) {
                    int t = Math.max(dp[i][k - 1], dp[k + 1][j]) + k;
                    dp[i][j] = Math.min(dp[i][j], t);
                }
            }
        }
        return dp[1][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getMoneyAmount(int n) {
        vector<vector<int>> dp(n + 10, vector<int>(n + 10));
        for (int l = 2; l <= n; ++l) {
            for (int i = 1; i + l - 1 <= n; ++i) {
                int j = i + l - 1;
                dp[i][j] = INT_MAX;
                for (int k = i; k <= j; ++k) {
                    int t = max(dp[i][k - 1], dp[k + 1][j]) + k;
                    dp[i][j] = min(dp[i][j], t);
                }
            }
        }
        return dp[1][n];
    }
};
```

### **Go**

```go
func getMoneyAmount(n int) int {
	dp := make([][]int, n+10)
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, n+10)
	}
	for l := 2; l <= n; l++ {
		for i := 1; i+l-1 <= n; i++ {
			j := i + l - 1
			dp[i][j] = math.MaxInt32
			for k := i; k <= j; k++ {
				t := max(dp[i][k-1], dp[k+1][j]) + k
				dp[i][j] = min(dp[i][j], t)
			}
		}
	}
	return dp[1][n]
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

### **...**

```

```

<!-- tabs:end -->
