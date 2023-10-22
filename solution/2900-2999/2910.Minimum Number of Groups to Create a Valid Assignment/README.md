# [2910. 合法分组的最少组数](https://leetcode.cn/problems/minimum-number-of-groups-to-create-a-valid-assignment)

[English Version](/solution/2900-2999/2910.Minimum%20Number%20of%20Groups%20to%20Create%20a%20Valid%20Assignment/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>我们想将下标进行分组，使得&nbsp;<code>[0, n - 1]</code>&nbsp;内所有下标&nbsp;<code>i</code>&nbsp;都 <strong>恰好</strong> 被分到其中一组。</p>

<p>如果以下条件成立，我们说这个分组方案是合法的：</p>

<ul>
	<li>对于每个组&nbsp;<code>g</code>&nbsp;，同一组内所有下标在&nbsp;<code>nums</code>&nbsp;中对应的数值都相等。</li>
	<li>对于任意两个组&nbsp;<code>g<sub>1</sub></code> 和&nbsp;<code>g<sub>2</sub></code>&nbsp;，两个组中&nbsp;<strong>下标数量</strong> 的&nbsp;<strong>差值不超过&nbsp;</strong><code>1</code>&nbsp;。</li>
</ul>

<p>请你返回一个整数，表示得到一个合法分组方案的 <strong>最少</strong>&nbsp;组数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,3,2,3]
<b>输出：</b>2
<b>解释：</b>一个得到 2 个分组的方案如下，中括号内的数字都是下标：
组 1 -&gt; [0,2,4]
组 2 -&gt; [1,3]
所有下标都只属于一个组。
组 1 中，nums[0] == nums[2] == nums[4] ，所有下标对应的数值都相等。
组 2 中，nums[1] == nums[3] ，所有下标对应的数值都相等。
组 1 中下标数目为 3 ，组 2 中下标数目为 2 。
两者之差不超过 1 。
无法得到一个小于 2 组的答案，因为如果只有 1 组，组内所有下标对应的数值都要相等。
所以答案为 2 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [10,10,10,3,1,1]
<b>输出：</b>4
<b>解释：</b>一个得到 2 个分组的方案如下，中括号内的数字都是下标：
组 1 -&gt; [0]
组 2 -&gt; [1,2]
组 3 -&gt; [3]
组 4 -&gt; [4,5]
分组方案满足题目要求的两个条件。
无法得到一个小于 4 组的答案。
所以答案为 4 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
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
