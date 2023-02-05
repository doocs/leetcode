# [1401. 圆和矩形是否有重叠](https://leetcode.cn/problems/circle-and-rectangle-overlapping)

[English Version](/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个以 <code>(radius, xCenter, yCenter)</code> 表示的圆和一个与坐标轴平行的矩形 <code>(x1, y1, x2, y2)</code> ，其中 <code>(x1, y1)</code> 是矩形左下角的坐标，而 <code>(x2, y2)</code> 是右上角的坐标。</p>

<p>如果圆和矩形有重叠的部分，请你返回 <code>true</code> ，否则返回 <code>false</code>&nbsp;。</p>

<p>换句话说，请你检测是否 <strong>存在</strong> 点 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_4_1728.png" style="width: 258px; height: 167px;" />
<pre>
<strong>输入：</strong>radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
<strong>输出：</strong>true
<strong>解释：</strong>圆和矩形存在公共点 (1,0) 。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
<strong>输出：</strong>false
</pre>

<p><strong class="example">示例 3 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_2_1728.png" style="width: 150px; height: 135px;" />
<pre>
<strong>输入：</strong>radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= radius &lt;= 2000</code></li>
	<li><code>-10<sup>4</sup> &lt;= xCenter, yCenter &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= x1 &lt; x2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= y1 &lt; y2 &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

计算矩形离圆最近的点和圆心的距离是否小于等于半径即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkOverlap(
        self,
        radius: int,
        xCenter: int,
        yCenter: int,
        x1: int,
        y1: int,
        x2: int,
        y2: int,
    ) -> bool:
        dx = dy = 0
        if x1 > xCenter:
            dx = xCenter - x1
        elif x2 < xCenter:
            dx = xCenter - x2
        if y1 > yCenter:
            dy = yCenter - y1
        elif y2 < yCenter:
            dy = yCenter - y2
        return dx * dx + dy * dy <= radius * radius
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int dx = x1 > xCenter ? x1 - xCenter : (x2 < xCenter ? xCenter - x2 : 0);
        int dy = y1 > yCenter ? y1 - yCenter : (y2 < yCenter ? yCenter - y2 : 0);
        return dx * dx + dy * dy <= radius * radius;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int dx = x1 > xCenter ? x1 - xCenter : (x2 < xCenter ? xCenter - x2 : 0);
        int dy = y1 > yCenter ? y1 - yCenter : (y2 < yCenter ? yCenter - y2 : 0);
        return dx * dx + dy * dy <= radius * radius;
    }
};
```

### **Go**

```go
func checkOverlap(radius int, xCenter int, yCenter int, x1 int, y1 int, x2 int, y2 int) bool {
	dx, dy := 0, 0
	if x1 > xCenter {
		dx = x1 - xCenter
	} else if x2 < xCenter {
		dx = x2 - xCenter
	}
	if y1 > yCenter {
		dy = y1 - yCenter
	} else if y2 < yCenter {
		dy = y2 - yCenter
	}
	return dx*dx+dy*dy <= radius*radius
}
```

### **...**

```

```

<!-- tabs:end -->
