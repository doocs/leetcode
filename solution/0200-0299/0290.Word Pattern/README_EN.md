# [290. Word Pattern](https://leetcode.com/problems/word-pattern)

[中文文档](/solution/0200-0299/0290.Word%20Pattern/README.md)

## Description

<p>Given a <code>pattern</code> and a string <code>s</code>, find if <code>s</code>&nbsp;follows the same pattern.</p>

<p>Here <b>follow</b> means a full match, such that there is a bijection between a letter in <code>pattern</code> and a <b>non-empty</b> word in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;abba&quot;, s = &quot;dog cat cat dog&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> pattern = &quot;abba&quot;, s = &quot;dog cat cat fish&quot;
<strong>Output:</strong> false
</pre>

<p><strong>Example 3:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        s = s.split(' ')
        n = len(pattern)
        if n != len(s):
            return False
        c2str, str2c = defaultdict(), defaultdict()
        for i in range(n):
            k, v = pattern[i], s[i]
            if k in c2str and c2str[k] != v:
                return False
            if v in str2c and str2c[v] != k:
                return False
            c2str[k], str2c[v] = v, k
        return True
```

### **Java**

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] ss = s.split(" ");
        int n = pattern.length();
        if (n != ss.length) {
            return false;
        }
        Map<Character, String> c2str = new HashMap<>();
        Map<String, Character> str2c = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char k = pattern.charAt(i);
            String v = ss[i];
            if (c2str.containsKey(k) && !Objects.equals(c2str.get(k), v)) {
                return false;
            }
            if (str2c.containsKey(v) && !Objects.equals(str2c.get(v), k)) {
                return false;
            }
            c2str.put(k, v);
            str2c.put(v, k);
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function wordPattern(pattern: string, s: string): boolean {
    let n = pattern.length;
    let values = s.split(' ');
    if (n != values.length) return false;
    let table = new Array(128);
    for (let i = 0; i < n; i++) {
        let k = pattern.charCodeAt(i),
            v = values[i];
        if (!table[k]) {
            if (table.includes(v)) return false;
            table[k] = v;
        } else {
            if (table[k] != v) return false;
        }
    }
    return true;
}
```

```ts
function wordPattern(pattern: string, s: string): boolean {
    const n = pattern.length;
    const cs = s.split(' ');
    if (n !== cs.length) {
        return false;
    }
    const map1 = new Map<string, number>();
    const map2 = new Map<string, number>();
    for (let i = 0; i < n; i++) {
        const c1 = pattern[i];
        const c2 = cs[i];
        if (!map1.has(c1)) {
            map1.set(c1, i);
        }
        if (!map2.has(c2)) {
            map2.set(c2, i);
        }
        if (map1.get(c1) !== map2.get(c2)) {
            return false;
        }
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool wordPattern(string pattern, string s) {
        istringstream is(s);
        vector<string> ss;
        while (is >> s) ss.push_back(s);
        int n = pattern.size();
        if (n != ss.size()) return false;

        unordered_map<char, string> c2str;
        unordered_map<string, char> str2c;
        for (int i = 0; i < n; ++i) {
            char k = pattern[i];
            string v = ss[i];
            if (c2str.count(k) && c2str[k] != v) return false;
            if (str2c.count(v) && str2c[v] != k) return false;
            c2str[k] = v;
            str2c[v] = k;
        }
        return true;
    }
};
```

### **Go**

```go
func wordPattern(pattern string, s string) bool {
	ss := strings.Split(s, " ")
	n := len(pattern)
	if n != len(ss) {
		return false
	}
	c2str := make(map[byte]string)
	str2c := make(map[string]byte)
	for i := 0; i < n; i++ {
		k, v := pattern[i], ss[i]
		if c2str[k] != "" && c2str[k] != v {
			return false
		}
		if str2c[v] > 0 && str2c[v] != k {
			return false
		}
		c2str[k], str2c[v] = v, k
	}
	return true
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
