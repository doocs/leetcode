# [1643. 第 K 条最小指令](https://leetcode.cn/problems/kth-smallest-instructions)

[English Version](/solution/1600-1699/1643.Kth%20Smallest%20Instructions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Bob 站在单元格 <code>(0, 0)</code> ，想要前往目的地 <code>destination</code> ：<code>(row, column)</code> 。他只能向 <strong>右</strong> 或向 <strong>下</strong> 走。你可以为 Bob 提供导航 <strong>指令</strong> 来帮助他到达目的地 <code>destination</code> 。</p>

<p><strong>指令</strong> 用字符串表示，其中每个字符：</p>

<ul>
	<li><code>'H'</code> ，意味着水平向右移动</li>
	<li><code>'V'</code> ，意味着竖直向下移动</li>
</ul>

<p>能够为 Bob 导航到目的地 <code>destination</code> 的指令可以有多种，例如，如果目的地 <code>destination</code> 是 <code>(2, 3)</code>，<code>"HHHVV"</code> 和 <code>"HVHVH"</code> 都是有效<strong> 指令</strong> 。</p>

<ul>
</ul>

<p>然而，Bob 很挑剔。因为他的幸运数字是 <code>k</code>，他想要遵循 <strong>按字典序排列后的第 <code>k</code> 条最小指令 </strong>的导航前往目的地 <code>destination</code> 。<code>k</code>  的编号 <strong>从 1 开始</strong> 。</p>

<p>给你一个整数数组 <code>destination</code> 和一个整数 <code>k</code> ，请你返回可以为<em> </em>Bob<em> </em>提供前往目的地 <code>destination</code> 导航的<strong> 按字典序排列后的第 <code>k</code> 条最小指令 </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex1.png" style="width: 300px;" /></p>

<pre>
<strong>输入：</strong>destination = [2,3], k = 1
<strong>输出：</strong>"HHHVV"
<strong>解释：</strong>能前往 (2, 3) 的所有导航指令 <strong>按字典序排列后</strong> 如下所示：
["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex2.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>输入：</strong>destination = [2,3], k = 2
<strong>输出：</strong>"HHVHV"
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex3.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>输入：</strong>destination = [2,3], k = 3
<strong>输出：</strong>"HHVVH"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>destination.length == 2</code></li>
	<li><code>1 <= row, column <= 15</code></li>
	<li><code>1 <= k <= nCr(row + column, row)</code>，其中 <code>nCr(a, b)</code> 表示组合数，即从 <code>a</code> 个物品中选 <code>b</code> 个物品的不同方案数。</li>
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
