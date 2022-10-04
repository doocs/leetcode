# [1003. 检查替换后的词是否有效](https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions)

[English Version](/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个字符串 <code>s</code> ，请你判断它是否 <strong>有效</strong> 。

<p>字符串 <code>s</code> <strong>有效</strong> 需要满足：假设开始有一个空字符串 <code>t = ""</code> ，你可以执行 <strong>任意次</strong> 下述操作将<strong> </strong><code>t</code><strong> 转换为 </strong><code>s</code> ：</p>

<ul>
	<li>将字符串 <code>"abc"</code> 插入到 <code>t</code> 中的任意位置。形式上，<code>t</code> 变为 <code>t<sub>left</sub> + "abc" + t<sub>right</sub></code>，其中 <code>t == t<sub>left</sub> + t<sub>right</sub></code> 。注意，<code>t<sub>left</sub></code> 和 <code>t<sub>right</sub></code> 可能为 <strong>空</strong> 。</li>
</ul>

<p>如果字符串 <code>s</code> 有效，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabcbc"
<strong>输出：</strong>true
<strong>解释：</strong>
"" -&gt; "<strong>abc</strong>" -&gt; "a<strong>abc</strong>bc"
因此，"aabcbc" 有效。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcabcababcc"
<strong>输出：</strong>true
<strong>解释：</strong>
"" -&gt; "<strong>abc</strong>" -&gt; "abc<strong>abc</strong>" -&gt; "abcabc<strong>abc</strong>" -&gt; "abcabcab<strong>abc</strong>c"
因此，"abcabcababcc" 有效。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abccba"
<strong>输出：</strong>false
<strong>解释：</strong>执行操作无法得到 "abccba" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由字母 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈模拟**

先判断字符串长度是否为 $3$ 的倍数，若不是直接返回 `false`。

接下来我们使用栈模拟操作，遍历字符串 $s$ 的每个字符 $c$：

若 $c$ 等于 `'c'`，且栈顶的两个元素分别为 `'a'` 和 `'b'`，则将栈顶的两个元素出栈；否则将 $c$ 入栈。

最后判断栈是否为空，若为空则返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) % 3:
            return False
        stk = []
        for c in s:
            if c == 'c' and len(stk) > 1 and stk[-2] == 'a' and stk[-1] == 'b':
                stk = stk[:-2]
            else:
                stk.append(c)
        return not stk
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 3 > 0) {
            return false;
        }
        StringBuilder stk = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = stk.length();
            if (c == 'c' && n > 1 && stk.charAt(n - 2) == 'a' && stk.charAt(n - 1) == 'b') {
                stk.deleteCharAt(n - 1);
                stk.deleteCharAt(n - 2);
            } else {
                stk.append(c);
            }
        }
        return stk.length() == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isValid(string s) {
        if (s.size() % 3) {
            return false;
        }
        string stk;
        for (char c : s) {
            int n = stk.size();
            if (c == 'c' && n > 1 && stk[n - 2] == 'a' && stk[n - 1] == 'b') {
                stk.pop_back();
                stk.pop_back();
            } else {
                stk.push_back(c);
            }
        }
        return stk.empty();
    }
};
```

### **Go**

```go
func isValid(s string) bool {
	if len(s)%3 > 0 {
		return false
	}
	stk := []rune{}
	for _, c := range s {
		n := len(stk)
		if c == 'c' && n > 1 && stk[n-2] == 'a' && stk[n-1] == 'b' {
			stk = stk[:n-2]
		} else {
			stk = append(stk, c)
		}
	}
	return len(stk) == 0
}
```

### **...**

```

```

<!-- tabs:end -->
