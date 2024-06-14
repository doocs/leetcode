---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2786.Visit%20Array%20Positions%20to%20Maximize%20Score/README.md
rating: 1732
source: 第 109 场双周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2786. 访问数组中的位置使分数最大](https://leetcode.cn/problems/visit-array-positions-to-maximize-score)

[English Version](/solution/2700-2799/2786.Visit%20Array%20Positions%20to%20Maximize%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个正整数&nbsp;<code>x</code>&nbsp;。</p>

<p>你 <strong>一开始</strong>&nbsp;在数组的位置 <code>0</code>&nbsp;处，你可以按照下述规则访问数组中的其他位置：</p>

<ul>
	<li>如果你当前在位置&nbsp;<code>i</code>&nbsp;，那么你可以移动到满足&nbsp;<code>i &lt; j</code>&nbsp;的&nbsp;<strong>任意</strong>&nbsp;位置&nbsp;<code>j</code>&nbsp;。</li>
	<li>对于你访问的位置 <code>i</code>&nbsp;，你可以获得分数&nbsp;<code>nums[i]</code>&nbsp;。</li>
	<li>如果你从位置 <code>i</code>&nbsp;移动到位置 <code>j</code>&nbsp;且&nbsp;<code>nums[i]</code> 和&nbsp;<code>nums[j]</code>&nbsp;的 <strong>奇偶性</strong>&nbsp;不同，那么你将失去分数&nbsp;<code>x</code>&nbsp;。</li>
</ul>

<p>请你返回你能得到的 <strong>最大</strong>&nbsp;得分之和。</p>

<p><strong>注意</strong>&nbsp;，你一开始的分数为&nbsp;<code>nums[0]</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,3,6,1,9,2], x = 5
<b>输出：</b>13
<b>解释：</b>我们可以按顺序访问数组中的位置：0 -&gt; 2 -&gt; 3 -&gt; 4 。
对应位置的值为 2 ，6 ，1 和 9 。因为 6 和 1 的奇偶性不同，所以下标从 2 -&gt; 3 让你失去 x = 5 分。
总得分为：2 + 6 + 1 + 9 - 5 = 13 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,4,6,8], x = 3
<b>输出：</b>20
<b>解释：</b>数组中的所有元素奇偶性都一样，所以我们可以将每个元素都访问一次，而且不会失去任何分数。
总得分为：2 + 4 + 6 + 8 = 20 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], x &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

根据题目描述，我们可以得到以下结论：

1. 从位置 $i$ 移动到位置 $j$ 时，如果 $nums[i]$ 和 $nums[j]$ 的奇偶性不同，那么会损失 $x$ 分；
2. 从位置 $i$ 移动到位置 $j$ 时，如果 $nums[i]$ 和 $nums[j]$ 的奇偶性相同，那么不会损失分数。

因此，我们可以用一个长度为 $2$ 的数组 $f$ 来表示当前位置的奇偶性为 $0$ 和 $1$ 时的最大得分。初始时 $f$ 的值为 $-\infty$，然后我们再初始化 $f[nums[0] \& 1] = nums[0]$，表示初始位置的得分。

接下来，我们从位置 $1$ 开始遍历数组 $nums$，对于每个位置 $i$ 对应的值 $v$，我们更新 $f[v \& 1]$ 的值为 $f[v \& 1]$ 和 $f[v \& 1 \oplus 1] - x$ 的较大值再加上 $v$，即 $f[v \& 1] = \max(f[v \& 1], f[v \& 1 \oplus 1] - x) + v$。

答案为 $f[0]$ 和 $f[1]$ 中的较大值。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int], x: int) -> int:
        f = [-inf] * 2
        f[nums[0] & 1] = nums[0]
        for v in nums[1:]:
            f[v & 1] = max(f[v & 1], f[v & 1 ^ 1] - x) + v
        return max(f)
```

#### Java

```java
class Solution {
    public long maxScore(int[] nums, int x) {
        long[] f = new long[2];
        Arrays.fill(f, -(1L << 60));
        f[nums[0] & 1] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int v = nums[i];
            f[v & 1] = Math.max(f[v & 1], f[v & 1 ^ 1] - x) + v;
        }
        return Math.max(f[0], f[1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& nums, int x) {
        const long long inf = 1LL << 60;
        vector<long long> f(2, -inf);
        f[nums[0] & 1] = nums[0];
        int n = nums.size();
        for (int i = 1; i < n; ++i) {
            int v = nums[i];
            f[v & 1] = max(f[v & 1], f[v & 1 ^ 1] - x) + v;
        }
        return max(f[0], f[1]);
    }
};
```

#### Go

```go
func maxScore(nums []int, x int) int64 {
	const inf int = 1 << 40
	f := [2]int{-inf, -inf}
	f[nums[0]&1] = nums[0]
	for _, v := range nums[1:] {
		f[v&1] = max(f[v&1], f[v&1^1]-x) + v
	}
	return int64(max(f[0], f[1]))
}
```

#### TypeScript

```ts
function maxScore(nums: number[], x: number): number {
    const f: number[] = Array(2).fill(-Infinity);
    f[nums[0] & 1] = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        const v = nums[i];
        f[v & 1] = Math.max(f[v & 1], f[(v & 1) ^ 1] - x) + v;
    }
    return Math.max(...f);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
