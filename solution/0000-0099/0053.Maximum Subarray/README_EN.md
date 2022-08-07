# [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray)

[中文文档](/solution/0000-0099/0053.Maximum%20Subarray/README.md)

## Description

<p>Given an integer array <code>nums</code>, find the contiguous subarray (containing at least one number) which has the largest sum and return <em>its sum</em>.</p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> [4,-1,2,1] has the largest sum = 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,-1,7,8]
<strong>Output:</strong> 23
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution using the <strong>divide and conquer</strong> approach, which is more subtle.</p>

## Solutions

### 1. Dynamic programming

### 2. Divide and Conquer

<!-- tabs:start -->

### **Python3**

Dynamic programming.

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        res = f = nums[0]
        for num in nums[1:]:
            f = num + max(f, 0)
            res = max(res, f)
        return res
```

Divide and Conquer.

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        def crossMaxSub(nums, left, mid, right):
            lsum = rsum = 0
            lmx = rmx = -inf
            for i in range(mid, left - 1, -1):
                lsum += nums[i]
                lmx = max(lmx, lsum)
            for i in range(mid + 1, right + 1):
                rsum += nums[i]
                rmx = max(rmx, rsum)
            return lmx + rmx

        def maxSub(nums, left, right):
            if left == right:
                return nums[left]
            mid = (left + right) >> 1
            lsum = maxSub(nums, left, mid)
            rsum = maxSub(nums, mid + 1, right)
            csum = crossMaxSub(nums, left, mid, right)
            return max(lsum, rsum, csum)

        left, right = 0, len(nums) - 1
        return maxSub(nums, left, right)
```

### **Java**

Dynamic programming.

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int f = nums[0], res = nums[0];
        for (int i = 1, n = nums.length; i < n; ++i) {
            f = nums[i] + Math.max(f, 0);
            res = Math.max(res, f);
        }
        return res;
    }
}
```

Divide and Conquer.

```java
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSub(nums, 0, nums.length - 1);
    }

    private int maxSub(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        int lsum = maxSub(nums, left, mid);
        int rsum = maxSub(nums, mid + 1, right);
        return Math.max(Math.max(lsum, rsum), crossMaxSub(nums, left, mid, right));
    }

    private int crossMaxSub(int[] nums, int left, int mid, int right) {
        int lsum = 0, rsum = 0;
        int lmx = Integer.MIN_VALUE, rmx = Integer.MIN_VALUE;
        for (int i = mid; i >= left; --i) {
            lsum += nums[i];
            lmx = Math.max(lmx, lsum);
        }
        for (int i = mid + 1; i <= right; ++i) {
            rsum += nums[i];
            rmx = Math.max(rmx, rsum);
        }
        return lmx + rmx;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int f = nums[0], res = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = nums[i] + max(f, 0);
            res = max(res, f);
        }
        return res;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let f = nums[0],
        res = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        f = nums[i] + Math.max(f, 0);
        res = Math.max(res, f);
    }
    return res;
};
```

### **Go**

```go
func maxSubArray(nums []int) int {
    f, res := nums[0], nums[0]
    for i := 1; i < len(nums); i++ {
        if f > 0 {
            f += nums[i]
        } else {
            f = nums[i]
        }
        if f > res {
            res = f
        }
    }
    return res
}
```

### **C#**

```cs
public class Solution {
    public int MaxSubArray(int[] nums) {
        int res = nums[0], f = nums[0];
        for (int i = 1; i < nums.Length; ++i)
        {
            f = nums[i] + Math.Max(f, 0);
            res = Math.Max(res, f);
        }
        return res;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = nums[0];
        let mut sum = nums[0];
        for i in 1..n {
            let num = nums[i];
            sum = num.max(sum + num);
            res = res.max(sum);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
