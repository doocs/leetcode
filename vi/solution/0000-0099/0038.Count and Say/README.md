---
comments: true
difficulty: Medium
tags:
    - String
---

<!-- problem:start -->

# [38. Count and Say](https://leetcode.com/problems/count-and-say)

[中文文档](/solution/0000-0099/0038.Count%20and%20Say/README.md)

## Mô tả

<!-- description:start -->

<p>Chuỗi <strong>count-and-say</strong> là một chuỗi các chữ số được xác định bởi công thức đệ quy:</p>

<ul>
	<li><code>countAndSay(1) = &quot;1&quot;</code></li>
	<li><code>countAndSay(n)</code> là mã hóa độ dài chạy của <code>countAndSay(n - 1)</code>.</li>
</ul>

<p><a href="http://en.wikipedia.org/wiki/Run-length_encoding" target="_blank">Mã hóa độ dài chạy</a> (RLE) là một phương pháp nén chuỗi, hoạt động bằng cách thay thế mỗi nhóm cực đại gồm các ký tự giống nhau liên tiếp bằng phép nối độ dài của nhóm với chính ký tự đó. Ví dụ, để nén chuỗi <code>&quot;3322251&quot;</code>, ta thay <code>&quot;33&quot;</code> bằng <code>&quot;23&quot;</code>, thay <code>&quot;222&quot;</code> bằng <code>&quot;32&quot;</code>, thay <code>&quot;5&quot;</code> bằng <code>&quot;15&quot;</code>, và thay <code>&quot;1&quot;</code> bằng <code>&quot;11&quot;</code>. Vì vậy, chuỗi được nén trở thành <code>&quot;23321511&quot;</code>.</p>

<p>Cho một số nguyên dương <code>n</code>, hãy trả về <em>phần tử thứ </em><code>n<sup>th</sup></code><em> của chuỗi <strong>count-and-say</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;1211&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
countAndSay(1) = &quot;1&quot;
countAndSay(2) = RLE của &quot;1&quot; = &quot;11&quot;
countAndSay(3) = RLE của &quot;11&quot; = &quot;21&quot;
countAndSay(4) = RLE của &quot;21&quot; = &quot;1211&quot;
</pre>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;1&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<p>Đây là trường hợp cơ sở.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán bằng cách lặp không?

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Bản thân định nghĩa đã là “phần tử thứ $n$ mô tả phần tử thứ $(n-1)$”, nên không có công thức đóng nào rẻ hơn. Vì $n \le 30$, chúng ta có thể bắt đầu từ `"1"` và lặp $n-1$ lần.
>
> Mỗi phần tử là mã hóa độ dài chạy của phần tử trước đó: một dãy các ký tự giống nhau trở thành “số lượng + ký tự”.
>
> Hai con trỏ duyệt chuỗi hiện tại $s$ và thêm độ dài cùng ký tự của từng đoạn vào một chuỗi mới. Câu hỏi mở rộng yêu cầu lời giải lặp, cách này cũng tránh ngăn xếp đệ quy.

<!-- thinking:end -->

Nhiệm vụ yêu cầu xuất ra chuỗi mô tả của phần tử thứ $n$, trong đó phần tử thứ $n$ là mô tả của phần tử thứ $n-1$ trong chuỗi. Do đó, chúng ta lặp $n-1$ lần. Trong mỗi lần lặp, chúng ta sử dụng hai con trỏ nhanh và chậm, lần lượt ký hiệu là j và i, để ghi lại vị trí của ký tự hiện tại và vị trí của ký tự tiếp theo không bằng ký tự hiện tại. Sau đó, chúng ta cập nhật chuỗi của phần tử trước đó thành $j-i$ lần xuất hiện của ký tự hiện tại.

Độ phức tạp thời gian:

1. Vòng lặp ngoài chạy `n - 1` lần, lặp để tạo chuỗi "Count and Say" cho đến phần tử thứ n.
2. Vòng lặp while bên trong duyệt qua từng ký tự trong chuỗi hiện tại s và đếm số lần xuất hiện liên tiếp của cùng một ký tự.
3. Vòng lặp while bên trong chạy trong thời gian $O(m)$, trong đó m là độ dài của chuỗi hiện tại s.

Nhìn chung, độ phức tạp thời gian là $O(n \times m)$, trong đó n là tham số đầu vào đại diện cho phần tử cần tạo và m là độ dài lớn nhất của chuỗi trong dãy.

Độ phức tạp không gian: $O(m)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countAndSay(self, n: int) -> str:
        s = '1'
        for _ in range(n - 1):
            i = 0
            t = []
            while i < len(s):
                j = i
                while j < len(s) and s[j] == s[i]:
                    j += 1
                t.append(str(j - i))
                t.append(str(s[i]))
                i = j
            s = ''.join(t)
        return s
```

#### Java

```java
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        while (--n > 0) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < s.length();) {
                int j = i;
                while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                    ++j;
                }
                t.append((j - i) + "");
                t.append(s.charAt(i));
                i = j;
            }
            s = t.toString();
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string countAndSay(int n) {
        string s = "1";
        while (--n) {
            string t = "";
            for (int i = 0; i < s.size();) {
                int j = i;
                while (j < s.size() && s[j] == s[i]) ++j;
                t += to_string(j - i);
                t += s[i];
                i = j;
            }
            s = t;
        }
        return s;
    }
};
```

#### Go

```go
func countAndSay(n int) string {
	s := "1"
	for k := 0; k < n-1; k++ {
		t := &strings.Builder{}
		i := 0
		for i < len(s) {
			j := i
			for j < len(s) && s[j] == s[i] {
				j++
			}
			t.WriteString(strconv.Itoa(j - i))
			t.WriteByte(s[i])
			i = j
		}
		s = t.String()
	}
	return s
}
```

#### TypeScript

```ts
function countAndSay(n: number): string {
    let s = '1';
    for (let i = 1; i < n; i++) {
        let t = '';
        let cur = s[0];
        let count = 1;
        for (let j = 1; j < s.length; j++) {
            if (s[j] !== cur) {
                t += `${count}${cur}`;
                cur = s[j];
                count = 0;
            }
            count++;
        }
        t += `${count}${cur}`;
        s = t;
    }
    return s;
}
```

#### Rust

```rust
use std::iter::once;

impl Solution {
    pub fn count_and_say(n: i32) -> String {
        (1..n)
            .fold(vec![1], |curr, _| {
                let mut next = vec![];
                let mut slow = 0;
                for fast in 0..=curr.len() {
                    if fast == curr.len() || curr[slow] != curr[fast] {
                        next.extend(once((fast - slow) as u8).chain(once(curr[slow])));
                        slow = fast;
                    }
                }
                next
            })
            .into_iter()
            .map(|digit| (digit + b'0') as char)
            .collect()
    }
}
```

#### JavaScript

```js
const countAndSay = function (n) {
    let s = '1';

    for (let i = 2; i <= n; i++) {
        let count = 1,
            str = '',
            len = s.length;

        for (let j = 0; j < len; j++) {
            if (j < len - 1 && s[j] === s[j + 1]) {
                count++;
            } else {
                str += `${count}${s[j]}`;
                count = 1;
            }
        }
        s = str;
    }
    return s;
};
```

#### C#

```cs
public class Solution {
    public string CountAndSay(int n) {
        var s = "1";
        while (n > 1) {
            var sb = new StringBuilder();
            var lastChar = '1';
            var count = 0;
            foreach (var ch in s) {
                if (count > 0 && lastChar == ch) {
                    ++count;
                }
                else {
                    if (count > 0) {
                        sb.Append(count);
                        sb.Append(lastChar);
                    }
                    lastChar = ch;
                    count = 1;
                }
            }
            if (count > 0) {
                sb.Append(count);
                sb.Append(lastChar);
            }
            s = sb.ToString();
            --n;
        }
        return s;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer $n
     * @return string
     */

    function countAndSay($n) {
        if ($n <= 0) {
            return '';
        }

        $result = '1';
        for ($i = 2; $i <= $n; $i++) {
            $count = 1;
            $say = '';
            for ($j = 1; $j < strlen($result); $j++) {
                if ($result[$j] == $result[$j - 1]) {
                    $count++;
                } else {
                    $say .= $count . $result[$j - 1];
                    $count = 1;
                }
            }
            $say .= $count . $result[strlen($result) - 1];
            $result = $say;
        }
        return $result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
