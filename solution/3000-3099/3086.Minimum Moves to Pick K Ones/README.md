---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3086.Minimum%20Moves%20to%20Pick%20K%20Ones/README.md
rating: 2672
source: 第 389 场周赛 Q4
tags:
    - 贪心
    - 数组
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [3086. 拾起 K 个 1 需要的最少行动次数](https://leetcode.cn/problems/minimum-moves-to-pick-k-ones)

[English Version](/solution/3000-3099/3086.Minimum%20Moves%20to%20Pick%20K%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的二进制数组 <code>nums</code>，其长度为 <code>n</code> ；另给你一个 <strong>正整数 </strong><code>k</code> 以及一个 <strong>非负整数 </strong><code>maxChanges</code> 。</p>

<p>Alice 在玩一个游戏，游戏的目标是让&nbsp;Alice 使用 <strong>最少 </strong>数量的 <strong>行动 </strong>次数从 <code>nums</code> 中拾起 <code>k</code> 个 1 。游戏开始时，Alice 可以选择数组 <code>[0, n - 1]</code> 范围内的任何索引&nbsp;<code>aliceIndex</code> 站立。如果 <code>nums[aliceIndex] == 1</code> ，Alice 会拾起一个 1 ，并且 <code>nums[aliceIndex]</code> 变成<code>0</code>（这<strong> 不算 </strong>作一次行动）。之后，Alice 可以执行 <strong>任意数量</strong> 的 <strong>行动</strong>（<strong>包括</strong><strong>零次</strong>），在每次行动中&nbsp;Alice 必须 <strong>恰好 </strong>执行以下动作之一：</p>

<ul>
	<li>选择任意一个下标 <code>j != aliceIndex</code> 且满足 <code>nums[j] == 0</code> ，然后将 <code>nums[j]</code> 设置为 <code>1</code> 。这个动作最多可以执行 <code>maxChanges</code> 次。</li>
	<li>选择任意两个相邻的下标 <code>x</code> 和 <code>y</code>（<code>|x - y| == 1</code>）且满足 <code>nums[x] == 1</code>, <code>nums[y] == 0</code> ，然后交换它们的值（将 <code>nums[y] = 1</code> 和 <code>nums[x] = 0</code>）。如果 <code>y == aliceIndex</code>，在这次行动后&nbsp;Alice 拾起一个 1 ，并且 <code>nums[y]</code> 变成 <code>0</code> 。</li>
</ul>

<p>返回&nbsp;Alice 拾起 <strong>恰好 </strong><code>k</code> 个 1 所需的 <strong>最少 </strong>行动次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">3</span></p>

<p><strong>解释：</strong>如果游戏开始时&nbsp;Alice 在 <code>aliceIndex == 1</code> 的位置上，按照以下步骤执行每个动作，他可以利用 <code>3</code> 次行动拾取 <code>3</code> 个 1 ：</p>

<ul>
	<li>游戏开始时&nbsp;Alice 拾取了一个 1 ，<code>nums[1]</code> 变成了 <code>0</code>。此时 <code>nums</code> 变为 <code>[1,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code> 。</li>
	<li>选择 <code>j == 2</code> 并执行第一种类型的动作。<code>nums</code> 变为 <code>[1,<strong><u>0</u></strong>,1,0,0,1,1,0,0,1]</code></li>
	<li>选择 <code>x == 2</code> 和 <code>y == 1</code> ，并执行第二种类型的动作。<code>nums</code> 变为 <code>[1,<strong><u>1</u></strong>,0,0,0,1,1,0,0,1]</code> 。由于 <code>y == aliceIndex</code>，Alice 拾取了一个 1 ，<code>nums</code> 变为&nbsp; <code>[1,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code> 。</li>
	<li>选择 <code>x == 0</code> 和 <code>y == 1</code> ，并执行第二种类型的动作。<code>nums</code> 变为 <code>[0,<strong><u>1</u></strong>,0,0,0,1,1,0,0,1]</code> 。由于 <code>y == aliceIndex</code>，Alice 拾取了一个 1 ，<code>nums</code> 变为&nbsp; <code>[0,<strong><u>0</u></strong>,0,0,0,1,1,0,0,1]</code> 。</li>
</ul>

<p>请注意，Alice 也可能执行其他的 <code>3</code> 次行动序列达成拾取 <code>3</code> 个 1 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;"><!-- 以下是示例内容的中文翻译，同时保留了原有的HTML格式和注释 -->
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [0,0,0,0], k = 2, maxChanges = 3</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">4</span></p>

<p><strong>解释：</strong>如果游戏开始时&nbsp;Alice 在 <code>aliceIndex == 0</code> 的位置上，按照以下步骤执行每个动作，他可以利用 <code>4</code> 次行动拾取 <code>2</code> 个 1 ：</p>

<ul>
	<li>选择 <code>j == 1</code> 并执行第一种类型的动作。<code>nums</code> 变为 <code>[<strong><u>0</u></strong>,1,0,0]</code> 。</li>
	<li>选择 <code>x == 1</code> 和 <code>y == 0</code> ，并执行第二种类型的动作。<code>nums</code> 变为 <code>[<strong><u>1</u></strong>,0,0,0]</code> 。由于 <code>y == aliceIndex</code>，Alice 拾起了一个 1 ，<code>nums</code> 变为 <code>[<strong><u>0</u></strong>,0,0,0]</code> 。</li>
	<li>再次选择 <code>j == 1</code> 并执行第一种类型的动作。<code>nums</code> 变为 <code>[<strong><u>0</u></strong>,1,0,0]</code> 。</li>
	<li>再次选择 <code>x == 1</code> 和 <code>y == 0</code> ，并执行第二种类型的动作。<code>nums</code> 变为 <code>[<strong><u>1</u></strong>,0,0,0]</code> 。由于<code>y == aliceIndex</code>，Alice 拾起了一个 1 ，<code>nums</code> 变为 <code>[<strong><u>0</u></strong>,0,0,0]</code> 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxChanges &lt;= 10<sup>5</sup></code></li>
	<li><code>maxChanges + sum(nums) &gt;= k</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 前缀和 + 二分查找

我们考虑枚举 Alice 的站立位置 $i$，对于每个 $i$，我们按照如下的策略进行操作：

- 首先，如果位置 $i$ 的数字为 $1$，我们可以直接拾取一个 $1$，不需要行动次数。
- 然后，我们对 $i$ 的左右两侧位置的数字 $1$ 进行拾取，执行的是行动 $2$，即把位置 $i-1$ 的 $1$ 移到位置 $i$，然后拾取；把位置 $i+1$ 的 $1$ 移到位置 $i$，然后拾取。每拾取一个 $1$，需要 $1$ 次行动。
- 接下来，我们最大限度地将 $i-1$ 或 $i+1$ 上的 $0$，利用行动 $1$，将其置为 $1$，然后利用行动 $2$，将其移动到位置 $i$，拾取。直到拾取的 $1$ 的数量达到 $k$ 或者行动 $1$ 的次数达到 $\textit{maxChanges}$。我们假设行动 $1$ 的次数为 $c$，那么总共需要 $2c$ 次行动。
- 利用完行动 $1$，如果拾取的 $1$ 的数量还没有达到 $k$，我们需要继续考虑在 $[1,..i-2]$ 和 $[i+2,..n]$ 的区间内，进行行动 $2$，将 $1$ 移动到位置 $i$，拾取。我们可以使用二分查找来确定这个区间的大小，使得拾取的 $1$ 的数量达到 $k$。具体地，我们二分枚举一个区间的大小 $d$，然后在区间 $[i-d,..i-2]$ 和 $[i+2,..i+d]$ 内，进行行动 $2$，将 $1$ 移动到位置 $i$，拾取。如果拾取的 $1$ 的数量达到 $k$，我们就更新答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMoves(self, nums: List[int], k: int, maxChanges: int) -> int:
        n = len(nums)
        cnt = [0] * (n + 1)
        s = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            cnt[i] = cnt[i - 1] + x
            s[i] = s[i - 1] + i * x
        ans = inf
        max = lambda x, y: x if x > y else y
        min = lambda x, y: x if x < y else y
        for i, x in enumerate(nums, 1):
            t = 0
            need = k - x
            for j in (i - 1, i + 1):
                if need > 0 and 1 <= j <= n and nums[j - 1] == 1:
                    need -= 1
                    t += 1
            c = min(need, maxChanges)
            need -= c
            t += c * 2
            if need <= 0:
                ans = min(ans, t)
                continue
            l, r = 2, max(i - 1, n - i)
            while l <= r:
                mid = (l + r) >> 1
                l1, r1 = max(1, i - mid), max(0, i - 2)
                l2, r2 = min(n + 1, i + 2), min(n, i + mid)
                c1 = cnt[r1] - cnt[l1 - 1]
                c2 = cnt[r2] - cnt[l2 - 1]
                if c1 + c2 >= need:
                    t1 = c1 * i - (s[r1] - s[l1 - 1])
                    t2 = s[r2] - s[l2 - 1] - c2 * i
                    ans = min(ans, t + t1 + t2)
                    r = mid - 1
                else:
                    l = mid + 1
        return ans
```

#### Java

```java
class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            cnt[i] = cnt[i - 1] + nums[i - 1];
            s[i] = s[i - 1] + i * nums[i - 1];
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            long t = 0;
            int need = k - nums[i - 1];
            for (int j = i - 1; j <= i + 1; j += 2) {
                if (need > 0 && 1 <= j && j <= n && nums[j - 1] == 1) {
                    --need;
                    ++t;
                }
            }
            int c = Math.min(need, maxChanges);
            need -= c;
            t += c * 2;
            if (need <= 0) {
                ans = Math.min(ans, t);
                continue;
            }
            int l = 2, r = Math.max(i - 1, n - i);
            while (l <= r) {
                int mid = (l + r) >> 1;
                int l1 = Math.max(1, i - mid), r1 = Math.max(0, i - 2);
                int l2 = Math.min(n + 1, i + 2), r2 = Math.min(n, i + mid);
                int c1 = cnt[r1] - cnt[l1 - 1];
                int c2 = cnt[r2] - cnt[l2 - 1];
                if (c1 + c2 >= need) {
                    long t1 = 1L * c1 * i - (s[r1] - s[l1 - 1]);
                    long t2 = s[r2] - s[l2 - 1] - 1L * c2 * i;
                    ans = Math.min(ans, t + t1 + t2);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumMoves(vector<int>& nums, int k, int maxChanges) {
        int n = nums.size();
        vector<int> cnt(n + 1, 0);
        vector<long long> s(n + 1, 0);

        for (int i = 1; i <= n; ++i) {
            cnt[i] = cnt[i - 1] + nums[i - 1];
            s[i] = s[i - 1] + 1LL * i * nums[i - 1];
        }

        long long ans = LLONG_MAX;

        for (int i = 1; i <= n; ++i) {
            long long t = 0;
            int need = k - nums[i - 1];

            for (int j = i - 1; j <= i + 1; j += 2) {
                if (need > 0 && 1 <= j && j <= n && nums[j - 1] == 1) {
                    --need;
                    ++t;
                }
            }

            int c = min(need, maxChanges);
            need -= c;
            t += c * 2;

            if (need <= 0) {
                ans = min(ans, t);
                continue;
            }

            int l = 2, r = max(i - 1, n - i);

            while (l <= r) {
                int mid = (l + r) / 2;
                int l1 = max(1, i - mid), r1 = max(0, i - 2);
                int l2 = min(n + 1, i + 2), r2 = min(n, i + mid);

                int c1 = cnt[r1] - cnt[l1 - 1];
                int c2 = cnt[r2] - cnt[l2 - 1];

                if (c1 + c2 >= need) {
                    long long t1 = 1LL * c1 * i - (s[r1] - s[l1 - 1]);
                    long long t2 = s[r2] - s[l2 - 1] - 1LL * c2 * i;
                    ans = min(ans, t + t1 + t2);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func minimumMoves(nums []int, k int, maxChanges int) int64 {
	n := len(nums)
	cnt := make([]int, n+1)
	s := make([]int, n+1)

	for i := 1; i <= n; i++ {
		cnt[i] = cnt[i-1] + nums[i-1]
		s[i] = s[i-1] + i*nums[i-1]
	}

	ans := math.MaxInt64

	for i := 1; i <= n; i++ {
		t := 0
		need := k - nums[i-1]

		for _, j := range []int{i - 1, i + 1} {
			if need > 0 && 1 <= j && j <= n && nums[j-1] == 1 {
				need--
				t++
			}
		}

		c := min(need, maxChanges)
		need -= c
		t += c * 2

		if need <= 0 {
			ans = min(ans, t)
			continue
		}

		l, r := 2, max(i-1, n-i)

		for l <= r {
			mid := (l + r) >> 1
			l1, r1 := max(1, i-mid), max(0, i-2)
			l2, r2 := min(n+1, i+2), min(n, i+mid)

			c1 := cnt[r1] - cnt[l1-1]
			c2 := cnt[r2] - cnt[l2-1]

			if c1+c2 >= need {
				t1 := c1*i - (s[r1] - s[l1-1])
				t2 := s[r2] - s[l2-1] - c2*i
				ans = min(ans, t+t1+t2)
				r = mid - 1
			} else {
				l = mid + 1
			}
		}
	}

	return int64(ans)
}
```

#### TypeScript

```ts
function minimumMoves(nums: number[], k: number, maxChanges: number): number {
    const n = nums.length;
    const cnt = Array(n + 1).fill(0);
    const s = Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        cnt[i] = cnt[i - 1] + nums[i - 1];
        s[i] = s[i - 1] + i * nums[i - 1];
    }

    let ans = Infinity;
    for (let i = 1; i <= n; i++) {
        let t = 0;
        let need = k - nums[i - 1];

        for (let j of [i - 1, i + 1]) {
            if (need > 0 && 1 <= j && j <= n && nums[j - 1] === 1) {
                need--;
                t++;
            }
        }

        const c = Math.min(need, maxChanges);
        need -= c;
        t += c * 2;

        if (need <= 0) {
            ans = Math.min(ans, t);
            continue;
        }

        let l = 2,
            r = Math.max(i - 1, n - i);

        while (l <= r) {
            const mid = (l + r) >> 1;
            const [l1, r1] = [Math.max(1, i - mid), Math.max(0, i - 2)];
            const [l2, r2] = [Math.min(n + 1, i + 2), Math.min(n, i + mid)];

            const c1 = cnt[r1] - cnt[l1 - 1];
            const c2 = cnt[r2] - cnt[l2 - 1];

            if (c1 + c2 >= need) {
                const t1 = c1 * i - (s[r1] - s[l1 - 1]);
                const t2 = s[r2] - s[l2 - 1] - c2 * i;
                ans = Math.min(ans, t + t1 + t2);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
