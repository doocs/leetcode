---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0020.Valid%20Parentheses/README_EN.md
tags:
    - Stack
    - String
---

# [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

[中文文档](/solution/0000-0099/0020.Valid%20Parentheses/README.md)

## Description

<p>Given a string <code>s</code> containing just the characters <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> and <code>&#39;]&#39;</code>, determine if the input string is valid.</p>

<p>An input string is valid if:</p>

<ol>
	<li>Open brackets must be closed by the same type of brackets.</li>
	<li>Open brackets must be closed in the correct order.</li>
	<li>Every close bracket has a corresponding open bracket of the same type.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()[]{}&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

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

### Solution 1: Stack

Traverse the bracket string $s$. When encountering a left bracket, push the current left bracket into the stack; when encountering a right bracket, pop the top element of the stack (if the stack is empty, directly return `false`), and judge whether it matches. If it does not match, directly return `false`.

Alternatively, when encountering a left bracket, you can push the corresponding right bracket into the stack; when encountering a right bracket, pop the top element of the stack (if the stack is empty, directly return `false`), and judge whether they are equal. If they do not match, directly return `false`.

> The difference between the two methods is only the timing of bracket conversion, one is when pushing into the stack, and the other is when popping out of the stack.

At the end of the traversal, if the stack is empty, it means the bracket string is valid, return `true`; otherwise, return `false`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the bracket string $s$.

<!-- tabs:start -->

```python
class Solution:
    def isValid(self, s: str) -> bool:
        stk = []
        d = {'()', '[]', '{}'}
        for c in s:
            if c in '({[':
                stk.append(c)
            elif not stk or stk.pop() + c not in d:
                return False
        return not stk
```

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stk.push(c);
            } else if (stk.isEmpty() || !match(stk.pop(), c)) {
                return false;
            }
        }
        return stk.isEmpty();
    }

    private boolean match(char l, char r) {
        return (l == '(' && r == ')') || (l == '{' && r == '}') || (l == '[' && r == ']');
    }
}
```

```cpp
class Solution {
public:
    bool isValid(string s) {
        string stk;
        for (char c : s) {
            if (c == '(' || c == '{' || c == '[')
                stk.push_back(c);
            else if (stk.empty() || !match(stk.back(), c))
                return false;
            else
                stk.pop_back();
        }
        return stk.empty();
    }

    bool match(char l, char r) {
        return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}');
    }
};
```

```go
func isValid(s string) bool {
	stk := []rune{}
	for _, c := range s {
		if c == '(' || c == '{' || c == '[' {
			stk = append(stk, c)
		} else if len(stk) == 0 || !match(stk[len(stk)-1], c) {
			return false
		} else {
			stk = stk[:len(stk)-1]
		}
	}
	return len(stk) == 0
}

func match(l, r rune) bool {
	return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}')
}
```

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

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
    let stk = [];
    for (const c of s) {
        if (c == '(' || c == '{' || c == '[') {
            stk.push(c);
        } else if (stk.length == 0 || !match(stk[stk.length - 1], c)) {
            return false;
        } else {
            stk.pop();
        }
    }
    return stk.length == 0;
};

function match(l, r) {
    return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}');
}
```

```cs
public class Solution {
    public bool IsValid(string s) {
        Stack<char> stk = new Stack<char>();
        foreach (var c in s.ToCharArray()) {
            if (c == '(') {
                stk.Push(')');
            } else if (c == '[') {
                stk.Push(']');
            } else if (c == '{') {
                stk.Push('}');
            } else if (stk.Count == 0 || stk.Pop() != c) {
                return false;
            }
        }
        return stk.Count == 0;
    }
}
```

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

```php
class Solution {
    /**
     * @param string $s
     * @return boolean
     */

    function isValid($s) {
        $stack = [];
        $brackets = [
            ')' => '(',
            '}' => '{',
            ']' => '[',
        ];

        for ($i = 0; $i < strlen($s); $i++) {
            $char = $s[$i];
            if (array_key_exists($char, $brackets)) {
                if (empty($stack) || $stack[count($stack) - 1] !== $brackets[$char]) {
                    return false;
                }
                array_pop($stack);
            } else {
                array_push($stack, $char);
            }
        }
        return empty($stack);
    }
}
```

<!-- tabs:end -->

<!-- end -->
