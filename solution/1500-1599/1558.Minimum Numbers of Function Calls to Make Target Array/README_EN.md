# [1558. Minimum Numbers of Function Calls to Make Target Array](https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array)

[中文文档](/solution/1500-1599/1558.Minimum%20Numbers%20of%20Function%20Calls%20to%20Make%20Target%20Array/README.md)

## Description

<p>You are given an integer array <code>nums</code>. You have an integer array <code>arr</code> of the same length with all values set to <code>0</code> initially. You also have the following <code>modify</code> function:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1558.Minimum%20Numbers%20of%20Function%20Calls%20to%20Make%20Target%20Array/images/sample_2_1887.png" style="width: 573px; height: 294px;" />
<p>You want to use the modify function to convert <code>arr</code> to <code>nums</code> using the minimum number of calls.</p>

<p>Return <em>the minimum number of function calls to make </em><code>nums</code><em> from </em><code>arr</code>.</p>

<p>The test cases are generated so that the answer fits in a <strong>32-bit</strong> signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Increment by 1 (second element): [0, 0] to get [0, 1] (1 operation).
Double all the elements: [0, 1] -&gt; [0, 2] -&gt; [0, 4] (2 operations).
Increment by 1 (both elements)  [0, 4] -&gt; [1, 4] -&gt; <strong>[1, 5]</strong> (2 operations).
Total of operations: 1 + 2 + 2 = 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Increment by 1 (both elements) [0, 0] -&gt; [0, 1] -&gt; [1, 1] (2 operations).
Double all the elements: [1, 1] -&gt; <strong>[2, 2]</strong> (1 operation).
Total of operations: 2 + 1 = 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> (initial)[0,0,0] -&gt; [1,0,0] -&gt; [1,0,1] -&gt; [2,0,2] -&gt; [2,1,2] -&gt; [4,2,4] -&gt; <strong>[4,2,5]</strong>(nums).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return sum(v.bit_count() for v in nums) + max(0, max(nums).bit_length() - 1)
```

### **Java**

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
            ans += Integer.bitCount(v);
        }
        ans += Integer.toBinaryString(mx).length() - 1;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = max(mx, v);
            ans += __builtin_popcount(v);
        }
        if (mx) ans += 31 - __builtin_clz(mx);
        return ans;
    }
};
```

### **Go**

```go
func minOperations(nums []int) int {
	ans, mx := 0, 0
	for _, v := range nums {
		mx = max(mx, v)
		for v > 0 {
			ans += v & 1
			v >>= 1
		}
	}
	if mx > 0 {
		for mx > 0 {
			ans++
			mx >>= 1
		}
		ans--
	}
	return ans
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
