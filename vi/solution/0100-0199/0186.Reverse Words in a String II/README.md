---
comments: true
difficulty: Medium
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [186. Reverse Words in a String II 🔒](https://leetcode.com/problems/reverse-words-in-a-string-ii)

[中文文档](/solution/0100-0199/0186.Reverse%20Words%20in%20a%20String%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng ký tự <code>s</code>, hãy đảo ngược thứ tự của các <strong>từ</strong>.</p>

<p>Một <strong>từ</strong> được định nghĩa là một chuỗi các ký tự không phải khoảng trắng. Các <strong>từ</strong> trong <code>s</code> sẽ được phân tách bằng một khoảng trắng.</p>

<p>Code của bạn phải giải quyết bài toán <strong>tại chỗ,</strong> tức là không cấp phát thêm bộ nhớ.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
<strong>Đầu ra:</strong> ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> s = ["a"]
<strong>Đầu ra:</strong> ["a"]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> là chữ cái tiếng Anh (chữ hoa hoặc chữ thường), chữ số hoặc khoảng trắng <code>&#39; &#39;</code>.</li>
	<li>Có <strong>ít nhất một</strong> từ trong <code>s</code>.</li>
	<li><code>s</code> không chứa khoảng trắng ở đầu hoặc cuối.</li>
	<li>Tất cả các từ trong <code>s</code> được đảm bảo phân tách bằng một khoảng trắng.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Đảo ngược thứ tự các từ trong mảng ký tự tại chỗ; các từ được phân tách bằng một khoảng trắng. $n\le 10^5$, $O(1)$ bộ nhớ bổ sung. Đảo ngược từng từ, sau đó đảo ngược toàn bộ mảng: thứ tự các từ bị đảo và các chữ cái bên trong mỗi từ trở về đúng thứ tự. Hai con trỏ đánh dấu từng từ.

<!-- thinking:end -->

Chúng ta có thể duyệt qua mảng ký tự $s$, sử dụng hai con trỏ $i$ và $j$ để tìm vị trí bắt đầu và kết thúc của từng từ, sau đó đảo ngược từng từ, và cuối cùng đảo ngược toàn bộ mảng ký tự.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng ký tự $s$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: List[str]) -> None:
        def reverse(i: int, j: int):
            while i < j:
                s[i], s[j] = s[j], s[i]
                i, j = i + 1, j - 1

        i, n = 0, len(s)
        for j, c in enumerate(s):
            if c == " ":
                reverse(i, j - 1)
                i = j + 1
            elif j == n - 1:
                reverse(i, j)
        reverse(0, n - 1)
```

#### Java

```java
class Solution {
    public void reverseWords(char[] s) {
        int n = s.length;
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    private void reverse(char[] s, int i, int j) {
        for (; i < j; ++i, --j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void reverseWords(vector<char>& s) {
        auto reverse = [&](int i, int j) {
            for (; i < j; ++i, --j) {
                swap(s[i], s[j]);
            }
        };
        int n = s.size();
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(i, j);
            }
        }
        reverse(0, n - 1);
    }
};
```

#### Go

```go
func reverseWords(s []byte) {
	reverse := func(i, j int) {
		for ; i < j; i, j = i+1, j-1 {
			s[i], s[j] = s[j], s[i]
		}
	}
	i, n := 0, len(s)
	for j, c := range s {
		if c == ' ' {
			reverse(i, j-1)
			i = j + 1
		} else if j == n-1 {
			reverse(i, j)
		}
	}
	reverse(0, n-1)
}
```

#### TypeScript

```ts
/**
 Không trả về gì, thay đổi s tại chỗ.
 */
function reverseWords(s: string[]): void {
    const n = s.length;
    const reverse = (i: number, j: number): void => {
        for (; i < j; ++i, --j) {
            [s[i], s[j]] = [s[j], s[i]];
        }
    };
    for (let i = 0, j = 0; j <= n; ++j) {
        if (s[j] === ' ') {
            reverse(i, j - 1);
            i = j + 1;
        } else if (j === n - 1) {
            reverse(i, j);
        }
    }
    reverse(0, n - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
