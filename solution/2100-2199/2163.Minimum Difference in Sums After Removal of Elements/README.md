# [2163. 删除元素后和的最小差值](https://leetcode.cn/problems/minimum-difference-in-sums-after-removal-of-elements)

[English Version](/solution/2100-2199/2163.Minimum%20Difference%20in%20Sums%20After%20Removal%20of%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它包含&nbsp;<code>3 * n</code>&nbsp;个元素。</p>

<p>你可以从 <code>nums</code>&nbsp;中删除 <strong>恰好</strong>&nbsp;<code>n</code>&nbsp;个元素，剩下的 <code>2 * n</code>&nbsp;个元素将会被分成两个 <strong>相同大小</strong>&nbsp;的部分。</p>

<ul>
	<li>前面&nbsp;<code>n</code>&nbsp;个元素属于第一部分，它们的和记为&nbsp;<code>sum<sub>first</sub></code>&nbsp;。</li>
	<li>后面&nbsp;<code>n</code>&nbsp;个元素属于第二部分，它们的和记为&nbsp;<code>sum<sub>second</sub></code>&nbsp;。</li>
</ul>

<p>两部分和的 <strong>差值</strong>&nbsp;记为&nbsp;<code>sum<sub>first</sub> - sum<sub>second</sub></code>&nbsp;。</p>

<ul>
	<li>比方说，<code>sum<sub>first</sub> = 3</code> 且&nbsp;<code>sum<sub>second</sub> = 2</code>&nbsp;，它们的差值为&nbsp;<code>1</code>&nbsp;。</li>
	<li>再比方，<code>sum<sub>first</sub> = 2</code> 且&nbsp;<code>sum<sub>second</sub> = 3</code>&nbsp;，它们的差值为&nbsp;<code>-1</code>&nbsp;。</li>
</ul>

<p>请你返回删除 <code>n</code>&nbsp;个元素之后，剩下两部分和的 <strong>差值的最小值</strong>&nbsp;是多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,1,2]
<b>输出：</b>-1
<b>解释：</b>nums 有 3 个元素，所以 n = 1 。
所以我们需要从 nums 中删除 1 个元素，并将剩下的元素分成两部分。
- 如果我们删除 nums[0] = 3 ，数组变为 [1,2] 。两部分和的差值为 1 - 2 = -1 。
- 如果我们删除 nums[1] = 1 ，数组变为 [3,2] 。两部分和的差值为 3 - 2 = 1 。
- 如果我们删除 nums[2] = 2 ，数组变为 [3,1] 。两部分和的差值为 3 - 1 = 2 。
两部分和的最小差值为 min(-1,1,2) = -1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [7,9,5,8,1,3]
<b>输出：</b>1
<b>解释：</b>n = 2 。所以我们需要删除 2 个元素，并将剩下元素分为 2 部分。
如果我们删除元素 nums[2] = 5 和 nums[3] = 8 ，剩下元素为 [7,9,1,3] 。和的差值为 (7+9) - (1+3) = 12 。
为了得到最小差值，我们应该删除 nums[1] = 9 和 nums[4] = 1 ，剩下的元素为 [7,5,8,3] 。和的差值为 (7+5) - (8+3) = 1 。
观察可知，最优答案为 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == 3 * n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
