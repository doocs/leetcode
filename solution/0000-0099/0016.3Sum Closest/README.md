# [16. 最接近的三数之和](https://leetcode.cn/problems/3sum-closest)

[English Version](/solution/0000-0099/0016.3Sum%20Closest/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code><em>&nbsp;</em>和 一个目标值&nbsp;<code>target</code>。请你从 <code>nums</code><em> </em>中选出三个整数，使它们的和与&nbsp;<code>target</code>&nbsp;最接近。</p>

<p>返回这三个数的和。</p>

<p>假定每组输入只存在恰好一个解。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,2,1,-4], target = 1
<strong>输出：</strong>2
<strong>解释：</strong>与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0], target = 1
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针解决。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
