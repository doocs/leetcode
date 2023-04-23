# [290. 单词规律](https://leetcode.cn/problems/word-pattern)

[English Version](/solution/0200-0299/0290.Word%20Pattern/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一种规律 <code>pattern</code>&nbsp;和一个字符串&nbsp;<code>s</code>&nbsp;，判断 <code>s</code>&nbsp;是否遵循相同的规律。</p>

<p>这里的&nbsp;<strong>遵循&nbsp;</strong>指完全匹配，例如，&nbsp;<code>pattern</code>&nbsp;里的每个字母和字符串&nbsp;<code>s</code><strong>&nbsp;</strong>中的每个非空单词之间存在着双向连接的对应规律。</p>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入:</strong> pattern = <code>"abba"</code>, s = <code>"dog cat cat dog"</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>pattern = <code>"abba"</code>, s = <code>"dog cat cat fish"</code>
<strong>输出:</strong> false</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> pattern = <code>"aaaa"</code>, s = <code>"dog cat cat dog"</code>
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 300</code></li>
	<li><code>pattern</code>&nbsp;只包含小写英文字母</li>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母和&nbsp;<code>' '</code></li>
	<li><code>s</code>&nbsp;<strong>不包含</strong> 任何前导或尾随对空格</li>
	<li><code>s</code>&nbsp;中每个单词都被 <strong>单个空格 </strong>分隔</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们先将字符串 $s$ 按照空格分割成单词数组 $ws$，如果 $pattern$ 和 $ws$ 的长度不相等，直接返回 `false`。否则，我们使用两个哈希表 $d_1$ 和 $d_2$，分别记录 $pattern$ 和 $ws$ 中每个字符和单词的对应关系。

接下来，我们遍历 $pattern$ 和 $ws$，对于每个字符 $a$ 和单词 $b$，如果 $d_1$ 中存在 $a$ 的映射，且映射的单词不是 $b$，或者 $d_2$ 中存在 $b$ 的映射，且映射的字符不是 $a$，则返回 `false`。否则，我们将 $a$ 和 $b$ 的映射分别加入 $d_1$ 和 $d_2$ 中。

遍历结束后，返回 `true`。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别是 $pattern$ 和字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
