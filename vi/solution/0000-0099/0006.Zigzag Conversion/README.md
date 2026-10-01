---
comments: true
difficulty: Medium
tags:
    - String
---

<!-- problem:start -->

# [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion)

[中文文档](/solution/0000-0099/0006.Zigzag%20Conversion/README.md)

## Mô tả

<!-- description:start -->

<p>Chuỗi <code>&quot;PAYPALISHIRING&quot;</code> được viết theo mẫu zigzag trên số hàng cho trước như sau: (bạn có thể muốn hiển thị mẫu này bằng phông chữ cố định để dễ đọc hơn)</p>

<pre>
P   A   H   N
A P L S I I G
Y   I   R
</pre>

<p>Sau đó đọc theo từng dòng: <code>&quot;PAHNAPLSIIGYIR&quot;</code></p>

<p>Hãy viết code nhận vào một chuỗi và thực hiện phép chuyển đổi này với số hàng cho trước:</p>

<pre>
string convert(string s, int numRows);
</pre>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;PAYPALISHIRING&quot;, numRows = 3
<strong>Đầu ra:</strong> &quot;PAHNAPLSIIGYIR&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;PAYPALISHIRING&quot;, numRows = 4
<strong>Đầu ra:</strong> &quot;PINALSIGYAHRPI&quot;
<strong>Giải thích:</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;A&quot;, numRows = 1
<strong>Đầu ra:</strong> &quot;A&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> gồm các chữ cái tiếng Anh (chữ thường và chữ hoa), <code>&#39;,&#39;</code> và <code>&#39;.&#39;</code>.</li>
	<li><code>1 &lt;= numRows &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Việc vẽ lưới 2D rồi đọc theo hàng là đúng, nhưng phần lớn các ô đều trống. $n \le 10^3$ vẫn đáp ứng được, nhưng chiều “cột” không được sử dụng bị lãng phí.
>
> Chỉ hàng của mỗi ký tự và thứ tự bên trong một hàng mới ảnh hưởng đến đáp án. Chỉ số hàng đi theo $0,1,\ldots,\textit{numRows}-1$ rồi quay ngược lại, đổi hướng ở hàng đầu hoặc hàng cuối.
>
> Vì vậy, chúng ta giữ $\textit{numRows}$ danh sách và đảo hướng $k$ ở các biên. $k$ bắt đầu bằng $-1$, nên hàng đầu tiên lập tức chuyển hướng đi xuống. Khi $\textit{numRows}=1$, hàng đầu và hàng cuối là cùng một hàng; chúng ta phải trả về $s$ nguyên vẹn, nếu không hướng sẽ đổi qua đổi lại tại chỗ.

<!-- thinking:end -->

Chúng ta sử dụng một mảng 2D $g$ để mô phỏng quá trình sắp xếp chuỗi theo mẫu zigzag, trong đó $g[i][j]$ biểu diễn ký tự ở hàng $i$ và cột $j$. Ban đầu, $i = 0$. Chúng ta cũng định nghĩa một biến hướng $k$, ban đầu là $k = -1$, nghĩa là đang di chuyển lên.

Chúng ta duyệt chuỗi $s$ từ trái sang phải. Với mỗi ký tự $c$, chúng ta nối nó vào $g[i]$. Nếu $i = 0$ hoặc $i = \textit{numRows} - 1$, điều đó có nghĩa là ký tự hiện tại đang ở một điểm đổi hướng trong mẫu zigzag, vì vậy chúng ta đảo giá trị của $k$, tức là $k = -k$. Sau đó, chúng ta cập nhật $i$ thành $i + k$, nghĩa là di chuyển lên hoặc xuống một hàng. Tiếp tục duyệt ký tự tiếp theo cho đến hết chuỗi $s$. Cuối cùng, chúng ta trả về phép nối của tất cả các hàng trong $g$ làm kết quả.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của chuỗi $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            return s
        g = [[] for _ in range(numRows)]
        i, k = 0, -1
        for c in s:
            g[i].append(c)
            if i == 0 or i == numRows - 1:
                k = -k
            i += k
        return ''.join(chain(*g))
```

#### Java

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] g = new StringBuilder[numRows];
        Arrays.setAll(g, k -> new StringBuilder());
        int i = 0, k = -1;
        for (char c : s.toCharArray()) {
            g[i].append(c);
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        return String.join("", g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        vector<string> g(numRows);
        int i = 0, k = -1;
        for (char c : s) {
            g[i] += c;
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        string ans;
        for (auto& t : g) {
            ans += t;
        }
        return ans;
    }
};
```

#### Go

```go
func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	g := make([][]byte, numRows)
	i, k := 0, -1
	for _, c := range s {
		g[i] = append(g[i], byte(c))
		if i == 0 || i == numRows-1 {
			k = -k
		}
		i += k
	}
	return string(bytes.Join(g, nil))
}
```

#### TypeScript

```ts
function convert(s: string, numRows: number): string {
    if (numRows === 1) {
        return s;
    }
    const g: string[][] = new Array(numRows).fill(0).map(() => []);
    let i = 0;
    let k = -1;
    for (const c of s) {
        g[i].push(c);
        if (i === numRows - 1 || i === 0) {
            k = -k;
        }
        i += k;
    }
    return g.flat().join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        if num_rows == 1 {
            return s;
        }

        let num_rows = num_rows as usize;
        let mut g = vec![String::new(); num_rows];
        let mut i = 0;
        let mut k = -1;

        for c in s.chars() {
            g[i].push(c);
            if i == 0 || i == num_rows - 1 {
                k = -k;
            }
            i = (i as isize + k) as usize;
        }

        g.concat()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
    if (numRows === 1) {
        return s;
    }
    const g = new Array(numRows).fill(_).map(() => []);
    let i = 0;
    let k = -1;
    for (const c of s) {
        g[i].push(c);
        if (i === 0 || i === numRows - 1) {
            k = -k;
        }
        i += k;
    }
    return g.flat().join('');
};
```

#### C#

```cs
public class Solution {
    public string Convert(string s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.Length;
        StringBuilder[] g = new StringBuilder[numRows];
        for (int j = 0; j < numRows; ++j) {
            g[j] = new StringBuilder();
        }
        int i = 0, k = -1;
        foreach (char c in s.ToCharArray()) {
            g[i].Append(c);
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        StringBuilder ans = new StringBuilder();
        foreach (StringBuilder t in g) {
            ans.Append(t);
        }
        return ans.ToString();
    }
}
```

#### C

```c
char* convert(char* s, int numRows) {
    if (numRows == 1) {
        return strdup(s);
    }

    int len = strlen(s);
    char** g = (char**) malloc(numRows * sizeof(char*));
    int* idx = (int*) malloc(numRows * sizeof(int));
    for (int i = 0; i < numRows; ++i) {
        g[i] = (char*) malloc((len + 1) * sizeof(char));
        idx[i] = 0;
    }

    int i = 0, k = -1;
    for (int p = 0; p < len; ++p) {
        g[i][idx[i]++] = s[p];
        if (i == 0 || i == numRows - 1) {
            k = -k;
        }
        i += k;
    }

    char* ans = (char*) malloc((len + 1) * sizeof(char));
    int pos = 0;
    for (int r = 0; r < numRows; ++r) {
        for (int j = 0; j < idx[r]; ++j) {
            ans[pos++] = g[r][j];
        }
        free(g[r]);
    }
    ans[pos] = '\0';

    free(g);
    free(idx);
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @param Integer $numRows
     * @return String
     */
    function convert($s, $numRows) {
        if ($numRows == 1) {
            return $s;
        }

        $g = array_fill(0, $numRows, '');
        $i = 0;
        $k = -1;

        $length = strlen($s);
        for ($j = 0; $j < $length; $j++) {
            $c = $s[$j];
            $g[$i] .= $c;

            if ($i == 0 || $i == $numRows - 1) {
                $k = -$k;
            }

            $i += $k;
        }
        return implode('', $g);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
