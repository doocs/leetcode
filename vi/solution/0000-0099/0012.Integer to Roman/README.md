---
comments: true
difficulty: Medium
tags:
    - Hash Table
    - Math
    - String
---

<!-- problem:start -->

# [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman)

[中文文档](/solution/0000-0099/0012.Integer%20to%20Roman/README.md)

## Mô tả

<!-- description:start -->

<p>Bảy ký hiệu khác nhau biểu diễn các chữ số La Mã với các giá trị sau:</p>

<table>
	<thead>
		<tr>
			<th>Ký hiệu</th>
			<th>Giá trị</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>I</td>
			<td>1</td>
		</tr>
		<tr>
			<td>V</td>
			<td>5</td>
		</tr>
		<tr>
			<td>X</td>
			<td>10</td>
		</tr>
		<tr>
			<td>L</td>
			<td>50</td>
		</tr>
		<tr>
			<td>C</td>
			<td>100</td>
		</tr>
		<tr>
			<td>D</td>
			<td>500</td>
		</tr>
		<tr>
			<td>M</td>
			<td>1000</td>
		</tr>
	</tbody>
</table>

<p>Chữ số La Mã được tạo thành bằng cách nối các biểu diễn của các giá trị hàng thập phân theo thứ tự từ lớn đến nhỏ. Việc chuyển một giá trị hàng thập phân thành chữ số La Mã tuân theo các quy tắc sau:</p>

<ul>
	<li>Nếu giá trị không bắt đầu bằng 4 hoặc 9, chọn ký hiệu có giá trị lớn nhất có thể được trừ khỏi đầu vào, nối ký hiệu đó vào kết quả, trừ giá trị của nó rồi chuyển phần còn lại thành chữ số La Mã.</li>
	<li>Nếu giá trị bắt đầu bằng 4 hoặc 9, hãy sử dụng <strong>dạng trừ</strong>, biểu diễn một ký hiệu bị trừ khỏi ký hiệu tiếp theo. Ví dụ, 4 là 1 (<code>I</code>) nhỏ hơn 5 (<code>V</code>): <code>IV</code>, và 9 là 1 (<code>I</code>) nhỏ hơn 10 (<code>X</code>): <code>IX</code>. Chỉ sử dụng các dạng trừ sau: 4 (<code>IV</code>), 9 (<code>IX</code>), 40 (<code>XL</code>), 90 (<code>XC</code>), 400 (<code>CD</code>) và 900 (<code>CM</code>).</li>
	<li>Chỉ các lũy thừa của 10 (<code>I</code>, <code>X</code>, <code>C</code>, <code>M</code>) mới có thể được nối liên tiếp tối đa 3 lần để biểu diễn các bội số của 10. Bạn không thể nối 5 (<code>V</code>), 50 (<code>L</code>) hoặc 500 (<code>D</code>) nhiều lần. Nếu cần nối một ký hiệu 4 lần, hãy sử dụng <strong>dạng trừ</strong>.</li>
</ul>

<p>Cho một số nguyên, hãy chuyển nó thành chữ số La Mã.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">num = 3749</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;MMMDCCXLIX&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
3000 = MMM vì 1000 (M) + 1000 (M) + 1000 (M)
 700 = DCC vì 500 (D) + 100 (C) + 100 (C)
  40 = XL vì 10 (X) nhỏ hơn 50 (L)
   9 = IX vì 1 (I) nhỏ hơn 10 (X)
Lưu ý: 49 không phải là 1 (I) nhỏ hơn 50 (L) vì phép chuyển đổi dựa trên các hàng thập phân
</pre>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">num = 58</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;LVIII&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
50 = L
 8 = VIII
</pre>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">num = 1994</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">&quot;MCMXCIV&quot;</span></p>

<p><strong>Giải thích:</strong></p>

<pre>
1000 = M
 900 = CM
  90 = XC
   4 = IV
</pre>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 3999</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tham lam

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là tra cứu theo từng hàng (hàng nghìn, hàng trăm, hàng chục, hàng đơn vị), kèm thêm các nhánh cho $4$ và $9$. Vì $1 \le num \le 3999$, cách này vẫn đáp ứng được, nhưng các trường hợp đặc biệt rất dễ bị bỏ sót.
>
> Điểm rắc rối là phải tách riêng các quy tắc "cộng" và "trừ". Nếu coi $\textit{CM}=900$, $\textit{CD}=400$, $\textit{XC}=90$ và các giá trị tương tự là những mệnh giá độc lập, chữ số La Mã sẽ trở thành một danh sách cố định các giá trị từ lớn đến nhỏ.
>
> Biểu diễn trong phạm vi này là duy nhất, nên chọn mệnh giá lớn nhất vẫn còn phù hợp luôn là hợp lệ. Chúng ta duyệt bảng đó theo cách tham lam, trừ và nối cho đến khi $num$ trở thành $0$.

<!-- thinking:end -->

Trước tiên, chúng ta có thể liệt kê tất cả các ký hiệu có thể có $cs$ và các giá trị tương ứng $vs$, sau đó duyệt từng giá trị $vs[i]$ từ lớn đến nhỏ. Mỗi lần, chúng ta sử dụng nhiều nhất có thể các ký hiệu $cs[i]$ tương ứng với giá trị này, cho đến khi số $num$ trở thành $0$.

Độ phức tạp thời gian là $O(m)$, và độ phức tạp không gian là $O(m)$. Trong đó, $m$ là số lượng ký hiệu.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intToRoman(self, num: int) -> str:
        cs = ('M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I')
        vs = (1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        ans = []
        for c, v in zip(cs, vs):
            while num >= v:
                num -= v
                ans.append(c)
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String intToRoman(int num) {
        List<String> cs
            = List.of("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        List<Integer> vs = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        StringBuilder ans = new StringBuilder();
        for (int i = 0, n = cs.size(); i < n; ++i) {
            while (num >= vs.get(i)) {
                num -= vs.get(i);
                ans.append(cs.get(i));
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string intToRoman(int num) {
        vector<string> cs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        vector<int> vs = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        string ans;
        for (int i = 0; i < cs.size(); ++i) {
            while (num >= vs[i]) {
                num -= vs[i];
                ans += cs[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func intToRoman(num int) string {
	cs := []string{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}
	vs := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
	ans := &strings.Builder{}
	for i, v := range vs {
		for num >= v {
			num -= v
			ans.WriteString(cs[i])
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function intToRoman(num: number): string {
    const cs: string[] = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I'];
    const vs: number[] = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
    const ans: string[] = [];
    for (let i = 0; i < vs.length; ++i) {
        while (num >= vs[i]) {
            num -= vs[i];
            ans.push(cs[i]);
        }
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn int_to_roman(num: i32) -> String {
        let cs = [
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I",
        ];
        let vs = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
        let mut num = num;
        let mut ans = String::new();

        for (i, &v) in vs.iter().enumerate() {
            while num >= v {
                num -= v;
                ans.push_str(cs[i]);
            }
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public string IntToRoman(int num) {
        List<string> cs = new List<string>{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        List<int> vs = new List<int>{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cs.Count; i++) {
            while (num >= vs[i]) {
                ans.Append(cs[i]);
                num -= vs[i];
            }
        }
        return ans.ToString();
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $num
     * @return String
     */
    function intToRoman($num) {
        $cs = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I'];
        $vs = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
        $ans = '';

        foreach ($vs as $i => $v) {
            while ($num >= $v) {
                $num -= $v;
                $ans .= $cs[$i];
            }
        }

        return $ans;
    }
}
```

#### C

```c
static const char* cs[] = {
    "M", "CM", "D", "CD", "C", "XC",
    "L", "XL", "X", "IX", "V", "IV", "I"};

static const int vs[] = {
    1000, 900, 500, 400, 100, 90,
    50, 40, 10, 9, 5, 4, 1};

char* intToRoman(int num) {
    static char ans[20];
    ans[0] = '\0';
    for (int i = 0; i < 13; ++i) {
        while (num >= vs[i]) {
            num -= vs[i];
            strcat(ans, cs[i]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
