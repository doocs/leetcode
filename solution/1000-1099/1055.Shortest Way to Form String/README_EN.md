# [1055. Shortest Way to Form String](https://leetcode.com/problems/shortest-way-to-form-string)

[中文文档](/solution/1000-1099/1055.Shortest%20Way%20to%20Form%20String/README.md)

## Description

<p>A <strong>subsequence</strong> of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;<u>a</u>b<u>c</u>d<u>e</u>&quot;</code> while <code>&quot;aec&quot;</code> is not).</p>

<p>Given two strings <code>source</code> and <code>target</code>, return <em>the minimum number of <strong>subsequences</strong> of </em><code>source</code><em> such that their concatenation equals </em><code>target</code>. If the task is impossible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> source = &quot;abc&quot;, target = &quot;abcbc&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The target &quot;abcbc&quot; can be formed by &quot;abc&quot; and &quot;bc&quot;, which are subsequences of source &quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> source = &quot;abc&quot;, target = &quot;acdbc&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> The target string cannot be constructed from the subsequences of source string due to the character &quot;d&quot; in target string.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> source = &quot;xyz&quot;, target = &quot;xzyxz&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The target string can be constructed as follows &quot;xz&quot; + &quot;y&quot; + &quot;xz&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= source.length, target.length &lt;= 1000</code></li>
	<li><code>source</code> and <code>target</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestWay(self, source: str, target: str) -> int:
        def f(i, j):
            while i < m and j < n:
                if source[i] == target[j]:
                    j += 1
                i += 1
            return j

        m, n = len(source), len(target)
        ans = j = 0
        while j < n:
            k = f(0, j)
            if k == j:
                return -1
            j = k
            ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int shortestWay(String source, String target) {
        int m = source.length(), n = target.length();
        int ans = 0, j = 0;
        while (j < n) {
            int i = 0;
            boolean ok = false;
            while (i < m && j < n) {
                if (source.charAt(i) == target.charAt(j)) {
                    ok = true;
                    ++j;
                }
                ++i;
            }
            if (!ok) {
                return -1;
            }
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestWay(string source, string target) {
        int m = source.size(), n = target.size();
        int ans = 0, j = 0;
        while (j < n) {
            int i = 0;
            bool ok = false;
            while (i < m && j < n) {
                if (source[i] == target[j]) {
                    ok = true;
                    ++j;
                }
                ++i;
            }
            if (!ok) {
                return -1;
            }
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestWay(source string, target string) int {
	m, n := len(source), len(target)
	ans, j := 0, 0
	for j < n {
		ok := false
		for i := 0; i < m && j < n; i++ {
			if source[i] == target[j] {
				ok = true
				j++
			}
		}
		if !ok {
			return -1
		}
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
