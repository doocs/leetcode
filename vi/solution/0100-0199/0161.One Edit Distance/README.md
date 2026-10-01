---
comments: true
difficulty: Medium
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [161. One Edit Distance 🔒](https://leetcode.com/problems/one-edit-distance)

[中文文档](/solution/0100-0199/0161.One%20Edit%20Distance/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi <code>s</code> và <code>t</code>, hãy trả về <code>true</code> nếu chúng cách nhau đúng một phép chỉnh sửa, ngược lại trả về <code>false</code>.</p>

<p>Một chuỗi <code>s</code> được gọi là cách chuỗi <code>t</code> đúng một khoảng cách nếu bạn có thể:</p>

<ul>
	<li>Chèn <strong>chính xác một</strong> ký tự vào <code>s</code> để nhận được <code>t</code>.</li>
	<li>Xóa <strong>chính xác một</strong> ký tự khỏi <code>s</code> để nhận được <code>t</code>.</li>
	<li>Thay thế <strong>chính xác một</strong> ký tự của <code>s</code> bằng <strong>một ký tự khác</strong> để nhận được <code>t</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;ab&quot;, t = &quot;acb&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Chúng ta có thể chèn &#39;c&#39; vào s&nbsp;để nhận được&nbsp;t.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;&quot;, t = &quot;&quot;
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Chúng ta không thể nhận được t từ s chỉ bằng một bước.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= s.length, t.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> và <code>t</code> chỉ gồm các chữ cái viết thường, chữ cái viết hoa và chữ số.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thảo luận các trường hợp khác nhau

<!-- thinking:start -->

> **Tư duy**
>
> Chính xác một thao tác chèn, xóa hoặc thay thế phải biến $s$ thành $t$. Khoảng cách độ dài lớn hơn $1$ là không thể. $n\le 10^4$. Giả sử $s$ có độ dài ít nhất bằng $t$. Tại vị trí khác nhau đầu tiên: nếu độ dài bằng nhau, so sánh các hậu tố (thay thế); nếu độ dài khác nhau, so sánh $s$ sau khi bỏ ký tự đó với phần còn lại của $t$ (xóa). Nếu chúng khớp trong suốt quá trình, $s$ phải dài hơn đúng một ký tự.

<!-- thinking:end -->

Gọi $m$ là độ dài của chuỗi $s$ và $n$ là độ dài của chuỗi $t$. Chúng ta có thể giả sử rằng $m$ luôn lớn hơn hoặc bằng $n$.

Nếu $m-n > 1$, trả về false ngay;

Nếu không, duyệt qua $s$ và $t$; nếu $s[i]$ không bằng $t[i]$:

- Nếu $m \neq n$, so sánh $s[i+1:]$ với $t[i:]$, trả về true nếu chúng bằng nhau, ngược lại trả về false;
- Nếu $m = n$, so sánh $s[i:]$ với $t[i:]$, trả về true nếu chúng bằng nhau, ngược lại trả về false.

Nếu vòng lặp kết thúc, điều đó có nghĩa là tất cả các ký tự của $s$ và $t$ đã được duyệt đều bằng nhau; lúc này cần thỏa mãn $m=n+1$.

Độ phức tạp thời gian là $O(m)$, trong đó $m$ là độ dài của chuỗi $s$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isOneEditDistance(self, s: str, t: str) -> bool:
        if len(s) < len(t):
            return self.isOneEditDistance(t, s)
        m, n = len(s), len(t)
        if m - n > 1:
            return False
        for i, c in enumerate(t):
            if c != s[i]:
                return s[i + 1 :] == t[i + 1 :] if m == n else s[i + 1 :] == t[i:]
        return m == n + 1
```

#### Java

```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return isOneEditDistance(t, s);
        }
        if (m - n > 1) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                return s.substring(i + 1).equals(t.substring(i));
            }
        }
        return m == n + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isOneEditDistance(string s, string t) {
        int m = s.size(), n = t.size();
        if (m < n) return isOneEditDistance(t, s);
        if (m - n > 1) return false;
        for (int i = 0; i < n; ++i) {
            if (s[i] != t[i]) {
                if (m == n) return s.substr(i + 1) == t.substr(i + 1);
                return s.substr(i + 1) == t.substr(i);
            }
        }
        return m == n + 1;
    }
};
```

#### Go

```go
func isOneEditDistance(s string, t string) bool {
	m, n := len(s), len(t)
	if m < n {
		return isOneEditDistance(t, s)
	}
	if m-n > 1 {
		return false
	}
	for i := range t {
		if s[i] != t[i] {
			if m == n {
				return s[i+1:] == t[i+1:]
			}
			return s[i+1:] == t[i:]
		}
	}
	return m == n+1
}
```

#### TypeScript

```ts
function isOneEditDistance(s: string, t: string): boolean {
    const [m, n] = [s.length, t.length];
    if (m < n) {
        return isOneEditDistance(t, s);
    }
    if (m - n > 1) {
        return false;
    }
    for (let i = 0; i < n; ++i) {
        if (s[i] !== t[i]) {
            return s.slice(i + 1) === t.slice(i + (m === n ? 1 : 0));
        }
    }
    return m === n + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
