---
comments: true
difficulty: Easy
tags:
    - Math
    - String
---

<!-- problem:start -->

# [171. Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number)

[中文文档](/solution/0100-0199/0171.Excel%20Sheet%20Column%20Number/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>columnTitle</code> biểu diễn tiêu đề cột như xuất hiện trong một trang tính Excel, hãy trả về <em>số cột tương ứng với nó</em>.</p>

<p>Ví dụ:</p>

<pre>
A -&gt; 1
B -&gt; 2
C -&gt; 3
...
Z -&gt; 26
AA -&gt; 27
AB -&gt; 28
...
</pre>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> columnTitle = &quot;A&quot;
<strong>Đầu ra:</strong> 1
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> columnTitle = &quot;AB&quot;
<strong>Đầu ra:</strong> 28
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> columnTitle = &quot;ZY&quot;
<strong>Đầu ra:</strong> 701
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= columnTitle.length &lt;= 7</code></li>
	<li><code>columnTitle</code> chỉ bao gồm các chữ cái tiếng Anh viết hoa.</li>
	<li><code>columnTitle</code> nằm trong phạm vi <code>[&quot;A&quot;, &quot;FXSHRXW&quot;]</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Chuyển đổi cơ số

<!-- thinking:start -->

> **Tư duy**
>
> Chuyển tiêu đề thành số là phép đảo ngược của bài toán $168$: mỗi chữ cái là một chữ số trong $1\ldots 26$. Duyệt từ trái sang phải với $\textit{ans}=\textit{ans}\times 26 + (c-'A'+1)$, theo cùng khuôn mẫu như khi phân tích một chuỗi thập phân.

<!-- thinking:end -->

Tên cột trong Excel là một biểu diễn ở cơ số 26. Ví dụ, "AB" biểu diễn số cột $1 \times 26 + 2 = 28$.

Do đó, chúng ta có thể duyệt qua chuỗi `columnTitle`, chuyển đổi từng ký tự thành giá trị tương ứng, rồi tính kết quả.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của chuỗi `columnTitle`. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        ans = 0
        for c in map(ord, columnTitle):
            ans = ans * 26 + c - ord("A") + 1
        return ans
```

#### Java

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); ++i) {
            ans = ans * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int titleToNumber(string columnTitle) {
        int ans = 0;
        for (char& c : columnTitle) {
            ans = ans * 26 + (c - 'A' + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func titleToNumber(columnTitle string) (ans int) {
	for _, c := range columnTitle {
		ans = ans*26 + int(c-'A'+1)
	}
	return
}
```

#### TypeScript

```ts
function titleToNumber(columnTitle: string): number {
    let ans: number = 0;
    for (const c of columnTitle) {
        ans = ans * 26 + (c.charCodeAt(0) - 'A'.charCodeAt(0) + 1);
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int TitleToNumber(string columnTitle) {
        int ans = 0;
        foreach (char c in columnTitle) {
            ans = ans * 26 + c - 'A' + 1;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
