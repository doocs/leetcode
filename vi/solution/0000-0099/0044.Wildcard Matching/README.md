---
comments: true
difficulty: Hard
tags:
    - Greedy
    - Recursion
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [44. Wildcard Matching](https://leetcode.com/problems/wildcard-matching)

[中文文档](/solution/0000-0099/0044.Wildcard%20Matching/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi đầu vào (<code>s</code>) và một pattern (<code>p</code>), hãy triển khai việc so khớp pattern wildcard với hỗ trợ <code>&#39;?&#39;</code> và <code>&#39;*&#39;</code>, trong đó:</p>

<ul>
	<li><code>&#39;?&#39;</code> Khớp với bất kỳ ký tự đơn nào.</li>
	<li><code>&#39;*&#39;</code> Khớp với bất kỳ chuỗi ký tự nào (bao gồm cả chuỗi rỗng).</li>
</ul>

<p>Việc so khớp phải bao phủ <strong>toàn bộ</strong> chuỗi đầu vào (không phải một phần).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;aa&quot;, p = &quot;a&quot;
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> &quot;a&quot; không khớp với toàn bộ chuỗi &quot;aa&quot;.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;aa&quot;, p = &quot;*&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong>&nbsp;&#39;*&#39; khớp với bất kỳ chuỗi nào.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;cb&quot;, p = &quot;?a&quot;
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong>&nbsp;&#39;?&#39; khớp với &#39;c&#39;, nhưng chữ cái thứ hai là &#39;a&#39;, không khớp với &#39;b&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= s.length, p.length &lt;= 2000</code></li>
	<li><code>s</code> chỉ chứa các chữ cái tiếng Anh viết thường.</li>
	<li><code>p</code> chỉ chứa các chữ cái tiếng Anh viết thường, <code>&#39;?&#39;</code> hoặc <code>&#39;*&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm có ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đệ quy theo từng ký tự: các ký tự literal và `?` khớp với một ký tự, còn `*` có thể khớp với chuỗi rỗng hoặc nhiều ký tự. Cách này đúng, nhưng nếu không ghi nhớ thì các nhánh của `*` sẽ bùng nổ. Với $|s|, |p| \le 2000$, cách đó sẽ bị quá thời gian.
>
> Nút thắt là việc hỏi đi hỏi lại cùng một cặp hậu tố $(i, j)$. Trạng thái chỉ là “$s[i:]$ có khớp với $p[j:]$ hay không”; các bài toán con bị chồng lấn rất nhiều.
>
> Ta lưu kết quả của $dfs(i, j)$ vào cache. Ba bước chuyển của `*` (tiêu thụ một ký tự, tiến cả hai chỉ số, bỏ qua `*`) đều là các chuyển trạng thái trên trạng thái đó. Ghi nhớ biến tìm kiếm theo cấp số nhân thành $O(mn)$.

<!-- thinking:end -->

Ta thiết kế một hàm $dfs(i, j)$, biểu thị việc chuỗi $s$ bắt đầu từ ký tự thứ $i$ có khớp với chuỗi $p$ bắt đầu từ ký tự thứ $j$ hay không. Đáp án là $dfs(0, 0)$.

Quá trình thực thi hàm $dfs(i, j)$ như sau:

- Nếu $i \geq \textit{len}(s)$, thì $dfs(i, j)$ chỉ là true khi $j \geq \textit{len}(p)$ hoặc $p[j] = '*'$ và $dfs(i, j + 1)$ là true.
- Nếu $j \geq \textit{len}(p)$, thì $dfs(i, j)$ là false.
- Nếu $p[j] = '*'$, thì $dfs(i, j)$ là true khi và chỉ khi một trong $dfs(i + 1, j)$, $dfs(i + 1, j + 1)$ hoặc $dfs(i, j + 1)$ là true.
- Nếu không, $dfs(i, j)$ là true khi và chỉ khi $p[j] = '?'$ hoặc $s[i] = p[j]$, đồng thời $dfs(i + 1, j + 1)$ là true.

Để tránh tính toán lặp lại, chúng ta sử dụng phương pháp tìm kiếm có ghi nhớ và lưu kết quả của $dfs(i, j)$ trong một hash table.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là độ dài của các chuỗi $s$ và $p$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        @cache
        def dfs(i: int, j: int) -> bool:
            if i >= len(s):
                return j >= len(p) or (p[j] == "*" and dfs(i, j + 1))
            if j >= len(p):
                return False
            if p[j] == "*":
                return dfs(i + 1, j) or dfs(i + 1, j + 1) or dfs(i, j + 1)
            return (p[j] == "?" or s[i] == p[j]) and dfs(i + 1, j + 1)

        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Boolean[][] f;
    private char[] s;
    private char[] p;
    private int m;
    private int n;

    public boolean isMatch(String s, String p) {
        this.s = s.toCharArray();
        this.p = p.toCharArray();
        m = s.length();
        n = p.length();
        f = new Boolean[m][n];
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (i >= m) {
            return j >= n || (p[j] == '*' && dfs(i, j + 1));
        }
        if (j >= n) {
            return false;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        if (p[j] == '*') {
            f[i][j] = dfs(i + 1, j) || dfs(i + 1, j + 1) || dfs(i, j + 1);
        } else {
            f[i][j] = (p[j] == '?' || s[i] == p[j]) && dfs(i + 1, j + 1);
        }
        return f[i][j];
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
        memset(f, -1, sizeof(f));
        function<bool(int, int)> dfs = [&](int i, int j) {
            if (i >= m) {
                return j >= n || (p[j] == '*' && dfs(i, j + 1));
            }
            if (j >= n) {
                return false;
            }
            if (f[i][j] != -1) {
                return f[i][j] == 1;
            }
            if (p[j] == '*') {
                f[i][j] = dfs(i + 1, j) || dfs(i, j + 1) ? 1 : 0;
            } else {
                f[i][j] = (p[j] == '?' || s[i] == p[j]) && dfs(i + 1, j + 1) ? 1 : 0;
            }
            return f[i][j] == 1;
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
		if i >= m {
			return j >= n || p[j] == '*' && dfs(i, j+1)
		}
		if j >= n {
			return false
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		f[i][j] = 2
		ok := false
		if p[j] == '*' {
			ok = dfs(i+1, j) || dfs(i+1, j+1) || dfs(i, j+1)
		} else {
			ok = (p[j] == '?' || s[i] == p[j]) && dfs(i+1, j+1)
		}
		if ok {
			f[i][j] = 1
		}
		return ok
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function isMatch(s: string, p: string): boolean {
    const m = s.length;
    const n = p.length;
    const f: number[][] = Array.from({ length: m + 1 }, () =>
        Array.from({ length: n + 1 }, () => -1),
    );
    const dfs = (i: number, j: number): boolean => {
        if (i >= m) {
            return j >= n || (p[j] === '*' && dfs(i, j + 1));
        }
        if (j >= n) {
            return false;
        }
        if (f[i][j] !== -1) {
            return f[i][j] === 1;
        }
        if (p[j] === '*') {
            f[i][j] = dfs(i + 1, j) || dfs(i, j + 1) ? 1 : 0;
        } else {
            f[i][j] = (p[j] === '?' || s[i] === p[j]) && dfs(i + 1, j + 1) ? 1 : 0;
        }
        return f[i][j] === 1;
    };
    return dfs(0, 0);
}
```

#### C#

```cs
public class Solution {
    private bool?[,] f;
    private char[] s;
    private char[] p;
    private int m;
    private int n;

    public bool IsMatch(string s, string p) {
        this.s = s.ToCharArray();
        this.p = p.ToCharArray();
        m = s.Length;
        n = p.Length;
        f = new bool?[m, n];
        return Dfs(0, 0);
    }

    private bool Dfs(int i, int j) {
        if (i >= m) {
            return j >= n || (p[j] == '*' && Dfs(i, j + 1));
        }
        if (j >= n) {
            return false;
        }
        if (f[i, j] != null) {
            return f[i, j].Value;
        }
        if (p[j] == '*') {
            f[i, j] = Dfs(i + 1, j) || Dfs(i + 1, j + 1) || Dfs(i, j + 1);
        } else {
            f[i, j] = (p[j] == '?' || s[i] == p[j]) && Dfs(i + 1, j + 1);
        }
        return f[i, j].Value;
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
> Lời giải 1 đã có độ phức tạp $O(mn)$, nhưng đệ quy phải trả giá bằng call stack và các hằng số của cache. Khi độ dài là $2000$, cả hai đều gây ảnh hưởng.
>
> Điều còn thiếu là viết cùng phép chuyển theo hướng từ dưới lên. $f[i][j]$ là dạng bảng của $dfs(i, j)$: điền biên chuỗi rỗng/`*` trước, sau đó điền phần còn lại. Độ phức tạp tiệm cận giữ nguyên, nhưng các hằng số ổn định hơn.

<!-- thinking:end -->

Chúng ta có thể chuyển phép tìm kiếm có ghi nhớ trong Lời giải 1 thành quy hoạch động.

Định nghĩa $f[i][j]$ biểu thị việc $i$ ký tự đầu tiên của chuỗi $s$ có khớp với $j$ ký tự đầu tiên của chuỗi $p$ hay không. Ban đầu, $f[0][0] = \textit{true}$, cho biết hai chuỗi rỗng khớp nhau. Với $j \in [1, n]$, nếu $p[j-1] = '*'$, thì $f[0][j] = f[0][j-1]$.

Tiếp theo, xét trường hợp $i \in [1, m]$ và $j \in [1, n]$:

- Nếu $p[j-1] = '*'$, thì $f[i][j] = f[i-1][j] \lor f[i][j-1] \lor f[i-1][j-1]$.
- Nếu không, $f[i][j] = (p[j-1] = '?' \lor s[i-1] = p[j-1]) \land f[i-1][j-1]$.

Đáp án cuối cùng là $f[m][n]$.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là độ dài của các chuỗi $s$ và $p$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        f = [[False] * (n + 1) for _ in range(m + 1)]
        f[0][0] = True
        for j in range(1, n + 1):
            if p[j - 1] == "*":
                f[0][j] = f[0][j - 1]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == "*":
                    f[i][j] = f[i - 1][j] or f[i][j - 1] or f[i - 1][j - 1]
                else:
                    f[i][j] = f[i - 1][j - 1] and (
                        p[j - 1] == "?" or s[i - 1] == p[j - 1]
                    )
        return f[m][n]
```

#### Java

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                f[0][j] = f[0][j - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i - 1][j] || f[i][j - 1] || f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j - 1]
                        && (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1));
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
        int m = s.length(), n = p.length();
        bool f[m + 1][n + 1];
        memset(f, false, sizeof(f));
        f[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] == '*') {
                f[0][j] = f[0][j - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    f[i][j] = f[i - 1][j] || f[i][j - 1] || f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j - 1] && (p[j - 1] == '?' || s[i - 1] == p[j - 1]);
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
	for j := 1; j <= n; j++ {
		if p[j-1] == '*' {
			f[0][j] = f[0][j-1]
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if p[j-1] == '*' {
				f[i][j] = f[i-1][j] || f[i][j-1] || f[i-1][j-1]
			} else {
				f[i][j] = f[i-1][j-1] && (p[j-1] == '?' || s[i-1] == p[j-1])
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function isMatch(s: string, p: string): boolean {
    const m: number = s.length;
    const n: number = p.length;
    const f: boolean[][] = Array.from({ length: m + 1 }, () =>
        Array.from({ length: n + 1 }, () => false),
    );
    f[0][0] = true;
    for (let j = 1; j <= n; ++j) {
        if (p.charAt(j - 1) === '*') {
            f[0][j] = f[0][j - 1];
        }
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (p[j - 1] === '*') {
                f[i][j] = f[i - 1][j] || f[i][j - 1] || f[i - 1][j - 1];
            } else {
                f[i][j] = f[i - 1][j - 1] && (p[j - 1] === '?' || s[i - 1] === p[j - 1]);
            }
        }
    }
    return f[m][n];
}
```

#### PHP

```php
class Solution {
    /**
     * @param string $s
     * @param string $p
     * @return boolean
     */

    function isMatch($s, $p) {
        $lengthS = strlen($s);
        $lengthP = strlen($p);
        $dp = [];
        for ($i = 0; $i <= $lengthS; $i++) {
            $dp[$i] = array_fill(0, $lengthP + 1, false);
        }
        $dp[0][0] = true;

        for ($i = 1; $i <= $lengthP; $i++) {
            if ($p[$i - 1] == '*') {
                $dp[0][$i] = $dp[0][$i - 1];
            }
        }
        for ($i = 1; $i <= $lengthS; $i++) {
            for ($j = 1; $j <= $lengthP; $j++) {
                if ($p[$j - 1] == '?' || $s[$i - 1] == $p[$j - 1]) {
                    $dp[$i][$j] = $dp[$i - 1][$j - 1];
                } elseif ($p[$j - 1] == '*') {
                    $dp[$i][$j] = $dp[$i][$j - 1] || $dp[$i - 1][$j];
                }
            }
        }
        return $dp[$lengthS][$lengthP];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
