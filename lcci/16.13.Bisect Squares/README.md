# [面试题 16.13. 平分正方形](https://leetcode.cn/problems/bisect-squares-lcci)

[English Version](/lcci/16.13.Bisect%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。</p>
<p>每个正方形的数据<code>square</code>包含3个数值，正方形的左下顶点坐标<code>[X,Y] = [square[0],square[1]]</code>，以及正方形的边长<code>square[2]</code>。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标<code>[X<sub>1</sub>,Y<sub>1</sub>]</code>和<code>[X<sub>2</sub>,Y<sub>2</sub>]</code>的返回格式为<code>{X<sub>1</sub>,Y<sub>1</sub>,X<sub>2</sub>,Y<sub>2</sub>}</code>，要求若<code>X<sub>1</sub> != X<sub>2</sub></code>，需保证<code>X<sub>1</sub> &lt; X<sub>2</sub></code>，否则需保证<code>Y<sub>1</sub> &lt;= Y<sub>2</sub></code>。</p>
<p>若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
square1 = {-1, -1, 2}
square2 = {0, -1, 2}
<strong>输出：</strong> {-1,0,2,0}
<strong>解释：</strong> 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>square.length == 3</code></li>
	<li><code>square[2] &gt; 0</code></li>
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
