# [面试题 08.10. 颜色填充](https://leetcode.cn/problems/color-fill-lcci)

[English Version](/lcci/08.10.Color%20Fill/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
