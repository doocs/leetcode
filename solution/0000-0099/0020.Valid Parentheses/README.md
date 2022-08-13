# [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses)

[English Version](/solution/0000-0099/0020.Valid%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code> 的字符串 <code>s</code> ，判断字符串是否有效。</p>

<p>有效字符串需满足：</p>

<ol>
	<li>左括号必须用相同类型的右括号闭合。</li>
	<li>左括号必须以正确的顺序闭合。</li>
</ol>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "()"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "()[]{}"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "(]"
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "([)]"
<strong>输出：</strong>false
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "{[]}"
<strong>输出：</strong>true</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

栈实现。

遍历括号字符串 `s`：

-   遇到左括号时，将右括号压入栈中；
-   遇到右括号时，弹出栈顶元素（若栈为空，直接返回 `false`），判断是否是相等。若不匹配，直接返回 `false`。

也可以选择：

-   遇到左括号时，压入当前的左括号。
-   遇到右括号时，弹出栈顶元素（若栈为空，直接返回 `false`），判断是否是匹配，若不匹配，直接返回 `false`。

> 两者的区别仅限于括号转换时机，一个是在入栈时，一个是在出栈时。

遍历结束，若栈为空，说明括号字符串有效。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isValid(self, s: str) -> bool:
        q = []
        parentheses = {'()', '[]', '{}'}
        for ch in s:
            if ch in '([{':
                q.append(ch)
            elif not q or q.pop() + ch not in parentheses:
                return False
        return not q
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> q = new ArrayDeque<>();
        for (char ch : chars) {
            boolean left = ch == '(' || ch == '[' || ch == '{';
            if (left) q.push(ch);
            else if (q.isEmpty() || !match(q.pop(), ch)) return false;
        }
        return q.isEmpty();
    }

    private boolean match(char l, char r) {
        return (l == '(' && r == ')') || (l == '{' && r == '}') || (l == '[' && r == ']');
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isValid(string s) {
        stack<char> q;
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (s[i] == '{' || s[i] == '[' || s[i] == '(')
                q.push(s[i]);
            else if (q.empty() || !match(q.top(), s[i]))
                return false;
            else
                q.pop();
        }
        return q.empty();
    }

private:
    bool match(char l, char r) {
        return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}');
    }
};
```

### **Go**

```go
func isValid(s string) bool {
	stack := newStack()
	for _, str := range s {
		if str == '(' || str == '[' || str == '{' {
			stack.push(byte(str))
		} else if str == ')' {
			if stack.pop() != (byte('(')) {
				return false
			}
		} else if str == ']' {
			if stack.pop() != (byte('[')) {
				return false
			}
		} else if str == '}' {
			if stack.pop() != (byte('{')) {
				return false
			}
		}
	}
	return stack.size() == 0
}

type Stack struct {
	data  []byte
	index int
}

func newStack() *Stack {
	return &Stack{
		data: make([]byte, 10),
	}
}

func (s *Stack) pop() byte {
	if s.index == 0 {
		return 0
	}
	s.index--
	r := s.data[s.index]
	return r
}

func (s *Stack) push(b byte) {
	if len(s.data)-1 <= s.index {
		newData := make([]byte, len(s.data))
		s.data = append(s.data, newData[:]...)
	}
	s.data[s.index] = b
	s.index++
}

func (s *Stack) size() int {
	return s.index
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
    let arr = [];
    for (let i = 0; i < s.length; i++) {
        if (s[i] === '{' || s[i] === '[' || s[i] === '(') {
            arr.push(s[i]);
        } else {
            if (s[i] === ')' && arr[arr.length - 1] === '(') arr.pop();
            else if (s[i] === ']' && arr[arr.length - 1] === '[') arr.pop();
            else if (s[i] === '}' && arr[arr.length - 1] === '{') arr.pop();
            else return false;
        }
    }
    return arr.length === 0;
};
```

### **Ruby**

```rb
# @param {String} s
# @return {Boolean}
def is_valid(s)
  stack = ''
  s.split('').each do |c|
    if ['{', '[', '('].include?(c)
      stack += c
    else
      if c == '}' && stack[stack.length - 1] == '{'

        stack = stack.length > 1 ? stack[0..stack.length - 2] : ""
      elsif c == ']' && stack[stack.length - 1] == '['
        stack = stack.length > 1 ? stack[0..stack.length - 2] : ""
      elsif c == ')' && stack[stack.length - 1] == '('
        stack = stack.length > 1 ? stack[0..stack.length - 2] : ""
      else
        return false
      end
    end
  end
  stack == ''
end
```

### **TypeScript**

```ts
const map = new Map([
    ['(', ')'],
    ['[', ']'],
    ['{', '}'],
]);

function isValid(s: string): boolean {
    const stack = [];
    for (const c of s) {
        if (map.has(c)) {
            stack.push(map.get(c));
        } else if (stack.pop() !== c) {
            return false;
        }
    }
    return stack.length === 0;
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn is_valid(s: String) -> bool {
        let mut map = HashMap::new();
        map.insert('(', ')');
        map.insert('[', ']');
        map.insert('{', '}');
        let mut stack = vec![];
        for c in s.chars() {
            if map.contains_key(&c) {
                stack.push(map[&c]);
            } else if stack.pop().unwrap_or(' ') != c {
                return false;
            }
        }
        stack.len() == 0
    }
}
```

### **...**

```

```

<!-- tabs:end -->
