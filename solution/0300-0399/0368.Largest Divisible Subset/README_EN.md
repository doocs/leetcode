# [368. Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset)

[中文文档](/solution/0300-0399/0368.Largest%20Divisible%20Subset/README.md)

## Description

<p>Given a set of <strong>distinct</strong> positive integers <code>nums</code>, return the largest subset <code>answer</code> such that every pair <code>(answer[i], answer[j])</code> of elements in this subset satisfies:</p>

<ul>
	<li><code>answer[i] % answer[j] == 0</code>, or</li>
	<li><code>answer[j] % answer[i] == 0</code></li>
</ul>

<p>If there are multiple solutions, return any of them.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> [1,3] is also accepted.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,8]
<strong>Output:</strong> [1,2,4,8]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>9</sup></code></li>
	<li>All the integers in <code>nums</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        f, p = [0] * n, [0] * n
        for i in range(n):
            l, pre = 1, i
            for j in range(n):
                if nums[i] % nums[j] == 0 and f[j] + 1 > l:
                    l = f[j] + 1
                    pre = j
            f[i] = l
            p[i] = pre
        max_len, max_index = 0, 0
        for i, v in enumerate(f):
            if max_len < v:
                max_len = v
                max_index = i
        ans = []
        while len(ans) < max_len:
            ans.append(nums[max_index])
            max_index = p[max_index]
        return ans[::-1]
```

### **Java**

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n], p = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 1, pre = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] + 1 > l) {
                    l = f[j] + 1;
                    pre = j;
                }
            }
            f[i] = l;
            p[i] = pre;
        }
        int maxLen = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > maxLen) {
                maxLen = f[i];
                maxIndex = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < maxLen) {
            ans.add(nums[maxIndex]);
            maxIndex = p[maxIndex];
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
