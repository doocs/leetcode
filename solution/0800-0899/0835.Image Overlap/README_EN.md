# [835. Image Overlap](https://leetcode.com/problems/image-overlap)

[中文文档](/solution/0800-0899/0835.Image%20Overlap/README.md)

## Description

<p>You are given two images, <code>img1</code> and <code>img2</code>, represented as binary, square matrices of size <code>n x n</code>. A binary matrix has only <code>0</code>s and <code>1</code>s as values.</p>

<p>We <strong>translate</strong> one image however we choose by sliding all the <code>1</code> bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the <strong>overlap</strong> by counting the number of positions that have a <code>1</code> in <strong>both</strong> images.</p>

<p>Note also that a translation does <strong>not</strong> include any kind of rotation. Any <code>1</code> bits that are translated outside of the matrix borders are erased.</p>

<p>Return <em>the largest possible overlap</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0835.Image%20Overlap/images/overlap1.jpg" style="width: 450px; height: 231px;" />
<pre>
<strong>Input:</strong> img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We translate img1 to right by 1 unit and down by 1 unit.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0835.Image%20Overlap/images/overlap_step1.jpg" style="width: 450px; height: 105px;" />
The number of positions that have a 1 in both images is 3 (shown in red).
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0835.Image%20Overlap/images/overlap_step2.jpg" style="width: 450px; height: 231px;" />
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> img1 = [[1]], img2 = [[1]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> img1 = [[0]], img2 = [[0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == img1.length == img1[i].length</code></li>
	<li><code>n == img2.length == img2[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>img1[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>img2[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
