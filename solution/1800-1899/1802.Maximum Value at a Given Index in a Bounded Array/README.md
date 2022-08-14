# [1802. 有界数组中指定下标处的最大值](https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array)

[English Version](/solution/1800-1899/1802.Maximum%20Value%20at%20a%20Given%20Index%20in%20a%20Bounded%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个正整数 <code>n</code>、<code>index</code> 和 <code>maxSum</code> 。你需要构造一个同时满足下述所有条件的数组 <code>nums</code>（下标 <strong>从 0 开始</strong> 计数）：</p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>nums[i]</code> 是 <strong>正整数</strong> ，其中 <code>0 &lt;= i &lt; n</code></li>
	<li><code>abs(nums[i] - nums[i+1]) &lt;= 1</code> ，其中 <code>0 &lt;= i &lt; n-1</code></li>
	<li><code>nums</code> 中所有元素之和不超过 <code>maxSum</code></li>
	<li><code>nums[index]</code> 的值被 <strong>最大化</strong></li>
</ul>

<p>返回你所构造的数组中的 <code>nums[index]</code> 。</p>

<p>注意：<code>abs(x)</code> 等于 <code>x</code> 的前提是 <code>x &gt;= 0</code> ；否则，<code>abs(x)</code> 等于 <code>-x</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 4, index = 2,  maxSum = 6
<strong>输出：</strong>2
<strong>解释：</strong>数组 [1,1,<strong>2</strong>,1] 和 [1,2,<strong>2</strong>,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6, index = 1,  maxSum = 10
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt; n</code></li>
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
