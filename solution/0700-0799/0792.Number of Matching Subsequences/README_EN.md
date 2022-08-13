# [792. Number of Matching Subsequences](https://leetcode.com/problems/number-of-matching-subsequences)

[中文文档](/solution/0700-0799/0792.Number%20of%20Matching%20Subsequences/README.md)

## Description

<p>Given a string <code>s</code> and an array of strings <code>words</code>, return <em>the number of</em> <code>words[i]</code> <em>that is a subsequence of</em> <code>s</code>.</p>

<p>A <strong>subsequence</strong> of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.</p>

<ul>
	<li>For example, <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;abcde&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcde&quot;, words = [&quot;a&quot;,&quot;bb&quot;,&quot;acd&quot;,&quot;ace&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three strings in words that are a subsequence of s: &quot;a&quot;, &quot;acd&quot;, &quot;ace&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dsahjpjauf&quot;, words = [&quot;ahjpjau&quot;,&quot;ja&quot;,&quot;ahbwzgqnuk&quot;,&quot;tnmlanowax&quot;]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numMatchingSubseq(self, s: str, words: List[str]) -> int:
        buckets = defaultdict(list)
        for word in words:
            buckets[word[0]].append(word)
        res = 0
        for c in s:
            old = buckets[c][::1]
            buckets[c].clear()
            for t in old:
                if len(t) == 1:
                    res += 1
                else:
                    buckets[t[1]].append(t[1:])
        return res
```

### **Java**

```java
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<String>[] buckets = new List[26];
        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new ArrayList<>();
        }
        for (String word : words) {
            buckets[word.charAt(0) - 'a'].add(word);
        }
        int res = 0;
        for (char c : s.toCharArray()) {
            List<String> old = new ArrayList<>(buckets[c - 'a']);
            buckets[c - 'a'].clear();
            for (String t : old) {
                if (t.length() == 1) {
                    ++res;
                } else {
                    buckets[t.charAt(1) - 'a'].add(t.substring(1));
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        vector<vector<string>> buckets(26);
        for (auto word : words) buckets[word[0] - 'a'].push_back(word);
        int res = 0;
        for (auto c : s) {
            auto old = buckets[c - 'a'];
            buckets[c - 'a'].clear();
            for (auto t : old) {
                if (t.size() == 1)
                    ++res;
                else
                    buckets[t[1] - 'a'].push_back(t.substr(1));
            }
        }
        return res;
    }
};
```

### **Go**

```go
func numMatchingSubseq(s string, words []string) int {
	buckets := make([][]string, 26)
	for _, word := range words {
		buckets[word[0]-'a'] = append(buckets[word[0]-'a'], word)
	}
	res := 0
	for _, c := range s {
		old := buckets[c-'a']
		buckets[c-'a'] = nil
		for _, t := range old {
			if len(t) == 1 {
				res++
			} else {
				buckets[t[1]-'a'] = append(buckets[t[1]-'a'], t[1:])
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
