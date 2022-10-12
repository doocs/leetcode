# [291. Word Pattern II](https://leetcode.com/problems/word-pattern-ii)

[中文文档](/solution/0200-0299/0291.Word%20Pattern%20II/README.md)

## Description

<p>Given a <code>pattern</code> and a string <code>s</code>, return <code>true</code><em> if </em><code>s</code><em> <strong>matches</strong> the </em><code>pattern</code><em>.</em></p>

<p>A string <code>s</code> <b>matches</b> a <code>pattern</code> if there is some <strong>bijective mapping</strong> of single characters to strings such that if each character in <code>pattern</code> is replaced by the string it maps to, then the resulting string is <code>s</code>. A <strong>bijective mapping</strong> means that no two characters map to the same string, and no character maps to two different strings.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;abab&quot;, s = &quot;redblueredblue&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> One possible mapping is as follows:
&#39;a&#39; -&gt; &quot;red&quot;
&#39;b&#39; -&gt; &quot;blue&quot;</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;aaaa&quot;, s = &quot;asdasdasdasd&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> One possible mapping is as follows:
&#39;a&#39; -&gt; &quot;asd&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;aabb&quot;, s = &quot;xyzabcxzyabc&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length, s.length &lt;= 20</code></li>
	<li><code>pattern</code> and <code>s</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wordPatternMatch(self, pattern: str, s: str) -> bool:
        def dfs(i, j):
            if i == m and j == n:
                return True
            if i == m or j == n or n - j < m - i:
                return False
            for k in range(j, n):
                t = s[j : k + 1]
                if d.get(pattern[i]) == t:
                    if dfs(i + 1, k + 1):
                        return True
                if pattern[i] not in d and t not in vis:
                    d[pattern[i]] = t
                    vis.add(t)
                    if dfs(i + 1, k + 1):
                        return True
                    d.pop(pattern[i])
                    vis.remove(t)
            return False

        m, n = len(pattern), len(s)
        d = {}
        vis = set()
        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    private Set<String> vis;
    private Map<Character, String> d;
    private String p;
    private String s;
    private int m;
    private int n;

    public boolean wordPatternMatch(String pattern, String s) {
        vis = new HashSet<>();
        d = new HashMap<>();
        this.p = pattern;
        this.s = s;
        m = p.length();
        n = s.length();
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (i == m && j == n) {
            return true;
        }
        if (i == m || j == n || m - i > n - j) {
            return false;
        }
        char c = p.charAt(i);
        for (int k = j + 1; k <= n; ++k) {
            String t = s.substring(j, k);
            if (d.getOrDefault(c, "").equals(t)) {
                if (dfs(i + 1, k)) {
                    return true;
                }
            }
            if (!d.containsKey(c) && !vis.contains(t)) {
                d.put(c, t);
                vis.add(t);
                if (dfs(i + 1, k)) {
                    return true;
                }
                vis.remove(t);
                d.remove(c);
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool wordPatternMatch(string pattern, string s) {
        unordered_set<string> vis;
        unordered_map<char, string> d;
        return dfs(0, 0, pattern, s, vis, d);
    }

    bool dfs(int i, int j, string& p, string& s, unordered_set<string>& vis, unordered_map<char, string>& d) {
        int m = p.size(), n = s.size();
        if (i == m && j == n) return true;
        if (i == m || j == n || m - i > n - j) return false;
        char c = p[i];
        for (int k = j + 1; k <= n; ++k) {
            string t = s.substr(j, k - j);
            if (d.count(c) && d[c] == t) {
                if (dfs(i + 1, k, p, s, vis, d)) return true;
            }
            if (!d.count(c) && !vis.count(t)) {
                d[c] = t;
                vis.insert(t);
                if (dfs(i + 1, k, p, s, vis, d)) return true;
                vis.erase(t);
                d.erase(c);
            }
        }
        return false;
    }
};
```

### **Go**

```go
func wordPatternMatch(pattern string, s string) bool {
	m, n := len(pattern), len(s)
	vis := map[string]bool{}
	d := map[byte]string{}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i == m && j == n {
			return true
		}
		if i == m || j == n || m-i > n-j {
			return false
		}
		c := pattern[i]
		for k := j + 1; k <= n; k++ {
			t := s[j:k]
			if v, ok := d[c]; ok && v == t {
				if dfs(i+1, k) {
					return true
				}
			}
			if _, ok := d[c]; !ok && !vis[t] {
				d[c] = t
				vis[t] = true
				if dfs(i+1, k) {
					return true
				}
				delete(d, c)
				vis[t] = false
			}
		}
		return false
	}
	return dfs(0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
