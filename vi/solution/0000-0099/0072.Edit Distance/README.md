---
comments: true
difficulty: Medium
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [72. Edit Distance](https://leetcode.com/problems/edit-distance)

[中文文档](/solution/0000-0099/0072.Edit%20Distance/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai chuỗi <code>word1</code> và <code>word2</code>, hãy trả về <em>số thao tác tối thiểu cần thiết để chuyển <code>word1</code> thành <code>word2</code></em>.</p>

<p>Bạn được phép thực hiện ba thao tác sau trên một từ:</p>

<ul>
	<li>Chèn một ký tự</li>
	<li>Xóa một ký tự</li>
	<li>Thay thế một ký tự</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> word1 = &quot;horse&quot;, word2 = &quot;ros&quot;
<strong>Đầu ra:</strong> 3
<strong>Giải thích:</strong> 
horse -&gt; rorse (thay thế &#39;h&#39; bằng &#39;r&#39;)
rorse -&gt; rose (xóa &#39;r&#39;)
rose -&gt; ros (xóa &#39;e&#39;)
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> word1 = &quot;intention&quot;, word2 = &quot;execution&quot;
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> 
intention -&gt; inention (xóa &#39;t&#39;)
inention -&gt; enention (thay thế &#39;i&#39; bằng &#39;e&#39;)
enention -&gt; exention (thay thế &#39;n&#39; bằng &#39;x&#39;)
exention -&gt; exection (thay thế &#39;n&#39; bằng &#39;c&#39;)
exection -&gt; execution (chèn &#39;u&#39;)
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= word1.length, word2.length &lt;= 500</code></li>
	<li><code>word1</code> và <code>word2</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đệ quy: tại mỗi vị trí của $word1$, thử chèn, xóa hoặc thay thế, sau đó chọn kết quả tốt nhất. Cách này đúng, nhưng có độ phức tạp hàm mũ. Với $m,n \le 500$, ta cần một cách có độ phức tạp đa thức.
>
> Điểm nghẽn là các bài toán con chồng lấp — việc chuyển tiền tố $i$ thành tiền tố $j$ được giải nhiều lần. Gọi $f[i][j]$ là số thao tác tối thiểu đó. Nếu các ký tự cuối giống nhau, ta kế thừa giá trị trên đường chéo; nếu không, ba thao tác tương ứng với ba ô lân cận, rồi lấy $\min$ cộng một. Các biên là chuỗi rỗng cần xóa hết / chèn hết. Đáp án là $f[m][n]$.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i][j]$ là số thao tác tối thiểu để chuyển $word1$ có độ dài $i$ thành $word2$ có độ dài $j$. $f[i][0] = i$, $f[0][j] = j$, $i \in [1, m], j \in [0, n]$.

Xét $f[i][j]$:

- Nếu $word1[i - 1] = word2[j - 1]$, thì ta chỉ cần xét số thao tác tối thiểu để chuyển $word1$ có độ dài $i - 1$ thành $word2$ có độ dài $j - 1$, do đó $f[i][j] = f[i - 1][j - 1]$;
- Nếu không, ta có thể xét các thao tác chèn, xóa và thay thế, khi đó $f[i][j] = \min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1$.

Cuối cùng, ta nhận được phương trình chuyển trạng thái:

$$
f[i][j] = \begin{cases}
i, & \textit{if } j = 0 \\
j, & \textit{if } i = 0 \\
f[i - 1][j - 1], & \textit{if } word1[i - 1] = word2[j - 1] \\
\min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1, & \textit{otherwise}
\end{cases}
$$

Cuối cùng, ta trả về $f[m][n]$.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. $m$ và $n$ lần lượt là độ dài của $word1$ và $word2$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for j in range(1, n + 1):
            f[0][j] = j
        for i, a in enumerate(word1, 1):
            f[i][0] = i
            for j, b in enumerate(word2, 1):
                if a == b:
                    f[i][j] = f[i - 1][j - 1]
                else:
                    f[i][j] = min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1
        return f[m][n]
```

#### Java

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int j = 1; j <= n; ++j) {
            f[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            f[i][0] = i;
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i - 1][j], Math.min(f[i][j - 1], f[i - 1][j - 1])) + 1;
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
    int minDistance(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        int f[m + 1][n + 1];
        for (int j = 0; j <= n; ++j) {
            f[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            f[i][0] = i;
            for (int j = 1; j <= n; ++j) {
                if (word1[i - 1] == word2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = min({f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]}) + 1;
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func minDistance(word1 string, word2 string) int {
	m, n := len(word1), len(word2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for j := 1; j <= n; j++ {
		f[0][j] = j
	}
	for i := 1; i <= m; i++ {
		f[i][0] = i
		for j := 1; j <= n; j++ {
			if word1[i-1] == word2[j-1] {
				f[i][j] = f[i-1][j-1]
			} else {
				f[i][j] = min(f[i-1][j], min(f[i][j-1], f[i-1][j-1])) + 1
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function minDistance(word1: string, word2: string): number {
    const m = word1.length;
    const n = word2.length;
    const f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let j = 1; j <= n; ++j) {
        f[0][j] = j;
    }
    for (let i = 1; i <= m; ++i) {
        f[i][0] = i;
        for (let j = 1; j <= n; ++j) {
            if (word1[i - 1] === word2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1;
            }
        }
    }
    return f[m][n];
}
```

#### JavaScript

```js
/**
 * @param {string} word1
 * @param {string} word2
 * @return {number}
 */
var minDistance = function (word1, word2) {
    const m = word1.length;
    const n = word2.length;
    const f = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let j = 1; j <= n; ++j) {
        f[0][j] = j;
    }
    for (let i = 1; i <= m; ++i) {
        f[i][0] = i;
        for (let j = 1; j <= n; ++j) {
            if (word1[i - 1] === word2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1;
            }
        }
    }
    return f[m][n];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
