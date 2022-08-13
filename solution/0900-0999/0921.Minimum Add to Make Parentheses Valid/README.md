# [921. 使括号有效的最少添加](https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid)

[English Version](/solution/0900-0999/0921.Minimum%20Add%20to%20Make%20Parentheses%20Valid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>只有满足下面几点之一，括号字符串才是有效的：</p>

<ul>
	<li>它是一个空字符串，或者</li>
	<li>它可以被写成&nbsp;<code>AB</code>&nbsp;（<code>A</code>&nbsp;与&nbsp;<code>B</code>&nbsp;连接）, 其中&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是有效字符串，或者</li>
	<li>它可以被写作&nbsp;<code>(A)</code>，其中&nbsp;<code>A</code>&nbsp;是有效字符串。</li>
</ul>

<p>给定一个括号字符串 <code>s</code> ，移动N次，你就可以在字符串的任何位置插入一个括号。</p>

<ul>
	<li>例如，如果 <code>s = "()))"</code> ，你可以插入一个开始括号为 <code>"(()))"</code> 或结束括号为 <code>"())))"</code> 。</li>
</ul>

<p>返回 <em>为使结果字符串 <code>s</code> 有效而必须添加的最少括号数</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "())"
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "((("
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 只包含&nbsp;<code>'('</code> 和&nbsp;<code>')'</code>&nbsp;字符。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stk = []
        for c in s:
            if c == '(':
                stk.append(c)
            else:
                if stk and stk[-1] == '(':
                    stk.pop()
                else:
                    stk.append(c)
        return len(stk)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stk.push(c);
            } else {
                if (!stk.isEmpty() && stk.peek() == '(') {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            }
        }
        return stk.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minAddToMakeValid(string s) {
        stack<char> stk;
        for (char& c : s) {
            if (c == '(')
                stk.push(c);
            else {
                if (!stk.empty() && stk.top() == '(') {
                    stk.pop();
                } else
                    stk.push(c);
            }
        }
        return stk.size();
    }
};
```

### **Go**

```go
func minAddToMakeValid(s string) int {
	var stk []rune
	for _, c := range s {
		if c == '(' {
			stk = append(stk, c)
		} else {
			if len(stk) > 0 && stk[len(stk)-1] == '(' {
				stk = stk[:len(stk)-1]
			} else {
				stk = append(stk, c)
			}
		}
	}
	return len(stk)
}
```

### **...**

```

```

<!-- tabs:end -->
