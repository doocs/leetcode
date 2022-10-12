# [1593. Split a String Into the Max Number of Unique Substrings](https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings)

[中文文档](/solution/1500-1599/1593.Split%20a%20String%20Into%20the%20Max%20Number%20of%20Unique%20Substrings/README.md)

## Description

<p>Given a string&nbsp;<code>s</code><var>,</var>&nbsp;return <em>the maximum&nbsp;number of unique substrings that the given string can be split into</em>.</p>

<p>You can split string&nbsp;<code>s</code> into any list of&nbsp;<strong>non-empty substrings</strong>, where the concatenation of the substrings forms the original string.&nbsp;However, you must split the substrings such that all of them are <strong>unique</strong>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababccc&quot;
<strong>Output:</strong> 5
<strong>Explanation</strong>: One way to split maximally is [&#39;a&#39;, &#39;b&#39;, &#39;ab&#39;, &#39;c&#39;, &#39;cc&#39;]. Splitting like [&#39;a&#39;, &#39;b&#39;, &#39;a&#39;, &#39;b&#39;, &#39;c&#39;, &#39;cc&#39;] is not valid as you have &#39;a&#39; and &#39;b&#39; multiple times.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> 2
<strong>Explanation</strong>: One way to split maximally is [&#39;a&#39;, &#39;ba&#39;].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;
<strong>Output:</strong> 1
<strong>Explanation</strong>: It is impossible to split the string any further.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>
	<p><code>1 &lt;= s.length&nbsp;&lt;= 16</code></p>
	</li>
	<li>
	<p><code>s</code> contains&nbsp;only lower case English letters.</p>
	</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        def dfs(i, t):
            if i >= len(s):
                nonlocal ans
                ans = max(ans, t)
                return
            for j in range(i + 1, len(s) + 1):
                if s[i: j] not in vis:
                    vis.add(s[i: j])
                    dfs(j, t + 1)
                    vis.remove(s[i: j])

        vis = set()
        ans = 1
        dfs(0, 0)
        return ans
```

### **Java**

```java
class Solution {
    private Set<String> vis = new HashSet<>();
    private int ans = 1;
    private String s;

    public int maxUniqueSplit(String s) {
        this.s = s;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int t) {
        if (i >= s.length()) {
            ans = Math.max(ans, t);
            return;
        }
        for (int j = i + 1; j <= s.length(); ++j) {
            String x = s.substring(i, j);
            if (vis.add(x)) {
                dfs(j, t + 1);
                vis.remove(x);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_set<string> vis;
    string s;
    int ans = 1;

    int maxUniqueSplit(string s) {
        this->s = s;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int t) {
        if (i >= s.size()) {
            ans = max(ans, t);
            return;
        }
        for (int j = i + 1; j <= s.size(); ++j) {
            string x = s.substr(i, j - i);
            if (!vis.count(x)) {
                vis.insert(x);
                dfs(j, t + 1);
                vis.erase(x);
            }
        }
    }
};
```

### **Go**

```go
func maxUniqueSplit(s string) int {
	ans := 1
	vis := map[string]bool{}

	var dfs func(i, t int)
	dfs = func(i, t int) {
		if i >= len(s) {
			ans = max(ans, t)
			return
		}
		for j := i + 1; j <= len(s); j++ {
			x := s[i:j]
			if !vis[x] {
				vis[x] = true
				dfs(j, t+1)
				vis[x] = false
			}
		}
	}
	dfs(0, 0)
	return ans
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
