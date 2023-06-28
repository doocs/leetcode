# [290. Word Pattern](https://leetcode.com/problems/word-pattern)

[中文文档](/solution/0200-0299/0290.Word%20Pattern/README.md)

## Description

<p>Given a <code>pattern</code> and a string <code>s</code>, find if <code>s</code>&nbsp;follows the same pattern.</p>

<p>Here <b>follow</b> means a full match, such that there is a bijection between a letter in <code>pattern</code> and a <b>non-empty</b> word in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;abba&quot;, s = &quot;dog cat cat dog&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;abba&quot;, s = &quot;dog cat cat fish&quot;
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;aaaa&quot;, s = &quot;dog cat cat dog&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 300</code></li>
	<li><code>pattern</code> contains only lower-case English letters.</li>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code> contains only lowercase English letters and spaces <code>&#39; &#39;</code>.</li>
	<li><code>s</code> <strong>does not contain</strong> any leading or trailing spaces.</li>
	<li>All the words in <code>s</code> are separated by a <strong>single space</strong>.</li>
</ul>

## Solutions

**Solution 1: Hash Table**

First, we split the string $s$ into a word array $ws$ with spaces. If the length of $pattern$ and $ws$ is not equal, return `false` directly. Otherwise, we use two hash tables $d_1$ and $d_2$ to record the correspondence between each character and word in $pattern$ and $ws$.

Then, we traverse $pattern$ and $ws$. For each character $a$ and word $b$, if there is a mapping for $a$ in $d_1$, and the mapped word is not $b$, or there is a mapping for $b$ in $d_2$, and the mapped character is not $a$, return `false`. Otherwise, we add the mapping of $a$ and $b$ to $d_1$ and $d_2$ respectively.

After the traversal, return `true`.

The time complexity is $O(m + n)$ and the space complexity is $O(m + n)$. Here $m$ and $n$ are the length of $pattern$ and string $s$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        ws = s.split()
        if len(pattern) != len(ws):
            return False
        d1 = {}
        d2 = {}
        for a, b in zip(pattern, ws):
            if (a in d1 and d1[a] != b) or (b in d2 and d2[b] != a):
                return False
            d1[a] = b
            d2[b] = a
        return True
```

### **Java**

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] ws = s.split(" ");
        if (pattern.length() != ws.length) {
            return false;
        }
        Map<Character, String> d1 = new HashMap<>();
        Map<String, Character> d2 = new HashMap<>();
        for (int i = 0; i < ws.length; ++i) {
            char a = pattern.charAt(i);
            String b = ws[i];
            if (!d1.getOrDefault(a, b).equals(b) || d2.getOrDefault(b, a) != a) {
                return false;
            }
            d1.put(a, b);
            d2.put(b, a);
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool wordPattern(string pattern, string s) {
        istringstream is(s);
        vector<string> ws;
        while (is >> s) {
            ws.push_back(s);
        }
        if (pattern.size() != ws.size()) {
            return false;
        }
        unordered_map<char, string> d1;
        unordered_map<string, char> d2;
        for (int i = 0; i < ws.size(); ++i) {
            char a = pattern[i];
            string b = ws[i];
            if ((d1.count(a) && d1[a] != b) || (d2.count(b) && d2[b] != a)) {
                return false;
            }
            d1[a] = b;
            d2[b] = a;
        }
        return true;
    }
};
```

### **Go**

```go
func wordPattern(pattern string, s string) bool {
	ws := strings.Split(s, " ")
	if len(ws) != len(pattern) {
		return false
	}
	d1 := map[rune]string{}
	d2 := map[string]rune{}
	for i, a := range pattern {
		b := ws[i]
		if v, ok := d1[a]; ok && v != b {
			return false
		}
		if v, ok := d2[b]; ok && v != a {
			return false
		}
		d1[a] = b
		d2[b] = a
	}
	return true
}
```

### **TypeScript**

```ts
function wordPattern(pattern: string, s: string): boolean {
    const ws = s.split(' ');
    if (pattern.length !== ws.length) {
        return false;
    }
    const d1 = new Map<string, string>();
    const d2 = new Map<string, string>();
    for (let i = 0; i < pattern.length; ++i) {
        const a = pattern[i];
        const b = ws[i];
        if (d1.has(a) && d1.get(a) !== b) {
            return false;
        }
        if (d2.has(b) && d2.get(b) !== a) {
            return false;
        }
        d1.set(a, b);
        d2.set(b, a);
    }
    return true;
}
```

### **C#**

```cs
public class Solution {
    public bool WordPattern(string pattern, string s) {
        var ws = s.Split(' ');
        if (pattern.Length != ws.Length) {
            return false;
        }
        var d1 = new Dictionary<char, string>();
        var d2 = new Dictionary<string, char>();
        for (int i = 0; i < ws.Length; ++i) {
            var a = pattern[i];
            var b = ws[i];
            if (d1.ContainsKey(a) && d1[a] != b) {
                return false;
            }
            if (d2.ContainsKey(b) && d2[b] != a) {
                return false;
            }
            d1[a] = b;
            d2[b] = a;
        }
        return true;
    }
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn word_pattern(pattern: String, s: String) -> bool {
        let cs1: Vec<char> = pattern.chars().collect();
        let cs2: Vec<&str> = s.split_whitespace().collect();
        let n = cs1.len();
        if n != cs2.len() {
            return false;
        }
        let mut map1 = HashMap::new();
        let mut map2 = HashMap::new();
        for i in 0..n {
            let c = cs1[i];
            let s = cs2[i];
            if !map1.contains_key(&c) {
                map1.insert(c, i);
            }
            if !map2.contains_key(&s) {
                map2.insert(s, i);
            }
            if map1.get(&c) != map2.get(&s) {
                return false
            }
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->
