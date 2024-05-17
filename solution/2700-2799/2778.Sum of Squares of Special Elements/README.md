---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2778.Sum%20of%20Squares%20of%20Special%20Elements/README.md
rating: 1151
source: 第 354 场周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [2778. 特殊元素平方和](https://leetcode.cn/problems/sum-of-squares-of-special-elements)

[English Version](/solution/2700-2799/2778.Sum%20of%20Squares%20of%20Special%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>1</strong> 开始、长度为 <code>n</code> 的整数数组 <code>nums</code> 。</p>

<p>对 <code>nums</code> 中的元素 <code>nums[i]</code> 而言，如果 <code>n</code> 能够被 <code>i</code> 整除，即 <code>n % i == 0</code> ，则认为 <code>num[i]</code> 是一个 <strong>特殊元素</strong> 。</p>

<p>返回 <code>nums</code> 中所有 <strong>特殊元素</strong> 的 <strong>平方和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>21
<strong>解释：</strong>nums 中共有 3 个特殊元素：nums[1]，因为 4 被 1 整除；nums[2]，因为 4 被 2 整除；以及 nums[4]，因为 4 被 4 整除。 
因此，nums 中所有特殊元素的平方和等于 nums[1] * nums[1] + nums[2] * nums[2] + nums[4] * nums[4] = 1 * 1 + 2 * 2 + 4 * 4 = 21 。  
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,7,1,19,18,3]
<strong>输出：</strong>63
<strong>解释：</strong>nums 中共有 4 个特殊元素：nums[1]，因为 6 被 1 整除；nums[2] ，因为 6 被 2 整除；nums[3]，因为 6 被 3 整除；以及 nums[6]，因为 6 被 6 整除。 
因此，nums 中所有特殊元素的平方和等于 nums[1] * nums[1] + nums[2] * nums[2] + nums[3] * nums[3] + nums[6] * nums[6] = 2 * 2 + 7 * 7 + 1 * 1 + 3 * 3 = 63 。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举数组中的每个元素，判断其是否为特殊元素，如果是则将其平方加入答案中。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def sumOfSquares(self, nums: List[int]) -> int:
        n = len(nums)
        return sum(x * x for i, x in enumerate(nums, 1) if n % i == 0)
```

```java
class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (n % i == 0) {
                ans += nums[i - 1] * nums[i - 1];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int sumOfSquares(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (n % i == 0) {
                ans += nums[i - 1] * nums[i - 1];
            }
        }
        return ans;
    }
};
```

```go
func sumOfSquares(nums []int) (ans int) {
	n := len(nums)
	for i, x := range nums {
		if n%(i+1) == 0 {
			ans += x * x
		}
	}
	return
}
```

```ts
function sumOfSquares(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (n % (i + 1) === 0) {
            ans += nums[i] * nums[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
