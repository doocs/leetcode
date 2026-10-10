---
comments: true
difficulty: 中等
rating: 2011
source: 第 82 场双周赛 Q3
tags:
    - 贪心
    - 数组
    - 二分查找
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2333. 最小差值平方和](https://leetcode.cn/problems/minimum-sum-of-squared-difference)

[English Version](/solution/2300-2399/2333.Minimum%20Sum%20of%20Squared%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，长度为&nbsp;<code>n</code>&nbsp;。</p>

<p>数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;的 <strong>差值平方和</strong>&nbsp;定义为所有满足&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;的&nbsp;<code>(nums1[i] - nums2[i])<sup>2</sup></code>&nbsp;之和。</p>

<p>同时给你两个正整数&nbsp;<code>k1</code> 和&nbsp;<code>k2</code>&nbsp;。你可以将&nbsp;<code>nums1</code>&nbsp;中的任意元素&nbsp;<code>+1</code> 或者&nbsp;<code>-1</code>&nbsp;至多&nbsp;<code>k1</code>&nbsp;次。类似的，你可以将&nbsp;<code>nums2</code>&nbsp;中的任意元素&nbsp;<code>+1</code> 或者&nbsp;<code>-1</code>&nbsp;至多&nbsp;<code>k2</code>&nbsp;次。</p>

<p>请你返回修改数组<em>&nbsp;</em><code>nums1</code><em>&nbsp;</em>至多<em>&nbsp;</em><code>k1</code>&nbsp;次且修改数组<em>&nbsp;</em><code>nums2</code>&nbsp;至多 <code>k2</code><em>&nbsp;</em>次后的最小&nbsp;<strong>差值平方和</strong>&nbsp;。</p>

<p><strong>注意：</strong>你可以将数组中的元素变成&nbsp;<strong>负</strong>&nbsp;整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
<b>输出：</b>579
<b>解释：</b>nums1 和 nums2 中的元素不能修改，因为 k1 = 0 和 k2 = 0 。
差值平方和为：(1 - 2)<sup>2 </sup>+ (2 - 10)<sup>2 </sup>+ (3 - 20)<sup>2 </sup>+ (4 - 19)<sup>2</sup>&nbsp;= 579 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
<b>输出：</b>43
<b>解释：</b>一种得到最小差值平方和的方式为：
- 将 nums1[0] 增加一次。
- 将 nums2[2] 增加一次。
最小差值平方和为：
(2 - 5)<sup>2 </sup>+ (4 - 8)<sup>2 </sup>+ (10 - 7)<sup>2 </sup>+ (12 - 9)<sup>2</sup>&nbsp;= 43 。
注意，也有其他方式可以得到最小差值平方和，但没有得到比 43 更小答案的方案。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k1, k2 &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 计数

<!-- thinking:start -->

> **思考**
>
> 对任一位置做一次 $\pm 1$，无论改的是 $nums1$ 还是 $nums2$，都只是把该位置的绝对差值减一，因此 $k_1$ 与 $k_2$ 可以合成总预算 $k$。 $n \le 10^5$，预算最高到 $2 \times 10^9$，按次模拟或用堆维护当前最大值都会超时。
>
> 平方严格凸，同一次操作减在更大的差值上，平方和下降更多，所以应当总是削当前最大的差值。差值本身不超过 $10^5$，相等的差值可以整层下移。
>
> 统计每个差值的出现次数后从大到小扫描：预算够覆盖整层，就把这一层全部并入下一层；否则用完剩余预算并停止。所有差值之和不超过 $k$ 时，答案为 $0$。

<!-- thinking:end -->

我们令 $k = k_1 + k_2$，并计算每个位置的差值 $d_i = |nums1_i - nums2_i|$。若这些差值之和不超过 $k$，则可以把它们全部降为 $0$，答案为 $0$。

否则用数组 $cnt$ 记录每个差值出现的次数，记最大差值为 $m$。从 $v = m$ 起向下枚举：这一层有 $cnt[v]$ 个数，实际能减一的个数是 $\textit{take} = \min(cnt[v], k)$。把这 $\textit{take}$ 个数挪到 $v - 1$，并令 $k$ 减去 $\textit{take}$。 $k$ 用尽后即可停止。

答案等于 $\sum_v v^2 \cdot cnt[v]$。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 是数组长度， $M$ 是差值的最大值，本题中 $M \le 10^5$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSumSquareDiff(
        self, nums1: List[int], nums2: List[int], k1: int, k2: int
    ) -> int:
        k = k1 + k2
        s = mx = 0
        cnt = [0] * 100001
        for a, b in zip(nums1, nums2):
            v = abs(a - b)
            cnt[v] += 1
            s += v
            mx = max(mx, v)
        if s <= k:
            return 0
        for v in range(mx, 0, -1):
            if cnt[v] == 0:
                continue
            take = min(cnt[v], k)
            k -= take
            cnt[v] -= take
            cnt[v - 1] += take
            if k == 0:
                break
        return sum(v * v * cnt[v] for v in range(mx + 1))
```

#### Java

```java
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int k = k1 + k2;
        long s = 0;
        int mx = 0;
        int[] cnt = new int[100001];
        for (int i = 0; i < nums1.length; ++i) {
            int v = Math.abs(nums1[i] - nums2[i]);
            ++cnt[v];
            s += v;
            mx = Math.max(mx, v);
        }
        if (s <= k) {
            return 0;
        }
        for (int v = mx; v > 0 && k > 0; --v) {
            if (cnt[v] == 0) {
                continue;
            }
            int take = Math.min(cnt[v], k);
            k -= take;
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        long ans = 0;
        for (int v = 0; v <= mx; ++v) {
            ans += (long) v * v * cnt[v];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2, int k1, int k2) {
        int k = k1 + k2;
        long long s = 0;
        int mx = 0;
        vector<int> cnt(100001);
        for (int i = 0; i < nums1.size(); ++i) {
            int v = abs(nums1[i] - nums2[i]);
            ++cnt[v];
            s += v;
            mx = max(mx, v);
        }
        if (s <= k) {
            return 0;
        }
        for (int v = mx; v > 0 && k > 0; --v) {
            if (cnt[v] == 0) {
                continue;
            }
            int take = min(cnt[v], k);
            k -= take;
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        long long ans = 0;
        for (int v = 0; v <= mx; ++v) {
            ans += 1LL * v * v * cnt[v];
        }
        return ans;
    }
};
```

#### Go

```go
func minSumSquareDiff(nums1 []int, nums2 []int, k1 int, k2 int) int64 {
	k := k1 + k2
	var s int64
	mx := 0
	cnt := make([]int, 100001)
	for i, a := range nums1 {
		v := abs(a - nums2[i])
		cnt[v]++
		s += int64(v)
		mx = max(mx, v)
	}
	if s <= int64(k) {
		return 0
	}
	for v := mx; v > 0 && k > 0; v-- {
		if cnt[v] == 0 {
			continue
		}
		take := min(cnt[v], k)
		k -= take
		cnt[v] -= take
		cnt[v-1] += take
	}
	var ans int64
	for v := 0; v <= mx; v++ {
		ans += int64(v) * int64(v) * int64(cnt[v])
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
