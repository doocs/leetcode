---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.10.Color%20Fill/README.md
---

<!-- problem:start -->

# [面试题 08.10. 颜色填充](https://leetcode.cn/problems/color-fill-lcci)

[English Version](/lcci/08.10.Color%20Fill/README_EN.md)

## 题目描述

<!-- description:start -->

<p>颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：
image = [[1,1,1],[1,1,0],[1,0,1]] 
sr = 1, sc = 1, newColor = 2
<strong> 输出</strong>：[[2,2,2],[2,2,0],[2,0,1]]
<strong> 解释</strong>: 
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
</pre>

<p> <strong>说明：</strong></p>

<ol>
<li>image 和 image[0] 的长度在范围 [1, 50] 内。</li>
<li>给出的初始点将满足 0 &lt;= sr &lt; image.length 和 0 &lt;= sc &lt; image[0].length。</li>
<li>image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $dfs(i, j)$，表示从 $(i, j)$ 开始填充颜色。如果 $(i, j)$ 不在图像范围内，或者 $(i, j)$ 的颜色不是原来的颜色，或者 $(i, j)$ 的颜色已经被填充成新的颜色，就返回。否则，将 $(i, j)$ 的颜色填充成新的颜色，然后递归搜索 $(i, j)$ 的上下左右四个方向。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为图像的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def floodFill(
        self, image: List[List[int]], sr: int, sc: int, newColor: int
    ) -> List[List[int]]:
        def dfs(i, j):
            if (
                not 0 <= i < m
                or not 0 <= j < n
                or image[i][j] != oc
                or image[i][j] == newColor
            ):
                return
            image[i][j] = newColor
            for a, b in pairwise(dirs):
                dfs(i + a, j + b)

        dirs = (-1, 0, 1, 0, -1)
        m, n = len(image), len(image[0])
        oc = image[sr][sc]
        dfs(sr, sc)
        return image
```

```java
class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] image;
    private int nc;
    private int oc;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        nc = newColor;
        oc = image[sr][sc];
        this.image = image;
        dfs(sr, sc);
        return image;
    }

    private void dfs(int i, int j) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oc
            || image[i][j] == nc) {
            return;
        }
        image[i][j] = nc;
        for (int k = 0; k < 4; ++k) {
            dfs(i + dirs[k], j + dirs[k + 1]);
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        int m = image.size(), n = image[0].size();
        int oc = image[sr][sc];
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int)> dfs = [&](int i, int j) {
            if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != oc || image[i][j] == newColor) {
                return;
            }
            image[i][j] = newColor;
            for (int k = 0; k < 4; ++k) {
                dfs(i + dirs[k], j + dirs[k + 1]);
            }
        };
        dfs(sr, sc);
        return image;
    }
};
```

```go
func floodFill(image [][]int, sr int, sc int, newColor int) [][]int {
	oc := image[sr][sc]
	m, n := len(image), len(image[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if i < 0 || i >= m || j < 0 || j >= n || image[i][j] != oc || image[i][j] == newColor {
			return
		}
		image[i][j] = newColor
		for k := 0; k < 4; k++ {
			dfs(i+dirs[k], j+dirs[k+1])
		}
	}
	dfs(sr, sc)
	return image
}
```

```ts
function floodFill(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    const dfs = (i: number, j: number): void => {
        if (i < 0 || i >= m) {
            return;
        }
        if (j < 0 || j >= n) {
            return;
        }
        if (image[i][j] !== oc || image[i][j] === nc) {
            return;
        }
        image[i][j] = nc;
        for (let k = 0; k < 4; ++k) {
            dfs(i + dirs[k], j + dirs[k + 1]);
        }
    };
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const [m, n] = [image.length, image[0].length];
    const oc = image[sr][sc];
    const nc = newColor;
    dfs(sr, sc);
    return image;
}
```

```rust
impl Solution {
    fn dfs(i: usize, j: usize, target: i32, new_color: i32, image: &mut Vec<Vec<i32>>) {
        if image[i][j] != target {
            return;
        }
        image[i][j] = new_color;
        if i != 0 {
            Self::dfs(i - 1, j, target, new_color, image);
        }
        if j != 0 {
            Self::dfs(i, j - 1, target, new_color, image);
        }
        if i + 1 != image.len() {
            Self::dfs(i + 1, j, target, new_color, image);
        }
        if j + 1 != image[0].len() {
            Self::dfs(i, j + 1, target, new_color, image);
        }
    }

    pub fn flood_fill(mut image: Vec<Vec<i32>>, sr: i32, sc: i32, new_color: i32) -> Vec<Vec<i32>> {
        let (sr, sc) = (sr as usize, sc as usize);
        let target = image[sr][sc];
        if target == new_color {
            return image;
        }
        Self::dfs(sr, sc, target, new_color, &mut image);
        image
    }
}
```

```swift
class Solution {
    private var dirs = [-1, 0, 1, 0, -1]
    private var image: [[Int]] = []
    private var nc: Int = 0
    private var oc: Int = 0

    func floodFill(_ image: inout [[Int]], _ sr: Int, _ sc: Int, _ newColor: Int) -> [[Int]] {
        self.nc = newColor
        self.oc = image[sr][sc]
        self.image = image
        dfs(sr, sc)
        return self.image
    }

    private func dfs(_ i: Int, _ j: Int) {
        if i < 0 || i >= image.count || j < 0 || j >= image[0].count || image[i][j] != oc || image[i][j] == nc {
            return
        }
        image[i][j] = nc
        for k in 0..<4 {
            dfs(i + dirs[k], j + dirs[k + 1])
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

### 方法二：BFS

我们可以使用广度优先搜索的方法，从起始点开始，将起始点的颜色填充成新的颜色，然后将起始点加入队列。每次从队列中取出一个点，然后将其上下左右四个方向的点加入队列，直到队列为空。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为图像的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def floodFill(
        self, image: List[List[int]], sr: int, sc: int, newColor: int
    ) -> List[List[int]]:
        if image[sr][sc] == newColor:
            return image
        q = deque([(sr, sc)])
        oc = image[sr][sc]
        image[sr][sc] = newColor
        dirs = (-1, 0, 1, 0, -1)
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < len(image) and 0 <= y < len(image[0]) and image[x][y] == oc:
                    q.append((x, y))
                    image[x][y] = newColor
        return image
```

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc});
        int oc = image[sr][sc];
        image[sr][sc] = newColor;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length
                    && image[x][y] == oc) {
                    q.offer(new int[] {x, y});
                    image[x][y] = newColor;
                }
            }
        }
        return image;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        int oc = image[sr][sc];
        image[sr][sc] = newColor;
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
                    image[x][y] = newColor;
                }
            }
        }
        return image;
    }
};
```

```go
func floodFill(image [][]int, sr int, sc int, newColor int) [][]int {
	if image[sr][sc] == newColor {
		return image
	}
	oc := image[sr][sc]
	q := [][]int{[]int{sr, sc}}
	image[sr][sc] = newColor
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := p[0]+dirs[k], p[1]+dirs[k+1]
			if x >= 0 && x < len(image) && y >= 0 && y < len(image[0]) && image[x][y] == oc {
				q = append(q, []int{x, y})
				image[x][y] = newColor
			}
		}
	}
	return image
}
```

```ts
function floodFill(image: number[][], sr: number, sc: number, newColor: number): number[][] {
    if (image[sr][sc] === newColor) {
        return image;
    }
    const q: number[][] = [[sr, sc]];
    const oc = image[sr][sc];
    image[sr][sc] = newColor;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.length) {
        const [i, j] = q.pop()!;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] === oc) {
                q.push([x, y]);
                image[x][y] = newColor;
            }
        }
    }
    return image;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
