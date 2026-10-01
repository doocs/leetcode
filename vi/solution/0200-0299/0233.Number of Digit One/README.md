---
comments: true
difficulty: Hard
tags:
    - Recursion
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [233. Number of Digit One](https://leetcode.com/problems/number-of-digit-one)

[中文文档](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên <code>n</code>, hãy đếm <em>tổng số chữ số </em><code>1</code><em> xuất hiện trong tất cả các số nguyên không âm nhỏ hơn hoặc bằng</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 13
<strong>Đầu ra:</strong> 6
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 0
<strong>Đầu ra:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động trên chữ số

<!-- thinking:start -->

> **Tư duy**
>
> Không thể quét mọi số nguyên trong $[1,n]$ khi $n$ lớn. Số lần xuất hiện của chữ số $1$ chỉ phụ thuộc vào các chữ số và cận trên, nên đây là một bài toán quy hoạch động trên chữ số.
>
> Xem $n$ là một chuỗi và ghi nhớ kết quả của $dfs(i,\textit{cnt},\textit{limit})$: vị trí $i$ tính từ đầu, số chữ số 1 là $\textit{cnt}$, và liệu ta vẫn đang khớp với tiền tố của $n$ hay không. Liệt kê chữ số hiện tại và cộng các kết quả.

<!-- thinking:end -->

Về bản chất, bài toán này yêu cầu tìm số lần chữ số $1$ xuất hiện trong đoạn $[l, ..r]$. Số đếm liên quan đến số lượng chữ số và giá trị của từng chữ số. Ta có thể sử dụng khái niệm quy hoạch động trên chữ số để giải bài toán này. Trong quy hoạch động trên chữ số, kích thước của số có ít ảnh hưởng đến độ phức tạp.

Với bài toán trên đoạn $[l, ..r]$, thông thường ta chuyển nó thành bài toán trên đoạn $[1, ..r]$, sau đó trừ đi kết quả của đoạn $[1, ..l - 1]$, tức là:

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

Tuy nhiên, với bài toán này, ta chỉ cần tìm giá trị cho đoạn $[1, ..r]$.

Ở đây, ta sử dụng tìm kiếm có ghi nhớ để cài đặt quy hoạch động trên chữ số. Ta tìm kiếm từ vị trí bắt đầu xuống dưới, và ở mức thấp nhất, ta nhận được số nghiệm. Sau đó, ta trả về các đáp án lần lượt từ các lớp lên trên, cuối cùng nhận được đáp án cuối cùng từ điểm bắt đầu của quá trình tìm kiếm.

Các bước cơ bản như sau:

Đầu tiên, ta chuyển số $n$ thành một chuỗi $s$. Sau đó, ta thiết kế hàm $\textit{dfs}(i, \textit{cnt}, \textit{limit})$, trong đó:

- Chữ số $i$ biểu thị vị trí hiện tại đang được tìm kiếm, bắt đầu từ chữ số cao nhất, tức là $i = 0$ biểu thị chữ số cao nhất.
- Chữ số $\textit{cnt}$ biểu thị số lần xuất hiện hiện tại của chữ số $1$ trong số đó.
- Boolean $\textit{limit}$ cho biết số hiện tại có bị giới hạn bởi cận trên hay không.

Hàm thực hiện như sau:

Nếu $i$ vượt quá độ dài của số $n$, điều đó có nghĩa là quá trình tìm kiếm đã kết thúc, ta trả về trực tiếp $cnt$. Nếu $\textit{limit}$ là đúng, $up$ là chữ số thứ $i$ của số hiện tại. Nếu không, $up = 9$. Tiếp theo, ta duyệt $j$ từ $0$ đến $up$. Với mỗi $j$:

- Nếu $j$ bằng $1$, ta tăng $cnt$ lên một.
- Gọi đệ quy $\textit{dfs}(i + 1, \textit{cnt}, \textit{limit} \land j = up)$.

Đáp án là $\textit{dfs}(0, 0, \text{True})$.

Độ phức tạp thời gian là $O(m^2 \times D)$, và độ phức tạp không gian là $O(m^2)$. Trong đó, $m$ là độ dài của số $n$, và $D = 10$.

Các bài toán tương tự:

Sau đây là bản dịch của các bài toán tương tự sang tiếng Anh:

- [357. Count Numbers with Unique Digits](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)
- [600. Non-negative Integers without Consecutive Ones](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)
- [788. Rotated Digits](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README_EN.md)
- [902. Numbers At Most N Given Digit Set](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)
- [1012. Numbers with Repeated Digits](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)
- [2376. Count Special Integers](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDigitOne(self, n: int) -> int:
        @cache
        def dfs(i: int, cnt: int, limit: bool) -> int:
            if i >= len(s):
                return cnt
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                ans += dfs(i + 1, cnt + (j == 1), limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True)
```

#### Java

```java
class Solution {
    private int m;
    private char[] s;
    private Integer[][] f;

    public int countDigitOne(int n) {
        s = String.valueOf(n).toCharArray();
        m = s.length;
        f = new Integer[m][m];
        return dfs(0, 0, true);
    }

    private int dfs(int i, int cnt, boolean limit) {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i][cnt] != null) {
            return f[i][cnt];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            ans += dfs(i + 1, cnt + (j == 1 ? 1 : 0), limit && j == up);
        }
        if (!limit) {
            f[i][cnt] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countDigitOne(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][m];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int cnt, bool limit) -> int {
            if (i >= m) {
                return cnt;
            }
            if (!limit && f[i][cnt] != -1) {
                return f[i][cnt];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                ans += dfs(i + 1, cnt + (j == 1), limit && j == up);
            }
            if (!limit) {
                f[i][cnt] = ans;
            }
            return ans;
        };
        return dfs(0, 0, true);
    }
};
```

#### Go

```go
func countDigitOne(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, cnt int, limit bool) int
	dfs = func(i, cnt int, limit bool) int {
		if i >= m {
			return cnt
		}
		if !limit && f[i][cnt] != -1 {
			return f[i][cnt]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			t := 0
			if j == 1 {
				t = 1
			}
			ans += dfs(i+1, cnt+t, limit && j == up)
		}
		if !limit {
			f[i][cnt] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}
```

#### TypeScript

```ts
function countDigitOne(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(m).fill(-1));
    const dfs = (i: number, cnt: number, limit: boolean): number => {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i][cnt] !== -1) {
            return f[i][cnt];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            ans += dfs(i + 1, cnt + (j === 1 ? 1 : 0), limit && j === up);
        }
        if (!limit) {
            f[i][cnt] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true);
}
```

#### C#

```cs
public class Solution {
    private int m;
    private char[] s;
    private int?[,] f;

    public int CountDigitOne(int n) {
        s = n.ToString().ToCharArray();
        m = s.Length;
        f = new int?[m, m];
        return Dfs(0, 0, true);
    }

    private int Dfs(int i, int cnt, bool limit) {
        if (i >= m) {
            return cnt;
        }
        if (!limit && f[i, cnt] != null) {
            return f[i, cnt].Value;
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            ans += Dfs(i + 1, cnt + (j == 1 ? 1 : 0), limit && j == up);
        }
        if (!limit) {
            f[i, cnt] = ans;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
