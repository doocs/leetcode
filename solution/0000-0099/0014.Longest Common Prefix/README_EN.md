# [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix)

[中文文档](/solution/0000-0099/0014.Longest%20Common%20Prefix/README.md)

## Description

<p>Write a function to find the longest common prefix string amongst an array of strings.</p>

<p>If there is no common prefix, return an empty string <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;flower&quot;,&quot;flow&quot;,&quot;flight&quot;]
<strong>Output:</strong> &quot;fl&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;dog&quot;,&quot;racecar&quot;,&quot;car&quot;]
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There is no common prefix among the input strings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= strs.length &lt;= 200</code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
	<li><code>strs[i]</code> consists of only lower-case English letters.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        n = len(strs)
        if n == 0:
            return ''
        for i in range(len(strs[0])):
            for j in range(1, n):
                if len(strs[j]) <= i or strs[j][i] != strs[0][i]:
                    return strs[0][:i]
        return strs[0]
```

### **Java**

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n;
        if ((n = strs.length) == 0) return "";
        for (int i = 0; i < strs[0].length(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int n;
        if ((n = strs.size()) == 0) return "";
        for (int i = 0; i < strs[0].size(); ++i) {
            for (int j = 1; j < n; ++j) {
                if (strs[j].size() <= i || strs[j][i] != strs[0][i]) {
                    return strs[0].substr(0, i);
                }
            }
        }
        return strs[0];
    }
};
```

### **Go**

```go
func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}

	var b strings.Builder
	m, n := len(strs[0]), len(strs)

LOOP:
	for i := 0; i < m; i++ {
		for j := 1; j < n; j++ {
			if i >= len(strs[j]) || strs[0][i] != strs[j][i] {
				break LOOP
			}
		}
		b.WriteByte(strs[0][i])
	}

	return b.String()
}
```

### **...**

```

```

<!-- tabs:end -->
