# [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

[中文文档](/solution/0000-0099/0020.Valid%20Parentheses/README.md)

## Description

<p>Given a string <code>s</code> containing just the characters <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> and <code>&#39;]&#39;</code>, determine if the input string is valid.</p>

<p>An input string is valid if:</p>

<ol>
	<li>Open brackets must be closed by the same type of brackets.</li>
	<li>Open brackets must be closed in the correct order.</li>
</ol>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()[]{}&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(]&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of parentheses only <code>&#39;()[]{}&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
