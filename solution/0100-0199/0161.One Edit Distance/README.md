# [161. 相隔为 1 的编辑距离](https://leetcode.cn/problems/one-edit-distance)

[English Version](/solution/0100-0199/0161.One%20Edit%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <code>s</code> 和&nbsp;<code>t</code> ，如果它们的编辑距离为 <code>1</code> ，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>字符串 <code>s</code> 和字符串 <code>t</code> 之间满足编辑距离等于 1 有三种可能的情形：</p>

<ul>
	<li>往 <code>s</code>&nbsp;中插入 <strong>恰好一个</strong> 字符得到 <code>t</code></li>
	<li>从 <code>s</code>&nbsp;中删除 <strong>恰好一个</strong> 字符得到 <code>t</code></li>
	<li>在 <code>s</code>&nbsp;中用 <strong>一个不同的字符</strong> 替换 <strong>恰好一个</strong> 字符得到 <code>t</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong><strong><em>s</em></strong> = "ab", <strong><em>t</em></strong> = "acb"
<strong>输出: </strong>true
<strong>解释: </strong>可以将 'c' 插入字符串 <strong><em>s</em></strong>&nbsp;来得到 <em><strong>t</strong></em>。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong><strong><em>s</em></strong> = "cab", <strong><em>t</em></strong> = "ad"
<strong>输出: </strong>false
<strong>解释: </strong>无法通过 1 步操作使 <em><strong>s</strong></em> 变为 <em><strong>t</strong></em>。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt;= s.length, t.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 和&nbsp;<code>t</code>&nbsp;由小写字母，大写字母和数字组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分情况讨论**

记 $m$ 表示字符串 $s$ 的长度，$n$ 表示字符串 $t$ 的长度。我们可以假定 $m$ 恒大于等于 $n$。

若 $m-n\gt1$，直接返回 false；

否则，遍历 $s$ 和 $t$，若遇到 $s[i]$ 不等于 $t[i]$：

-   若 $m \neq n$，比较 $s[i+1:]$ 与 $t[i:]$，相等返回 true，否则返回 false；
-   若 $m = n$，比较 $s[i:]$ 与 $t[i:]$，相等返回 true，否则返回 false。

遍历结束，说明遍历过的 $s$ 跟 $t$ 所有字符相等，此时需要满足 $m=n+1$。

时间复杂度 $O(m)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isOneEditDistance(self, s: str, t: str) -> bool:
        if len(s) < len(t):
            return self.isOneEditDistance(t, s)
        m, n = len(s), len(t)
        if m - n > 1:
            return False
        for i, c in enumerate(t):
            if c != s[i]:
                return s[i + 1 :] == t[i + 1 :] if m == n else s[i + 1 :] == t[i:]
        return m == n + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return isOneEditDistance(t, s);
        }
        if (m - n > 1) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                return s.substring(i + 1).equals(t.substring(i));
            }
        }
        return m == n + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isOneEditDistance(string s, string t) {
        int m = s.size(), n = t.size();
        if (m < n) return isOneEditDistance(t, s);
        if (m - n > 1) return false;
        for (int i = 0; i < n; ++i) {
            if (s[i] != t[i]) {
                if (m == n) return s.substr(i + 1) == t.substr(i + 1);
                return s.substr(i + 1) == t.substr(i);
            }
        }
        return m == n + 1;
    }
};
```

### **Go**

```go
func isOneEditDistance(s string, t string) bool {
	m, n := len(s), len(t)
	if m < n {
		return isOneEditDistance(t, s)
	}
	if m-n > 1 {
		return false
	}
	for i := range t {
		if s[i] != t[i] {
			if m == n {
				return s[i+1:] == t[i+1:]
			}
			return s[i+1:] == t[i:]
		}
	}
	return m == n+1
}
```

### **...**

```

```

<!-- tabs:end -->
