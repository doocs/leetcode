# [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest)

[中文文档](/solution/0000-0099/0016.3Sum%20Closest/README.md)

## Description

<p>Given an integer array <code>nums</code> of length <code>n</code> and an integer <code>target</code>, find three integers in <code>nums</code> such that the sum is closest to <code>target</code>.</p>

<p>Return <em>the sum of the three integers</em>.</p>

<p>You may assume that each input would have exactly one solution.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,2,1,-4], target = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0], target = 1
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        def twoSumClosest(nums, start, end, target):
            res = 0
            diff = 10000
            while start < end:
                val = nums[start] + nums[end]
                if val == target:
                    return val
                if abs(val - target) < diff:
                    res = val
                    diff = abs(val - target)
                if val < target:
                    start += 1
                else:
                    end -= 1
            return res

        nums.sort()
        res, n = 0, len(nums)
        diff = 10000
        for i in range(n - 2):
            t = twoSumClosest(nums, i + 1, n - 1, target - nums[i])
            if abs(nums[i] + t - target) < diff:
                res = nums[i] + t
                diff = abs(nums[i] + t - target)
        return res
```

### **Java**

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; ++i) {
            int t = twoSumClosest(nums, i + 1, n - 1, target - nums[i]);
            if (Math.abs(nums[i] + t - target) < diff) {
                res = nums[i] + t;
                diff = Math.abs(nums[i] + t - target);
            }
        }
        return res;
    }

    private int twoSumClosest(int[] nums, int start, int end, int target) {
        int res = 0;
        int diff = Integer.MAX_VALUE;
        while (start < end) {
            int val = nums[start] + nums[end];
            if (val == target) {
                return val;
            }
            if (Math.abs(val - target) < diff) {
                res = val;
                diff = Math.abs(val - target);
            }
            if (val < target) {
                ++start;
            } else {
                --end;
            }
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function (nums, target) {
    let len = nums.length;
    nums.sort((a, b) => a - b);
    let diff = Infinity;
    let res;
    for (let i = 0; i < len - 2; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        let left = i + 1,
            right = len - 1;
        let cur = nums[i] + nums[i + 1] + nums[i + 2];
        if (cur > target) {
            let newDiff = Math.abs(cur - target);
            if (newDiff < diff) {
                diff = newDiff;
                res = cur;
            }
            break;
        }
        while (left < right) {
            cur = nums[i] + nums[left] + nums[right];
            if (cur === target) return target;
            let newDiff = Math.abs(cur - target);
            if (newDiff < diff) {
                diff = newDiff;
                res = cur;
            }
            if (cur < target) {
                while (nums[left] === nums[left + 1]) left++;
                left++;
                continue;
            } else {
                while (nums[right] === nums[right - 1]) right--;
                right--;
                continue;
            }
        }
    }
    return res;
};
```

### **...**

```

```

<!-- tabs:end -->
