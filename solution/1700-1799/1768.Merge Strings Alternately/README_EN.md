# [1768. Merge Strings Alternately](https://leetcode.com/problems/merge-strings-alternately)

[中文文档](/solution/1700-1799/1768.Merge%20Strings%20Alternately/README.md)

## Description

<p>You are given two strings <code>word1</code> and <code>word2</code>. Merge the strings by adding letters in alternating order, starting with <code>word1</code>. If a string is longer than the other, append the additional letters onto the end of the merged string.</p>

<p>Return <em>the merged string.</em></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> word1 = &quot;abc&quot;, word2 = &quot;pqr&quot;

<strong>Output:</strong> &quot;apbqcr&quot;

<strong>Explanation:</strong>&nbsp;The merged string will be merged as so:

word1:  a   b   c

word2:    p   q   r

merged: a p b q c r

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> word1 = &quot;ab&quot;, word2 = &quot;pqrs&quot;

<strong>Output:</strong> &quot;apbqrs&quot;

<strong>Explanation:</strong>&nbsp;Notice that as word2 is longer, &quot;rs&quot; is appended to the end.

word1:  a   b 

word2:    p   q   r   s

merged: a p b q   r   s

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> word1 = &quot;abcd&quot;, word2 = &quot;pq&quot;

<strong>Output:</strong> &quot;apbqcd&quot;

<strong>Explanation:</strong>&nbsp;Notice that as word1 is longer, &quot;cd&quot; is appended to the end.

word1:  a   b   c   d

word2:    p   q 

merged: a p b q c   d

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= word1.length, word2.length &lt;= 100</code></li>
    <li><code>word1</code> and <code>word2</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mergeAlternately(self, word1: str, word2: str) -> str:
        i, m, n = 0, len(word1), len(word2)
        res = []
        while i < m or i < n:
            if i < m:
                res.append(word1[i])
            if i < n:
                res.append(word2[i])
            i += 1
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) {
                res.append(word1.charAt(i));
            }
            if (i < n) {
                res.append(word2.charAt(i));
            }
        }
        return res.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string mergeAlternately(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        string res;
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) {
                res.push_back(word1[i]);
            }
            if (i < n) {
                res.push_back(word2[i]);
            }
        }
        return res;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn merge_alternately(word1: String, word2: String) -> String {
        let s1 = word1.as_bytes();
        let s2 = word2.as_bytes();
        let n = s1.len().max(s2.len());
        let mut res = vec![];
        for i in 0..n {
            if s1.get(i).is_some() {
                res.push(s1[i]);
            }
            if s2.get(i).is_some() {
                res.push(s2[i]);
            }
        }
        String::from_utf8(res).unwrap()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
