# [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones)

[中文文档](/solution/0400-0499/0485.Max%20Consecutive%20Ones/README.md)

## Description

<p>Given a binary array, find the maximum number of consecutive 1s in this array.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [1,1,0,1,1,1]

<b>Output:</b> 3

<b>Explanation:</b> The first two digits or the last three digits are consecutive 1s.

    The maximum number of consecutive 1s is 3.

</pre>

</p>

<p><b>Note:</b>

<ul>

<li>The input array will only contain <code>0</code> and <code>1</code>.</li>

<li>The length of input array is a positive integer and will not exceed 10,000</li>

</ul>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        res = t = 0
        for num in nums:
            if num == 1:
                t += 1
            else:
                res = max(res, t)
                t = 0
        return max(res, t)
```

### **Java**

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, t = 0;
        for (int num : nums) {
            if (num == 1) {
                ++t;
            } else {
                res = Math.max(res, t);
                t = 0;
            }
        }
        return Math.max(res, t);
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function (nums) {
  let res = 0,
    t = 0;
  for (let num of nums) {
    if (num == 1) {
      ++t;
    } else {
      res = Math.max(res, t);
      t = 0;
    }
  }
  return Math.max(res, t);
};
```

### **...**

```

```

<!-- tabs:end -->
