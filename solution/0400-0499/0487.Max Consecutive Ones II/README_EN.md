# [487. Max Consecutive Ones II](https://leetcode.com/problems/max-consecutive-ones-ii)

[中文文档](/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README.md)

## Description

<p>
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
</p>

<p><b>Example 1:</b><br />
<pre>
<b>Input:</b> [1,0,1,1,0]
<b>Output:</b> 4
<b>Explanation:</b> Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
</pre>
</p>

<p><b>Note:</b>
<ul>
<li>The input array will only contain <code>0</code> and <code>1</code>.</li>
<li>The length of input array is a positive integer and will not exceed 10,000</li>
</ul>
</p>

<p><b>Follow up:</b><br />
What if the input numbers come in one by one as an <b>infinite stream</b>? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        n = len(nums)
        prefix = [0] * n
        suffix = [0] * n
        res = 0
        for i in range(n):
            if i == 0:
                prefix[i] = nums[i]
            else:
                prefix[i] = 0 if nums[i] == 0 else prefix[i - 1] + 1
            res = max(res, prefix[i])

        for i in range(n - 1, -1, -1):
            if i == n - 1:
                suffix[i] = nums[i]
            else:
                suffix[i] = 0 if nums[i] == 0 else suffix[i + 1] + 1

        for i in range(n):
            if nums[i] == 0:
                t = 1
                if i > 0:
                    t += prefix[i - 1]
                if i < n - 1:
                    t += suffix[i + 1]
                res = max(res, t)
        return res
```

### **Java**

- Two Pointers, time complexity: O(n²), memory complexity: O(1)

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 1;
            int j = i;
            while (j < n && (cnt > 0 || nums[j] == 1)) {
                if (nums[j] == 0) --cnt;
                ++j;
            }
            res = Math.max(res, j - i);
        }
        return res;
    }
}
```

- Prefix Array & Suffix Array, time complexity: O(n), memory complexity: O(n)

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0) prefix[0] = nums[0];
            else prefix[i] = nums[i] == 0 ? 0 : prefix[i - 1] + 1;
            res = Math.max(res, prefix[i]);
        }

        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1) suffix[n - 1] = nums[n - 1];
            else suffix[i] = nums[i] == 0 ? 0 : suffix[i + 1] + 1;
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int t = 1;
                if (i > 0) t += prefix[i - 1];
                if (i < n - 1) t += suffix[i + 1];
                res = Math.max(res, t);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
