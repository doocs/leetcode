---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3866.First%20Unique%20Even%20Element/README.md
---

<!-- problem:start -->

# [3866. 找到第一个唯一偶数](https://leetcode.cn/problems/first-unique-even-element)

[English Version](/solution/3800-3899/3866.First%20Unique%20Even%20Element/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>请你返回一个整数，表示 <code>nums</code> 中出现 <strong>恰好</strong> 一次的第一个 <strong>偶数</strong>（以数组下标最早为准）。如果不存在这样的整数，返回 -1。</p>

<p>如果一个整数 <code>x</code> 能被 2 整除，那么它就被认为是 <strong>偶数</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,4,2,5,4,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>2 和 6 都是偶数，并且它们都恰好出现一次。因为 2 在数组中出现得更早，所以答案是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>没有恰好出现一次的偶数，所以返回 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个哈希表或数组 $\textit{cnt}$ 来统计每个整数在数组中出现的次数。然后我们再遍历一次数组，找到第一个满足条件的偶数并返回它。如果没有满足条件的偶数，我们返回 -1。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(M)$，其中 $M$ 是数组中整数的范围（在本题中为 100）。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstUniqueEven(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        for x in nums:
            if x % 2 == 0 and cnt[x] == 1:
                return x
        return -1
```

#### Java

```java
class Solution {
    public int firstUniqueEven(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (x % 2 == 0 && cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstUniqueEven(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x : nums) {
            if (x % 2 == 0 && cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func firstUniqueEven(nums []int) int {
    cnt := make([]int, 101)
    for _, x := range nums {
        cnt[x]++
    }
    for _, x := range nums {
        if x%2 == 0 && cnt[x] == 1 {
            return x
        }
    }
    return -1
}
```

#### TypeScript

```ts
function firstUniqueEven(nums: number[]): number {
    const cnt: number[] = new Array(101).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    for (const x of nums) {
        if (x % 2 === 0 && cnt[x] === 1) {
            return x;
        }
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
