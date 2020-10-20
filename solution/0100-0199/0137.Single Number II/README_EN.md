# [137. Single Number II](https://leetcode.com/problems/single-number-ii)

[中文文档](/solution/0100-0199/0137.Single%20Number%20II/README.md)

## Description

<p>Given a <strong>non-empty</strong>&nbsp;array of integers, every element appears <em>three</em> times except for one, which appears exactly once. Find that single one.</p>

<p><strong>Note:</strong></p>

<p>Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [2,2,3,2]

<strong>Output:</strong> 3

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [0,1,0,1,0,1,99]

<strong>Output:</strong> 99</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; ++i) {
                bits[i] += (num & 1);
                num >>= 1;
            }
        }

        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 == 1) {
                res += (1 << i);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
