# [327. 区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum)

[English Version](/solution/0300-0399/0327.Count%20of%20Range%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code> 。区间和 <code>S(i, j)</code> 表示在 <code>nums</code> 中，位置从 <code>i</code> 到 <code>j</code> 的元素之和，包含 <code>i</code> 和 <code>j</code> (<code>i</code> ≤ <code>j</code>)。</p>

<p>请你以下标 <code>i</code> （<code>0 <= i <= nums.length</code> ）为起点，元素个数逐次递增，计算子数组内的元素和。</p>

<p>当元素和落在范围 <code>[lower, upper]</code> （包含 <code>lower</code> 和 <code>upper</code>）之内时，记录子数组当前最末元素下标 <code>j</code> ，记作 <strong>有效</strong> 区间和 <code>S(i, j)</code> 。</p>

<p>求数组中，值位于范围 <code>[lower, upper]</code> （包含 <code>lower</code> 和 <code>upper</code>）之内的 <strong>有效</strong> 区间和的个数。</p>

<p> </p>

<p><strong>注意：</strong><br />
最直观的算法复杂度是 <em>O</em>(<em>n</em><sup>2</sup>) ，请在此基础上优化你的算法。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong><em>nums</em> = <code>[-2,5,-1]</code>, <em>lower</em> = <code>-2</code>, <em>upper</em> = <code>2</code>,
<strong>输出：</strong>3 
<strong>解释：</strong>
下标 i = 0 时，子数组 [-2]、[-2,5]、[-2,5,-1]，对应元素和分别为 -2、3、2 ；其中 -2 和 2 落在范围 [lower = -2, upper = 2] 之间，因此记录有效区间和 S(0,0)，S(0,2) 。
下标 i = 1 时，子数组 [5]、[5,-1] ，元素和 5、4 ；没有满足题意的有效区间和。
下标 i = 2 时，子数组 [-1] ，元素和 -1 ；记录有效区间和 S(2,2) 。
故，共有 3 个有效区间和。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 10^4</code></li>
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
