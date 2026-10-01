---
comments: true
difficulty: Easy
tags:
    - Two Pointers
    - String
    - String Matching
    - KMP
    - Boyer–Moore
    - Extended KMP
---

<!-- problem:start -->

# [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string)

[中文文档](/solution/0000-0099/0028.Find%20the%20Index%20of%20the%20First%20Occurrence%20in%20a%20String/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi <code>needle</code> và <code>haystack</code>, trả về chỉ số xuất hiện đầu tiên của <code>needle</code> trong <code>haystack</code>, hoặc <code>-1</code> nếu <code>needle</code> không phải là một phần của <code>haystack</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> haystack = &quot;sadbutsad&quot;, needle = &quot;sad&quot;
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> &quot;sad&quot; xuất hiện tại các chỉ số 0 và 6.
Lần xuất hiện đầu tiên ở chỉ số 0, nên chúng ta trả về 0.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> haystack = &quot;leetcode&quot;, needle = &quot;leeto&quot;
<strong>Đầu ra:</strong> -1
<strong>Giải thích:</strong> &quot;leeto&quot; không xuất hiện trong &quot;leetcode&quot;, nên chúng ta trả về -1.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= haystack.length, needle.length &lt;= 10<sup>4</sup></code></li>
	<li><code>haystack</code> và <code>needle</code> chỉ gồm các ký tự tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thử mọi vị trí bắt đầu $i$ trong $haystack$ và kiểm tra xem lát cắt độ dài $m$ có bằng $needle$ hay không. Với $n,m \le 10^4$, trường hợp xấu nhất $O((n-m)m)$ vào khoảng $10^8$ và thường vẫn vượt qua.
>
> Nút thắt là phải so sánh $m$ ký tự ở nhiều vị trí gần khớp. Các bộ so khớp thông minh hơn phân bổ chi phí để tiến gần thời gian tuyến tính, nhưng kích thước này chưa bắt buộc phải dùng chúng.
>
> Chúng ta chỉ cần lần khớp đầu tiên; nếu không khớp thì chỉ cần chuyển sang $i$ tiếp theo.
>
> Vì vậy, duyệt $i$ từ $0$ đến $n-m$, trả về $i$ khi bằng nhau, nếu không trả về $-1$. Không gian bổ sung là $O(1)$.

<!-- thinking:end -->

Chúng ta so sánh chuỗi `needle` với từng ký tự của chuỗi `haystack` tại vị trí bắt đầu. Nếu tìm thấy một chỉ số khớp, chúng ta trả về chỉ số đó ngay.

Giả sử độ dài của chuỗi `haystack` là $n$ và độ dài của chuỗi `needle` là $m$, độ phức tạp thời gian là $O((n-m) \times m)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        for i in range(n - m + 1):
            if haystack[i : i + m] == needle:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        int len1 = haystack.length();
        int len2 = needle.length();
        int p = 0;
        int q = 0;
        while (p < len1) {
            if (haystack.charAt(p) == needle.charAt(q)) {
                if (len2 == 1) {
                    return p;
                }
                ++p;
                ++q;
            } else {
                p -= q - 1;
                q = 0;
            }

            if (q == len2) {
                return p - q;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        for (int i = 0; i + m <= n; ++i) {
            if (haystack.substr(i, m) == needle) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func strStr(haystack string, needle string) int {
	n, m := len(haystack), len(needle)
	for i := 0; i <= n-m; i++ {
		if haystack[i:i+m] == needle {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function strStr(haystack: string, needle: string): number {
    const m = haystack.length;
    const n = needle.length;
    for (let i = 0; i <= m - n; i++) {
        let isEqual = true;
        for (let j = 0; j < n; j++) {
            if (haystack[i + j] !== needle[j]) {
                isEqual = false;
                break;
            }
        }
        if (isEqual) {
            return i;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let n = haystack.len();
        let m = needle.len();
        if m > n {
            return -1;
        }
        for i in 0..=n - m {
            if &haystack[i..i + m] == needle {
                return i as i32;
            }
        }
        -1
    }
}
```

#### JavaScript

```js
/**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function (haystack, needle) {
    const slen = haystack.length;
    const plen = needle.length;
    if (slen == plen) {
        return haystack == needle ? 0 : -1;
    }
    for (let i = 0; i <= slen - plen; i++) {
        let j;
        for (j = 0; j < plen; j++) {
            if (haystack[i + j] != needle[j]) {
                break;
            }
        }
        if (j == plen) return i;
    }
    return -1;
};
```

#### C#

```cs
public class Solution {
    public int StrStr(string haystack, string needle) {
        for (var i = 0; i < haystack.Length - needle.Length + 1; ++i) {
            var j = 0;
            for (; j < needle.Length; ++j) {
                if (haystack[i + j] != needle[j]) break;
            }
            if (j == needle.Length) return i;
        }
        return -1;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $haystack
     * @param String $needle
     * @return Integer
     */
    function strStr($haystack, $needle) {
        $strNew = str_replace($needle, '+', $haystack);
        $cnt = substr_count($strNew, '+');
        if ($cnt > 0) {
            for ($i = 0; $i < strlen($strNew); $i++) {
                if ($strNew[$i] == '+') {
                    return $i;
                }
            }
        } else {
            return -1;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Thuật toán so khớp chuỗi Rabin-Karp

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 có thể so sánh toàn bộ $m$ ký tự tại mỗi vị trí bắt đầu, tiến gần $O(nm)$. Chúng ta muốn cửa sổ tiếp theo tái sử dụng kết quả từ cửa sổ trước.
>
> Có thể băm một chuỗi con có độ dài cố định bằng rolling hash: thêm ký tự đi vào, loại bỏ ký tự đi ra và cập nhật trong $O(1)$. Khi hash của cửa sổ bằng hash của $needle$, hãy so sánh các chuỗi gốc để loại trừ va chạm.
>
> Một lần duyệt $haystack$ khi đó tốn $O(n+m)$ kỳ vọng.

<!-- thinking:end -->

Thuật toán [Rabin-Karp](https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm) về cơ bản sử dụng cửa sổ trượt kết hợp với hàm băm để so sánh hash của các chuỗi có độ dài cố định, nhờ đó có thể giảm độ phức tạp thời gian của việc so sánh xem hai chuỗi có giống nhau hay không xuống còn $O(1)$.

Giả sử độ dài của chuỗi `haystack` là $n$ và độ dài của chuỗi `needle` là $m$, độ phức tạp thời gian là $O(n+m)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        mod = (1 << 31) - 1
        target = sha = 0
        multi = 1
        for i in range(m):
            target = (target * 256 + ord(needle[i])) % mod
        for _ in range(1, m):
            multi = multi * 256 % mod
        left = 0
        for right in range(n):
            sha = (sha * 256 + ord(haystack[right])) % mod
            if right - left + 1 < m:
                continue
            if sha == target and haystack[left : right + 1] == needle:
                return left
            sha = (sha - ord(haystack[left]) * multi % mod + mod) % mod
            left += 1
        return -1
```

#### Java

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        final int mod = (1 << 31) - 1;
        long target = 0, sha = 0, multi = 1;
        for (int i = 0; i < m; ++i) {
            target = (target * 256 + needle.charAt(i)) % mod;
        }
        for (int i = 1; i < m; ++i) {
            multi = multi * 256 % mod;
        }
        int left = 0;
        for (int right = 0; right < n; ++right) {
            sha = (sha * 256 + haystack.charAt(right)) % mod;
            if (right - left + 1 < m) {
                continue;
            }
            if (sha == target && haystack.substring(left, right + 1).equals(needle)) {
                return left;
            }
            sha = (sha - haystack.charAt(left) * multi % mod + mod) % mod;
            ++left;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        const int mod = (1 << 31) - 1;
        long long target = 0, sha = 0, multi = 1;
        for (int i = 0; i < m; ++i) {
            target = (target * 256 + needle[i]) % mod;
        }
        for (int i = 1; i < m; ++i) {
            multi = multi * 256 % mod;
        }
        int left = 0;
        for (int right = 0; right < n; ++right) {
            sha = (sha * 256 + haystack[right]) % mod;
            if (right - left + 1 < m) {
                continue;
            }
            if (sha == target && haystack.substr(left, m) == needle) {
                return left;
            }
            sha = (sha - haystack[left] * multi % mod + mod) % mod;
            ++left;
        }
        return -1;
    }
};
```

#### Go

```go
func strStr(haystack string, needle string) int {
	n, m := len(haystack), len(needle)
	sha, target, left, right, mod := 0, 0, 0, 0, 1<<31-1
	multi := 1
	for i := 0; i < m; i++ {
		target = (target*256%mod + int(needle[i])) % mod
	}
	for i := 1; i < m; i++ {
		multi = multi * 256 % mod
	}

	for ; right < n; right++ {
		sha = (sha*256%mod + int(haystack[right])) % mod
		if right-left+1 < m {
			continue
		}
		// 此时 left~right 的长度已经为 needle 的长度 m 了，只需要比对 sha 值与 target 是否一致即可
		// 为避免 hash 冲突，还需要确保 haystack[left:right+1] 与 needle 相同
		if sha == target && haystack[left:right+1] == needle {
			return left
		}
		// 未匹配成功，left 右移一位
		sha = (sha - (int(haystack[left])*multi)%mod + mod) % mod
		left++
	}
	return -1
}
```

#### TypeScript

```ts
function strStr(haystack: string, needle: string): number {
    const n = haystack.length;
    const m = needle.length;
    const mod = 2 ** 31 - 1;
    let target = 0;
    let sha = 0;
    let multi = 1;
    for (let i = 0; i < m; ++i) {
        target = (target * 256 + needle.charCodeAt(i)) % mod;
    }
    for (let i = 1; i < m; ++i) {
        multi = (multi * 256) % mod;
    }
    let left = 0;
    for (let right = 0; right < n; ++right) {
        sha = (sha * 256 + haystack.charCodeAt(right)) % mod;
        if (right - left + 1 < m) {
            continue;
        }
        if (sha === target && haystack.slice(left, right + 1) === needle) {
            return left;
        }
        sha = (sha - ((haystack.charCodeAt(left) * multi) % mod) + mod) % mod;
        ++left;
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: KMP

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 2 khiến việc so sánh cửa sổ có độ phức tạp kỳ vọng $O(1)$, nhưng vẫn băm theo modulo một số nguyên tố và phải xác minh các chuỗi gốc khi xảy ra va chạm. Chúng ta muốn một lần quét tuyến tính trong trường hợp xấu nhất mà không băm.
>
> Sau một lần không khớp, không cần đưa con trỏ trong $\textit{haystack}$ quay lại đầu cửa sổ. Hàm tiền tố của $\textit{needle}$ lưu biên dài nhất của tiền tố đã khớp, nhờ đó chúng ta biết cần tiếp tục từ đâu trong mẫu.
>
> Xây dựng $\textit{next}$ cho $\textit{needle}$, sau đó quét $\textit{haystack}$ một lần và chỉ lùi theo $\textit{next}$. Thời gian là $O(n+m)$ và không gian bổ sung là $O(m)$.

<!-- thinking:end -->

Tính hàm tiền tố $\textit{next}$ của $\textit{needle}$, trong đó $\textit{next}[i]$ là biên thực sự dài nhất của $\textit{needle}[0..i]$. Quét $\textit{haystack}$: các ký tự bằng nhau làm tăng độ dài khớp, còn khi không khớp thì nhảy độ dài đó về $\textit{next}[j-1]$. Khi độ dài khớp đạt $m$, trả về chỉ số bắt đầu $i-m+1$.

Độ phức tạp thời gian là $O(n+m)$ và độ phức tạp không gian là $O(m)$, trong đó $n$ và $m$ lần lượt là độ dài của $\textit{haystack}$ và $\textit{needle}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        n, m = len(haystack), len(needle)
        nxt = [0] * m
        j = 0
        for i in range(1, m):
            while j and needle[i] != needle[j]:
                j = nxt[j - 1]
            if needle[i] == needle[j]:
                j += 1
            nxt[i] = j
        j = 0
        for i, ch in enumerate(haystack):
            while j and ch != needle[j]:
                j = nxt[j - 1]
            if ch == needle[j]:
                j += 1
            if j == m:
                return i - m + 1
        return -1
```

#### Java

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        int[] nxt = new int[m];
        for (int i = 1, j = 0; i < m; ++i) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = nxt[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                ++j;
            }
            nxt[i] = j;
        }
        for (int i = 0, j = 0; i < n; ++i) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = nxt[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                ++j;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size(), m = needle.size();
        vector<int> nxt(m);
        for (int i = 1, j = 0; i < m; ++i) {
            while (j > 0 && needle[i] != needle[j]) {
                j = nxt[j - 1];
            }
            if (needle[i] == needle[j]) {
                ++j;
            }
            nxt[i] = j;
        }
        for (int i = 0, j = 0; i < n; ++i) {
            while (j > 0 && haystack[i] != needle[j]) {
                j = nxt[j - 1];
            }
            if (haystack[i] == needle[j]) {
                ++j;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func strStr(haystack string, needle string) int {
	n, m := len(haystack), len(needle)
	nxt := make([]int, m)
	for i, j := 1, 0; i < m; i++ {
		for j > 0 && needle[i] != needle[j] {
			j = nxt[j-1]
		}
		if needle[i] == needle[j] {
			j++
		}
		nxt[i] = j
	}
	for i, j := 0, 0; i < n; i++ {
		for j > 0 && haystack[i] != needle[j] {
			j = nxt[j-1]
		}
		if haystack[i] == needle[j] {
			j++
		}
		if j == m {
			return i - m + 1
		}
	}
	return -1
}
```

#### TypeScript

```ts
function strStr(haystack: string, needle: string): number {
    const n = haystack.length;
    const m = needle.length;
    const nxt = Array(m).fill(0);
    for (let i = 1, j = 0; i < m; ++i) {
        while (j > 0 && needle[i] !== needle[j]) {
            j = nxt[j - 1];
        }
        if (needle[i] === needle[j]) {
            ++j;
        }
        nxt[i] = j;
    }
    for (let i = 0, j = 0; i < n; ++i) {
        while (j > 0 && haystack[i] !== needle[j]) {
            j = nxt[j - 1];
        }
        if (haystack[i] === needle[j]) {
            ++j;
        }
        if (j === m) {
            return i - m + 1;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        let haystack = haystack.as_bytes();
        let needle = needle.as_bytes();
        let n = haystack.len();
        let m = needle.len();
        let mut nxt = vec![0; m];
        let mut j = 0;
        for i in 1..m {
            while j > 0 && needle[i] != needle[j] {
                j = nxt[j - 1];
            }
            if needle[i] == needle[j] {
                j += 1;
            }
            nxt[i] = j;
        }
        j = 0;
        for i in 0..n {
            while j > 0 && haystack[i] != needle[j] {
                j = nxt[j - 1];
            }
            if haystack[i] == needle[j] {
                j += 1;
            }
            if j == m {
                return (i - m + 1) as i32;
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
