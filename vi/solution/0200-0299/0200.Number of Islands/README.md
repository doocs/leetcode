---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Matrix
---

<!-- problem:start -->

# [200. Number of Islands](https://leetcode.com/problems/number-of-islands)

[中文文档](/solution/0200-0299/0200.Number%20of%20Islands/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một lưới nhị phân 2D <code>m x n</code> <code>grid</code> biểu diễn bản đồ gồm các ô <code>&#39;1&#39;</code> (đất) và <code>&#39;0&#39;</code> (nước), hãy trả về <em>số lượng đảo</em>.</p>

<p>Một <strong>đảo</strong> được bao quanh bởi nước và được hình thành bằng cách nối các ô đất kề nhau theo chiều ngang hoặc chiều dọc. Có thể giả sử cả bốn cạnh của lưới đều được bao quanh bởi nước.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> grid = [
  [&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;0&quot;],
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;],
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;0&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;]
]
<strong>Đầu ra:</strong> 1
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> grid = [
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;1&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;],
  [&quot;0&quot;,&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;1&quot;]
]
<strong>Đầu ra:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>grid[i][j]</code> là <code>&#39;0&#39;</code> hoặc <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS

<!-- thinking:start -->

> **Tư duy**
>
> Nếu tính mỗi ô có giá trị $1$ là một đảo riêng, chúng ta sẽ đếm trùng phần đất liên thông 4 hướng. Với $m,n \le 300$, có thể duyệt toàn bộ lưới, nhưng mỗi thành phần liên thông phải được xử lý trong một lần tìm kiếm.
>
> Các ô đất kề nhau tạo thành một thành phần, vì vậy DFS bắt đầu từ bất kỳ ô đất nào chưa được xét sẽ bao phủ toàn bộ hòn đảo. Chúng ta quét lưới, bắt đầu DFS tại mỗi ô $1$, đổi đất đã duyệt thành $0$, và tăng đáp án một lần sau mỗi lần tìm kiếm.

<!-- thinking:end -->

Chúng ta có thể dùng tìm kiếm theo chiều sâu (DFS) để duyệt qua từng hòn đảo. Chúng ta duyệt qua từng ô $(i, j)$ trong lưới. Nếu giá trị của ô là '1', điều đó có nghĩa là chúng ta đã tìm thấy một hòn đảo mới. Chúng ta có thể bắt đầu DFS từ ô này, đánh dấu tất cả các ô đất liên thông thành '0' để tránh đếm trùng. Mỗi khi tìm thấy một hòn đảo mới, chúng ta tăng số lượng đảo lên 1.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó $m$ và $n$ lần lượt là số hàng và số cột trong lưới.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def dfs(i, j):
            grid[i][j] = '0'
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == '1':
                    dfs(x, y)

        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    dfs(i, j)
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    private char[][] grid;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        grid[i][j] = '0';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(x, y);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        auto dfs = [&](this auto&& dfs, int i, int j) -> void {
            grid[i][j] = '0';
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == '1') {
                    dfs(x, y);
                }
            }
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = '0'
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1' {
				dfs(x, y)
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				dfs(i, j)
				ans++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];

    const dfs = (i: number, j: number) => {
        grid[i][j] = '0';
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (grid[x]?.[y] === '1') {
                dfs(x, y);
            }
        }
    };

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === '1') {
                dfs(i, j);
                ans++;
            }
        }
    }

    return ans;
}
```

#### Rust

```rust
const DIRS: [i32; 5] = [-1, 0, 1, 0, -1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        fn dfs(grid: &mut Vec<Vec<char>>, i: usize, j: usize) {
            grid[i][j] = '0';
            for k in 0..4 {
                let x = (i as i32) + DIRS[k];
                let y = (j as i32) + DIRS[k + 1];
                if x >= 0
                    && (x as usize) < grid.len()
                    && y >= 0
                    && (y as usize) < grid[0].len()
                    && grid[x as usize][y as usize] == '1'
                {
                    dfs(grid, x as usize, y as usize);
                }
            }
        }

        let mut grid = grid;
        let mut ans = 0;
        for i in 0..grid.len() {
            for j in 0..grid[0].len() {
                if grid[i][j] == '1' {
                    dfs(&mut grid, i, j);
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int NumIslands(char[][] grid) {
        int m = grid.Length;
        int n = grid[0].Length;
        int ans = 0;
        int[] dirs = { -1, 0, 1, 0, -1 };

        void Dfs(int i, int j) {
            grid[i][j] = '0';
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m &&
                    y >= 0 && y < n &&
                    grid[x][y] == '1') {
                    Dfs(x, y);
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    Dfs(i, j);
                    ans++;
                }
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: BFS

<!-- thinking:start -->

> **Tư duy**
>
> DFS đã đếm được các thành phần, nhưng độ sâu đệ quy có thể đạt $O(mn)$. Có thể mở rộng cùng tính liên thông 4 hướng bằng một hàng đợi.
>
> Thứ tự quét vẫn giữ nguyên; chỉ thay việc tìm kiếm bằng BFS tường minh, và không gian được giới hạn bởi hàng đợi.

<!-- thinking:end -->

Chúng ta cũng có thể dùng tìm kiếm theo chiều rộng (BFS) để duyệt qua từng hòn đảo. Chúng ta duyệt qua từng ô $(i, j)$ trong lưới. Nếu giá trị của ô là '1', điều đó có nghĩa là chúng ta đã tìm thấy một hòn đảo mới. Chúng ta có thể bắt đầu BFS từ ô này, đánh dấu tất cả các ô đất liên thông thành '0' để tránh đếm trùng. Mỗi khi tìm thấy một hòn đảo mới, chúng ta tăng số lượng đảo lên 1.

Quy trình BFS cụ thể như sau:

1. Đưa ô bắt đầu $(i, j)$ vào hàng đợi và đánh dấu giá trị của nó là '0'.
2. Trong khi hàng đợi chưa rỗng, thực hiện các thao tác sau:
    - Lấy ra một ô $p$ khỏi hàng đợi.
    - Duyệt qua bốn ô kề nhau $(x, y)$ của $p$. Nếu $(x, y)$ nằm trong phạm vi của lưới và giá trị của nó là '1', đưa ô đó vào hàng đợi và đánh dấu giá trị của nó là '0'.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó $m$ và $n$ lần lượt là số hàng và số cột trong lưới.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def bfs(i, j):
            grid[i][j] = '0'
            q = deque([(i, j)])
            while q:
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y] == '1':
                        q.append((x, y))
                        grid[x][y] = 0

        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    bfs(i, j)
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    private char[][] grid;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    private void bfs(int i, int j) {
        grid[i][j] = '0';
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k];
                int y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    q.offer(new int[] {x, y});
                    grid[x][y] = '0';
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        auto bfs = [&](int i, int j) -> void {
            grid[i][j] = '0';
            queue<pair<int, int>> q;
            q.push({i, j});
            while (!q.empty()) {
                auto [a, b] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = a + dirs[k];
                    int y = b + dirs[k + 1];
                    if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == '1') {
                        q.push({x, y});
                        grid[x][y] = '0';
                    }
                }
            }
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	bfs := func(i, j int) {
		grid[i][j] = '0'
		q := [][]int{[]int{i, j}}
		dirs := []int{-1, 0, 1, 0, -1}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1' {
					q = append(q, []int{x, y})
					grid[x][y] = '0'
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				bfs(i, j)
				ans++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    const bfs = (i: number, j: number) => {
        grid[i][j] = '0';
        const q = [[i, j]];
        const dirs = [-1, 0, 1, 0, -1];
        for (const [i, j] of q) {
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (grid[x]?.[y] == '1') {
                    q.push([x, y]);
                    grid[x][y] = '0';
                }
            }
        }
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                bfs(i, j);
                ++ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

const DIRS: [i32; 5] = [-1, 0, 1, 0, -1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        fn bfs(grid: &mut Vec<Vec<char>>, i: usize, j: usize) {
            grid[i][j] = '0';
            let mut queue = VecDeque::from([(i, j)]);
            while !queue.is_empty() {
                let (i, j) = queue.pop_front().unwrap();
                for k in 0..4 {
                    let x = (i as i32) + DIRS[k];
                    let y = (j as i32) + DIRS[k + 1];
                    if x >= 0
                        && (x as usize) < grid.len()
                        && y >= 0
                        && (y as usize) < grid[0].len()
                        && grid[x as usize][y as usize] == '1'
                    {
                        grid[x as usize][y as usize] = '0';
                        queue.push_back((x as usize, y as usize));
                    }
                }
            }
        }

        let mut grid = grid;
        let mut ans = 0;
        for i in 0..grid.len() {
            for j in 0..grid[0].len() {
                if grid[i][j] == '1' {
                    bfs(&mut grid, i, j);
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Union-Find

<!-- thinking:start -->

> **Tư duy**
>
> Nếu muốn biểu diễn tính kề nhau dưới dạng một phân hoạch tường minh, hãy coi mỗi ô đất là một nút union-find và hợp nhất nó với các ô đất lân cận.
>
> Số lượng đảo là số thành phần sau khi quét. Các thao tác hợp nhất và tìm kiếm gần tuyến tính; không gian vẫn tỉ lệ với kích thước lưới.

<!-- thinking:end -->

Chúng ta có thể dùng cấu trúc dữ liệu Union-Find để giải bài toán này. Chúng ta duyệt qua từng ô $(i, j)$ trong lưới, và nếu giá trị của ô là '1', chúng ta hợp nhất nó với các ô đất kề nhau. Cuối cùng, chúng ta đếm số nút gốc khác nhau trong cấu trúc Union-Find, đó chính là số lượng đảo.

Độ phức tạp thời gian là $O(m \times n \times \log (m \times n))$, và độ phức tạp không gian là $O(m \times n)$. Trong đó $m$ và $n$ lần lượt là số hàng và số cột trong lưới.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        dirs = (0, 1, 0)
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        if x < m and y < n and grid[x][y] == '1':
                            p[find(i * n + j)] = find(x * n + y)
        return sum(
            grid[i][j] == '1' and i * n + j == find(i * n + j)
            for i in range(m)
            for j in range(n)
        )
```

#### Java

```java
class Solution {
    private int[] p;

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid[x][y] == '1') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && i * n + j == find(i * n + j)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<int> p(m * n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int dirs[3] = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid[x][y] == '1') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += grid[i][j] == '1' && i * n + j == find(i * n + j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{1, 0, 1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				for k := 0; k < 2; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x < m && y < n && grid[x][y] == '1' {
						p[find(x*n+y)] = find(i*n + j)
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' && i*n+j == find(i*n+j) {
				ans++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const p: number[] = Array.from({ length: m * n }, (_, i) => i);
    function find(x: number): number {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    const dirs = [1, 0, 1];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                for (let k = 0; k < 2; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (grid[x]?.[y] == '1') {
                        p[find(i * n + j)] = find(x * n + y);
                    }
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1' && i * n + j == find(i * n + j)) {
                ++ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
const DIRS: [usize; 3] = [1, 0, 1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut p: Vec<i32> = (0..(m * n) as i32).collect();

        fn find(p: &mut Vec<i32>, x: usize) -> i32 {
            if p[x] != (x as i32) {
                p[x] = find(p, p[x] as usize);
            }
            p[x]
        }

        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == '1' {
                    for k in 0..2 {
                        let x = i + DIRS[k];
                        let y = j + DIRS[k + 1];
                        if x < m && y < n && grid[x][y] == '1' {
                            let f1 = find(&mut p, x * n + y);
                            let f2 = find(&mut p, i * n + j);
                            p[f1 as usize] = f2;
                        }
                    }
                }
            }
        }

        let mut ans = 0;
        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == '1' && p[i * n + j] == ((i * n + j) as i32) {
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
