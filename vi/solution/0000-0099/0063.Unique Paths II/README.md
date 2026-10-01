---
comments: true
difficulty: Medium
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii)

[中文文档](/solution/0000-0099/0063.Unique%20Paths%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một mảng số nguyên <code>m x n</code> <code>grid</code>. Một robot ban đầu nằm ở <b>góc trên bên trái</b> (tức là <code>grid[0][0]</code>). Robot cố gắng di chuyển đến <strong>góc dưới bên phải</strong> (tức là <code>grid[m - 1][n - 1]</code>). Robot chỉ có thể di chuyển xuống hoặc sang phải tại bất kỳ thời điểm nào.</p>

<p>Trong <code>grid</code>, chướng ngại vật và ô trống lần lượt được đánh dấu bằng <code>1</code> hoặc <code>0</code>. Đường đi mà robot thực hiện không được bao gồm <strong>bất kỳ</strong> ô nào là chướng ngại vật.</p>

<p>Trả về <em>số lượng đường đi duy nhất có thể mà robot có thể thực hiện để đến góc dưới bên phải</em>.</p>

<p>Các test case được tạo sao cho đáp án nhỏ hơn hoặc bằng <code>2 * 10<sup>9</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0063.Unique%20Paths%20II/images/robot1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Có một chướng ngại vật ở giữa lưới 3x3 ở trên.
Có hai cách để đến góc dưới bên phải:
1. Right -&gt; Right -&gt; Down -&gt; Down
2. Down -&gt; Down -&gt; Right -&gt; Right
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0063.Unique%20Paths%20II/images/robot2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Đầu vào:</strong> obstacleGrid = [[0,1],[0,0]]
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == obstacleGrid.length</code></li>
	<li><code>n == obstacleGrid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>obstacleGrid[i][j]</code> là <code>0</code> hoặc <code>1</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Giống như Unique Paths, tìm kiếm thô chỉ di chuyển xuống hoặc sang phải sẽ bùng nổ: $m, n \le 100$. Các chướng ngại vật cũng phá vỡ mẹo “hàng đầu tiên và cột đầu tiên đều là $1$”.
>
> Điểm nghẽn là mở rộng cùng một ô $(i, j)$ nhiều lần. Từ đây, nếu vượt ngoài biên hoặc gặp chướng ngại vật thì kết quả là $0$, ô đích là $1$, nếu không thì hai nhánh được cộng lại.
>
> Ghi nhớ kết quả của $\textit{dfs}(i, j)$ để mỗi ô chỉ được tính một lần; thời gian và không gian đều là $O(mn)$.

<!-- thinking:end -->

Chúng ta xây dựng một hàm $\textit{dfs}(i, j)$ biểu thị số lượng đường đi từ ô $(i, j)$ đến ô $(m - 1, n - 1)$. Ở đây, $m$ và $n$ lần lượt là số hàng và số cột của lưới.

Quy trình thực thi của hàm $\textit{dfs}(i, j)$ như sau:

- Nếu $i \ge m$ hoặc $j \ge n$, hoặc $\textit{obstacleGrid}[i][j] = 1$, số lượng đường đi là $0$;
- Nếu $i = m - 1$ và $j = n - 1$, số lượng đường đi là $1$;
- Nếu không, số lượng đường đi là $\textit{dfs}(i + 1, j) + \textit{dfs}(i, j + 1)$.

Để tránh tính toán dư thừa, chúng ta có thể sử dụng memoization.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Ở đây, $m$ và $n$ lần lượt là số hàng và số cột của lưới.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= m or j >= n or obstacleGrid[i][j]:
                return 0
            if i == m - 1 and j == n - 1:
                return 1
            return dfs(i + 1, j) + dfs(i, j + 1)

        m, n = len(obstacleGrid), len(obstacleGrid[0])
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private int[][] obstacleGrid;
    private int m;
    private int n;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        this.obstacleGrid = obstacleGrid;
        f = new Integer[m][n];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= n || obstacleGrid[i][j] == 1) {
            return 0;
        }
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (f[i][j] == null) {
            f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        }
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size(), n = obstacleGrid[0].size();
        vector<vector<int>> f(m, vector<int>(n, -1));
        auto dfs = [&](this auto&& dfs, int i, int j) {
            if (i >= m || j >= n || obstacleGrid[i][j]) {
                return 0;
            }
            if (i == m - 1 && j == n - 1) {
                return 1;
            }
            if (f[i][j] == -1) {
                f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
            }
            return f[i][j];
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	m, n := len(obstacleGrid), len(obstacleGrid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= n || obstacleGrid[i][j] == 1 {
			return 0
		}
		if i == m-1 && j == n-1 {
			return 1
		}
		if f[i][j] == -1 {
			f[i][j] = dfs(i+1, j) + dfs(i, j+1)
		}
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function uniquePathsWithObstacles(obstacleGrid: number[][]): number {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i >= m || j >= n || obstacleGrid[i][j] === 1) {
            return 0;
        }
        if (i === m - 1 && j === n - 1) {
            return 1;
        }
        if (f[i][j] === -1) {
            f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        }
        return f[i][j];
    };
    return dfs(0, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths_with_obstacles(obstacle_grid: Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        let mut f = vec![vec![-1; n]; m];
        Self::dfs(0, 0, &obstacle_grid, &mut f)
    }

    fn dfs(i: usize, j: usize, obstacle_grid: &Vec<Vec<i32>>, f: &mut Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        if i >= m || j >= n || obstacle_grid[i][j] == 1 {
            return 0;
        }
        if i == m - 1 && j == n - 1 {
            return 1;
        }
        if f[i][j] != -1 {
            return f[i][j];
        }
        let down = Self::dfs(i + 1, j, obstacle_grid, f);
        let right = Self::dfs(i, j + 1, obstacle_grid, f);
        f[i][j] = down + right;
        f[i][j]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} obstacleGrid
 * @return {number}
 */
var uniquePathsWithObstacles = function (obstacleGrid) {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i, j) => {
        if (i >= m || j >= n || obstacleGrid[i][j] === 1) {
            return 0;
        }
        if (i === m - 1 && j === n - 1) {
            return 1;
        }
        if (f[i][j] === -1) {
            f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        }
        return f[i][j];
    };
    return dfs(0, 0);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã có độ phức tạp $O(mn)$ sau khi memoization, nhưng vẫn sử dụng đệ quy, với ngăn xếp ngầm sâu hơn và các hằng số lớn hơn. Chướng ngại vật cắt đứt hàng đầu tiên và cột đầu tiên, nên các biên đó không còn toàn là $1$; điền một bảng theo thứ tự từ dưới lên trực tiếp hơn.
>
> $f[i][j]$ là số lượng đường đi từ điểm bắt đầu: bằng $0$ trên chướng ngại vật, nếu không thì lấy từ phía trên và bên trái. Điền trước hàng đầu tiên và cột đầu tiên cho đến chướng ngại vật đầu tiên, sau đó điền phần bên trong, không cần đệ quy.

<!-- thinking:end -->

Chúng ta có thể sử dụng cách tiếp cận quy hoạch động bằng cách định nghĩa một mảng 2D $f$, trong đó $f[i][j]$ biểu thị số lượng đường đi từ ô $(0,0)$ đến ô $(i,j)$.

Trước tiên, chúng ta khởi tạo tất cả các giá trị trong cột đầu tiên và hàng đầu tiên của $f$, sau đó duyệt các hàng và cột còn lại với hai trường hợp:

- Nếu $\textit{obstacleGrid}[i][j] = 1$, điều đó có nghĩa là số lượng đường đi là $0$, nên $f[i][j] = 0$;
- Nếu $\textit{obstacleGrid}[i][j] = 0$, thì $f[i][j] = f[i - 1][j] + f[i][j - 1]$.

Cuối cùng, trả về $f[m - 1][n - 1]$.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Ở đây, $m$ và $n$ lần lượt là số hàng và số cột của lưới.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        f = [[0] * n for _ in range(m)]
        for i in range(m):
            if obstacleGrid[i][0] == 1:
                break
            f[i][0] = 1
        for j in range(n):
            if obstacleGrid[0][j] == 1:
                break
            f[0][j] = 1
        for i in range(1, m):
            for j in range(1, n):
                if obstacleGrid[i][j] == 0:
                    f[i][j] = f[i - 1][j] + f[i][j - 1]
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size(), n = obstacleGrid[0].size();
        vector<vector<int>> f(m, vector<int>(n));
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
};
```

#### Go

```go
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	m, n := len(obstacleGrid), len(obstacleGrid[0])
	f := make([][]int, m)
	for i := 0; i < m; i++ {
		f[i] = make([]int, n)
	}
	for i := 0; i < m && obstacleGrid[i][0] == 0; i++ {
		f[i][0] = 1
	}
	for j := 0; j < n && obstacleGrid[0][j] == 0; j++ {
		f[0][j] = 1
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if obstacleGrid[i][j] == 0 {
				f[i][j] = f[i-1][j] + f[i][j-1]
			}
		}
	}
	return f[m-1][n-1]
}
```

#### TypeScript

```ts
function uniquePathsWithObstacles(obstacleGrid: number[][]): number {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        if (obstacleGrid[i][0] === 1) {
            break;
        }
        f[i][0] = 1;
    }
    for (let i = 0; i < n; i++) {
        if (obstacleGrid[0][i] === 1) {
            break;
        }
        f[0][i] = 1;
    }
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (obstacleGrid[i][j] === 1) {
                continue;
            }
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths_with_obstacles(obstacle_grid: Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        let mut f = vec![vec![0; n]; m];
        for i in 0..n {
            if obstacle_grid[0][i] == 1 {
                break;
            }
            f[0][i] = 1;
        }
        for i in 0..m {
            if obstacle_grid[i][0] == 1 {
                break;
            }
            f[i][0] = 1;
        }
        for i in 1..m {
            for j in 1..n {
                if obstacle_grid[i][j] == 1 {
                    continue;
                }
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        f[m - 1][n - 1]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} obstacleGrid
 * @return {number}
 */
var uniquePathsWithObstacles = function (obstacleGrid) {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        if (obstacleGrid[i][0] === 1) {
            break;
        }
        f[i][0] = 1;
    }
    for (let i = 0; i < n; i++) {
        if (obstacleGrid[0][i] === 1) {
            break;
        }
        f[0][i] = 1;
    }
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (obstacleGrid[i][j] === 1) {
                continue;
            }
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
