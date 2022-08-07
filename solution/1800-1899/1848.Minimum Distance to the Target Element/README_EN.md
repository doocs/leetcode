# [1848. Minimum Distance to the Target Element](https://leetcode.com/problems/minimum-distance-to-the-target-element)

[中文文档](/solution/1800-1899/1848.Minimum%20Distance%20to%20the%20Target%20Element/README.md)

## Description

<p>Given an integer array <code>nums</code> <strong>(0-indexed)</strong> and two integers <code>target</code> and <code>start</code>, find an index <code>i</code> such that <code>nums[i] == target</code> and <code>abs(i - start)</code> is <strong>minimized</strong>. Note that&nbsp;<code>abs(x)</code>&nbsp;is the absolute value of <code>x</code>.</p>

<p>Return <code>abs(i - start)</code>.</p>

<p>It is <strong>guaranteed</strong> that <code>target</code> exists in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], target = 5, start = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> nums[4] = 5 is the only value equal to target, so the answer is abs(4 - 3) = 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], target = 1, start = 0
<strong>Output:</strong> 0
<strong>Explanation:</strong> nums[0] = 1 is the only value equal to target, so the answer is abs(0 - 0) = 0.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
<strong>Output:</strong> 0
<strong>Explanation:</strong> Every value of nums is 1, but nums[0] minimizes abs(i - start), which is abs(0 - 0) = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= start &lt; nums.length</code></li>
	<li><code>target</code> is in <code>nums</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMinDistance(self, nums: List[int], target: int, start: int) -> int:
        res = inf
        for i, num in enumerate(nums):
            if num == target:
                res = min(res, abs(i - start))
        return res
```

### **Java**

```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                res = Math.min(res, Math.abs(i - start));
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getMinDistance(vector<int>& nums, int target, int start) {
        int res = nums.size();
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == target) {
                res = min(res, abs(i - start));
            }
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
