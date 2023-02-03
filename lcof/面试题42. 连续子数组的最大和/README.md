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

**方法一：动态规划**

我们定义 $f[i]$ 表示以第 $i$ 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：

$$
\max_{0 \leq i \leq n-1} f[i]
$$

那么我们如何求 $f[i]$ 呢？我们可以考虑 $nums[i]$ 单独成为一段还是加入 $f[i-1]$ 对应的那一段，这取决于 $nums[i]$ 和 $f[i-1] + nums[i]$ 哪个大，我们希望获得一个比较大的，于是可以写出这样的状态转移方程：

$$
f[i] = \max(f[i-1] + nums[i], nums[i])
$$

或者可以写成这样：

$$
f[i] = \max(f[i-1], 0) + nums[i]
$$

我们可以不用开一个数组来存储所有的计算结果，而是只用两个变量 $f$ 和 $ans$ 来维护对于每一个位置 $i$ 我们的最大值，这样我们可以省去空间复杂度的开销。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans, f = -inf, 0
        for x in nums:
            f = max(f, 0) + x
            ans = max(ans, f)
        return ans
```

### **Java**

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int f = 0;
        for (int x : nums) {
            f = Math.max(f, 0) + x;
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int ans = INT_MIN;
        int f = 0;
        for (int& x : nums) {
            f = max(f, 0) + x;
            ans = max(ans, f);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSubArray(nums []int) int {
	ans, f := -1000000000, 0
	for _, x := range nums {
		f = max(f, 0) + x
		ans = max(ans, f)
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

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let ans = -1e10;
    let f = 0;
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
};
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
        int ans = -1000000000;
        int f = 0;
        foreach (int x in nums) {
            f = Math.Max(f, 0) + x;
            ans = Math.Max(ans, f);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
