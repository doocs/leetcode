---
comments: true
difficulty: Medium
tags:
    - String
---

<!-- problem:start -->

# [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi)

[中文文档](/solution/0000-0099/0008.String%20to%20Integer%20%28atoi%29/README.md)

## Mô tả

<!-- description:start -->

<p>Triển khai hàm <code>myAtoi(string s)</code>, hàm này chuyển đổi một chuỗi thành một số nguyên có dấu 32-bit.</p>

<p>Thuật toán cho <code>myAtoi(string s)</code> như sau:</p>

<ol>
	<li><strong>Khoảng trắng</strong>: Bỏ qua mọi khoảng trắng ở đầu (<code>&quot; &quot;</code>).</li>
	<li><strong>Dấu</strong>: Xác định dấu bằng cách kiểm tra xem ký tự tiếp theo có phải là <code>&#39;-&#39;</code> hoặc <code>&#39;+&#39;</code> hay không; giả sử số dương nếu không có ký tự nào trong hai ký tự trên.</li>
	<li><strong>Chuyển đổi</strong>: Đọc số nguyên bằng cách bỏ qua các số 0 ở đầu cho đến khi gặp một ký tự không phải chữ số hoặc đến cuối chuỗi. Nếu không đọc được chữ số nào, kết quả là 0.</li>
	<li><strong>Giới hạn</strong>: Nếu số nguyên nằm ngoài phạm vi số nguyên có dấu 32-bit <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, thì giới hạn số nguyên đó để nó nằm trong phạm vi. Cụ thể, các số nguyên nhỏ hơn <code>-2<sup>31</sup></code> phải được làm tròn thành <code>-2<sup>31</sup></code>, còn các số nguyên lớn hơn <code>2<sup>31</sup> - 1</code> phải được làm tròn thành <code>2<sup>31</sup> - 1</code>.</li>
</ol>

<p>Trả về số nguyên làm kết quả cuối cùng.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;42&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">42</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
Các ký tự được gạch chân là những ký tự được đọc, còn dấu mũ là vị trí hiện tại của bộ đọc.
Bước 1: &quot;42&quot; (không đọc ký tự nào vì không có khoảng trắng ở đầu)
         ^
Bước 2: &quot;42&quot; (không đọc ký tự nào vì không có dấu &#39;-&#39; hoặc &#39;+&#39;)
         ^
Bước 3: &quot;<u>42</u>&quot; (&quot;42&quot; được đọc)
           ^
</pre>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot; -042&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">-42</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
Bước 1: &quot;<u>   </u>-042&quot; (khoảng trắng ở đầu được đọc và bỏ qua)
            ^
Bước 2: &quot;   <u>-</u>042&quot; (đọc được &#39;-&#39;, nên kết quả phải là số âm)
             ^
Bước 3: &quot;   -<u>042</u>&quot; (&quot;042&quot; được đọc, các số 0 ở đầu được bỏ qua trong kết quả)
               ^
</pre>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;1337c0d3&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">1337</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
Bước 1: &quot;1337c0d3&quot; (không đọc ký tự nào vì không có khoảng trắng ở đầu)
         ^
Bước 2: &quot;1337c0d3&quot; (không đọc ký tự nào vì không có dấu &#39;-&#39; hoặc &#39;+&#39;)
         ^
Bước 3: &quot;<u>1337</u>c0d3&quot; (&quot;1337&quot; được đọc; dừng đọc vì ký tự tiếp theo không phải chữ số)
             ^
</pre>
</div>

<p><strong class="example">Ví dụ 4:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;0-1&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">0</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
Bước 1: &quot;0-1&quot; (không đọc ký tự nào vì không có khoảng trắng ở đầu)
         ^
Bước 2: &quot;0-1&quot; (không đọc ký tự nào vì không có dấu &#39;-&#39; hoặc &#39;+&#39;)
         ^
Bước 3: &quot;<u>0</u>-1&quot; (&quot;0&quot; được đọc; dừng đọc vì ký tự tiếp theo không phải chữ số)
           ^
</pre>
</div>

<p><strong class="example">Ví dụ 5:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;words and 987&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">0</span></p>

<p><strong>Giải thích:</strong></p>

<p>Việc đọc dừng lại ở ký tự không phải chữ số đầu tiên &#39;w&#39;.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 200</code></li>
	<li><code>s</code> bao gồm các chữ cái tiếng Anh (chữ thường và chữ hoa), các chữ số (<code>0-9</code>), <code>&#39; &#39;</code>, <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code> và <code>&#39;.&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt chuỗi

<!-- thinking:start -->

> **Tư duy**
>
> Chỉ cần quét từ trái sang phải một lần; $n \le 200$ không phải vấn đề về độ phức tạp. Những lỗi dễ mắc nằm ở thứ tự các quy tắc: bỏ qua các khoảng trắng ở đầu, xử lý nhiều nhất một dấu, lấy các chữ số liên tiếp, dừng ở ký tự đầu tiên không phải chữ số và giới hạn kết quả trong 32 bit.
>
> Không có số nguyên 64-bit, chúng ta không thể hoàn tất phép nhân rồi mới cắt ngưỡng. Chúng ta cộng dồn giá trị tuyệt đối, so sánh với $\lfloor (2^{31}-1)/10 \rfloor$ trước khi nhân với $10$, và bão hòa ở $2^{31}-1$ hoặc $-2^{31}$ khi tràn số.
>
> Cả hai dấu đều dùng chung ngưỡng trên; dấu được áp dụng ở cuối. Chuỗi rỗng hoặc chỉ gồm khoảng trắng không có chữ số nào, vì vậy chúng ta trả về $0$.

<!-- thinking:end -->

Trước tiên, chúng ta xác định chuỗi có rỗng hay không. Nếu rỗng, chúng ta trực tiếp trả về $0$.

Ngược lại, chúng ta cần duyệt chuỗi, bỏ qua các khoảng trắng ở đầu và xác định xem ký tự đầu tiên không phải khoảng trắng là dấu dương hay dấu âm.

Sau đó, chúng ta duyệt các ký tự tiếp theo. Nếu đó là một chữ số, chúng ta kiểm tra xem việc thêm chữ số này có gây tràn số nguyên hay không. Nếu có, chúng ta trả về kết quả tương ứng với dấu dương hoặc dấu âm. Nếu không, chúng ta thêm chữ số vào kết quả. Chúng ta tiếp tục duyệt các ký tự tiếp theo cho đến khi gặp ký tự không phải chữ số hoặc kết thúc quá trình duyệt.

Sau khi kết thúc quá trình duyệt, chúng ta trả về kết quả tương ứng với dấu dương hoặc dấu âm.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của chuỗi. Chúng ta chỉ cần xử lý lần lượt tất cả các ký tự. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def myAtoi(self, s: str) -> int:
        if not s:
            return 0
        n = len(s)
        if n == 0:
            return 0
        i = 0
        while s[i] == ' ':
            i += 1
            if i == n:
                return 0
        sign = -1 if s[i] == '-' else 1
        if s[i] in ['-', '+']:
            i += 1
        res, flag = 0, (2**31 - 1) // 10
        while i < n:
            if not s[i].isdigit():
                break
            c = int(s[i])
            if res > flag or (res == flag and c > 7):
                return 2**31 - 1 if sign > 0 else -(2**31)
            res = res * 10 + c
            i += 1
        return sign * res
```

#### Java

```java
class Solution {
    public int myAtoi(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n == 0) return 0;
        int i = 0;
        while (s.charAt(i) == ' ') {
            if (++i == n) return 0;
        }
        int sign = 1;
        if (s.charAt(i) == '-') sign = -1;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') ++i;
        int res = 0, flag = Integer.MAX_VALUE / 10;
        for (; i < n; ++i) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') break;
            if (res > flag || (res == flag && s.charAt(i) > '7'))
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (s.charAt(i) - '0');
        }
        return sign * res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int myAtoi(string s) {
        int i = 0, n = s.size();
        while (i < n && s[i] == ' ')
            ++i;

        int sign = 1;
        if (i < n && (s[i] == '-' || s[i] == '+')) {
            sign = s[i] == '-' ? -1 : 1;
            ++i;
        }

        int res = 0;
        while (i < n && isdigit(s[i])) {
            int digit = s[i] - '0';
            if (res > INT_MAX / 10 || (res == INT_MAX / 10 && digit > INT_MAX % 10)) {
                return sign == 1 ? INT_MAX : INT_MIN;
            }
            res = res * 10 + digit;
            ++i;
        }
        return res * sign;
    }
};
```

#### Go

```go
func myAtoi(s string) int {
	i, n := 0, len(s)
	num := 0

	for i < n && s[i] == ' ' {
		i++
	}
	if i == n {
		return 0
	}

	sign := 1
	if s[i] == '-' {
		sign = -1
		i++
	} else if s[i] == '+' {
		i++
	}

	for i < n && s[i] >= '0' && s[i] <= '9' {
		num = num*10 + int(s[i]-'0')
		i++
		if num > math.MaxInt32 {
			break
		}
	}

	if num > math.MaxInt32 {
		if sign == -1 {
			return math.MinInt32
		}
		return math.MaxInt32
	}
	return sign * num
}
```

#### JavaScript

```js
const myAtoi = function (str) {
    str = str.trim();
    if (!str) return 0;
    let isPositive = 1;
    let i = 0,
        ans = 0;
    if (str[i] === '+') {
        isPositive = 1;
        i++;
    } else if (str[i] === '-') {
        isPositive = 0;
        i++;
    }
    for (; i < str.length; i++) {
        let t = str.charCodeAt(i) - 48;
        if (t > 9 || t < 0) break;
        if (ans > 2147483647 / 10 || ans > (2147483647 - t) / 10) {
            return isPositive ? 2147483647 : -2147483648;
        } else {
            ans = ans * 10 + t;
        }
    }
    return isPositive ? ans : -ans;
};
```

#### C#

```cs
public class Solution {
    public int MyAtoi(string str) {
        int i = 0;
        long result = 0;
        bool minus = false;
        while (i < str.Length && char.IsWhiteSpace(str[i])) {
            ++i;
        }
        if (i < str.Length) {
            if (str[i] == '+') {
                ++i;
            }
            else if (str[i] == '-') {
                minus = true;
                ++i;
            }
        }
        while (i < str.Length && char.IsDigit(str[i])) {
            result = result * 10 + str[i] - '0';
            if (result > int.MaxValue) {
                break;
            }
            ++i;
        }
        if (minus) result = -result;
        if (result > int.MaxValue) {
            result = int.MaxValue;
        }
        if (result < int.MinValue) {
            result = int.MinValue;
        }
        return (int)result;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param string $s
     * @return int
     */

    function myAtoi($s) {
        $s = str_replace('e', 'x', $s);
        if (intval($s) < pow(-2, 31)) {
            return -2147483648;
        }
        if (intval($s) > pow(2, 31) - 1) {
            return 2147483647;
        }
        return intval($s);
    }
}
```

#### C

```c
int myAtoi(char* s) {
    int i = 0;

    while (s[i] == ' ') {
        i++;
    }

    int sign = 1;
    if (s[i] == '-' || s[i] == '+') {
        sign = (s[i] == '-') ? -1 : 1;
        i++;
    }

    int res = 0;
    while (isdigit(s[i])) {
        int digit = s[i] - '0';
        if (res > INT_MAX / 10 || (res == INT_MAX / 10 && digit > INT_MAX % 10)) {
            return sign == 1 ? INT_MAX : INT_MIN;
        }
        res = res * 10 + digit;
        i++;
    }

    return res * sign;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
