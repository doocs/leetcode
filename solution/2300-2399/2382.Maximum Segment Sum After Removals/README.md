# [2382. 删除操作后的最大子段和](https://leetcode.cn/problems/maximum-segment-sum-after-removals)

[English Version](/solution/2300-2399/2382.Maximum%20Segment%20Sum%20After%20Removals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code> 和&nbsp;<code>removeQueries</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。对于第&nbsp;<code>i</code>&nbsp;个查询，<code>nums</code>&nbsp;中位于下标&nbsp;<code>removeQueries[i]</code>&nbsp;处的元素被删除，将 <code>nums</code>&nbsp;分割成更小的子段。</p>

<p>一个 <strong>子段</strong>&nbsp;是 <code>nums</code>&nbsp;中连续 <strong>正</strong>&nbsp;整数形成的序列。<strong>子段和</strong>&nbsp;是子段中所有元素的和。</p>

<p>请你返回一个长度为 <code>n</code>&nbsp;的整数数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[i]</code>是第&nbsp;<code>i</code>&nbsp;次删除操作以后的&nbsp;<strong>最大</strong>&nbsp;子段和。</p>

<p><strong>注意：</strong>一个下标至多只会被删除一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
<b>输出：</b>[14,7,2,2,0]
<b>解释：</b>用 0 表示被删除的元素，答案如下所示：
查询 1 ：删除第 0 个元素，nums 变成 [0,2,5,6,1] ，最大子段和为子段 [2,5,6,1] 的和 14 。
查询 2 ：删除第 3 个元素，nums 变成 [0,2,5,0,1] ，最大子段和为子段 [2,5] 的和 7 。
查询 3 ：删除第 2 个元素，nums 变成 [0,2,0,0,1] ，最大子段和为子段 [2] 的和 2 。
查询 4 ：删除第 4 个元素，nums 变成 [0,2,0,0,0] ，最大子段和为子段 [2] 的和 2 。
查询 5 ：删除第 1 个元素，nums 变成 [0,0,0,0,0] ，最大子段和为 0 ，因为没有任何子段存在。
所以，我们返回 [14,7,2,2,0] 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [3,2,11,1], removeQueries = [3,2,1,0]
<b>输出：</b>[16,5,3,0]
<b>解释：</b>用 0 表示被删除的元素，答案如下所示：
查询 1 ：删除第 3 个元素，nums 变成 [3,2,11,0] ，最大子段和为子段 [3,2,11] 的和 16 。
查询 2 ：删除第 2 个元素，nums 变成 [3,2,0,0] ，最大子段和为子段 [3,2] 的和 5 。
查询 3 ：删除第 1 个元素，nums 变成 [3,0,0,0] ，最大子段和为子段 [3] 的和 3 。
查询 5 ：删除第 0 个元素，nums 变成 [0,0,0,0] ，最大子段和为 0 ，因为没有任何子段存在。
所以，我们返回 [16,5,3,0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length == removeQueries.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= removeQueries[i] &lt; n</code></li>
	<li><code>removeQueries</code>&nbsp;中所有数字 <strong>互不相同</strong>&nbsp;。</li>
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

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
