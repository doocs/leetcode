# [1712. Ways to Split Array Into Three Subarrays](https://leetcode.com/problems/ways-to-split-array-into-three-subarrays)

[中文文档](/solution/1700-1799/1712.Ways%20to%20Split%20Array%20Into%20Three%20Subarrays/README.md)

## Description

<p>A split of an integer array is <strong>good</strong> if:</p>

<ul>
    <li>The array is split into three <strong>non-empty</strong> contiguous subarrays - named <code>left</code>, <code>mid</code>, <code>right</code> respectively from left to right.</li>
    <li>The sum of the elements in <code>left</code> is less than or equal to the sum of the elements in <code>mid</code>, and the sum of the elements in <code>mid</code> is less than or equal to the sum of the elements in <code>right</code>.</li>

</ul>

<p>Given <code>nums</code>, an array of <strong>non-negative</strong> integers, return <em>the number of <strong>good</strong> ways to split</em> <code>nums</code>. As the number may be too large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,1,1]

<strong>Output:</strong> 1

<strong>Explanation:</strong> The only good way to split nums is [1] [1] [1].</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,2,2,2,5,0]

<strong>Output:</strong> 3

<strong>Explanation:</strong> There are three good ways of splitting nums:

[1] [2] [2,2,5,0]

[1] [2,2] [2,5,0]

[1,2] [2,2] [5,0]

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> nums = [3,2,1]

<strong>Output:</strong> 0

<strong>Explanation:</strong> There is no good way to split nums.</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
    <li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 1e9 + 7
        n = len(nums)
        pre = [0] * (n + 1)
        for i in range(1, n + 1):
            pre[i] = pre[i - 1] + nums[i - 1]
        ans = 0
        for i in range(1, n - 1):
            if pre[i] * 3 > pre[n]:
                break
            left, right = i + 1, n - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if pre[mid] - pre[i] <= pre[n] - pre[mid]:
                    left = mid
                else:
                    right = mid - 1
            mid_right = left
            left, right = i + 1, n - 1
            while left < right:
                mid = (left + right) >> 1
                if pre[mid] - pre[i] >= pre[i]:
                    right = mid
                else:
                    left = mid + 1
            ans += (mid_right - left + 1) % mod
        return int(ans % mod)
```

### **Java**

```java
class Solution {
    public int waysToSplit(int[] nums) {
        double mod = 1e9 + 7;
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        double ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (pre[i] * 3 > pre[n]) {
                break;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if (pre[mid] - pre[i] <= pre[n] - pre[mid]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            int midRight = left;
            left = i + 1; right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (pre[mid] - pre[i] >= pre[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += (midRight - left + 1) % mod;
        }
        return (int) (ans % mod);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
