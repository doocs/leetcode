# [1787. 使所有区间的异或结果为零](https://leetcode.cn/problems/make-the-xor-of-all-segments-equal-to-zero)

[English Version](/solution/1700-1799/1787.Make%20the%20XOR%20of%20All%20Segments%20Equal%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>​​​ 和一个整数 <code>k</code>​​​​​ 。区间 <code>[left, right]</code>（<code>left <= right</code>）的 <strong>异或结果</strong> 是对下标位于 <code>left</code> 和 <code>right</code>（包括 <code>left</code> 和 <code>right</code> ）之间所有元素进行 <code>XOR</code> 运算的结果：<code>nums[left] XOR nums[left+1] XOR ... XOR nums[right]</code> 。</p>

<p>返回数组中 <strong>要更改的最小元素数</strong> ，以使所有长度为 <code>k</code> 的区间异或结果等于零。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,0,3,0], k = 1
<strong>输出：</strong>3
<strong>解释：</strong>将数组 [<strong>1</strong>,<strong>2</strong>,0,<strong>3</strong>,0] 修改为 [<strong>0</strong>,<strong>0</strong>,0,<strong>0</strong>,0]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,2,1,7,3,4,7], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>将数组 [3,4,<strong>5</strong>,<strong>2</strong>,<strong>1</strong>,7,3,4,7] 修改为 [3,4,<strong>7</strong>,<strong>3</strong>,<strong>4</strong>,7,3,4,7]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,1,2,5,1,2,6], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>将数组[1,2,<strong>4,</strong>1,2,<strong>5</strong>,1,2,<strong>6</strong>] 修改为 [1,2,<strong>3</strong>,1,2,<strong>3</strong>,1,2,<strong>3</strong>]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= nums.length <= 2000</code></li>
	<li><code>​​​​​​0 <= nums[i] < 2<sup>10</sup></code></li>
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
