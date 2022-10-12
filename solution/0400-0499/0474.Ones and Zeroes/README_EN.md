# [474. Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes)

[中文文档](/solution/0400-0499/0474.Ones%20and%20Zeroes/README.md)

## Description

<p>You are given an array of binary strings <code>strs</code> and two integers <code>m</code> and <code>n</code>.</p>

<p>Return <em>the size of the largest subset of <code>strs</code> such that there are <strong>at most</strong> </em><code>m</code><em> </em><code>0</code><em>&#39;s and </em><code>n</code><em> </em><code>1</code><em>&#39;s in the subset</em>.</p>

<p>A set <code>x</code> is a <strong>subset</strong> of a set <code>y</code> if all elements of <code>x</code> are also elements of <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;10&quot;,&quot;0001&quot;,&quot;111001&quot;,&quot;1&quot;,&quot;0&quot;], m = 5, n = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The largest subset with at most 5 0&#39;s and 3 1&#39;s is {&quot;10&quot;, &quot;0001&quot;, &quot;1&quot;, &quot;0&quot;}, so the answer is 4.
Other valid but smaller subsets include {&quot;0001&quot;, &quot;1&quot;} and {&quot;10&quot;, &quot;1&quot;, &quot;0&quot;}.
{&quot;111001&quot;} is an invalid subset because it contains 4 1&#39;s, greater than the maximum of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;10&quot;,&quot;0&quot;,&quot;1&quot;], m = 1, n = 1
<strong>Output:</strong> 2
<b>Explanation:</b> The largest subset is {&quot;0&quot;, &quot;1&quot;}, so the answer is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 600</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists only of digits <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        l = len(strs)
        dp = [[[0] * (n + 1) for i in range(m + 1)] for j in range(l)]
        t = [(s.count('0'), s.count('1')) for s in strs]
        n0, n1 = t[0]
        for j in range(m + 1):
            for k in range(n + 1):
                if n0 <= j and n1 <= k:
                    dp[0][j][k] = 1

        for i in range(1, l):
            n0, n1 = t[i]
            for j in range(m + 1):
                for k in range(n + 1):
                    dp[i][j][k] = dp[i - 1][j][k]
                    if n0 <= j and n1 <= k:
                        dp[i][j][k] = max(dp[i][j][k], dp[i - 1][j - n0][k - n1] + 1)

        return dp[-1][-1][-1]
```

```python
class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        t = [(s.count('0'), s.count('1')) for s in strs]
        for k in range(len(strs)):
            n0, n1 = t[k]
            for i in range(m, n0 - 1, -1):
                for j in range(n, n1 - 1, -1):
                    dp[i][j] = max(dp[i][j], dp[i - n0][j - n1] + 1)
        return dp[-1][-1]
```

### **Java**

```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] t = count(s);
            for (int i = m; i >= t[0]; --i) {
                for (int j = n; j >= t[1]; --j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - t[0]][j - t[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] count(String s) {
        int n0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ++n0;
            }
        }
        return new int[] {n0, s.length() - n0};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (auto s : strs) {
            vector<int> t = count(s);
            for (int i = m; i >= t[0]; --i)
                for (int j = n; j >= t[1]; --j)
                    dp[i][j] = max(dp[i][j], dp[i - t[0]][j - t[1]] + 1);
        }
        return dp[m][n];
    }

    vector<int> count(string s) {
        int n0 = 0;
        for (char c : s)
            if (c == '0') ++n0;
        return {n0, (int)s.size() - n0};
    }
};
```

### **Go**

```go
func findMaxForm(strs []string, m int, n int) int {
	dp := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		dp[i] = make([]int, n+1)
	}
	for _, s := range strs {
		t := count(s)
		for i := m; i >= t[0]; i-- {
			for j := n; j >= t[1]; j-- {
				dp[i][j] = max(dp[i][j], dp[i-t[0]][j-t[1]]+1)
			}
		}
	}
	return dp[m][n]
}

func count(s string) []int {
	n0 := 0
	for i := 0; i < len(s); i++ {
		if s[i] == '0' {
			n0++
		}
	}
	return []int{n0, len(s) - n0}
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
