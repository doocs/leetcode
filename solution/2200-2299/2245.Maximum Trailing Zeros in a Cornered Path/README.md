# [2245. 转角路径的乘积中最多能有几个尾随零](https://leetcode-cn.com/problems/maximum-trailing-zeros-in-a-cornered-path)

[English Version](/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>grid</code> ，大小为 <code>m x n</code>，其中每个单元格都含一个正整数。</p>

<p><strong>转角路径</strong> 定义为：包含至多一个弯的一组相邻单元。具体而言，路径应该完全 <strong>向水平方向</strong> 或者 <strong>向竖直方向</strong> 移动过弯（如果存在弯），而不能访问之前访问过的单元格。在过弯之后，路径应当完全朝 <strong>另一个</strong> 方向行进：如果之前是向水平方向，那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。</p>

<p>一条路径的 <strong>乘积</strong> 定义为：路径上所有值的乘积。</p>

<p>请你从 <code>grid</code> 中找出一条乘积中尾随零数目最多的转角路径，并返回该路径中尾随零的数目。</p>

<p>注意：</p>

<ul>
	<li><strong>水平</strong> 移动是指向左或右移动。</li>
	<li><strong>竖直 </strong>移动是指向上或下移动。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex1new2.jpg" style="width: 577px; height: 190px;" /></p>

<pre>
<strong>输入：</strong>grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
<strong>输出：</strong>3
<strong>解释：</strong>左侧的图展示了一条有效的转角路径。
其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
可以证明在这条转角路径的乘积中尾随零数目最多。

中间的图不是一条有效的转角路径，因为它有不止一个弯。
右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex2.jpg" style="width: 150px; height: 157px;" /></p>

<pre>
<strong>输入：</strong>grid = [[4,3,2],[7,6,1],[8,8,8]]
<strong>输出：</strong>0
<strong>解释：</strong>网格如上图所示。
不存在乘积含尾随零的转角路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 1000</code></li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
