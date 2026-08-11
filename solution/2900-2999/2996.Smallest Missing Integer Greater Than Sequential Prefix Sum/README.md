---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2996.Smallest%20Missing%20Integer%20Greater%20Than%20Sequential%20Prefix%20Sum/README.md
rating: 1405
source: 第 121 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [2996. 大于等于顺序前缀和的最小缺失整数](https://leetcode.cn/problems/smallest-missing-integer-greater-than-sequential-prefix-sum)

[English Version](/solution/2900-2999/2996.Smallest%20Missing%20Integer%20Greater%20Than%20Sequential%20Prefix%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果一个前缀&nbsp;<code>nums[0..i]</code>&nbsp;满足对于&nbsp;<code>1 &lt;= j &lt;= i</code>&nbsp;的所有元素都有&nbsp;<code>nums[j] = nums[j - 1] + 1</code>&nbsp;，那么我们称这个前缀是一个 <strong>顺序前缀</strong> 。特殊情况是，只包含&nbsp;<code>nums[0]</code>&nbsp;的前缀也是一个 <strong>顺序前缀</strong> 。</p>

<p>请你返回 <code>nums</code>&nbsp;中没有出现过的 <strong>最小</strong>&nbsp;整数&nbsp;<code>x</code>&nbsp;，满足&nbsp;<code>x</code>&nbsp;大于等于&nbsp;<strong>最长</strong> 顺序前缀的和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,2,5]
<b>输出：</b>6
<b>解释：</b>nums 的最长顺序前缀是 [1,2,3] ，和为 6 ，6 不在数组中，所以 6 是大于等于最长顺序前缀和的最小整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,1,12,14,13]
<b>输出：</b>15
<b>解释：</b>nums 的最长顺序前缀是 [3,4,5] ，和为 12 ，12、13 和 14 都在数组中，但 15 不在，所以 15 是大于等于最长顺序前缀和的最小整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们先求出数组 $nums$ 的最长顺序前缀和 $s$，然后从 $s$ 开始枚举整数 $x$，如果 $x$ 不在数组 $nums$ 中，那么 $x$ 就是答案。

由于题目中 $nums[i] \leq 50$，我们可以用一个长度为 $51$ 的数组（或者哈希表）来记录数组中出现过的整数，从而快速判断一个整数是否在数组 $nums$ 中。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是数组元素的上限，本题中 $M = 51$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        s = nums[0]
        for x, y in pairwise(nums):
            if x + 1 != y:
                break
            s += y
        st = set(nums)
        while s in st:
            s += 1
        return s
```

#### Java

```java
class Solution {
    public int missingInteger(int[] nums) {
        int s = nums[0];
        for (int j = 1; j < nums.length && nums[j] == nums[j - 1] + 1; ++j) {
            s += nums[j];
        }
        final int m = 51;
        boolean[] st = new boolean[m];
        for (int x : nums) {
            st[x] = true;
        }
        while (s < m && st[s]) {
            ++s;
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingInteger(vector<int>& nums) {
        int s = nums[0];
        for (int j = 1; j < nums.size() && nums[j] == nums[j - 1] + 1; ++j) {
            s += nums[j];
        }

        const int m = 51;
        bool st[m] = {};
        for (int x : nums) {
            st[x] = true;
        }

        while (s < m && st[s]) {
            ++s;
        }
        return s;
    }
};
```

#### Go

```go
func missingInteger(nums []int) int {
	s := nums[0]
	for j := 1; j < len(nums) && nums[j] == nums[j-1]+1; j++ {
		s += nums[j]
	}

	const m = 51
	st := make([]bool, m)
	for _, x := range nums {
		st[x] = true
	}

	for s < m && st[s] {
		s++
	}
	return s
}
```

#### TypeScript

```ts
function missingInteger(nums: number[]): number {
    let s = nums[0];
    for (let j = 1; j < nums.length && nums[j] === nums[j - 1] + 1; ++j) {
        s += nums[j];
    }

    const m = 51;
    const st = new Array<boolean>(m).fill(false);
    for (const x of nums) {
        st[x] = true;
    }

    while (s < m && st[s]) {
        ++s;
    }
    return s;
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_integer(nums: Vec<i32>) -> i32 {
        let mut s = nums[0];

        for j in 1..nums.len() {
            if nums[j] != nums[j - 1] + 1 {
                break;
            }
            s += nums[j];
        }

        const M: usize = 51;
        let mut st = [false; M];

        for &x in &nums {
            st[x as usize] = true;
        }

        while s < M as i32 && st[s as usize] {
            s += 1;
        }

        s
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
