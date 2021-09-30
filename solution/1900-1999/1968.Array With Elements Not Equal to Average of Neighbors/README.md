# [1968. 构造元素不等于两相邻元素平均值的数组](https://leetcode-cn.com/problems/array-with-elements-not-equal-to-average-of-neighbors)

[English Version](/solution/1900-1999/1968.Array%20With%20Elements%20Not%20Equal%20to%20Average%20of%20Neighbors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>下标从 0 开始</strong> 的数组 <code>nums</code> ，数组由若干 <strong>互不相同的</strong> 整数组成。你打算重新排列数组中的元素以满足：重排后，数组中的每个元素都 <strong>不等于</strong> 其两侧相邻元素的 <strong>平均值</strong> 。</p>

<p>更公式化的说法是，重新排列的数组应当满足这一属性：对于范围&nbsp;<code>1 &lt;= i &lt; nums.length - 1</code> 中的每个 <code>i</code> ，<code>(nums[i-1] + nums[i+1]) / 2</code> <strong>不等于</strong> <code>nums[i]</code> 均成立 。</p>

<p>返回满足题意的任一重排结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5]
<strong>输出：</strong>[1,2,4,5,3]
<strong>解释：</strong>
i=1, nums[i] = 2, 两相邻元素平均值为 (1+4) / 2 = 2.5
i=2, nums[i] = 4, 两相邻元素平均值为 (2+5) / 2 = 3.5
i=3, nums[i] = 5, 两相邻元素平均值为 (4+3) / 2 = 3.5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [6,2,0,9,7]
<strong>输出：</strong>[9,7,6,2,0]
<strong>解释：</strong>
i=1, nums[i] = 7, 两相邻元素平均值为 (9+6) / 2 = 7.5
i=2, nums[i] = 6, 两相邻元素平均值为 (7+2) / 2 = 4.5
i=3, nums[i] = 2, 两相邻元素平均值为 (6+0) / 2 = 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
