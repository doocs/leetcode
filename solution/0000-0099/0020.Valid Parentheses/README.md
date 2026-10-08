---
comments: true
difficulty: 简单
tags:
    - 栈
    - 字符串
    - 括号序列
---

<!-- problem:start -->

# [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses)

[English Version](/solution/0000-0099/0020.Valid%20Parentheses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code>&nbsp;的字符串 <code>s</code> ，判断字符串是否有效。</p>

<p>有效字符串需满足：</p>

<ol>
	<li>左括号必须用相同类型的右括号闭合。</li>
	<li>左括号必须以正确的顺序闭合。</li>
	<li>每个右括号都有一个对应的相同类型的左括号。</li>
</ol>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "()"</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "()[]{}"</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "(]"</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "([])"</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>
</div>

<p><strong class="example">示例 5：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "([)]"</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

<!-- thinking:start -->

> **思考**
>
> 把已经相邻的 $()$、 $[]$ 和 $\{\}$ 反复从字符串中删去，直到不能再删，结果是正确的。每一轮都要重新扫描， $n \le 10^4$ 时最坏会退化成 $O(n^2)$，括号层层嵌套时也容易超时。
>
> 瓶颈在于配对并不只看相邻的两个字符。后出现的左括号必须先被闭合，更早的左括号还要留在原处；像 $([)]$ 这种交错，局部删除已经无法把字符串消成空串。
>
> 于是匹配是后进先出的：当前右括号唯一合法的对象，就是最近一个尚未闭合的左括号。栈用来记录这些尚未完成的匹配。读到左括号时，把与之对应的右括号压入栈顶，也就是把下一次允许出现的右括号放在最上面；读到右括号时只和栈顶比较。不相等说明类型或顺序已经错了。扫完之后栈里还有元素，则说明仍有左括号没有闭合。

<!-- thinking:end -->

用哈希表 $\textit{d}$ 记录每种左括号对应的右括号，用栈 $\textit{stk}$ 保存尚未匹配的右括号。从左到右扫描字符串 $s$。当前字符是左括号时，将 $\textit{d}$ 中对应的右括号压入 $\textit{stk}$；当前字符是右括号时，若 $\textit{stk}$ 为空，或弹出的栈顶与该字符不相等，则返回 `false`。

扫描结束后，若 $\textit{stk}$ 为空，说明每一对括号都按正确的类型和顺序闭合，返回 `true`；否则仍有左括号没有闭合，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。

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
