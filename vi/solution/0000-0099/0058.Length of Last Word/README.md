---
comments: true
difficulty: Easy
tags:
    - String
---

<!-- problem:start -->

# [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word)

[中文文档](/solution/0000-0099/0058.Length%20of%20Last%20Word/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code> gồm các từ và khoảng trắng, hãy trả về <em>độ dài của từ <strong>cuối cùng</strong> trong chuỗi.</em></p>

<p>Một <strong>từ</strong> là một <span data-keyword="substring-nonempty">chuỗi con</span> cực đại chỉ gồm các ký tự không phải khoảng trắng.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;Hello World&quot;
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> Từ cuối cùng là &quot;World&quot; với độ dài 5.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;   fly me   to   the moon  &quot;
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Từ cuối cùng là &quot;moon&quot; với độ dài 4.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;luffy is still joyboy&quot;
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> Từ cuối cùng là &quot;joyboy&quot; với độ dài 6.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> chỉ gồm các chữ cái tiếng Anh và khoảng trắng <code>&#39; &#39;</code>.</li>
	<li>Sẽ có ít nhất một từ trong <code>s</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt ngược + Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là tách theo khoảng trắng và lấy token cuối cùng. Với $n \le 10^4$, cách này vẫn chạy qua, nhưng chúng ta duyệt toàn bộ chuỗi và cấp phát các chuỗi con.
>
> Phần lãng phí là mọi từ đứng trước. Từ cuối cùng nằm ở cuối chuỗi (có thể nằm sau các khoảng trắng ở cuối).
>
> Vì vậy, duyệt từ phải sang trái: bỏ qua các khoảng trắng ở cuối để lấy $i$, sau đó đi đến mép trái của từ để lấy $j$; độ dài là $i-j$.

<!-- thinking:end -->

Chúng ta bắt đầu duyệt từ cuối chuỗi $s$, tìm ký tự đầu tiên không phải khoảng trắng, đó là ký tự cuối cùng của từ cuối cùng, và đánh dấu chỉ số là $i$. Sau đó tiếp tục duyệt về phía trước, tìm ký tự đầu tiên là khoảng trắng, đó là ký tự ngay trước ký tự đầu tiên của từ cuối cùng, và đánh dấu chỉ số là $j$. Khi đó, độ dài của từ cuối cùng là $i - j$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của chuỗi $s$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        i = len(s) - 1
        while i >= 0 and s[i] == ' ':
            i -= 1
        j = i
        while j >= 0 and s[j] != ' ':
            j -= 1
        return i - j
```

#### Java

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s.charAt(j) != ' ') {
            --j;
        }
        return i - j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthOfLastWord(string s) {
        int i = s.size() - 1;
        while (~i && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (~j && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
};
```

#### Go

```go
func lengthOfLastWord(s string) int {
	i := len(s) - 1
	for i >= 0 && s[i] == ' ' {
		i--
	}
	j := i
	for j >= 0 && s[j] != ' ' {
		j--
	}
	return i - j
}
```

#### TypeScript

```ts
function lengthOfLastWord(s: string): number {
    let i = s.length - 1;
    while (i >= 0 && s[i] === ' ') {
        --i;
    }
    let j = i;
    while (j >= 0 && s[j] !== ' ') {
        --j;
    }
    return i - j;
}
```

#### Rust

```rust
impl Solution {
    pub fn length_of_last_word(s: String) -> i32 {
        let s = s.trim_end();
        let n = s.len();
        for (i, c) in s.char_indices().rev() {
            if c == ' ' {
                return (n - i - 1) as i32;
            }
        }
        n as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLastWord = function (s) {
    let i = s.length - 1;
    while (i >= 0 && s[i] === ' ') {
        --i;
    }
    let j = i;
    while (j >= 0 && s[j] !== ' ') {
        --j;
    }
    return i - j;
};
```

#### C#

```cs
public class Solution {
    public int LengthOfLastWord(string s) {
        int i = s.Length - 1;
        while (i >= 0 && s[i] == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s[j] != ' ') {
            --j;
        }
        return i - j;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLastWord($s) {
        $count = 0;
        while ($s[strlen($s) - 1] == ' ') {
            $s = substr($s, 0, -1);
        }
        while (strlen($s) != 0 && $s[strlen($s) - 1] != ' ') {
            $count++;
            $s = substr($s, 0, -1);
        }
        return $count;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
