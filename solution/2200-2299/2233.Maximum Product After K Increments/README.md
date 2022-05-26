# [2233. K 次增加后的最大乘积](https://leetcode.cn/problems/maximum-product-after-k-increments)

[English Version](/solution/2200-2299/2233.Maximum%20Product%20After%20K%20Increments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非负整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。每次操作，你可以选择&nbsp;<code>nums</code>&nbsp;中 <strong>任一</strong>&nbsp;元素并将它 <strong>增加</strong>&nbsp;<code>1</code>&nbsp;。</p>

<p>请你返回 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;次操作后，能得到的<em>&nbsp;</em><code>nums</code>的&nbsp;<strong>最大乘积</strong>&nbsp;。由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [0,4], k = 5
<b>输出：</b>20
<b>解释：</b>将第一个数增加 5 次。
得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
可以证明 20 是能得到的最大乘积，所以我们返回 20 。
存在其他增加 nums 的方法，也能得到最大乘积。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [6,3,3,2], k = 2
<b>输出：</b>216
<b>解释：</b>将第二个数增加 1 次，将第四个数增加 1 次。
得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
可以证明 216 是能得到的最大乘积，所以我们返回 216 。
存在其他增加 nums 的方法，也能得到最大乘积。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maximumProduct = function (nums, k) {
    const n = nums.length;
    let pq = new MinPriorityQueue();
    for (let i = 0; i < n; i++) {
        pq.enqueue(nums[i]);
    }
    for (let i = 0; i < k; i++) {
        pq.enqueue(pq.dequeue().element + 1);
    }
    let ans = 1;
    const limit = 10 ** 9 + 7;
    for (let i = 0; i < n; i++) {
        ans = (ans * pq.dequeue().element) % limit;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
