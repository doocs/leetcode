# [356. 直线镜像](https://leetcode.cn/problems/line-reflection)

[English Version](/solution/0300-0399/0356.Line%20Reflection/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个二维平面空间中，给你 n&nbsp;个点的坐标。问，是否能找出一条平行于 y<strong>&nbsp;</strong>轴的直线，让这些点关于这条直线成镜像排布？</p>

<p><strong>注意</strong>：题目数据中可能有重复的点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0356.Line%20Reflection/images/356_example_1.png" style="width: 389px; height: 340px;" />
<pre>
<strong>输入：</strong>points = [[1,1],[-1,1]]
<strong>输出：</strong>true
<strong>解释：</strong>可以找出 x = 0 这条线。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0356.Line%20Reflection/images/356_example_2.png" style="width: 300px; height: 294px;" />
<pre>
<strong>输入：</strong>points = [[1,1],[-1,-1]]
<strong>输出：</strong>false
<strong>解释：</strong>无法找出这样一条线。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
	<li><code>-10^8&nbsp;&lt;= points[i][j] &lt;=&nbsp;10^8</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能找到比 O(<em>n</em><sup>2</sup>) 更优的解法吗?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先找出所有点中的最小、最大的 x 坐标 `minX` 和 `maxX`。若存在满足条件的直线，则直线 `x = (minX + maxX) / 2`。(或者说：`s = minX + maxX`)

遍历每个点 `point(x, y)`，若 `(s - x, y)` 不在点集里，说明不满足条件，直接返回 false。遍历结束返回 true。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isReflected(self, points: List[List[int]]) -> bool:
        min_x, max_x = inf, -inf
        point_set = set()
        for x, y in points:
            min_x = min(min_x, x)
            max_x = max(max_x, x)
            point_set.add((x, y))
        s = min_x + max_x
        for x, y in points:
            if (s - x, y) not in point_set:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isReflected(int[][] points) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        Set<String> pointSet = new HashSet<>();
        for (int[] point : points) {
            minX = Math.min(minX, point[0]);
            maxX = Math.max(maxX, point[0]);
            pointSet.add(point[0] + "." + point[1]);
        }
        long s = minX + maxX;
        for (int[] point : points) {
            if (!pointSet.contains((s - point[0]) + "." + point[1])) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
