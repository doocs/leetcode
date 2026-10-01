---
comments: true
difficulty: Easy
tags:
    - Stack
    - String
    - Parentheses
---

<!-- problem:start -->

# [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

[中文文档](/solution/0000-0099/0020.Valid%20Parentheses/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> containing just the characters <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> and <code>&#39;]&#39;</code>, determine if the input string is valid.</p>

<p>An input string is valid if:</p>

<ol>
	<li>Open brackets must be closed by the same type of brackets.</li>
	<li>Open brackets must be closed in the correct order.</li>
	<li>Every close bracket has a corresponding open bracket of the same type.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;()&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;()[]{}&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;(]&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;([])&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 5:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;([)]&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of parentheses only <code>&#39;()[]{}&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Stack

<!-- thinking:start -->

> **Thinking**
>
> Repeatedly deleting adjacent pairs $()$, $[]$, and $\{\}$ until nothing is left is correct, but each pass rescans the string. With $n \le 10^4$, deeply nested input falls to $O(n^2)$ and can easily time out.
>
> The bottleneck is that a match is more than two neighboring characters. A later opener must close first, while an earlier one stays open. Once the brackets cross, as in $([)]$, deleting neighbors cannot reduce the string to empty.
>
> Matching is therefore last-in, first-out: the only legal partner of the current closer is the most recent unmatched opener. A stack keeps those unfinished matches. An opener pushes its closer, so the closer allowed next sits on top, and a closer is compared only with that top. A mismatch means the type or the order is already wrong, and a non-empty stack at the end means some opener never closed.

<!-- thinking:end -->

We use a hash table $\textit{d}$ to map each opening bracket to its closing bracket, and a stack $\textit{stk}$ to store closers that have not been matched yet. Scan $s$ from left to right. When the current character is an opening bracket, push the corresponding closer from $\textit{d}$ onto $\textit{stk}$. When it is a closing bracket, return `false` if $\textit{stk}$ is empty or the popped top is different from that character.

After the scan, return `true` if $\textit{stk}$ is empty: every pair has been closed in the right type and order. If the stack still holds a closer, some opening bracket was never matched, so return `false`.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isValid(self, s: str) -> bool:
        stk = []
        d = {'(': ')', '[': ']', '{': '}'}
        for c in s:
            if c in d:
                stk.append(d[c])
            elif not stk or stk.pop() != c:
                return False
        return not stk
```

#### Java

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        Map<Character, Character> d = new HashMap<>(3);
        d.put('(', ')');
        d.put('[', ']');
        d.put('{', '}');
        for (char c : s.toCharArray()) {
            if (d.containsKey(c)) {
                stk.push(d.get(c));
            } else if (stk.isEmpty() || stk.pop() != c) {
                return false;
            }
        }
        return stk.isEmpty();
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isValid(string s) {
        string stk;
        unordered_map<char, char> d{{'(', ')'}, {'[', ']'}, {'{', '}'}};
        for (char c : s) {
            if (d.contains(c)) {
                stk.push_back(d[c]);
            } else if (stk.empty() || stk.back() != c) {
                return false;
            } else {
                stk.pop_back();
            }
        }
        return stk.empty();
    }
};
```

#### Go

```go
func isValid(s string) bool {
	stk := []byte{}
	d := map[byte]byte{'(': ')', '[': ']', '{': '}'}
	for i := 0; i < len(s); i++ {
		c := s[i]
		if v, ok := d[c]; ok {
			stk = append(stk, v)
		} else if len(stk) == 0 || stk[len(stk)-1] != c {
			return false
		} else {
			stk = stk[:len(stk)-1]
		}
	}
	return len(stk) == 0
}
```

#### TypeScript

```ts
function isValid(s: string): boolean {
    const d = new Map<string, string>([
        ['(', ')'],
        ['[', ']'],
        ['{', '}'],
    ]);
    const stk: string[] = [];
    for (const c of s) {
        if (d.has(c)) {
            stk.push(d.get(c)!);
        } else if (stk.pop() !== c) {
            return false;
        }
    }
    return stk.length === 0;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn is_valid(s: String) -> bool {
        let d: HashMap<char, char> = [('(', ')'), ('[', ']'), ('{', '}')]
            .iter()
            .copied()
            .collect();
        let mut stk = Vec::new();
        for c in s.chars() {
            if let Some(&v) = d.get(&c) {
                stk.push(v);
            } else if stk.pop() != Some(c) {
                return false;
            }
        }
        stk.is_empty()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
    const d = new Map([
        ['(', ')'],
        ['[', ']'],
        ['{', '}'],
    ]);
    const stk = [];
    for (const c of s) {
        if (d.has(c)) {
            stk.push(d.get(c));
        } else if (stk.pop() !== c) {
            return false;
        }
    }
    return stk.length === 0;
};
```

#### C#

```cs
public class Solution {
    public bool IsValid(string s) {
        Stack<char> stk = new Stack<char>();
        Dictionary<char, char> d = new Dictionary<char, char>();
        d.Add('(', ')');
        d.Add('[', ']');
        d.Add('{', '}');
        foreach (char c in s) {
            if (d.ContainsKey(c)) {
                stk.Push(d[c]);
            } else if (stk.Count == 0 || stk.Pop() != c) {
                return false;
            }
        }
        return stk.Count == 0;
    }
}
```

#### Ruby

```rb
# @param {String} s
# @return {Boolean}
def is_valid(s)
  stk = []
  d = { '(' => ')', '[' => ']', '{' => '}' }
  s.each_char do |c|
    if d.key?(c)
      stk.push(d[c])
    elsif stk.empty? || stk.pop != c
      return false
    end
  end
  stk.empty?
end
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function isValid($s) {
        $stk = [];
        $d = [
            '(' => ')',
            '[' => ']',
            '{' => '}',
        ];
        $n = strlen($s);
        for ($i = 0; $i < $n; $i++) {
            $c = $s[$i];
            if (isset($d[$c])) {
                $stk[] = $d[$c];
            } elseif (empty($stk) || array_pop($stk) !== $c) {
                return false;
            }
        }
        return empty($stk);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
