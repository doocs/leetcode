---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Array
    - String
    - Backtracking
    - Matrix
---

<!-- problem:start -->

# [79. Word Search](https://leetcode.com/problems/word-search)

[中文文档](/solution/0000-0099/0079.Word%20Search/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một ma trận ký tự <code>m x n</code> <code>board</code> và một chuỗi <code>word</code>, hãy trả về <code>true</code> <em>nếu</em> <code>word</code> <em>tồn tại trong ma trận</em>.</p>

<p>Từ có thể được tạo thành từ các chữ cái của những ô liền kề tuần tự, trong đó các ô liền kề nằm ngang hoặc dọc. Không được sử dụng cùng một ô chứa chữ cái quá một lần.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCCED&quot;
<strong>Đầu ra:</strong> true
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word-1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;SEE&quot;
<strong>Đầu ra:</strong> true
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0079.Word%20Search/images/word3.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCB&quot;
<strong>Đầu ra:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n = board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 6</code></li>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>board</code> và <code>word</code> chỉ gồm các chữ cái tiếng Anh viết thường và viết hoa.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể sử dụng cắt tỉa trong quá trình tìm kiếm để làm cho lời giải của mình nhanh hơn với một <code>board</code> lớn hơn không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS (Quay lui)

<!-- thinking:start -->

> **Tư duy**
>
> Xuất phát từ mỗi ô, duyệt các đường đi có độ dài $|word|$ và so sánh. Có nhiều nhất $36$ ô và $|word| \le 15$, nên tìm kiếm không cắt tỉa sẽ bùng nổ. So khớp ngay trong khi duyệt, dừng khi không khớp và không bao giờ dùng lại một ô.
>
> Liệt kê các điểm bắt đầu; $dfs(i,j,k)$ nghĩa là $(i,j)$ phải khớp với $word[k]$. Khi khớp, ghi `'0'` để chặn việc đi vào lại, thử bốn ô lân cận tại $k+1$, rồi khôi phục. Các chữ cái không bao giờ là `'0'`, nên không cần vis bổ sung. Chỉ cần một điểm bắt đầu thành công.

<!-- thinking:end -->

Chúng ta có thể liệt kê từng vị trí $(i, j)$ trong ma trận làm điểm bắt đầu tìm kiếm, rồi bắt đầu tìm kiếm theo chiều sâu từ điểm đó. Nếu có thể tìm đến cuối từ, điều đó có nghĩa là từ tồn tại; nếu không, từ không tồn tại.

Do đó, chúng ta thiết kế một hàm $dfs(i, j, k)$, biểu thị liệu chúng ta có thể tìm kiếm thành công từ vị trí $(i, j)$ của ma trận, bắt đầu từ ký tự thứ $k$ của từ hay không. Các bước thực hiện của hàm $dfs(i, j, k)$ như sau:

- Nếu $k = |word|-1$, điều đó có nghĩa là chúng ta đã tìm đến ký tự cuối cùng của từ. Khi đó, chúng ta chỉ cần kiểm tra xem ký tự ở vị trí $(i, j)$ của ma trận có bằng $word[k]$ hay không. Nếu bằng, điều đó có nghĩa là từ tồn tại; nếu không, điều đó có nghĩa là từ không tồn tại. Dù từ có tồn tại hay không, không cần tiếp tục tìm kiếm, vì vậy trả về kết quả ngay.
- Ngược lại, nếu ký tự $word[k]$ không bằng ký tự ở vị trí $(i, j)$ của ma trận, điều đó có nghĩa là lần tìm kiếm này thất bại, nên trả về `false` ngay.
- Ngược lại, chúng ta tạm thời lưu ký tự ở vị trí $(i, j)$ của ma trận vào $c$, sau đó thay đổi ký tự ở vị trí này thành ký tự đặc biệt `'0'`, cho biết ô tại vị trí này đã được sử dụng để ngăn không cho sử dụng lại trong các lần tìm kiếm tiếp theo. Sau đó, chúng ta bắt đầu tìm kiếm ký tự thứ $k+1$ trong ma trận theo các hướng lên, xuống, trái và phải từ vị trí $(i, j)$. Nếu bất kỳ hướng nào thành công, điều đó có nghĩa là việc tìm kiếm thành công; nếu không, điều đó có nghĩa là việc tìm kiếm thất bại. Khi đó, chúng ta cần khôi phục ký tự ở vị trí $(i, j)$ của ma trận, tức là đặt $c$ trở lại vị trí $(i, j)$ (quay lui).

Trong hàm chính, chúng ta liệt kê từng vị trí $(i, j)$ trong ma trận làm điểm bắt đầu. Nếu việc gọi $dfs(i, j, 0)$ trả về `true`, điều đó có nghĩa là từ tồn tại; nếu không, từ không tồn tại, nên trả về `false`.

Độ phức tạp thời gian là $O(m \times n \times 3^k)$, và độ phức tạp không gian là $O(\min(m \times n, k))$. Trong đó, $m$ và $n$ lần lượt là số hàng và số cột của ma trận; còn $k$ là độ dài của chuỗi $word$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        def dfs(i: int, j: int, k: int) -> bool:
            if k == len(word) - 1:
                return board[i][j] == word[k]
            if board[i][j] != word[k]:
                return False
            c = board[i][j]
            board[i][j] = "0"
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                ok = 0 <= x < m and 0 <= y < n and board[x][y] != "0"
                if ok and dfs(x, y, k + 1):
                    return True
            board[i][j] = c
            return False

        m, n = len(board), len(board[0])
        return any(dfs(i, j, 0) for i in range(m) for j in range(n))
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private String word;
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        this.word = word;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int k) {
        if (k == word.length() - 1) {
            return board[i][j] == word.charAt(k);
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int u = 0; u < 4; ++u) {
            int x = i + dirs[u], y = j + dirs[u + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (k == word.size() - 1) {
                return board[i][j] == word[k];
            }
            if (board[i][j] != word[k]) {
                return false;
            }
            char c = board[i][j];
            board[i][j] = '0';
            for (int u = 0; u < 4; ++u) {
                int x = i + dirs[u], y = j + dirs[u + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k + 1)) {
                    return true;
                }
            }
            board[i][j] = c;
            return false;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func exist(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	var dfs func(int, int, int) bool
	dfs = func(i, j, k int) bool {
		if k == len(word)-1 {
			return board[i][j] == word[k]
		}
		if board[i][j] != word[k] {
			return false
		}
		dirs := [5]int{-1, 0, 1, 0, -1}
		c := board[i][j]
		board[i][j] = '0'
		for u := 0; u < 4; u++ {
			x, y := i+dirs[u], j+dirs[u+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k+1) {
				return true
			}
		}
		board[i][j] = c
		return false
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if dfs(i, j, 0) {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function exist(board: string[][], word: string): boolean {
    const [m, n] = [board.length, board[0].length];
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, k: number): boolean => {
        if (k === word.length - 1) {
            return board[i][j] === word[k];
        }
        if (board[i][j] !== word[k]) {
            return false;
        }
        const c = board[i][j];
        board[i][j] = '0';
        for (let u = 0; u < 4; ++u) {
            const [x, y] = [i + dirs[u], j + dirs[u + 1]];
            const ok = x >= 0 && x < m && y >= 0 && y < n;
            if (ok && board[x][y] !== '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}
```

#### JavaScript

```js
function exist(board, word) {
    const [m, n] = [board.length, board[0].length];
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i, j, k) => {
        if (k === word.length - 1) {
            return board[i][j] === word[k];
        }
        if (board[i][j] !== word[k]) {
            return false;
        }
        const c = board[i][j];
        board[i][j] = '0';
        for (let u = 0; u < 4; ++u) {
            const [x, y] = [i + dirs[u], j + dirs[u + 1]];
            const ok = x >= 0 && x < m && y >= 0 && y < n;
            if (ok && board[x][y] !== '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(
        i: usize,
        j: usize,
        c: usize,
        word: &[u8],
        board: &Vec<Vec<char>>,
        vis: &mut Vec<Vec<bool>>,
    ) -> bool {
        if (board[i][j] as u8) != word[c] {
            return false;
        }
        if c == word.len() - 1 {
            return true;
        }
        vis[i][j] = true;
        let dirs = [[-1, 0], [0, -1], [1, 0], [0, 1]];
        for [x, y] in dirs.into_iter() {
            let i = x + (i as i32);
            let j = y + (j as i32);
            if i < 0 || i == (board.len() as i32) || j < 0 || j == (board[0].len() as i32) {
                continue;
            }
            let (i, j) = (i as usize, j as usize);
            if !vis[i][j] && Self::dfs(i, j, c + 1, word, board, vis) {
                return true;
            }
        }
        vis[i][j] = false;
        false
    }

    pub fn exist(board: Vec<Vec<char>>, word: String) -> bool {
        let m = board.len();
        let n = board[0].len();
        let word = word.as_bytes();
        let mut vis = vec![vec![false; n]; m];
        for i in 0..m {
            for j in 0..n {
                if Self::dfs(i, j, 0, word, &board, &mut vis) {
                    return true;
                }
            }
        }
        false
    }
}
```

#### C#

```cs
public class Solution {
    private int m;
    private int n;
    private char[][] board;
    private string word;

    public bool Exist(char[][] board, string word) {
        m = board.Length;
        n = board[0].Length;
        this.board = board;
        this.word = word;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private bool dfs(int i, int j, int k) {
        if (k == word.Length - 1) {
            return board[i][j] == word[k];
        }
        if (board[i][j] != word[k]) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '0';
        int[] dirs = { -1, 0, 1, 0, -1 };
        for (int u = 0; u < 4; ++u) {
            int x = i + dirs[u];
            int y = j + dirs[u + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k + 1)) {
                return true;
            }
        }
        board[i][j] = c;
        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
