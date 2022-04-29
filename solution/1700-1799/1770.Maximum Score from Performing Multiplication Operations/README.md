# [1770. 执行乘法运算的最大分数](https://leetcode.cn/problems/maximum-score-from-performing-multiplication-operations)

[English Version](/solution/1700-1799/1770.Maximum%20Score%20from%20Performing%20Multiplication%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度分别 <code>n</code> 和 <code>m</code> 的整数数组 <code>nums</code> 和 <code>multipliers</code><strong> </strong>，其中 <code>n &gt;= m</code> ，数组下标 <strong>从 1 开始</strong> 计数。</p>

<p>初始时，你的分数为 <code>0</code> 。你需要执行恰好 <code>m</code> 步操作。在第 <code>i</code> 步操作（<strong>从 1 开始</strong> 计数）中，需要：</p>

<ul>
	<li>选择数组 <code>nums</code> <strong>开头处或者末尾处</strong> 的整数 <code>x</code> 。</li>
	<li>你获得 <code>multipliers[i] * x</code> 分，并累加到你的分数中。</li>
	<li>将 <code>x</code> 从数组 <code>nums</code> 中移除。</li>
</ul>

<p>在执行<em> </em><code>m</code> 步操作后，返回 <strong>最大</strong> 分数<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], multipliers = [3,2,1]
<strong>输出：</strong>14
<strong>解释：</strong>一种最优解决方案如下：
- 选择末尾处的整数 3 ，[1,2,<strong>3</strong>] ，得 3 * 3 = 9 分，累加到分数中。
- 选择末尾处的整数 2 ，[1,<strong>2</strong>] ，得 2 * 2 = 4 分，累加到分数中。
- 选择末尾处的整数 1 ，[<strong>1</strong>] ，得 1 * 1 = 1 分，累加到分数中。
总分数为 9 + 4 + 1 = 14 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
<strong>输出：</strong>102
<strong>解释：</strong>一种最优解决方案如下：
- 选择开头处的整数 -5 ，[<strong>-5</strong>,-3,-3,-2,7,1] ，得 -5 * -10 = 50 分，累加到分数中。
- 选择开头处的整数 -3 ，[<strong>-3</strong>,-3,-2,7,1] ，得 -3 * -5 = 15 分，累加到分数中。
- 选择开头处的整数 -3 ，[<strong>-3</strong>,-2,7,1] ，得 -3 * 3 = -9 分，累加到分数中。
- 选择末尾处的整数 1 ，[-2,7,<strong>1</strong>] ，得 1 * 4 = 4 分，累加到分数中。
- 选择末尾处的整数 7 ，[-2,<strong>7</strong>] ，得 7 * 6 = 42 分，累加到分数中。
总分数为 50 + 15 - 9 + 4 + 42 = 102 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == multipliers.length</code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>3</sup></code></li>
	<li><code>m &lt;= n &lt;= 10<sup>5</sup></code><code> </code></li>
	<li><code>-1000 &lt;= nums[i], multipliers[i] &lt;= 1000</code></li>
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
