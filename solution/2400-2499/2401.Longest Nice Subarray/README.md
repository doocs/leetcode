---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2401.Longest%20Nice%20Subarray/README.md
rating: 1749
source: 第 309 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [2401. 最长优雅子数组](https://leetcode.cn/problems/longest-nice-subarray)

[English Version](/solution/2400-2499/2401.Longest%20Nice%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <strong>正</strong> 整数组成的数组 <code>nums</code> 。</p>

<p>如果&nbsp;<code>nums</code> 的子数组中位于 <strong>不同</strong> 位置的每对元素按位 <strong>与（AND）</strong>运算的结果等于 <code>0</code> ，则称该子数组为 <strong>优雅</strong> 子数组。</p>

<p>返回 <strong>最长</strong> 的优雅子数组的长度。</p>

<p><strong>子数组</strong> 是数组中的一个 <strong>连续</strong> 部分。</p>

<p><strong>注意：</strong>长度为 <code>1</code> 的子数组始终视作优雅子数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,8,48,10]
<strong>输出：</strong>3
<strong>解释：</strong>最长的优雅子数组是 [3,8,48] 。子数组满足题目条件：
- 3 AND 8 = 0
- 3 AND 48 = 0
- 8 AND 48 = 0
可以证明不存在更长的优雅子数组，所以返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [3,1,5,11,13]
<strong>输出：</strong>1
<strong>解释：</strong>最长的优雅子数组长度为 1 ，任何长度为 1 的子数组都满足题目条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

根据题目描述，子数组的每个元素的二进制位上的 $1$ 的位置不能相同，这样才能保证任意两个元素的按位与结果为 $0$。

因此，我们可以使用双指针 $l$ 和 $r$ 维护一个滑动窗口，使得窗口内的元素满足题目条件。

我们用一个变量 $\textit{mask}$ 来表示窗口内的元素的按位或的结果，接下来，遍历数组的每个元素。对于当前元素 $x$，如果 $\textit{mask}$ 和 $x$ 的按位与结果不为 $0$，说明当前元素 $x$ 与窗口内的元素有重复的二进制位，此时需要移动左指针 $l$，直到 $\textit{mask}$ 和 $x$ 的按位与结果为 $0$。然后，我们将 $\textit{mask}$ 和 $x$ 的按位或的结果赋值给 $\textit{mask}$，并更新答案 $\textit{ans} = \max(\textit{ans}, r - l + 1)$。

遍历结束后，返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        ans = mask = l = 0
        for r, x in enumerate(nums):
            while mask & x:
                mask ^= nums[l]
                l += 1
            mask |= x
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            while ((mask & nums[r]) != 0) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestNiceSubarray(vector<int>& nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.size(); ++r) {
            while (mask & nums[r]) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func longestNiceSubarray(nums []int) (ans int) {
	mask, l := 0, 0
	for r, x := range nums {
		for mask&x != 0 {
			mask ^= nums[l]
			l++
		}
		mask |= x
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function longestNiceSubarray(nums: number[]): number {
    let [ans, mask] = [0, 0];
    for (let l = 0, r = 0; r < nums.length; ++r) {
        while (mask & nums[r]) {
            mask ^= nums[l++];
        }
        mask |= nums[r];
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_nice_subarray(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mask = 0;
        let mut l = 0;
        for (r, &x) in nums.iter().enumerate() {
            while mask & x != 0 {
                mask ^= nums[l];
                l += 1;
            }
            mask |= x;
            ans = ans.max((r - l + 1) as i32);
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int LongestNiceSubarray(int[] nums) {
        int ans = 0, mask = 0;
        for (int l = 0, r = 0; r < nums.Length; ++r) {
            while ((mask & nums[r]) != 0) {
                mask ^= nums[l++];
            }
            mask |= nums[r];
            ans = Math.Max(ans, r - l + 1);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
