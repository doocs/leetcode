---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1482.Minimum%20Number%20of%20Days%20to%20Make%20m%20Bouquets/README.md
rating: 1945
source: 第 193 场周赛 Q3
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1482. 制作 m 束花所需的最少天数](https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets)

[English Version](/solution/1400-1499/1482.Minimum%20Number%20of%20Days%20to%20Make%20m%20Bouquets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>bloomDay</code>，以及两个整数 <code>m</code> 和 <code>k</code> 。</p>

<p>现需要制作 <code>m</code> 束花。制作花束时，需要使用花园中 <strong>相邻的 <code>k</code> 朵花</strong> 。</p>

<p>花园中有 <code>n</code> 朵花，第 <code>i</code> 朵花会在 <code>bloomDay[i]</code> 时盛开，<strong>恰好</strong> 可以用于 <strong>一束</strong> 花中。</p>

<p>请你返回从花园中摘 <code>m</code> 束花需要等待的最少的天数。如果不能摘到 <code>m</code> 束花则返回 <strong>-1</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>bloomDay = [1,10,3,10,2], m = 3, k = 1
<strong>输出：</strong>3
<strong>解释：</strong>让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
现在需要制作 3 束花，每束只需要 1 朵。
1 天后：[x, _, _, _, _]   // 只能制作 1 束花
2 天后：[x, _, _, _, x]   // 只能制作 2 束花
3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>bloomDay = [1,10,3,10,2], m = 3, k = 2
<strong>输出：</strong>-1
<strong>解释：</strong>要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
<strong>输出：</strong>12
<strong>解释：</strong>要制作 2 束花，每束需要 3 朵。
花园在 7 天后和 12 天后的情况如下：
7 天后：[x, x, x, x, _, x, x]
可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
12 天后：[x, x, x, x, x, x, x]
显然，我们可以用不同的方式制作两束花。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>bloomDay = [1000000000,1000000000], m = 1, k = 1
<strong>输出：</strong>1000000000
<strong>解释：</strong>需要等 1000000000 天才能采到花来制作花束
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>bloomDay.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= bloomDay[i] &lt;= 10^9</code></li>
	<li><code>1 &lt;= m &lt;= 10^6</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

根据题目描述，如果一个天数 $t$ 可以满足制作 $m$ 束花，那么对任意 $t' > t$，也一定可以满足制作 $m$ 束花。因此，我们可以使用二分查找的方法找到最小的满足制作 $m$ 束花的天数。

我们记花园中最大的开花天数为 $mx$，接下来，我们定义二分查找的左边界 $l = 1$，右边界 $r = mx + 1$。

然后，我们进行二分查找，对于每一个中间值 $\textit{mid} = \frac{l + r}{2}$，我们判断是否可以制作 $m$ 束花。如果可以，我们将右边界 $r$ 更新为 $\textit{mid}$，否则，我们将左边界 $l$ 更新为 $\textit{mid} + 1$。

最终，当 $l = r$ 时，结束二分查找。此时如果 $l > mx$，说明无法制作 $m$ 束花，返回 $-1$，否则返回 $l$。

因此，问题转换为判断一个天数 $\textit{days}$ 是否可以制作 $m$ 束花。

我们可以使用一个函数 $\text{check}(\textit{days})$ 来判断是否可以制作 $m$ 束花。具体地，我们从左到右遍历花园中的每一朵花，如果当前花开的天数小于等于 $\textit{days}$，我们将当前花加入到当前花束中，否则，我们将当前花束清空。当当前花束中的花的数量等于 $k$ 时，我们将当前花束的数量加一，并清空当前花束。最后，我们判断当前花束的数量是否大于等于 $m$，如果是，说明可以制作 $m$ 束花，否则，说明无法制作 $m$ 束花。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别为花园中的花的数量和最大的开花天数，本题中 $M \leq 10^9$。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDays(self, bloomDay: List[int], m: int, k: int) -> int:
        def check(days: int) -> int:
            cnt = cur = 0
            for x in bloomDay:
                cur = cur + 1 if x <= days else 0
                if cur == k:
                    cnt += 1
                    cur = 0
            return cnt >= m

        mx = max(bloomDay)
        l = bisect_left(range(mx + 2), True, key=check)
        return -1 if l > mx else l
```

#### Java

```java
class Solution {
    private int[] bloomDay;
    private int m, k;

    public int minDays(int[] bloomDay, int m, int k) {
        this.bloomDay = bloomDay;
        this.m = m;
        this.k = k;
        final int mx = (int) 1e9;
        int l = 1, r = mx + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > mx ? -1 : l;
    }

    private boolean check(int days) {
        int cnt = 0, cur = 0;
        for (int x : bloomDay) {
            cur = x <= days ? cur + 1 : 0;
            if (cur == k) {
                ++cnt;
                cur = 0;
            }
        }
        return cnt >= m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDays(vector<int>& bloomDay, int m, int k) {
        int mx = ranges::max(bloomDay);
        int l = 1, r = mx + 1;
        auto check = [&](int days) {
            int cnt = 0, cur = 0;
            for (int x : bloomDay) {
                cur = x <= days ? cur + 1 : 0;
                if (cur == k) {
                    cnt++;
                    cur = 0;
                }
            }
            return cnt >= m;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > mx ? -1 : l;
    }
};
```

#### Go

```go
func minDays(bloomDay []int, m int, k int) int {
	mx := slices.Max(bloomDay)
	if l := sort.Search(mx+2, func(days int) bool {
		cnt, cur := 0, 0
		for _, x := range bloomDay {
			if x <= days {
				cur++
				if cur == k {
					cnt++
					cur = 0
				}
			} else {
				cur = 0
			}
		}
		return cnt >= m
	}); l <= mx {
		return l
	}
	return -1

}
```

#### TypeScript

```ts
function minDays(bloomDay: number[], m: number, k: number): number {
    const mx = Math.max(...bloomDay);
    let [l, r] = [1, mx + 1];
    const check = (days: number): boolean => {
        let [cnt, cur] = [0, 0];
        for (const x of bloomDay) {
            cur = x <= days ? cur + 1 : 0;
            if (cur === k) {
                cnt++;
                cur = 0;
            }
        }
        return cnt >= m;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > mx ? -1 : l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
