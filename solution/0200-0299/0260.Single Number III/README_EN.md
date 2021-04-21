# [260. Single Number III](https://leetcode.com/problems/single-number-iii)

[中文文档](/solution/0200-0299/0260.Single%20Number%20III/README.md)

## Description

<p>Given an integer array <code>nums</code>, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in <strong>any order</strong>.</p>

<p><strong>Follow up:&nbsp;</strong>Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1,3,2,5]
<strong>Output:</strong> [3,5]
<strong>Explanation: </strong> [5, 3] is also a valid answer.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0]
<strong>Output:</strong> [-1,0]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> [1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Each integer in <code>nums</code> will appear twice, only two integers will appear once.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        eor = 0
        for num in nums:
            eor ^= num
        diff = eor & (~eor + 1)
        a = 0
        for num in nums:
            if (num & diff) == 0:
                a ^= num
        b = eor ^ a
        return [a, b]
```

### **Java**

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int num : nums) {
            if ((num & diff) == 0) {
                a ^= num;
            }
        }
        int b = eor ^ a;
        return new int[]{a, b};
    }
}
```

### **...**

```

```

<!-- tabs:end -->
