# [1675. 数组的最小偏移量](https://leetcode.cn/problems/minimize-deviation-in-array)

[English Version](/solution/1600-1699/1675.Minimize%20Deviation%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>n</code> 个正整数组成的数组 <code>nums</code> 。</p>

<p>你可以对数组的任意元素执行任意次数的两类操作：</p>

<ul>
	<li>如果元素是<strong> 偶数</strong> ，<strong>除以</strong> <code>2</code>
    <ul>
    	<li>例如，如果数组是 <code>[1,2,3,4]</code> ，那么你可以对最后一个元素执行此操作，使其变成 <code>[1,2,3,<strong>2</strong>]</code></li>
    </ul>
    </li>
    <li>如果元素是 <strong>奇数</strong> ，<strong>乘上</strong> <code>2</code>
    <ul>
    	<li>例如，如果数组是 <code>[1,2,3,4]</code> ，那么你可以对第一个元素执行此操作，使其变成 <code>[<strong>2</strong>,2,3,4]</code></li>
    </ul>
    </li>
</ul>

<p>数组的 <strong>偏移量</strong> 是数组中任意两个元素之间的 <strong>最大差值</strong> 。</p>

<p>返回数组在执行某些操作之后可以拥有的 <strong>最小偏移量</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>你可以将数组转换为 [1,2,3,<strong>2</strong>]，然后转换成 [<strong>2</strong>,2,3,2]，偏移量是 3 - 2 = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4,1,5,20,3]
<strong>输出：</strong>3
<strong>解释：</strong>两次操作后，你可以将数组转换为 [4,<strong>2</strong>,5,<strong>5</strong>,3]，偏移量是 5 - 2 = 3
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [2,10,8]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup><span style="">5</span></sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
