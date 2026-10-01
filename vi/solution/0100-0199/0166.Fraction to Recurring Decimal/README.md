---
comments: true
difficulty: Medium
tags:
    - Hash Table
    - Math
    - String
---

<!-- problem:start -->

# [166. Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal)

[中文文档](/solution/0100-0199/0166.Fraction%20to%20Recurring%20Decimal/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai số nguyên biểu diễn <code>numerator</code> và <code>denominator</code> của một phân số, hãy trả về <em>phân số ở dạng chuỗi</em>.</p>

<p>Nếu phần thập phân lặp lại, hãy đặt phần lặp trong dấu ngoặc đơn.</p>

<p>Nếu có nhiều đáp án, hãy trả về <strong>bất kỳ đáp án nào trong số đó</strong>.</p>

<p><strong>Đảm bảo</strong> rằng độ dài của chuỗi kết quả nhỏ hơn <code>10<sup>4</sup></code> với mọi đầu vào đã cho.</p>

<p><strong>Lưu ý</strong> rằng nếu phân số có thể biểu diễn dưới dạng <em>chuỗi hữu hạn</em>, bạn <strong>phải</strong> trả về nó.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> numerator = 1, denominator = 2
<strong>Đầu ra:</strong> &quot;0.5&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> numerator = 2, denominator = 1
<strong>Đầu ra:</strong> &quot;2&quot;
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> numerator = 4, denominator = 333
<strong>Đầu ra:</strong> &quot;0.(012)&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;=&nbsp;numerator, denominator &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>denominator != 0</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Toán học + Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Viết phân số dưới dạng số thập phân và đặt phần lặp trong dấu ngoặc. Phép chia dài lặp lại khi và chỉ khi một số dư lặp lại. Trước hết, xử lý dấu và phần nguyên. Trong phần thập phân, ánh xạ mỗi số dư tới chỉ số đầu tiên mà nó xuất hiện; khi gặp lại một số dư, chèn dấu ngoặc tại vị trí đó. Số dư $0$ cho biết số thập phân kết thúc.

<!-- thinking:end -->

Trước hết, chúng ta kiểm tra xem $numerator$ có bằng $0$ hay không. Nếu có, chúng ta trả về trực tiếp `"0"`.

Tiếp theo, chúng ta kiểm tra xem $numerator$ và $denominator$ có trái dấu hay không. Nếu có, kết quả là số âm và chúng ta đặt ký tự đầu tiên của kết quả là `"-"`.

Sau đó, chúng ta lấy giá trị tuyệt đối của $numerator$ và $denominator$, lần lượt ký hiệu là $a$ và $b$. Vì miền giá trị của numerator và denominator là $[-2^{31}, 2^{31} - 1]$, việc lấy giá trị tuyệt đối trực tiếp có thể gây tràn số, nên chúng ta chuyển cả $a$ và $b$ sang số nguyên long.

Tiếp theo, chúng ta tính phần nguyên, tức là phần nguyên của phép chia $a$ cho $b$, chuyển nó thành chuỗi và thêm vào kết quả. Sau đó, chúng ta lấy phần dư của phép chia $a$ cho $b$, ký hiệu là $a$.

Nếu $a$ bằng $0$, điều đó có nghĩa là kết quả là một số nguyên, và chúng ta trả về kết quả trực tiếp.

Tiếp theo, chúng ta tính phần thập phân. Chúng ta sử dụng một bảng băm $d$ để ghi lại độ dài của kết quả tương ứng với mỗi số dư. Chúng ta liên tục nhân $a$ với $10$, sau đó cộng phần nguyên của phép chia $a$ cho $b$ vào kết quả, rồi lấy phần dư của phép chia $a$ cho $b$, ký hiệu là $a$. Nếu $a$ bằng $0$, điều đó có nghĩa là kết quả là một số thập phân hữu hạn, và chúng ta trả về kết quả trực tiếp. Nếu $a$ đã xuất hiện trong bảng băm, điều đó có nghĩa là kết quả là một số thập phân tuần hoàn. Chúng ta tìm vị trí bắt đầu của chu kỳ, chèn kết quả vào trong dấu ngoặc đơn, rồi trả về kết quả.

Độ phức tạp thời gian là $O(l)$ và độ phức tạp không gian là $O(l)$, trong đó $l$ là độ dài của kết quả. Trong bài toán này, $l < 10^4$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        if numerator == 0:
            return "0"
        ans = []
        neg = (numerator > 0) ^ (denominator > 0)
        if neg:
            ans.append("-")
        a, b = abs(numerator), abs(denominator)
        ans.append(str(a // b))
        a %= b
        if a == 0:
            return "".join(ans)
        ans.append(".")
        d = {}
        while a:
            d[a] = len(ans)
            a *= 10
            ans.append(str(a // b))
            a %= b
            if a in d:
                ans.insert(d[a], "(")
                ans.append(")")
                break
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean neg = (numerator > 0) ^ (denominator > 0);
        sb.append(neg ? "-" : "");
        long a = Math.abs((long) numerator), b = Math.abs((long) denominator);
        sb.append(a / b);
        a %= b;
        if (a == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> d = new HashMap<>();
        while (a != 0) {
            d.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;
            if (d.containsKey(a)) {
                sb.insert(d.get(a), "(");
                sb.append(")");
                break;
            }
        }
        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        string ans;
        bool neg = (numerator > 0) ^ (denominator > 0);
        if (neg) {
            ans += "-";
        }
        long long a = abs(1LL * numerator), b = abs(1LL * denominator);
        ans += to_string(a / b);
        a %= b;
        if (a == 0) {
            return ans;
        }
        ans += ".";
        unordered_map<long long, int> d;
        while (a) {
            d[a] = ans.size();
            a *= 10;
            ans += to_string(a / b);
            a %= b;
            if (d.contains(a)) {
                ans.insert(d[a], "(");
                ans += ")";
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func fractionToDecimal(numerator int, denominator int) string {
	if numerator == 0 {
		return "0"
	}
	ans := ""
	if (numerator > 0) != (denominator > 0) {
		ans += "-"
	}
	a := int64(numerator)
	b := int64(denominator)
	a = abs(a)
	b = abs(b)
	ans += strconv.FormatInt(a/b, 10)
	a %= b
	if a == 0 {
		return ans
	}
	ans += "."
	d := make(map[int64]int)
	for a != 0 {
		if pos, ok := d[a]; ok {
			ans = ans[:pos] + "(" + ans[pos:] + ")"
			break
		}
		d[a] = len(ans)
		a *= 10
		ans += strconv.FormatInt(a/b, 10)
		a %= b
	}
	return ans
}

func abs(x int64) int64 {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function fractionToDecimal(numerator: number, denominator: number): string {
    if (numerator === 0) {
        return '0';
    }
    const sb: string[] = [];
    const neg: boolean = numerator > 0 !== denominator > 0;
    sb.push(neg ? '-' : '');
    let a: number = Math.abs(numerator),
        b: number = Math.abs(denominator);
    sb.push(Math.floor(a / b).toString());
    a %= b;
    if (a === 0) {
        return sb.join('');
    }
    sb.push('.');
    const d: Map<number, number> = new Map();
    while (a !== 0) {
        d.set(a, sb.length);
        a *= 10;
        sb.push(Math.floor(a / b).toString());
        a %= b;
        if (d.has(a)) {
            sb.splice(d.get(a)!, 0, '(');
            sb.push(')');
            break;
        }
    }
    return sb.join('');
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn fraction_to_decimal(numerator: i32, denominator: i32) -> String {
        if numerator == 0 {
            return "0".to_string();
        }
        let mut ans = String::new();

        let neg = (numerator > 0) ^ (denominator > 0);
        if neg {
            ans.push('-');
        }

        let mut a = (numerator as i64).abs();
        let b = (denominator as i64).abs();

        ans.push_str(&(a / b).to_string());
        a %= b;

        if a == 0 {
            return ans;
        }

        ans.push('.');

        let mut d: HashMap<i64, usize> = HashMap::new();
        while a != 0 {
            if let Some(&pos) = d.get(&a) {
                ans.insert(pos, '(');
                ans.push(')');
                break;
            }
            d.insert(a, ans.len());
            a *= 10;
            ans.push_str(&(a / b).to_string());
            a %= b;
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public string FractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        bool neg = (numerator > 0) ^ (denominator > 0);
        sb.Append(neg ? "-" : "");
        long a = Math.Abs((long)numerator), b = Math.Abs((long)denominator);
        sb.Append(a / b);
        a %= b;
        if (a == 0) {
            return sb.ToString();
        }
        sb.Append(".");
        Dictionary<long, int> d = new Dictionary<long, int>();
        while (a != 0) {
            d[a] = sb.Length;
            a *= 10;
            sb.Append(a / b);
            a %= b;
            if (d.ContainsKey(a)) {
                sb.Insert(d[a], "(");
                sb.Append(")");
                break;
            }
        }
        return sb.ToString();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
