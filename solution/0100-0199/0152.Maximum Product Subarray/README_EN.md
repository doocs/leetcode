# [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray)

[中文文档](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README.md)

## Description

<p>Given an integer array <code>nums</code>, find a contiguous non-empty subarray within the array that has the largest product, and return <em>the product</em>.</p>

<p>The test cases are generated so that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> [2,3] has the largest product 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The result cannot be 2, because [-2,-1] is not a subarray.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maxf = minf = res = nums[0]
        for num in nums[1:]:
            m, n = maxf, minf
            maxf = max(num, m * num, n * num)
            minf = min(num, m * num, n * num)
            res = max(res, maxf)
        return res
```

### **Java**

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int m = maxf, n = minf;
            maxf = Math.max(nums[i], Math.max(m * nums[i], n * nums[i]));
            minf = Math.min(nums[i], Math.min(m * nums[i], n * nums[i]));
            res = Math.max(res, maxf);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function maxProduct(nums: number[]): number {
    let n = nums.length;
    let preMax = nums[0],
        preMin = nums[0],
        ans = nums[0];
    for (let i = 1; i < n; ++i) {
        let cur = nums[i];
        let x = preMax,
            y = preMin;
        preMax = Math.max(x * cur, y * cur, cur);
        preMin = Math.min(x * cur, y * cur, cur);
        ans = Math.max(preMax, ans);
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int MaxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.Length; ++i)
        {
            int m = maxf, n = minf;
            maxf = Math.Max(nums[i], Math.Max(nums[i] * m, nums[i] * n));
            minf = Math.Min(nums[i], Math.Min(nums[i] * m, nums[i] * n));
            res = Math.Max(res, maxf);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int m = maxf, n = minf;
            maxf = max(nums[i], max(nums[i] * m, nums[i] * n));
            minf = min(nums[i], min(nums[i] * m, nums[i] * n));
            res = max(res, maxf);
        }
        return res;
    }
};
```

### **Go**

```go
func maxProduct(nums []int) int {
	maxf, minf, res := nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		m, n := maxf, minf
		maxf = max(nums[i], max(nums[i]*m, nums[i]*n))
		minf = min(nums[i], min(nums[i]*m, nums[i]*n))
		res = max(res, maxf)
	}
	return res
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

### **Rust**

```rust
impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut min = nums[0];
        let mut max = nums[0];
        let mut res = nums[0];
        for &num in nums.iter().skip(1) {
            let (pre_min, pre_max) = (min, max);
            min = num.min(num * pre_min).min(num * pre_max);
            max = num.max(num * pre_min).max(num * pre_max);
            res = res.max(max);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
