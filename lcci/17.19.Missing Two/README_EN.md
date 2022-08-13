# [17.19. Missing Two](https://leetcode.cn/problems/missing-two-lcci)

[中文文档](/lcci/17.19.Missing%20Two/README.md)

## Description

<p>You are given an array with all the numbers from 1 to N appearing exactly once, except for two number that is missing. How can you find the missing number in O(N) time and 0(1) space?</p>

<p>You can return the missing numbers in any order.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [1]

<strong>Output: </strong>[2,3]</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [2,3]

<strong>Output: </strong>[1,4]</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>nums.length &lt;=&nbsp;30000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingTwo(self, nums: List[int]) -> List[int]:
        res, n = 0, len(nums)
        for i in range(n):
            res ^= nums[i]
            res ^= i + 1
        res ^= n + 1
        res ^= n + 2
        pos = 0
        while (res & 1) == 0:
            pos += 1
            res >>= 1

        a = b = 0
        for num in nums:
            t = num >> pos
            if (t & 1) == 0:
                a ^= num
            else:
                b ^= num

        for i in range(1, n + 3):
            t = i >> pos
            if (t & 1) == 0:
                a ^= i
            else:
                b ^= i
        return [a, b]
```

### **Java**

```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            res ^= nums[i];
            res ^= (i + 1);
        }
        res ^= (n + 1);
        res ^= (n + 2);

        int pos = 0;
        while ((res & 1) == 0) {
            pos += 1;
            res >>= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            int t = num >> pos;
            if ((t & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        for (int i = 1; i <= n + 2; ++i) {
            int t = i >> pos;
            if ((t & 1) == 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        return new int[]{a, b};
    }
}
```

### **...**

```

```

<!-- tabs:end -->
