---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2874.Maximum%20Value%20of%20an%20Ordered%20Triplet%20II/README.md
rating: 1583
source: 第 365 场周赛 Q2
tags:
    - 数组
---

<!-- problem:start -->

# [2874. 有序三元组中的最大值 II](https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-ii)

[English Version](/solution/2800-2899/2874.Maximum%20Value%20of%20an%20Ordered%20Triplet%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。</p>

<p>请你从所有满足&nbsp;<code>i &lt; j &lt; k</code> 的下标三元组 <code>(i, j, k)</code> 中，找出并返回下标三元组的最大值。如果所有满足条件的三元组的值都是负数，则返回 <code>0</code> 。</p>

<p><strong>下标三元组</strong> <code>(i, j, k)</code> 的值等于 <code>(nums[i] - nums[j]) * nums[k]</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [12,6,1,2,7]
<strong>输出：</strong>77
<strong>解释：</strong>下标三元组 (0, 2, 4) 的值是 (nums[0] - nums[2]) * nums[4] = 77 。
可以证明不存在值大于 77 的有序下标三元组。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,10,3,4,19]
<strong>输出：</strong>133
<strong>解释：</strong>下标三元组 (1, 2, 4) 的值是 (nums[1] - nums[2]) * nums[4] = 133 。
可以证明不存在值大于 133 的有序下标三元组。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>0
<strong>解释：</strong>唯一的下标三元组 (0, 1, 2) 的值是一个负数，(nums[0] - nums[1]) * nums[2] = -3 。因此，答案是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：维护前缀最大值和最大差值

我们用两个变量 $\textit{mx}$ 和 $\textit{mxDiff}$ 分别维护前缀最大值和最大差值，用一个变量 $\textit{ans}$ 维护答案。初始时，这些变量都为 $0$。

接下来，我们枚举数组的每个元素 $x$ 作为 $\textit{nums}[k]$，首先更新答案 $\textit{ans} = \max(\textit{ans}, \textit{mxDiff} \times x)$，然后我们更新最大差值 $\textit{mxDiff} = \max(\textit{mxDiff}, \textit{mx} - x)$，最后更新前缀最大值 $\textit{mx} = \max(\textit{mx}, x)$。

枚举完所有元素后，返回答案 $\textit{ans}$。

时间复杂度 $O(n)$，其中 $n$ 是数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTripletValue(self, nums: List[int]) -> int:
        ans = mx = mx_diff = 0
        for x in nums:
            ans = max(ans, mx_diff * x)
            mx_diff = max(mx_diff, mx - x)
            mx = max(mx, x)
        return ans
```

#### Java

```java
class Solution {
    public long maximumTripletValue(int[] nums) {
        long ans = 0, mxDiff = 0;
        int mx = 0;
        for (int x : nums) {
            ans = Math.max(ans, mxDiff * x);
            mxDiff = Math.max(mxDiff, mx - x);
            mx = Math.max(mx, x);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTripletValue(vector<int>& nums) {
        long long ans = 0, mxDiff = 0;
        int mx = 0;
        for (int x : nums) {
            ans = max(ans, mxDiff * x);
            mxDiff = max(mxDiff, 1LL * mx - x);
            mx = max(mx, x);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumTripletValue(nums []int) int64 {
	ans, mx, mxDiff := 0, 0, 0
	for _, x := range nums {
		ans = max(ans, mxDiff*x)
		mxDiff = max(mxDiff, mx-x)
		mx = max(mx, x)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function maximumTripletValue(nums: number[]): number {
    let [ans, mx, mxDiff] = [0, 0, 0];
    for (const x of nums) {
        ans = Math.max(ans, mxDiff * x);
        mxDiff = Math.max(mxDiff, mx - x);
        mx = Math.max(mx, x);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_triplet_value(nums: Vec<i32>) -> i64 {
        let mut ans: i64 = 0;
        let mut mx: i32 = 0;
        let mut mx_diff: i32 = 0;

        for &x in &nums {
            ans = ans.max(mx_diff as i64 * x as i64);
            mx_diff = mx_diff.max(mx - x);
            mx = mx.max(x);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
