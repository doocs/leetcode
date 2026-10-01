---
comments: true
difficulty: Hard
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [132. Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii)

[中文文档](/solution/0100-0199/0132.Palindrome%20Partitioning%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Với một chuỗi <code>s</code>, hãy phân hoạch <code>s</code> sao cho mọi <span data-keyword="substring-nonempty">chuỗi con</span> trong phân hoạch đều là một <span data-keyword="palindrome-string">palindrome</span>.</p>

<p>Hãy trả về <em>số lần cắt <strong>tối thiểu</strong> cần thiết để phân hoạch</em> <code>s</code> thành các palindrome.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;aab&quot;
<strong>Đầu ra:</strong> 1
<strong>Giải thích:</strong> Phân hoạch palindrome [&quot;aa&quot;,&quot;b&quot;] có thể được tạo ra bằng 1 lần cắt.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;a&quot;
<strong>Đầu ra:</strong> 0
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;ab&quot;
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta chỉ cần số lần cắt ít nhất, không cần các phân hoạch. $n\le 2000$, vì vậy không thể liệt kê chúng. Hãy tiền xử lý các đoạn palindrome như trước. $f[i]$ là số lần cắt ít nhất của $s[0..i]$: thử palindrome cuối cùng $s[j..i]$ và lấy $f[j-1]+1$; nếu toàn bộ tiền tố là palindrome thì đáp án là $0$.

<!-- thinking:end -->

Đầu tiên, chúng ta tiền xử lý chuỗi $s$ để xác định mỗi chuỗi con $s[i..j]$ có phải là palindrome hay không, và ghi lại thông tin này trong một mảng hai chiều $g[i][j]$, trong đó $g[i][j]$ cho biết chuỗi con $s[i..j]$ có phải là palindrome hay không.

Tiếp theo, chúng ta định nghĩa $f[i]$ là số lần cắt tối thiểu cần thiết cho chuỗi con $s[0..i-1]$. Ban đầu, $f[i] = i$.

Tiếp theo, chúng ta xét cách chuyển trạng thái cho $f[i]$. Chúng ta có thể duyệt qua điểm cắt trước đó $j$. Nếu chuỗi con $s[j..i]$ là palindrome, thì $f[i]$ có thể được chuyển từ $f[j]$. Nếu $j = 0$, điều đó có nghĩa là bản thân $s[0..i]$ là palindrome và không cần cắt, tức là $f[i] = 0$. Do đó, phương trình chuyển trạng thái như sau:

$$
f[i] = \min_{0 \leq j \leq i} \begin{cases} f[j-1] + 1, & \textit{if}\ g[j][i] = \textit{True} \\ 0, & \textit{if}\ g[0][i] = \textit{True} \end{cases}
$$

Đáp án là $f[n]$, trong đó $n$ là độ dài của chuỗi $s$.

Độ phức tạp thời gian là $O(n^2)$, và độ phức tạp không gian là $O(n^2)$. Ở đây, $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        g = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = s[i] == s[j] and g[i + 1][j - 1]
        f = list(range(n))
        for i in range(1, n):
            for j in range(i + 1):
                if g[j][i]:
                    f[i] = min(f[i], 1 + f[j - 1] if j else 0)
        return f[-1]
```

#### Java

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (var row : g) {
            Arrays.fill(row, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = i;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[j][i]) {
                    f[i] = Math.min(f[i], j > 0 ? 1 + f[j - 1] : 0);
                }
            }
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCut(string s) {
        int n = s.size();
        bool g[n][n];
        memset(g, true, sizeof(g));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s[i] == s[j] && g[i + 1][j - 1];
            }
        }
        int f[n];
        iota(f, f + n, 0);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[j][i]) {
                    f[i] = min(f[i], j ? 1 + f[j - 1] : 0);
                }
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func minCut(s string) int {
	n := len(s)
	g := make([][]bool, n)
	f := make([]int, n)
	for i := range g {
		g[i] = make([]bool, n)
		f[i] = i
		for j := range g[i] {
			g[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = s[i] == s[j] && g[i+1][j-1]
		}
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= i; j++ {
			if g[j][i] {
				if j == 0 {
					f[i] = 0
				} else {
					f[i] = min(f[i], f[j-1]+1)
				}
			}
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function minCut(s: string): number {
    const n = s.length;
    const g: boolean[][] = Array.from({ length: n }, () => Array(n).fill(true));
    for (let i = n - 1; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = s[i] === s[j] && g[i + 1][j - 1];
        }
    }
    const f: number[] = Array.from({ length: n }, (_, i) => i);
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j <= i; ++j) {
            if (g[j][i]) {
                f[i] = Math.min(f[i], j ? 1 + f[j - 1] : 0);
            }
        }
    }
    return f[n - 1];
}
```

#### C#

```cs
public class Solution {
    public int MinCut(string s) {
        int n = s.Length;
        bool[,] g = new bool[n,n];
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = i;
            for (int j = 0; j < n; ++j) {
                g[i,j] = true;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i,j] = s[i] == s[j] && g[i + 1,j - 1];
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[j,i]) {
                    f[i] = Math.Min(f[i], j > 0 ? 1 + f[j - 1] : 0);
                }
            }
        }
        return f[n - 1];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
