---
comments: true
difficulty: Hard
tags:
    - Stack
    - Recursion
    - Math
    - String
---

<!-- problem:start -->

# [224. Basic Calculator](https://leetcode.com/problems/basic-calculator)

[中文文档](/solution/0200-0299/0224.Basic%20Calculator/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code> biểu diễn một biểu thức hợp lệ, hãy triển khai một máy tính cơ bản để tính giá trị của biểu thức đó và trả về <em>kết quả tính toán</em>.</p>

<p><strong>Lưu ý:</strong> Bạn <strong>không được phép</strong> sử dụng bất kỳ hàm dựng sẵn nào để tính các chuỗi dưới dạng biểu thức toán học, chẳng hạn như <code>eval()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;1 + 1&quot;
<strong>Đầu ra:</strong> 2
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot; 2-1 + 2 &quot;
<strong>Đầu ra:</strong> 3
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;(1+(4+5+2)-3)+(6+8)&quot;
<strong>Đầu ra:</strong> 23
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> bao gồm các chữ số, <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code> và <code>&#39; &#39;</code>.</li>
	<li><code>s</code> biểu diễn một biểu thức hợp lệ.</li>
	<li><code>&#39;+&#39;</code> <strong>không</strong> được sử dụng như một phép toán một ngôi (tức là <code>&quot;+1&quot;</code> và <code>&quot;+(2 + 3)&quot;</code> không hợp lệ).</li>
	<li><code>&#39;-&#39;</code> có thể được sử dụng như một phép toán một ngôi (tức là <code>&quot;-1&quot;</code> và <code>&quot;-(2 + 3)&quot;</code> hợp lệ).</li>
	<li>Trong đầu vào sẽ không có hai toán tử liên tiếp.</li>
	<li>Mọi số và phép tính đang thực hiện đều vừa trong một số nguyên 32-bit có dấu.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ngăn xếp

<!-- thinking:start -->

> **Tư duy**
>
> Dấu cộng, dấu trừ và dấu ngoặc khiến việc chỉ tính tổng từ trái sang phải không đủ. Một đoạn nằm trong ngoặc là một phép cộng dồn riêng, vì vậy khi bắt đầu đoạn đó, ta phải lưu tổng và dấu của phần bên ngoài.
>
> Một ngăn xếp lưu $ans$ và $sign$ tại $($ rồi đặt lại chúng; tại $)$, ta lấy chúng ra và cộng kết quả bên trong nhân với dấu bên ngoài. Các chữ số được gộp vào $ans$ theo dấu hiện tại.

<!-- thinking:end -->

Chúng ta sử dụng một ngăn xếp $stk$ để lưu kết quả tính toán hiện tại và toán tử, một biến $sign$ để lưu dấu hiện tại, và một biến $ans$ để lưu kết quả tính toán cuối cùng.

Tiếp theo, chúng ta duyệt qua từng ký tự của chuỗi $s$:

- Nếu ký tự hiện tại là một chữ số, chúng ta sử dụng một vòng lặp để đọc các chữ số liên tiếp tiếp theo, sau đó cộng hoặc trừ số đó vào $ans$ tùy theo dấu hiện tại.
- Nếu ký tự hiện tại là `'+'`, chúng ta đặt biến $sign$ thành dương.
- Nếu ký tự hiện tại là `'-'`, chúng ta đặt biến $sign$ thành âm.
- Nếu ký tự hiện tại là `'('`, chúng ta đưa $ans$ và $sign$ hiện tại vào ngăn xếp, đặt lại chúng lần lượt về 0 và 1, rồi bắt đầu tính $ans$ và $sign$ mới.
- Nếu ký tự hiện tại là `')'`, chúng ta lấy hai phần tử trên cùng của ngăn xếp ra, một là toán tử và phần còn lại là số được tính trước dấu ngoặc. Chúng ta nhân số hiện tại với toán tử, rồi cộng số trước đó để nhận được $ans$ mới.

Sau khi duyệt qua chuỗi $s$, chúng ta trả về $ans$.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculate(self, s: str) -> int:
        stk = []
        ans, sign = 0, 1
        i, n = 0, len(s)
        while i < n:
            if s[i].isdigit():
                x = 0
                j = i
                while j < n and s[j].isdigit():
                    x = x * 10 + int(s[j])
                    j += 1
                ans += sign * x
                i = j - 1
            elif s[i] == "+":
                sign = 1
            elif s[i] == "-":
                sign = -1
            elif s[i] == "(":
                stk.append(ans)
                stk.append(sign)
                ans, sign = 0, 1
            elif s[i] == ")":
                ans = stk.pop() * ans + stk.pop()
            i += 1
        return ans
```

#### Java

```java
class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        int sign = 1;
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                int x = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    x = x * 10 + s.charAt(j) - '0';
                    j++;
                }
                ans += sign * x;
                i = j - 1;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (c == ')') {
                ans = stk.pop() * ans + stk.pop();
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int calculate(string s) {
        stack<int> stk;
        int ans = 0, sign = 1;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (isdigit(s[i])) {
                int x = 0;
                int j = i;
                while (j < n && isdigit(s[j])) {
                    x = x * 10 + (s[j] - '0');
                    ++j;
                }
                ans += sign * x;
                i = j - 1;
            } else if (s[i] == '+') {
                sign = 1;
            } else if (s[i] == '-') {
                sign = -1;
            } else if (s[i] == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (s[i] == ')') {
                ans *= stk.top();
                stk.pop();
                ans += stk.top();
                stk.pop();
            }
        }
        return ans;
    }
};
```

#### Go

```go
func calculate(s string) (ans int) {
	stk := []int{}
	sign := 1
	n := len(s)
	for i := 0; i < n; i++ {
		switch s[i] {
		case ' ':
		case '+':
			sign = 1
		case '-':
			sign = -1
		case '(':
			stk = append(stk, ans)
			stk = append(stk, sign)
			ans, sign = 0, 1
		case ')':
			ans *= stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			ans += stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		default:
			x := 0
			j := i
			for ; j < n && '0' <= s[j] && s[j] <= '9'; j++ {
				x = x*10 + int(s[j]-'0')
			}
			ans += sign * x
			i = j - 1
		}
	}
	return
}
```

#### TypeScript

```ts
function calculate(s: string): number {
    const stk: number[] = [];
    let sign = 1;
    let ans = 0;
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] === ' ') {
            continue;
        }
        if (s[i] === '+') {
            sign = 1;
        } else if (s[i] === '-') {
            sign = -1;
        } else if (s[i] === '(') {
            stk.push(ans);
            stk.push(sign);
            ans = 0;
            sign = 1;
        } else if (s[i] === ')') {
            ans *= stk.pop() as number;
            ans += stk.pop() as number;
        } else {
            let x = 0;
            let j = i;
            for (; j < n && !isNaN(Number(s[j])) && s[j] !== ' '; ++j) {
                x = x * 10 + (s[j].charCodeAt(0) - '0'.charCodeAt(0));
            }
            ans += sign * x;
            i = j - 1;
        }
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int Calculate(string s) {
        var stk = new Stack<int>();
        int sign = 1;
        int n = s.Length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == ' ') {
                continue;
            }
            if (s[i] == '+') {
                sign = 1;
            } else if (s[i] == '-') {
                sign = -1;
            } else if (s[i] == '(') {
                stk.Push(ans);
                stk.Push(sign);
                ans = 0;
                sign = 1;
            } else if (s[i] == ')') {
                ans *= stk.Pop();
                ans += stk.Pop();
            } else {
                int num = 0;
                while (i < n && char.IsDigit(s[i])) {
                    num = num * 10 + s[i] - '0';
                    ++i;
                }
                --i;
                ans += sign * num;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
