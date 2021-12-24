# [683. K 个关闭的灯泡](https://leetcode-cn.com/problems/k-empty-slots)

[English Version](/solution/0600-0699/0683.K%20Empty%20Slots/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>N</code> 个灯泡排成一行，编号从 <code>1</code> 到 <code>N</code> 。最初，所有灯泡都关闭。每天只打开一个灯泡，直到 <code>N</code> 天后所有灯泡都打开。</p>

<p>给你一个长度为 <code>N</code> 的灯泡数组 <code>blubs</code> ，其中 <code>bulls[i] = x</code> 意味着在第 <code>(i+1)</code> 天，我们会把在位置 <code>x</code> 的灯泡打开，其中 <code>i</code> <strong>从 0 开始</strong>，<code>x</code> <strong>从 1 开始</strong>。</p>

<p class="MachineTrans-lang-zh-CN">给你一个整数 <code>K</code> ，请你输出在第几天恰好有两个打开的灯泡，使得它们中间 <strong>正好</strong> 有 K 个灯泡且这些灯泡 <strong>全部是关闭的</strong> 。</p>

<p class="MachineTrans-lang-zh-CN">如果不存在这种情况，返回 <code>-1</code> 。如果有多天都出现这种情况，请返回 <strong>最小的天数</strong> 。</p>

<p> </p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
bulbs: [1,3,2]
K: 1
<b>输出：</b>2
<b>解释：</b>
第一天 bulbs[0] = 1，打开第一个灯泡 [1,0,0]
第二天 bulbs[1] = 3，打开第三个灯泡 [1,0,1]
第三天 bulbs[2] = 2，打开第二个灯泡 [1,1,1]
返回2，因为在第二天，两个打开的灯泡之间恰好有一个关闭的灯泡。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
bulbs: [1,2,3]
k: 1
<strong>输出：</strong>-1
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 <= N <= 20000</code></li>
	<li><code>1 <= bulbs[i] <= N</code></li>
	<li><code>bulbs</code> 是一个由从 <code>1</code> 到 <code>N</code> 的数字构成的排列</li>
	<li><code>0 <= K <= 20000</code></li>
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
