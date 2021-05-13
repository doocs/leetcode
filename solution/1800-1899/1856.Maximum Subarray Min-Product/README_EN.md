# [1856. Maximum Subarray Min-Product](https://leetcode.com/problems/maximum-subarray-min-product)

[中文文档](/solution/1800-1899/1856.Maximum%20Subarray%20Min-Product/README.md)

## Description

<p>The <strong>min-product</strong> of an array is equal to the <strong>minimum value</strong> in the array <strong>multiplied by</strong> the array&#39;s <strong>sum</strong>.</p>

<ul>
	<li>For example, the array <code>[3,2,5]</code> (minimum value is <code>2</code>) has a min-product of <code>2 * (3+2+5) = 2 * 10 = 20</code>.</li>
</ul>

<p>Given an array of integers <code>nums</code>, return <em>the <strong>maximum min-product</strong> of any <strong>non-empty subarray</strong> of </em><code>nums</code>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that the min-product should be maximized <strong>before</strong> performing the modulo operation. Testcases are generated such that the maximum min-product <strong>without</strong> modulo will fit in a <strong>64-bit signed integer</strong>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,<u>2,3,2</u>]
<strong>Output:</strong> 14
<strong>Explanation:</strong> The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
2 * (2+3+2) = 2 * 7 = 14.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,<u>3,3</u>,1,2]
<strong>Output:</strong> 18
<strong>Explanation:</strong> The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
3 * (3+3) = 3 * 6 = 18.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,<u>5,6,4</u>,2]
<strong>Output:</strong> 60
<strong>Explanation:</strong> The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
4 * (5+6+4) = 4 * 15 = 60.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        n = len(nums)
        pre_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            pre_sum[i] = pre_sum[i - 1] + nums[i - 1]

        stack = []
        next_lesser = [n] * n
        for i in range(n):
            while stack and nums[stack[-1]] > nums[i]:
                next_lesser[stack.pop()] = i
            stack.append(i)

        stack = []
        prev_lesser = [-1] * n
        for i in range(n - 1, -1, -1):
            while stack and nums[stack[-1]] > nums[i]:
                prev_lesser[stack.pop()] = i
            stack.append(i)

        res = 0
        for i in range(n):
            start, end = prev_lesser[i], next_lesser[i]
            t = nums[i] * (pre_sum[end] - pre_sum[start + 1])
            res = max(res, t)
        return res % (10 ** 9 + 7)
```

### **Java**

```java
class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nextLesser = new int[n];
        Arrays.fill(nextLesser, n);
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                nextLesser[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack = new ArrayDeque<>();
        int[] prevLesser = new int[n];
        Arrays.fill(prevLesser, -1);
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                prevLesser[stack.pop()] = i;
            }
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int start = prevLesser[i], end = nextLesser[i];
            long t = nums[i] * (preSum[end] - preSum[start + 1]);
            res = Math.max(res, t);
        }
        return (int) (res % 1000000007);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
