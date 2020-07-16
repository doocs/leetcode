# [面试题 08.10. 颜色填充](https://leetcode-cn.com/problems/color-fill-lcci)

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


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) {
            return;
        }

        int color = image[sr][sc];
        if (color != newColor && color == oldColor) {
            image[sr][sc] = newColor;
            // up down left right
            dfs(image, sr, sc + 1, oldColor, newColor);
            dfs(image, sr, sc - 1, oldColor, newColor);
            dfs(image, sr + 1, sc, oldColor, newColor);
            dfs(image, sr - 1, sc, oldColor, newColor);
        }
    }
}
```

### ...
```

```
