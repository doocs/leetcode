# [16.17. Contiguous Sequence](https://leetcode.cn/problems/contiguous-sequence-lcci)

[中文文档](/lcci/16.17.Contiguous%20Sequence/README.md)

## Description

<p>You are given an array of integers (both positive and negative). Find the contiguous sequence with the largest sum. Return the sum.</p>

<p><strong>Example: </strong></p>

<pre>



<strong>Input: </strong> [-2,1,-3,4,-1,2,1,-5,4]



<strong>Output: </strong> 6



<strong>Explanation: </strong> [4,-1,2,1] has the largest sum 6.



</pre>

<p><strong>Follow Up: </strong></p>

<p>If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python


```

### **Java**

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
