---
comments: true
difficulty: Easy
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings)

[中文文档](/solution/0200-0299/0205.Isomorphic%20Strings/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi <code>s</code> và <code>t</code>, <em>hãy xác định xem chúng có đẳng cấu hay không</em>.</p>

<p>Hai chuỗi <code>s</code> và <code>t</code> là đẳng cấu nếu có thể thay thế các ký tự trong <code>s</code> để nhận được <code>t</code>.</p>

<p>Mọi lần xuất hiện của một ký tự phải được thay thế bằng một ký tự khác, đồng thời giữ nguyên thứ tự các ký tự. Không có hai ký tự nào được ánh xạ tới cùng một ký tự, nhưng một ký tự có thể được ánh xạ tới chính nó.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;egg&quot;, t = &quot;add&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>

<p><strong>Giải thích:</strong></p>

<p>Có thể làm cho hai chuỗi <code>s</code> và <code>t</code> giống hệt nhau bằng cách:</p>

<ul>
	<li>Ánh xạ <code>&#39;e&#39;</code> tới <code>&#39;a&#39;</code>.</li>
	<li>Ánh xạ <code>&#39;g&#39;</code> tới <code>&#39;d&#39;</code>.</li>
</ul>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;f11&quot;, t = &quot;b23&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">false</span></p>

<p><strong>Giải thích:</strong></p>

<p>Không thể làm cho hai chuỗi <code>s</code> và <code>t</code> giống hệt nhau, vì <code>&#39;1&#39;</code> cần được ánh xạ tới cả <code>&#39;2&#39;</code> và <code>&#39;3&#39;</code>.</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;paper&quot;, t = &quot;title&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>s</code> và <code>t</code> chỉ gồm các ký tự ascii hợp lệ.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm hoặc mảng

<!-- thinking:start -->

> **Tư duy**
>
> Tính đẳng cấu cần một song ánh giữa các ký tự của $s$ và $t$; ánh xạ một chiều sẽ bỏ sót các xung đột nhiều-một. Hai chuỗi có cùng độ dài và bảng chữ cái hữu hạn, nên chỉ cần duyệt một lần.
>
> Chúng ta duy trì $d_1$ và $d_2$ cho các ánh xạ $s\to t$ và $t\to s$. Nếu xung đột với ánh xạ đã tồn tại thì thất bại; ngược lại, cập nhật và tiếp tục.

<!-- thinking:end -->

Chúng ta có thể sử dụng hai bảng băm hoặc mảng $d_1$ và $d_2$ để ghi lại quan hệ ánh xạ ký tự giữa $s$ và $t$.

Duyệt qua $s$ và $t$; nếu quan hệ ánh xạ của các ký tự tương ứng trong $d_1$ và $d_2$ khác nhau thì trả về `false`, nếu không thì cập nhật các quan hệ ánh xạ tương ứng trong $d_1$ và $d_2$. Sau khi duyệt xong, điều đó có nghĩa là $s$ và $t$ đẳng cấu, và trả về `true`.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(C)$. Trong đó $n$ là độ dài của chuỗi $s$; còn $C$ là kích thước của tập ký tự, với $C = 256$ trong bài toán này.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        d1 = {}
        d2 = {}
        for a, b in zip(s, t):
            if (a in d1 and d1[a] != b) or (b in d2 and d2[b] != a):
                return False
            d1[a] = b
            d2[b] = a
        return True
```

#### Java

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> d1 = new HashMap<>();
        Map<Character, Character> d2 = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1.containsKey(a) && d1.get(a) != b) {
                return false;
            }
            if (d2.containsKey(b) && d2.get(b) != a) {
                return false;
            }
            d1.put(a, b);
            d2.put(b, a);
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        int d1[256]{};
        int d2[256]{};
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            char a = s[i], b = t[i];
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = d2[b] = i + 1;
        }
        return true;
    }
};
```

#### Go

```go
func isIsomorphic(s string, t string) bool {
	d1 := [256]int{}
	d2 := [256]int{}
	for i := range s {
		if d1[s[i]] != d2[t[i]] {
			return false
		}
		d1[s[i]] = i + 1
		d2[t[i]] = i + 1
	}
	return true
}
```

#### TypeScript

```ts
function isIsomorphic(s: string, t: string): boolean {
    const d1: number[] = new Array(256).fill(0);
    const d2: number[] = new Array(256).fill(0);
    for (let i = 0; i < s.length; ++i) {
        const a = s.charCodeAt(i);
        const b = t.charCodeAt(i);
        if (d1[a] !== d2[b]) {
            return false;
        }
        d1[a] = i + 1;
        d2[b] = i + 1;
    }
    return true;
}
```

#### Rust

```rust
use std::collections::HashMap;
impl Solution {
    fn help(s: &[u8], t: &[u8]) -> bool {
        let mut map = HashMap::new();
        for i in 0..s.len() {
            if map.contains_key(&s[i]) {
                if map.get(&s[i]).unwrap() != &t[i] {
                    return false;
                }
            } else {
                map.insert(s[i], t[i]);
            }
        }
        true
    }

    pub fn is_isomorphic(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        Self::help(s, t) && Self::help(t, s)
    }
}
```

#### C#

```cs
public class Solution {
    public bool IsIsomorphic(string s, string t) {
        int[] d1 = new int[256];
        int[] d2 = new int[256];
        for (int i = 0; i < s.Length; ++i) {
            var a = s[i];
            var b = t[i];
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = i + 1;
            d2[b] = i + 1;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Hai bảng băm là đúng nhưng phải chịu overhead của thao tác băm. Bảng chữ cái có kích thước $256$, nên các mảng là đủ dùng.
>
> Lưu chỉ số cuối cùng của mỗi ký tự dưới dạng một dấu thời gian dùng chung: nếu các chỉ số được ghi lại khác nhau thì ánh xạ không nhất quán.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        d1, d2 = [0] * 256, [0] * 256
        for i, (a, b) in enumerate(zip(s, t), 1):
            a, b = ord(a), ord(b)
            if d1[a] != d2[b]:
                return False
            d1[a] = d2[b] = i
        return True
```

#### Java

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] d1 = new int[256];
        int[] d2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = i + 1;
            d2[b] = i + 1;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
