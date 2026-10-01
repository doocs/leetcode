---
comments: true
difficulty: Medium
tags:
    - Stack
    - Math
    - String
---

<!-- problem:start -->

# [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii)

[中文文档](/solution/0200-0299/0227.Basic%20Calculator%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code> biểu diễn một biểu thức, <em>hãy tính giá trị của biểu thức này và trả về giá trị đó</em>.&nbsp;</p>

<p>Phép chia số nguyên phải cắt kết quả về phía 0.</p>

<p>Bạn có thể giả sử rằng biểu thức đã cho luôn hợp lệ. Tất cả kết quả trung gian nằm trong khoảng <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.</p>

<p><strong>Lưu ý:</strong> Bạn không được sử dụng bất kỳ hàm dựng sẵn nào để tính giá trị của chuỗi dưới dạng biểu thức toán học, chẳng hạn như <code>eval()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> s = "3+2*2"
<strong>Đầu ra:</strong> 7
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> s = " 3/2 "
<strong>Đầu ra:</strong> 1
</pre><p><strong class="example">Ví dụ 3:</strong></p>
<pre><strong>Đầu vào:</strong> s = " 3+5 / 2 "
<strong>Đầu ra:</strong> 5
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> chỉ gồm các số nguyên và toán tử <code>(&#39;+&#39;, &#39;-&#39;, &#39;*&#39;, &#39;/&#39;)</code>, được phân tách bởi một số khoảng trắng.</li>
	<li><code>s</code> biểu diễn một biểu thức hợp lệ.</li>
	<li>Tất cả số nguyên trong biểu thức là các số nguyên không âm trong khoảng <code>[0, 2<sup>31</sup> - 1]</code>.</li>
	<li>Đáp án <strong>được đảm bảo</strong> nằm trong một <strong>số nguyên 32-bit</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ngăn xếp

<!-- thinking:start -->

> **Tư duy**
>
> Phép nhân và phép chia có độ ưu tiên cao hơn phép cộng, và không có dấu ngoặc, vì vậy chúng ta không thể cộng ngay mọi số. Hãy coi $+$ và $-$ là các hạng tử có dấu trên một ngăn xếp, rồi gộp $*$ và $/$ vào phần tử trên cùng.
>
> $sign$ ghi nhớ toán tử trước đó; sau khi hoàn tất một số, chúng ta đẩy số đó vào ngăn xếp hoặc cập nhật phần tử trên cùng, sau đó tính tổng ngăn xếp.

<!-- thinking:end -->

Chúng ta duyệt chuỗi $s$ và dùng biến `sign` để ghi lại toán tử đứng trước mỗi số. Với số đầu tiên, toán tử đứng trước nó được coi là dấu cộng. Mỗi khi duyệt đến cuối một số, chúng ta quyết định cách tính dựa trên `sign`:

- Dấu cộng: đẩy số vào ngăn xếp;
- Dấu trừ: đẩy số đối của số đó vào ngăn xếp;
- Dấu nhân và chia: tính toán số đó với phần tử trên cùng của ngăn xếp, rồi thay thế phần tử trên cùng bằng kết quả tính toán.

Sau khi duyệt xong, tổng các phần tử trong ngăn xếp là đáp án.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculate(self, s: str) -> int:
        v, n = 0, len(s)
        sign = '+'
        stk = []
        for i, c in enumerate(s):
            if c.isdigit():
                v = v * 10 + int(c)
            if i == n - 1 or c in '+-*/':
                match sign:
                    case '+':
                        stk.append(v)
                    case '-':
                        stk.append(-v)
                    case '*':
                        stk.append(stk.pop() * v)
                    case '/':
                        stk.append(int(stk.pop() / v))
                sign = c
                v = 0
        return sum(stk)
```

#### Java

```java
class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        char sign = '+';
        int v = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                v = v * 10 + (c - '0');
            }
            if (i == s.length() - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') {
                    stk.push(v);
                } else if (sign == '-') {
                    stk.push(-v);
                } else if (sign == '*') {
                    stk.push(stk.pop() * v);
                } else {
                    stk.push(stk.pop() / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.isEmpty()) {
            ans += stk.pop();
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
        int v = 0, n = s.size();
        char sign = '+';
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (isdigit(c)) v = v * 10 + (c - '0');
            if (i == n - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+')
                    stk.push(v);
                else if (sign == '-')
                    stk.push(-v);
                else if (sign == '*') {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t * v);
                } else {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func calculate(s string) int {
	sign := '+'
	stk := []int{}
	v := 0
	for i, c := range s {
		digit := '0' <= c && c <= '9'
		if digit {
			v = v*10 + int(c-'0')
		}
		if i == len(s)-1 || !digit && c != ' ' {
			switch sign {
			case '+':
				stk = append(stk, v)
			case '-':
				stk = append(stk, -v)
			case '*':
				stk[len(stk)-1] *= v
			case '/':
				stk[len(stk)-1] /= v
			}
			sign = c
			v = 0
		}
	}
	ans := 0
	for _, v := range stk {
		ans += v
	}
	return ans
}
```

#### C#

```cs
struct Element {
    public char Op;
    public int Number;
    public Element(char op, int number) {
        Op = op;
        Number = number;
    }
}

public class Solution {
    public int Calculate(string s) {
        var stack = new Stack<Element>();
        var readingNumber = false;
        var number = 0;
        var op = '+';
        foreach (var ch in ((IEnumerable<char>)s).Concat(Enumerable.Repeat('+', 1))) {
            if (ch >= '0' && ch <= '9') {
                if (!readingNumber) {
                    readingNumber = true;
                    number = 0;
                }
                number = (number * 10) + (ch - '0');
            }
            else if (ch != ' ') {
                readingNumber = false;
                if (op == '+' || op == '-') {
                    if (stack.Count == 2) {
                        var prev = stack.Pop();
                        var first = stack.Pop();
                        if (prev.Op == '+') {
                            stack.Push(new Element(first.Op, first.Number + prev.Number));
                        }
                        else { // '-'
                            stack.Push(new Element(first.Op, first.Number - prev.Number));
                        }
                    }
                    stack.Push(new Element(op, number));
                }
                else {
                    var prev = stack.Pop();
                    if (op == '*') {
                        stack.Push(new Element(prev.Op, prev.Number * number));
                    }
                    else { // '/'
                        stack.Push(new Element(prev.Op, prev.Number / number));
                    }
                }
                op = ch;
            }
        }

        if (stack.Count == 2) {
            var second = stack.Pop();
            var first = stack.Pop();
            if (second.Op == '+') {
                stack.Push(new Element(first.Op, first.Number + second.Number));
            }
            else { // '-'
                stack.Push(new Element(first.Op, first.Number - second.Number));
            }
        }

        return stack.Peek().Number;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
