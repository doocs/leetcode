# [312. 戳气球](https://leetcode-cn.com/problems/burst-balloons)

[English Version](/solution/0300-0399/0312.Burst%20Balloons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>有 <code>n</code> 个气球，编号为<code>0</code> 到 <code>n-1</code>，每个气球上都标有一个数字，这些数字存在数组&nbsp;<code>nums</code>&nbsp;中。</p>

<p>现在要求你戳破所有的气球。每当你戳破一个气球 <code>i</code> 时，你可以获得&nbsp;<code>nums[left] * nums[i] * nums[right]</code>&nbsp;个硬币。&nbsp;这里的&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;代表和&nbsp;<code>i</code>&nbsp;相邻的两个气球的序号。注意当你戳破了气球 <code>i</code> 后，气球&nbsp;<code>left</code>&nbsp;和气球&nbsp;<code>right</code>&nbsp;就变成了相邻的气球。</p>

<p>求所能获得硬币的最大数量。</p>

<p><strong>说明:</strong></p>

<ul>
	<li>你可以假设&nbsp;<code>nums[-1] = nums[n] = 1</code>，但注意它们不是真实存在的所以并不能被戳破。</li>
	<li>0 &le; <code>n</code> &le; 500, 0 &le; <code>nums[i]</code> &le; 100</li>
</ul>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>[3,1,5,8]</code>
<strong>输出:</strong> <code>167 
<strong>解释: </strong></code>nums = [3,1,5,8] --&gt; [3,5,8] --&gt;   [3,8]   --&gt;  [8]  --&gt; []
&nbsp;    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
</pre>

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
