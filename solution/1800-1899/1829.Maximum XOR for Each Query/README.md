# [1829. 每个查询的最大异或值](https://leetcode-cn.com/problems/maximum-xor-for-each-query)

[English Version](/solution/1800-1899/1829.Maximum%20XOR%20for%20Each%20Query/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>有序</strong> 数组 <code>nums</code> ，它由 <code>n</code> 个非负整数组成，同时给你一个整数 <code>maximumBit</code> 。你需要执行以下查询 <code>n</code> 次：</p>

<ol>
	<li>找到一个非负整数 <code>k < 2<sup>maximumBit</sup></code> ，使得 <code>nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k</code> 的结果 <strong>最大化</strong> 。<code>k</code> 是第 <code>i</code> 个查询的答案。</li>
	<li>从当前数组 <code>nums</code> 删除 <strong>最后</strong> 一个元素。</li>
</ol>

<p>请你返回一个数组 <code>answer</code> ，其中<em> </em><code>answer[i]</code>是第 <code>i</code> 个查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,1,3], maximumBit = 2
<b>输出：</b>[0,3,2,3]
<b>解释：</b>查询的答案如下：
第一个查询：nums = [0,1,1,3]，k = 0，因为 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3 。
第二个查询：nums = [0,1,1]，k = 3，因为 0 XOR 1 XOR 1 XOR 3 = 3 。
第三个查询：nums = [0,1]，k = 2，因为 0 XOR 1 XOR 2 = 3 。
第四个查询：nums = [0]，k = 3，因为 0 XOR 3 = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,4,7], maximumBit = 3
<b>输出：</b>[5,2,6,5]
<b>解释：</b>查询的答案如下：
第一个查询：nums = [2,3,4,7]，k = 5，因为 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7。
第二个查询：nums = [2,3,4]，k = 2，因为 2 XOR 3 XOR 4 XOR 2 = 7 。
第三个查询：nums = [2,3]，k = 6，因为 2 XOR 3 XOR 6 = 7 。
第四个查询：nums = [2]，k = 5，因为 2 XOR 5 = 7 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,2,2,5,7], maximumBit = 3
<b>输出：</b>[4,3,6,4,6,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= maximumBit <= 20</code></li>
	<li><code>0 <= nums[i] < 2<sup>maximumBit</sup></code></li>
	<li><code>nums</code>​​​ 中的数字已经按 <strong>升序</strong> 排好序。</li>
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
