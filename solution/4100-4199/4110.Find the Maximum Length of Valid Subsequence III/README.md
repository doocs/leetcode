---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4110. 找出有效子序列的最大长度 III 🔒](https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-iii)

[English Version](/solution/4100-4199/4110.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code> 和一个<strong>正</strong>整数 <code>k</code>。</p>

<p>如果 <code>nums</code> 的一个&nbsp;<strong><span data-keyword="subsequence-array">子序列&nbsp;</span></strong>中，每对相邻元素的和都能被 <code>k</code> 整除，则称该子序列是&nbsp;<strong>有效的</strong>。</p>

<p>长度为 1 的子序列始终是有效的。</p>

<p>返回 <code>nums</code> 的&nbsp;<strong>最长有效子序列&nbsp;</strong>的长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择子序列 <code>[1, 2, 4, 5]</code>。</li>
	<li>相邻元素的和分别为 <code>1 + 2 = 3</code>、<code>2 + 4 = 6</code> 和 <code>4 + 5 = 9</code>，都能被 3 整除。</li>
	<li>不存在长度超过 4 的有效子序列。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,10,15], k = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择整个数组 <code>[5, 10, 15]</code>。</li>
	<li>相邻元素的和分别为 <code>5 + 10 = 15</code> 和 <code>10 + 15 = 25</code>，都能被 5 整除。</li>
	<li>每个元素都是 5 的倍数，因此任意两个元素都可以在有效子序列中相邻，答案为 3。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3,5,7,9], k = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择整个数组 <code>[1, 3, 5, 7, 9]</code>。</li>
	<li>相邻元素的和分别为 <code>1 + 3 = 4</code>、<code>3 + 5 = 8</code>、<code>5 + 7 = 12</code> 和 <code>7 + 9 = 16</code>，都能被 4 整除。</li>
	<li>各元素除以 4 的余数在 1 和 3 之间交替出现，而它们的和为 4，因此答案为 5。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 对每个位置向前枚举所有可以与它相邻的元素，比较次数是 $O(n^2)$。这里 $n$ 达到 $10^5$，这一枚举无法通过。
>
> 相邻两数之和能被 $k$ 整除，当且仅当它们对 $k$ 的余数 $x$ 与 $y$ 满足 $y=(k-x)\bmod k$。前驱余数由当前余数唯一确定，有效子序列的余数只能在 $x$ 与 $y$ 之间交替。当 $x=y$ 时，子序列里每个元素的余数都相同。
>
> 同一余数的各个位置里，只需留下最靠右的一个。更靠后的元素只能接到互补余数当前最右的下标上，这条链的长度不会短于任何更早的同余数位置。
>
> 因此我们从左到右扫描数组，用哈希表 $\textit{last}$ 记录每个余数最后出现的下标，并用 $f[i]$ 记录以位置 $i$ 结尾的最长有效子序列长度。遇到余数 $x$ 时，若 $y$ 已经出现，就用 $f[\textit{last}[y]]+1$ 更新 $f[i]$，再把 $\textit{last}[x]$ 改写为 $i$。

<!-- thinking:end -->

我们用 $f[i]$ 表示以 $\textit{nums}[i]$ 结尾的最长有效子序列的长度，初始时每个 $f[i]=1$。哈希表 $\textit{last}$ 记录每个余数最后一次出现的下标。

从左到右扫描数组。对于位置 $i$，令 $x=\textit{nums}[i]\bmod k$， $y=(k-x)\bmod k$。若 $\textit{last}$ 中已有余数 $y$，记其下标为 $j$，则将 $f[i]$ 更新为 $\max(f[i], f[j]+1)$。随后令 $\textit{last}[x]=i$。

答案为所有 $f[i]$ 的最大值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: list[int], k: int) -> int:
        f = [1] * len(nums)
        last = {}
        ans = 1
        for i, x in enumerate(nums):
            x %= k
            y = (k - x) % k
            if y in last:
                j = last[y]
                f[i] = max(f[i], f[j] + 1)
                ans = max(ans, f[i])
            last[x] = i
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        Map<Integer, Integer> last = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            int y = (k - x) % k;
            if (last.containsKey(y)) {
                int j = last.get(y);
                f[i] = Math.max(f[i], f[j] + 1);
                ans = Math.max(ans, f[i]);
            }
            last.put(x, i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> f(n, 1);
        unordered_map<int, int> last;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            int y = (k - x) % k;
            if (last.count(y)) {
                int j = last[y];
                f[i] = max(f[i], f[j] + 1);
                ans = max(ans, f[i]);
            }
            last[x] = i;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = 1
	}
	last := map[int]int{}
	ans := 1
	for i, v := range nums {
		x := v % k
		y := (k - x) % k
		if j, ok := last[y]; ok {
			f[i] = max(f[i], f[j]+1)
			ans = max(ans, f[i])
		}
		last[x] = i
	}
	return ans
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[] = new Array(n).fill(1);
    const last = new Map<number, number>();
    let ans = 1;
    for (let i = 0; i < n; i++) {
        const x = nums[i] % k;
        const y = (k - x) % k;
        if (last.has(y)) {
            const j = last.get(y)!;
            f[i] = Math.max(f[i], f[j] + 1);
            ans = Math.max(ans, f[i]);
        }
        last.set(x, i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
