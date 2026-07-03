---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3974.Maximum%20Total%20Sum%20of%20K%20Selected%20Elements/README.md
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3974. K 个元素的最大总和](https://leetcode.cn/problems/maximum-total-sum-of-k-selected-elements)

[English Version](/solution/3900-3999/3974.Maximum%20Total%20Sum%20of%20K%20Selected%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，以及两个整数 <code>k</code> 和 <code>mul</code>。</p>

<p>从 <code>nums</code> 中选出&nbsp;<strong>恰好</strong>&nbsp;<code>k</code> 个元素。你可以按照任意顺序逐个处理这些元素。</p>

<p>对于每个被选择的元素，都可以<strong>&nbsp;独立地</strong>&nbsp;选择以下两种操作之一：</p>

<ul>
	<li>将该元素的值&nbsp;<strong>加&nbsp;</strong>到总和中；或</li>
	<li>将该元素乘以 <code>mul</code> 的<strong>&nbsp;当前</strong>&nbsp;值，并将结果<strong>&nbsp;加</strong>&nbsp;到总和中。</li>
</ul>

<p>每处理一个被选择的元素后，无论选择哪种操作，<code>mul</code> 都会&nbsp;<strong>减少</strong> 1。<code>mul</code> 的当前值可能变为 0 或负数。</p>

<p>返回一个整数，表示可能得到的&nbsp;<strong>最大&nbsp;</strong>总和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,1,2,9], k = 3, mul = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">26</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方式如下：</p>

<ul>
	<li>一种最优选择是 <code>nums[3] = 9</code>、<code>nums[0] = 6</code> 和 <code>nums[2] = 2</code>。</li>
	<li>先处理 <code>nums[3] = 9</code>：选择乘法，因此贡献 <code>9 * 2 = 18</code>。此时，<code>mul</code> 变为 1。</li>
	<li>接着处理 <code>nums[0] = 6</code>：选择乘法，因此贡献 <code>6 * 1 = 6</code>。此时，<code>mul</code> 变为 0。</li>
	<li>最后处理 <code>nums[2] = 2</code>：选择直接相加，因此贡献 2。</li>
	<li>总和为 <code>18 + 6 + 2 = 26</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,7,5,2], k = 2, mul = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">43</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方式如下：</p>

<ul>
	<li>一种最优选择是 <code>nums[1] = 7</code> 和 <code>nums[2] = 5</code>。</li>
	<li>先处理 <code>nums[1] = 7</code>：选择乘法，因此贡献 <code>7 * 4 = 28</code>。此时，<code>mul</code> 变为 3。</li>
	<li>接着处理 <code>nums[2] = 5</code>：选择乘法，因此贡献 <code>5 * 3 = 15</code>。</li>
	<li>总和为 <code>28 + 15 = 43</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,4], k = 1, mul = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方式如下：</p>

<ul>
	<li>一种最优选择是 <code>nums[0] = 4</code>。</li>
	<li>处理 <code>nums[0] = 4</code>：选择乘法，因此贡献 <code>4 * 1 = 4</code>。</li>
	<li>总和为 4。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= mul &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

我们不妨对数组 $\textit{nums}$ 排序，然后从大到小依次选择 $k$ 个元素。对于当前第 $i$ 个元素，我们可以选择将其乘以 $\max(1, \textit{mul})$ 并加到总和中，然后 $\textit{mul}$ 减 $1$。最后返回总和即可。

时间复杂度 $O(n \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: list[int], k: int, mul: int) -> int:
        nums.sort()
        n = len(nums)
        ans = 0
        for i in range(n - 1, n - 1 - k, -1):
            ans += nums[i] * max(1, mul)
            mul -= 1
        return ans
```

#### Java

```java
class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;

        for (int i = n - 1; i >= n - k; i--) {
            int m = Math.max(1, mul);
            ans += (long) nums[i] * m;
            mul--;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSum(vector<int>& nums, int k, int mul) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long ans = 0;

        for (int i = n - 1; i >= n - k; --i) {
            int m = max(1, mul);
            ans += 1LL * nums[i] * m;
            mul--;
        }

        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int, k int, mul int) int64 {
    sort.Ints(nums)
    n := len(nums)
    var ans int64 = 0

    for i := n - 1; i >= n-k; i-- {
        m := max(1, mul)
        ans += int64(nums[i]) * int64(m)
        mul--
    }

    return ans
}
```

#### TypeScript

```ts
function maxSum(nums: number[], k: number, mul: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;

    for (let i = n - 1; i >= n - k; i--) {
        const m = Math.max(1, mul);
        ans += nums[i] * m;
        mul--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
