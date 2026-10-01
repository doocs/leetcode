---
comments: true
difficulty: Easy
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome)

[中文文档](/solution/0100-0199/0125.Valid%20Palindrome/README.md)

## Mô tả

<!-- description:start -->

<p>Một cụm từ là một <strong>palindrome</strong> nếu, sau khi chuyển tất cả chữ cái viết hoa thành chữ thường và loại bỏ mọi ký tự không phải chữ cái hoặc chữ số, nó đọc xuôi và ngược như nhau. Các ký tự chữ và số bao gồm chữ cái và chữ số.</p>

<p>Cho một chuỗi <code>s</code>, hãy trả về <code>true</code><em> nếu đó là một <strong>palindrome</strong>, hoặc </em><code>false</code><em> nếu ngược lại</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;A man, a plan, a canal: Panama&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> &quot;amanaplanacanalpanama&quot; là một palindrome.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;race a car&quot;
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> &quot;raceacar&quot; không phải là một palindrome.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot; &quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> s là một chuỗi rỗng &quot;&quot; sau khi loại bỏ các ký tự không phải chữ cái hoặc chữ số.
Vì một chuỗi rỗng đọc xuôi và ngược như nhau, nên đó là một palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> chỉ bao gồm các ký tự ASCII có thể in được.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Kiểm tra xem chuỗi có phải là một palindrome sau khi bỏ qua kiểu chữ và các ký tự không phải chữ cái hoặc chữ số hay không. Lọc thành một chuỗi mới rồi đảo ngược vẫn hoạt động với $n \le 2\times 10^5$, nhưng sử dụng thêm $O(n)$ không gian.
>
> Một palindrome chỉ cần các cặp ký tự khớp nhau. Hai con trỏ di chuyển vào trong: bỏ qua các ký tự không hợp lệ, so sánh các ký tự hợp lệ không phân biệt hoa thường. Một lượt duyệt, không gian hằng số.

<!-- thinking:end -->

Chúng ta sử dụng hai con trỏ $i$ và $j$ để trỏ đến hai đầu của chuỗi $s$, sau đó lặp qua quy trình sau cho đến khi $i \geq j$:

1. Nếu $s[i]$ không phải là chữ cái hoặc chữ số, di chuyển con trỏ $i$ một bước sang phải và tiếp tục vòng lặp kế tiếp.
2. Nếu $s[j]$ không phải là chữ cái hoặc chữ số, di chuyển con trỏ $j$ một bước sang trái và tiếp tục vòng lặp kế tiếp.
3. Nếu dạng chữ thường của $s[i]$ và $s[j]$ không bằng nhau, trả về `false`.
4. Nếu không, di chuyển con trỏ $i$ một bước sang phải và con trỏ $j$ một bước sang trái, rồi tiếp tục vòng lặp kế tiếp.

Ở cuối vòng lặp, trả về `true`.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của chuỗi $s$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j:
            if not s[i].isalnum():
                i += 1
            elif not s[j].isalnum():
                j -= 1
            elif s[i].lower() != s[j].lower():
                return False
            else:
                i, j = i + 1, j - 1
        return True
```

#### Java

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            } else if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            } else {
                ++i;
                --j;
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
    bool isPalindrome(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            if (!isalnum(s[i])) {
                ++i;
            } else if (!isalnum(s[j])) {
                --j;
            } else if (tolower(s[i]) != tolower(s[j])) {
                return false;
            } else {
                ++i;
                --j;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		if !isalnum(s[i]) {
			i++
		} else if !isalnum(s[j]) {
			j--
		} else if tolower(s[i]) != tolower(s[j]) {
			return false
		} else {
			i, j = i+1, j-1
		}
	}
	return true
}

func isalnum(ch byte) bool {
	return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
}

func tolower(ch byte) byte {
	if ch >= 'A' && ch <= 'Z' {
		return ch + 32
	}
	return ch
}
```

#### TypeScript

```ts
function isPalindrome(s: string): boolean {
    let i = 0;
    let j = s.length - 1;
    while (i < j) {
        if (!/[a-zA-Z0-9]/.test(s[i])) {
            ++i;
        } else if (!/[a-zA-Z0-9]/.test(s[j])) {
            --j;
        } else if (s[i].toLowerCase() !== s[j].toLowerCase()) {
            return false;
        } else {
            ++i;
            --j;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        let s = s.to_lowercase();
        let s = s.as_bytes();
        let n = s.len();
        let (mut l, mut r) = (0, n - 1);
        while l < r {
            while l < r && !s[l].is_ascii_alphanumeric() {
                l += 1;
            }
            while l < r && !s[r].is_ascii_alphanumeric() {
                r -= 1;
            }
            if s[l] != s[r] {
                return false;
            }
            l += 1;
            if r != 0 {
                r -= 1;
            }
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isPalindrome = function (s) {
    let i = 0;
    let j = s.length - 1;
    while (i < j) {
        if (!/[a-zA-Z0-9]/.test(s[i])) {
            ++i;
        } else if (!/[a-zA-Z0-9]/.test(s[j])) {
            --j;
        } else if (s[i].toLowerCase() !== s[j].toLowerCase()) {
            return false;
        } else {
            ++i;
            --j;
        }
    }
    return true;
};
```

#### C#

```cs
public class Solution {
    public bool IsPalindrome(string s) {
        int i = 0, j = s.Length - 1;
        while (i < j) {
            if (!char.IsLetterOrDigit(s[i])) {
                ++i;
            } else if (!char.IsLetterOrDigit(s[j])) {
                --j;
            } else if (char.ToLower(s[i++]) != char.ToLower(s[j--])) {
                return false;
            }
        }
        return true;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function isPalindrome($s) {
        $regex = '/[a-z0-9]/';
        $s = strtolower($s);
        preg_match_all($regex, $s, $matches);
        if ($matches[0] == null) {
            return true;
        }
        $len = floor(count($matches[0]) / 2);
        for ($i = 0; $i < $len; $i++) {
            if ($matches[0][$i] != $matches[0][count($matches[0]) - 1 - $i]) {
                return false;
            }
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
