# [223. 矩形面积](https://leetcode.cn/problems/rectangle-area)

[English Version](/solution/0200-0299/0223.Rectangle%20Area/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <strong>二维</strong> 平面上两个 <strong>由直线构成且边与坐标轴平行/垂直</strong> 的矩形，请你计算并返回两个矩形覆盖的总面积。</p>

<p>每个矩形由其 <strong>左下</strong> 顶点和 <strong>右上</strong> 顶点坐标表示：</p>

<div class="MachineTrans-Lines">
<ul>
	<li class="MachineTrans-lang-zh-CN">第一个矩形由其左下顶点 <code>(ax1, ay1)</code> 和右上顶点 <code>(ax2, ay2)</code> 定义。</li>
	<li class="MachineTrans-lang-zh-CN">第二个矩形由其左下顶点 <code>(bx1, by1)</code> 和右上顶点 <code>(bx2, by2)</code> 定义。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="Rectangle Area" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0223.Rectangle%20Area/images/rectangle-plane.png" style="width: 700px; height: 365px;" />
<pre>
<strong>输入：</strong>ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
<strong>输出：</strong>45
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
<strong>输出：</strong>16
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>4</sup> &lt;= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

计算重叠部分的面积，注意考虑没有重叠的情况

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def computeArea(
        self,
        ax1: int,
        ay1: int,
        ax2: int,
        ay2: int,
        bx1: int,
        by1: int,
        bx2: int,
        by2: int,
    ) -> int:
        a = (ax2 - ax1) * (ay2 - ay1)
        b = (bx2 - bx1) * (by2 - by1)
        width = min(ax2, bx2) - max(ax1, bx1)
        height = min(ay2, by2) - max(ay1, by1)
        return a + b - max(height, 0) * max(width, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a = (ax2 - ax1) * (ay2 - ay1);
        int b = (bx2 - bx1) * (by2 - by1);
        int width = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int height = Math.min(ay2, by2) - Math.max(ay1, by1);
        return a + b - Math.max(height, 0) * Math.max(width, 0);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a = (ax2 - ax1) * (ay2 - ay1);
        int b = (bx2 - bx1) * (by2 - by1);
        int width = min(ax2, bx2) - max(ax1, bx1);
        int height = min(ay2, by2) - max(ay1, by1);
        return a + b - max(height, 0) * max(width, 0);
    }
};
```

### **Go**

```go
func computeArea(ax1 int, ay1 int, ax2 int, ay2 int, bx1 int, by1 int, bx2 int, by2 int) int {
	a := (ax2 - ax1) * (ay2 - ay1)
	b := (bx2 - bx1) * (by2 - by1)
	width := min(ax2, bx2) - max(ax1, bx1)
	height := min(ay2, by2) - max(ay1, by1)
	return a + b - max(height, 0)*max(width, 0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
