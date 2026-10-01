---
comments: true
difficulty: Medium
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string)

[中文文档](/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README.md)

## Mô tả

<!-- description:start -->

<p>Với chuỗi đầu vào <code>s</code>, hãy đảo ngược thứ tự của các <strong>từ</strong>.</p>

<p><strong>Từ</strong> được định nghĩa là một dãy các ký tự không phải khoảng trắng. Các <strong>từ</strong> trong <code>s</code> được phân tách bằng ít nhất một khoảng trắng.</p>

<p>Trả về <em>một chuỗi gồm các từ theo thứ tự ngược lại, được nối bằng một khoảng trắng duy nhất.</em></p>

<p><b>Lưu ý</b> rằng <code>s</code> có thể chứa khoảng trắng ở đầu hoặc cuối, hoặc nhiều khoảng trắng giữa hai từ. Chuỗi trả về chỉ được có một khoảng trắng phân tách các từ. Không được chứa khoảng trắng thừa.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;the sky is blue&quot;
<strong>Đầu ra:</strong> &quot;blue is sky the&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;  hello world  &quot;
<strong>Đầu ra:</strong> &quot;world hello&quot;
<strong>Giải thích:</strong> Chuỗi được đảo ngược của bạn không được chứa khoảng trắng ở đầu hoặc cuối.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;a good   example&quot;
<strong>Đầu ra:</strong> &quot;example good a&quot;
<strong>Giải thích:</strong> Bạn cần giảm nhiều khoảng trắng giữa hai từ xuống còn một khoảng trắng trong chuỗi được đảo ngược.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> chứa các chữ cái tiếng Anh (chữ hoa và chữ thường), chữ số và khoảng trắng <code>&#39; &#39;</code>.</li>
	<li>Có <strong>ít nhất một</strong> từ trong <code>s</code>.</li>
</ul>

<p>&nbsp;</p>
<p><b data-stringify-type="bold">Câu hỏi mở rộng:&nbsp;</b>Nếu kiểu dữ liệu chuỗi có thể thay đổi trong ngôn ngữ của bạn, bạn có thể giải bài toán <b data-stringify-type="bold">ngay trên chuỗi</b>, chỉ sử dụng thêm <code data-stringify-type="code">O(1)</code> không gian không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Đảo ngược thứ tự từ và gộp các khoảng trắng thừa. $n\le 10^4$. Hai con trỏ bỏ qua khoảng trắng, tách từng từ, sau đó đảo ngược danh sách và nối lại. Khoảng trắng ở đầu, cuối và lặp lại sẽ biến mất.

<!-- thinking:end -->

Chúng ta có thể sử dụng hai con trỏ $i$ và $j$ để tìm từng từ, thêm từ đó vào danh sách kết quả, sau đó đảo ngược danh sách kết quả và cuối cùng nối nó thành một chuỗi.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        words = []
        i, n = 0, len(s)
        while i < n:
            while i < n and s[i] == " ":
                i += 1
            if i < n:
                j = i
                while j < n and s[j] != " ":
                    j += 1
                words.append(s[i:j])
                i = j
        return " ".join(words[::-1])
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n;) {
            while (i < n && s.charAt(i) == ' ') {
                ++i;
            }
            if (i < n) {
                StringBuilder t = new StringBuilder();
                int j = i;
                while (j < n && s.charAt(j) != ' ') {
                    t.append(s.charAt(j++));
                }
                words.add(t.toString());
                i = j;
            }
        }
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseWords(string s) {
        int i = 0;
        int j = 0;
        int n = s.size();
        while (i < n) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                if (j != 0) {
                    s[j++] = ' ';
                }
                int k = i;
                while (k < n && s[k] != ' ') {
                    s[j++] = s[k++];
                }
                reverse(s.begin() + j - (k - i), s.begin() + j);
                i = k;
            }
        }
        s.erase(s.begin() + j, s.end());
        reverse(s.begin(), s.end());
        return s;
    }
};
```

#### Go

```go
func reverseWords(s string) string {
	words := []string{}
	i, n := 0, len(s)
	for i < n {
		for i < n && s[i] == ' ' {
			i++
		}
		if i < n {
			j := i
			t := []byte{}
			for j < n && s[j] != ' ' {
				t = append(t, s[j])
				j++
			}
			words = append(words, string(t))
			i = j
		}
	}
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    const words: string[] = [];
    const n = s.length;
    let i = 0;
    while (i < n) {
        while (i < n && s[i] === ' ') {
            i++;
        }
        if (i < n) {
            let j = i;
            while (j < n && s[j] !== ' ') {
                j++;
            }
            words.push(s.slice(i, j));
            i = j;
        }
    }
    return words.reverse().join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        let mut words = Vec::new();
        let s: Vec<char> = s.chars().collect();
        let mut i = 0;
        let n = s.len();

        while i < n {
            while i < n && s[i] == ' ' {
                i += 1;
            }
            if i < n {
                let mut j = i;
                while j < n && s[j] != ' ' {
                    j += 1;
                }
                words.push(s[i..j].iter().collect::<String>());
                i = j;
            }
        }

        words.reverse();
        words.join(" ")
    }
}
```

#### C#

```cs
public class Solution {
    public string ReverseWords(string s) {
        List<string> words = new List<string>();
        int n = s.Length;
        for (int i = 0; i < n;) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                System.Text.StringBuilder t = new System.Text.StringBuilder();
                int j = i;
                while (j < n && s[j] != ' ') {
                    t.Append(s[j++]);
                }
                words.Add(t.ToString());
                i = j;
            }
        }
        words.Reverse();
        return string.Join(" ", words);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tách chuỗi

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 tự tách các từ. Hàm split tích hợp trên khoảng trắng đã loại bỏ các khoảng trắng thừa; hãy đảo ngược danh sách đó và nối lại. Mã ngắn hơn, cùng độ phức tạp.

<!-- thinking:end -->

Chúng ta có thể sử dụng hàm split tích hợp của chuỗi để tách chuỗi thành một danh sách các từ theo khoảng trắng, sau đó đảo ngược danh sách và cuối cùng nối nó thành một chuỗi.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        return " ".join(reversed(s.split()))
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

#### Go

```go
func reverseWords(s string) string {
	words := strings.Fields(s)
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    return s.trim().split(/\s+/).reverse().join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split_whitespace().rev().collect::<Vec<&str>>().join(" ")
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
