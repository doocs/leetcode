# [17.04. Missing Number](https://leetcode-cn.com/problems/missing-number-lcci)

[中文文档](/lcci/17.04.Missing%20Number/README.md)

## Description
<p>An array&nbsp;contains all the integers from 0 to n, except for one number which is missing.&nbsp; Write code to find the missing integer. Can you do it in O(n) time?</p>



<p><strong>Note: </strong>This problem is slightly different from the original one the book.</p>



<p><strong>Example 1: </strong></p>



<pre>

<strong>Input: </strong>[3,0,1]

<strong>Output: </strong>2</pre>



<p>&nbsp;</p>



<p><strong>Example 2: </strong></p>



<pre>

<strong>Input: </strong>[9,6,4,2,3,5,7,0,1]

<strong>Output: </strong>8

</pre>




## Solutions


<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        res = 0
        for i, num in enumerate(nums):
            res ^= i
            res ^= num
        res ^= len(nums)
        return res
```

### **Java**

```java
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= n;
        return res;
    }
}
```

### **...**
```

```

<!-- tabs:end -->