---
comments: true
difficulty: Easy
tags:
    - Bit Manipulation
    - Math
    - String
    - Simulation
---

<!-- problem:start -->

# [67. Add Binary](https://leetcode.com/problems/add-binary)

[中文文档](/solution/0000-0099/0067.Add%20Binary/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi nhị phân <code>a</code> và <code>b</code>, hãy trả về <em>tổng của chúng dưới dạng một chuỗi nhị phân</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> a = "11", b = "1"
<strong>Đầu ra:</strong> "100"
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> a = "1010", b = "1011"
<strong>Đầu ra:</strong> "10101"
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>4</sup></code></li>
	<li><code>a</code> và <code>b</code> chỉ&nbsp;gồm các ký tự <code>&#39;0&#39;</code> hoặc <code>&#39;1&#39;</code>.</li>
	<li>Mỗi chuỗi không chứa số 0 ở đầu, ngoại trừ chính số 0.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là chuyển cả hai chuỗi nhị phân thành số nguyên, cộng chúng rồi chuyển ngược lại. Độ dài có thể lên tới $10^4$, vượt quá giới hạn của các kiểu số nguyên thông thường.
>
> Nút thắt nằm ở độ dài không bằng nhau và khả năng xuất hiện thêm một bit nhớ ở vị trí cao nhất. Đây chính là phép cộng theo cách chúng ta học ở trường như với hệ thập phân: căn chỉnh từ phía bit thấp và duy trì một $\textit{carry}$.
>
> Hai con trỏ duyệt từ các đuôi; bit bị thiếu được xem là $0$. Vòng lặp phải tiếp tục khi $\textit{carry}$ vẫn còn, nếu không một $1$ cuối cùng như `"1"+"1"` sẽ bị bỏ mất.

<!-- thinking:end -->

Chúng ta dùng một biến $\textit{carry}$ để ghi lại giá trị nhớ hiện tại, và hai con trỏ $i$ và $j$ lần lượt trỏ đến cuối $a$ và $b$, rồi cộng từng bit từ cuối về đầu.

Độ phức tạp thời gian là $O(\max(m, n))$, trong đó $m$ và $n$ lần lượt là độ dài của các chuỗi $a$ và $b$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        ans = []
        i, j, carry = len(a) - 1, len(b) - 1, 0
        while i >= 0 or j >= 0 or carry:
            carry += (0 if i < 0 else int(a[i])) + (0 if j < 0 else int(b[j]))
            carry, v = divmod(carry, 2)
            ans.append(str(v))
            i, j = i - 1, j - 1
        return "".join(ans[::-1])
```

#### Java

```java
class Solution {
    public String addBinary(String a, String b) {
        var sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        for (int carry = 0; i >= 0 || j >= 0 || carry > 0; --i, --j) {
            carry += (i >= 0 ? a.charAt(i) - '0' : 0) + (j >= 0 ? b.charAt(j) - '0' : 0);
            sb.append(carry % 2);
            carry /= 2;
        }
        return sb.reverse().toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string addBinary(string a, string b) {
        string ans;
        int i = a.size() - 1, j = b.size() - 1;
        for (int carry = 0; i >= 0 || j >= 0 || carry; --i, --j) {
            carry += (i >= 0 ? a[i] - '0' : 0) + (j >= 0 ? b[j] - '0' : 0);
            ans.push_back((carry % 2) + '0');
            carry /= 2;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func addBinary(a string, b string) string {
	i, j := len(a)-1, len(b)-1
	ans := []byte{}
	for carry := 0; i >= 0 || j >= 0 || carry > 0; i, j = i-1, j-1 {
		if i >= 0 {
			carry += int(a[i] - '0')
		}
		if j >= 0 {
			carry += int(b[j] - '0')
		}
		ans = append(ans, byte(carry%2+'0'))
		carry /= 2
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

#### TypeScript

```ts
function addBinary(a: string, b: string): string {
    let i = a.length - 1;
    let j = b.length - 1;
    const ans: number[] = [];
    for (let carry = 0; i >= 0 || j >= 0 || carry; --i, --j) {
        carry += (i >= 0 ? a[i] : '0').charCodeAt(0) - '0'.charCodeAt(0);
        carry += (j >= 0 ? b[j] : '0').charCodeAt(0) - '0'.charCodeAt(0);
        ans.push(carry % 2);
        carry >>= 1;
    }
    return ans.reverse().join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn add_binary(a: String, b: String) -> String {
        let mut i = (a.len() as i32) - 1;
        let mut j = (b.len() as i32) - 1;
        let mut carry = 0;
        let mut ans = String::new();
        let a = a.as_bytes();
        let b = b.as_bytes();
        while i >= 0 || j >= 0 || carry > 0 {
            if i >= 0 {
                carry += a[i as usize] - b'0';
                i -= 1;
            }
            if j >= 0 {
                carry += b[j as usize] - b'0';
                j -= 1;
            }
            ans.push_str(&(carry % 2).to_string());
            carry /= 2;
        }
        ans.chars().rev().collect()
    }
}
```

#### C#

```cs
public class Solution {
    public string AddBinary(string a, string b) {
        int i = a.Length - 1;
        int j = b.Length - 1;
        var sb = new StringBuilder();
        for (int carry = 0; i >= 0 || j >= 0 || carry > 0; --i, --j) {
            carry += i >= 0 ? a[i] - '0' : 0;
            carry += j >= 0 ? b[j] - '0' : 0;
            sb.Append(carry % 2);
            carry /= 2;
        }
        var ans = sb.ToString().ToCharArray();
        Array.Reverse(ans);
        return new string(ans);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
