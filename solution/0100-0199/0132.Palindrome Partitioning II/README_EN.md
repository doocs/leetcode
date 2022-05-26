# [132. Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii)

[中文文档](/solution/0100-0199/0132.Palindrome%20Partitioning%20II/README.md)

## Description

<p>Given a string <code>s</code>, partition <code>s</code> such that every substring of the partition is a palindrome.</p>

<p>Return <em>the minimum cuts needed</em> for a palindrome partitioning of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The palindrome partitioning [&quot;aa&quot;,&quot;b&quot;] could be produced using 1 cut.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ab&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        dp1 = [[False] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i, n):
                dp1[i][j] = s[i] == s[j] and (j - i < 3 or dp1[i + 1][j - 1])
        dp2 = [0] * n
        for i in range(n):
            if not dp1[0][i]:
                dp2[i] = i
                for j in range(1, i + 1):
                    if dp1[j][i]:
                        dp2[i] = min(dp2[i], dp2[j - 1] + 1)
        return dp2[-1]
```

### **Java**

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp1 = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp1[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp1[i + 1][j - 1]);
            }
        }
        int[] dp2 = new int[n];
        for (int i = 0; i < n; i++) {
            if (!dp1[0][i]) {
                dp2[i] = i;
                for (int j = 1; j <= i; j++) {
                    if (dp1[j][i]) {
                        dp2[i] = Math.min(dp2[i], dp2[j - 1] + 1);
                    }
                }
            }
        }
        return dp2[n - 1];
    }
}
```

### **Go**

```go
func minCut(s string) int {
	n := len(s)
	dp1 := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp1[i] = make([]bool, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			dp1[i][j] = s[i] == s[j] && (j-i < 3 || dp1[i+1][j-1])
		}
	}
	dp2 := make([]int, n)
	for i := 0; i < n; i++ {
		if !dp1[0][i] {
			dp2[i] = i
			for j := 1; j <= i; j++ {
				if dp1[j][i] {
					dp2[i] = min(dp2[i], dp2[j-1]+1)
				}
			}
		}
	}
	return dp2[n-1]
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    int minCut(string s) {
        int n = s.size();
        vector<vector<bool>> dp1(n, vector<bool>(n));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                dp1[i][j] = s[i] == s[j] && (j - i < 3 || dp1[i + 1][j - 1]);
            }
        }
        vector<int> dp2(n);
        for (int i = 0; i < n; ++i) {
            if (!dp1[0][i]) {
                dp2[i] = i;
                for (int j = 1; j <= i; ++j) {
                    if (dp1[j][i]) {
                        dp2[i] = min(dp2[i], dp2[j - 1] + 1);
                    }
                }
            }
        }
        return dp2[n - 1];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
