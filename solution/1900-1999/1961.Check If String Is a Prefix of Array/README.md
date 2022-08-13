# [1961. 检查字符串是否为数组前缀](https://leetcode.cn/problems/check-if-string-is-a-prefix-of-array)

[English Version](/solution/1900-1999/1961.Check%20If%20String%20Is%20a%20Prefix%20of%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符串数组 <code>words</code> ，请你判断 <code>s</code> 是否为 <code>words</code> 的 <strong>前缀字符串</strong> 。</p>

<p>字符串 <code>s</code> 要成为 <code>words</code> 的 <strong>前缀字符串</strong> ，需要满足：<code>s</code> 可以由 <code>words</code> 中的前 <code>k</code>（<code>k</code> 为 <strong>正数</strong> ）个字符串按顺序相连得到，且 <code>k</code> 不超过 <code>words.length</code> 。</p>

<p>如果 <code>s</code> 是 <code>words</code> 的 <strong>前缀字符串</strong> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "iloveleetcode", words = ["i","love","leetcode","apples"]
<strong>输出：</strong>true
<strong>解释：</strong>
s 可以由 "i"、"love" 和 "leetcode" 相连得到。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "iloveleetcode", words = ["apples","i","love","leetcode"]
<strong>输出：</strong>false
<strong>解释：</strong>
数组的前缀相连无法得到 s 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>words[i]</code> 和 <code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPrefixString(self, s: str, words: List[str]) -> bool:
        t = 0
        for i, w in enumerate(words):
            t += len(w)
            if len(s) == t:
                return ''.join(words[: i + 1]) == s
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder t = new StringBuilder();
        for (String w : words) {
            t.append(w);
            if (s.length() == t.length()) {
                return s.equals(t.toString());
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPrefixString(string s, vector<string>& words) {
        string t = "";
        for (string& w : words) {
            t += w;
            if (t.size() == s.size()) return t == s;
        }
        return false;
    }
};
```

### **Go**

```go
func isPrefixString(s string, words []string) bool {
	t := ""
	for _, w := range words {
		t += w
		if t == s {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
