---
comments: true
difficulty: 困难
rating: 2288
source: 第 499 场周赛 Q4
tags:
    - 线段树
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3915. 距离至少为 K 的交替子序列的最大和](https://leetcode.cn/problems/maximum-sum-of-alternating-subsequence-with-distance-at-least-k)

[English Version](/solution/3900-3999/3915.Maximum%20Sum%20of%20Alternating%20Subsequence%20With%20Distance%20at%20Least%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>选择一个下标满足 <code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>m</sub> &lt; n</code> 的<strong>&nbsp;子序列</strong>，并满足：</p>

<ul>
	<li>对于每个 <code>1 &lt;= t &lt; m</code>，都有 <code>i<sub>t+1</sub> - i<sub>t</sub> &gt;= k</code>。</li>
	<li>所选的值构成一个<strong>&nbsp;严格交替&nbsp;</strong>序列。换句话说，满足以下两种形式之一：
	<ul>
		<li><code>nums[i<sub>1</sub>] &lt; nums[i<sub>2</sub>] &gt; nums[i<sub>3</sub>] &lt; ...</code>，或</li>
		<li><code>nums[i<sub>1</sub>] &gt; nums[i<sub>2</sub>] &lt; nums[i<sub>3</sub>] &gt; ...</code></li>
	</ul>
	</li>
</ul>

<p>长度为 1 的&nbsp;<strong>子序列&nbsp;</strong>也被认为符合&nbsp;<strong>严格交替&nbsp;</strong>。一个<strong>&nbsp;有效&nbsp;</strong>子序列的得分为其所选元素值的<strong>&nbsp;总和</strong>。</p>

<p>返回一个整数，表示有效子序列可能取得的<strong>&nbsp;最大</strong><strong>得分</strong>。<br />
<br />
<strong>子序列&nbsp;</strong>是指通过删除原数组中的某些元素或不删除任何元素，并且不改变剩余元素相对顺序后得到的数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,4,2], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<p>一种最优选择是下标 <code>[0, 2]</code>，对应的值为 <code>[5, 2]</code>。</p>

<ul>
	<li>距离条件成立，因为 <code>2 - 0 = 2 &gt;= k</code>。</li>
	<li>这些值严格交替，因为 <code>5 &gt; 2</code>。</li>
</ul>

<p>得分为 <code>5 + 2 = 7</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,5,4,2,4], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">14</span></p>

<p><strong>解释：</strong></p>

<p>一种最优选择是下标 <code>[0, 1, 3, 4]</code>，对应的值为 <code>[3, 5, 2, 4]</code>。</p>

<ul>
	<li>距离条件成立，因为任意两个相邻选中下标之差都至少为 <code>k = 1</code>。</li>
	<li>这些值严格交替，因为 <code>3 &lt; 5 &gt; 2 &lt; 4</code>。</li>
</ul>

<p>得分为 <code>3 + 5 + 2 + 4 = 14</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>唯一的有效子序列是 <code>[5]</code>。长度为 1 的子序列始终是严格交替的，因此得分为 5。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划 + 树状数组

<!-- thinking:start -->

> **思考**
>
> 朴素子序列 DP 若枚举所有距离至少为 $k$ 的前驱，时间为 $O(n^2)$，在 $n\le 10^5$ 下无法接受。转移还带有「前驱更大 / 更小」的值域约束。
>
> 令 $f[i][0]$ 表示以 $i$ 结尾且当前为谷，$f[i][1]$ 表示当前为峰。谷只能接在更大的峰之后，峰只能接在更小的谷之后，且下标间隔至少 $k$。
>
> 两棵树状数组按离散化后的值域维护 $f[\cdot][0]$ 的前缀最大与 $f[\cdot][1]$ 的后缀最大。处理下标 $i$ 时，先把 $i-k$ 的状态插入树中再查询，这样参与转移的前驱天然满足距离至少为 $k$。

<!-- thinking:end -->

**状态定义**

定义 $f[i][0]$ 表示以下标 $i$ 结尾、且末位元素为**谷**（需要下一个更大才符合交替）的合法子序列最大和；$f[i][1]$ 表示以下标 $i$ 结尾、且末位元素为**峰**（需要下一个更小才符合交替）的合法子序列最大和。

**状态转移**

转移时枚举满足 $j \leq i - k$ 的前驱下标 $j$：

- 状态 $f[i][0]$（谷）：由 $f[j][1]$ 转移，需要 $\text{nums}[j] > \text{nums}[i]$，即查询值域 $(\text{nums}[i],\ +\infty)$ 上 $f[\cdot][1]$ 的最大值：

$$f[i][0] = \text{nums}[i] + \max\!\left(0,\ \max_{\substack{j \leq i-k \\ \text{nums}[j] > \text{nums}[i]}} f[j][1]\right)$$

- 状态 $f[i][1]$（峰）：由 $f[j][0]$ 转移，需要 $\text{nums}[j] < \text{nums}[i]$，即查询值域 $[1,\ \text{nums}[i]-1]$ 上 $f[\cdot][0]$ 的最大值：

$$f[i][1] = \text{nums}[i] + \max\!\left(0,\ \max_{\substack{j \leq i-k \\ \text{nums}[j] < \text{nums}[i]}} f[j][0]\right)$$

最终答案为 $\max_{0 \leq i < n}\max(f[i][0],\ f[i][1])$。

**优化**

上述转移涉及动态的值域前缀/后缀最大值查询，可以用两棵**树状数组**维护：

- 树状数组 $\text{bit}_0$：以值为下标，维护 $f[\cdot][0]$ 的前缀最大值，用于查询 $\text{nums}[j] < \text{nums}[i]$ 的情况。
- 树状数组 $\text{bit}_1$：以 $m + 1 - \textit{rank}$（$m$ 为不同值个数）为倒置下标，维护 $f[\cdot][1]$ 的前缀最大值，等价于值域上的后缀最大值，用于查询 $\text{nums}[j] > \text{nums}[i]$ 的情况。

为了保证只有 $j \leq i - k$ 的下标才能参与转移，在处理第 $i$ 个元素时，先将第 $i - k$ 个元素的状态加入树状数组，再查询。

时间复杂度 $O(n \log m)$，空间复杂度 $O(n + m)$，其中 $n$ 为数组长度，$m$ 为不同值的个数。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, val: int) -> None:
        while x <= self.n:
            self.c[x] = max(self.c[x], val)
            x += x & -x

    def query(self, x: int) -> int:
        ans = 0
        while x > 0:
            ans = max(ans, self.c[x])
            x -= x & -x
        return ans


class Solution:
    def maxAlternatingSum(self, nums: List[int], k: int) -> int:
        vals = sorted(set(nums))
        m = len(vals)
        rank = {v: i + 1 for i, v in enumerate(vals)}
        bit0 = BinaryIndexedTree(m)
        bit1 = BinaryIndexedTree(m)
        n = len(nums)
        f = [[0, 0] for _ in range(n)]
        ans = 0
        for i, x in enumerate(nums):
            if i >= k:
                r = rank[nums[i - k]]
                bit0.update(r, f[i - k][0])
                bit1.update(m + 1 - r, f[i - k][1])
            r = rank[x]
            f[i][0] = x + bit1.query(m - r)
            f[i][1] = x + bit0.query(r - 1)
            ans = max(ans, f[i][0], f[i][1])
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    private final int n;
    private final long[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    void update(int x, long val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += x & -x;
        }
    }

    long query(int x) {
        long ans = 0;
        while (x > 0) {
            ans = Math.max(ans, c[x]);
            x -= x & -x;
        }
        return ans;
    }
}

class Solution {
    public long maxAlternatingSum(int[] nums, int k) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }
        BinaryIndexedTree bit0 = new BinaryIndexedTree(m);
        BinaryIndexedTree bit1 = new BinaryIndexedTree(m);
        int n = nums.length;
        long[][] f = new long[n][2];
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k) {
                int r = rank(sorted, m, nums[i - k]);
                bit0.update(r, f[i - k][0]);
                bit1.update(m + 1 - r, f[i - k][1]);
            }
            int r = rank(sorted, m, nums[i]);
            f[i][0] = nums[i] + bit1.query(m - r);
            f[i][1] = nums[i] + bit0.query(r - 1);
            ans = Math.max(ans, Math.max(f[i][0], f[i][1]));
        }
        return ans;
    }

    private int rank(int[] sorted, int m, int x) {
        int l = 0, r = m;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (sorted[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
public:
    explicit BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, long long val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += x & -x;
        }
    }

    long long query(int x) {
        long long ans = 0;
        while (x > 0) {
            ans = max(ans, c[x]);
            x -= x & -x;
        }
        return ans;
    }

private:
    int n;
    vector<long long> c;
};

class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums, int k) {
        vector<int> sorted = nums;
        ranges::sort(sorted);
        sorted.erase(unique(sorted.begin(), sorted.end()), sorted.end());
        int m = sorted.size();
        auto rank = [&](int x) {
            return ranges::lower_bound(sorted, x) - sorted.begin() + 1;
        };
        BinaryIndexedTree bit0(m), bit1(m);
        int n = nums.size();
        vector<array<long long, 2>> f(n);
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k) {
                int r = rank(nums[i - k]);
                bit0.update(r, f[i - k][0]);
                bit1.update(m + 1 - r, f[i - k][1]);
            }
            int r = rank(nums[i]);
            f[i][0] = nums[i] + bit1.query(m - r);
            f[i][1] = nums[i] + bit0.query(r - 1);
            ans = max({ans, f[i][0], f[i][1]});
        }
        return ans;
    }
};
```

#### Go

```go
type fenwick []int64

func (f fenwick) update(i int, val int64) {
	for ; i < len(f); i += i & -i {
		f[i] = max(f[i], val)
	}
}

func (f fenwick) query(i int) (res int64) {
	for ; i > 0; i &= i - 1 {
		res = max(res, f[i])
	}
	return
}

func maxAlternatingSum(nums []int, k int) (ans int64) {
	sorted := slices.Clone(nums)
	slices.Sort(sorted)
	sorted = slices.Compact(sorted)
	m := len(sorted)
	bit0 := make(fenwick, m+1)
	bit1 := make(fenwick, m+1)
	n := len(nums)
	f := make([][2]int64, n)
	for i, x := range nums {
		if i >= k {
			r := sort.SearchInts(sorted, nums[i-k]) + 1
			bit0.update(r, f[i-k][0])
			bit1.update(m+1-r, f[i-k][1])
		}
		r := sort.SearchInts(sorted, x) + 1
		f[i][0] = int64(x) + bit1.query(m-r)
		f[i][1] = int64(x) + bit0.query(r-1)
		ans = max(ans, f[i][0], f[i][1])
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
