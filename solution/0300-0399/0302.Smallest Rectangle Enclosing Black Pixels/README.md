# [302. 包含全部黑色像素的最小矩形](https://leetcode-cn.com/problems/smallest-rectangle-enclosing-black-pixels)

[English Version](/solution/0300-0399/0302.Smallest%20Rectangle%20Enclosing%20Black%20Pixels/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>图片在计算机处理中往往是使用二维矩阵来表示的。</p>

<p>假设，这里我们用的是一张黑白的图片，那么&nbsp;<code>0</code>&nbsp;代表白色像素，<code>1</code>&nbsp;代表黑色像素。</p>

<p>其中黑色的像素他们相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素（像素点是水平或竖直方向连接的）。</p>

<p>那么，给出某一个黑色像素点&nbsp;<code>(x, y)</code>&nbsp;的位置，你是否可以找出包含全部黑色像素的最小矩形（与坐标轴对齐）的面积呢？</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0302.Smallest%20Rectangle%20Enclosing%20Black%20Pixels/images/302_smallest_rectangle_enclosing_black_pixels.png" style="width: 100px;"></p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
[
  &quot;0010&quot;,
  &quot;0110&quot;,
  &quot;0100&quot;
]
<code>和 x = 0, </code><code>y = 2</code>

<strong>输出:</strong> 6
</pre>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找，时间复杂度 `O(mlogn + nlogm)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    int minArea(vector<vector<char>>& image, int x, int y) {
        int m = image.size(), n = image[0].size();
        int left = 0, right = x;
        while (left < right)
        {
            int mid = (left + right) >> 1;
            int c = 0;
            while (c < n && image[mid][c] == '0') ++c;
            if (c < n) right = mid;
            else left = mid + 1;
        }
        int u = left;
        left = x;
        right = m - 1;
        while (left < right)
        {
            int mid = (left + right + 1) >> 1;
            int c = 0;
            while (c < n && image[mid][c] == '0') ++c;
            if (c < n) left = mid;
            else right = mid - 1;
        }
        int d = left;
        left = 0;
        right = y;
        while (left < right)
        {
            int mid = (left + right) >> 1;
            int r = 0;
            while (r < m && image[r][mid] == '0') ++r;
            if (r < m) right = mid;
            else left = mid + 1;
        }
        int l = left;
        left = y;
        right = n - 1;
        while (left < right)
        {
            int mid = (left + right + 1) >> 1;
            int r = 0;
            while (r < m && image[r][mid] == '0') ++r;
            if (r < m) left = mid;
            else right = mid - 1;
        }
        int r = left;
        return (d - u + 1) * (r - l + 1);
    }
};
```

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
