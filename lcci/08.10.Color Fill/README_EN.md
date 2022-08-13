# [08.10. Color Fill](https://leetcode.cn/problems/color-fill-lcci)

[中文文档](/lcci/08.10.Color%20Fill/README.md)

## Description

<p>Implement the &quot;paint fill&quot; function that one might see on many image editing programs. That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color, fill in the surrounding area until the color changes from the original color.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong>Input</strong>: 

image = [[1,1,1],[1,1,0],[1,0,1]] 

sr = 1, sc = 1, newColor = 2

<strong>Output</strong>: [[2,2,2],[2,2,0],[2,0,1]]

<strong>Explanation</strong>: 

From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 

by a path of the same color as the starting pixel are colored with the new color.

Note the bottom corner is not colored 2, because it is not 4-directionally connected

to the starting pixel.</pre>

<p><b>Note:</b></p>

<ul>
	<li>The length of&nbsp;<code>image</code>&nbsp;and&nbsp;<code>image[0]</code>&nbsp;will be in the range&nbsp;<code>[1, 50]</code>.</li>
	<li>The given starting pixel will satisfy&nbsp;<code>0 &lt;= sr &lt; image.length</code>&nbsp;and&nbsp;<code>0 &lt;= sc &lt; image[0].length</code>.</li>
	<li>The value of each color in&nbsp;<code>image[i][j]</code>&nbsp;and&nbsp;<code>newColor</code>&nbsp;will be an integer in&nbsp;<code>[0, 65535]</code>.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def floodFill(
        self, image: List[List[int]], sr: int, sc: int, newColor: int
    ) -> List[List[int]]:
        def dfs(i, j, oc, nc):
            if (
                i < 0
                or i >= len(image)
                or j < 0
                or j >= len(image[0])
                or image[i][j] != oc
                or image[i][j] == nc
            ):
                return
            image[i][j] = nc
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                dfs(i + x, j + y, oc, nc)

        dfs(sr, sc, image[sr][sc], newColor)
        return image
```

### **Java**

```java
class Solution {
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int oc, int nc) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oc || image[i][j] == nc) {
            return;
        }
        image[i][j] = nc;
        for (int[] dir : dirs) {
            dfs(image, i + dir[0], j + dir[1], oc, nc);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    void dfs(vector<vector<int>>& image, int i, int j, int oc, int nc) {
        if (i < 0 || i >= image.size() || j < 0 || j >= image[0].size() || image[i][j] != oc || image[i][j] == nc) return;
        image[i][j] = nc;
        for (auto& dir : dirs) dfs(image, i + dir[0], j + dir[1], oc, nc);
    }
};
```

### **Go**

```go
func floodFill(image [][]int, sr int, sc int, newColor int) [][]int {
	dfs(image, sr, sc, image[sr][sc], newColor)
	return image
}

func dfs(image [][]int, i, j, oc, nc int) {
	if i < 0 || i >= len(image) || j < 0 || j >= len(image[0]) || image[i][j] != oc || image[i][j] == nc {
		return
	}
	image[i][j] = nc
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, dir := range dirs {
		dfs(image, i+dir[0], j+dir[1], oc, nc)
	}
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
