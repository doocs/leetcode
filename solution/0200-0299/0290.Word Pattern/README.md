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
        ch2str, str2ch = {}, {}
        ss = s.split(' ')
        n = len(pattern)
        if n != len(ss):
            return False
        for i in range(n):
            if ch2str.get(pattern[i]) is not None and ch2str.get(pattern[i]) != ss[i]:
                return False
            if str2ch.get(ss[i]) is not None and str2ch.get(ss[i]) != pattern[i]:
                return False
            ch2str[pattern[i]] = ss[i]
            str2ch[ss[i]] = pattern[i]
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> ch2str = new HashMap<>();
        Map<String, Character> str2ch = new HashMap<>();
        String[] ss = s.split(" ");
        int n = pattern.length();
        if (n != ss.length) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            char ch = pattern.charAt(i);
            if (ch2str.containsKey(ch) && !ch2str.get(ch).equals(ss[i])) {
                return false;
            }
            if (str2ch.containsKey(ss[i]) && !str2ch.get(ss[i]).equals(ch)) {
                return false;
            }
            ch2str.put(ch, ss[i]);
            str2ch.put(ss[i], ch);
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
        let k = pattern.charCodeAt(i), v = values[i];
        if (!table[k]) {
            if (table.includes(v)) return false;
            table[k] = v;
        } else {
            if (table[k] != v) return false;
        }
    }
    return true;
};
```

### **...**

```

```

<!-- tabs:end -->
