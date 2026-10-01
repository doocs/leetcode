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

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code> chỉ chứa các ký tự <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> và <code>&#39;]&#39;</code>, hãy xác định xem chuỗi đầu vào có hợp lệ hay không.</p>

<p>Một chuỗi đầu vào hợp lệ nếu:</p>

<ol>
	<li>Các dấu ngoặc mở phải được đóng bằng cùng loại dấu ngoặc.</li>
	<li>Các dấu ngoặc mở phải được đóng theo đúng thứ tự.</li>
	<li>Mỗi dấu ngoặc đóng đều có một dấu ngoặc mở tương ứng cùng loại.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;()&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;()[]{}&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;(]&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">false</span></p>
</div>

<p><strong class="example">Ví dụ 4:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;([])&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Ví dụ 5:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;([)]&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> chỉ bao gồm các dấu ngoặc <code>&#39;()[]{}&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ngăn xếp

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là liên tục loại bỏ $()$, $[]$ và $\{\}$ cho đến khi không còn gì thay đổi. Cách này đúng, nhưng trong trường hợp xấu nhất có độ phức tạp $O(n^2)$. Với $n\le 10^4$, cách này có thể vẫn vượt qua, nhưng cách triển khai khá vụng về.
>
> Việc ghép cặp tuân theo nguyên tắc mở sau cùng thì đóng trước, vì vậy chúng ta cần LIFO. Một dấu ngoặc trái chờ dấu ngoặc phải tương ứng; một dấu ngoặc phải phải ghép với dấu ngoặc trái chưa ghép gần nhất. Ngăn xếp phải rỗng ở cuối, nếu không một dấu ngoặc trái nào đó chưa được đóng.
>
> Vì vậy, chúng ta đẩy các dấu ngoặc trái vào ngăn xếp, và khi gặp một dấu ngoặc phải thì lấy phần tử trên cùng ra để so sánh.

<!-- thinking:end -->

Duyệt qua chuỗi dấu ngoặc $s$. Khi gặp một dấu ngoặc trái, đẩy dấu ngoặc trái hiện tại vào ngăn xếp; khi gặp một dấu ngoặc phải, lấy phần tử trên cùng của ngăn xếp ra (nếu ngăn xếp rỗng thì trả về `false` ngay), rồi kiểm tra xem nó có khớp hay không. Nếu không khớp, trả về `false` ngay.

Ngoài ra, khi gặp một dấu ngoặc trái, chúng ta có thể đẩy dấu ngoặc phải tương ứng vào ngăn xếp; khi gặp một dấu ngoặc phải, lấy phần tử trên cùng của ngăn xếp ra (nếu ngăn xếp rỗng thì trả về `false` ngay), rồi kiểm tra xem chúng có bằng nhau hay không. Nếu không bằng nhau, trả về `false` ngay.

> Điểm khác biệt giữa hai phương pháp chỉ nằm ở thời điểm chuyển đổi dấu ngoặc: một phương pháp chuyển đổi khi đẩy vào ngăn xếp, còn phương pháp kia chuyển đổi khi lấy ra khỏi ngăn xếp.

Sau khi duyệt xong, nếu ngăn xếp rỗng thì chuỗi dấu ngoặc hợp lệ, trả về `true`; ngược lại, trả về `false`.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của chuỗi dấu ngoặc $s$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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

#### JavaScript

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

#### C#

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

#### Ruby

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

#### PHP

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

<!-- solution:end -->

<!-- problem:end -->
