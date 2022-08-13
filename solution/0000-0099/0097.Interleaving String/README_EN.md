# [97. Interleaving String](https://leetcode.com/problems/interleaving-string)

[中文文档](/solution/0000-0099/0097.Interleaving%20String/README.md)

## Description

<p>Given strings <code>s1</code>, <code>s2</code>, and <code>s3</code>, find whether <code>s3</code> is formed by an <strong>interleaving</strong> of <code>s1</code> and <code>s2</code>.</p>

<p>An <strong>interleaving</strong> of two strings <code>s</code> and <code>t</code> is a configuration where <code>s</code> and <code>t</code> are divided into <code>n</code> and <code>m</code> <strong>non-empty</strong> substrings respectively, such that:</p>

<ul>
	<li><code>s = s<sub>1</sub> + s<sub>2</sub> + ... + s<sub>n</sub></code></li>
	<li><code>t = t<sub>1</sub> + t<sub>2</sub> + ... + t<sub>m</sub></code></li>
	<li><code>|n - m| &lt;= 1</code></li>
	<li>The <strong>interleaving</strong> is <code>s<sub>1</sub> + t<sub>1</sub> + s<sub>2</sub> + t<sub>2</sub> + s<sub>3</sub> + t<sub>3</sub> + ...</code> or <code>t<sub>1</sub> + s<sub>1</sub> + t<sub>2</sub> + s<sub>2</sub> + t<sub>3</sub> + s<sub>3</sub> + ...</code></li>
</ul>

<p><strong>Note:</strong> <code>a + b</code> is the concatenation of strings <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0097.Interleaving%20String/images/interleave.jpg" style="width: 561px; height: 203px;" />
<pre>
<strong>Input:</strong> s1 = &quot;aabcc&quot;, s2 = &quot;dbbca&quot;, s3 = &quot;aadbbcbcac&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> One way to obtain s3 is:
Split s1 into s1 = &quot;aa&quot; + &quot;bc&quot; + &quot;c&quot;, and s2 into s2 = &quot;dbbc&quot; + &quot;a&quot;.
Interleaving the two splits, we get &quot;aa&quot; + &quot;dbbc&quot; + &quot;bc&quot; + &quot;a&quot; + &quot;c&quot; = &quot;aadbbcbcac&quot;.
Since s3 can be obtained by interleaving s1 and s2, we return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;aabcc&quot;, s2 = &quot;dbbca&quot;, s3 = &quot;aadbbbaccc&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> Notice how it is impossible to interleave s2 with any other string to obtain s3.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;&quot;, s2 = &quot;&quot;, s3 = &quot;&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>0 &lt;= s3.length &lt;= 200</code></li>
	<li><code>s1</code>, <code>s2</code>, and <code>s3</code> consist of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it using only <code>O(s2.length)</code> additional memory space?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False

        @cache
        def dfs(i, j):
            if i == m and j == n:
                return True

            return (
                i < m
                and s1[i] == s3[i + j]
                and dfs(i + 1, j)
                or j < n
                and s2[j] == s3[i + j]
                and dfs(i, j + 1)
            )

        return dfs(0, 0)
```

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i:
                    dp[i][j] = s1[i - 1] == s3[k] and dp[i - 1][j]
                if j:
                    dp[i][j] |= (s2[j - 1] == s3[k] and dp[i][j - 1])
        return dp[-1][-1]
```

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i:
                    dp[j] &= (s1[i - 1] == s3[k])
                if j:
                    dp[j] |= (s2[j - 1] == s3[k] and dp[j - 1])
        return dp[-1]
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private String s1;
    private String s2;
    private String s3;
    private Map<Integer, Boolean> memo = new HashMap<>();

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (m + n != s3.length()) {
            return false;
        }
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        System.out.println(i + ", " + j);
        if (i == m && j == n) {
            return true;
        }
        if (memo.containsKey(i * 100 + j)) {
            return memo.get(i * 100 + j);
        }

        boolean ret = (i < m && s1.charAt(i) == s3.charAt(i + j) && dfs(i + 1, j)) ||
                (j < n && s2.charAt(j) == s3.charAt(i + j) && dfs(i, j + 1));

        memo.put(i * 100 + j, ret);
        return ret;
    }
}
```

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    dp[j] &= (s1.charAt(i - 1) == s3.charAt(k));
                }
                if (j > 0) {
                    dp[j] |= (s2.charAt(j - 1) == s3.charAt(k) && dp[j - 1]);
                }
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) return false;

        unordered_map<int, bool> memo;

        function<bool(int, int)> dfs;
        dfs = [&](int i, int j) {
            if (i == m && j == n) return true;
            auto it = memo.find(i * 100 + j);
            if (it != memo.end()) return it->second;

            bool ret = (i < m && s1[i] == s3[i + j] && dfs(i + 1, j)) || (j < n && s2[j] == s3[i + j] && dfs(i, j + 1));

            memo[i * 100 + j] = ret;
            return ret;
        };

        return dfs(0, 0);
    }
};
```

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) return false;
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 0; i <= m; ++i)
        {
            for (int j = 0; j <= n; ++j)
            {
                int k = i + j - 1;
                if (i) dp[j] &= (s1[i - 1] == s3[k]);
                if (j) dp[j] |= (s2[j - 1] == s3[k] && dp[j - 1]);
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}

	memo := make(map[int]bool)

	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		if i == m && j == n {
			return true
		}
		if v, ok := memo[i*100+j]; ok {
			return v
		}

		ret := (i < m && s1[i] == s3[i+j] && dfs(i+1, j)) ||
			(j < n && s2[j] == s3[i+j] && dfs(i, j+1))

		memo[i*100+j] = ret
		return ret
	}

	return dfs(0, 0)
}
```

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	dp := make([]bool, n+1)
	dp[0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 {
				dp[j] = dp[j] && (s1[i-1] == s3[k])
			}
			if j > 0 {
				dp[j] = dp[j] || (s2[j-1] == s3[k] && dp[j-1])
			}
		}
	}
	return dp[n]
}
```

### **...**

```

```

<!-- tabs:end -->
