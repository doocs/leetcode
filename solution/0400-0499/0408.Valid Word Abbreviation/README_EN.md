# [408. Valid Word Abbreviation](https://leetcode.com/problems/valid-word-abbreviation)

[中文文档](/solution/0400-0499/0408.Valid%20Word%20Abbreviation/README.md)

## Description

<p>A string can be <strong>abbreviated</strong> by replacing any number of <strong>non-adjacent</strong>, <strong>non-empty</strong> substrings with their lengths. The lengths <strong>should not</strong> have leading zeros.</p>

<p>For example, a string such as <code>&quot;substitution&quot;</code> could be abbreviated as (but not limited to):</p>

<ul>
	<li><code>&quot;s10n&quot;</code> (<code>&quot;s <u>ubstitutio</u> n&quot;</code>)</li>
	<li><code>&quot;sub4u4&quot;</code> (<code>&quot;sub <u>stit</u> u <u>tion</u>&quot;</code>)</li>
	<li><code>&quot;12&quot;</code> (<code>&quot;<u>substitution</u>&quot;</code>)</li>
	<li><code>&quot;su3i1u2on&quot;</code> (<code>&quot;su <u>bst</u> i <u>t</u> u <u>ti</u> on&quot;</code>)</li>
	<li><code>&quot;substitution&quot;</code> (no substrings replaced)</li>
</ul>

<p>The following are <strong>not valid</strong> abbreviations:</p>

<ul>
	<li><code>&quot;s55n&quot;</code> (<code>&quot;s <u>ubsti</u> <u>tutio</u> n&quot;</code>, the replaced substrings are adjacent)</li>
	<li><code>&quot;s010n&quot;</code> (has leading zeros)</li>
	<li><code>&quot;s0ubstitution&quot;</code> (replaces an empty substring)</li>
</ul>

<p>Given a string <code>word</code> and an abbreviation <code>abbr</code>, return <em>whether the string <strong>matches</strong> the given abbreviation</em>.</p>

<p>A <strong>substring</strong> is a contiguous <strong>non-empty</strong> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;internationalization&quot;, abbr = &quot;i12iz4n&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;internationalization&quot; can be abbreviated as &quot;i12iz4n&quot; (&quot;i <u>nternational</u> iz <u>atio</u> n&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;apple&quot;, abbr = &quot;a2e&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The word &quot;apple&quot; cannot be abbreviated as &quot;a2e&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= abbr.length &lt;= 10</code></li>
	<li><code>abbr</code> consists of lowercase English letters and digits.</li>
	<li>All the integers in <code>abbr</code> will fit in a 32-bit integer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        i = j = 0
        m, n = len(word), len(abbr)
        while i < m:
            if j >= n:
                return False
            if word[i] == abbr[j]:
                i, j = i + 1, j + 1
                continue
            k = j
            while k < n and abbr[k].isdigit():
                k += 1
            t = abbr[j: k]
            if not t.isdigit() or t[0] == '0' or int(t) == 0:
                return False
            i += int(t)
            j = k
        return i == m and j == n
```

### **Java**

```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0;
        while (i < m) {
            if (j >= n) {
                return false;
            }
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;
                ++j;
                continue;
            }
            int k = j;
            while (k < n && Character.isDigit(abbr.charAt(k))) {
                ++k;
            }
            String t = abbr.substring(j, k);
            if (j == k || t.charAt(0) == '0' || Integer.parseInt(t) == 0) {
                return false;
            }
            i += Integer.parseInt(t);
            j = k;
        }
        return i == m && j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validWordAbbreviation(string word, string abbr) {
        int i = 0, j = 0;
        int m = word.size(), n = abbr.size();
        while (i < m) {
            if (j >= n) {
                return false;
            }
            if (word[i] == abbr[j]) {
                ++i;
                ++j;
                continue;
            }
            int k = j;
            while (k < n && isdigit(abbr[k])) {
                ++k;
            }
            string t = abbr.substr(j, k - j);
            if (k == j || t[0] == '0') {
                return false;
            }
            int x = stoi(t);
            if (x == 0) {
                return false;
            }
            i += x;
            j = k;
        }
        return i == m && j == n;
    }
};
```

### **Go**

```go
func validWordAbbreviation(word string, abbr string) bool {
	i, j := 0, 0
	m, n := len(word), len(abbr)
	for i < m {
		if j >= n {
			return false
		}
		if word[i] == abbr[j] {
			i++
			j++
			continue
		}
		k := j
		for k < n && abbr[k] >= '0' && abbr[k] <= '9' {
			k++
		}
		if k == j || abbr[j] == '0' {
			return false
		}
		x, _ := strconv.Atoi(abbr[j:k])
		if x == 0 {
			return false
		}
		i += x
		j = k
	}
	return i == m && j == n
}
```

### **...**

```

```

<!-- tabs:end -->
