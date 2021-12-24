# [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k)

[中文文档](/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README.md)

## Description

<p>Your are given an array of positive integers <code>nums</code>.</p>

<p>Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than <code>k</code>.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> nums = [10, 5, 2, 6], k = 100

<b>Output:</b> 8

<b>Explanation:</b> The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].

Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

</pre>

</p>

<p><b>Note:</b>

<li><code>0 < nums.length <= 50000</code>.</li>

<li><code>0 < nums[i] < 1000</code>.</li>

<li><code>0 <= k < 10^6</code>.</li>

</p>

## Solutions

<!-- tabs:start -->

### **C++**

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int left = 0, right;
        long mul = 1;
        int count = 0;

        for (right = 0; right < nums.size(); right++) {
            mul *= nums[right];

            while(left <= right && mul >= k) {
                mul /= nums[left++];
            }

            count += right >= left? right - left + 1: 0;
        }

        return count;
    }
};
```

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
