---
comments: true
difficulty: Hard
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [115. Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences)

[中文文档](/solution/0100-0199/0115.Distinct%20Subsequences/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi s và t, hãy trả về <i>số lượng các</i> <b><i>dãy con phân biệt</i></b><i> của </i>s<i> bằng </i>t.</p>

<p>Các trường hợp kiểm thử được tạo sao cho đáp án phù hợp với một số nguyên có dấu 32 bit.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;rabbbit&quot;, t = &quot;rabbit&quot;
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong>
Như minh họa dưới đây, có 3 cách tạo ra &quot;rabbit&quot; từ s.
<code><strong><u>rabb</u></strong>b<strong><u>it</u></strong></code>
<code><strong><u>ra</u></strong>b<strong><u>bbit</u></strong></code>
<code><strong><u>rab</u></strong>b<strong><u>bit</u></strong></code>
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;babgbag&quot;, t = &quot;bag&quot;
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong>
Như minh họa dưới đây, có 5 cách tạo ra &quot;bag&quot; từ s.
<code><strong><u>ba</u></strong>b<u><strong>g</strong></u>bag</code>
<code><strong><u>ba</u></strong>bgba<strong><u>g</u></strong></code>
<code><u><strong>b</strong></u>abgb<strong><u>ag</u></strong></code>
<code>ba<u><strong>b</strong></u>gb<u><strong>ag</strong></u></code>
<code>babg<strong><u>bag</u></strong></code></pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 1000</code></li>
	<li><code>s</code> và <code>t</code> chỉ gồm các chữ cái tiếng Anh.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Hãy đếm các dãy con của $s$ bằng $t$. Việc chọn lấy hoặc bỏ qua từng ký tự của $s$ sẽ bùng nổ khi cả hai độ dài đạt $1000$; đệ quy ngây thơ lặp lại các cặp hậu tố giống nhau.
>
> Trạng thái là phần chúng ta đã dùng của $s$ và $t$. $f[i][j]$ là số cách để $i$ ký tự đầu tiên của $s$ tạo thành $j$ ký tự đầu tiên của $t$. Khi hai ký tự hiện tại khớp nhau, chúng ta cộng thêm chuyển trạng thái sử dụng cả hai. Chuỗi $t$ rỗng có đúng một dãy con rỗng.

<!-- thinking:end -->

Ta định nghĩa $f[i][j]$ là số cách mà $i$ ký tự đầu tiên của chuỗi $s$ tạo thành $j$ ký tự đầu tiên của chuỗi $t$. Ban đầu, $f[i][0]=1$ với mọi $i \in [0,m]$.

Khi $i > 0$, ta xét cách tính $f[i][j]$:

- Khi $s[i-1] \ne t[j-1]$, ta không thể chọn $s[i-1]$, nên $f[i][j]=f[i-1][j]$;
- Ngược lại, ta có thể chọn $s[i-1]$, nên $f[i][j]=f[i-1][j-1]$.

Do đó, ta có phương trình chuyển trạng thái sau:

$$
f[i][j]=\left\{
\begin{aligned}
&f[i-1][j], &s[i-1] \ne t[j-1] \\
&f[i-1][j-1]+f[i-1][j], &s[i-1]=t[j-1]
\end{aligned}
\right.
$$

Đáp án cuối cùng là $f[m][n]$, trong đó $m$ và $n$ lần lượt là độ dài của các chuỗi $s$ và $t$.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m + 1):
            f[i][0] = 1
        for i, a in enumerate(s, 1):
            for j, b in enumerate(t, 1):
                f[i][j] = f[i - 1][j]
                if a == b:
                    f[i][j] += f[i - 1][j - 1]
        return f[m][n]
```

#### Java

```java
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numDistinct(string s, string t) {
        int m = s.size(), n = t.size();
        unsigned long long f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func numDistinct(s string, t string) int {
	m, n := len(s), len(t)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 0; i <= m; i++ {
		f[i][0] = 1
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if s[i-1] == t[j-1] {
				f[i][j] += f[i-1][j-1]
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function numDistinct(s: string, t: string): number {
    const m = s.length;
    const n = t.length;
    const f: number[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        f[i][0] = 1;
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (s[i - 1] === t[j - 1]) {
                f[i][j] += f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn num_distinct(s: String, t: String) -> i32 {
        let n = s.len();
        let m = t.len();
        let mut dp: Vec<Vec<u64>> = vec![vec![0; m + 1]; n + 1];

        // Initialize the dp vector
        for i in 0..=n {
            dp[i][0] = 1;
        }

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                dp[i][j] = if s.as_bytes()[i - 1] == t.as_bytes()[j - 1] {
                    dp[i - 1][j] + dp[i - 1][j - 1]
                } else {
                    dp[i - 1][j]
                };
            }
        }

        dp[n][m] as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động tối ưu

<!-- thinking:start -->

> **Tư duy**
>
> Trong Lời giải 1, $f[i][j]$ chỉ phụ thuộc vào hàng trước đó. Sau khi nén thành một chiều, chúng ta phải cập nhật $j$ từ phải sang trái để $f[j-1]$ không bị ghi đè quá sớm. Không gian giảm xuống còn $O(|t|)$.

<!-- thinking:end -->

$f[i][j]$ chỉ phụ thuộc vào hàng trước đó, nên có thể bỏ chiều thứ nhất. Cập nhật $j$ từ lớn đến nhỏ để $f[j-1]$ không bị ghi đè. Độ phức tạp không gian trở thành $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        n = len(t)
        f = [1] + [0] * n
        for a in s:
            for j in range(n, 0, -1):
                if a == t[j - 1]:
                    f[j] += f[j - 1]
        return f[n]
```

#### Java

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = t.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (char a : s.toCharArray()) {
            for (int j = n; j > 0; --j) {
                char b = t.charAt(j - 1);
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numDistinct(string s, string t) {
        int n = t.size();
        unsigned long long f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (char& a : s) {
            for (int j = n; j; --j) {
                char b = t[j - 1];
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func numDistinct(s string, t string) int {
	n := len(t)
	f := make([]int, n+1)
	f[0] = 1
	for _, a := range s {
		for j := n; j > 0; j-- {
			if b := t[j-1]; byte(a) == b {
				f[j] += f[j-1]
			}
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function numDistinct(s: string, t: string): number {
    const n = t.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (const a of s) {
        for (let j = n; j; --j) {
            const b = t[j - 1];
            if (a === b) {
                f[j] += f[j - 1];
            }
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
