# [2001. 可互换矩形的组数](https://leetcode.cn/problems/number-of-pairs-of-interchangeable-rectangles)

[English Version](/solution/2000-2099/2001.Number%20of%20Pairs%20of%20Interchangeable%20Rectangles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用一个下标从 <strong>0</strong> 开始的二维整数数组&nbsp;<code>rectangles</code> 来表示 <code>n</code> 个矩形，其中 <code>rectangles[i] = [width<sub>i</sub>, height<sub>i</sub>]</code> 表示第 <code>i</code> 个矩形的宽度和高度。</p>

<p>如果两个矩形 <code>i</code> 和 <code>j</code>（<code>i &lt; j</code>）的宽高比相同，则认为这两个矩形 <strong>可互换</strong> 。更规范的说法是，两个矩形满足&nbsp;<code>width<sub>i</sub>/height<sub>i</sub> == width<sub>j</sub>/height<sub>j</sub></code>（使用实数除法而非整数除法），则认为这两个矩形 <strong>可互换</strong> 。</p>

<p>计算并返回&nbsp;<code>rectangles</code> 中有多少对 <strong>可互换 </strong>矩形。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[4,8],[3,6],[10,20],[15,30]]
<strong>输出：</strong>6
<strong>解释：</strong>下面按下标（从 0 开始）列出可互换矩形的配对情况：
- 矩形 0 和矩形 1 ：4/8 == 3/6
- 矩形 0 和矩形 2 ：4/8 == 10/20
- 矩形 0 和矩形 3 ：4/8 == 15/30
- 矩形 1 和矩形 2 ：3/6 == 10/20
- 矩形 1 和矩形 3 ：3/6 == 15/30
- 矩形 2 和矩形 3 ：10/20 == 15/30
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rectangles = [[4,5],[7,8]]
<strong>输出：</strong>0
<strong>解释：</strong>不存在成对的可互换矩形。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rectangles.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>rectangles[i].length == 2</code></li>
	<li><code>1 &lt;= width<sub>i</sub>, height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
