# [836. 矩形重叠](https://leetcode.cn/problems/rectangle-overlap)

[English Version](/solution/0800-0899/0836.Rectangle%20Overlap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>矩形以列表 <code>[x1, y1, x2, y2]</code> 的形式表示，其中 <code>(x1, y1)</code> 为左下角的坐标，<code>(x2, y2)</code> 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。</p>

<p>如果相交的面积为 <strong>正</strong> ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。</p>

<p>给出两个矩形 <code>rec1</code> 和 <code>rec2</code> 。如果它们重叠，返回 <code>true</code>；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rec1 = [0,0,2,2], rec2 = [1,1,3,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rec1 = [0,0,1,1], rec2 = [1,0,2,1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rec1 = [0,0,1,1], rec2 = [2,2,3,3]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rect1.length == 4</code></li>
	<li><code>rect2.length == 4</code></li>
	<li><code>-10<sup>9</sup> &lt;= rec1[i], rec2[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>rec1</code> 和 <code>rec2</code> 表示一个面积不为零的有效矩形</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：判断不重叠的情况**

我们记矩形 $rec1$ 的坐标点为 $(x_1, y_1, x_2, y_2)$，矩形 $rec2$ 的坐标点为 $(x_3, y_3, x_4, y_4)$。

那么当满足以下任一条件时，矩形 $rec1$ 和 $rec2$ 不重叠：

-   满足 $y_3 \geq y_2$，即 $rec2$ 在 $rec1$ 的上方；
-   满足 $y_4 \leq y_1$，即 $rec2$ 在 $rec1$ 的下方；
-   满足 $x_3 \geq x_2$，即 $rec2$ 在 $rec1$ 的右方；
-   满足 $x_4 \leq x_1$，即 $rec2$ 在 $rec1$ 的左方。

当以上条件都不满足时，矩形 $rec1$ 和 $rec2$ 重叠。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isRectangleOverlap(self, rec1: List[int], rec2: List[int]) -> bool:
        x1, y1, x2, y2 = rec1
        x3, y3, x4, y4 = rec2
        return not (y3 >= y2 or y4 <= y1 or x3 >= x2 or x4 <= x1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
        int x3 = rec2[0], y3 = rec2[1], x4 = rec2[2], y4 = rec2[3];
        return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isRectangleOverlap(vector<int>& rec1, vector<int>& rec2) {
        int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
        int x3 = rec2[0], y3 = rec2[1], x4 = rec2[2], y4 = rec2[3];
        return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1);
    }
};
```

### **Go**

```go
func isRectangleOverlap(rec1 []int, rec2 []int) bool {
	x1, y1, x2, y2 := rec1[0], rec1[1], rec1[2], rec1[3]
	x3, y3, x4, y4 := rec2[0], rec2[1], rec2[2], rec2[3]
	return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1)
}
```

### **...**

```

```

<!-- tabs:end -->
