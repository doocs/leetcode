---
comments: true
difficulty: Medium
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [91. Decode Ways](https://leetcode.com/problems/decode-ways)

[中文文档](/solution/0000-0099/0091.Decode%20Ways/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn đã chặn được một thông điệp bí mật được mã hóa dưới dạng một chuỗi các chữ số. Thông điệp được <strong>giải mã</strong> theo ánh xạ sau:</p>

<p><code>&quot;1&quot; -&gt; &#39;A&#39;<br />
&quot;2&quot; -&gt; &#39;B&#39;<br />
...<br />
&quot;25&quot; -&gt; &#39;Y&#39;<br />
&quot;26&quot; -&gt; &#39;Z&#39;</code></p>

<p>Tuy nhiên, khi giải mã thông điệp, bạn nhận ra rằng có nhiều cách khác nhau để giải mã thông điệp vì một số mã nằm trong các mã khác (<code>&quot;2&quot;</code> và <code>&quot;5&quot;</code> so với <code>&quot;25&quot;</code>).</p>

<p>Ví dụ, <code>&quot;11106&quot;</code> có thể được giải mã thành:</p>

<ul>
	<li><code>&quot;AAJF&quot;</code> với cách nhóm <code>(1, 1, 10, 6)</code></li>
	<li><code>&quot;KJF&quot;</code> với cách nhóm <code>(11, 10, 6)</code></li>
	<li>Cách nhóm <code>(1, 11, 06)</code> không hợp lệ vì <code>&quot;06&quot;</code> không phải là mã hợp lệ (chỉ <code>&quot;6&quot;</code> là hợp lệ).</li>
</ul>

<p>Lưu ý: có thể có những chuỗi không thể giải mã.<br />
<br />
Cho một chuỗi s chỉ chứa các chữ số, hãy trả về <strong>số cách</strong> để <strong>giải mã</strong> chuỗi đó. Nếu không thể giải mã toàn bộ chuỗi theo bất kỳ cách hợp lệ nào, hãy trả về <code>0</code>.</p>

<p>Các ca kiểm thử được tạo sao cho đáp án vừa với một số nguyên <strong>32-bit</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;12&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">2</span></p>

<p><strong>Giải thích:</strong></p>

<p>&quot;12&quot; có thể được giải mã thành &quot;AB&quot; (1 2) hoặc &quot;L&quot; (12).</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;226&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">3</span></p>

<p><strong>Giải thích:</strong></p>

<p>&quot;226&quot; có thể được giải mã thành &quot;BZ&quot; (2 26), &quot;VF&quot; (22 6), hoặc &quot;BBF&quot; (2 2 6).</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">s = &quot;06&quot;</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">0</span></p>

<p><strong>Giải thích:</strong></p>

<p>&quot;06&quot; không thể được ánh xạ thành &quot;F&quot; vì có số 0 đứng đầu (&quot;6&quot; khác với &quot;06&quot;). Trong trường hợp này, chuỗi không phải là một mã hóa hợp lệ, vì vậy hãy trả về 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> chỉ chứa các chữ số và có thể chứa một hoặc nhiều số 0 đứng đầu.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là đệ quy: tại chỉ số $i$, giải mã riêng $s[i]$ (nếu khác $0$), hoặc ghép nó với chữ số trước đó thành một số trong khoảng $10$– $26$. $n \le 100$, nhưng nếu không ghi nhớ kết quả thì cây đệ quy có cấp số mũ vì cùng một tiền tố bị giải mã lặp đi lặp lại.
>
> Nút thắt nằm ở chỗ số cách giải mã $i$ ký tự đầu tiên chỉ phụ thuộc vào các tiền tố ngắn hơn, và một cách tách chỉ có thể là “một chữ số / hai chữ số”. Đây chính là bài toán leo cầu thang với các ràng buộc về mã hóa.
>
> Vì vậy, đặt $f[i]$ là số cách của $i$ ký tự đầu tiên. Chuỗi rỗng có một cách; $0$ không thể đứng riêng; chỉ các số từ $10$ đến $26$ mới có thể tạo thành một cặp. Ta duyệt một lần thay vì liệt kê mọi cách phân hoạch.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i]$ biểu diễn số cách giải mã $i$ ký tự đầu tiên của chuỗi. Ban đầu, $f[0]=1$, còn các giá trị $f[i]=0$.

Hãy xét cách chuyển trạng thái của $f[i]$.

- Nếu ký tự thứ $i$ (tức là $s[i-1]$) tự nó tạo thành một mã, nó tương ứng với một cách giải mã, tức là $f[i]=f[i-1]$. Điều kiện là $s[i-1] \neq 0$.
- Nếu chuỗi tạo bởi ký tự thứ $i-1$ và ký tự thứ $i$ nằm trong khoảng $[1,26]$, thì chúng có thể được xem như một thể thống nhất, tương ứng với một cách giải mã, tức là $f[i] = f[i] + f[i-2]$. Điều kiện là $s[i-2] \neq 0$, và $s[i-2]s[i-1]$ nằm trong khoảng $[1,26]$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)
        f = [1] + [0] * n
        for i, c in enumerate(s, 1):
            if c != "0":
                f[i] = f[i - 1]
            if i > 1 and s[i - 2] != "0" and int(s[i - 2 : i]) <= 26:
                f[i] += f[i - 2]
        return f[n]
```

#### Java

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                f[i] += f[i - 2];
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
    int numDecodings(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] <= '6')) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func numDecodings(s string) int {
	n := len(s)
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		if s[i-1] != '0' {
			f[i] = f[i-1]
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			f[i] += f[i-2]
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function numDecodings(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        if (s[i - 1] !== '0') {
            f[i] = f[i - 1];
        }
        if (i > 1 && (s[i - 2] === '1' || (s[i - 2] === '2' && s[i - 1] <= '6'))) {
            f[i] += f[i - 2];
        }
    }
    return f[n];
}
```

#### C#

```cs
public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
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
> Lời giải 1 vốn đã đúng. Mỗi $f[i]$ chỉ cần $f[i-1]$ và $f[i-2]$, vì vậy không cần giữ cả mảng. Hai biến cuộn giảm không gian xuống còn $O(1)$; thời gian vẫn là một lần duyệt.

<!-- thinking:end -->

Chúng ta nhận thấy trạng thái $f[i]$ chỉ liên quan đến $f[i-1]$ và $f[i-2]$. Do đó, chúng ta có thể dùng hai biến để thay thế các trạng thái này, giảm độ phức tạp không gian từ $O(n)$ xuống $O(1)$. Độ phức tạp thời gian vẫn là $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        f, g = 0, 1
        for i, c in enumerate(s, 1):
            h = g if c != "0" else 0
            if i > 1 and s[i - 2] != "0" and int(s[i - 2 : i]) <= 26:
                h += f
            f, g = g, h
        return g
```

#### Java

```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s.charAt(i - 1) != '0' ? g : 0;
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numDecodings(string s) {
        int n = s.size();
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
};
```

#### Go

```go
func numDecodings(s string) int {
	n := len(s)
	f, g := 0, 1
	for i := 1; i <= n; i++ {
		h := 0
		if s[i-1] != '0' {
			h = g
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			h += f
		}
		f, g = g, h
	}
	return g
}
```

#### TypeScript

```ts
function numDecodings(s: string): number {
    const n = s.length;
    let [f, g] = [0, 1];
    for (let i = 1; i <= n; ++i) {
        let h = s[i - 1] !== '0' ? g : 0;
        if (i > 1 && (s[i - 2] === '1' || (s[i - 2] === '2' && s[i - 1] <= '6'))) {
            h += f;
        }
        [f, g] = [g, h];
    }
    return g;
}
```

#### C#

```cs
public class Solution {
    public int NumDecodings(string s) {
        int n = s.Length;
        int f = 0, g = 1;
        for (int i = 1; i <= n; ++i) {
            int h = s[i - 1] != '0' ? g : 0;
            if (i > 1 && (s[i - 2] == '1' || (s[i - 2] == '2' && s[i - 1] <= '6'))) {
                h += f;
            }
            f = g;
            g = h;
        }
        return g;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
