---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0291.Word%20Pattern%20II/README.md
tags:
    - 哈希表
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [291. 单词规律 II 🔒](https://leetcode.cn/problems/word-pattern-ii)

[English Version](/solution/0200-0299/0291.Word%20Pattern%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一种规律&nbsp;<code>pattern</code>&nbsp;和一个字符串&nbsp;<code>s</code>，请你判断&nbsp;<code>s</code>&nbsp;是否和<em>&nbsp;</em><code>pattern</code>&nbsp;的规律<strong>相匹配</strong>。</p>

<p>如果存在单个字符到 <strong>非空</strong> 字符串的 <strong>双射映射</strong> ，那么字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;匹配<meta charset="UTF-8" />&nbsp;<code>pattern</code>&nbsp;，即：如果&nbsp;<meta charset="UTF-8" /><code>pattern</code>&nbsp;中的每个字符都被它映射到的字符串替换，那么最终的字符串则为 <code>s</code> 。<strong>双射</strong> 意味着映射双方一一对应，不会存在两个字符映射到同一个字符串，也不会存在一个字符分别映射到两个不同的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>pattern = "abab", s = "redblueredblue"
<strong>输出：</strong>true
<strong>解释：</strong>一种可能的映射如下：
'a' -&gt; "red"
'b' -&gt; "blue"</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>pattern = "aaaa", s = "asdasdasdasd"
<strong>输出：</strong>true
<strong>解释：</strong>一种可能的映射如下：
'a' -&gt; "asd"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>pattern = "aabb", s = "xyzabcxzyabc"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length, s.length &lt;= 20</code></li>
	<li><code>pattern</code> 和 <code>s</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
