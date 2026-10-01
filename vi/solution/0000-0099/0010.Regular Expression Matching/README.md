---
comments: true
difficulty: Hard
tags:
    - Recursion
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching)

[中文文档](/solution/0000-0099/0010.Regular%20Expression%20Matching/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi đầu vào <code>s</code>&nbsp;và một pattern <code>p</code>, hãy triển khai việc khớp biểu thức chính quy với hỗ trợ <code>&#39;.&#39;</code> và <code>&#39;*&#39;</code>, trong đó:</p>

<ul>
	<li><code>&#39;.&#39;</code> Khớp với bất kỳ ký tự đơn nào.​​​​</li>
	<li><code>&#39;*&#39;</code> Khớp với không hoặc nhiều lần phần tử đứng trước nó.</li>
</ul>

<p>Trả về một boolean cho biết việc khớp có bao phủ toàn bộ chuỗi đầu vào hay không (không phải một phần).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;aa&quot;, p = &quot;a&quot;
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> &quot;a&quot; không khớp với toàn bộ chuỗi &quot;aa&quot;.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;aa&quot;, p = &quot;a*&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> &#39;*&#39; có nghĩa là không hoặc nhiều lần phần tử đứng trước nó, &#39;a&#39;. Do đó, bằng cách lặp lại &#39;a&#39; một lần, nó trở thành &quot;aa&quot;.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;ab&quot;, p = &quot;.*&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> &quot;.*&quot; có nghĩa là &quot;không hoặc nhiều lần (*) của bất kỳ ký tự nào (.)&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li>
	<li><code>1 &lt;= p.length&nbsp;&lt;= 20</code></li>
	<li><code>s</code> chỉ chứa các chữ cái tiếng Anh viết thường.</li>
	<li><code>p</code> chỉ chứa các chữ cái tiếng Anh viết thường, <code>&#39;.&#39;</code> và&nbsp;<code>&#39;*&#39;</code>.</li>
	<li>Đảm bảo rằng với mỗi lần xuất hiện của ký tự <code>&#39;*&#39;</code>, sẽ có một ký tự hợp lệ đứng trước để khớp.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm có ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Khớp từ trái sang phải và, tại mỗi `*`, thử số lần lặp $0,1,2,\ldots$ là cách tìm kiếm tự nhiên. $s$ và $p$ có độ dài tối đa là $20$, vì vậy đôi khi thời gian lũy thừa vẫn có thể vượt qua, nhưng cùng một cặp vị trí lại bị tính toán lặp đi lặp lại.
>
> Một `*` chỉ áp dụng cho token đứng trước nó: hoặc khớp token đó $0$ lần (bỏ qua hai chỉ số của pattern), hoặc ký tự hiện tại khớp với token đó (bao gồm cả `.`) và cùng `*` tiêu thụ ký tự tiếp theo của $s$. Một ký tự thông thường phải được tiêu thụ một-một.
>
> Do đó, trạng thái là “hậu tố $i$ của $s$ có thể khớp với hậu tố $j$ của $p$ hay không”. Dùng tìm kiếm có ghi nhớ để các nhánh lũy thừa gộp lại thành $O(mn)$ cặp $(i,j)$.

<!-- thinking:end -->

Ta thiết kế một hàm $dfs(i, j)$, cho biết ký tự thứ $i$ của $s$ có khớp với ký tự thứ $j$ của $p$ hay không. Đáp án là $dfs(0, 0)$.

Quá trình tính hàm $dfs(i, j)$ như sau:

- Nếu $j$ đã đến cuối $p$, thì nếu $i$ cũng đã đến cuối $s$, việc khớp thành công, ngược lại việc khớp thất bại.
- Nếu ký tự tiếp theo sau $j$ là `'*'`, ta có thể chọn khớp $0$ ký tự $s[i]$, tương ứng với $dfs(i, j + 2)$. Nếu $i \lt m$ và $s[i]$ khớp với $p[j]$, ta có thể chọn khớp $1$ ký tự $s[i]$, tương ứng với $dfs(i + 1, j)$.
- Nếu ký tự tiếp theo sau $j$ không phải là `'*'`, thì nếu $i \lt m$ và $s[i]$ khớp với $p[j]$, đó là $dfs(i + 1, j + 1)$. Nếu không, việc khớp thất bại.

Trong quá trình này, ta có thể dùng tìm kiếm có ghi nhớ để tránh tính toán lặp lại.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là độ dài của $s$ và $p$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        @cache
        def dfs(i, j):
            if j >= n:
                return i == m
            if j + 1 < n and p[j + 1] == '*':
                return dfs(i, j + 2) or (
                    i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j)
                )
            return i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j + 1)

        m, n = len(s), len(p)
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Boolean[][] f;
    private String s;
    private String p;
    private int m;
    private int n;

    public boolean isMatch(String s, String p) {
        m = s.length();
        n = p.length();
        f = new Boolean[m + 1][n + 1];
        this.s = s;
        this.p = p;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (j >= n) {
            return i == m;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        boolean res = false;
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            res = dfs(i, j + 2)
                || (i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j));
        } else {
            res = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j + 1);
        }
        return f[i][j] = res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof f);
        function<bool(int, int)> dfs = [&](int i, int j) -> bool {
            if (j >= n) {
                return i == m;
            }
            if (f[i][j]) {
                return f[i][j] == 1;
            }
            int res = -1;
            if (j + 1 < n && p[j + 1] == '*') {
                if (dfs(i, j + 2) or (i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j))) {
                    res = 1;
                }
            } else if (i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j + 1)) {
                res = 1;
            }
            f[i][j] = res;
            return res == 1;
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if j >= n {
			return i == m
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		res := -1
		if j+1 < n && p[j+1] == '*' {
			if dfs(i, j+2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i+1, j)) {
				res = 1
			}
		} else if i < m && (s[i] == p[j] || p[j] == '.') && dfs(i+1, j+1) {
			res = 1
		}
		f[i][j] = res
		return res == 1
	}
	return dfs(0, 0)
}
```

#### Rust

```rust
impl Solution {
    pub fn is_match(s: String, p: String) -> bool {
        let (m, n) = (s.len(), p.len());
        let mut f = vec![vec![0; n + 1]; m + 1];

        fn dfs(
            s: &Vec<char>,
            p: &Vec<char>,
            f: &mut Vec<Vec<i32>>,
            i: usize,
            j: usize,
            m: usize,
            n: usize,
        ) -> bool {
            if j >= n {
                return i == m;
            }
            if f[i][j] != 0 {
                return f[i][j] == 1;
            }
            let mut res = -1;
            if j + 1 < n && p[j + 1] == '*' {
                if dfs(s, p, f, i, j + 2, m, n)
                    || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(s, p, f, i + 1, j, m, n))
                {
                    res = 1;
                }
            } else if i < m && (s[i] == p[j] || p[j] == '.') && dfs(s, p, f, i + 1, j + 1, m, n) {
                res = 1;
            }
            f[i][j] = res;
            res == 1
        }

        dfs(
            &s.chars().collect(),
            &p.chars().collect(),
            &mut f,
            0,
            0,
            m,
            n,
        )
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    const m = s.length;
    const n = p.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const dfs = (i, j) => {
        if (j >= n) {
            return i === m;
        }
        if (f[i][j]) {
            return f[i][j] === 1;
        }
        let res = -1;
        if (j + 1 < n && p[j + 1] === '*') {
            if (dfs(i, j + 2) || (i < m && (s[i] === p[j] || p[j] === '.') && dfs(i + 1, j))) {
                res = 1;
            }
        } else if (i < m && (s[i] === p[j] || p[j] === '.') && dfs(i + 1, j + 1)) {
            res = 1;
        }
        f[i][j] = res;
        return res === 1;
    };
    return dfs(0, 0);
};
```

#### C#

```cs
public class Solution {
    private string s;
    private string p;
    private int m;
    private int n;
    private int[,] f;

    public bool IsMatch(string s, string p) {
        m = s.Length;
        n = p.Length;
        f = new int[m + 1, n + 1];
        this.s = s;
        this.p = p;
        return dfs(0, 0);
    }

    private bool dfs(int i, int j) {
        if (j >= n) {
            return i == m;
        }
        if (f[i, j] != 0) {
            return f[i, j] == 1;
        }
        int res = -1;
        if (j + 1 < n && p[j + 1] == '*') {
            if (dfs(i, j + 2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j))) {
                res = 1;
            }
        } else if (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j + 1)) {
            res = 1;
        }
        f[i, j] = res;
        return res == 1;
    }
}
```

#### C

```c
#define MAX_LEN 1000

char *ss, *pp;
int m, n;
int f[MAX_LEN + 1][MAX_LEN + 1];

bool dfs(int i, int j) {
    if (j >= n) {
        return i == m;
    }
    if (f[i][j] != 0) {
        return f[i][j] == 1;
    }
    int res = -1;
    if (j + 1 < n && pp[j + 1] == '*') {
        if (dfs(i, j + 2) || (i < m && (ss[i] == pp[j] || pp[j] == '.') && dfs(i + 1, j))) {
            res = 1;
        }
    } else if (i < m && (ss[i] == pp[j] || pp[j] == '.') && dfs(i + 1, j + 1)) {
        res = 1;
    }
    f[i][j] = res;
    return res == 1;
}

bool isMatch(char* s, char* p) {
    ss = s;
    pp = p;
    m = strlen(s);
    n = strlen(p);
    memset(f, 0, sizeof(f));
    return dfs(0, 0);
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param String $p
     * @return Boolean
     */
    function isMatch($s, $p) {
        $m = strlen($s);
        $n = strlen($p);
        $f = array_fill(0, $m + 1, array_fill(0, $n + 1, 0));

        $dfs = function ($i, $j) use (&$s, &$p, $m, $n, &$f, &$dfs) {
            if ($j >= $n) {
                return $i == $m;
            }
            if ($f[$i][$j] != 0) {
                return $f[$i][$j] == 1;
            }
            $res = -1;
            if ($j + 1 < $n && $p[$j + 1] == '*') {
                if (
                    $dfs($i, $j + 2) ||
                    ($i < $m && ($s[$i] == $p[$j] || $p[$j] == '.') && $dfs($i + 1, $j))
                ) {
                    $res = 1;
                }
            } elseif ($i < $m && ($s[$i] == $p[$j] || $p[$j] == '.') && $dfs($i + 1, $j + 1)) {
                $res = 1;
            }
            $f[$i][$j] = $res;
            return $res == 1;
        };

        return $dfs(0, 0);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 dùng ghi nhớ đã đạt $O(mn)$, nhưng vẫn là đệ quy: có độ sâu ngăn xếp ngầm và hằng số lớn hơn. Các chuyển trạng thái không có hiệu ứng sau, vì vậy ta có thể điền $i,j$ theo thứ tự tăng dần.
>
> $f[i][j]$ là việc $i$ ký tự đầu tiên của $s$ có khớp với $j$ ký tự đầu tiên của $p$ hay không. Chuỗi rỗng khớp với chuỗi rỗng. Ta cũng cần $i=0$ để các pattern như `a*` có thể khớp với chuỗi rỗng, đó là lý do vòng lặp ngoài bắt đầu từ tiền tố rỗng.
>
> Các trường hợp `*` giống với Lời giải 1: bỏ qua hai ký tự của pattern, hoặc tiêu thụ thêm một ký tự của $s$ khi ký tự đó phù hợp. Sau khi điền xong bảng, ta đọc $f[m][n]$.

<!-- thinking:end -->

Ta có thể chuyển tìm kiếm có ghi nhớ trong Lời giải 1 thành quy hoạch động.

Định nghĩa $f[i][j]$ biểu diễn việc $i$ ký tự đầu tiên của chuỗi $s$ có khớp với $j$ ký tự đầu tiên của chuỗi $p$ hay không. Đáp án là $f[m][n]$. Khởi tạo $f[0][0] = true$, cho biết chuỗi rỗng và biểu thức chính quy rỗng khớp với nhau.

Tương tự Lời giải 1, ta có thể thảo luận các trường hợp khác nhau.

- Nếu $p[j - 1]$ là `'*'`, ta có thể chọn khớp $0$ ký tự $s[i - 1]$, khi đó $f[i][j] = f[i][j - 2]$. Nếu $s[i - 1]$ khớp với $p[j - 2]$, ta có thể chọn khớp $1$ ký tự $s[i - 1]$, khi đó $f[i][j] = f[i][j] \lor f[i - 1][j]$.
- Nếu $p[j - 1]$ không phải là `'*'`, thì nếu $s[i - 1]$ khớp với $p[j - 1]$, ta có $f[i][j] = f[i - 1][j - 1]$. Nếu không, việc khớp thất bại.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là độ dài của $s$ và $p$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        f = [[False] * (n + 1) for _ in range(m + 1)]
        f[0][0] = True
        for i in range(m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == "*":
                    f[i][j] = f[i][j - 2]
                    if i > 0 and (p[j - 2] == "." or s[i - 1] == p[j - 2]):
                        f[i][j] |= f[i - 1][j]
                elif i > 0 and (p[j - 1] == "." or s[i - 1] == p[j - 1]):
                    f[i][j] = f[i - 1][j - 1]
        return f[m][n]
```

#### Java

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (i > 0 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
                        f[i][j] |= f[i - 1][j];
                    }
                } else if (i > 0
                    && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    f[i][j] = f[i - 1][j - 1];
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
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        bool f[m + 1][n + 1];
        memset(f, false, sizeof f);
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    f[i][j] = f[i][j - 2];
                    if (i && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) {
                        f[i][j] |= f[i - 1][j];
                    }
                } else if (i && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	f := make([][]bool, m+1)
	for i := range f {
		f[i] = make([]bool, n+1)
	}
	f[0][0] = true
	for i := 0; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if p[j-1] == '*' {
				f[i][j] = f[i][j-2]
				if i > 0 && (p[j-2] == '.' || p[j-2] == s[i-1]) {
					f[i][j] = f[i][j] || f[i-1][j]
				}
			} else if i > 0 && (p[j-1] == '.' || p[j-1] == s[i-1]) {
				f[i][j] = f[i-1][j-1]
			}
		}
	}
	return f[m][n]
}
```

#### Rust

```rust
impl Solution {
    pub fn is_match(s: String, p: String) -> bool {
        let m = s.len();
        let n = p.len();
        let mut f = vec![vec![false; n + 1]; m + 1];

        f[0][0] = true;

        let s: Vec<char> = s.chars().collect();
        let p: Vec<char> = p.chars().collect();

        for i in 0..=m {
            for j in 1..=n {
                if p[j - 1] == '*' {
                    f[i][j] = f[i][j - 2];
                    if i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1]) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else if i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }

        f[m][n]
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    const m = s.length;
    const n = p.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(false));
    f[0][0] = true;
    for (let i = 0; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (p[j - 1] === '*') {
                f[i][j] = f[i][j - 2];
                if (i && (p[j - 2] === '.' || p[j - 2] === s[i - 1])) {
                    f[i][j] |= f[i - 1][j];
                }
            } else if (i && (p[j - 1] === '.' || p[j - 1] === s[i - 1])) {
                f[i][j] = f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
};
```

#### C#

```cs
public class Solution {
    public bool IsMatch(string s, string p) {
        int m = s.Length, n = p.Length;
        bool[,] f = new bool[m + 1, n + 1];
        f[0, 0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    f[i, j] = f[i, j - 2];
                    if (i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) {
                        f[i, j] |= f[i - 1, j];
                    }
                } else if (i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
                    f[i, j] = f[i - 1, j - 1];
                }
            }
        }
        return f[m, n];
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param String $p
     * @return Boolean
     */
    function isMatch($s, $p) {
        $m = strlen($s);
        $n = strlen($p);

        $f = array_fill(0, $m + 1, array_fill(0, $n + 1, false));
        $f[0][0] = true;

        for ($i = 0; $i <= $m; $i++) {
            for ($j = 1; $j <= $n; $j++) {
                if ($p[$j - 1] == '*') {
                    $f[$i][$j] = $f[$i][$j - 2];
                    if ($i > 0 && ($p[$j - 2] == '.' || $p[$j - 2] == $s[$i - 1])) {
                        $f[$i][$j] = $f[$i][$j] || $f[$i - 1][$j];
                    }
                } elseif ($i > 0 && ($p[$j - 1] == '.' || $p[$j - 1] == $s[$i - 1])) {
                    $f[$i][$j] = $f[$i - 1][$j - 1];
                }
            }
        }

        return $f[$m][$n];
    }
}
```

#### C

```c
bool isMatch(char* s, char* p) {
    int m = strlen(s), n = strlen(p);
    bool f[m + 1][n + 1];
    memset(f, 0, sizeof(f));
    f[0][0] = true;

    for (int i = 0; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] == '*') {
                f[i][j] = f[i][j - 2];
                if (i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) {
                    f[i][j] = f[i][j] || f[i - 1][j];
                }
            } else if (i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
                f[i][j] = f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
