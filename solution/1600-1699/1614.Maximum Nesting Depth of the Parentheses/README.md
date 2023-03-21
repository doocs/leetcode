# [1614. 括号的最大嵌套深度](https://leetcode.cn/problems/maximum-nesting-depth-of-the-parentheses)

[English Version](/solution/1600-1699/1614.Maximum%20Nesting%20Depth%20of%20the%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果字符串满足以下条件之一，则可以称之为 <strong>有效括号字符串</strong><strong>（valid parentheses string</strong>，可以简写为 <strong>VPS</strong>）：</p>

<ul>
	<li>字符串是一个空字符串 <code>""</code>，或者是一个不为 <code>"("</code> 或 <code>")"</code> 的单字符。</li>
	<li>字符串可以写为 <code>AB</code>（<code>A</code> 与 <code>B</code>&nbsp;字符串连接），其中 <code>A</code> 和 <code>B</code> 都是 <strong>有效括号字符串</strong> 。</li>
	<li>字符串可以写为 <code>(A)</code>，其中 <code>A</code> 是一个 <strong>有效括号字符串</strong> 。</li>
</ul>

<p>类似地，可以定义任何有效括号字符串&nbsp;<code>S</code> 的 <strong>嵌套深度</strong> <code>depth(S)</code>：</p>

<ul>
	<li><code>depth("") = 0</code></li>
	<li><code>depth(C) = 0</code>，其中 <code>C</code> 是单个字符的字符串，且该字符不是 <code>"("</code> 或者 <code>")"</code></li>
	<li><code>depth(A + B) = max(depth(A), depth(B))</code>，其中 <code>A</code> 和 <code>B</code> 都是 <strong>有效括号字符串</strong></li>
	<li><code>depth("(" + A + ")") = 1 + depth(A)</code>，其中 <code>A</code> 是一个 <strong>有效括号字符串</strong></li>
</ul>

<p>例如：<code>""</code>、<code>"()()"</code>、<code>"()(()())"</code> 都是 <strong>有效括号字符串</strong>（嵌套深度分别为 0、1、2），而 <code>")("</code> 、<code>"(()"</code> 都不是 <strong>有效括号字符串</strong> 。</p>

<p>给你一个 <strong>有效括号字符串</strong> <code>s</code>，返回该字符串的<em> </em><code>s</code> <strong>嵌套深度</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "(1+(2*3)+((<strong>8</strong>)/4))+1"
<strong>输出：</strong>3
<strong>解释：</strong>数字 8 在嵌套的 3 层括号中。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "(1)+((2))+(((<strong>3</strong>)))"
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由数字 <code>0-9</code> 和字符 <code>'+'</code>、<code>'-'</code>、<code>'*'</code>、<code>'/'</code>、<code>'('</code>、<code>')'</code> 组成</li>
	<li>题目数据保证括号表达式 <code>s</code> 是 <strong>有效的括号表达式</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历**

我们可以遍历字符串，维护当前的嵌套深度，遇到左括号时深度加一，并且更新组最大深大；遇到右括号时深度减一。

遍历结束后，返回最大深度即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxDepth(self, s: str) -> int:
        ans = d = 0
        for c in s:
            if c == '(':
                d += 1
                ans = max(ans, d)
            elif c == ')':
                d -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxDepth(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ans = Math.max(ans, ++d);
            } else if (c == ')') {
                --d;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDepth(string s) {
        int ans = 0, d = 0;
        for (char& c : s) {
            if (c == '(') {
                ans = max(ans, ++d);
            } else if (c == ')') {
                --d;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxDepth(s string) (ans int) {
	d := 0
	for _, c := range s {
		if c == '(' {
			d++
			ans = max(ans, d)
		} else if c == ')' {
			d--
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var maxDepth = function (s) {
    let ans = 0;
    let d = 0;
    for (const c of s) {
        if (c === '(') {
            ans = Math.max(ans, ++d);
        } else if (c === ')') {
            --d;
        }
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int MaxDepth(string s) {
        int ans = 0, d = 0;
        foreach(char c in s) {
            if (c == '(') {
                ans = Math.Max(ans, ++d);
            } else if (c == ')') {
                --d;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maxDepth(s: string): number {
    let ans = 0;
    let d = 0;
    for (const c of s) {
        if (c === '(') {
            ans = Math.max(ans, ++d);
        } else if (c === ')') {
            --d;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
