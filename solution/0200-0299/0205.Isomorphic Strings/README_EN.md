# [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings)

[中文文档](/solution/0200-0299/0205.Isomorphic%20Strings/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, <em>determine if they are isomorphic</em>.</p>

<p>Two strings <code>s</code> and <code>t</code> are isomorphic if the characters in <code>s</code> can be replaced to get <code>t</code>.</p>

<p>All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "egg", t = "add"
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "foo", t = "bar"
<strong>Output:</strong> false
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "paper", t = "title"
<strong>Output:</strong> true
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>s</code> and <code>t</code> consist of any valid ascii character.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        d1, d2 = {}, {}
        for a, b in zip(s, t):
            if a in d1 and d1[a] != b:
                return False
            if b in d2 and d2[b] != a:
                return False
            d1[a] = b
            d2[b] = a
        return True
```

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        d1, d2 = [0] * 256, [0] * 256
        for i, (a, b) in enumerate(zip(s, t)):
            a, b = ord(a), ord(b)
            if d1[a] != d2[b]:
                return False
            d1[a] = d2[b] = i + 1
        return True
```

### **Java**

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> d1 = new HashMap<>();
        Map<Character, Character> d2 = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1.containsKey(a) && d1.get(a) != b) {
                return false;
            }
            if (d2.containsKey(b) && d2.get(b) != a) {
                return false;
            }
            d1.put(a, b);
            d2.put(b, a);
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] d1 = new int[256];
        int[] d2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = i + 1;
            d2[b] = i + 1;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        vector<int> d1(256);
        vector<int> d2(256);
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            char a = s[i], b = t[i];
            if (d1[a] != d2[b]) return false;
            d1[a] = d2[b] = i + 1;
        }
        return true;
    }
};
```

### **Go**

```go
func isIsomorphic(s string, t string) bool {
	d1, d2 := make([]int, 256), make([]int, 256)
	for i, a := range s {
		b := t[i]
		if d1[a] != d2[b] {
			return false
		}
		d1[a], d2[b] = i+1, i+1
	}
	return true
}
```

### **C#**

```cs
public class Solution {
    public bool IsIsomorphic(string s, string t) {
        var d1 = new Dictionary<char, char>();
        var d2 = new Dictionary<char, char>();
        for (var i = 0; i < s.Length; ++i)
        {
            char mapping1;
            char mapping2;
            var found1 = d1.TryGetValue(s[i], out mapping1);
            var found2 = d2.TryGetValue(t[i], out mapping2);
            if (found1 ^ found2) return false;
            if (!found1)
            {
                d1.Add(s[i], t[i]);
                d2.Add(t[i], s[i]);
            }
            else if (mapping1 != t[i] || mapping2 != s[i])
            {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function isIsomorphic(s: string, t: string): boolean {
    const n = s.length;
    const help = (s: string, t: string) => {
        const map = new Map();
        for (let i = 0; i < n; i++) {
            if (map.has(s[i])) {
                if (map.get(s[i]) !== t[i]) {
                    return false;
                }
            } else {
                map.set(s[i], t[i]);
            }
        }
        return true;
    };
    return help(s, t) && help(t, s);
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    fn help(s: &[u8], t: &[u8]) -> bool {
        let mut map = HashMap::new();
        for i in 0..s.len() {
            if map.contains_key(&s[i]) {
                if map.get(&s[i]).unwrap() != &t[i] {
                    return false;
                }
            } else {
                map.insert(s[i], t[i]);
            }
        }
        true
    }

    pub fn is_isomorphic(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        Self::help(s, t) && Self::help(t, s)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
