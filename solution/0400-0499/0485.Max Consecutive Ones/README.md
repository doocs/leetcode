# [485. 最大连续 1 的个数](https://leetcode-cn.com/problems/max-consecutive-ones)

[English Version](/solution/0400-0499/0485.Max%20Consecutive%20Ones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组， 计算其中最大连续 1 的个数。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>[1,1,0,1,1,1]
<strong>输出：</strong>3
<strong>解释：</strong>开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>输入的数组只包含 <code>0</code> 和 <code>1</code> 。</li>
	<li>输入数组的长度是正整数，且不超过 10,000。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
