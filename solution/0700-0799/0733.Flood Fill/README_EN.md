---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0733.Flood%20Fill/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Matrix
---

<!-- problem:start -->

# [733. Flood Fill](https://leetcode.com/problems/flood-fill)

[中文文档](/solution/0700-0799/0733.Flood%20Fill/README.md)

## Description

<!-- description:start -->

<p>You are given an image represented by an <code>m x n</code> grid of integers <code>image</code>, where <code>image[i][j]</code> represents the pixel value of the image. You are also given three integers <code>sr</code>, <code>sc</code>, and <code>color</code>. Your task is to perform a <strong>flood fill</strong> on the image starting from the pixel <code>image[sr][sc]</code>.</p>

<p>To perform a <strong>flood fill</strong>:</p>

<ol>
	<li>Begin with the starting pixel and change its color to <code>color</code>.</li>
	<li>Perform the same process for each pixel that is <strong>directly adjacent</strong> (pixels that share a side with the original pixel, either horizontally or vertically) and shares the <strong>same color</strong> as the starting pixel.</li>
	<li>Keep <strong>repeating</strong> this process by checking neighboring pixels of the <em>updated</em> pixels&nbsp;and modifying their color if it matches the original color of the starting pixel.</li>
	<li>The process <strong>stops</strong> when there are <strong>no more</strong> adjacent pixels of the original color to update.</li>
</ol>

<p>Return the <strong>modified</strong> image after performing the flood fill.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[2,2,2],[2,2,0],[2,0,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0733.Flood%20Fill/images/flood1-grid.jpg" style="width: 613px; height: 253px;" /></p>

<p>From the center of the image with position <code>(sr, sc) = (1, 1)</code> (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.</p>

<p>Note the bottom corner is <strong>not</strong> colored 2, because it is not horizontally or vertically connected to the starting pixel.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">[[0,0,0],[0,0,0]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == image.length</code></li>
	<li><code>n == image[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= image[i][j], color &lt; 2<sup>16</sup></code></li>
	<li><code>0 &lt;= sr &lt; m</code></li>
	<li><code>0 &lt;= sc &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We denote the initial pixel's color as $\textit{oc}$. If $\textit{oc}$ is not equal to the target color $\textit{color}$, we start a depth-first search from $(\textit{sr}, \textit{sc})$ to change the color of all eligible pixels to the target color.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns of the 2D array $\textit{image}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def floodFill(
        self, image: List[List[int]], sr: int, sc: int, color: int
    ) -> List[List[int]]:
        def dfs(i: int, j: int):
            image[i][j] = color
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < len(image) and 0 <= y < len(image[0]) and image[x][y] == oc:
                    dfs(x, y)

        oc = image[sr][sc]
        if oc != color:
            dirs = (-1, 0, 1, 0, -1)
            dfs(sr, sc)
        return image
```

#### Java

```java
class Solution {
    private int[][] image;
    private int oc;
    private int color;
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        oc = image[sr][sc];
        if (oc == color) {
            return image;
        }
        this.image = image;
        this.color = color;
        dfs(sr, sc);
        return image;
    }

    private void dfs(int i, int j) {
        image[i][j] = color;
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == oc) {
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
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        int m = image.size(), n = image[0].size();
        int oc = image[sr][sc];
        if (oc == color) {
            return image;
        }
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto dfs = [&](this auto&& dfs, int i, int j) -> void {
            image[i][j] = color;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && image[x][y] == oc) {
                    dfs(x, y);
                }
            }
        };
        dfs(sr, sc);
        return image;
    }
};
```

#### Go

```go
func floodFill(image [][]int, sr int, sc int, color int) [][]int {
	m, n := len(image), len(image[0])
	oc := image[sr][sc]
	if oc == color {
		return image
	}

	dirs := []int{-1, 0, 1, 0, -1}

	var dfs func(i, j int)
	dfs = func(i, j int) {
		image[i][j] = color
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && image[x][y] == oc {
				dfs(x, y)
			}
		}
	}

	dfs(sr, sc)
	return image
}
```

#### TypeScript

```ts
function floodFill(image: number[][], sr: number, sc: number, color: number): number[][] {
    const [m, n] = [image.length, image[0].length];
    const oc = image[sr][sc];
    if (oc === color) {
        return image;
    }

    const dirs = [-1, 0, 1, 0, -1];

    const dfs = (i: number, j: number): void => {
        image[i][j] = color;
        for (let k = 0; k < 4; k++) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && image[x][y] === oc) {
                dfs(x, y);
            }
        }
    };

    dfs(sr, sc);
    return image;
}
```

#### Rust

```rust
impl Solution {
    pub fn flood_fill(mut image: Vec<Vec<i32>>, sr: i32, sc: i32, color: i32) -> Vec<Vec<i32>> {
        let m = image.len();
        let n = image[0].len();
        let sr = sr as usize;
        let sc = sc as usize;

        let oc = image[sr][sc];
        if oc == color {
            return image;
        }
        let dirs = [-1, 0, 1, 0, -1];
        fn dfs(
            image: &mut Vec<Vec<i32>>,
            i: usize,
            j: usize,
            oc: i32,
            color: i32,
            m: usize,
            n: usize,
            dirs: &[i32; 5],
        ) {
            image[i][j] = color;
            for k in 0..4 {
                let x = i as isize + dirs[k] as isize;
                let y = j as isize + dirs[k + 1] as isize;
                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let x = x as usize;
                    let y = y as usize;
                    if image[x][y] == oc {
                        dfs(image, x, y, oc, color, m, n, dirs);
                    }
                }
            }
        }

        dfs(&mut image, sr, sc, oc, color, m, n, &dirs);
        image
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: BFS

We first check if the initial pixel's color is equal to the target color. If it is, we return the original image directly. Otherwise, we can use the breadth-first search method, starting from $(\textit{sr}, \textit{sc})$, to change the color of all eligible pixels to the target color.

Specifically, we define a queue $\textit{q}$ and add the initial pixel $(\textit{sr}, \textit{sc})$ to the queue. Then, we continuously take pixels $(i, j)$ from the queue, change their color to the target color, and add the pixels in the four directions (up, down, left, right) that have the same original color as the initial pixel to the queue. When the queue is empty, we have completed the flood fill.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns of the 2D array $\textit{image}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def floodFill(
        self, image: List[List[int]], sr: int, sc: int, color: int
    ) -> List[List[int]]:
        if image[sr][sc] == color:
            return image
        q = deque([(sr, sc)])
        oc = image[sr][sc]
        image[sr][sc] = color
        dirs = (-1, 0, 1, 0, -1)
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < len(image) and 0 <= y < len(image[0]) and image[x][y] == oc:
                    q.append((x, y))
                    image[x][y] = color
        return image
```

#### Java

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc});
        int oc = image[sr][sc];
        image[sr][sc] = color;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length
                    && image[x][y] == oc) {
                    q.offer(new int[] {x, y});
                    image[x][y] = color;
                }
            }
        }
        return image;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;
        int oc = image[sr][sc];
        image[sr][sc] = color;
        queue<pair<int, int>> q;
        q.push({sr, sc});
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [a, b] = q.front();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = a + dirs[k];
                int y = b + dirs[k + 1];
                if (x >= 0 && x < image.size() && y >= 0 && y < image[0].size() && image[x][y] == oc) {
                    q.push({x, y});
                    image[x][y] = color;
                }
            }
        }
        return image;
    }
};
```

#### Go

```go
func floodFill(image [][]int, sr int, sc int, color int) [][]int {
	if image[sr][sc] == color {
		return image
	}
	oc := image[sr][sc]
	q := [][]int{[]int{sr, sc}}
	image[sr][sc] = color
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := p[0]+dirs[k], p[1]+dirs[k+1]
			if x >= 0 && x < len(image) && y >= 0 && y < len(image[0]) && image[x][y] == oc {
				q = append(q, []int{x, y})
				image[x][y] = color
			}
		}
	}
	return image
}
```

#### TypeScript

```ts
function floodFill(image: number[][], sr: number, sc: number, color: number): number[][] {
    if (image[sr][sc] === color) {
        return image;
    }

    const oc = image[sr][sc];
    image[sr][sc] = color;

    const q: [number, number][] = [];
    q.push([sr, sc]);

    const dirs = [-1, 0, 1, 0, -1];
    const [m, n] = [image.length, image[0].length];

    while (q.length > 0) {
        const [a, b] = q.shift()!;
        for (let k = 0; k < 4; ++k) {
            const x = a + dirs[k];
            const y = b + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && image[x][y] === oc) {
                q.push([x, y]);
                image[x][y] = color;
            }
        }
    }

    return image;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn flood_fill(mut image: Vec<Vec<i32>>, sr: i32, sc: i32, color: i32) -> Vec<Vec<i32>> {
        let m = image.len();
        let n = image[0].len();
        let (sr, sc) = (sr as usize, sc as usize);

        if image[sr][sc] == color {
            return image;
        }

        let oc = image[sr][sc];
        image[sr][sc] = color;

        let mut q = VecDeque::new();
        q.push_back((sr, sc));

        let dirs = [-1, 0, 1, 0, -1];

        while let Some((i, j)) = q.pop_front() {
            for k in 0..4 {
                let x = i as isize + dirs[k] as isize;
                let y = j as isize + dirs[k + 1] as isize;

                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let (x, y) = (x as usize, y as usize);
                    if image[x][y] == oc {
                        q.push_back((x, y));
                        image[x][y] = color;
                    }
                }
            }
        }

        image
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
