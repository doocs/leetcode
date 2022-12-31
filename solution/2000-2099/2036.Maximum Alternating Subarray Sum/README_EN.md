# [2036. Maximum Alternating Subarray Sum](https://leetcode.com/problems/maximum-alternating-subarray-sum)

[中文文档](/solution/2000-2099/2036.Maximum%20Alternating%20Subarray%20Sum/README.md)

## Description

<p>A <strong>subarray</strong> of a <strong>0-indexed</strong> integer array is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>The <strong>alternating subarray sum</strong> of a subarray that ranges from index <code>i</code> to <code>j</code> (<strong>inclusive</strong>, <code>0 &lt;= i &lt;= j &lt; nums.length</code>) is <code>nums[i] - nums[i+1] + nums[i+2] - ... +/- nums[j]</code>.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, return <em>the <strong>maximum alternating subarray sum</strong> of any subarray of </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,-1,1,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The subarray [3,-1,1] has the largest alternating subarray sum.
The alternating subarray sum is 3 - (-1) + 1 = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The subarrays [2], [2,2,2], and [2,2,2,2,2] have the largest alternating subarray sum.
The alternating subarray sum of [2] is 2.
The alternating subarray sum of [2,2,2] is 2 - 2 + 2 = 2.
The alternating subarray sum of [2,2,2,2,2] is 2 - 2 + 2 - 2 + 2 = 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
There is only one non-empty subarray, which is [1].
The alternating subarray sum is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumAlternatingSubarraySum(self, nums: List[int]) -> int:
        ans = nums[0]
        a, b = nums[0], -inf
        for v in nums[1:]:
            a, b = max(v, b + v), a - v
            ans = max(ans, a, b)
        return ans
```

### **Java**

```java
class Solution {
    public long maximumAlternatingSubarraySum(int[] nums) {
        long ans = nums[0];
        long a = nums[0], b = -(1 << 30);
        for (int i = 1; i < nums.length; ++i) {
            long c = a, d = b;
            a = Math.max(nums[i], d + nums[i]);
            b = c - nums[i];
            ans = Math.max(ans, Math.max(a, b));
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long maximumAlternatingSubarraySum(vector<int>& nums) {
        ll ans = nums[0];
        ll a = nums[0], b = -(1 << 30);
        for (int i = 1; i < nums.size(); ++i) {
            ll c = a, d = b;
            a = max(1ll * nums[i], d + nums[i]);
            b = c - nums[i];
            ans = max(ans, max(a, b));
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumAlternatingSubarraySum(nums []int) int64 {
	ans := nums[0]
	a, b := nums[0], -(1 << 30)
	for _, v := range nums[1:] {
		c, d := a, b
		a = max(v, d+v)
		b = c - v
		ans = max(ans, max(a, b))
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
