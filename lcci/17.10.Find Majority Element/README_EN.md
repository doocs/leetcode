# [17.10. Find Majority Element](https://leetcode-cn.com/problems/find-majority-element-lcci)

[中文文档](/lcci/17.10.Find%20Majority%20Element/README.md)

## Description

<p>A majority element is an element that makes up more than half of the items in an array. Given a positive integers array, find the majority element. If there is no majority element, return -1. Do this in O(N) time and O(1) space.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>[1,2,5,9,5,9,5,5,5]

<strong>Output: </strong>5</pre>

<p>&nbsp;</p>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>[3,2]

<strong>Output: </strong>-1</pre>

<p>&nbsp;</p>

<p><strong>Example 3: </strong></p>

<pre>

<strong>Input: </strong>[2,2,1,1,1,2,2]

<strong>Output: </strong>2

</pre>

## Solutions

Boyer–Moore majority vote algorithm

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
var majorityElement = function(nums) {
    let candidate = 0, count = 0;
    for (let num of nums) {
        if (count == 0) candidate = num;
        if (candidate == num) {
            count++;
        } else {
            count--;
        }
    }
    let n = 0;
    for (let num of nums) {
        if (candidate == num) n++;
    }
    return n > (nums.length / 2) ? candidate : -1;
};
```

### **...**

```

```

<!-- tabs:end -->
