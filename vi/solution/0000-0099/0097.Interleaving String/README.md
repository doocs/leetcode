---
comments: true
difficulty: Medium
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [97. Interleaving String](https://leetcode.com/problems/interleaving-string)

[中文文档](/solution/0000-0099/0097.Interleaving%20String/README.md)

## Mô tả

<!-- description:start -->

<p>Cho các chuỗi <code>s1</code>, <code>s2</code> và <code>s3</code>, hãy xác định liệu <code>s3</code> có được tạo thành bằng cách <strong>đan xen</strong> <code>s1</code> và <code>s2</code> hay không.</p>

<p>Phép <strong>đan xen</strong> hai chuỗi <code>s</code> và <code>t</code> là một cấu hình trong đó <code>s</code> và <code>t</code> lần lượt được chia thành <code>n</code> và <code>m</code> <span data-keyword="substring-nonempty">chuỗi con</span>, sao cho:</p>

<ul>
	<li><code>s = s<sub>1</sub> + s<sub>2</sub> + ... + s<sub>n</sub></code></li>
	<li><code>t = t<sub>1</sub> + t<sub>2</sub> + ... + t<sub>m</sub></code></li>
	<li><code>|n - m| &lt;= 1</code></li>
	<li>Phép <strong>đan xen</strong> là <code>s<sub>1</sub> + t<sub>1</sub> + s<sub>2</sub> + t<sub>2</sub> + s<sub>3</sub> + t<sub>3</sub> + ...</code> hoặc <code>t<sub>1</sub> + s<sub>1</sub> + t<sub>2</sub> + s<sub>2</sub> + t<sub>3</sub> + s<sub>3</sub> + ...</code></li>
</ul>

<p><strong>Lưu ý:</strong> <code>a + b</code> là phép nối các chuỗi <code>a</code> và <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0097.Interleaving%20String/images/interleave.jpg" style="width: 561px; height: 203px;" />
<pre>
<strong>Đầu vào:</strong> s1 = &quot;aabcc&quot;, s2 = &quot;dbbca&quot;, s3 = &quot;aadbbcbcac&quot;
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Một cách để thu được s3 là:
Chia s1 thành s1 = &quot;aa&quot; + &quot;bc&quot; + &quot;c&quot;, và s2 thành s2 = &quot;dbbc&quot; + &quot;a&quot;.
Đan xen hai phép chia, ta được &quot;aa&quot; + &quot;dbbc&quot; + &quot;bc&quot; + &quot;a&quot; + &quot;c&quot; = &quot;aadbbcbcac&quot;.
Vì có thể thu được s3 bằng cách đan xen s1 và s2, ta trả về true.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s1 = &quot;aabcc&quot;, s2 = &quot;dbbca&quot;, s3 = &quot;aadbbbaccc&quot;
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Hãy chú ý rằng không thể đan xen s2 với bất kỳ chuỗi nào khác để thu được s3.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s1 = &quot;&quot;, s2 = &quot;&quot;, s3 = &quot;&quot;
<strong>Đầu ra:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>0 &lt;= s3.length &lt;= 200</code></li>
	<li><code>s1</code>, <code>s2</code> và <code>s3</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán này chỉ bằng cách sử dụng thêm <code>O(s2.length)</code> không gian bộ nhớ không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm có ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Phép đan xen giữ nguyên thứ tự tương đối của $s_1$ và $s_2$, vì vậy trước hết độ dài phải khớp. Vét cạn chọn ký tự tiếp theo từ $s_1$ hoặc $s_2$ ở mỗi bước và số trường hợp tăng bùng nổ.
>
> Trạng thái chỉ là “đã sử dụng bao nhiêu ký tự của $s_1$ và $s_2$”: chỉ số của $s_3$ là $k=i+j$. Một lựa chọn hợp lệ khi $s_1[i]$ hoặc $s_2[j]$ bằng $s_3[k]$. Ghi nhớ $(i,j)$, ta có $O(mn)$ bài toán con.

<!-- thinking:end -->

Ta gọi độ dài của chuỗi $s_1$ là $m$ và độ dài của chuỗi $s_2$ là $n$. Nếu $m + n \neq |s_3|$, thì chắc chắn $s_3$ không phải là chuỗi đan xen của $s_1$ và $s_2$, nên ta trả về `false`.

Tiếp theo, ta thiết kế hàm $dfs(i, j)$, biểu thị liệu phần còn lại của $s_3$ có thể được đan xen từ ký tự thứ $i$ của $s_1$ và ký tự thứ $j$ của $s_2$ hay không. Đáp án là $dfs(0, 0)$.

Quá trình tính hàm $dfs(i, j)$ như sau:

Nếu $i \geq m$ và $j \geq n$, điều đó có nghĩa là cả $s_1$ và $s_2$ đều đã được duyệt hết, nên ta trả về `true`.

Nếu $i < m$ và $s_1[i] = s_3[i + j]$, điều đó có nghĩa là ký tự $s_1[i]$ là một phần của $s_3[i + j]$. Vì vậy, ta gọi đệ quy $dfs(i + 1, j)$ để kiểm tra xem ký tự tiếp theo của $s_1$ có thể khớp với ký tự hiện tại của $s_2$ hay không. Nếu có thể khớp, ta trả về `true`.

Tương tự, nếu $j < n$ và $s_2[j] = s_3[i + j]$, điều đó có nghĩa là ký tự $s_2[j]$ là một phần của $s_3[i + j]$. Vì vậy, ta gọi đệ quy $dfs(i, j + 1)$ để kiểm tra xem ký tự tiếp theo của $s_2$ có thể khớp với ký tự hiện tại của $s_1$ hay không. Nếu có thể khớp, ta trả về `true`.

Nếu không, ta trả về `false`.

Để tránh tính toán lặp lại, ta có thể sử dụng tìm kiếm có ghi nhớ.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là độ dài của các chuỗi $s_1$ và $s_2$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        @cache
        def dfs(i: int, j: int) -> bool:
            if i >= m and j >= n:
                return True
            k = i + j
            if i < m and s1[i] == s3[k] and dfs(i + 1, j):
                return True
            if j < n and s2[j] == s3[k] and dfs(i, j + 1):
                return True
            return False

        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Map<List<Integer>, Boolean> f = new HashMap<>();
    private String s1;
    private String s2;
    private String s3;
    private int m;
    private int n;

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (i >= m && j >= n) {
            return true;
        }
        var key = List.of(i, j);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int k = i + j;
        boolean ans = false;
        if (i < m && s1.charAt(i) == s3.charAt(k) && dfs(i + 1, j)) {
            ans = true;
        }
        if (!ans && j < n && s2.charAt(j) == s3.charAt(k) && dfs(i, j + 1)) {
            ans = true;
        }
        f.put(key, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        vector<vector<int>> f(m + 1, vector<int>(n + 1, -1));
        function<bool(int, int)> dfs = [&](int i, int j) {
            if (i >= m && j >= n) {
                return true;
            }
            if (f[i][j] != -1) {
                return f[i][j] == 1;
            }
            f[i][j] = 0;
            int k = i + j;
            if (i < m && s1[i] == s3[k] && dfs(i + 1, j)) {
                f[i][j] = 1;
            }
            if (!f[i][j] && j < n && s2[j] == s3[k] && dfs(i, j + 1)) {
                f[i][j] = 1;
            }
            return f[i][j] == 1;
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}

	f := map[int]bool{}
	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		if i >= m && j >= n {
			return true
		}
		if v, ok := f[i*200+j]; ok {
			return v
		}
		k := i + j
		f[i*200+j] = (i < m && s1[i] == s3[k] && dfs(i+1, j)) || (j < n && s2[j] == s3[k] && dfs(i, j+1))
		return f[i*200+j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: number[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    const dfs = (i: number, j: number): boolean => {
        if (i >= m && j >= n) {
            return true;
        }
        if (f[i][j]) {
            return f[i][j] === 1;
        }
        f[i][j] = -1;
        if (i < m && s1[i] === s3[i + j] && dfs(i + 1, j)) {
            f[i][j] = 1;
        }
        if (f[i][j] === -1 && j < n && s2[j] === s3[i + j] && dfs(i, j + 1)) {
            f[i][j] = 1;
        }
        return f[i][j] === 1;
    };
    return dfs(0, 0);
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn is_interleave(s1: String, s2: String, s3: String) -> bool {
        let n = s1.len();
        let m = s2.len();

        if s1.len() + s2.len() != s3.len() {
            return false;
        }

        let mut record = vec![vec![-1; m + 1]; n + 1];

        Self::dfs(
            &mut record,
            n,
            m,
            0,
            0,
            &s1.chars().collect(),
            &s2.chars().collect(),
            &s3.chars().collect(),
        )
    }

    #[allow(dead_code)]
    fn dfs(
        record: &mut Vec<Vec<i32>>,
        n: usize,
        m: usize,
        i: usize,
        j: usize,
        s1: &Vec<char>,
        s2: &Vec<char>,
        s3: &Vec<char>,
    ) -> bool {
        if i >= n && j >= m {
            return true;
        }

        if record[i][j] != -1 {
            return record[i][j] == 1;
        }

        // Set the initial value
        record[i][j] = 0;
        let k = i + j;

        // Let's try `s1` first
        if i < n && s1[i] == s3[k] && Self::dfs(record, n, m, i + 1, j, s1, s2, s3) {
            record[i][j] = 1;
        }

        // If the first approach does not succeed, let's then try `s2`
        if record[i][j] == 0
            && j < m
            && s2[j] == s3[k]
            && Self::dfs(record, n, m, i, j + 1, s1, s2, s3)
        {
            record[i][j] = 1;
        }

        record[i][j] == 1
    }
}
```

#### C#

```cs
public class Solution {
    private int m;
    private int n;
    private string s1;
    private string s2;
    private string s3;
    private int[,] f;

    public bool IsInterleave(string s1, string s2, string s3) {
        m = s1.Length;
        n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        f = new int[m + 1, n + 1];
        return dfs(0, 0);
    }

    private bool dfs(int i, int j) {
        if (i >= m && j >= n) {
            return true;
        }
        if (f[i, j] != 0) {
            return f[i, j] == 1;
        }
        f[i, j] = -1;
        if (i < m && s1[i] == s3[i + j] && dfs(i + 1, j)) {
            f[i, j] = 1;
        }
        if (f[i, j] == -1 && j < n && s2[j] == s3[i + j] && dfs(i, j + 1)) {
            f[i, j] = 1;
        }
        return f[i, j] == 1;
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
> Lời giải 1 đã có phép chuyển trạng thái. Theo hướng từ dưới lên, $f[i][j]$ biểu thị liệu $i$ ký tự đầu tiên của $s_1$ và $j$ ký tự đầu tiên của $s_2$ có thể tạo thành $i+j$ ký tự đầu tiên của $s_3$ hay không. Trạng thái rỗng-rỗng là đúng; điền theo thứ tự tăng dần của $i,j$ sẽ loại bỏ ngăn xếp đệ quy.

<!-- thinking:end -->

Ta có thể chuyển phép tìm kiếm có ghi nhớ trong Lời giải 1 thành quy hoạch động.

Ta định nghĩa $f[i][j]$ biểu thị liệu $i$ ký tự đầu tiên của chuỗi $s_1$ và $j$ ký tự đầu tiên của chuỗi $s_2$ có thể đan xen để tạo thành $i + j$ ký tự đầu tiên của chuỗi $s_3$ hay không. Khi chuyển trạng thái, ta có thể xét liệu ký tự hiện tại được lấy từ ký tự cuối cùng của $s_1$ hay ký tự cuối cùng của $s_2$. Do đó, ta có công thức chuyển trạng thái:

$$
f[i][j] = \begin{cases}
f[i - 1][j] & \textit{if } s_1[i - 1] = s_3[i + j - 1] \\
\textit{or } f[i][j - 1] & \textit{if } s_2[j - 1] = s_3[i + j - 1] \\
\textit{false} & \textit{otherwise}
\end{cases}
$$

trong đó $f[0][0] = \textit{true}$ biểu thị rằng chuỗi rỗng là một chuỗi đan xen của hai chuỗi rỗng.

Đáp án là $f[m][n]$.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó, $m$ và $n$ lần lượt là độ dài của các chuỗi $s_1$ và $s_2$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        f = [[False] * (n + 1) for _ in range(m + 1)]
        f[0][0] = True
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i and s1[i - 1] == s3[k]:
                    f[i][j] = f[i - 1][j]
                if j and s2[j - 1] == s3[k]:
                    f[i][j] |= f[i][j - 1]
        return f[m][n]
```

#### Java

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(k)) {
                    f[i][j] = f[i - 1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(k)) {
                    f[i][j] |= f[i][j - 1];
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
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        bool f[m + 1][n + 1];
        memset(f, false, sizeof(f));
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1[i - 1] == s3[k]) {
                    f[i][j] = f[i - 1][j];
                }
                if (j > 0 && s2[j - 1] == s3[k]) {
                    f[i][j] |= f[i][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	f := make([][]bool, m+1)
	for i := range f {
		f[i] = make([]bool, n+1)
	}
	f[0][0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 && s1[i-1] == s3[k] {
				f[i][j] = f[i-1][j]
			}
			if j > 0 && s2[j-1] == s3[k] {
				f[i][j] = (f[i][j] || f[i][j-1])
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: boolean[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(false));
    f[0][0] = true;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            const k = i + j - 1;
            if (i > 0 && s1[i - 1] === s3[k]) {
                f[i][j] = f[i - 1][j];
            }
            if (j > 0 && s2[j - 1] === s3[k]) {
                f[i][j] = f[i][j] || f[i][j - 1];
            }
        }
    }
    return f[m][n];
}
```

#### C#

```cs
public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        int m = s1.Length, n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        bool[,] f = new bool[m + 1, n + 1];
        f[0, 0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1[i - 1] == s3[k]) {
                    f[i, j] = f[i - 1, j];
                }
                if (j > 0 && s2[j - 1] == s3[k]) {
                    f[i, j] |= f[i, j - 1];
                }
            }
        }
        return f[m, n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Tối ưu hóa bằng mảng cuộn

<!-- thinking:start -->

> **Tư duy**
>
> Câu hỏi mở rộng yêu cầu thêm không gian $O(|s_2|)$. $f[i][j]$ chỉ phụ thuộc vào hàng trước đó và ô bên trái, vì vậy một mảng cuộn là đủ — đúng với giới hạn $O(n)$ trong đề bài.

<!-- thinking:end -->

Ta nhận thấy $f[i][j]$ chỉ liên quan đến $f[i - 1][j]$ và $f[i][j - 1]$. Vì vậy, ta có thể sử dụng một mảng cuộn để giảm độ phức tạp không gian xuống $O(n)$. Độ phức tạp thời gian vẫn là $O(m \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        if m + n != len(s3):
            return False
        f = [True] + [False] * n
        for i in range(m + 1):
            for j in range(n + 1):
                k = i + j - 1
                if i:
                    f[j] &= s1[i - 1] == s3[k]
                if j:
                    f[j] |= f[j - 1] and s2[j - 1] == s3[k]
        return f[n]
```

#### Java

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[j] &= s1.charAt(i - 1) == s3.charAt(k);
                }
                if (j > 0) {
                    f[j] |= (f[j - 1] & s2.charAt(j - 1) == s3.charAt(k));
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
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) {
            return false;
        }
        bool f[n + 1];
        memset(f, false, sizeof(f));
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i) {
                    f[j] &= s1[i - 1] == s3[k];
                }
                if (j) {
                    f[j] |= (s2[j - 1] == s3[k] && f[j - 1]);
                }
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}
	f := make([]bool, n+1)
	f[0] = true
	for i := 0; i <= m; i++ {
		for j := 0; j <= n; j++ {
			k := i + j - 1
			if i > 0 {
				f[j] = (f[j] && s1[i-1] == s3[k])
			}
			if j > 0 {
				f[j] = (f[j] || (s2[j-1] == s3[k] && f[j-1]))
			}
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: boolean[] = new Array(n + 1).fill(false);
    f[0] = true;
    for (let i = 0; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            const k = i + j - 1;
            if (i) {
                f[j] = f[j] && s1[i - 1] === s3[k];
            }
            if (j) {
                f[j] = f[j] || (f[j - 1] && s2[j - 1] === s3[k]);
            }
        }
    }
    return f[n];
}
```

#### C#

```cs
public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        int m = s1.Length, n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        bool[] f = new bool[n + 1];
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[j] &= s1[i - 1] == s3[k];
                }
                if (j > 0) {
                    f[j] |= (f[j - 1] & s2[j - 1] == s3[k]);
                }
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
