# [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences)

[中文文档](/solution/0100-0199/0187.Repeated%20DNA%20Sequences/README.md)

## Description

<p>The <strong>DNA sequence</strong> is composed of a series of nucleotides abbreviated as <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, and <code>&#39;T&#39;</code>.</p>

<ul>
	<li>For example, <code>&quot;ACGAATTCCG&quot;</code> is a <strong>DNA sequence</strong>.</li>
</ul>

<p>When studying <strong>DNA</strong>, it is useful to identify repeated sequences within the DNA.</p>

<p>Given a string <code>s</code> that represents a <strong>DNA sequence</strong>, return all the <strong><code>10</code>-letter-long</strong> sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>Output:</strong> ["AAAAACCCCC","CCCCCAAAAA"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "AAAAAAAAAAAAA"
<strong>Output:</strong> ["AAAAAAAAAA"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;A&#39;</code>, <code>&#39;C&#39;</code>, <code>&#39;G&#39;</code>, or <code>&#39;T&#39;</code>.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        n = 10
        subs = set()
        res = set()
        for i in range(len(s) - n + 1):
            sub = s[i:i + n]
            if sub in subs:
                res.add(sub)
            subs.add(sub)
        return list(res)
```

### **Java**

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = 10;
        Set<String> subs = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < s.length() - len + 1; ++i) {
            String sub = s.substring(i, i + len);
            if (subs.contains(sub)) {
                res.add(sub);
            }
            subs.add(sub);
        }
        return new ArrayList<>(res);
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function(s) {
    let n = 10;
    let subs = new Set();
    let res = new Set();
    for (let i = 0; i < s.length - n + 1; i++) {
        let sub = s.slice(i, i + n);
        if (subs.has(sub)) {
            res.add(sub);
        }
        subs.add(sub);
    }
    return [...res];
};
```

### **Go**

```go
func findRepeatedDnaSequences(s string) []string {
	cnt := make(map[string]int)
	n := len(s) - 10
	ans := make([]string, 0)
	for i := 0; i <= n; i++ {
		sub := s[i : i+10]
		cnt[sub]++
		if cnt[sub] == 2 {
			ans = append(ans, sub)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        map<string, int> cnt;
        int n = s.size() - 10;
        vector<string> ans;
        for (int i = 0; i <= n; ++i) {
            string sub = s.substr(i, 10);
            if (++cnt[sub] == 2) {
                ans.push_back(sub);
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
