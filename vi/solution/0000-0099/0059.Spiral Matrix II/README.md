---
comments: true
difficulty: Medium
tags:
    - Array
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii)

[中文文档](/solution/0000-0099/0059.Spiral%20Matrix%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên dương <code>n</code>, hãy tạo một <code>n x n</code> <code>matrix</code> được điền các phần tử từ <code>1</code> đến <code>n<sup>2</sup></code> theo thứ tự xoắn ốc.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0059.Spiral%20Matrix%20II/images/spiraln.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> [[1,2,3],[8,9,4],[7,6,5]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> [[1]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Mô phỏng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là điền theo từng lớp: bốn cạnh cho mỗi lớp, sau đó thu hẹp lại. $n \le 20$ vẫn đủ để vượt qua, nhưng sẽ có các lỗi biên tương tự.
>
> Điểm bất tiện là phải tự quản lý bốn cạnh. Tương tự như khi đọc theo đường xoắn ốc: giữ hướng hiện tại, rẽ phải khi ô tiếp theo nằm ngoài phạm vi hoặc đã được điền. Các ô trống có giá trị $0$, các ô đã điền có giá trị khác không, nên không cần thêm $\textit{vis}$.
>
> Vì vậy, lặp qua các hướng và ghi các giá trị từ $1$ đến $n^2$.

<!-- thinking:end -->

Chúng ta có thể mô phỏng trực tiếp quá trình tạo ma trận xoắn ốc.

Định nghĩa một mảng 2D $\textit{ans}$ để lưu ma trận xoắn ốc. Sử dụng $i$ và $j$ để biểu diễn chỉ số hàng và cột hiện tại, đồng thời sử dụng $k$ để biểu diễn chỉ số của hướng hiện tại. $\textit{dirs}$ biểu diễn ánh xạ giữa các chỉ số hướng và các hướng tương ứng.

Bắt đầu từ $1$, lần lượt điền vào từng vị trí trong ma trận. Sau khi điền một vị trí, tính chỉ số hàng và cột của vị trí tiếp theo. Nếu vị trí tiếp theo nằm ngoài phạm vi hoặc đã được điền, thay đổi hướng rồi tính lại chỉ số hàng và cột của vị trí tiếp theo.

Độ phức tạp thời gian là $O(n^2)$, trong đó $n$ là độ dài cạnh của ma trận. Bỏ qua phần không gian được sử dụng bởi mảng kết quả, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        ans = [[0] * n for _ in range(n)]
        dirs = (0, 1, 0, -1, 0)
        i = j = k = 0
        for v in range(1, n * n + 1):
            ans[i][j] = v
            x, y = i + dirs[k], j + dirs[k + 1]
            if x < 0 or x >= n or y < 0 or y >= n or ans[x][y]:
                k = (k + 1) % 4
            i, j = i + dirs[k], j + dirs[k + 1]
        return ans
```

#### Java

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        final int[] dirs = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        for (int v = 1; v <= n * n; ++v) {
            ans[i][j] = v;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= n || y < 0 || y >= n || ans[x][y] != 0) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> ans(n, vector<int>(n, 0));
        const int dirs[5] = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        for (int v = 1; v <= n * n; ++v) {
            ans[i][j] = v;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= n || y < 0 || y >= n || ans[x][y] != 0) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
};
```

#### Go

```go
func generateMatrix(n int) [][]int {
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	dirs := [5]int{0, 1, 0, -1, 0}
	i, j, k := 0, 0, 0
	for v := 1; v <= n*n; v++ {
		ans[i][j] = v
		x, y := i+dirs[k], j+dirs[k+1]
		if x < 0 || x >= n || y < 0 || y >= n || ans[x][y] != 0 {
			k = (k + 1) % 4
		}
		i += dirs[k]
		j += dirs[k+1]
	}
	return ans
}
```

#### TypeScript

```ts
function generateMatrix(n: number): number[][] {
    const ans: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const dirs = [0, 1, 0, -1, 0];
    let [i, j, k] = [0, 0, 0];
    for (let v = 1; v <= n * n; v++) {
        ans[i][j] = v;
        const [x, y] = [i + dirs[k], j + dirs[k + 1]];
        if (x < 0 || x >= n || y < 0 || y >= n || ans[x][y] !== 0) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn generate_matrix(n: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![vec![0; n as usize]; n as usize];
        let dirs = [0, 1, 0, -1, 0];
        let (mut i, mut j, mut k) = (0, 0, 0);
        for v in 1..=n * n {
            ans[i as usize][j as usize] = v;
            let (x, y) = (i + dirs[k], j + dirs[k + 1]);
            if x < 0 || x >= n || y < 0 || y >= n || ans[x as usize][y as usize] != 0 {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function (n) {
    const ans = Array.from({ length: n }, () => Array(n).fill(0));
    const dirs = [0, 1, 0, -1, 0];
    let [i, j, k] = [0, 0, 0];
    for (let v = 1; v <= n * n; v++) {
        ans[i][j] = v;
        const [x, y] = [i + dirs[k], j + dirs[k + 1]];
        if (x < 0 || x >= n || y < 0 || y >= n || ans[x][y] !== 0) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
