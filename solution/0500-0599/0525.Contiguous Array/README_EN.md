# [525. Contiguous Array](https://leetcode.com/problems/contiguous-array)

[中文文档](/solution/0500-0599/0525.Contiguous%20Array/README.md)

## Description

<p>Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1. </p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [0,1]

<b>Output:</b> 2

<b>Explanation:</b> [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> [0,1,0]

<b>Output:</b> 2

<b>Explanation:</b> [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

</pre>

</p>

<p><b>Note:</b>

The length of the given binary array will not exceed 50,000.

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp
class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        int presum = 0;
        int maxlen = 0;
        unordered_map<int, int> mp;
        mp[0] = -1;
        for (int i = 0; i < nums.size(); i++) {
            presum += nums[i] == 0? -1: 1;
            if (mp.find(presum) != mp.end())
                maxlen = max(maxlen, i - mp[presum]);
            else
                mp[presum] = i;
        }

        return maxlen;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
