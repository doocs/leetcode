# [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum)

[中文文档](/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README.md)

## Description

<p>Given an array of positive integers <code>nums</code> and a positive integer <code>target</code>, return the minimal length of a <strong>contiguous subarray</strong> <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> of which the sum is greater than or equal to <code>target</code>. If there is no such subarray, return <code>0</code> instead.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 7, nums = [2,3,1,2,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The subarray [4,3] has the minimal length under the problem constraint.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 4, nums = [1,4,4]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution of which the time complexity is <code>O(n log(n))</code>.

## Solutions

<!-- tabs:start -->

### **Python3**

Presum and binary search.

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        pre_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            pre_sum[i] = pre_sum[i - 1] + nums[i - 1]
        res = n + 1
        for i in range(1, n + 1):
            t = pre_sum[i - 1] + target
            left, right = 0, n
            while left < right:
                mid = (left + right) >> 1
                if pre_sum[mid] >= t:
                    right = mid
                else:
                    left = mid + 1
            if pre_sum[left] - pre_sum[i - 1] >= target:
                res = min(res, left - i + 1)
        return 0 if res == n + 1 else res
```

Slide window.

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        left = right = 0
        sum, res = 0, n + 1
        while right < n:
            sum += nums[right]
            while sum >= target:
                res = min(res, right - left + 1)
                sum -= nums[left]
                left += 1
            right += 1
        return 0 if res == n + 1 else res
```

### **Java**

Presum and binary search.

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            preSum[i] = preSum[i - 1] +nums[i - 1];
        }
        int res = n + 1;
        for (int i = 1; i <= n; ++i) {
            int t = preSum[i - 1] + target;
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (preSum[mid] >= t) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (preSum[left] - preSum[i - 1] >= target) {
                res = Math.min(res, left - i + 1);
            }
        }
        return res == n + 1 ? 0 : res;
    }
}
```

Slide window.

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0, res = n + 1;
        while (right < n) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
            ++right;
        }
        return res == n + 1 ? 0 : res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int left = 0, right;
        int sum = 0;
        int minlen = INT_MAX;

        for (right = 0; right < nums.size(); right++) {
            sum += nums[right];
            while(left <= right && sum >= target) {
                minlen = min(minlen, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minlen == INT_MAX? 0: minlen;
    }
};
```

### **C#**

```cs
public class Solution {
    public int MinSubArrayLen(int target, int[] nums) {
        int n = nums.Length;
        int left = 0, right = 0;
        int sum = 0, res = n + 1;
        while (right < n)
        {
            sum += nums[right];
            while (sum >= target)
            {
                res = Math.Min(res, right - left + 1);
                sum -= nums[left++];
            }
            ++right;
        }
        return res == n + 1 ? 0 : res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
