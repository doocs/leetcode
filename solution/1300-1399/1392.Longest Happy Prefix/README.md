# [1392. 最长快乐前缀](https://leetcode-cn.com/problems/longest-happy-prefix)

[English Version](/solution/1300-1399/1392.Longest%20Happy%20Prefix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>「快乐前缀」是在原字符串中既是&nbsp;<strong>非空</strong> 前缀也是后缀（不包括原字符串自身）的字符串。</p>

<p>给你一个字符串 <code>s</code>，请你返回它的 <strong>最长快乐前缀</strong>。</p>

<p>如果不存在满足题意的前缀，则返回一个空字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;level&quot;
<strong>输出：</strong>&quot;l&quot;
<strong>解释：</strong>不包括 s 自己，一共有 4 个前缀（&quot;l&quot;, &quot;le&quot;, &quot;lev&quot;, &quot;leve&quot;）和 4 个后缀（&quot;l&quot;, &quot;el&quot;, &quot;vel&quot;, &quot;evel&quot;）。最长的既是前缀也是后缀的字符串是 &quot;l&quot; 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;ababab&quot;
<strong>输出：</strong>&quot;abab&quot;
<strong>解释：</strong>&quot;abab&quot; 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;leetcodeleet&quot;
<strong>输出：</strong>&quot;leet&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;a&quot;
<strong>输出：</strong>&quot;&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code> 只含有小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

字符串哈希。用于计算字符串哈希值，快速判断两个字符串是否相等。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestPrefix(self, s: str) -> str:
        for i in range(1, len(s)):
            if s[:-i] == s[i:]:
                return s[i:]
        return ''
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private long[] p;
    private long[] h;

    public String longestPrefix(String s) {
        int base = 131;
        int n = 100010;
        p = new long[n];
        h = new long[n];
        p[0] = 1;
        for (int i = 1; i <= s.length(); ++i) {
            p[i] = p[i - 1] * base;
            h[i] = h[i - 1] * base + s.charAt(i - 1);
        }
        int l = s.length();
        for (int i = l - 1; i > 0; --i) {
            if (get(1, i) == get(l - i + 1, l)) {
                return s.substring(0, i);
            }
        }
        return "";
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
```

### **C++**

```cpp
typedef unsigned long long ULL;
class Solution {
public:
    string longestPrefix(string s) {
        int base = 131;
        int n = 100010;
        ULL p[100010];
        p[0] = 1;
        ULL h[100010];
        h[0] = 0;
        for (int i = 1; i <= s.size(); i++)
        {
            p[i] = p[i - 1] * base;
            h[i] = h[i - 1] * base + s[i - 1];
        }
        int l = s.size();
        for (int i = l - 1; i >= 1; i--)
        {
            ULL prefix = h[i];
            ULL suffix = h[l] - h[l - i] * p[i];
            if (prefix == suffix)
            {
                return s.substr(0, i);
            }
        }
        return "";
    }
};
```

### **Go**

```go
func longestPrefix(s string) string {
	base := 131
	n := 100010
	p := make([]int, n)
	h := make([]int, n)
	p[0] = 1
	for i := 1; i <= len(s); i++ {
		p[i] = p[i-1] * base
		h[i] = h[i-1]*base + int(s[i-1])
	}
	l := len(s)
	for i := l - 1; i > 0; i-- {
		prefix := h[i]
		suffix := h[l] - h[l-i]*p[i]
		if prefix == suffix {
			return s[:i]
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
