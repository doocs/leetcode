# [2486. 追加字符以获得子序列](https://leetcode.cn/problems/append-characters-to-string-to-make-subsequence)

[English Version](/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个仅由小写英文字母组成的字符串 <code>s</code> 和 <code>t</code> 。</p>

<p>现在需要通过向 <code>s</code> 末尾追加字符的方式使 <code>t</code> 变成 <code>s</code> 的一个 <strong>子序列</strong> ，返回需要追加的最少字符数。</p>

<p>子序列是一个可以由其他字符串删除部分（或不删除）字符但不改变剩下字符顺序得到的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "coaching", t = "coding"
<strong>输出：</strong>4
<strong>解释：</strong>向 s 末尾追加字符串 "ding" ，s = "coachingding" 。
现在，t 是 s ("<em><strong>co</strong></em>aching<em><strong>ding</strong></em>") 的一个子序列。
可以证明向 s 末尾追加任何 3 个字符都无法使 t 成为 s 的一个子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcde", t = "a"
<strong>输出：</strong>0
<strong>解释：</strong>t 已经是 s ("<em><strong>a</strong></em>bcde") 的一个子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "z", t = "abcde"
<strong>输出：</strong>5
<strong>解释：</strong>向 s 末尾追加字符串 "abcde" ，s = "zabcde" 。
现在，t 是 s ("z<em><strong>abcde</strong></em>") 的一个子序列。 
可以证明向 s 末尾追加任何 4 个字符都无法使 t 成为 s 的一个子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

定义两个指针 $i$ 和 $j$，分别指向字符串 $s$ 和 $t$ 的首字符。遍历字符串 $t$，当 $s[i] \neq t[j]$ 时，指针 $i$ 后移，直到 $s[i] = t[j]$ 或者 $i$ 到达字符串 $s$ 的末尾。如果 $i$ 到达字符串 $s$ 的末尾，说明 $t$ 中的字符 $t[j]$ 无法在 $s$ 中找到对应的字符，返回 $t$ 中剩余的字符数。否则，将指针 $i$ 和 $j$ 同时后移，继续遍历字符串 $t$。

时间复杂度 $(m + n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $t$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        i = 0
        for j in range(n):
            while i < m and s[i] != t[j]:
                i += 1
            if i == m:
                return n - j
            i += 1
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int appendCharacters(String s, String t) {
        int m = s.length(), n = t.length();
        for (int i = 0, j = 0; j < n; ++j) {
            while (i < m && s.charAt(i) != t.charAt(j)) {
                ++i;
            }
            if (i++ == m) {
                return n - j;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int appendCharacters(string s, string t) {
        int m = s.size(), n = t.size();
        for (int i = 0, j = 0; j < n; ++j) {
            while (i < m && s[i] != t[j]) {
                ++i;
            }
            if (i++ == m) {
                return n - j;
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func appendCharacters(s string, t string) int {
	m, n := len(s), len(t)
	for i, j := 0, 0; j < n; i, j = i+1, j+1 {
		for i < m && s[i] != t[j] {
			i++
		}
		if i == m {
			return n - j
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
