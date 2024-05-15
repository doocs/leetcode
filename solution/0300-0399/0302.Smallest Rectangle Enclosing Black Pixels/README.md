---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0302.Smallest%20Rectangle%20Enclosing%20Black%20Pixels/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 二分查找
    - 矩阵
---

# [302. 包含全部黑色像素的最小矩形 🔒](https://leetcode.cn/problems/smallest-rectangle-enclosing-black-pixels)

[English Version](/solution/0300-0399/0302.Smallest%20Rectangle%20Enclosing%20Black%20Pixels/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>图片在计算机处理中往往是使用二维矩阵来表示的。</p>

<p>给你一个大小为 <code>m x n</code> 的二进制矩阵&nbsp;<code>image</code> 表示一张黑白图片，<code>0</code>&nbsp;代表白色像素，<code>1</code>&nbsp;代表黑色像素。</p>

<p>黑色像素相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素。像素点是水平或竖直方向连接的。</p>

<p>给你两个整数 <code>x</code> 和 <code>y</code> 表示某一个黑色像素的位置，请你找出包含全部黑色像素的最小矩形（与坐标轴对齐），并返回该矩形的面积。</p>

<p>你必须设计并实现一个时间复杂度低于&nbsp;<code>O(mn)</code> 的算法来解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0302.Smallest%20Rectangle%20Enclosing%20Black%20Pixels/images/pixel-grid.jpg" style="width: 333px; height: 253px;" />
<pre>
<strong>输入：</strong>image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>image = [["1"]], x = 0, y = 0
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == image.length</code></li>
	<li><code>n == image[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>image[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
	<li><code>1 &lt;= x &lt; m</code></li>
	<li><code>1 &lt;= y &lt; n</code></li>
	<li><code>image[x][y] == '1'</code></li>
	<li><code>image</code> 中的黑色像素仅形成一个 <strong>组件</strong></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def minArea(self, image: List[List[str]], x: int, y: int) -> int:
        m, n = len(image), len(image[0])
        left, right = 0, x
        while left < right:
            mid = (left + right) >> 1
            c = 0
            while c < n and image[mid][c] == '0':
                c += 1
            if c < n:
                right = mid
            else:
                left = mid + 1
        u = left
        left, right = x, m - 1
        while left < right:
            mid = (left + right + 1) >> 1
            c = 0
            while c < n and image[mid][c] == '0':
                c += 1
            if c < n:
                left = mid
            else:
                right = mid - 1
        d = left
        left, right = 0, y
        while left < right:
            mid = (left + right) >> 1
            r = 0
            while r < m and image[r][mid] == '0':
                r += 1
            if r < m:
                right = mid
            else:
                left = mid + 1
        l = left
        left, right = y, n - 1
        while left < right:
            mid = (left + right + 1) >> 1
            r = 0
            while r < m and image[r][mid] == '0':
                r += 1
            if r < m:
                left = mid
            else:
                right = mid - 1
        r = left
        return (d - u + 1) * (r - l + 1)
```

```java
class Solution {

    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right) >> 1;
            int c = 0;
            while (c < n && image[mid][c] == '0') {
                ++c;
            }
            if (c < n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int u = left;
        left = x;
        right = m - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            int c = 0;
            while (c < n && image[mid][c] == '0') {
                ++c;
            }
            if (c < n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int d = left;
        left = 0;
        right = y;
        while (left < right) {
            int mid = (left + right) >> 1;
            int r = 0;
            while (r < m && image[r][mid] == '0') {
                ++r;
            }
            if (r < m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int l = left;
        left = y;
        right = n - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            int r = 0;
            while (r < m && image[r][mid] == '0') {
                ++r;
            }
            if (r < m) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int r = left;
        return (d - u + 1) * (r - l + 1);
    }
}
```

```cpp
class Solution {
public:
    int minArea(vector<vector<char>>& image, int x, int y) {
        int m = image.size(), n = image[0].size();
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right) >> 1;
            int c = 0;
            while (c < n && image[mid][c] == '0') ++c;
            if (c < n)
                right = mid;
            else
                left = mid + 1;
        }
        int u = left;
        left = x;
        right = m - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            int c = 0;
            while (c < n && image[mid][c] == '0') ++c;
            if (c < n)
                left = mid;
            else
                right = mid - 1;
        }
        int d = left;
        left = 0;
        right = y;
        while (left < right) {
            int mid = (left + right) >> 1;
            int r = 0;
            while (r < m && image[r][mid] == '0') ++r;
            if (r < m)
                right = mid;
            else
                left = mid + 1;
        }
        int l = left;
        left = y;
        right = n - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            int r = 0;
            while (r < m && image[r][mid] == '0') ++r;
            if (r < m)
                left = mid;
            else
                right = mid - 1;
        }
        int r = left;
        return (d - u + 1) * (r - l + 1);
    }
};
```

```go
func minArea(image [][]byte, x int, y int) int {
	m, n := len(image), len(image[0])
	left, right := 0, x
	for left < right {
		mid := (left + right) >> 1
		c := 0
		for c < n && image[mid][c] == '0' {
			c++
		}
		if c < n {
			right = mid
		} else {
			left = mid + 1
		}
	}
	u := left
	left, right = x, m-1
	for left < right {
		mid := (left + right + 1) >> 1
		c := 0
		for c < n && image[mid][c] == '0' {
			c++
		}
		if c < n {
			left = mid
		} else {
			right = mid - 1
		}
	}
	d := left
	left, right = 0, y
	for left < right {
		mid := (left + right) >> 1
		r := 0
		for r < m && image[r][mid] == '0' {
			r++
		}
		if r < m {
			right = mid
		} else {
			left = mid + 1
		}
	}
	l := left
	left, right = y, n-1
	for left < right {
		mid := (left + right + 1) >> 1
		r := 0
		for r < m && image[r][mid] == '0' {
			r++
		}
		if r < m {
			left = mid
		} else {
			right = mid - 1
		}
	}
	r := left
	return (d - u + 1) * (r - l + 1)
}
```

<!-- tabs:end -->

<!-- end -->
