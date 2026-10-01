---
comments: true
difficulty: Hard
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [87. Scramble String](https://leetcode.com/problems/scramble-string)

[中文文档](/solution/0000-0099/0087.Scramble%20String/README.md)

## Mô tả

<!-- description:start -->

<p>Chúng ta có thể xáo trộn một chuỗi s để nhận được chuỗi t bằng thuật toán sau:</p>

<ol>
	<li>Nếu độ dài của chuỗi là 1, dừng lại.</li>
	<li>Nếu độ dài của chuỗi &gt; 1, thực hiện các bước sau:
	<ul>
		<li>Chia chuỗi thành hai chuỗi con không rỗng tại một chỉ số ngẫu nhiên, tức là nếu chuỗi là <code>s</code>, chia nó thành <code>x</code> và <code>y</code> sao cho <code>s = x + y</code>.</li>
		<li><strong>Ngẫu nhiên</strong>&nbsp;quyết định đổi chỗ hai chuỗi con hoặc giữ nguyên thứ tự của chúng. Tức là sau bước này, <code>s</code> có thể trở thành <code>s = x + y</code> hoặc <code>s = y + x</code>.</li>
		<li>Áp dụng đệ quy bước 1 cho mỗi chuỗi con <code>x</code> và <code>y</code>.</li>
	</ul>
	</li>
</ol>

<p>Cho hai chuỗi <code>s1</code> và <code>s2</code> có <strong>cùng độ dài</strong>, hãy trả về <code>true</code> nếu <code>s2</code> là chuỗi xáo trộn của <code>s1</code>, ngược lại trả về <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s1 = &quot;great&quot;, s2 = &quot;rgeat&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Một kịch bản có thể áp dụng trên s1 là:
&quot;great&quot; --&gt; &quot;gr/eat&quot; // chia tại chỉ số ngẫu nhiên.
&quot;gr/eat&quot; --&gt; &quot;gr/eat&quot; // quyết định ngẫu nhiên là không đổi chỗ hai chuỗi con và giữ nguyên thứ tự của chúng.
&quot;gr/eat&quot; --&gt; &quot;g/r / e/at&quot; // áp dụng đệ quy cùng thuật toán cho cả hai chuỗi con. chia tại chỉ số ngẫu nhiên của mỗi chuỗi.
&quot;g/r / e/at&quot; --&gt; &quot;r/g / e/at&quot; // quyết định ngẫu nhiên là đổi chỗ chuỗi con đầu tiên và giữ nguyên thứ tự của chuỗi con thứ hai.
&quot;r/g / e/at&quot; --&gt; &quot;r/g / e/ a/t&quot; // tiếp tục áp dụng thuật toán đệ quy, chia &quot;at&quot; thành &quot;a/t&quot;.
&quot;r/g / e/ a/t&quot; --&gt; &quot;r/g / e/ a/t&quot; // quyết định ngẫu nhiên là giữ nguyên thứ tự của cả hai chuỗi con.
Thuật toán dừng lại tại đây, và chuỗi kết quả là &quot;rgeat&quot;, chính là s2.
Vì một kịch bản có thể đã xáo trộn s1 thành s2, chúng ta trả về true.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s1 = &quot;abcde&quot;, s2 = &quot;caebd&quot;
<strong>Đầu ra:</strong> false
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s1 = &quot;a&quot;, s2 = &quot;a&quot;
<strong>Đầu ra:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>s1.length == s2.length</code></li>
	<li><code>1 &lt;= s1.length &lt;= 30</code></li>
	<li><code>s1</code> và <code>s2</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm có ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đệ quy theo định nghĩa: thử mọi cách chia, đổi chỗ hoặc không đổi chỗ, rồi đệ quy cho cả hai phía. Cách này đúng, nhưng cùng một cặp chuỗi con bị xét nhiều lần. Với $n \le 30$, số trường hợp sẽ bùng nổ nếu không có memo.
>
> Nút thắt nằm ở các bài toán con chồng lấp. Trạng thái chỉ là “$s_1[i:i+k]$ có thể xáo trộn thành $s_2[j:j+k]$ hay không.”
>
> Một phép xáo trộn chỉ quyết định đổi chỗ hoặc không đổi chỗ sau khi chia. Ta liệt kê độ dài phần chia $h$: hoặc hai phần nằm đúng vị trí tương ứng, hoặc chúng giao chéo nhau. Lưu kết quả của $dfs(i, j, k)$: có $O(n^3)$ trạng thái, mỗi trạng thái thử $O(n)$ cách chia, nên tổng thời gian là $O(n^4)$.

<!-- thinking:end -->

Chúng ta xây dựng một hàm $dfs(i, j, k)$, biểu thị liệu chuỗi con bắt đầu từ $i$ với độ dài $k$ trong $s_1$ có thể được chuyển thành chuỗi con bắt đầu từ $j$ với độ dài $k$ trong $s_2$ hay không. Nếu có thể chuyển đổi, trả về `true`, ngược lại trả về `false`. Đáp án là $dfs(0, 0, n)$, trong đó $n$ là độ dài của chuỗi.

Cách tính hàm $dfs(i, j, k)$ như sau:

- Nếu $k=1$, chúng ta chỉ cần kiểm tra xem $s_1[i]$ và $s_2[j]$ có bằng nhau hay không. Nếu bằng nhau, trả về `true`, ngược lại trả về `false`;
- Nếu $k \gt 1$, chúng ta liệt kê độ dài của phần được chia $h$, khi đó có hai trường hợp: nếu hai chuỗi con sau khi chia không bị đổi chỗ, thì đó là $dfs(i, j, h) \land dfs(i+h, j+h, k-h)$; nếu hai chuỗi con sau khi chia bị đổi chỗ, thì đó là $dfs(i, j+k-h, h) \land dfs(i+h, j, k-h)$. Nếu một trong hai trường hợp đúng, thì $dfs(i, j, k)$ là true, trả về `true`, ngược lại trả về `false`.

Cuối cùng, chúng ta trả về $dfs(0, 0, n)$.

Để tránh tính toán lặp lại, chúng ta có thể sử dụng tìm kiếm có ghi nhớ.

Độ phức tạp thời gian là $O(n^4)$, và độ phức tạp không gian là $O(n^3)$. Trong đó $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        @cache
        def dfs(i: int, j: int, k: int) -> bool:
            if k == 1:
                return s1[i] == s2[j]
            for h in range(1, k):
                if dfs(i, j, h) and dfs(i + h, j + h, k - h):
                    return True
                if dfs(i + h, j, k - h) and dfs(i, j + k - h, h):
                    return True
            return False

        return dfs(0, 0, len(s1))
```

#### Java

```java
class Solution {
    private Boolean[][][] f;
    private String s1;
    private String s2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        f = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }

    private boolean dfs(int i, int j, int k) {
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return f[i][j][k] = true;
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return f[i][j][k] = true;
            }
        }
        return f[i][j][k] = false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isScramble(string s1, string s2) {
        int n = s1.size();
        int f[n][n][n + 1];
        memset(f, -1, sizeof(f));
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (f[i][j][k] != -1) {
                return f[i][j][k] == 1;
            }
            if (k == 1) {
                return s1[i] == s2[j];
            }
            for (int h = 1; h < k; ++h) {
                if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                    return f[i][j][k] = true;
                }
                if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                    return f[i][j][k] = true;
                }
            }
            return f[i][j][k] = false;
        };
        return dfs(0, 0, n);
    }
};
```

#### Go

```go
func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n+1)
		}
	}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if k == 1 {
			return s1[i] == s2[j]
		}
		if f[i][j][k] != 0 {
			return f[i][j][k] == 1
		}
		f[i][j][k] = 2
		for h := 1; h < k; h++ {
			if (dfs(i, j, h) && dfs(i+h, j+h, k-h)) || (dfs(i+h, j, k-h) && dfs(i, j+k-h, h)) {
				f[i][j][k] = 1
				return true
			}
		}
		return false
	}
	return dfs(0, 0, n)
}
```

#### TypeScript

```ts
function isScramble(s1: string, s2: string): boolean {
    const n = s1.length;
    const f = new Array(n)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n + 1).fill(-1)));
    const dfs = (i: number, j: number, k: number): boolean => {
        if (f[i][j][k] !== -1) {
            return f[i][j][k] === 1;
        }
        if (k === 1) {
            return s1[i] === s2[j];
        }
        for (let h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return Boolean((f[i][j][k] = 1));
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return Boolean((f[i][j][k] = 1));
            }
        }
        return Boolean((f[i][j][k] = 0));
    };
    return dfs(0, 0, n);
}
```

#### C#

```cs
public class Solution {
    private string s1;
    private string s2;
    private int[,,] f;

    public bool IsScramble(string s1, string s2) {
        int n = s1.Length;
        this.s1 = s1;
        this.s2 = s2;
        f = new int[n, n, n + 1];
        return dfs(0, 0, n);
    }

    private bool dfs(int i, int j, int k) {
        if (f[i, j, k] != 0) {
            return f[i, j, k] == 1;
        }
        if (k == 1) {
            return s1[i] == s2[j];
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                f[i, j, k] = 1;
                return true;
            }
            if (dfs(i, j + k - h, h) && dfs(i + h, j, k - h)) {
                f[i, j, k] = 1;
                return true;
            }
        }
        f[i, j, k] = -1;
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động (DP trên đoạn)

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã có độ phức tạp $O(n^4)$, nhưng đệ quy phải trả giá bằng stack lời gọi và các hằng số của cache. Với $n = 30$, cả hai yếu tố này đều gây ảnh hưởng.
>
> Điều còn thiếu là viết cùng phép chuyển trạng thái theo hướng từ dưới lên. $f[i][j][k]$ là dạng bảng của $dfs(i, j, k)$: điền các độ dài $1$ trước, sau đó tăng dần $k$. Độ phức tạp tiệm cận giữ nguyên, nhưng các hằng số ổn định hơn.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i][j][k]$ là liệu chuỗi con có độ dài $k$ bắt đầu từ $i$ của chuỗi $s_1$ có thể được chuyển thành chuỗi con có độ dài $k$ bắt đầu từ $j$ của chuỗi $s_2$ hay không. Khi đó đáp án là $f[0][0][n]$, trong đó $n$ là độ dài của chuỗi.

Với chuỗi con có độ dài $1$, nếu $s_1[i] = s_2[j]$, thì $f[i][j][1] = true$, ngược lại $f[i][j][1] = false$.

Tiếp theo, chúng ta liệt kê độ dài $k$ của chuỗi con từ nhỏ đến lớn, liệt kê $i$ từ $0$, rồi liệt kê $j$ từ $0$. Nếu $f[i][j][h] \land f[i + h][j + h][k - h]$ hoặc $f[i][j + k - h][h] \land f[i + h][j][k - h]$ là true, thì $f[i][j][k]$ cũng true.

Cuối cùng, chúng ta trả về $f[0][0][n]$.

Độ phức tạp thời gian là $O(n^4)$, và độ phức tạp không gian là $O(n^3)$. Trong đó $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        n = len(s1)
        f = [[[False] * (n + 1) for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                f[i][j][1] = s1[i] == s2[j]
        for k in range(2, n + 1):
            for i in range(n - k + 1):
                for j in range(n - k + 1):
                    for h in range(1, k):
                        if f[i][j][h] and f[i + h][j + h][k - h]:
                            f[i][j][k] = True
                            break
                        if f[i + h][j][k - h] and f[i][j + k - h][h]:
                            f[i][j][k] = True
                            break
        return f[0][0][n]
```

#### Java

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if (f[i][j][h] && f[i + h][j + h][k - h]) {
                            f[i][j][k] = true;
                            break;
                        }
                        if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isScramble(string s1, string s2) {
        int n = s1.length();
        bool f[n][n][n + 1];
        memset(f, false, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j][1] = s1[i] == s2[j];
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if () {
                            f[i][j][k] = true;
                            break;
                        }
                        if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
};
```

#### Go

```go
func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	f := make([][][]bool, n)
	for i := range f {
		f[i] = make([][]bool, n)
		for j := 0; j < n; j++ {
			f[i][j] = make([]bool, n+1)
			f[i][j][1] = s1[i] == s2[j]
		}
	}
	for k := 2; k <= n; k++ {
		for i := 0; i <= n-k; i++ {
			for j := 0; j <= n-k; j++ {
				for h := 1; h < k; h++ {
					if (f[i][j][h] && f[i+h][j+h][k-h]) || (f[i+h][j][k-h] && f[i][j+k-h][h]) {
						f[i][j][k] = true
						break
					}
				}
			}
		}
	}
	return f[0][0][n]
}
```

#### TypeScript

```ts
function isScramble(s1: string, s2: string): boolean {
    const n = s1.length;
    const f = new Array(n)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n + 1).fill(false)));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            f[i][j][1] = s1[i] === s2[j];
        }
    }
    for (let k = 2; k <= n; ++k) {
        for (let i = 0; i <= n - k; ++i) {
            for (let j = 0; j <= n - k; ++j) {
                for (let h = 1; h < k; ++h) {
                    if (f[i][j][h] && f[i + h][j + h][k - h]) {
                        f[i][j][k] = true;
                        break;
                    }
                    if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                        f[i][j][k] = true;
                        break;
                    }
                }
            }
        }
    }
    return f[0][0][n];
}
```

#### C#

```cs
public class Solution {
    public bool IsScramble(string s1, string s2) {
        int n = s1.Length;
        bool[,,] f = new bool[n, n, n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++ j) {
                f[i, j, 1] = s1[i] == s2[j];
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if (f[i, j, h] && f[i + h, j + h, k - h]) {
                            f[i, j, k] = true;
                            break;
                        }
                        if (f[i, j + k - h, h] && f[i + h, j, k - h]) {
                            f[i, j, k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0, 0, n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
