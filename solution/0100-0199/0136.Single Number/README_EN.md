# [136. Single Number](https://leetcode.com/problems/single-number)

[中文文档](/solution/0100-0199/0136.Single%20Number/README.md)

## Description

<p>Given a <strong>non-empty</strong>&nbsp;array of integers, every element appears <em>twice</em> except for one. Find that single one.</p>

<p><strong>Note:</strong></p>

<p>Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [2,2,1]

<strong>Output:</strong> 1

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [4,1,2,1,2]

<strong>Output:</strong> 4

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        res = 0
        for num in nums:
            res ^= num
        return res
```

### **Java**

```java
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
