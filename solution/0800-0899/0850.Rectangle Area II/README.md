# [850. 矩形面积 II](https://leetcode-cn.com/problems/rectangle-area-ii)

[English Version](/solution/0800-0899/0850.Rectangle%20Area%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们给出了一个（轴对齐的）二维矩形列表&nbsp;<code>rectangles</code>&nbsp;。 对于&nbsp;<code>rectangle[i] = [x1, y1, x2, y2]</code>，其中（x1，y1）是矩形&nbsp;<code>i</code>&nbsp;左下角的坐标，<meta charset="UTF-8" />&nbsp;<code>(x<sub>i1</sub>, y<sub>i1</sub>)</code>&nbsp;是该矩形 <strong>左下角</strong> 的坐标，<meta charset="UTF-8" />&nbsp;<code>(x<sub>i2</sub>, y<sub>i2</sub>)</code>&nbsp;是该矩形&nbsp;<strong>右上角</strong> 的坐标。</p>

<p>计算平面中所有&nbsp;<code>rectangles</code>&nbsp;所覆盖的 <strong>总面积 </strong>。任何被两个或多个矩形覆盖的区域应只计算 <strong>一次</strong> 。</p>

<p>返回<em> <strong>总面积</strong> </em>。因为答案可能太大，返回<meta charset="UTF-8" />&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code> 的&nbsp;<strong>模</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0850.Rectangle%20Area%20II/images/rectangle_area_ii_pic.png" style="height: 300px; width: 400px;" /></p>

<pre>
<strong>输入：</strong>rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
<strong>输出：</strong>6
<strong>解释：</strong>如图所示，三个矩形覆盖了总面积为6的区域。
从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
从(1,0)到(2,3)，三个矩形都重叠。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[0,0,1000000000,1000000000]]
<strong>输出：</strong>49
<strong>解释：</strong>答案是 10<sup>18</sup> 对 (10<sup>9</sup> + 7) 取模的结果， 即 49 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length &lt;= 200</code></li>
	<li><code>rectanges[i].length = 4</code><meta charset="UTF-8" /></li>
	<li><code>0 &lt;= x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li>矩形叠加覆盖后的总面积不会超越&nbsp;<code>2^63 - 1</code>&nbsp;，这意味着可以用一个&nbsp;64 位有符号整数来保存面积结果。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
