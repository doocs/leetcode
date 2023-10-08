# [2897. 对数组执行操作使平方和最大](https://leetcode.cn/problems/apply-operations-on-array-to-maximize-sum-of-squares)

[English Version](/solution/2800-2899/2897.Apply%20Operations%20on%20Array%20to%20Maximize%20Sum%20of%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以对数组执行以下操作 <strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>选择两个互不相同的下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，<strong>同时</strong>&nbsp;将&nbsp;<code>nums[i]</code>&nbsp;更新为&nbsp;<code>(nums[i] AND nums[j])</code> 且将&nbsp;<code>nums[j]</code>&nbsp;更新为&nbsp;<code>(nums[i] OR nums[j])</code>&nbsp;，<code>OR</code>&nbsp;表示按位 <strong>或</strong>&nbsp;运算，<code>AND</code>&nbsp;表示按位 <strong>与</strong>&nbsp;运算。</li>
</ul>

<p>你需要从最终的数组里选择&nbsp;<code>k</code>&nbsp;个元素，并计算它们的 <strong>平方</strong>&nbsp;之和。</p>

<p>请你返回你可以得到的 <strong>最大</strong>&nbsp;平方和。</p>

<p>由于答案可能会很大，将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,6,5,8], k = 2
<b>输出：</b>261
<b>解释：</b>我们可以对数组执行以下操作：
- 选择 i = 0 和 j = 3 ，同时将 nums[0] 变为 (2 AND 8) = 0 且 nums[3] 变为 (2 OR 8) = 10 ，结果数组为 nums = [0,6,5,10] 。
- 选择 i = 2 和 j = 3 ，同时将 nums[2] 变为 (5 AND 10) = 0 且 nums[3] 变为 (5 OR 10) = 15 ，结果数组为 nums = [0,6,0,15] 。
从最终数组里选择元素 15 和 6 ，平方和为 15<sup>2</sup> + 6<sup>2</sup> = 261 。
261 是可以得到的最大结果。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [4,5,4,7], k = 3
<b>输出：</b>90
<b>解释：</b>不需要执行任何操作。
选择元素 7 ，5 和 4 ，平方和为 7<sup>2</sup> + 5<sup>2</sup> + 4<sup>2</sup> = 90 。
90 是可以得到的最大结果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
