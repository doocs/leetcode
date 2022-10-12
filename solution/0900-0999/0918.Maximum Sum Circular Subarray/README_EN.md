# [918. Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray)

[中文文档](/solution/0900-0999/0918.Maximum%20Sum%20Circular%20Subarray/README.md)

## Description

<p>Given a <strong>circular integer array</strong> <code>nums</code> of length <code>n</code>, return <em>the maximum possible sum of a non-empty <strong>subarray</strong> of </em><code>nums</code>.</p>

<p>A <strong>circular array</strong> means the end of the array connects to the beginning of the array. Formally, the next element of <code>nums[i]</code> is <code>nums[(i + 1) % n]</code> and the previous element of <code>nums[i]</code> is <code>nums[(i - 1 + n) % n]</code>.</p>

<p>A <strong>subarray</strong> may only include each element of the fixed buffer <code>nums</code> at most once. Formally, for a subarray <code>nums[i], nums[i + 1], ..., nums[j]</code>, there does not exist <code>i &lt;= k1</code>, <code>k2 &lt;= j</code> with <code>k1 % n == k2 % n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-2,3,-2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Subarray [3] has maximum sum 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,-3,5]
<strong>Output:</strong> 10
<strong>Explanation:</strong> Subarray [5,5] has maximum sum 5 + 5 = 10.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,-2,-3]
<strong>Output:</strong> -2
<strong>Explanation:</strong> Subarray [-2] has maximum sum -2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        s1 = s2 = f1 = f2 = nums[0]
        for num in nums[1:]:
            f1 = num + max(f1, 0)
            f2 = num + min(f2, 0)
            s1 = max(s1, f1)
            s2 = min(s2, f2)
        return s1 if s1 <= 0 else max(s1, sum(nums) - s2)
```

### **Java**

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            total += nums[i];
            f1 = nums[i] + Math.max(f1, 0);
            f2 = nums[i] + Math.min(f2, 0);
            s1 = Math.max(s1, f1);
            s2 = Math.min(s2, f2);
        }
        return s1 > 0 ? Math.max(s1, total - s2) : s1;
    }
}
```

### **TypeScript**

```ts
function maxSubarraySumCircular(nums: number[]): number {
    let pre1 = nums[0],
        pre2 = nums[0];
    let ans1 = nums[0],
        ans2 = nums[0];
    let sum = nums[0];

    for (let i = 1; i < nums.length; ++i) {
        let cur = nums[i];
        sum += cur;
        pre1 = Math.max(pre1 + cur, cur);
        ans1 = Math.max(pre1, ans1);

        pre2 = Math.min(pre2 + cur, cur);
        ans2 = Math.min(pre2, ans2);
    }
    return ans1 > 0 ? Math.max(ans1, sum - ans2) : ans1;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        int s1 = nums[0], s2 = nums[0], f1 = nums[0], f2 = nums[0], total = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            total += nums[i];
            f1 = nums[i] + max(f1, 0);
            f2 = nums[i] + min(f2, 0);
            s1 = max(s1, f1);
            s2 = min(s2, f2);
        }
        return s1 > 0 ? max(s1, total - s2) : s1;
    }
};
```

### **Go**

```go
func maxSubarraySumCircular(nums []int) int {
	s1, s2, f1, f2, total := nums[0], nums[0], nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		total += nums[i]
		f1 = nums[i] + max(f1, 0)
		f2 = nums[i] + min(f2, 0)
		s1 = max(s1, f1)
		s2 = min(s2, f2)
	}
	if s1 <= 0 {
		return s1
	}
	return max(s1, total-s2)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
