# [791. Custom Sort String](https://leetcode.com/problems/custom-sort-string)

[中文文档](/solution/0700-0799/0791.Custom%20Sort%20String/README.md)

## Description

<p>You are given two strings order and s. All the characters of <code>order</code> are <strong>unique</strong> and were sorted in some custom order previously.</p>

<p>Permute the characters of <code>s</code> so that they match the order that <code>order</code> was sorted. More specifically, if a character <code>x</code> occurs before a character <code>y</code> in <code>order</code>, then <code>x</code> should occur before <code>y</code> in the permuted string.</p>

<p>Return <em>any permutation of </em><code>s</code><em> that satisfies this property</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> order = &quot;cba&quot;, s = &quot;abcd&quot;
<strong>Output:</strong> &quot;cbad&quot;
<strong>Explanation:</strong> 
&quot;a&quot;, &quot;b&quot;, &quot;c&quot; appear in order, so the order of &quot;a&quot;, &quot;b&quot;, &quot;c&quot; should be &quot;c&quot;, &quot;b&quot;, and &quot;a&quot;. 
Since &quot;d&quot; does not appear in order, it can be at any position in the returned string. &quot;dcba&quot;, &quot;cdba&quot;, &quot;cbda&quot; are also valid outputs.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> order = &quot;cbafg&quot;, s = &quot;abcd&quot;
<strong>Output:</strong> &quot;cbad&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= order.length &lt;= 26</code></li>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>order</code> and <code>s</code> consist of lowercase English letters.</li>
	<li>All the characters of <code>order</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        cs = list(s)
        m = {v: i for i, v in enumerate(order)}
        cs.sort(key=lambda x: m.get(x, -1))
        return ''.join(cs)
```

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        cnt = Counter(s)
        ans = []
        for c in order:
            ans.append(cnt[c] * c)
            cnt[c] = 0
        for c in s:
            ans.append(cnt[c] * c)
            cnt[c] = 0
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String customSortString(String order, String s) {
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < order.length(); ++i) {
            m.put(order.charAt(i), i);
        }
        List<Character> cs = new ArrayList<>();
        for (char c : s.toCharArray()) {
            cs.add(c);
        }
        cs.sort((a, b) -> m.getOrDefault(a, -1) - m.getOrDefault(b, -1));
        StringBuilder ans = new StringBuilder();
        for (char c : cs) {
            ans.append(c);
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        for (char c : s.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string customSortString(string order, string s) {
        vector<int> cnt(26);
        for (char c : s) ++cnt[c - 'a'];
        string ans = "";
        for (char c : order) {
            while (cnt[c - 'a']-- > 0) {
                ans += c;
            }
        }
        for (char c : s) {
            while (cnt[c - 'a']-- > 0) {
                ans += c;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func customSortString(order string, s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	ans := []rune{}
	for _, c := range order {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	for _, c := range s {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
