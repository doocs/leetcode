---
comments: true
difficulty: Hard
tags:
    - Stack
    - String
    - Dynamic Programming
    - Parentheses
---

<!-- problem:start -->

# [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses)

[中文文档](/solution/0000-0099/0032.Longest%20Valid%20Parentheses/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi chỉ chứa các ký tự <code>&#39;(&#39;</code> và <code>&#39;)&#39;</code>, hãy trả về <em>độ dài của chuỗi dấu ngoặc đơn hợp lệ (đúng định dạng) </em><span data-keyword="substring-nonempty"><em>chuỗi con</em></span> dài nhất.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;(()&quot;
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Chuỗi con dấu ngoặc đơn hợp lệ dài nhất là &quot;()&quot;.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;)()())&quot;
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Chuỗi con dấu ngoặc đơn hợp lệ dài nhất là &quot;()()&quot;.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;&quot;
<strong>Đầu ra:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s[i]</code> là <code>&#39;(&#39;</code> hoặc <code>&#39;)&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là kiểm tra mọi chuỗi con bằng một ngăn xếp. Cách này đúng, nhưng $n \le 3 \times 10^4$ khiến $O(n^2)$ hoặc $O(n^3)$ quá chậm.
>
> Điểm nghẽn là sự chồng lấp: đoạn hợp lệ dài nhất kết thúc tại một vị trí có thể được xây dựng từ các đoạn hợp lệ ngắn hơn.
>
> Một chuỗi con hợp lệ chỉ có thể kết thúc bằng `)`. Nếu nó ghép cặp với ký tự trước đó, độ dài của nó bằng độ dài tiền tố trước cặp này cộng $2$. Nếu ký tự trước đó cũng là `)`, chúng ta bỏ qua đoạn đã hợp lệ kết thúc tại đó và kiểm tra xem ngay trước đoạn đó có một `(` hay không.
>
> Vì vậy, đặt $f[i]$ là độ dài hợp lệ dài nhất kết thúc tại $s[i-1]$, thực hiện chuyển trạng thái theo hai trường hợp ghép cặp đó, rồi lấy $\max f[i]$.

<!-- thinking:end -->

Ta định nghĩa $f[i]$ là độ dài của chuỗi dấu ngoặc đơn hợp lệ dài nhất kết thúc tại $s[i-1]$, và đáp án là $max(f[i])$.

Khi $i \lt 2$, độ dài chuỗi nhỏ hơn $2$ nên không có chuỗi dấu ngoặc đơn hợp lệ nào, do đó $f[i] = 0$.

Khi $i \ge 2$, ta xét độ dài của chuỗi dấu ngoặc đơn hợp lệ dài nhất kết thúc tại $s[i-1]$, tức là $f[i]$:

- Nếu $s[i-1]$ là dấu ngoặc trái, thì độ dài của chuỗi dấu ngoặc đơn hợp lệ dài nhất kết thúc tại $s[i-1]$ chắc chắn là $0$, nên $f[i] = 0$.
- Nếu $s[i-1]$ là dấu ngoặc phải, có hai trường hợp sau:
    - Nếu $s[i-2]$ là dấu ngoặc trái, thì độ dài của chuỗi dấu ngoặc đơn hợp lệ dài nhất kết thúc tại $s[i-1]$ là $f[i-2] + 2$.
    - Nếu $s[i-2]$ là dấu ngoặc phải, thì độ dài của chuỗi dấu ngoặc đơn hợp lệ dài nhất kết thúc tại $s[i-1]$ là $f[i-1] + 2$, nhưng ta cũng cần xét xem $s[i-f[i-1]-2]$ có phải là dấu ngoặc trái hay không. Nếu đúng, thì độ dài của chuỗi dấu ngoặc đơn hợp lệ dài nhất kết thúc tại $s[i-1]$ là $f[i-1] + 2 + f[i-f[i-1]-2]$.

Do đó, ta có công thức chuyển trạng thái:

$$
\begin{cases}
f[i] = 0, & \textit{if } s[i-1] = '(',\\
f[i] = f[i-2] + 2, & \textit{if } s[i-1] = ')' \textit{ and } s[i-2] = '(',\\
f[i] = f[i-1] + 2 + f[i-f[i-1]-2], & \textit{if } s[i-1] = ')' \textit{ and } s[i-2] = ')' \textit{ and } s[i-f[i-1]-2] = '(',\\
\end{cases}
$$

Cuối cùng, ta chỉ cần trả về $max(f)$.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i, c in enumerate(s, 1):
            if c == ")":
                if i > 1 and s[i - 2] == "(":
                    f[i] = f[i - 2] + 2
                else:
                    j = i - f[i - 1] - 1
                    if j and s[j - 1] == "(":
                        f[i] = f[i - 1] + 2 + f[j - 1]
        return max(f)
```

#### Java

```java
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.max(ans, f[i]);
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
    int longestValidParentheses(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
            }
        }
        return *max_element(f, f + n + 1);
    }
};
```

#### Go

```go
func longestValidParentheses(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		if s[i-1] == ')' {
			if s[i-2] == '(' {
				f[i] = f[i-2] + 2
			} else if j := i - f[i-1] - 1; j > 0 && s[j-1] == '(' {
				f[i] = f[i-1] + 2 + f[j-1]
			}
		}
	}
	return slices.Max(f)
}
```

#### TypeScript

```ts
function longestValidParentheses(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        if (s[i - 1] === ')') {
            if (s[i - 2] === '(') {
                f[i] = f[i - 2] + 2;
            } else {
                const j = i - f[i - 1] - 1;
                if (j && s[j - 1] === '(') {
                    f[i] = f[i - 1] + 2 + f[j - 1];
                }
            }
        }
    }
    return Math.max(...f);
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_valid_parentheses(s: String) -> i32 {
        let mut ans = 0;
        let mut f = vec![0; s.len() + 1];
        for i in 2..=s.len() {
            if s.chars().nth(i - 1).unwrap() == ')' {
                if s.chars().nth(i - 2).unwrap() == '(' {
                    f[i] = f[i - 2] + 2;
                } else if (i as i32) - f[i - 1] - 1 > 0
                    && s.chars().nth(i - (f[i - 1] as usize) - 2).unwrap() == '('
                {
                    f[i] = f[i - 1] + 2 + f[i - (f[i - 1] as usize) - 2];
                }
                ans = ans.max(f[i]);
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var longestValidParentheses = function (s) {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        if (s[i - 1] === ')') {
            if (s[i - 2] === '(') {
                f[i] = f[i - 2] + 2;
            } else {
                const j = i - f[i - 1] - 1;
                if (j && s[j - 1] === '(') {
                    f[i] = f[i - 1] + 2 + f[j - 1];
                }
            }
        }
    }
    return Math.max(...f);
};
```

#### C#

```cs
public class Solution {
    public int LongestValidParentheses(string s) {
        int n = s.Length;
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.Max(ans, f[i]);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Sử dụng ngăn xếp

<!-- thinking:start -->

> **Tư duy**
>
> Phương pháp 1 đã có độ phức tạp $O(n)$, nhưng công thức truy hồi tách theo việc ký tự trước đó là `(` hay không, cũng như theo việc ghép cặp xuyên qua một đoạn đã hợp lệ, nên các ranh giới rất dễ bị bỏ sót.
>
> Điều còn thiếu không phải là độ phức tạp tốt hơn, mà là một bản ghi khớp trực tiếp hơn với việc ghép cặp: các chỉ số của những `(` chưa được ghép cặp và vị trí bắt đầu của tiền tố cuối cùng bị phá vỡ.
>
> Khởi tạo ngăn xếp với $-1$ làm mốc, thêm vào khi gặp `(`, lấy ra khi gặp `)`. Ngăn xếp rỗng có nghĩa là `)` hiện tại tạo ra một mốc mới; nếu không, phần tử trên cùng của ngăn xếp cập nhật độ dài. Độ phức tạp thời gian và không gian vẫn là $O(n)$; quá trình ghép cặp chỉ dễ quan sát hơn.

<!-- thinking:end -->

- Duy trì một ngăn xếp để lưu các chỉ số của dấu ngoặc trái. Khởi tạo phần tử dưới cùng của ngăn xếp bằng giá trị -1 để thuận tiện cho việc tính độ dài của chuỗi dấu ngoặc đơn hợp lệ.
- Duyệt qua từng phần tử của chuỗi:
    - Nếu ký tự là dấu ngoặc trái, đẩy chỉ số của ký tự đó vào ngăn xếp.
    - Nếu ký tự là dấu ngoặc phải, lấy một phần tử ra khỏi ngăn xếp để biểu thị rằng ta đã tìm thấy một cặp dấu ngoặc hợp lệ.
        - Nếu ngăn xếp rỗng, điều đó có nghĩa là ta không tìm thấy dấu ngoặc trái để ghép với dấu ngoặc phải. Trong trường hợp này, đẩy chỉ số của ký tự đó vào làm điểm bắt đầu mới.
        - Nếu ngăn xếp không rỗng, tính độ dài của chuỗi dấu ngoặc đơn hợp lệ và cập nhật nó.

Tóm lại:

Điểm mấu chốt của thuật toán này là duy trì một ngăn xếp để lưu các chỉ số của dấu ngoặc trái, sau đó cập nhật độ dài của chuỗi con dấu ngoặc đơn hợp lệ bằng cách đẩy và lấy các phần tử ra khỏi ngăn xếp.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = [-1]
        ans = 0
        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            else:
                stack.pop()
                if not stack:
                    stack.append(i)
                else:
                    ans = max(ans, i - stack[-1])
        return ans
```

#### Go

```go
func longestValidParentheses(s string) int {
	ans := 0
	stack := []int{-1}
	for i, v := range s {
		if v == '(' {
			stack = append(stack, i)
		} else {
			stack = stack[:len(stack)-1]
			if len(stack) == 0 {
				stack = append(stack, i)
			} else {
				if ans < i-stack[len(stack)-1] {
					ans = i - stack[len(stack)-1]
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestValidParentheses(s: string): number {
    let max_length: number = 0;
    const stack: number[] = [-1];
    for (let i = 0; i < s.length; i++) {
        if (s.charAt(i) == '(') {
            stack.push(i);
        } else {
            stack.pop();

            if (stack.length === 0) {
                stack.push(i);
            } else {
                max_length = Math.max(max_length, i - stack[stack.length - 1]);
            }
        }
    }

    return max_length;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_valid_parentheses(s: String) -> i32 {
        let mut stack = vec![-1];
        let mut res = 0;
        for i in 0..s.len() {
            if let Some('(') = s.chars().nth(i) {
                stack.push(i as i32);
            } else {
                stack.pop().unwrap();
                if stack.is_empty() {
                    stack.push(i as i32);
                } else {
                    res = std::cmp::max(res, (i as i32) - stack.last().unwrap());
                }
            }
        }
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var longestValidParentheses = function (s) {
    let ans = 0;
    const stack = [-1];
    for (i = 0; i < s.length; i++) {
        if (s.charAt(i) === '(') {
            stack.push(i);
        } else {
            stack.pop();
            if (stack.length === 0) {
                stack.push(i);
            } else {
                ans = Math.max(ans, i - stack[stack.length - 1]);
            }
        }
    }
    return ans;
};
```

#### PHP

```php
class Solution {
    /**
     * @param string $s
     * @return integer
     */

    function longestValidParentheses($s) {
        $stack = [];
        $maxLength = 0;

        array_push($stack, -1);
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] === '(') {
                array_push($stack, $i);
            } else {
                array_pop($stack);

                if (empty($stack)) {
                    array_push($stack, $i);
                } else {
                    $length = $i - end($stack);
                    $maxLength = max($maxLength, $length);
                }
            }
        }
        return $maxLength;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
