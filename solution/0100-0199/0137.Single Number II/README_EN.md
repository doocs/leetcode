# [137. Single Number II](https://leetcode.com/problems/single-number-ii)

[中文文档](/solution/0100-0199/0137.Single%20Number%20II/README.md)

## Description

<p>Given an integer array <code>nums</code> where&nbsp;every element appears <strong>three times</strong> except for one, which appears <strong>exactly once</strong>. <em>Find the single element and return it</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,3,2]
<strong>Output:</strong> 3
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0,1,0,1,0,1,99]
<strong>Output:</strong> 99
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>Each element in <code>nums</code> appears exactly <strong>three times</strong> except for one element which appears <strong>once</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong>&nbsp;Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        bits = [0] * 32
        for num in nums:
            for i in range(32):
                bits[i] += (num & 1)
                num >>= 1
        res = 0
        for i in range(32):
            if bits[i] % 3 != 0:
                res |= (1 << i)
        return res if bits[31] % 3 == 0 else ~(res ^ 0xffffffff)
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
                res |= (1 << i);
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
