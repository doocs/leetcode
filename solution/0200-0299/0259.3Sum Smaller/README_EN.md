# [259. 3Sum Smaller](https://leetcode.com/problems/3sum-smaller)

[中文文档](/solution/0200-0299/0259.3Sum%20Smaller/README.md)

## Description

<p>Given an array of <code>n</code> integers <code>nums</code> and an integer&nbsp;<code>target</code>, find the number of index triplets <code>i</code>, <code>j</code>, <code>k</code> with <code>0 &lt;= i &lt; j &lt; k &lt; n</code> that satisfy the condition <code>nums[i] + nums[j] + nums[k] &lt; target</code>.</p>

<p><b style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">Follow up:</b> Could you solve it in <code>O(n<sup>2</sup>)</code> runtime?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,1,3], target = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [], target = 0
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0], target = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>0 &lt;= n &lt;= 300</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>-100 &lt;= target &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        def threeSumSmaller(nums, start, end, target):
            count = 0
            while start < end:
                if nums[start] + nums[end] < target:
                    count += (end - start)
                    start += 1
                else:
                    end -= 1
            return count

        nums.sort()
        n, count = len(nums), 0
        for i in range(n - 2):
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i])
        return count
```

### **Java**

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; ++i) {
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i]);
        }
        return count;
    }

    private int threeSumSmaller(int[] nums, int start, int end, int target) {
        int count = 0;
        while (start < end) {
            if (nums[start] + nums[end] < target) {
                count += (end - start);
                ++start;
            } else {
                --end;
            }
        }
        return count;
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
var threeSumSmaller = function (nums, target) {
    let len = nums.length;
    if (len < 3) return 0;
    nums.sort((a, b) => a - b);
    let res = 0;
    for (let i = 0; i < len - 2; i++) {
        let left = i + 1,
            right = len - 1;
        if (nums[i] + nums[left] + nums[i + 2] >= target) break;
        while (left < right) {
            if (nums[i] + nums[left] + nums[right] < target) {
                res += right - left;
                left++;
                continue;
            } else {
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
