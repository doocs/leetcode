# [2061. 扫地机器人清扫过的空间个数](https://leetcode.cn/problems/number-of-spaces-cleaning-robot-cleaned)

[English Version](/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个房间用一个<strong>从 0 开始索引</strong>的二维二进制矩阵 <code>room</code> 表示，其中 <code>0</code> 表示<strong>空闲</strong>空间， <code>1</code> 表示放有<strong>物体</strong>的空间。在每个测试用例中，房间左上角永远是空闲的。</p>

<p>一个扫地机器人面向右侧，从左上角开始清扫。机器人将一直前进，直到抵达房间边界或触碰到物体时，机器人将会<strong>顺时针</strong>旋转 90 度并重复以上步骤。初始位置和所有机器人走过的空间都会被它<strong>清扫干净</strong>。</p>

<p>若机器人持续运转下去，返回被<strong>清扫干净</strong>的空间数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/images/image-20211101204703-1.png" style="width: 250px; height: 242px;" /></p>

<pre>
<strong>输入:</strong> room = [[0,0,0],[1,1,0],[0,0,0]]
<strong>输出:</strong> 7
<strong>解释:</strong>
机器人清理了位于 (0, 0)、 (0, 1) 和 (0, 2) 的空间。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向下。
机器人清理了位于 (1, 2) 和 (2, 2) 的空间。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向左。
机器人清理了位于 (2, 1) 和 (2, 0) 的空间。
机器人已清理了所有 7 处空闲空间，所以返回 7。
</pre>

<p><strong>示例 2：</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/images/image-20211101204736-2.png" style="width: 250px; height: 245px;" /></p>

<pre>
<strong>输入:</strong> room = [[0,1,0],[1,0,0],[0,0,0]]
<strong>输出t:</strong> 1
<strong>解释:</strong>
机器人清理了位于 (0, 0) 的空间。
机器人触碰到了物体，所以它顺时针旋转 90 度，现在面向下。
机器人触碰到了物体，所以它顺时针旋转 90 度，现在面向左。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向上。
机器人位于房间边界，所以它顺时针旋转 90 度，现在面向右。
机器人回到了起始位置。
机器人清理了 1 处空间，所以返回 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == room.length</code></li>
	<li><code>n == room[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>room[r][c]</code> 只会是 <code>0</code> 或 <code>1</code> 。</li>
	<li><code>room[0][0] == 0</code></li>
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
