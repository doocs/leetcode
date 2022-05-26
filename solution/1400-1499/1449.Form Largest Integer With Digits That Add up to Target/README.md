# [1449. 数位成本和为目标值的最大数字](https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target)

[English Version](/solution/1400-1499/1449.Form%20Largest%20Integer%20With%20Digits%20That%20Add%20up%20to%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个整数数组&nbsp;<code>cost</code>&nbsp;和一个整数&nbsp;<code>target</code>&nbsp;。请你返回满足如下规则可以得到的&nbsp;<strong>最大</strong>&nbsp;整数：</p>

<ul>
	<li>给当前结果添加一个数位（<code>i + 1</code>）的成本为&nbsp;<code>cost[i]</code>&nbsp;（<code>cost</code>&nbsp;数组下标从 0 开始）。</li>
	<li>总成本必须恰好等于&nbsp;<code>target</code>&nbsp;。</li>
	<li>添加的数位中没有数字 0 。</li>
</ul>

<p>由于答案可能会很大，请你以字符串形式返回。</p>

<p>如果按照上述要求无法得到任何整数，请你返回 &quot;0&quot; 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>cost = [4,3,2,5,6,7,2,5,5], target = 9
<strong>输出：</strong>&quot;7772&quot;
<strong>解释：</strong>添加数位 &#39;7&#39; 的成本为 2 ，添加数位 &#39;2&#39; 的成本为 3 。所以 &quot;7772&quot; 的代价为 2*3+ 3*1 = 9 。 &quot;997&quot; 也是满足要求的数字，但 &quot;7772&quot; 是较大的数字。
<strong> 数字     成本</strong>
  1  -&gt;   4
  2  -&gt;   3
  3  -&gt;   2
  4  -&gt;   5
  5  -&gt;   6
  6  -&gt;   7
  7  -&gt;   2
  8  -&gt;   5
  9  -&gt;   5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>cost = [7,6,5,5,5,6,8,7,8], target = 12
<strong>输出：</strong>&quot;85&quot;
<strong>解释：</strong>添加数位 &#39;8&#39; 的成本是 7 ，添加数位 &#39;5&#39; 的成本是 5 。&quot;85&quot; 的成本为 7 + 5 = 12 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>cost = [2,4,6,2,4,6,4,4,4], target = 5
<strong>输出：</strong>&quot;0&quot;
<strong>解释：</strong>总成本是 target 的条件下，无法生成任何整数。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>cost = [6,10,15,40,40,40,40,40,40], target = 47
<strong>输出：</strong>&quot;32211&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>cost.length == 9</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 5000</code></li>
	<li><code>1 &lt;= target &lt;= 5000</code></li>
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
