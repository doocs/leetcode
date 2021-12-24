# [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones)

[中文文档](/solution/0400-0499/0485.Max%20Consecutive%20Ones/README.md)

## Description

<p>Given a binary array <code>nums</code>, return <em>the maximum number of consecutive </em><code>1</code><em>&#39;s in the array</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,1,0,1]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

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
