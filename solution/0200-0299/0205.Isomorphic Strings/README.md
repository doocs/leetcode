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

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
