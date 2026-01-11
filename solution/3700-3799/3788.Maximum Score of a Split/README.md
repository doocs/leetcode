---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3788.Maximum%20Score%20of%20a%20Split/README.md
rating: 1306
source: 第 482 场周赛 Q1
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [3788. 分割的最大得分](https://leetcode.cn/problems/maximum-score-of-a-split)

[English Version](/solution/3700-3799/3788.Maximum%20Score%20of%20a%20Split/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>请你选出一个下标 <code>i</code>&nbsp;以分割数组，该下标满足 <code>0 &lt;= i &lt; n - 1</code>。</p>

<p>对于选择的分割下标 <code>i</code>：</p>

<ul>
	<li>令 <code>prefixSum(i)</code> 表示数组前缀的和，即 <code>nums[0] + nums[1] + ... + nums[i]</code>。</li>
	<li>令 <code>suffixMin(i)</code> 表示数组后缀的最小值，即 <code>nums[i + 1], nums[i + 2], ..., nums[n - 1]</code> 中的最小值。</li>
</ul>

<p>在下标 <code>i</code> 的<strong>&nbsp;分割得分</strong>&nbsp;定义为：</p>

<p><code>score(i) = prefixSum(i) - suffixMin(i)</code></p>

<p>返回所有有效分割下标中&nbsp;<strong>最大</strong>&nbsp;的分割得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [10,-1,3,-4,-5]</span></p>

<p><strong>输出：</strong> <span class="example-io">17</span></p>

<p><strong>解释：</strong></p>

<p>最优的分割下标是 <code>i = 2</code>，<code>score(2) = prefixSum(2) - suffixMin(2) = (10 + (-1) + 3) - (-5) = 17</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-7,-5,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">-2</span></p>

<p><strong>解释：</strong></p>

<p>最优的分割下标是 <code>i = 0</code>，<code>score(0) = prefixSum(0) - suffixMin(0) = (-7) - (-5) = -2</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>唯一有效分割下标是 <code>i = 0</code>，<code>score(0) = prefixSum(0) - suffixMin(0) = 1 - 1 = 0</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>​​​​​​​ &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 枚举

我们首先定义一个长度为 $n$ 的数组 $\textit{suf}$，其中 $\textit{suf}[i]$ 表示数组 $\textit{nums}$ 从下标 $i$ 到下标 $n - 1$ 的最小值。我们可以从后向前遍历数组 $\textit{nums}$ 来计算数组 $\textit{suf}$。

接下来，我们定义一个变量 $\textit{pre}$ 来表示数组 $\textit{nums}$ 的前缀和。我们遍历数组 $\textit{nums}$ 的前 $n - 1$ 个元素，对于每个下标 $i$，我们将 $\textit{nums}[i]$ 加入到 $\textit{pre}$ 中，并计算分割得分 $\textit{score}(i) = \textit{pre} - \textit{suf}[i + 1]$。我们使用一个变量 $\textit{ans}$ 来维护所有分割得分的最大值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int]) -> int:
        n = len(nums)
        suf = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            suf[i] = min(nums[i], suf[i + 1])
        ans = -inf
        pre = 0
        for i in range(n - 1):
            pre += nums[i]
            ans = max(ans, pre - suf[i + 1])
        return ans
```

#### Java

```java
class Solution {
    public long maximumScore(int[] nums) {
        int n = nums.length;
        long[] suf = new long[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = Math.min(nums[i], suf[i + 1]);
        }
        long ans = Long.MIN_VALUE;
        long pre = 0;
        for (int i = 0; i < n - 1; ++i) {
            pre += nums[i];
            ans = Math.max(ans, pre - suf[i + 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> suf(n);
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = min((long long) nums[i], suf[i + 1]);
        }
        long long ans = LLONG_MIN;
        long long pre = 0;
        for (int i = 0; i < n - 1; ++i) {
            pre += nums[i];
            ans = max(ans, pre - suf[i + 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumScore(nums []int) int64 {
	n := len(nums)
	suf := make([]int64, n)
	suf[n-1] = int64(nums[n-1])
	for i := n - 2; i >= 0; i-- {
		suf[i] = min(int64(nums[i]), suf[i+1])
	}
	var pre int64 = 0
	var ans int64 = math.MinInt64
	for i := 0; i < n-1; i++ {
		pre += int64(nums[i])
		ans = max(ans, pre-suf[i+1])
	}
	return ans
}
```

#### TypeScript

```ts
function maximumScore(nums: number[]): number {
    const n = nums.length;
    const suf: number[] = new Array(n);
    suf[n - 1] = nums[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        suf[i] = Math.min(nums[i], suf[i + 1]);
    }
    let ans = Number.NEGATIVE_INFINITY;
    let pre = 0;
    for (let i = 0; i < n - 1; ++i) {
        pre += nums[i];
        ans = Math.max(ans, pre - suf[i + 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
