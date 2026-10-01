---
comments: true
difficulty: Medium
tags:
    - Stack
    - Array
    - Math
---

<!-- problem:start -->

# [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation)

[中文文档](/solution/0100-0199/0150.Evaluate%20Reverse%20Polish%20Notation/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một mảng các chuỗi <code>tokens</code> biểu diễn một biểu thức số học trong <a href="http://en.wikipedia.org/wiki/Reverse_Polish_notation" target="_blank">Ký pháp Ba Lan đảo ngược</a>.</p>

<p>Hãy tính giá trị biểu thức. Trả về <em>một số nguyên biểu diễn giá trị của biểu thức</em>.</p>

<p><strong>Lưu ý</strong> rằng:</p>

<ul>
	<li>Các toán tử hợp lệ là <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;*&#39;</code> và <code>&#39;/&#39;</code>.</li>
	<li>Mỗi toán hạng có thể là một số nguyên hoặc một biểu thức khác.</li>
	<li>Phép chia giữa hai số nguyên luôn <strong>cắt về phía 0</strong>.</li>
	<li>Sẽ không có phép chia cho 0.</li>
	<li>Đầu vào biểu diễn một biểu thức số học hợp lệ ở dạng ký pháp Ba Lan đảo ngược.</li>
	<li>Đáp án và tất cả các phép tính trung gian có thể được biểu diễn bằng một số nguyên <strong>32-bit</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> tokens = [&quot;2&quot;,&quot;1&quot;,&quot;+&quot;,&quot;3&quot;,&quot;*&quot;]
<strong>Đầu ra:</strong> 9
<strong>Giải thích:</strong> ((2 + 1) * 3) = 9
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> tokens = [&quot;4&quot;,&quot;13&quot;,&quot;5&quot;,&quot;/&quot;,&quot;+&quot;]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> (4 + (13 / 5)) = 6
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> tokens = [&quot;10&quot;,&quot;6&quot;,&quot;9&quot;,&quot;3&quot;,&quot;+&quot;,&quot;-11&quot;,&quot;*&quot;,&quot;/&quot;,&quot;*&quot;,&quot;17&quot;,&quot;+&quot;,&quot;5&quot;,&quot;+&quot;]
<strong>Đầu ra:</strong> 22
<strong>Giải thích:</strong> ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= tokens.length &lt;= 10<sup>4</sup></code></li>
	<li><code>tokens[i]</code> là một trong hai dạng: toán tử <code>&quot;+&quot;</code>, <code>&quot;-&quot;</code>, <code>&quot;*&quot;</code> hoặc <code>&quot;/&quot;</code>, hoặc một số nguyên trong phạm vi <code>[-200, 200]</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Ký pháp Ba Lan đảo ngược đặt toán tử sau các toán hạng, nên thứ tự tính đã được cố định và chúng ta không cần dấu ngoặc hay độ ưu tiên. $n\le 10^4$. Đẩy các số vào stack; khi gặp toán tử, lấy ra hai giá trị, tính toán rồi đẩy kết quả trở lại. Stack lưu các giá trị trung gian đang chờ; cuối cùng còn lại một giá trị.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import operator


class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        opt = {
            "+": operator.add,
            "-": operator.sub,
            "*": operator.mul,
            "/": operator.truediv,
        }
        s = []
        for token in tokens:
            if token in opt:
                s.append(int(opt[token](s.pop(-2), s.pop(-1))))
            else:
                s.append(int(token))
        return s[0]
```

#### Java

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.length() > 1 || Character.isDigit(t.charAt(0))) {
                stk.push(Integer.parseInt(t));
            } else {
                int y = stk.pop();
                int x = stk.pop();
                switch (t) {
                case "+":
                    stk.push(x + y);
                    break;
                case "-":
                    stk.push(x - y);
                    break;
                case "*":
                    stk.push(x * y);
                    break;
                default:
                    stk.push(x / y);
                    break;
                }
            }
        }
        return stk.pop();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        stack<int> stk;
        for (auto& t : tokens) {
            if (t.size() > 1 || isdigit(t[0])) {
                stk.push(stoi(t));
            } else {
                int y = stk.top();
                stk.pop();
                int x = stk.top();
                stk.pop();
                if (t[0] == '+')
                    stk.push(x + y);
                else if (t[0] == '-')
                    stk.push(x - y);
                else if (t[0] == '*')
                    stk.push(x * y);
                else
                    stk.push(x / y);
            }
        }
        return stk.top();
    }
};
```

#### Go

```go
func evalRPN(tokens []string) int {
	// https://github.com/emirpasic/gods#arraystack
	stk := arraystack.New()
	for _, token := range tokens {
		if len(token) > 1 || token[0] >= '0' && token[0] <= '9' {
			num, _ := strconv.Atoi(token)
			stk.Push(num)
		} else {
			y := popInt(stk)
			x := popInt(stk)
			switch token {
			case "+":
				stk.Push(x + y)
			case "-":
				stk.Push(x - y)
			case "*":
				stk.Push(x * y)
			default:
				stk.Push(x / y)
			}
		}
	}
	return popInt(stk)
}

func popInt(stack *arraystack.Stack) int {
	v, _ := stack.Pop()
	return v.(int)
}
```

#### TypeScript

```ts
function evalRPN(tokens: string[]): number {
    const stack = [];
    for (const token of tokens) {
        if (/\d/.test(token)) {
            stack.push(Number(token));
        } else {
            const a = stack.pop();
            const b = stack.pop();
            switch (token) {
                case '+':
                    stack.push(b + a);
                    break;
                case '-':
                    stack.push(b - a);
                    break;
                case '*':
                    stack.push(b * a);
                    break;
                case '/':
                    stack.push(~~(b / a));
                    break;
            }
        }
    }
    return stack[0];
}
```

#### Rust

```rust
impl Solution {
    pub fn eval_rpn(tokens: Vec<String>) -> i32 {
        let mut stack = vec![];
        for token in tokens {
            match token.parse() {
                Ok(num) => stack.push(num),
                Err(_) => {
                    let a = stack.pop().unwrap();
                    let b = stack.pop().unwrap();
                    stack.push(match token.as_str() {
                        "+" => b + a,
                        "-" => b - a,
                        "*" => b * a,
                        "/" => b / a,
                        _ => 0,
                    });
                }
            }
        }
        stack[0]
    }
}
```

#### C#

```cs
public class Solution {
    public int EvalRPN(string[] tokens) {
        var stack = new Stack<int>();
        foreach (var token in tokens) {
            switch (token) {
                case "+":
                    stack.Push(stack.Pop() + stack.Pop());
                    break;
                case "-":
                    stack.Push(-stack.Pop() + stack.Pop());
                    break;
                case "*":
                    stack.Push(stack.Pop() * stack.Pop());
                    break;
                case "/":
                    var right = stack.Pop();
                    stack.Push(stack.Pop() / right);
                    break;
                default:
                    stack.Push(int.Parse(token));
                    break;
            }
        }
        return stack.Pop();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 ánh xạ các toán tử tới các hàm. Phiên bản này thay đổi đỉnh stack tại chỗ, rẽ nhánh theo bốn toán tử, đồng thời chú ý thứ tự toán hạng và việc cắt về 0. Cấu trúc giữ nguyên, không dùng bảng hàm.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        nums = []
        for t in tokens:
            if len(t) > 1 or t.isdigit():
                nums.append(int(t))
            else:
                if t == "+":
                    nums[-2] += nums[-1]
                elif t == "-":
                    nums[-2] -= nums[-1]
                elif t == "*":
                    nums[-2] *= nums[-1]
                else:
                    nums[-2] = int(nums[-2] / nums[-1])
                nums.pop()
        return nums[0]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
