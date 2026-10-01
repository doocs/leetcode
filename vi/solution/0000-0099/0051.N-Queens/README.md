---
comments: true
difficulty: Hard
tags:
    - Array
    - Backtracking
    - Algorithm X
---

<!-- problem:start -->

# [51. N-Queens](https://leetcode.com/problems/n-queens)

[中文文档](/solution/0000-0099/0051.N-Queens/README.md)

## Mô tả

<!-- description:start -->

<p>Bài toán <strong>n-queens</strong> là bài toán đặt <code>n</code> quân hậu trên bàn cờ <code>n x n</code> sao cho không có hai quân hậu nào tấn công lẫn nhau.</p>

<p>Cho một số nguyên <code>n</code>, hãy trả về <em>tất cả các nghiệm phân biệt của <strong>bài toán n-queens</strong></em>. Bạn có thể trả về đáp án theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Mỗi nghiệm chứa một cấu hình bàn cờ phân biệt biểu diễn cách đặt quân hậu trong bài toán n-queens, trong đó <code>&#39;Q&#39;</code> và <code>&#39;.&#39;</code> lần lượt biểu thị quân hậu và ô trống.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0051.N-Queens/images/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>Đầu vào:</strong> n = 4
<strong>Đầu ra:</strong> [[&quot;.Q..&quot;,&quot;...Q&quot;,&quot;Q...&quot;,&quot;..Q.&quot;],[&quot;..Q.&quot;,&quot;Q...&quot;,&quot;...Q&quot;,&quot;.Q..&quot;]]
<strong>Giải thích:</strong> Có hai nghiệm phân biệt cho bài toán 4-queens như được minh họa ở trên
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> [[&quot;Q&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS (Quay lui)

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thử mọi hoán vị cột: đặt quân hậu ở hàng $i$ tại cột $p_i$, sau đó kiểm tra các đường chéo. Vì $n \le 9$, có thể tìm kiếm $9!$, nhưng chúng ta chỉ phát hiện xung đột sau khi bàn cờ đã được lấp đầy, đồng thời vẫn phải tạo lưới chuỗi.
>
> Sự lãng phí nằm ở việc đặt tất cả quân hậu rồi mới kiểm tra. Mỗi hàng bắt buộc có một quân hậu; xung đột chỉ có thể xảy ra ở cùng cột hoặc cùng đường chéo. Có thể đánh dấu cột, $i+j$ và $n-i+j$ trong $O(1)$.
>
> Vì vậy, chúng ta DFS lần lượt theo từng hàng: chỉ đặt và đánh dấu khi vị trí hợp lệ, sau đó quay lui. Bàn cờ $g$ chuyển đổi giữa `'Q'`/`'.'` đồng bộ; khi đến hàng $n$, chúng ta sao chép một đáp án.

<!-- thinking:end -->

Chúng ta định nghĩa ba mảng $col$, $dg$ và $udg$ để biểu diễn lần lượt việc cột, đường chéo chính và đường chéo phụ có quân hậu hay không. Nếu có một quân hậu ở vị trí $(i, j)$, thì $col[j]$, $dg[i + j]$ và $udg[n - i + j]$ đều bằng $1$. Ngoài ra, chúng ta sử dụng một mảng $g$ để ghi lại trạng thái hiện tại của bàn cờ, trong đó tất cả phần tử của $g$ ban đầu là `'.'`.

Tiếp theo, chúng ta định nghĩa một hàm $dfs(i)$, biểu thị việc đặt quân hậu bắt đầu từ hàng thứ $i$.

Trong $dfs(i)$, nếu $i = n$, điều đó có nghĩa là chúng ta đã hoàn tất việc đặt tất cả quân hậu. Chúng ta đưa $g$ hiện tại vào mảng đáp án rồi kết thúc đệ quy.

Nếu không, chúng ta liệt kê từng cột $j$ của hàng hiện tại. Nếu vị trí $(i, j)$ không có quân hậu, tức là $col[j]$, $dg[i + j]$ và $udg[n - i + j]$ đều bằng $0$, thì chúng ta có thể đặt một quân hậu, nghĩa là đổi $g[i][j]$ thành `'Q'` và đặt $col[j]$, $dg[i + j]$ và $udg[n - i + j]$ thành $1$. Sau đó chúng ta tiếp tục tìm kiếm hàng kế tiếp, tức là gọi $dfs(i + 1)$. Sau khi lời gọi đệ quy kết thúc, chúng ta cần đổi $g[i][j]$ trở lại `'.'` và đặt $col[j]$, $dg[i + j]$ và $udg[n - i + j]$ về $0$.

Trong hàm chính, chúng ta gọi $dfs(0)$ để bắt đầu đệ quy, cuối cùng trả về mảng đáp án.

Độ phức tạp thời gian là $O(n^2 \times n!)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nguyên được cung cấp trong đề bài.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        def dfs(i: int):
            if i == n:
                ans.append(["".join(row) for row in g])
                return
            for j in range(n):
                if col[j] + dg[i + j] + udg[n - i + j] == 0:
                    g[i][j] = "Q"
                    col[j] = dg[i + j] = udg[n - i + j] = 1
                    dfs(i + 1)
                    col[j] = dg[i + j] = udg[n - i + j] = 0
                    g[i][j] = "."

        ans = []
        g = [["."] * n for _ in range(n)]
        col = [0] * n
        dg = [0] * (n << 1)
        udg = [0] * (n << 1)
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<List<String>> ans = new ArrayList<>();
    private int[] col;
    private int[] dg;
    private int[] udg;
    private String[][] g;
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        col = new int[n];
        dg = new int[n << 1];
        udg = new int[n << 1];
        g = new String[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], ".");
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            List<String> t = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                t.add(String.join("", g[j]));
            }
            ans.add(t);
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] == 0) {
                g[i][j] = "Q";
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                g[i][j] = ".";
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<int> col(n);
        vector<int> dg(n << 1);
        vector<int> udg(n << 1);
        vector<vector<string>> ans;
        vector<string> t(n, string(n, '.'));
        function<void(int)> dfs = [&](int i) -> void {
            if (i == n) {
                ans.push_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (col[j] + dg[i + j] + udg[n - i + j] == 0) {
                    t[i][j] = 'Q';
                    col[j] = dg[i + j] = udg[n - i + j] = 1;
                    dfs(i + 1);
                    col[j] = dg[i + j] = udg[n - i + j] = 0;
                    t[i][j] = '.';
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func solveNQueens(n int) (ans [][]string) {
	col := make([]int, n)
	dg := make([]int, n<<1)
	udg := make([]int, n<<1)
	t := make([][]byte, n)
	for i := range t {
		t[i] = make([]byte, n)
		for j := range t[i] {
			t[i][j] = '.'
		}
	}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			tmp := make([]string, n)
			for i := range tmp {
				tmp[i] = string(t[i])
			}
			ans = append(ans, tmp)
			return
		}
		for j := 0; j < n; j++ {
			if col[j]+dg[i+j]+udg[n-i+j] == 0 {
				col[j], dg[i+j], udg[n-i+j] = 1, 1, 1
				t[i][j] = 'Q'
				dfs(i + 1)
				t[i][j] = '.'
				col[j], dg[i+j], udg[n-i+j] = 0, 0, 0
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function solveNQueens(n: number): string[][] {
    const col: number[] = Array(n).fill(0);
    const dg: number[] = Array(n << 1).fill(0);
    const udg: number[] = Array(n << 1).fill(0);
    const ans: string[][] = [];
    const t: string[][] = Array.from({ length: n }, () => Array(n).fill('.'));
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.map(x => x.join('')));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] === 0) {
                t[i][j] = 'Q';
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                t[i][j] = '.';
            }
        }
    };
    dfs(0);
    return ans;
}
```

#### C#

```cs
public class Solution {
    private int n;
    private int[] col;
    private int[] dg;
    private int[] udg;
    private IList<IList<string>> ans = new List<IList<string>>();
    private IList<string> t = new List<string>();

    public IList<IList<string>> SolveNQueens(int n) {
        this.n = n;
        col = new int[n];
        dg = new int[n << 1];
        udg = new int[n << 1];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ans.Add(new List<string>(t));
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (col[j] + dg[i + j] + udg[n - i + j] == 0) {
                char[] row = new char[n];
                Array.Fill(row, '.');
                row[j] = 'Q';
                t.Add(new string(row));
                col[j] = dg[i + j] = udg[n - i + j] = 1;
                dfs(i + 1);
                col[j] = dg[i + j] = udg[n - i + j] = 0;
                t.RemoveAt(t.Count - 1);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
