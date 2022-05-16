# [205. 同构字符串](https://leetcode.cn/problems/isomorphic-strings)

[English Version](/solution/0200-0299/0205.Isomorphic%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;，判断它们是否是同构的。</p>

<p>如果&nbsp;<code>s</code>&nbsp;中的字符可以按某种映射关系替换得到&nbsp;<code>t</code>&nbsp;，那么这两个字符串是同构的。</p>

<p>每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>s = <code>"egg", </code>t = <code>"add"</code>
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = <code>"foo", </code>t = <code>"bar"</code>
<strong>输出：</strong>false</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = <code>"paper", </code>t = <code>"title"</code>
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;由任意有效的 ASCII 字符组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        a2b, b2a = {}, {}
        n = len(s)
        for i in range(n):
            a, b = s[i], t[i]
            if (a in a2b and a2b[a] != b) or (b in b2a and b2a[b] != a):
                return False
            a2b[a] = b
            b2a[b] = a
        return True
```

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        m1, m2 = [0] * 256, [0] * 256
        for i in range(len(s)):
            c1, c2 = ord(s[i]), ord(t[i])
            if m1[c1] != m2[c2]:
                return False
            m1[c1] = m2[c2] = i + 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> a2b = new HashMap<>();
        Map<Character, Character> b2a = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if ((a2b.containsKey(a) && a2b.get(a) != b) || (b2a.containsKey(b) && b2a.get(b) != a)) return false;
            a2b.put(a, b);
            b2a.put(b, a);
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (m1[c1] != m2[c2]) {
                return false;
            }
            m1[c1] = i + 1;
            m2[c2] = i + 1;
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
        vector<int> m1(256);
        vector<int> m2(256);
        for (int i = 0; i < s.size(); ++i)
        {
            if (m1[s[i]] != m2[t[i]]) return 0;
            m1[s[i]] = i + 1;
            m2[t[i]] = i + 1;
        }
        return 1;
    }
};
```

### **Go**

```go
func isIsomorphic(s string, t string) bool {
	m1, m2 := make([]int, 256), make([]int, 256)
	for i := 0; i < len(s); i++ {
		if m1[s[i]] != m2[t[i]] {
			return false
		}
		m1[s[i]] = i + 1
		m2[t[i]] = i + 1
	}
	return true
}
```

### **C#**

```cs
using System.Collections.Generic;

public class Solution {
    public bool IsIsomorphic(string s, string t) {
        if (s.Length != t.Length) return false;
        var dict1 = new Dictionary<char, char>();
        var dict2 = new Dictionary<char, char>();
        for (var i = 0; i < s.Length; ++i)
        {
            char mapping1;
            char mapping2;
            var found1 = dict1.TryGetValue(s[i], out mapping1);
            var found2 = dict2.TryGetValue(t[i], out mapping2);
            if (found1 ^ found2) return false;
            if (!found1)
            {
                dict1.Add(s[i], t[i]);
                dict2.Add(t[i], s[i]);
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
