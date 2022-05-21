# [2270. 分割数组的方案数](https://leetcode.cn/problems/number-of-ways-to-split-array)

[English Version](/solution/2200-2299/2270.Number%20of%20Ways%20to%20Split%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。<br />
<span style="">如果以下描述为真，那么</span><span style=""> </span><code>nums</code>&nbsp;在下标 <code>i</code>&nbsp;处有一个 <strong>合法的分割</strong>&nbsp;：</p>

<ul>
	<li>前&nbsp;<code>i + 1</code>&nbsp;个元素的和 <strong>大于等于</strong>&nbsp;剩下的&nbsp;<code>n - i - 1</code>&nbsp;个元素的和。</li>
	<li>下标 <code>i</code>&nbsp;的右边 <strong>至少有一个</strong>&nbsp;元素，也就是说下标&nbsp;<code>i</code>&nbsp;满足&nbsp;<code>0 &lt;= i &lt; n - 1</code>&nbsp;。</li>
</ul>

<p>请你返回&nbsp;<code>nums</code>&nbsp;中的&nbsp;<strong>合法分割</strong>&nbsp;方案数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [10,4,-8,7]
<b>输出：</b>2
<b>解释：</b>
总共有 3 种不同的方案可以将 nums 分割成两个非空的部分：
- 在下标 0 处分割 nums 。那么第一部分为 [10] ，和为 10 。第二部分为 [4,-8,7] ，和为 3 。因为 10 &gt;= 3 ，所以 i = 0 是一个合法的分割。
- 在下标 1 处分割 nums 。那么第一部分为 [10,4] ，和为 14 。第二部分为 [-8,7] ，和为 -1 。因为 14 &gt;= -1 ，所以 i = 1 是一个合法的分割。
- 在下标 2 处分割 nums 。那么第一部分为 [10,4,-8] ，和为 6 。第二部分为 [7] ，和为 7 。因为 6 &lt; 7 ，所以 i = 2 不是一个合法的分割。
所以 nums 中总共合法分割方案受为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,1,0]
<b>输出：</b>2
<b>解释：</b>
总共有 2 种 nums 的合法分割：
- 在下标 1 处分割 nums 。那么第一部分为 [2,3] ，和为 5 。第二部分为 [1,0] ，和为 1 。因为 5 &gt;= 1 ，所以 i = 1 是一个合法的分割。
- 在下标 2 处分割 nums 。那么第一部分为 [2,3,1] ，和为 6 。第二部分为 [0] ，和为 0 。因为 6 &gt;= 0 ，所以 i = 2 是一个合法的分割。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToSplitArray(self, nums: List[int]) -> int:
        n, left, right = len(nums), 0, sum(nums)
        cnt = 0
        for i in range(n - 1):
            left += nums[i]
            right -= nums[i]
            if left >= right:
                cnt += 1
        return cnt
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        long[] pre = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (pre[i] >= pre[nums.length] - pre[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
