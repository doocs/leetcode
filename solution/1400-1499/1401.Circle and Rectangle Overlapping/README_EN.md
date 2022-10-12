# [1401. Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping)

[中文文档](/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README.md)

## Description

<p>You are given a circle represented as <code>(radius, xCenter, yCenter)</code> and an axis-aligned rectangle represented as <code>(x1, y1, x2, y2)</code>, where <code>(x1, y1)</code> are the coordinates of the bottom-left corner, and <code>(x2, y2)</code> are the coordinates of the top-right corner of the rectangle.</p>

<p>Return <code>true</code><em> if the circle and rectangle are overlapped otherwise return </em><code>false</code>. In other words, check if there is <strong>any</strong> point <code>(x<sub>i</sub>, y<sub>i</sub>)</code> that belongs to the circle and the rectangle at the same time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_4_1728.png" style="width: 258px; height: 167px;" />
<pre>
<strong>Input:</strong> radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
<strong>Output:</strong> true
<strong>Explanation:</strong> Circle and rectangle share the point (1,0).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_2_1728.png" style="width: 150px; height: 135px;" />
<pre>
<strong>Input:</strong> radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= radius &lt;= 2000</code></li>
	<li><code>-10<sup>4</sup> &lt;= xCenter, yCenter &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= x1 &lt; x2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= y1 &lt; y2 &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
