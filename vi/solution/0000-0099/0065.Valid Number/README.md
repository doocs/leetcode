---
comments: true
difficulty: Hard
tags:
    - String
---

<!-- problem:start -->

# [65. Valid Number](https://leetcode.com/problems/valid-number)

[中文文档](/solution/0000-0099/0065.Valid%20Number/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code>, hãy trả về liệu <code>s</code> có phải là một <strong>số hợp lệ</strong> hay không.<br />
<br />
Ví dụ, tất cả các giá trị sau đều là số hợp lệ: <code>&quot;2&quot;, &quot;0089&quot;, &quot;-0.1&quot;, &quot;+3.14&quot;, &quot;4.&quot;, &quot;-.9&quot;, &quot;2e10&quot;, &quot;-90E3&quot;, &quot;3e+7&quot;, &quot;+6e-1&quot;, &quot;53.5e93&quot;, &quot;-123.456e789&quot;</code>, trong khi các giá trị sau không phải là số hợp lệ: <code>&quot;abc&quot;, &quot;1a&quot;, &quot;1e&quot;, &quot;e3&quot;, &quot;99e2.5&quot;, &quot;--6&quot;, &quot;-+3&quot;, &quot;95a54e53&quot;</code>.</p>

<p>Về mặt hình thức, một <strong>số hợp lệ</strong> được định nghĩa theo một trong các định nghĩa sau:</p>

<ol>
	<li>Một <strong>số nguyên</strong> theo sau bởi một <strong>số mũ tùy chọn</strong>.</li>
	<li>Một <strong>số thập phân</strong> theo sau bởi một <strong>số mũ tùy chọn</strong>.</li>
</ol>

<p>Một <strong>số nguyên</strong> được định nghĩa là một <strong>dấu tùy chọn</strong> <code>&#39;-&#39;</code> hoặc <code>&#39;+&#39;</code> theo sau bởi <strong>các chữ số</strong>.</p>

<p>Một <strong>số thập phân</strong> được định nghĩa là một <strong>dấu tùy chọn</strong> <code>&#39;-&#39;</code> hoặc <code>&#39;+&#39;</code> theo sau bởi một trong các định nghĩa sau:</p>

<ol>
	<li><strong>Các chữ số</strong> theo sau bởi một <strong>dấu chấm</strong> <code>&#39;.&#39;</code>.</li>
	<li><strong>Các chữ số</strong> theo sau bởi một <strong>dấu chấm</strong> <code>&#39;.&#39;</code> rồi theo sau bởi <strong>các chữ số</strong>.</li>
	<li>Một <strong>dấu chấm</strong> <code>&#39;.&#39;</code> theo sau bởi <strong>các chữ số</strong>.</li>
</ol>

<p>Một <strong>số mũ</strong> được định nghĩa là một <strong>ký hiệu số mũ</strong> <code>&#39;e&#39;</code> hoặc <code>&#39;E&#39;</code> theo sau bởi một <strong>số nguyên</strong>.</p>

<p><strong>Các chữ số</strong> được định nghĩa là một hoặc nhiều chữ số.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;0&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;e&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">false</span></p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;.&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> chỉ bao gồm các chữ cái tiếng Anh (cả chữ hoa và chữ thường), chữ số (<code>0-9</code>), dấu cộng <code>&#39;+&#39;</code>, dấu trừ <code>&#39;-&#39;</code>, hoặc dấu chấm <code>&#39;.&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Phân tích trường hợp

<!-- thinking:start -->

> **Tư duy**
>
> Một số hợp lệ có nhiều dạng: dấu tùy chọn, số nguyên hoặc số thập phân, sau đó là số mũ tùy chọn. $n \le 20$, vì vậy regex hoặc bộ phân tích cú pháp của ngôn ngữ đều sẽ đủ, nhưng chúng ta phải tự quyết định.
>
> Nút thắt nằm ở cách các quy tắc giao nhau: dấu chấm thập phân không thể xuất hiện trong số mũ, `e` cần có chữ số ở cả hai phía, số mũ có thể có dấu riêng, và một `.` hoặc `+` đứng riêng là không hợp lệ.
>
> Chỉ cần một lần quét từ trái sang phải: xử lý dấu ở đầu, sau đó đếm $dot$ và $e$ để mỗi ký tự xuất hiện nhiều nhất một lần, và từ chối ngay khi ký tự hoặc vị trí không hợp lệ. Thời gian tuyến tính, không gian phụ không đổi.

<!-- thinking:end -->

Trước tiên, chúng ta kiểm tra xem chuỗi có bắt đầu bằng dấu dương hoặc dấu âm hay không. Nếu có, chúng ta di chuyển con trỏ $i$ về phía trước một bước. Nếu tại thời điểm này con trỏ $i$ đã đến cuối chuỗi, điều đó có nghĩa là chuỗi chỉ chứa một dấu dương hoặc dấu âm, nên chúng ta trả về `false`.

Nếu ký tự mà con trỏ $i$ hiện tại trỏ tới là một dấu chấm thập phân, và không có số nào sau dấu chấm thập phân, hoặc có `e` hay `E` sau dấu chấm thập phân, chúng ta trả về `false`.

Tiếp theo, chúng ta sử dụng hai biến $dot$ và $e$ để ghi lại số lượng dấu chấm thập phân và `e` hoặc `E` tương ứng.

Chúng ta dùng con trỏ $j$ để trỏ tới ký tự hiện tại:

- Nếu ký tự hiện tại là một dấu chấm thập phân, và trước đó đã xuất hiện một dấu chấm thập phân hoặc `e` hoặc `E`, trả về `false`. Nếu không, tăng $dot$ lên một;
- Nếu ký tự hiện tại là `e` hoặc `E`, và `e` hoặc `E` đã xuất hiện trước đó, hoặc ký tự hiện tại ở đầu hoặc cuối chuỗi, trả về `false`. Nếu không, tăng $e$ lên một; sau đó kiểm tra xem ký tự tiếp theo có phải là dấu dương hoặc dấu âm hay không, nếu phải thì di chuyển con trỏ $j$ về phía trước một bước. Nếu tại thời điểm này con trỏ $j$ đã đến cuối chuỗi, trả về `false`;
- Nếu ký tự hiện tại không phải là một số, trả về `false`.

Sau khi duyệt qua chuỗi, trả về `true`.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(1)$. Trong đó, $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        n = len(s)
        i = 0
        if s[i] in '+-':
            i += 1
        if i == n:
            return False
        if s[i] == '.' and (i + 1 == n or s[i + 1] in 'eE'):
            return False
        dot = e = 0
        j = i
        while j < n:
            if s[j] == '.':
                if e or dot:
                    return False
                dot += 1
            elif s[j] in 'eE':
                if e or j == i or j == n - 1:
                    return False
                e += 1
                if s[j + 1] in '+-':
                    j += 1
                    if j == n - 1:
                        return False
            elif not s[j].isnumeric():
                return False
            j += 1
        return True
```

#### Java

```java
class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            ++i;
        }
        if (i == n) {
            return false;
        }
        if (s.charAt(i) == '.'
            && (i + 1 == n || s.charAt(i + 1) == 'e' || s.charAt(i + 1) == 'E')) {
            return false;
        }
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s.charAt(j) == '.') {
                if (e > 0 || dot > 0) {
                    return false;
                }
                ++dot;
            } else if (s.charAt(j) == 'e' || s.charAt(j) == 'E') {
                if (e > 0 || j == i || j == n - 1) {
                    return false;
                }
                ++e;
                if (s.charAt(j + 1) == '+' || s.charAt(j + 1) == '-') {
                    if (++j == n - 1) {
                        return false;
                    }
                }
            } else if (s.charAt(j) < '0' || s.charAt(j) > '9') {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isNumber(string s) {
        int n = s.size();
        int i = 0;
        if (s[i] == '+' || s[i] == '-') ++i;
        if (i == n) return false;
        if (s[i] == '.' && (i + 1 == n || s[i + 1] == 'e' || s[i + 1] == 'E')) return false;
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s[j] == '.') {
                if (e || dot) return false;
                ++dot;
            } else if (s[j] == 'e' || s[j] == 'E') {
                if (e || j == i || j == n - 1) return false;
                ++e;
                if (s[j + 1] == '+' || s[j + 1] == '-') {
                    if (++j == n - 1) return false;
                }
            } else if (s[j] < '0' || s[j] > '9')
                return false;
        }
        return true;
    }
};
```

#### Go

```go
func isNumber(s string) bool {
	i, n := 0, len(s)
	if s[i] == '+' || s[i] == '-' {
		i++
	}
	if i == n {
		return false
	}
	if s[i] == '.' && (i+1 == n || s[i+1] == 'e' || s[i+1] == 'E') {
		return false
	}
	var dot, e int
	for j := i; j < n; j++ {
		if s[j] == '.' {
			if e > 0 || dot > 0 {
				return false
			}
			dot++
		} else if s[j] == 'e' || s[j] == 'E' {
			if e > 0 || j == i || j == n-1 {
				return false
			}
			e++
			if s[j+1] == '+' || s[j+1] == '-' {
				j++
				if j == n-1 {
					return false
				}
			}
		} else if s[j] < '0' || s[j] > '9' {
			return false
		}
	}
	return true
}
```

#### Rust

```rust
impl Solution {
    pub fn is_number(s: String) -> bool {
        let mut i = 0;
        let n = s.len();

        if let Some(c) = s.chars().nth(i) {
            if c == '+' || c == '-' {
                i += 1;
                if i == n {
                    return false;
                }
            }
        }
        if let Some(x) = s.chars().nth(i) {
            if x == '.'
                && (i + 1 == n
                    || (if let Some(m) = s.chars().nth(i + 1) {
                        m == 'e' || m == 'E'
                    } else {
                        false
                    }))
            {
                return false;
            }
        }

        let mut dot = 0;
        let mut e = 0;
        let mut j = i;

        while j < n {
            if let Some(c) = s.chars().nth(j) {
                if c == '.' {
                    if e > 0 || dot > 0 {
                        return false;
                    }
                    dot += 1;
                } else if c == 'e' || c == 'E' {
                    if e > 0 || j == i || j == n - 1 {
                        return false;
                    }
                    e += 1;
                    if let Some(x) = s.chars().nth(j + 1) {
                        if x == '+' || x == '-' {
                            j += 1;
                            if j == n - 1 {
                                return false;
                            }
                        }
                    }
                } else if !c.is_ascii_digit() {
                    return false;
                }
            }
            j += 1;
        }

        true
    }
}
```

#### C#

```cs
public class Solution {
    private readonly Regex _isNumber_Regex = new Regex(@"^\s*[+-]?(\d+(\.\d*)?|\.\d+)([Ee][+-]?\d+)?\s*$");

    public bool IsNumber(string s) {
        return _isNumber_Regex.IsMatch(s);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
