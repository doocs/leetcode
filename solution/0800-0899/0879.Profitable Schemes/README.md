# [879. 盈利计划](https://leetcode.cn/problems/profitable-schemes)

[English Version](/solution/0800-0899/0879.Profitable%20Schemes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>集团里有 <code>n</code> 名员工，他们可以完成各种各样的工作创造利润。</p>

<p>第 <code>i</code> 种工作会产生 <code>profit[i]</code> 的利润，它要求 <code>group[i]</code> 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。</p>

<p>工作的任何至少产生 <code>minProfit</code> 利润的子集称为 <strong>盈利计划</strong> 。并且工作的成员总数最多为 <code>n</code> 。</p>

<p>有多少种计划可以选择？因为答案很大，所以<strong> 返回结果模 </strong><code>10^9 + 7</code><strong> 的值</strong>。</p>

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5, minProfit = 3, group = [2,2], profit = [2,3]
<strong>输出：</strong>2
<strong>解释：</strong>至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
总的来说，有两种计划。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
<strong>输出：</strong>7
<strong>解释：</strong>至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。</pre>
</div>
</div>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 100</code></li>
	<li><code>0 <= minProfit <= 100</code></li>
	<li><code>1 <= group.length <= 100</code></li>
	<li><code>1 <= group[i] <= 100</code></li>
	<li><code>profit.length == group.length</code></li>
	<li><code>0 <= profit[i] <= 100</code></li>
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
