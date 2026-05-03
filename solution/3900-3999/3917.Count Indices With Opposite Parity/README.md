---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3917.Count%20Indices%20With%20Opposite%20Parity/README.md
---

<!-- problem:start -->

# [3917. 统计下标的相反奇偶性得分](https://leetcode.cn/problems/count-indices-with-opposite-parity)

[English Version](/solution/3900-3999/3917.Count%20Indices%20With%20Opposite%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>下标 <code>i</code> 的<strong>&nbsp;分数&nbsp;</strong>定义为满足以下条件的下标 <code>j</code> 的数量：</p>

<ul>
	<li><code>i &lt; j &lt; n</code>，并且</li>
	<li><code>nums[i]</code> 和 <code>nums[j]</code> 的奇偶性不同（一个为偶数，另一个为奇数）。</li>
</ul>

<p>返回一个长度为 <code>n</code> 的整数数组 <code>answer</code>，其中 <code>answer[i]</code> 表示下标 <code>i</code> 的分数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,1,1,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums[0] = 1</code>，为奇数。因此，下标 <code>j = 1</code> 和 <code>j = 3</code> 满足条件，所以下标 0 的分数为 2。</li>
	<li><code>nums[1] = 2</code>，为偶数。因此，下标 <code>j = 2</code> 满足条件，所以下标 1 的分数为 1。</li>
	<li><code>nums[2] = 3</code>，为奇数。因此，下标 <code>j = 3</code> 满足条件，所以下标 2 的分数为 1。</li>
	<li><code>nums[3] = 4</code>，为偶数。因此，没有下标满足条件，所以下标 3 的分数为 0。</li>
</ul>

<p>因此，<code>answer = [2, 1, 1, 0]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0]</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code> 中只有一个元素。因此，下标 0 的分数为 0。</p>
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

我们首先统计数组 $\textit{nums}$ 中偶数和奇数的数量，分别记为 $cnt[0]$ 和 $cnt[1]$。

然后，我们从左到右遍历数组 $\textit{nums}$，对于下标 $i$，我们先将 $cnt[\textit{nums}[i] \bmod 2]$ 减 1，然后将 $cnt[\textit{nums}[i] \bmod 2 \oplus 1]$ 的值赋给 $ans[i]$。

遍历结束后，返回答案数组 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。忽略答案数组的空间复杂度，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOppositeParity(self, nums: list[int]) -> list[int]:
        cnt = [0, 0]
        for x in nums:
            cnt[x & 1] += 1
        ans = [0] * len(nums)
        for i, x in enumerate(nums):
            cnt[x & 1] -= 1
            ans[i] = cnt[x & 1 ^ 1]
        return ans
```

#### Java

```java
class Solution {
    public int[] countOppositeParity(int[] nums) {
        int[] cnt = new int[2];
        for (int x : nums) {
            cnt[x & 1]++;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            cnt[nums[i] & 1]--;
            ans[i] = cnt[nums[i] & 1 ^ 1];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countOppositeParity(vector<int>& nums) {
        int cnt[2] = {0, 0};
        for (int x : nums) {
            cnt[x & 1]++;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            cnt[nums[i] & 1]--;
            ans[i] = cnt[(nums[i] & 1) ^ 1];
        }
        return ans;
    }
};
```

#### Go

```go
func countOppositeParity(nums []int) []int {
    cnt := [2]int{}
    for _, x := range nums {
        cnt[x&1]++
    }
    n := len(nums)
    ans := make([]int, n)
    for i, x := range nums {
        cnt[x&1]--
        ans[i] = cnt[x&1^1]
    }
    return ans
}
```

#### TypeScript

```ts
function countOppositeParity(nums: number[]): number[] {
    const cnt = Array<number>(2).fill(0);
    for (const x of nums) {
        ++cnt[x & 1];
    }
    const n = nums.length;
    const ans = Array<number>(n).fill(0);
    for (let i = 0; i < n; ++i) {
        --cnt[nums[i] & 1];
        ans[i] = cnt[(nums[i] & 1) ^ 1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
