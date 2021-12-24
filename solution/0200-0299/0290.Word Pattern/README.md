# [290. 单词规律](https://leetcode-cn.com/problems/word-pattern)

[English Version](/solution/0200-0299/0290.Word%20Pattern/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一种规律 <code>pattern</code>&nbsp;和一个字符串&nbsp;<code>str</code>&nbsp;，判断 <code>str</code> 是否遵循相同的规律。</p>

<p>这里的&nbsp;<strong>遵循&nbsp;</strong>指完全匹配，例如，&nbsp;<code>pattern</code>&nbsp;里的每个字母和字符串&nbsp;<code>str</code><strong>&nbsp;</strong>中的每个非空单词之间存在着双向连接的对应规律。</p>

<p><strong>示例1:</strong></p>

<pre><strong>输入:</strong> pattern = <code>&quot;abba&quot;</code>, str = <code>&quot;dog cat cat dog&quot;</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong>pattern = <code>&quot;abba&quot;</code>, str = <code>&quot;dog cat cat fish&quot;</code>
<strong>输出:</strong> false</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> pattern = <code>&quot;aaaa&quot;</code>, str = <code>&quot;dog cat cat dog&quot;</code>
<strong>输出:</strong> false</pre>

<p><strong>示例&nbsp;4:</strong></p>

<pre><strong>输入:</strong> pattern = <code>&quot;abba&quot;</code>, str = <code>&quot;dog dog dog dog&quot;</code>
<strong>输出:</strong> false</pre>

<p><strong>说明:</strong><br>
你可以假设&nbsp;<code>pattern</code>&nbsp;只包含小写字母，&nbsp;<code>str</code>&nbsp;包含了由单个空格分隔的小写字母。&nbsp; &nbsp;&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    let values = s.split(" ");
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
        for (int i = 0; i < n; ++i)
        {
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

### **...**

```

```

<!-- tabs:end -->
