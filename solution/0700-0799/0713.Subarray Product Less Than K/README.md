# [713. 乘积小于 K 的子数组](https://leetcode-cn.com/problems/subarray-product-less-than-k)

[English Version](/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数数组&nbsp;<code>nums</code>。</p>

<p>找出该数组内乘积小于&nbsp;<code>k</code>&nbsp;的连续的子数组的个数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [10,5,2,6], k = 100
<strong>输出:</strong> 8
<strong>解释:</strong> 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
</pre>

<p><strong>说明:</strong></p>

<ul>
	<li><code>0 &lt; nums.length &lt;= 50000</code></li>
	<li><code>0 &lt; nums[i] &lt; 1000</code></li>
	<li><code>0 &lt;= k &lt; 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
