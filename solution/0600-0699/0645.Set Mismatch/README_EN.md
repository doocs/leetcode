# [645. Set Mismatch](https://leetcode.com/problems/set-mismatch)

[中文文档](/solution/0600-0699/0645.Set%20Mismatch/README.md)

## Description
<p>

The set <code>S</code> originally contains numbers from 1 to <code>n</code>. But unfortunately, due to the data error, one of the numbers in the set got duplicated to <b>another</b> number in the set, which results in repetition of one number and loss of another number. 

</p>



<p>

Given an array <code>nums</code> representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

</p>





<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> nums = [1,2,2,4]

<b>Output:</b> [2,3]

</pre>

</p>



<p><b>Note:</b><br>

<ol>

<li>The given array size will in the range [2, 10000].</li>

<li>The given array's numbers won't have any order.</li>

</ol>

</p>


## Solutions


<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        res = 0
        for num in nums:
            res ^= num
        for i in range(1, len(nums) + 1):
            res ^= i
        pos = 0
        while (res & 1) == 0:
            res >>= 1
            pos += 1
        a = b = 0
        for num in nums:
            if ((num >> pos) & 1) == 0:
                a ^= num
            else:
                b ^= num
        for i in range(1, len(nums) + 1):
            if ((i >> pos) & 1) == 0:
                a ^= i
            else:
                b ^= i
        for num in nums:
            if num == a:
                return [a, b]
        return [b, a]
```

### **Java**

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        for (int i = 1, n = nums.length; i < n + 1; ++i) {
            res ^= i;
        }
        int pos = 0;
        while ((res & 1) == 0) {
            res >>= 1;
            ++pos;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if (((num >> pos) & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        for (int i = 1, n = nums.length; i < n + 1; ++i) {
            if (((i >> pos) & 1) == 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        for (int num : nums) {
            if (num == a) {
                return new int[]{a, b};
            }
        }
        return new int[]{b, a};
    }
}
```

### **...**
```

```

<!-- tabs:end -->