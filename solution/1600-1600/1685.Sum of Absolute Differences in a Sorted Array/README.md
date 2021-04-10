# [1685. 有序数组中差绝对值之和](https://leetcode-cn.com/problems/sum-of-absolute-differences-in-a-sorted-array)

[English Version](/solution/1600-1600/1685.Sum%20of%20Absolute%20Differences%20in%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>非递减 </strong>有序整数数组 <code>nums</code> 。</p>

<p>请你建立并返回一个整数数组<em> </em><code>result</code>，它跟<em> </em><code>nums</code> 长度相同，且<code>result[i]</code> 等于<em> </em><code>nums[i]</code> 与数组中所有其他元素差的绝对值之和。</p>

<p>换句话说， <code>result[i]</code> 等于 <code>sum(|nums[i]-nums[j]|)</code> ，其中 <code>0 <= j < nums.length</code> 且 <code>j != i</code> （下标从 0 开始）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,5]
<b>输出：</b>[4,3,5]
<b>解释：</b>假设数组下标从 0 开始，那么
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4，
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3，
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,6,8,10]
<b>输出：</b>[24,15,13,15,21]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= nums[i + 1] <= 10<sup>4</sup></code></li>
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
