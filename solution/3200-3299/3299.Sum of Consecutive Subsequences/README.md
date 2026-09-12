---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3299.Sum%20of%20Consecutive%20Subsequences/README.md
tags:
    - 数组
    - 哈希表
    - 动态规划
---

<!-- problem:start -->

# [3299. 连续子序列的和 🔒](https://leetcode.cn/problems/sum-of-consecutive-subsequences)

[English Version](/solution/3200-3299/3299.Sum%20of%20Consecutive%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个长度为&nbsp;<code>n</code>&nbsp;的数组&nbsp;<code>arr</code>&nbsp;符合下面其中一个条件，可以称它 <strong>连续</strong>：</p>

<ul>
	<li>对于所有的&nbsp;<code>1 &lt;= i &lt; n</code>，<code>arr[i] - arr[i - 1] == 1</code>。</li>
	<li>对于所有的&nbsp;<code>1 &lt;= i &lt; n</code>，<code>arr[i] - arr[i - 1] == -1</code>。</li>
</ul>

<p>数组的 <strong>值</strong> 是其元素的和。</p>

<p>例如，<code>[3, 4, 5]</code>&nbsp;是一个值为 12 的连续数组，并且&nbsp;<code>[9, 8]</code>&nbsp;是另一个值为 17 的连续数组。而&nbsp;<code>[3, 4, 3]</code> 和&nbsp;<code>[8, 6]</code>&nbsp;都不连续。</p>

<p>给定一个整数数组&nbsp;<code>nums</code>，返回所有 <strong>连续</strong>&nbsp;非空&nbsp;<span data-keyword="subsequence-array">子序列</span>&nbsp;的 <strong>值</strong>&nbsp;之和。</p>

<p>由于答案可能很大，返回它对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;<strong>取模</strong>&nbsp;的值。</p>

<p><strong>注意</strong>&nbsp;长度为 1 的数组也被认为是连续的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,2]</span></p>

<p><strong>输出：</strong><span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>连续子序列为&nbsp;<code>[1]</code>，<code>[2]</code>，<code>[1, 2]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,4,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>31</span></p>

<p><strong>解释：</strong></p>

<p>连续子序列为：<code>[1]</code>，<code>[4]</code>，<code>[2]</code>，<code>[3]</code>，<code>[1, 2]</code>，<code>[2, 3]</code>，<code>[4, 3]</code>，<code>[1, 2, 3]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举贡献

<!-- thinking:start -->

> **思考**
>
> 连续子序列指相邻元素差为 $1$ 或 $-1$ 的子序列（不必下标连续），长度至少 $2$ 的贡献再加全部单点。$n\le 10^5$，枚举子序列不可行。一个元素的贡献等于它出现在多少条递增/递减链中。
>
> 对递增：左侧以 $x-1$ 结尾的链数与右侧以 $x+1$ 开头的链数相乘再加两侧单独延伸。哈希表从左、从右各扫一次得到 $left,right$，贡献 $(l+r+lr)\times x$。递减把数组反转后再算一遍，最后加上全数组之和。

<!-- thinking:end -->

我们不妨统计每个元素 $\textit{nums}[i]$ 在多少个长度大于 $1$ 的连续子序列中出现，那么其个数乘以 $\textit{nums}[i]$ 就是 $\textit{nums}[i]$ 在所有长度大于 $1$ 的连续子序列中的贡献。我们将其累加，再加上所有元素的和，即为答案。

我们可以先统计连续递增子序列对答案的贡献，再统计连续递减子序列对答案的贡献，最后再加上所有元素的和即可。

在实现上，我们定义一个函数 $\textit{calc}(\textit{nums})$，其中 $\textit{nums}$ 是一个数组，返回 $\textit{nums}$ 所有长度大于 $1$ 的连续子序列的和。

在函数中，我们可以使用两个数组 $\textit{left}$ 和 $\textit{right}$ 分别记录每个元素 $\textit{nums}[i]$ 的左侧以 $\textit{nums}[i] - 1$ 结尾的连续递增子序列的个数，以及右侧以 $\textit{nums}[i] + 1$ 开头的连续递增子序列的个数。这样，我们就可以在 $O(n)$ 的时间复杂度内计算出 $\textit{nums}$ 在所有长度大于 $1$ 的连续子序列中的贡献。

在主函数中，我们首先调用 $\textit{calc}(\textit{nums})$ 计算出连续递增子序列对答案的贡献，然后将 $\textit{nums}$ 反转后再次调用 $\textit{calc}(\textit{nums})$ 计算出连续递减子序列对答案的贡献，最后再加上所有元素的和即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSum(self, nums: List[int]) -> int:
        def calc(nums: List[int]) -> int:
            n = len(nums)
            left = [0] * n
            right = [0] * n
            cnt = Counter()
            for i in range(1, n):
                cnt[nums[i - 1]] += 1 + cnt[nums[i - 1] - 1]
                left[i] = cnt[nums[i] - 1]
            cnt = Counter()
            for i in range(n - 2, -1, -1):
                cnt[nums[i + 1]] += 1 + cnt[nums[i + 1] + 1]
                right[i] = cnt[nums[i] + 1]
            return sum((l + r + l * r) * x for l, r, x in zip(left, right, nums)) % mod

        mod = 10**9 + 7
        x = calc(nums)
        nums.reverse()
        y = calc(nums)
        return (x + y + sum(nums)) % mod
```

#### Java

```java
class Solution {
    private final int mod = (int) 1e9 + 7;

    public int getSum(int[] nums) {
        long x = calc(nums);
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        long y = calc(nums);
        long s = Arrays.stream(nums).asLongStream().sum();
        return (int) ((x + y + s) % mod);
    }

    private long calc(int[] nums) {
        int n = nums.length;
        long[] left = new long[n];
        long[] right = new long[n];
        Map<Integer, Long> cnt = new HashMap<>();
        for (int i = 1; i < n; ++i) {
            cnt.merge(nums[i - 1], 1 + cnt.getOrDefault(nums[i - 1] - 1, 0L), Long::sum);
            left[i] = cnt.getOrDefault(nums[i] - 1, 0L);
        }
        cnt.clear();
        for (int i = n - 2; i >= 0; --i) {
            cnt.merge(nums[i + 1], 1 + cnt.getOrDefault(nums[i + 1] + 1, 0L), Long::sum);
            right[i] = cnt.getOrDefault(nums[i] + 1, 0L);
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = (ans + (left[i] + right[i] + left[i] * right[i] % mod) * nums[i] % mod) % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getSum(vector<int>& nums) {
        using ll = long long;
        const int mod = 1e9 + 7;
        auto calc = [&](const vector<int>& nums) -> ll {
            int n = nums.size();
            vector<ll> left(n), right(n);
            unordered_map<int, ll> cnt;

            for (int i = 1; i < n; ++i) {
                cnt[nums[i - 1]] += 1 + cnt[nums[i - 1] - 1];
                left[i] = cnt[nums[i] - 1];
            }

            cnt.clear();

            for (int i = n - 2; i >= 0; --i) {
                cnt[nums[i + 1]] += 1 + cnt[nums[i + 1] + 1];
                right[i] = cnt[nums[i] + 1];
            }

            ll ans = 0;
            for (int i = 0; i < n; ++i) {
                ans = (ans + (left[i] + right[i] + left[i] * right[i] % mod) * nums[i] % mod) % mod;
            }
            return ans;
        };

        ll x = calc(nums);
        reverse(nums.begin(), nums.end());
        ll y = calc(nums);
        ll s = accumulate(nums.begin(), nums.end(), 0LL);
        return static_cast<int>((x + y + s) % mod);
    }
};
```

#### Go

```go
func getSum(nums []int) int {
	const mod = 1e9 + 7

	calc := func(nums []int) int64 {
		n := len(nums)
		left := make([]int64, n)
		right := make([]int64, n)
		cnt := make(map[int]int64)

		for i := 1; i < n; i++ {
			cnt[nums[i-1]] += 1 + cnt[nums[i-1]-1]
			left[i] = cnt[nums[i]-1]
		}

		cnt = make(map[int]int64)

		for i := n - 2; i >= 0; i-- {
			cnt[nums[i+1]] += 1 + cnt[nums[i+1]+1]
			right[i] = cnt[nums[i]+1]
		}

		var ans int64
		for i, x := range nums {
			ans = (ans + (left[i]+right[i]+(left[i]*right[i]%mod))*int64(x)%mod) % mod
		}
		return ans
	}

	x := calc(nums)
	for i, j := 0, len(nums)-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	y := calc(nums)
	s := int64(0)
	for _, num := range nums {
		s += int64(num)
	}
	return int((x + y + s) % mod)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
