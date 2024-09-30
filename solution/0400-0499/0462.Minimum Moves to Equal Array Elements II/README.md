---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README.md
tags:
    - 数组
    - 数学
    - 排序
---

<!-- problem:start -->

# [462. 最小操作次数使数组元素相等 II](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii)

[English Version](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，返回使所有数组元素相等需要的最小操作数。</p>

<p>在一次操作中，你可以使数组中的一个元素加 <code>1</code> 或者减 <code>1</code> 。</p>

<p>测试用例经过设计以使答案在&nbsp;<strong>32 位</strong>&nbsp;整数范围内。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>
只需要两次操作（每次操作指南使一个元素加 1 或减 1）：
[<u>1</u>,2,3]  =&gt;  [2,2,<u>3</u>]  =&gt;  [2,2,2]
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,10,2,9]
<strong>输出：</strong>16
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 中位数

这个问题可以抽象为，在数轴上有 $n$ 个点，找到一个点使得所有点到该点的距离之和最小。答案为 $n$ 个点的中位数。

中位数有这样的性质：所有数与中位数的距离之和最小。

证明：

首先，给定一个从小到大的数列 $a_1, a_2, \cdots, a_n$，我们假设 $x$ 是从 $a_1$ 到 $a_n$ 与其距离之和最小的点，显然 $x$ 必须位于 $a_1$ 和 $a_n$ 之间。而由于 $a_1$ 与 $a_n$ 与 $x$ 的距离之和都相等，都等于 $a_n-a_1$，因此，接下来不考虑 $a_1$ 和 $a_n$，我们只考虑 $a_2, a_3, \cdots, a_{n-1}$，这样的话，我们就可以把问题转化为在 $a_2, a_3, \cdots, a_{n-1}$ 中找到一个点与其距离之和最小，依此类推，我们最后可以得出结论：在一个数列中，中位数与其余数的距离之和最小。

在这个问题中，我们可以先对数组进行排序，然后找到中位数，最后计算所有数与中位数的距离之和即可。

时间复杂度 $O(n\log n)$，空间复杂度 $O(\log n)$。

相似题目：

-   [296. 最佳的碰头地点](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0296.Best%20Meeting%20Point/README.md)
-   [2448. 使数组相等的最小开销](https://github.com/doocs/leetcode/blob/main/solution/2400-2499/2448.Minimum%20Cost%20to%20Make%20Array%20Equal/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()
        k = nums[len(nums) >> 1]
        return sum(abs(v - k) for v in nums)
```

#### Java

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int k = nums[nums.length >> 1];
        int ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - k);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves2(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int k = nums[nums.size() >> 1];
        int ans = 0;
        for (int& v : nums) {
            ans += abs(v - k);
        }
        return ans;
    }
};
```

#### Go

```go
func minMoves2(nums []int) int {
	sort.Ints(nums)
	k := nums[len(nums)>>1]
	ans := 0
	for _, v := range nums {
		ans += abs(v - k)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minMoves2(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const k = nums[nums.length >> 1];
    return nums.reduce((r, v) => r + Math.abs(v - k), 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_moves2(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let k = nums[nums.len() / 2];
        let mut ans = 0;
        for num in nums.iter() {
            ans += (num - k).abs();
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
