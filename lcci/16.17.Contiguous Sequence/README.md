# [面试题 16.17. 连续数列](https://leetcode.cn/problems/contiguous-sequence-lcci)

[English Version](/lcci/16.17.Contiguous%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong> 6
<strong>解释：</strong> 连续子数组 [4,-1,2,1] 的和最大，为 6。
</pre>

<p><strong>进阶：</strong></p>

<p>如果你已经实现复杂度为 O(<em>n</em>) 的解法，尝试使用更为精妙的分治法求解。</p>

## 解法

动态规划

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
 * @return {number}
 */
var maxSubArray = function (nums) {
    let dp = [-Infinity];
    for (let i = 0; i < nums.length; i++) {
        let cur = nums[i];
        dp[i + 1] = Math.max(dp[i] + cur, cur);
    }
    return Math.max(...dp);
};
```

### **...**

```


```

<!-- tabs:end -->
