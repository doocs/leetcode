---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2234.Maximum%20Total%20Beauty%20of%20the%20Gardens/README.md
rating: 2561
source: 第 288 场周赛 Q4
tags:
    - 贪心
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [2234. 花园的最大总美丽值](https://leetcode.cn/problems/maximum-total-beauty-of-the-gardens)

[English Version](/solution/2200-2299/2234.Maximum%20Total%20Beauty%20of%20the%20Gardens/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 是&nbsp;<code>n</code>&nbsp;个花园的园丁，她想通过种花，最大化她所有花园的总美丽值。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为 <code>n</code>&nbsp;的整数数组&nbsp;<code>flowers</code>&nbsp;，其中&nbsp;<code>flowers[i]</code>&nbsp;是第 <code>i</code>&nbsp;个花园里已经种的花的数目。已经种了的花 <strong>不能</strong>&nbsp;移走。同时给你&nbsp;<code>newFlowers</code>&nbsp;，表示 Alice 额外可以种花的&nbsp;<strong>最大数目</strong>&nbsp;。同时给你的还有整数&nbsp;<code>target</code>&nbsp;，<code>full</code>&nbsp;和&nbsp;<code>partial</code>&nbsp;。</p>

<p>如果一个花园有 <strong>至少</strong>&nbsp;<code>target</code>&nbsp;朵花，那么这个花园称为 <strong>完善的</strong>&nbsp;，花园的 <strong>总美丽值</strong>&nbsp;为以下分数之 <strong>和</strong> ：</p>

<ul>
	<li><b>完善</b> 花园数目乘以&nbsp;<code>full</code>.</li>
	<li>剩余 <strong>不完善</strong>&nbsp;花园里，花的 <strong>最少数目</strong>&nbsp;乘以&nbsp;<code>partial</code>&nbsp;。如果没有不完善花园，那么这一部分的值为&nbsp;<code>0</code>&nbsp;。</li>
</ul>

<p>请你返回 Alice 种最多 <code>newFlowers</code>&nbsp;朵花以后，能得到的<strong>&nbsp;最大</strong>&nbsp;总美丽值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
<b>输出：</b>14
<b>解释：</b>Alice 可以按以下方案种花
- 在第 0 个花园种 2 朵花
- 在第 1 个花园种 3 朵花
- 在第 2 个花园种 1 朵花
- 在第 3 个花园种 1 朵花
花园里花的数目为 [3,6,2,2] 。总共种了 2 + 3 + 1 + 1 = 7 朵花。
只有 1 个花园是完善的。
不完善花园里花的最少数目是 2 。
所以总美丽值为 1 * 12 + 2 * 1 = 12 + 2 = 14 。
没有其他方案可以让花园总美丽值超过 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
<b>输出：</b>30
<b>解释：</b>Alice 可以按以下方案种花
- 在第 0 个花园种 3 朵花
- 在第 1 个花园种 0 朵花
- 在第 2 个花园种 0 朵花
- 在第 3 个花园种 2 朵花
花园里花的数目为 [5,4,5,5] 。总共种了 3 + 0 + 0 + 2 = 5 朵花。
有 3 个花园是完善的。
不完善花园里花的最少数目为 4 。
所以总美丽值为 3 * 2 + 4 * 6 = 6 + 24 = 30 。
没有其他方案可以让花园总美丽值超过 30 。
注意，Alice可以让所有花园都变成完善的，但这样她的总美丽值反而更小。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= flowers[i], target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= newFlowers &lt;= 10<sup>10</sup></code></li>
	<li><code>1 &lt;= full, partial &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 二分查找

我们注意到，如果一个花园中种的花的数目已经大于等于 $\textit{target}$，那么这个花园就已经是完善的花园，不能再改变。而不完善的花园中，可以通过种更多的花来使得这个花园变成完善的花园。

我们不妨枚举有多少个花园最终成为完善的花园，假设初始时有 $x$ 个完善的花园，那么我们可以在 $[x, n]$ 范围内枚举。我们应该选择哪些不完善花园变成完善花园呢？实际上，我们应该选择那么花的数目较多的花园，这样才能使得最终剩下的可额外种植的花更多，将这些花用于提升不完善花园的最小值。因此，我们对数组 $\textit{flowers}$ 进行排序。

接下来，我们枚举完善花园的数目 $x$，那么当前要变成完善花园的是 $\textit{target}[n-x]$，需要种植的花的数量为 $\max(0, \textit{target} - \textit{flowers}[n - x])$。

我们更新剩余可种植的花 $\textit{newFlowers}$，如果小于 $0$，说明已经不能将更多的花园变成完善花园了，直接退出枚举。

否则，我们在 $[0,..n-x-1]$ 范围内，二分查找可以把不完善花园变成完善花园的最大下标。记下标为 $l$，那么所需要种植的花的数量为 $\textit{cost} = \textit{flowers}[l] \times (l + 1) - s[l + 1]$，其中 $s[i]$ 是 $\textit{flowers}$ 数组中前 $i$ 个数之和。如果此时还能提升最小值的大小，我们算出能提升的幅度 $\frac{\textit{newFlowers} - \textit{cost}}{l + 1}$，并且保证最终的最小值不超过 $\textit{target}-1$。即最小值 $y = \min(\textit{flowers}[l] + \frac{\textit{newFlowers} - \textit{cost}}{l + 1}, \textit{target} - 1)$。那么此时花园的美丽值为 $x \times \textit{full} + y \times \textit{partial}$。答案为所有美丽值的最大值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{flowers}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBeauty(
        self, flowers: List[int], newFlowers: int, target: int, full: int, partial: int
    ) -> int:
        flowers.sort()
        n = len(flowers)
        s = list(accumulate(flowers, initial=0))
        ans, i = 0, n - bisect_left(flowers, target)
        for x in range(i, n + 1):
            newFlowers -= 0 if x == 0 else max(target - flowers[n - x], 0)
            if newFlowers < 0:
                break
            l, r = 0, n - x - 1
            while l < r:
                mid = (l + r + 1) >> 1
                if flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers:
                    l = mid
                else:
                    r = mid - 1
            y = 0
            if r != -1:
                cost = flowers[l] * (l + 1) - s[l + 1]
                y = min(flowers[l] + (newFlowers - cost) // (l + 1), target - 1)
            ans = max(ans, x * full + y * partial)
        return ans
```

#### Java

```java
class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + flowers[i];
        }
        long ans = 0;
        int x = 0;
        for (int v : flowers) {
            if (v >= target) {
                ++x;
            }
        }
        for (; x <= n; ++x) {
            newFlowers -= (x == 0 ? 0 : Math.max(target - flowers[n - x], 0));
            if (newFlowers < 0) {
                break;
            }
            int l = 0, r = n - x - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if ((long) flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            long y = 0;
            if (r != -1) {
                long cost = (long) flowers[l] * (l + 1) - s[l + 1];
                y = Math.min(flowers[l] + (newFlowers - cost) / (l + 1), target - 1);
            }
            ans = Math.max(ans, (long) x * full + y * partial);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumBeauty(vector<int>& flowers, long long newFlowers, int target, int full, int partial) {
        sort(flowers.begin(), flowers.end());
        int n = flowers.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + flowers[i - 1];
        }
        long long ans = 0;
        int i = flowers.end() - lower_bound(flowers.begin(), flowers.end(), target);
        for (int x = i; x <= n; ++x) {
            newFlowers -= (x == 0 ? 0 : max(target - flowers[n - x], 0));
            if (newFlowers < 0) {
                break;
            }
            int l = 0, r = n - x - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if (1LL * flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int y = 0;
            if (r != -1) {
                long long cost = 1LL * flowers[l] * (l + 1) - s[l + 1];
                long long mx = flowers[l] + (newFlowers - cost) / (l + 1);
                long long threshold = target - 1;
                y = min(mx, threshold);
            }
            ans = max(ans, 1LL * x * full + 1LL * y * partial);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumBeauty(flowers []int, newFlowers int64, target int, full int, partial int) int64 {
	sort.Ints(flowers)
	n := len(flowers)
	s := make([]int, n+1)
	for i, x := range flowers {
		s[i+1] = s[i] + x
	}
	ans := 0
	i := n - sort.SearchInts(flowers, target)
	for x := i; x <= n; x++ {
		if x > 0 {
			newFlowers -= int64(max(target-flowers[n-x], 0))
		}
		if newFlowers < 0 {
			break
		}
		l, r := 0, n-x-1
		for l < r {
			mid := (l + r + 1) >> 1
			if int64(flowers[mid]*(mid+1)-s[mid+1]) <= newFlowers {
				l = mid
			} else {
				r = mid - 1
			}
		}
		y := 0
		if r != -1 {
			cost := flowers[l]*(l+1) - s[l+1]
			y = min(flowers[l]+int((newFlowers-int64(cost))/int64(l+1)), target-1)
		}
		ans = max(ans, x*full+y*partial)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function maximumBeauty(
    flowers: number[],
    newFlowers: number,
    target: number,
    full: number,
    partial: number,
): number {
    flowers.sort((a, b) => a - b);
    const n = flowers.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + flowers[i - 1];
    }
    let x = flowers.filter(f => f >= target).length;
    let ans = 0;
    for (; x <= n; ++x) {
        newFlowers -= x === 0 ? 0 : Math.max(target - flowers[n - x], 0);
        if (newFlowers < 0) {
            break;
        }
        let l = 0;
        let r = n - x - 1;
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            if (flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        let y = 0;
        if (r !== -1) {
            const cost = flowers[l] * (l + 1) - s[l + 1];
            y = Math.min(flowers[l] + Math.floor((newFlowers - cost) / (l + 1)), target - 1);
        }
        ans = Math.max(ans, x * full + y * partial);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
