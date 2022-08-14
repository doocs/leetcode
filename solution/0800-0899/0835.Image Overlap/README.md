# [835. 图像重叠](https://leetcode.cn/problems/image-overlap)

[English Version](/solution/0800-0899/0835.Image%20Overlap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个图像 <code>img1</code> 和 <code>img2</code> ，两个图像的大小都是 <code>n x n</code> ，用大小相同的二进制正方形矩阵表示。二进制矩阵仅由若干 <code>0</code> 和若干 <code>1</code> 组成。</p>

<p><strong>转换</strong> 其中一个图像，将所有的 <code>1</code> 向左，右，上，或下滑动任何数量的单位；然后把它放在另一个图像的上面。该转换的 <strong>重叠</strong> 是指两个图像 <strong>都</strong> 具有 <code>1</code> 的位置的数目。</p>

<div class="original__bRMd">
<div>
<p>请注意，转换 <strong>不包括</strong> 向任何方向旋转。越过矩阵边界的 <code>1</code> 都将被清除。</p>

<p>最大可能的重叠数量是多少？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0835.Image%20Overlap/images/overlap1.jpg" style="width: 450px; height: 231px;" />
<pre>
<strong>输入：</strong>img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
<strong>输出：</strong>3
<strong>解释：</strong>将 img1 向右移动 1 个单位，再向下移动 1 个单位。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0835.Image%20Overlap/images/overlap_step1.jpg" style="width: 450px; height: 105px;" />
两个图像都具有 <code>1</code> 的位置的数目是 3（用红色标识）。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0835.Image%20Overlap/images/overlap_step2.jpg" style="width: 450px; height: 231px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>img1 = [[1]], img2 = [[1]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>img1 = [[0]], img2 = [[0]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == img1.length == img1[i].length</code></li>
	<li><code>n == img2.length == img2[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>img1[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>img2[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>
</div>
</div>

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
