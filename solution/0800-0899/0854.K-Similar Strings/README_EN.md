# [854. K-Similar Strings](https://leetcode.com/problems/k-similar-strings)

[中文文档](/solution/0800-0899/0854.K-Similar%20Strings/README.md)

## Description

<p>Strings <code>s1</code> and <code>s2</code> are <code>k</code><strong>-similar</strong> (for some non-negative integer <code>k</code>) if we can swap the positions of two letters in <code>s1</code> exactly <code>k</code> times so that the resulting string equals <code>s2</code>.</p>

<p>Given two anagrams <code>s1</code> and <code>s2</code>, return the smallest <code>k</code> for which <code>s1</code> and <code>s2</code> are <code>k</code><strong>-similar</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;ba&quot;
<strong>Output:</strong> 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abc&quot;, s2 = &quot;bca&quot;
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length &lt;= 20</code></li>
	<li><code>s2.length == s1.length</code></li>
	<li><code>s1</code> and <code>s2</code> contain only lowercase letters from the set <code>{&#39;a&#39;, &#39;b&#39;, &#39;c&#39;, &#39;d&#39;, &#39;e&#39;, &#39;f&#39;}</code>.</li>
	<li><code>s2</code> is an anagram of <code>s1</code>.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kSimilarity(self, s1: str, s2: str) -> int:
        def next(s):
            i = 0
            res = []
            while s[i] == s2[i]:
                i += 1
            for j in range(i + 1, n):
                if s[j] == s2[i] and s[j] != s2[j]:
                    res.append(s[:i] + s[j] + s[i + 1 : j] + s[i] + s[j + 1 :])
            return res

        q = deque([s1])
        vis = {s1}
        ans, n = 0, len(s1)
        while q:
            for _ in range(len(q)):
                s = q.popleft()
                if s == s2:
                    return ans
                for nxt in next(s):
                    if nxt not in vis:
                        vis.add(nxt)
                        q.append(nxt)
            ans += 1
        return -1
```

### **Java**

```java
class Solution {
    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.offer(s1);
        vis.add(s1);
        int ans = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                s1 = q.poll();
                if (s1.equals(s2)) {
                    return ans;
                }
                for (String nxt : next(s1, s2)) {
                    if (!vis.contains(nxt)) {
                        vis.add(nxt);
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private List<String> next(String s, String s2) {
        int i = 0;
        int n = s.length();
        for (; i < n && s.charAt(i) == s2.charAt(i); ++i);
        char[] cs = s.toCharArray();
        List<String> res = new ArrayList<>();
        for (int j = i + 1; j < n; ++j) {
            if (cs[j] == s2.charAt(i) && cs[j] != s2.charAt(j)) {
                swap(cs, i, j);
                res.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return res;
    }

    private void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kSimilarity(string s1, string s2) {
        queue<string> q {{s1}};
        unordered_set<string> vis {{s1}};
        int ans = 0;
        while (!q.empty()) {
            for (int i = q.size(); i; --i) {
                s1 = q.front();
                q.pop();
                if (s1 == s2) return ans;
                for (string nxt : next(s1, s2)) {
                    if (!vis.count(nxt)) {
                        vis.insert(nxt);
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    vector<string> next(string& s, string& s2) {
        int i = 0, n = s.size();
        for (; i < n && s[i] == s2[i]; ++i)
            ;
        vector<string> res;
        for (int j = i + 1; j < n; ++j) {
            if (s[j] == s2[i] && s[j] != s2[j]) {
                swap(s[i], s[j]);
                res.push_back(s);
                swap(s[i], s[j]);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func kSimilarity(s1 string, s2 string) int {
	next := func(s string) []string {
		i := 0
		res := []string{}
		for s[i] == s2[i] {
			i++
		}
		for j := i + 1; j < len(s1); j++ {
			if s[j] == s2[i] && s[j] != s2[j] {
				res = append(res, s[0:i]+string(s[j])+s[i+1:j]+string(s[i])+s[j+1:])
			}
		}
		return res
	}

	q := []string{s1}
	vis := map[string]bool{s1: true}
	ans := 0
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			s1 = q[0]
			q = q[1:]
			if s1 == s2 {
				return ans
			}
			for _, nxt := range next(s1) {
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
		ans++
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
