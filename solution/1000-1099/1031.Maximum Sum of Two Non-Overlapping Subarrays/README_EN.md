# [1031. Maximum Sum of Two Non-Overlapping Subarrays](https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays)

[中文文档](/solution/1000-1099/1031.Maximum%20Sum%20of%20Two%20Non-Overlapping%20Subarrays/README.md)

## Description

<p>Given an integer array <code>nums</code> and two integers <code>firstLen</code> and <code>secondLen</code>, return <em>the maximum sum of elements in two non-overlapping <strong>subarrays</strong> with lengths </em><code>firstLen</code><em> and </em><code>secondLen</code>.</p>

<p>The array with length <code>firstLen</code> could occur before or after the array with length <code>secondLen</code>, but they have to be non-overlapping.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong> One choice of subarrays is [9] with length 1, and [6,5] with length 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
<strong>Output:</strong> 29
<strong>Explanation:</strong> One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
<strong>Output:</strong> 31
<strong>Explanation:</strong> One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= firstLen, secondLen &lt;= 1000</code></li>
	<li><code>2 &lt;= firstLen + secondLen &lt;= 1000</code></li>
	<li><code>firstLen + secondLen &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        s = [0] * (n + 1)
        for i in range(1, n + 1):
            s[i] = s[i - 1] + nums[i - 1]
        ans1, ans2, fm, sm = 0, 0, 0, 0
        for i in range(n - firstLen - secondLen + 1):
            fm = max(fm, s[i + firstLen] - s[i])
            ans1 = max(fm + s[i + firstLen + secondLen] - s[i + firstLen], ans1)
        for i in range(n - firstLen - secondLen + 1):
            sm = max(sm, s[i + secondLen] - s[i])
            ans2 = max(sm + s[i + firstLen + secondLen] - s[i + secondLen], ans2)
        return max(ans1, ans2)
```

### **Java**

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans1 = 0, ans2 = 0, fm = 0, sm = 0;
        for (int i = 0; i < n - firstLen - secondLen + 1; i++) {
            fm = Math.max(s[i + firstLen] - s[i], fm);
            ans1 = Math.max(fm + s[i + firstLen + secondLen] - s[i + firstLen], ans1);
        }
        for (int i = 0; i < n - firstLen - secondLen + 1; i++) {
            sm = Math.max(s[i + secondLen] - s[i], sm);
            ans2 = Math.max(sm + s[i + firstLen + secondLen] - s[i + secondLen], ans2);
        }
        return Math.max(ans1, ans2);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
