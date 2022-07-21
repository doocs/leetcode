# [面试题 42. 连续子数组的最大和](https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)

## 题目描述

<p>输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。</p>

<p>要求时间复杂度为O(n)。</p>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre><strong>输入:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出:</strong> 6
<strong>解释:</strong>&nbsp;连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;arr.length &lt;= 10^5</code></li>
	<li><code>-100 &lt;= arr[i] &lt;= 100</code></li>
</ul>

<p>注意：本题与主站 53 题相同：<a href="https://leetcode.cn/problems/maximum-subarray/">https://leetcode.cn/problems/maximum-subarray/</a></p>

<p>&nbsp;</p>

## 解法

设 dp[i] 表示 `[0..i]` 中，以 `nums[i]` 结尾的最大子数组和，状态转移方程 `dp[i] = nums[i] + max(dp[i - 1], 0)`。

由于 `dp[i]` 只与子问题 `dp[i-1]` 有关，故可以用一个变量 f 来表示。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        res = f = nums[0]
        for i in range(1, n):
            f = nums[i] + max(f, 0)
            res = max(res, f)
        return res
```

### **Java**

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0], f = nums[0];
        for (int i = 1, n = nums.length; i < n; ++i) {
            f = nums[i] + Math.max(f, 0);
            res = Math.max(res, f);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let res = nums[0];
    let f = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        f = Math.max(f, 0) + nums[i];
        res = Math.max(res, f);
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int res = nums[0], f = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = max(f, 0) + nums[i];
            res = max(res, f);
        }
        return res;
    }
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

### **TypeScript**

```ts
function maxSubArray(nums: number[]): number {
    let res = nums[0];
    for (let i = 1; i < nums.length; i++) {
        nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
        res = Math.max(res, nums[i]);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_sub_array(mut nums: Vec<i32>) -> i32 {
        let mut res = nums[0];
        for i in 1..nums.len() {
            nums[i] = nums[i].max(nums[i - 1] + nums[i]);
            res = res.max(nums[i]);
        }
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public int MaxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        foreach (int x in nums) {
            pre = Math.Max(pre + x, x);
            maxAns = Math.Max(maxAns, pre);
        }
        return maxAns;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
