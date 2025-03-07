---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3471.Find%20the%20Largest%20Almost%20Missing%20Integer/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3471. 找出最大的几近缺失整数](https://leetcode.cn/problems/find-the-largest-almost-missing-integer)

[English Version](/solution/3400-3499/3471.Find%20the%20Largest%20Almost%20Missing%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code> 。</p>

<p>如果整数 <code>x</code>&nbsp;恰好仅出现在&nbsp;<code>nums</code>&nbsp;中的一个大小为 <code>k</code>&nbsp;的子数组中，则认为&nbsp;<code>x</code>&nbsp;是 <code>nums</code>&nbsp;中的几近缺失（<strong>almost missing</strong>）整数。</p>

<p>返回 <code>nums</code> 中 <strong>最大的几近缺失</strong> 整数，如果不存在这样的整数，返回&nbsp;<code>-1</code>&nbsp;。</p>
<strong>子数组</strong> 是数组中的一个连续元素序列。

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,9,2,1,7], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><b>解释：</b></p>

<ul>
	<li>1 出现在两个大小为 3 的子数组中：<code>[9, 2, 1]</code>、<code>[2, 1, 7]</code></li>
	<li>2 出现在三个大小为&nbsp;3 的子数组中：<code>[3, 9, 2]</code>、<code>[9, 2, 1]</code>、<code>[2, 1, 7]</code></li>
	<li index="2">3 出现在一个大小为 3 的子数组中：<code>[3, 9, 2]</code></li>
	<li index="3">7 出现在一个大小为 3 的子数组中：<code>[2, 1, 7]</code></li>
	<li index="4">9 出现在两个大小为 3 的子数组中：<code>[3, 9, 2]</code>、<code>[9, 2, 1]</code></li>
</ul>

<p>返回 7 ，因为它满足题意的所有整数中最大的那个。</p>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,9,7,2,1,7], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b></p>

<ul>
	<li>1 出现在两个大小为 3 的子数组中：<code>[9, 7, 2, 1]</code>、<code>[7, 2, 1, 7]</code></li>
	<li>2 出现在三个大小为 3 的子数组中：<code>[3, 9, 7, 2]</code>、<code>[9, 7, 2, 1]</code>、<code>[7, 2, 1, 7]</code></li>
	<li>3 出现在一个大小为 3 的子数组中：<code>[3, 9, 7, 2]</code></li>
	<li>7 出现在三个大小为 3 的子数组中：<code>[3, 9, 7, 2]</code>、<code>[9, 7, 2, 1]</code>、<code>[7, 2, 1, 7]</code></li>
	<li>9 出现在两个大小为 3 的子数组中：<code>[3, 9, 7, 2]</code>、<code>[9, 7, 2, 1]</code></li>
</ul>

<p>返回 3&nbsp;，因为它满足题意的所有整数中最大的那个。</p>
</div>

<p><b>示例 3：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [0,0], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>-1</span></p>

<p><b>解释：</b></p>

<p>不存在满足题意的整数。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分情况讨论

如果 $k = 1$，那么数组中每个元素都构成一个大小为 $1$ 的子数组，此时我们只需要统计数组中只出现一次的元素中的最大值即可。

如果 $k = n$，那么整个数组构成一个大小为 $n$ 的子数组，此时我们只需要返回数组中的最大值即可。

如果 $1 < k < n$，只有 $\textit{nums}[0]$ 和 $\textit{nums}[n-1]$ 可能是几近缺失整数，如果它们在数组中的其他位置出现过，那么它们就不是几近缺失整数。因此我们只需要判断 $\textit{nums}[0]$ 和 $\textit{nums}[n-1]$ 是否在数组中的其他位置出现过即可，取其中的最大值返回。

如果不存在几近缺失整数，返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestInteger(self, nums: List[int], k: int) -> int:
        def f(k: int) -> int:
            for i, x in enumerate(nums):
                if i != k and x == nums[k]:
                    return -1
            return nums[k]

        if k == 1:
            cnt = Counter(nums)
            return max((x for x, v in cnt.items() if v == 1), default=-1)
        if k == len(nums):
            return max(nums)
        return max(f(0), f(len(nums) - 1))
```

#### Java

```java
class Solution {
    private int[] nums;

    public int largestInteger(int[] nums, int k) {
        this.nums = nums;
        if (k == 1) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int x : nums) {
                cnt.merge(x, 1, Integer::sum);
            }
            int ans = -1;
            for (var e : cnt.entrySet()) {
                if (e.getValue() == 1) {
                    ans = Math.max(ans, e.getKey());
                }
            }
            return ans;
        }
        if (k == nums.length) {
            return Arrays.stream(nums).max().getAsInt();
        }
        return Math.max(f(0), f(nums.length - 1));
    }

    private int f(int k) {
        for (int i = 0; i < nums.length; ++i) {
            if (i != k && nums[i] == nums[k]) {
                return -1;
            }
        }
        return nums[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        if (k == 1) {
            unordered_map<int, int> cnt;
            for (int x : nums) {
                ++cnt[x];
            }
            int ans = -1;
            for (auto& [x, v] : cnt) {
                if (v == 1) {
                    ans = max(ans, x);
                }
            }
            return ans;
        }
        int n = nums.size();
        if (k == n) {
            return ranges::max(nums);
        }
        auto f = [&](int k) -> int {
            for (int i = 0; i < n; ++i) {
                if (i != k && nums[i] == nums[k]) {
                    return -1;
                }
            }
            return nums[k];
        };
        return max(f(0), f(n - 1));
    }
};
```

#### Go

```go
func largestInteger(nums []int, k int) int {
    if k == 1 {
        cnt := make(map[int]int)
        for _, x := range nums {
            cnt[x]++
        }
        ans := -1
        for x, v := range cnt {
            if v == 1 {
                ans = max(ans, x)
            }
        }
        return ans
    }

    n := len(nums)
    if k == n {
        return slices.Max(nums)
    }

    f := func(k int) int {
        for i, x := range nums {
            if i != k && x == nums[k] {
                return -1
            }
        }
        return nums[k]
    }

    return max(f(0), f(n-1))
}
```

#### TypeScript

```ts
function largestInteger(nums: number[], k: number): number {
    if (k === 1) {
        const cnt = new Map<number, number>();
        for (const x of nums) {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
        let ans = -1;
        for (const [x, v] of cnt.entries()) {
            if (v === 1 && x > ans) {
                ans = x;
            }
        }
        return ans;
    }

    const n = nums.length;
    if (k === n) {
        return Math.max(...nums);
    }

    const f = (k: number): number => {
        for (let i = 0; i < n; i++) {
            if (i !== k && nums[i] === nums[k]) {
                return -1;
            }
        }
        return nums[k];
    };

    return Math.max(f(0), f(n - 1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
