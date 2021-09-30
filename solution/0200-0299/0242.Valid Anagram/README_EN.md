# [242. Valid Anagram](https://leetcode.com/problems/valid-anagram)

[中文文档](/solution/0200-0299/0242.Valid%20Anagram/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, return <code>true</code> <em>if</em> <code>t</code> <em>is an anagram of</em> <code>s</code><em>, and</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "anagram", t = "nagaram"
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "rat", t = "car"
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if the inputs contain Unicode characters? How would you adapt your solution to such a case?</p>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        n = len(s)
        chars = [0] * 26
        for i in range(n):
            chars[ord(s[i]) - ord('a')] += 1
            chars[ord(t[i]) - ord('a')] -= 1
        for c in chars:
            if c != 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int n;
        if ((n = s.length()) != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < n; ++i) {
            ++chars[s.charAt(i) - 'a'];
            --chars[t.charAt(i) - 'a'];
        }
        for (int c : chars) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function isAnagram(s: string, t: string): boolean {
    if (s.length != t.length) return false;
    let record = new Array(26).fill(0);
    let base = 'a'.charCodeAt(0);
    for (let i = 0; i < s.length; ++i) {
        ++record[s.charCodeAt(i) - base];
        --record[t.charCodeAt(i) - base];
    }
    return record.every(v => v == 0);
};
```

### **C++**

```cpp
class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.size() != t.size())
            return false;
        vector<int> chars(26, 0);
        for (int i = 0, n = s.size(); i < n; ++i)
        {
            ++chars[s[i] - 'a'];
            --chars[t[i] - 'a'];
        }
        for (int c : chars)
        {
            if (c != 0)
                return false;
        }
        return true;
    }
};
```

### **Go**

```go
func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	var chars [26]int
	for i := 0; i < len(s); i++ {
		chars[s[i]-'a']++
		chars[t[i]-'a']--
	}
	for _, c := range chars {
		if c != 0 {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
