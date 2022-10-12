# [1941. Check if All Characters Have Equal Number of Occurrences](https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences)

[中文文档](/solution/1900-1999/1941.Check%20if%20All%20Characters%20Have%20Equal%20Number%20of%20Occurrences/README.md)

## Description

<p>Given a string <code>s</code>, return <code>true</code><em> if </em><code>s</code><em> is a <strong>good</strong> string, or </em><code>false</code><em> otherwise</em>.</p>

<p>A string <code>s</code> is <strong>good</strong> if <strong>all</strong> the characters that appear in <code>s</code> have the <strong>same</strong> number of occurrences (i.e., the same frequency).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacbc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The characters that appear in s are &#39;a&#39;, &#39;b&#39;, and &#39;c&#39;. All characters occur 2 times in s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabb&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The characters that appear in s are &#39;a&#39; and &#39;b&#39;.
&#39;a&#39; occurs 3 times while &#39;b&#39; occurs 2 times, which is not the same number of times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def areOccurrencesEqual(self, s: str) -> bool:
        cnt = Counter(s)
        return len(set(cnt.values())) == 1
```

### **Java**

```java
class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int t = 0;
        for (int v : cnt) {
            if (v == 0) {
                continue;
            }
            if (t == 0) {
                t = v;
            } else if (t != v) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areOccurrencesEqual(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        unordered_set<int> ss;
        for (int& v : cnt)
            if (v) ss.insert(v);
        return ss.size() == 1;
    }
};
```

### **Go**

```go
func areOccurrencesEqual(s string) bool {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	ss := map[int]bool{}
	for _, v := range cnt {
		if v == 0 {
			continue
		}
		ss[v] = true
	}
	return len(ss) == 1
}
```

### **...**

```

```

<!-- tabs:end -->
